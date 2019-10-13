package utils;

import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class reports {
	private static ExtentReports report = null; 
	public ExtentTest test = null; 
	
	public static ExtentReports getInstance(){
		
		if (report==null){
			Date d = new Date(); 
			String timeStamp = d.toString().replace(" ", "_").replace(":","_");
			String reportPath = System.getProperty("user.dir")+"//src//test//resources//reports//r_"+timeStamp+".html";
			report = new ExtentReports(reportPath);
		 	}	
			return report; 
		}

}
