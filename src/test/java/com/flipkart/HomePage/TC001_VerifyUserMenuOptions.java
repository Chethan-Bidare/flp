package com.flipkart.HomePage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.TestBase.TestBase;
import com.flipkart.UI_Actions.HomePage;
import com.flipkart.UI_Actions.LoginPage;

public class TC001_VerifyUserMenuOptions extends TestBase {

	
	@BeforeClass
	public void setup() throws IOException {
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLoginWithMobileNumber(OR.getProperty("userMobile"), OR.getProperty("pwd"));
		waitForPageLoad();
	}
	
	@Test
	public void verifyUserMenuOptions() throws InterruptedException {
		HomePage hp = new HomePage();
		LoginPage lp = new LoginPage();
		hp.clickOnUserOptions("My Profile");
		waitUntilTitleContains(APP.getProperty("MyProfilePageTitle"));
		Assert.assertEquals(getPageTitle(), APP.getProperty("MyProfilePageTitle"));
		
		hp.clickOnUserOptions("Flipkart Plus Zone");
		waitUntilURLcontains(APP.getProperty("flipkartPlusZoneURL"));
		Assert.assertTrue(getURL().contains(APP.getProperty("flipkartPlusZoneURL")));
		
		hp.clickOnUserOptions("Game Zone");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("flipkartGameZoneURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("flipkartGameZoneURL")));
		
		hp.clickOnUserOptions("Orders");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("OrdersURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("OrdersURL")));
		
		hp.clickOnUserOptions("Wishlist");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("WishlistURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("WishlistURL")));
		
		hp.clickOnUserOptions("Rewards");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("RewardsURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("RewardsURL")));
		
		hp.clickOnUserOptions("Gift Cards");
		wait.until(ExpectedConditions.urlContains(APP.getProperty("GiftCardURL")));
		Assert.assertTrue(getURL().contains(APP.getProperty("GiftCardURL")));
		
		hp.clickOnUserOptions("Notifications");
		wait.until(ExpectedConditions.titleContains(APP.getProperty("NotificationsPageTitle")));
		Assert.assertEquals(getPageTitle(), APP.getProperty("NotificationsPageTitle"));
		
		hp.clickOnUserOptions("Logout");
		lp.clickOnCloseButtonInLoginPopUp();
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//*[contains(text(),'Login & Signup')]")), APP.getProperty("userNameBeforeLogin")));
		Assert.assertTrue(isExpectedTextPresentInPage(APP.getProperty("userNameBeforeLogin")));
	}
}
