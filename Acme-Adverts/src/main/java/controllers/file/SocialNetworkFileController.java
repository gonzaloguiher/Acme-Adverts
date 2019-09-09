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
import domain.Manager;
import domain.SocialNetworkFile;

import services.ContractService;
import services.ManagerService;
import services.SocialNetworkFileService;

@Controller
@RequestMapping("/socialNetworkFile")
public class SocialNetworkFileController extends AbstractController {

	@Autowired
	private SocialNetworkFileService socialNetworkFileService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ContractService contractService;

	// Listing -----------------------------------------------------------------

	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public ModelAndView list(@RequestParam int contractId) {
	
		 ModelAndView result;
	
		 Contract contract = contractService.findOne(contractId);
		 
		 Collection<SocialNetworkFile> socialNetworkFiles = contract.getSocialNetworkFiles();
		 
		 System.out.println("Social Network files: " + socialNetworkFiles);
	
		 result = new ModelAndView("socialNetworkFile/list");
		 result.addObject("socialNetworkFiles", socialNetworkFiles);
		 result.addObject("requestURI", "socialNetworkFile/list.do");
	
		 return result;
	 }

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contractId) {

		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		Contract contract = contractService.findOne(contractId);
		SocialNetworkFile socialNetworkFile = socialNetworkFileService.create(contract);
		
		if (socialNetworkFile.getContract().getPakage().getManager().equals(manager) && socialNetworkFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(socialNetworkFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialNetworkFileId) {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();
		SocialNetworkFile socialNetworkFile = socialNetworkFileService.findOne(socialNetworkFileId);

		if (socialNetworkFile.getContract().getPakage().getManager().equals(manager) && socialNetworkFile.getContract().getSignManager() == null) {
			result = this.createEditModelAndView(socialNetworkFile);
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialNetworkFile socialNetworkFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = socialNetworkFile.getContract();

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(socialNetworkFile);
		} else
			try {
				socialNetworkFileService.save(socialNetworkFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("contract", contract);
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(socialNetworkFile, "file.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int socialNetworkFileId) {

		ModelAndView result;

		Manager logged = managerService.findByPrincipal();
		SocialNetworkFile socialNetworkFile = socialNetworkFileService.findOne(socialNetworkFileId);
		Contract contract = socialNetworkFile.getContract();

		if (socialNetworkFile.getContract().getPakage().getManager().equals(logged) && socialNetworkFile.getContract().getSignManager() == null) {
			try {
				socialNetworkFileService.delete(socialNetworkFile);
				result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
				result.addObject("socialNetworkFile", socialNetworkFile);

			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(socialNetworkFile, "file.commit.error");
			}
		} else {
			result = new ModelAndView("error/access");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SocialNetworkFile socialNetworkFile, final BindingResult binding) {

		ModelAndView result;

		Contract contract = socialNetworkFile.getContract();
		
		try {
			this.socialNetworkFileService.delete(socialNetworkFile);
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(socialNetworkFile, "file.commit.error");
		}

		return result;
	}

	// Cancel -----------------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int socialNetworkFileId) {
		ModelAndView result;

		SocialNetworkFile socialNetworkFile = socialNetworkFileService.findOne(socialNetworkFileId);
		Contract contract = socialNetworkFile.getContract();

		try {
			result = new ModelAndView("redirect:/contract/manager/show.do?contractId=" + contract.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(socialNetworkFile, "file.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final SocialNetworkFile socialNetworkFile) {

		ModelAndView result = this.createEditModelAndView(socialNetworkFile, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final SocialNetworkFile socialNetworkFile, final String messageCode) {

		ModelAndView result = new ModelAndView("socialNetworkFile/edit");

		result.addObject("socialNetworkFile", socialNetworkFile);
		result.addObject("message", messageCode);
		return result;

	}
	
}
