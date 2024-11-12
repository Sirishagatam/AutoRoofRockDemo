package com.RoofRocketai.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;



public class LoginPage {
	
	private WebDriver driver;
		
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	//locators
	@FindBy(id="username")
	WebElement email;
	
	@FindBy(id="userpassword")
	WebElement passw;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	WebElement signin;

	@FindBy(xpath="//div[text()=\"Hi, Tester\"]")
	WebElement successmsg;

	//methods
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void doSignIn(String mail,String pass) throws InterruptedException {
		
		email.sendKeys(mail);
		passw.sendKeys(pass);
		signin.click();
	
	}
	
	public String checkSignInmsg() {
		
		String msg;
		if(getUrl().contains("research")) {
	    	msg=successmsg.getText();
		} else {
			msg="Login is not successful !! Verify Credentials !!!";
		}
		
	    return msg;
	    
	}
}
