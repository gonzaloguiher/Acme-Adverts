package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Contract;
import domain.SocialNetworkFile;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class SocialNetworkFileServiceTest extends AbstractTest {

	@Autowired
	private SocialNetworkFileService socialNetworkFileService;
	
	@Autowired
	private ContractService contractService;
	
	// ANALYSIS OF DATA COVERAGE:
	// Total coverage: 100.0%
	// Covered Instructions: 695
	//  Missed Instructions: 0
	//   Total Instructions: 695
	
	@Test
	public void driverCreateSocialNetworkFile() {
		
		final Object testingData[][] = {{"manager1", null},
										{"manager2", null},
										{"manager3", null},
										{"customer1", IllegalArgumentException.class},
										{"customer2", IllegalArgumentException.class},
										{"customer3", IllegalArgumentException.class},
										{"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateCreateSocialNetworkFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateCreateSocialNetworkFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			this.socialNetworkFileService.create(contract);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverSaveSocialNetworkFile() {
		
		Object testingData[][] = {{"manager1",  "https://www.banner1.com", "https://www.target1.com", null},
				  				  {"manager2",  "https://www.banner2.com", "https://www.target2.com", null},
				  				  {"manager3",  "https://www.banner3.com", "https://www.target3.com", null},
				  				  {"admin",     "https://www.banner4.com", "https://www.target4.com", IllegalArgumentException.class},
				  				  {"customer1", "https://www.banner5.com", "https://www.target5.com", IllegalArgumentException.class},
				  				  {"customer2", "https://www.banner6.com", "https://www.target6.com", IllegalArgumentException.class},
				  				  {"customer3", "https://www.banner7.com", "https://www.target7.com", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateSaveSocialNetworkFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>)testingData[i][3]);
		}
	}
	
	protected void templateSaveSocialNetworkFile(String username, String banner, String target, Class<?> expected) {
		Class<?> caught = null;
		
		Contract contract = (Contract) contractService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			SocialNetworkFile socialNetworkFile = socialNetworkFileService.create(contract);
			socialNetworkFile.setBanner(banner);
			socialNetworkFile.setTarget(target);
			socialNetworkFile = socialNetworkFileService.save(socialNetworkFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverUpdateSocialNetworkFile() {
		
		SocialNetworkFile socialNetworkFile = (SocialNetworkFile) socialNetworkFileService.findAll().toArray()[0];
		
		Object testingData[][] = {{"manager1",  "https://www.banner1.com", "https://www.target1.com", null},
								  {"manager2",  socialNetworkFile.getBanner(), "https://www.target2.com", null},
								  {"manager3",  "https://www.banner3.com", socialNetworkFile.getTarget(), null},
								  {"admin",     "https://www.banner4.com", "https://www.target4.com", IllegalArgumentException.class},
								  {"customer1", "https://www.banner5.com", "https://www.target5.com", IllegalArgumentException.class},
								  {"customer2", "https://www.banner6.com", "https://www.target6.com", IllegalArgumentException.class},
								  {"customer3", "https://www.banner7.com", "https://www.target7.com", IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateUpdateSocialNetworkFile((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>)testingData[i][3]);
		}
	}
	
	protected void templateUpdateSocialNetworkFile(String username, String banner, String target, Class<?> expected) {
		Class<?> caught = null;
		
		SocialNetworkFile socialNetworkFile = (SocialNetworkFile) socialNetworkFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			socialNetworkFile.setBanner(banner);
			socialNetworkFile.setTarget(target);
			socialNetworkFileService.save(socialNetworkFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
	
	@Test
	public void driverDeleteSocialNetworkFile() {
		
		Object testingData[][] = {{"manager1", null},
								  {"manager2", null},
								  {"manager3", null},
								  {"customer1", IllegalArgumentException.class},
								  {"customer2", IllegalArgumentException.class},
								  {"customer3", IllegalArgumentException.class},
								  {"admin",     IllegalArgumentException.class}};
		
		for(int i = 0; i < testingData.length; i++) {
			templateDeleteSocialNetworkFile((String) testingData[i][0], (Class<?>)testingData[i][1]);
		}
	}
	
	protected void templateDeleteSocialNetworkFile(String username, Class<?> expected) {
		Class<?> caught = null;
		
		SocialNetworkFile socialNetworkFile = (SocialNetworkFile) socialNetworkFileService.findAll().toArray()[0];
		
		try {
			super.authenticate(username);
			socialNetworkFileService.delete(socialNetworkFile);
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}
}