package com.RoofRocketai.Testcases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import org.testng.Assert;


public class NavigatePageTest extends BaseClass {

	@Test(priority=1)
	public void getAllMenu() throws InterruptedException {
		test = report.createTest("Verify Navigation of links in side-menu executing on "+browser);
		test.log(Status.INFO, "Login to UI");
		lp.doSignIn(prop.getProperty("email"),prop.getProperty("password"));
		test.log(Status.INFO, "Verify if all links is working fine");
		int val=np.getAllMenus();
		//	Assert.assertTrue(lp.getUrl().contains(menu.getText()),menu.getText()+" Page is not navigated successfully");
		Assert.assertTrue(val==0, "*** Page is not navigated successfully !!! ***\n");	
		System.out.println("*** Page is navigated successfully !!! ***\n");
			
	}
	
}
