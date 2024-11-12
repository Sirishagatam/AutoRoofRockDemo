package com.RoofRocketai.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

public class UserCreateFormPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	@Parameters({"bwname"})
	
	public UserCreateFormPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
//this.js=(JavascriptExecutor) driver;

	}
	
	//locators
	@FindBy(xpath="//span[contains(text(),'Users')]")
	WebElement clickuser;
	
	@FindBy(xpath="//a[text()=\" Create New User\"]")
	WebElement createuser;
	
	@FindBy(id="firstname")
	WebElement firstname;
	
	@FindBy(id="lastname")
	WebElement lastname;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="(//button[text()=\"Submit\"])[1]")
	WebElement submit;
	
	@FindBy(xpath="//table[@id=\"tech-companies-1\"]//tbody//tr//td[2]")
	List<WebElement> userlist;
	
	@FindBy(xpath="//div[@class=\"alert alert-danger\"]")
	WebElement missingfield;
	


    //methods
	 public void createUser(String fname,String lname,String mail,String pass) throws InterruptedException {
		 
		//s.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		 //click on users
		 wait.until(ExpectedConditions.elementToBeClickable(clickuser)).click();
		 
		 		 
		 //click on create user 
		 wait.until(ExpectedConditions.elementToBeClickable(createuser)).click();
		 

		 //enter user details
		 firstname.sendKeys(fname);
		 lastname.sendKeys(lname);
		 email.sendKeys(mail);
		 password.sendKeys(pass);
		 submit.click();
		 
	}
	 
	public String createUserMissingField() throws InterruptedException {
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(missingfield));
		String errmsg=missingfield.getText();
		return errmsg;
		
	}
	
	public int verifyUser(String name) throws Exception {
	
		wait.until(ExpectedConditions.elementToBeClickable(clickuser)).click();
	
		System.out.println("name:"+name);
		int rflag=0;
		
			
		wait.until(ExpectedConditions.visibilityOfAllElements(userlist));
		
		 for(WebElement m:userlist)
		 {
			 System.out.println("User list: "+ m.getText());
			 if(m.getText().contains(name)) {
				 System.out.println("User: "+name+" is successfully added\n");
				 rflag++;
				 break;
				 
			 }
		 }
		 if(rflag!=0) {
			 System.out.println("User: "+name+" is not successfully added\n");
		 }
		 return rflag;
	}
	
}
