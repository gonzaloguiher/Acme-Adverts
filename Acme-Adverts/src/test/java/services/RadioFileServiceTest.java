package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Contract;
import domain.RadioFile;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class RadioFileServiceTest extends AbstractTest {

	@Autowired
	private RadioFileService radioFileService;
	
	@Autowired
	private ContractService contractService;
	
	// ANALYSIS OF DATA COVERAGE:
	// Total coverage: 100.0%
	// Covered Instructions: 769
	//  Missed Instructions: 0
	//   Total Instructions: 769
	
	@Test
	public void driverCreateRadioFile() {
		
		final Object testingData[][] = {{"manager1", null},
										{"manager2", null},
										{"manager3", null},
										{"customer1", IllegalArgumentException.class},
										{"customer2", IllegalArgumentException.class},
										{"customer3", IllegalArgumentException.class},
										{"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateCreateRadioFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateCreateRadioFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			this.radioFileService.create(contract);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverSaveRadioFile() {
		
		Object testingData[][] = {{"manager1",  "http://www.spotify.com/asdllasd123.avi", "Radio Name 1", "09:15 - 11:10", null},
								  {"manager2",  "http://www.spotify.com/asdllasd123.avi", "Radio Name 2", "10:15 - 12:10", null},
								  {"manager3",  "http://www.spotify.com/asdllasd123.avi", "Radio Name 3", "11:15 - 13:10", null},
								  {"admin",     "http://www.spotify.com/asdllasd123.avi", "Radio Name 4", "12:15 - 14:10", IllegalArgumentException.class},
								  {"customer1", "http://www.spotify.com/asdllasd123.avi", "Radio Name 5", "13:15 - 15:10", IllegalArgumentException.class},
								  {"customer2", "http://www.spotify.com/asdllasd123.avi", "Radio Name 6", "14:15 - 16:10", IllegalArgumentException.class},
								  {"customer3", "http://www.spotify.com/asdllasd123.avi", "Radio Name 7", "15:15 - 17:10", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateSaveRadioFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>)testingData[i][4]);
		}
	}
	
	protected void templateSaveRadioFile(String username, String sound, String name, String schedule, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			RadioFile radioFile = radioFileService.create(contract);
			radioFile.setSound(sound);
			radioFile.setName(name);
			radioFile.setSchedule(schedule);
			radioFile = radioFileService.save(radioFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverUpdateRadioFile() {
		
		RadioFile radioFile = (RadioFile) radioFileService.findAll().toArray()[0];
		
		Object testingData[][] = {{"manager1",  "http://www.spotify.com/asdllasd123.avi", "Radio Name 1", "09:15 - 11:10", null},
				  				  {"manager2",  radioFile.getSound(), "Radio Name 2", "10:15 - 12:10", null},
				  				  {"manager3",  "http://www.spotify.com/asdllasd123.avi", radioFile.getName(), "11:15 - 13:10", null},
				  				  {"manager2",  "http://www.spotify.com/asdllasd123.avi", "Radio Name 3", radioFile.getSchedule(), null},
				  				  {"admin",     "http://www.spotify.com/asdllasd123.avi", "Radio Name 4", "12:15 - 14:10", IllegalArgumentException.class},
				  				  {"customer1", "http://www.spotify.com/asdllasd123.avi", "Radio Name 5", "13:15 - 15:10", IllegalArgumentException.class},
				  				  {"customer2", "http://www.spotify.com/asdllasd123.avi", "Radio Name 6", "14:15 - 16:10", IllegalArgumentException.class},
				  				  {"customer3", "http://www.spotify.com/asdllasd123.avi", "Radio Name 7", "15:15 - 17:10", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateUpdateRadioFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>)testingData[i][4]);
		}
	}
	
	protected void templateUpdateRadioFile(String username, String sound, String name, String schedule, Class<?> expected) {
		Class<?> caught = null;
		
		RadioFile radioFile = (RadioFile) radioFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			radioFile.setSound(sound);
			radioFile.setName(name);
			radioFile.setSchedule(schedule);
			radioFileService.save(radioFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverDeleteRadioFile() {
		
		Object testingData[][] = {{"manager1", null},
								  {"manager2", null},
								  {"manager3", null},
								  {"customer1", IllegalArgumentException.class},
								  {"customer2", IllegalArgumentException.class},
								  {"customer3", IllegalArgumentException.class},
								  {"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateDeleteRadioFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateDeleteRadioFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		RadioFile radioFile = (RadioFile) radioFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			radioFileService.delete(radioFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
}
