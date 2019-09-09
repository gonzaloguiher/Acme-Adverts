package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Contract;
import domain.BillboardFile;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class BillboardFileServiceTest extends AbstractTest {

	@Autowired
	private BillboardFileService billboardFileService;
	
	@Autowired
	private ContractService contractService;
	
	// ANALYSIS OF DATA COVERAGE:
	// Total coverage: 100.0%
	// Covered Instructions: 695
	//  Missed Instructions: 0
	//   Total Instructions: 695
	
	@Test
	public void driverCreateBillboardFile() {
		
		final Object testingData[][] = {{"manager1", null},
										{"manager2", null},
										{"manager3", null},
										{"customer1", IllegalArgumentException.class},
										{"customer2", IllegalArgumentException.class},
										{"customer3", IllegalArgumentException.class},
										{"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateCreateBillboardFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateCreateBillboardFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			this.billboardFileService.create(contract);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverSaveBillboardFile() {
		
		Object testingData[][] = {{"manager1",  "location1", "http:/www.link1.com", null},
								  {"manager2",  "location2", "http:/www.link2.com", null},
								  {"manager3",  "location3", "http:/www.link3.com", null},
								  {"admin",     "location4", "http:/www.link4.com", IllegalArgumentException.class},
								  {"customer1", "location5", "http:/www.link5.com", IllegalArgumentException.class},
								  {"customer2", "location6", "http:/www.link6.com", IllegalArgumentException.class},
								  {"customer3", "location7", "http:/www.link7.com", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateSaveBillboardFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>)testingData[i][3]);
		}
	}
	
	protected void templateSaveBillboardFile(String username, String location, String image, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			BillboardFile billboardFile = billboardFileService.create(contract);
			billboardFile.setLocation(location);
			billboardFile.setImage(image);
			billboardFile = billboardFileService.save(billboardFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverUpdateBillboardFile() {
		
		BillboardFile billboardFile = (BillboardFile) billboardFileService.findAll().toArray()[0];
		
		Object testingData[][] = {{"manager1",  "location1", "http:/www.link1.com", null},
								  {"manager2",  billboardFile.getLocation(), "http:/www.link2.com", null},
								  {"manager3",  "location2", billboardFile.getImage(), null},
								  {"admin",     "location3", "http:/www.link3.com", IllegalArgumentException.class},
								  {"customer1", "location4", "http:/www.link4.com", IllegalArgumentException.class},
								  {"customer2", "location5", "http:/www.link5.com", IllegalArgumentException.class},
								  {"customer3", "location6", "http:/www.link6.com", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateUpdateBillboardFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>)testingData[i][3]);
		}
	}
	
	protected void templateUpdateBillboardFile(String username, String location, String image, Class<?> expected) {
		Class<?> caught = null;
		
		BillboardFile billboardFile = (BillboardFile) billboardFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			billboardFile.setLocation(location);
			billboardFile.setImage(image);
			billboardFileService.save(billboardFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverDeleteBillboardFile() {
		
		Object testingData[][] = {{"manager1", null},
								  {"manager2", null},
								  {"manager3", null},
								  {"customer1", IllegalArgumentException.class},
								  {"customer2", IllegalArgumentException.class},
								  {"customer3", IllegalArgumentException.class},
								  {"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateDeleteBillboardFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateDeleteBillboardFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		BillboardFile billboardFile = (BillboardFile) billboardFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			billboardFileService.delete(billboardFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
}