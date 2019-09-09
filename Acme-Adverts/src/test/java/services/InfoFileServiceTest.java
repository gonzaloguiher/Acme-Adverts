package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Contract;
import domain.InfoFile;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class InfoFileServiceTest extends AbstractTest {

	@Autowired
	private InfoFileService infoFileService;
	
	@Autowired
	private ContractService contractService;
	
	// ANALYSIS OF DATA COVERAGE:
	// Total coverage: 100.0%
	// Covered Instructions: 695
	//  Missed Instructions: 0
	//   Total Instructions: 695
	
	@Test
	public void driverCreateInfoFile() {
		
		final Object testingData[][] = {{"manager1", null},
										{"manager2", null},
										{"manager3", null},
										{"customer1", IllegalArgumentException.class},
										{"customer2", IllegalArgumentException.class},
										{"customer3", IllegalArgumentException.class},
										{"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateCreateInfoFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateCreateInfoFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			this.infoFileService.create(contract);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverSaveInfoFile() {
		
		Object testingData[][] = {{"manager1",  "Title 1", "Text 1", null},
				  				  {"manager2",  "Title 2", "Text 2", null},
				  				  {"manager3",  "Title 3", "Text 3", null},
				  				  {"admin",     "Title 4", "Text 4", IllegalArgumentException.class},
				  				  {"customer1", "Title 5", "Text 5", IllegalArgumentException.class},
				  				  {"customer2", "Title 6", "Text 6", IllegalArgumentException.class},
				  				  {"customer3", "Title 7", "Text 7", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateSaveInfoFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>)testingData[i][3]);
		}
	}
	
	protected void templateSaveInfoFile(String username, String title, String text, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			InfoFile infoFile = infoFileService.create(contract);
			infoFile.setTitle(title);
			infoFile.setText(text);
			infoFile = infoFileService.save(infoFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverUpdateInfoFile() {
		
		InfoFile infoFile = (InfoFile) infoFileService.findAll().toArray()[0];
		
		Object testingData[][] = {{"manager1",  "Title 1", "Text 1", null},
								  {"manager2",  infoFile.getTitle(), "Text 2", null},
								  {"manager3",  "Title 3", infoFile.getText(), null},
								  {"admin",     "Title 4", "Text 4", IllegalArgumentException.class},
								  {"customer1", "Title 5", "Text 5", IllegalArgumentException.class},
								  {"customer2", "Title 6", "Text 6", IllegalArgumentException.class},
								  {"customer3", "Title 7", "Text 7", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateUpdateInfoFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>)testingData[i][3]);
		}
	}
	
	protected void templateUpdateInfoFile(String username, String title, String text, Class<?> expected) {
		Class<?> caught = null;
		
		InfoFile infoFile = (InfoFile) infoFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			infoFile.setTitle(title);
			infoFile.setText(text);
			infoFileService.save(infoFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverDeleteInfoFile() {
		
		Object testingData[][] = {{"manager1", null},
								  {"manager2", null},
								  {"manager3", null},
								  {"customer1", IllegalArgumentException.class},
								  {"customer2", IllegalArgumentException.class},
								  {"customer3", IllegalArgumentException.class},
								  {"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateDeleteInfoFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateDeleteInfoFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		InfoFile infoFile = (InfoFile) infoFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			infoFileService.delete(infoFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
}