package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Contract;
import domain.TVFile;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class TVFileServiceTest extends AbstractTest {

	@Autowired
	private TVFileService TVFileService;
	
	@Autowired
	private ContractService contractService;
	
	// ANALYSIS OF DATA COVERAGE:
	// Total coverage: 100.0%
	// Covered Instructions: 769
	//  Missed Instructions: 0
	//   Total Instructions: 769
	
	@Test
	public void driverCreateTVFile() {
		
		final Object testingData[][] = {{"manager1", null},
										{"manager2", null},
										{"manager3", null},
										{"customer1", IllegalArgumentException.class},
										{"customer2", IllegalArgumentException.class},
										{"customer3", IllegalArgumentException.class},
										{"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateCreateTVFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateCreateTVFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			this.TVFileService.create(contract);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverSaveTVFile() {
		
		Object testingData[][] = {{"manager1",  "http://www.netflix.com/asdllasd123.avi", "TV Name 1", "09:15 - 11:10", null},
								  {"manager2",  "http://www.netflix.com/asdllasd123.avi", "TV Name 2", "10:15 - 12:10", null},
								  {"manager3",  "http://www.netflix.com/asdllasd123.avi", "TV Name 3", "11:15 - 13:10", null},
								  {"admin",     "http://www.netflix.com/asdllasd123.avi", "TV Name 4", "12:15 - 14:10", IllegalArgumentException.class},
								  {"customer1", "http://www.netflix.com/asdllasd123.avi", "TV Name 5", "13:15 - 15:10", IllegalArgumentException.class},
								  {"customer2", "http://www.netflix.com/asdllasd123.avi", "TV Name 6", "14:15 - 16:10", IllegalArgumentException.class},
								  {"customer3", "http://www.netflix.com/asdllasd123.avi", "TV Name 7", "15:15 - 17:10", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateSaveTVFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>)testingData[i][4]);
		}
	}
	
	protected void templateSaveTVFile(String username, String video, String name, String schedule, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			TVFile TVFile = TVFileService.create(contract);
			TVFile.setVideo(video);
			TVFile.setName(name);
			TVFile.setSchedule(schedule);
			TVFile = TVFileService.save(TVFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverUpdateTVFile() {
		
		TVFile TVFile = (TVFile) TVFileService.findAll().toArray()[0];
		
		Object testingData[][] = {{"manager1",  "http://www.netflix.com/asdllasd123.avi", "TV Name 1", "09:15 - 11:10", null},
				  				  {"manager2",  TVFile.getVideo(), "TV Name 2", "10:15 - 12:10", null},
				  				  {"manager3",  "http://www.netflix.com/asdllasd123.avi", TVFile.getName(), "11:15 - 13:10", null},
				  				  {"manager2",  "http://www.netflix.com/asdllasd123.avi", "TV Name 3", TVFile.getSchedule(), null},
				  				  {"admin",     "http://www.netflix.com/asdllasd123.avi", "TV Name 4", "12:15 - 14:10", IllegalArgumentException.class},
				  				  {"customer1", "http://www.netflix.com/asdllasd123.avi", "TV Name 5", "13:15 - 15:10", IllegalArgumentException.class},
				  				  {"customer2", "http://www.netflix.com/asdllasd123.avi", "TV Name 6", "14:15 - 16:10", IllegalArgumentException.class},
				  				  {"customer3", "http://www.netflix.com/asdllasd123.avi", "TV Name 7", "15:15 - 17:10", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateUpdateTVFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>)testingData[i][4]);
		}
	}
	
	protected void templateUpdateTVFile(String username, String video, String name, String schedule, Class<?> expected) {
		Class<?> caught = null;
		
		TVFile TVFile = (TVFile) TVFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			TVFile.setVideo(video);
			TVFile.setName(name);
			TVFile.setSchedule(schedule);
			TVFileService.save(TVFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverDeleteTVFile() {
		
		Object testingData[][] = {{"manager1", null},
								  {"manager2", null},
								  {"manager3", null},
								  {"customer1", IllegalArgumentException.class},
								  {"customer2", IllegalArgumentException.class},
								  {"customer3", IllegalArgumentException.class},
								  {"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateDeleteTVFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateDeleteTVFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		TVFile TVFile = (TVFile) TVFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			TVFileService.delete(TVFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
}
