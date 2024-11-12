package com.RoofRocketai.Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class LoginPageTest extends BaseClass {
	
	@Parameters({"bwname"})
	@Test(priority=1)
	public void verifySignIn() throws InterruptedException {
		test = report.createTest("Verify Login with valid Credentials executing on "+browser);
		test.log(Status.INFO, "Login to UI with valid credentials");
		lp.doSignIn(prop.getProperty("email"),prop.getProperty("password"));
		String smsg=lp.checkSignInmsg();
		Assert.assertTrue(smsg.contains("Hi, Tester"), smsg);
		System.out.println("*** SignIn is successful for valid credentials !!! ***\n");
		
	}

	@Test(priority=2)
	public void verifyInvalidSignIn() throws InterruptedException {
		test = report.createTest("Verify Login with invalid Credentials executing on "+browser);
		test.log(Status.INFO, "Login to UI with invalid credentials");
		driver.get(prop.getProperty("url"));
		lp.doSignIn(prop.getProperty("email"),prop.getProperty("ipassword"));
		String sign=lp.getUrl();
		Assert.assertTrue(sign.contains("research"), "*** Signin is not successful for invalid credentials. Verify !!! ***\n");
		System.out.println("*** Signin is successful for invalid credentials !!! ***\n");
		
	}
	
}
