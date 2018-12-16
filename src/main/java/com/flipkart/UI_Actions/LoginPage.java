package com.flipkart.UI_Actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.TestBase.TestBase;

public class LoginPage extends TestBase{

	public static final Logger log = Logger.getLogger(LoginPage.class);
	
	@FindBy(xpath="//input[@class='_2zrpKA']")
	WebElement userID ;
	
	@FindBy(xpath="//input[@class='_2zrpKA _3v41xv']")
	WebElement pwd ;
	
	@FindBy(xpath="//button/span[contains(text(),'Login')]")
	WebElement loginButton ;
	
	@FindBy(xpath="//span[contains(text(),'New to Flipkart? Sign up')]")
	WebElement signUpButton ;
	
	@FindBy(xpath="//button[@class='_2AkmmA _29YdH8']")
	WebElement closeButton ;
	
	@FindBy(xpath="//*[@class='_2cyQi_']")
	WebElement userName ;
	
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterEmailID(String emailID) {
		userID.sendKeys(emailID);
		log.info("User email ID entered:"+emailID);
	}
	
	public void enterMobileNum(String mobNum) {
		userID.sendKeys(mobNum);
	}
	
	public void enterPassword(String pwd) {
		this.pwd.sendKeys(pwd);
		//log.info(" Password entered :"+pwd);
	}
	
	public void doLoginWithMobileNumber(String mobNum,String pwd) {
		enterMobileNum(mobNum);
		log.info(" user Mobile Number entered:"+mobNum);
		enterPassword(pwd);
		log.info("Password entered :"+pwd);
		loginButton.click();
		log.info("Clicked on Login Button");
	}
	
	public void clickOnCloseButtonInLoginPopUp() {
		closeButton.click();
	}
	
	public String getUserNameInHomePage() {
		return userName.getText(); 
	}
}


























