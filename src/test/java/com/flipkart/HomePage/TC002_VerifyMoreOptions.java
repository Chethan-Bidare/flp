package com.flipkart.HomePage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.TestBase.TestBase;
import com.flipkart.UI_Actions.HomePage;
import com.flipkart.UI_Actions.LoginPage;


public class TC002_VerifyMoreOptions extends TestBase {

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
	public void verifyMore() throws InterruptedException {
		HomePage hp = new HomePage();
		
		Thread.sleep(5000);
		hp.clickOnMoreOptions("Sell on Flipkart");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("SellOnFlipkartURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("SellOnFlipkartURL")));
		previousPage();
		
		hp.clickOnMoreOptions("24x7 Customer Care");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("CustomerCareURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("CustomerCareURL")));
		
		hp.clickOnMoreOptions("Advertise");
		wait.until(ExpectedConditions.titleContains(APP.getProperty("AdvertisePageTitle")));
		Assert.assertTrue(getPageTitle().contains(APP.getProperty("AdvertisePageTitle")));
		previousPage();
		
		hp.clickOnMoreOptions("Download App");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("downloadAppURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("downloadAppURL")));
	}
}
