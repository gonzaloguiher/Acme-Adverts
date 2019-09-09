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
import domain.InfoFile;
import domain.Manager;

import services.ContractService;
import services.InfoFileService;
import services.ManagerService;

@Controller
@RequestMapping("/infoFile")
public class InfoFileController extends AbstractController {

	@Autowired
	private InfoFileService infoFileService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ContractService contractService;

	// Listing -----------------------------------------------------------------

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public ModelAndView list(@RequestParam int contractId) {
	
		 ModelAndView result;
	
		 Contract contract = contractService.findOne(contractId);
		 
		 Collection<InfoFile> infoFiles = contract.getInfoFiles();
	
		 System.out.println("Info files: " + infoFiles);
		 
		 result = new ModelAndView("file/list");
		 result.addObject("infoFiles", infoFiles);
		 result.addObject("requestURI", "file/list.do");
	
		 return result;
	 }

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contractId) {

		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		Contract contract = contractService.findOne(contractId);
		InfoFile infoFile = infoFileService.create(contract);
		
		if (infoFile.getContract().getPakage().getManager().equals(manager) && infoFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(infoFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int infoFileId) {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();
		InfoFile infoFile = infoFileService.findOne(infoFileId);

		if (infoFile.getContract().getPakage().getManager().equals(manager) && infoFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(infoFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final InfoFile infoFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = infoFile.getContract();

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(infoFile);
		} else
			try {
				infoFileService.save(infoFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("contract", contract);
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(infoFile, "file.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int infoFileId) {

		ModelAndView result;

		Manager logged = managerService.findByPrincipal();
		InfoFile infoFile = infoFileService.findOne(infoFileId);
		Contract contract = infoFile.getContract();

		if (infoFile.getContract().getPakage().getManager().equals(logged) && infoFile.getContract().getSignManager() == null) {
			try {
				infoFileService.delete(infoFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("infoFile", infoFile);

			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(infoFile, "file.commit.error");
			}
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final InfoFile infoFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = infoFile.getContract();
		
		try {
			this.infoFileService.delete(infoFile);
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(infoFile, "file.commit.error");
		}

		return result;
	}

	// Cancel -----------------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int infoFileId) {
		ModelAndView result;

		InfoFile infoFile = infoFileService.findOne(infoFileId);
		Contract contract = infoFile.getContract();

		try {
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(infoFile, "file.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final InfoFile infoFile) {

		ModelAndView result = this.createEditModelAndView(infoFile, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final InfoFile infoFile, final String messageCode) {

		ModelAndView result = new ModelAndView("infoFile/edit");

		result.addObject("infoFile", infoFile);
		result.addObject("message", messageCode);
		return result;

	}	
}