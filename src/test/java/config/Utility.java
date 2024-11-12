
package config;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import com.RoofRocketai.Testcases.BaseClass;

public class Utility extends BaseClass {

	
	public static String captureScreenshot(WebDriver driver) throws IOException {
		
		
		String spath=System.getProperty("user.dir")+"//Screenshot//RoofRocketai"+System.currentTimeMillis()+".png";
       

		TakesScreenshot ts=(TakesScreenshot) driver;
		
		File temp=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(spath);
			
		FileHandler.copy(temp, dest);
		
		System.out.println("Screenshot is Taken\n");
        
        	
        return spath;
	}

}


