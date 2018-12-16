package com.flipkart.Login;



import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.TestBase.TestBase;
import com.flipkart.UI_Actions.LoginPage;

public class TC001_VerifyLoginFunctionality extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginFunctionality.class);
	
	@BeforeClass
	public void setup() throws IOException {
		init();
		log.info("Initializing Setup");
	}
	
	@Test(priority=0)
	public void verifyLoginWithValidCredentials() {
		LoginPage loginPage = new LoginPage();
		loginPage.doLoginWithMobileNumber(OR.getProperty("userMobile"), OR.getProperty("pwd"));
		waitForPageLoad();
		Assert.assertEquals(loginPage.getUserNameInHomePage(), APP.getProperty("userName"));
	}

	@Test(priority=1)
	public  void verifyLoginWithInvalidCredentials() {
		
	}
}
