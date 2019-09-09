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
import domain.BillboardFile;
import domain.Manager;

import services.ContractService;
import services.BillboardFileService;
import services.ManagerService;

@Controller
@RequestMapping("/billboardFile")
public class BillboardFileController extends AbstractController {

	@Autowired
	private BillboardFileService billboardFileService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ContractService contractService;

	// Listing -----------------------------------------------------------------

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public ModelAndView list(@RequestParam int contractId) {
	
		 ModelAndView result;
	
		 Contract contract = contractService.findOne(contractId);
		 
		 Collection<BillboardFile> billboardFiles = contract.getBillboardFiles();
		 
		 System.out.println("Billboards files: " + billboardFiles);
	
		 result = new ModelAndView("file/list");
		 result.addObject("billboardFiles", billboardFiles);
		 result.addObject("requestURI", "file/list.do");
	
		 return result;
	 }

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contractId) {

		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		Contract contract = contractService.findOne(contractId);
		BillboardFile billboardFile = billboardFileService.create(contract);
		
		if (billboardFile.getContract().getPakage().getManager().equals(manager) && billboardFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(billboardFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int billboardFileId) {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();
		BillboardFile billboardFile = billboardFileService.findOne(billboardFileId);

		if (billboardFile.getContract().getPakage().getManager().equals(manager) && billboardFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(billboardFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final BillboardFile billboardFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = billboardFile.getContract();

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(billboardFile);
		} else
			try {
				billboardFileService.save(billboardFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("contract", contract);
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(billboardFile, "file.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int billboardFileId) {

		ModelAndView result;

		Manager logged = managerService.findByPrincipal();
		BillboardFile billboardFile = billboardFileService.findOne(billboardFileId);
		Contract contract = billboardFile.getContract();

		if (billboardFile.getContract().getPakage().getManager().equals(logged) && billboardFile.getContract().getSignManager() == null) {
			try {
				billboardFileService.delete(billboardFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("billboardFile", billboardFile);

			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(billboardFile, "file.commit.error");
			}
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final BillboardFile billboardFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = billboardFile.getContract();
		
		try {
			this.billboardFileService.delete(billboardFile);
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(billboardFile, "file.commit.error");
		}

		return result;
	}

	// Cancel -----------------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int billboardFileId) {
		ModelAndView result;

		BillboardFile billboardFile = billboardFileService.findOne(billboardFileId);
		Contract contract = billboardFile.getContract();

		try {
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(billboardFile, "file.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final BillboardFile billboardFile) {

		ModelAndView result = this.createEditModelAndView(billboardFile, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final BillboardFile billboardFile, final String messageCode) {

		ModelAndView result = new ModelAndView("billboardFile/edit");

		result.addObject("billboardFile", billboardFile);
		result.addObject("message", messageCode);
		return result;

	}
}