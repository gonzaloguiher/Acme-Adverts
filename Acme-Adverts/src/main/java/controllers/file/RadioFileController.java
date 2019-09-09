package controllers.file;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Contract;
import domain.RadioFile;
import domain.Manager;

import services.ContractService;
import services.RadioFileService;
import services.ManagerService;

@Controller
@RequestMapping("/radioFile")
public class RadioFileController extends AbstractController {

	@Autowired
	private RadioFileService radioFileService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ContractService contractService;

	// Listing -----------------------------------------------------------------

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public ModelAndView list(@RequestParam int contractId) {
	
		 ModelAndView result;
	
		 Contract contract = contractService.findOne(contractId);
		 
		 Collection<RadioFile> radioFiles = contract.getRadioFiles();
		 
		 System.out.println("Radio files: " + radioFiles);
	
		 result = new ModelAndView("radioFile/list");
		 result.addObject("radioFiles", radioFiles);
		 result.addObject("requestURI", "radioFile/list.do");
	
		 return result;
	 }

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contractId) {

		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		Contract contract = contractService.findOne(contractId);
		RadioFile radioFile = radioFileService.create(contract);
		
		if (radioFile.getContract().getPakage().getManager().equals(manager) && radioFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(radioFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int radioFileId) {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();
		RadioFile radioFile = radioFileService.findOne(radioFileId);

		if (radioFile.getContract().getPakage().getManager().equals(manager) && radioFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(radioFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RadioFile radioFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = radioFile.getContract();

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(radioFile);
		} else
			try {
				radioFileService.save(radioFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("contract", contract);
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(radioFile, "file.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int radioFileId) {

		ModelAndView result;

		Manager logged = managerService.findByPrincipal();
		RadioFile radioFile = radioFileService.findOne(radioFileId);
		Contract contract = radioFile.getContract();

		if (radioFile.getContract().getPakage().getManager().equals(logged) && radioFile.getContract().getSignManager() == null) {
			try {
				radioFileService.delete(radioFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("radioFile", radioFile);

			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(radioFile, "file.commit.error");
			}
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final RadioFile radioFile, final BindingResult binding) {

		ModelAndView result;
		
		Contract contract = radioFile.getContract();

		try {
			this.radioFileService.delete(radioFile);
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(radioFile, "file.commit.error");
		}

		return result;
	}

	// Cancel -----------------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int radioFileId) {
		ModelAndView result;

		RadioFile radioFile = radioFileService.findOne(radioFileId);
		Contract contract = radioFile.getContract();

		try {
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(radioFile, "file.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final RadioFile radioFile) {

		ModelAndView result = this.createEditModelAndView(radioFile, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final RadioFile radioFile, final String messageCode) {

		ModelAndView result = new ModelAndView("radioFile/edit");

		result.addObject("radioFile", radioFile);
		result.addObject("message", messageCode);
		return result;

	}
	
}
