package com.RoofRocketai.Testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.RoofRocketai.Pages.LoginPage;
import com.RoofRocketai.Pages.NavigatePage;
import com.RoofRocketai.Pages.UserCreateFormPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.Utility;

public class BaseClass {

	public static ExtentSparkReporter htmlReporter;
    public static ExtentReports report;
    public static ExtentTest test;
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected Properties prop;
	
	FileInputStream fis;
 	LoginPage lp;
	NavigatePage np;
	UserCreateFormPage up;
	public static String browser;
	
	public BaseClass() {
		prop=new Properties();
		try {
			prop.load(new FileInputStream("C:\\Users\\sirim\\eclipse-workspace\\DemoRoodRocketai\\src\\MyData.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	@BeforeSuite
	public void reportSetup() {
		htmlReporter = new ExtentSparkReporter("RoofRocketaiTestResult.html");
		report = new ExtentReports();	
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Machine Name", "Lenovo");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("User", "Lekha");
		report.setSystemInfo("Browser", "Google Chrome");
		  	  
		// Configuration for look of report
		htmlReporter.config().setDocumentTitle("Extent Report for RoofRocketai Verification");
		htmlReporter.config().setReportName("RoofRocketai Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE MMMM dd yyyy, hh:mm a '('zzz')'");

	}
	
	@BeforeMethod
	public void getParameterName(ITestContext context) {
		browser=context.getCurrentXmlTest().getParameter("bwname");
	}
	
	@AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
        	String spath;
            
        	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            
			test.fail(result.getThrowable());
			try {
					spath = Utility.captureScreenshot(driver);
					test.addScreenCaptureFromPath(spath);
			}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
					         		 
        } else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
     
    

	@Parameters({"bwname"})
	@BeforeClass
	public void driverInitialize(String bwname) throws IOException {
		
			

		if (bwname.equalsIgnoreCase("chrome")) {
			
			driver=new ChromeDriver();
		} else if (bwname.equalsIgnoreCase("edge")) {
	
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		lp=new LoginPage(driver);
		np=new NavigatePage(driver);
		up=new UserCreateFormPage(driver);

		driver.get(prop.getProperty("url"));
			
	}
	
	@AfterClass
	public void driverClose() {
		driver.quit();
	}
	
	@AfterSuite
	public void reportFlush() {
		report.flush();
	}

}
