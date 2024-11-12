package com.RoofRocketai.Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

public class NavigatePage {

	private WebDriver driver;

	private WebDriverWait wait;
	
	public static List<WebElement> mn;
	@Parameters({"bwname"})
	
//ublic WebDriverWait wait;
	
	public NavigatePage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	public int getAllMenus() throws InterruptedException {
		
		
		By menufind=By.xpath("//div[@id=\"navbarsleft\"]//ul//li");
		List<WebElement> mn=driver.findElements(menufind);
		
		int z=0;
		String previous=driver.getCurrentUrl();
		String current=driver.getCurrentUrl();
		for(int i=0;i<mn.size();i++) {
			previous=current;
			mn=driver.findElements(menufind);
			WebElement newm=mn.get(i);
		    Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(newm));
			String mtext=newm.getText();

			System.out.println("Navigating to Menu: "+ mtext);
			if(!mtext.contains("DFY")) {
				newm.click();
			}
			
			current=driver.getCurrentUrl();
			System.out.println("Current url is: "+current);
			
			if(previous!=current) {
				System.out.println("previous: "+previous+"\n"+"current: "+current);
			} else {
				System.out.println("previous: "+previous+"\n"+"current: "+current);
				z++;
			}
			
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menufind));
			
		}
	
		return z;
		
	}
	
}
	
	   
    
	

