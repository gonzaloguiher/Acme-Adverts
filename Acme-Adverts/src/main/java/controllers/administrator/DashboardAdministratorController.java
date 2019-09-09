package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BillboardFileService;
import services.ContractService;
import services.CustomerService;
import services.InfoFileService;
import services.ManagerService;
import services.RadioFileService;
import services.SocialNetworkFileService;
import services.TVFileService;

import controllers.AbstractController;

@Controller
@RequestMapping("/administrator/")
public class DashboardAdministratorController extends AbstractController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private BillboardFileService billboardFileService;
	
	@Autowired
	private InfoFileService infoFileService;
	
	@Autowired
	private RadioFileService radioFileService;
	
	@Autowired
	private TVFileService TVFileService;
	
	@Autowired
	private SocialNetworkFileService socialNetworkFileService;
	
	//DASHBOARD--------------------------------------------------------
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView res;
	
		res = new ModelAndView("administrator/dashboard");

		res.addObject("avgRequestsPerCustomer", Math.round(customerService.getAvgRequestsPerCustomer()*100.0d)/100.0d);
		res.addObject("minRequestsPerCustomer", customerService.getMinRequestsPerCustomer());
		res.addObject("maxRequestsPerCustomer", customerService.getMaxRequestsPerCustomer());
		res.addObject("stdevRequestsPerCustomer", Math.round(customerService.getStdevRequestsPerCustomer()*100.0d)/100.0d);

		res.addObject("avgRequestsPerCustomerStatusPending",  Math.round(customerService.getAvgRequestsPerCustomerStatusPending()*100.0d)/100.0d);
//		res.addObject("avgRequestsPerCustomerStatusApproved", Math.round(customerService.getAvgRequestsPerCustomerStatusApproved()*100.0d)/100.0d);
//		res.addObject("avgRequestsPerCustomerStatusRejected", Math.round(customerService.getAvgRequestsPerCustomerStatusRejected()*100.0d)/100.0d);
		
		res.addObject("minRequestsPerCustomerStatusPending",  customerService.getMinRequestsPerCustomerStatusPending());
//		res.addObject("minRequestsPerCustomerStatusApproved", customerService.getMinRequestsPerCustomerStatusApproved());
//		res.addObject("minRequestsPerCustomerStatusRejected", customerService.getMinRequestsPerCustomerStatusRejected());
		
		res.addObject("maxRequestsPerCustomerStatusPending",  customerService.getMaxRequestsPerCustomerStatusPending());
//		res.addObject("maxRequestsPerCustomerStatusApproved", customerService.getMaxRequestsPerCustomerStatusApproved());
//		res.addObject("maxRequestsPerCustomerStatusRejected", customerService.getMaxRequestsPerCustomerStatusRejected());
		
		res.addObject("customersWith10PercentPendingRequests",  customerService.customersWith10PercentPending());
//		res.addObject("customersWith10PercentApprovedRequests", customerService.customersWith10PercentApproved());
//		res.addObject("customersWith10PercentRejectedRequests", customerService.customersWith10PercentRejected());
		
		res.addObject("avgRequestsPerManager", Math.round(managerService.getAvgRequestsPerManager()*100.0d)/100.0d);
		res.addObject("minRequestsPerManager", managerService.getMinRequestsPerManager());
		res.addObject("maxRequestsPerManager", managerService.getMaxRequestsPerManager());
//		res.addObject("stdevRequestsPerManager", Math.round(managerService.getStdevRequestsPerManager()*100.0d)/100.0d);
		
		res.addObject("avgRequestsPerManagerStatusPending",  Math.round(managerService.getAvgRequestsPerManagerStatusPending()*100.0d)/100.0d);
		res.addObject("avgRequestsPerManagerStatusApproved", Math.round(managerService.getAvgRequestsPerManagerStatusApproved()*100.0d)/100.0d);
		res.addObject("avgRequestsPerManagerStatusRejected", Math.round(managerService.getAvgRequestsPerManagerStatusRejected()*100.0d)/100.0d);
		
		res.addObject("minRequestsPerManagerStatusPending",  managerService.getMinRequestsPerManagerStatusPending());
//		res.addObject("minRequestsPerManagerStatusApproved", managerService.getMinRequestsPerManagerStatusApproved());
//		res.addObject("minRequestsPerManagerStatusRejected", managerService.getMinRequestsPerManagerStatusRejected());
		
		res.addObject("maxRequestsPerManagerStatusPending",  managerService.getMaxRequestsPerManagerStatusPending());
