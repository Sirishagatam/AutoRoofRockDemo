package com.RoofRocketai.Testcases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import config.Utility;

import org.testng.Assert;


public class UserCreateFormPageTest extends BaseClass {

	@Test(priority=1)
	public void verifyValidUser() {
		
		int val;
		System.out.println("*** Executing verification of valid user TC ***\n");
		test = report.createTest("Verify created user is available executing on "+browser);
		test.log(Status.INFO, "Login to UI");
		try {
			lp.doSignIn(prop.getProperty("email"),prop.getProperty("password"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.INFO, "Create user "+prop.getProperty("user1fname")+"  "+prop.getProperty("user1lname"));
		try {
			up.createUser(prop.getProperty("user1fname"),prop.getProperty("user1lname"),prop.getProperty("user1mail"),prop.getProperty("user1pass"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.INFO, "Verify user "+prop.getProperty("user1fname")+"  "+prop.getProperty("user1lname")+" is available");
		try {
			val=up.verifyUser(prop.getProperty("user1fname")+"  "+prop.getProperty("user1lname"));
			Thread.sleep(2000);
			Assert.assertTrue(val>0,"User: "+prop.getProperty("user1fname")+"  "+prop.getProperty("user1lname")+" is not Successfully added");
			System.out.println("*** User is successfully added and shows up !!! ***\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void verifyInvalidUser() {
		int val;
		System.out.println("*** Executing verification of non-existing user TC ***\n");
		test = report.createTest("Verify user not previously added is available executing on "+browser);
		test.log(Status.INFO, "Verify non-existing user "+prop.getProperty("user5fname")+"  "+prop.getProperty("user1lname")+" is available");
		try {
			
			val=up.verifyUser(prop.getProperty("user5fname")+"  "+prop.getProperty("user5lname"));
			Thread.sleep(2000);
			Assert.assertTrue(val>0,"User: "+prop.getProperty("user5fname")+"  "+prop.getProperty("user5lname")+" is not added previously. Create User !!!\n");
			System.out.println("*** User is not previously added but still shows up. Verify !!! ***\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test(priority=3)
	public void verifyUserFormMissingDetails() {
		String emsg,spath;
		System.out.println("*** Executing verification of submitting user with missing data TC ***\n");
		test=report.createTest("Verify Error displayed if missing data while creating user executing on "+browser);
		test.log(Status.INFO, "Verify with not entering data and submitting form");
		try {
			up.createUser(prop.getProperty("user1fname")," ","xyz@com",prop.getProperty("user1pass"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			emsg=up.createUserMissingField();
			Thread.sleep(2000);
			Assert.assertTrue(emsg.contains("wrong"),"Error message not shown. Verify !!! ***\n");
			System.out.println("*** Error message shown. Verify !!! ***\n"+emsg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

   
}
