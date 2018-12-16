package com.flipkart.UI_Actions;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.flipkart.TestBase.TestBase;

public class HomePage extends TestBase{

	public static final Logger log = Logger.getLogger(HomePage.class);
	
	@FindBy(xpath="//*[@class='_2cyQi_']")
	WebElement userName ;
	
	@FindBy(xpath="//*[@class='LM6RPg']")
	WebElement search ;
	
	/*	@FindBy(xpath="//a[contains(text(),'Login & Signup')]")
	WebElement Login&SignUp ;*/
	
	@FindBy(xpath="//div/span[contains(text(),'More')]")
	WebElement moreOptions ;
	/*
	@FindBy(xpath="")
	WebElement abc ;
	
	@FindBy(xpath="")
	WebElement abc ;
	
	@FindBy(xpath="")
	WebElement abc ;
	
	@FindBy(xpath="")
	WebElement abc ;
	
	@FindBy(xpath="")
	WebElement abc ;
	
	@FindBy(xpath="")
	WebElement abc ;
	
	@FindBy(xpath="")
	WebElement abc ;*/
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnUserOptions(String userOptionName) throws InterruptedException {
		userName.click();
		log.info("clicked on User Menu");
		Thread.sleep(2000);
		userName.click();
		driver.findElement(By.xpath("//*[contains(text(),'"+userOptionName+"')]")).click();
	}
	
	public void clickOnMoreOptions(String moreOptions) throws InterruptedException {
		this.moreOptions.click();
		Thread.sleep(2000);
		this.moreOptions.click();
		driver.findElement(By.xpath("//*[contains(text(),'"+moreOptions+"')]")).click();
	}
	
	public void selectCategory(String categoryName) {
		driver.findElement(By.xpath("//li/span[text()='"+categoryName+"']")).click();
	}
	
	public void selectSubCategoryFromMainCategory(String subCategory) {
		driver.findElement(By.xpath("//li[@class='_1KCOnI _2BfSTw _1h5QLb _3ZgIXy']/a[text()='"+subCategory+"']")).click();
	}
	
	public void selectSuperSubCategoryFromSubCategory(String superSubCategory) {
		driver.findElement(By.xpath("//li[@class='_1KCOnI _3ZgIXy']/a[text()='"+superSubCategory+"']")).click();
	}
	
	
	public void getSubCategoryNamesForParticularCategory() {
		
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
