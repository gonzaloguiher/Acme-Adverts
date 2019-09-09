package controllers;

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
import domain.BillboardFile;
import domain.Manager;

import services.ContractService;
import services.BillboardFileService;
import services.ManagerService;

@Controller
@RequestMapping("/file")
public class FileController extends AbstractController {

	@Autowired
	private BillboardFileService fileService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ContractService contractService;

	// Listing -----------------------------------------------------------------

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public ModelAndView list(@RequestParam int contractId) {
	
		 ModelAndView result;
	
		 Contract contract = contractService.findOne(contractId);
		 
		 Collection<BillboardFile> files = contract.getBillboardFiles();
	
		 result = new ModelAndView("file/list");
		 result.addObject("files", files);
		 result.addObject("requestURI", "file/list.do");
	
		 return result;
	 }

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contractId) {

		Contract contract = contractService.findOne(contractId);
		BillboardFile file = fileService.create(contract);
		
		ModelAndView result = this.createEditModelAndView(file);
		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int fileId) {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();
		BillboardFile file = fileService.findOne(fileId);

		if (file.getContract().getPakage().getManager().equals(manager) && file.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(file);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final BillboardFile file, final BindingResult binding) {

		ModelAndView result;

		Contract contract = file.getContract();

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(file);
		} else
			try {
				fileService.save(file);
				result = new ModelAndView("redirect:/contract/manager/list.do");
				result.addObject("contract", contract);
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(file, "file.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int fileId) {

		ModelAndView result;

		Manager logged = managerService.findByPrincipal();
		BillboardFile file = fileService.findOne(fileId);

		if (file.getContract().getPakage().getManager().equals(logged) && file.getContract().getSignManager() == null) {
			try {
				fileService.delete(file);
				result = new ModelAndView("redirect:/contract/manager/list.do");
				result.addObject("file", file);

			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(file, "file.commit.error");
			}
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final BillboardFile file, final BindingResult binding) {

		ModelAndView result;

		try {
			this.fileService.delete(file);
			result = new ModelAndView("redirect:/contract/manager/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(file, "file.commit.error");
		}

		return result;
	}

	// Cancel -----------------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int fileId) {
		ModelAndView result;

		BillboardFile file = fileService.findOne(fileId);

		try {
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(file, "file.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final BillboardFile file) {

		ModelAndView result = this.createEditModelAndView(file, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final BillboardFile file, final String messageCode) {

		ModelAndView result = new ModelAndView("file/edit");

		result.addObject("file", file);
		result.addObject("message", messageCode);
		return result;

	}
}