//		res.addObject("maxRequestsPerManagerStatusApproved", managerService.getMaxRequestsPerManagerStatusApproved());
//		res.addObject("maxRequestsPerManagerStatusRejected", managerService.getMaxRequestsPerManagerStatusRejected());
		
//		res.addObject("avgResultsPerFinder", Math.round(finderService.getAvgResultsPerFinder()*100.0d/100.0d));
//		res.addObject("minResultsPerFinder", finderService.getMinResultsPerFinder());
//		res.addObject("maxResultsPerFinder", finderService.getMaxResultsPerFinder());
//		res.addObject("stdevResultsPerFinder", Math.round(finderService.getStdevResultsPerFinder()*100.0d/100.0d));
		
		res.addObject("avgBillboardFilesPerContract",    Math.round(billboardFileService.getAvgBillboardFilesPerContract()*100.0d)/100.0d);
		res.addObject("minBillboardFilesPerContract",    billboardFileService.getMinBillboardFilesPerContract());
		res.addObject("maxBillboardFilesPerContract",    billboardFileService.getMaxBillboardFilesPerContract());
		res.addObject("stdevBillboardFilesPerContract",  Math.round(billboardFileService.getStdevBillboardFilesPerContract()*100.0d)/100.0d);
		
		res.addObject("avgInfoFilesPerContract",    Math.round(infoFileService.getAvgInfoFilesPerContract()*100.0d)/100.0d);
		res.addObject("minInfoFilesPerContract",    infoFileService.getMinInfoFilesPerContract());
		res.addObject("maxInfoFilesPerContract",    infoFileService.getMaxInfoFilesPerContract());
		res.addObject("stdevInfoFilesPerContract",  Math.round(infoFileService.getStdevInfoFilesPerContract()*100.0d)/100.0d);
		
		res.addObject("avgRadioFilesPerContract",   Math.round(radioFileService.getAvgRadioFilesPerContract()*100.0d)/100.0d);
		res.addObject("minRadioFilesPerContract",   radioFileService.getMinRadioFilesPerContract());
		res.addObject("maxRadioFilesPerContract",   radioFileService.getMaxRadioFilesPerContract());
		res.addObject("stdevRadioFilesPerContract", Math.round(radioFileService.getStdevRadioFilesPerContract()*100.0d)/100.0d);
		
		res.addObject("avgSocialNetworkFilesPerContract",   Math.round(socialNetworkFileService.getAvgSocialNetworkFilesPerContract()*100.0d)/100.0d);
		res.addObject("minSocialNetworkFilesPerContract",   socialNetworkFileService.getMinSocialNetworkFilesPerContract());
		res.addObject("maxSocialNetworkFilesPerContract",   socialNetworkFileService.getMaxSocialNetworkFilesPerContract());
		res.addObject("stdevSocialNetworkFilesPerContract", Math.round(socialNetworkFileService.getStdevSocialNetworkFilesPerContract()*100.0d)/100.0d);
		
		res.addObject("avgTVFilesPerContract",   Math.round(TVFileService.getAvgTVFilesPerContract()*100.0d)/100.0d);
		res.addObject("minTVFilesPerContract",   TVFileService.getMinTVFilesPerContract());
		res.addObject("maxTVFilesPerContract",   TVFileService.getMaxTVFilesPerContract());
		res.addObject("stdevTVFilesPerContract", Math.round(TVFileService.getStdevTVFilesPerContract()*100.0d)/100.0d);
		
		res.addObject("avgFilesPerContract",   Math.round(contractService.getAvgFilesPerContract()*100.0d)/100.0d);
		res.addObject("minFilesPerContract",   contractService.getMinFilesPerContract());
		res.addObject("maxFilesPerContract",   contractService.getMaxFilesPerContract());
		res.addObject("stdevFilesPerContract", Math.round(contractService.getStdevFilesPerContract()*100.0d)/100.0d);
		
		res.addObject("top10ManagersRequestsStatusPending",  managerService.top10ManagersRequestsStatusPending());
//		res.addObject("top10ManagersRequestsStatusApproved", managerService.top10ManagersRequestsStatusApproved());
//		res.addObject("top10ManagersRequestsStatusRejected", managerService.top10ManagersRequestsStatusRejected());
		
//		res.addObject("top10CustomersRequestsStatusPending",  requestService.top10CustomersRequestsStatusPending());
//		res.addObject("top10CustomersRequestsStatusApproved",  requestService.top10CustomersRequestsStatusApproved());
//		res.addObject("top10CustomersRequestsStatusRejected",  requestService.top10CustomersRequestsStatusRejected());

		return res;
	}
	
}