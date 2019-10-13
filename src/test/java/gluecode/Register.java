package gluecode;


import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import basetest.baseclass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.DataReader;
import utils.reports;


public class Register extends baseclass{
	public static ExtentReports report = null; 
	public ExtentTest test = null; 
	HashMap<String,String> TCData= DataReader.getData("New User Register Test Case");
	
	@Given("^MyOmni website is up and running$")
	public void myomni_website_is_up_and_running() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		report = reports.getInstance();
		test = report.startTest("Register User"); 
		test.log(LogStatus.INFO, "Initialize Config File");
		loadConfig();
		test.log(LogStatus.INFO, "Navigate to the URL");
		navigateTo();	
		test.log(LogStatus.PASS, "Login Successful");
		takeScreenShot(test);
	}

	@When("^I fill up registration form$")
	public void i_fill_up_registtion_form() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		test.log(LogStatus.INFO, "Click the Register Button");
		click("RegisterBtn_XPATH");
		test.log(LogStatus.INFO, "Fill the Form with all Data");
		setText("LoginName_XPATH",TCData.get("LoginName"));
		setText("Email_XPATH",TCData.get("Email"));
		setText("Pass_XPATH",TCData.get("Password"));
		setText("CndPass_XPATH",TCData.get("Confirm Password"));
		setText("FirstName_XPATH",TCData.get("First Name"));
		setText("LastName_XPATH",TCData.get("Last Name"));
		setText("OffcPhNo_XPATH",TCData.get("PhoneNo"));
		selectDropDownValue("DepSel_XPATH",TCData.get("DepSel"));
		setText("CSN_ID","121");
		click("Radisbtn_XPATH");
		test.log(LogStatus.PASS, "Form is filled with the Data");
		takeScreenShot(test);
		click("nextBtn_XPATH");
		test.log(LogStatus.INFO, "Click on Login Button");
		takeScreenShot(test);
	}

	@Then("^New user should be registered$")
	public void new_user_should_be_registered() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   //System.out.println("New User Should be Registerted");
		EndTest(); 
	   report.endTest(test);
	   report.flush(); 
	}

}
