package com.flipkart.HomePage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.TestBase.TestBase;
import com.flipkart.UI_Actions.HomePage;
import com.flipkart.UI_Actions.LoginPage;

public class TC003_VerifySubCategoryItems extends TestBase {

public static final Logger log = Logger.getLogger(TC002_VerifyMoreOptions.class);
	
	@BeforeClass
	public void setup() throws IOException {
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLoginWithMobileNumber(OR.getProperty("userMobile"), OR.getProperty("pwd"));
		waitForPageLoad();
	}
	
	@Test
	public void verifySubCategory() throws InterruptedException {
		HomePage hp = new HomePage();
		Thread.sleep(10000);
		hp.selectCategory("Electronics");
		Thread.sleep(2000);
		hp.selectSubCategoryFromMainCategory("Mobiles");
		Thread.sleep(10000);	
	}
}
