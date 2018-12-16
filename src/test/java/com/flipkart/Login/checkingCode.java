package com.flipkart.Login;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.TestBase.TestBase;
import com.flipkart.UI_Actions.HomePage;
import com.flipkart.UI_Actions.LoginPage;

public class checkingCode extends TestBase{


	@BeforeClass
	public void setup() throws IOException {
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLoginWithMobileNumber(OR.getProperty("userMobile"), OR.getProperty("pwd"));
		waitForPageLoad();
	}
	
	@Test
	public void checkCode() throws InterruptedException {
		HomePage hp = new HomePage();
		Thread.sleep(10000);
		hp.selectCategory("Electronics");
		Thread.sleep(5000);
		hp.selectSubCategoryFromMainCategory("Mobiles");
		Thread.sleep(10000);
	}
}
