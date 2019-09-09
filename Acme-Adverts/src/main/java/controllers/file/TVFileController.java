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
import domain.TVFile;
import domain.Manager;

import services.ContractService;
import services.TVFileService;
import services.ManagerService;

@Controller
@RequestMapping("/TVFile")
public class TVFileController extends AbstractController {

	@Autowired
	private TVFileService TVFileService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ContractService contractService;

	// Listing -----------------------------------------------------------------

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public ModelAndView list(@RequestParam int contractId) {
	
		 ModelAndView result;
	
		 Contract contract = contractService.findOne(contractId);
		 
		 Collection<TVFile> TVFiles = contract.getTVFiles();
		 
		 System.out.println("TV files: " + TVFiles);
	
		 result = new ModelAndView("TVFile/list");
		 result.addObject("TVFiles", TVFiles);
		 result.addObject("requestURI", "TVFile/list.do");
	
		 return result;
	 }

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contractId) {

		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		Contract contract = contractService.findOne(contractId);
		TVFile TVFile = TVFileService.create(contract);
		
		if (TVFile.getContract().getPakage().getManager().equals(manager) && TVFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(TVFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int TVFileId) {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();
		TVFile TVFile = TVFileService.findOne(TVFileId);

		if (TVFile.getContract().getPakage().getManager().equals(manager) && TVFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(TVFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TVFile TVFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = TVFile.getContract();

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(TVFile);
		} else
			try {
				TVFileService.save(TVFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("contract", contract);
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(TVFile, "file.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int TVFileId) {

		ModelAndView result;

		Manager logged = managerService.findByPrincipal();
		TVFile TVFile = TVFileService.findOne(TVFileId);
		Contract contract = TVFile.getContract();

		if (TVFile.getContract().getPakage().getManager().equals(logged) && TVFile.getContract().getSignManager() == null) {
			try {
				TVFileService.delete(TVFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("TVFile", TVFile);

			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(TVFile, "file.commit.error");
			}
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final TVFile TVFile, final BindingResult binding) {

		ModelAndView result;
		
		Contract contract = TVFile.getContract();

		try {
			this.TVFileService.delete(TVFile);
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(TVFile, "file.commit.error");
		}

		return result;
	}

	// Cancel -----------------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int TVFileId) {
		ModelAndView result;

		TVFile TVFile = TVFileService.findOne(TVFileId);
		Contract contract = TVFile.getContract();

		try {
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(TVFile, "file.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final TVFile TVFile) {

		ModelAndView result = this.createEditModelAndView(TVFile, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final TVFile TVFile, final String messageCode) {

		ModelAndView result = new ModelAndView("TVFile/edit");

		result.addObject("TVFile", TVFile);
		result.addObject("message", messageCode);
		return result;

	}
}