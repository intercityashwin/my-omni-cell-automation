package basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import gluecode.Register;

public class baseclass{

	public FileInputStream fis = null; 
	public Properties prop = null; 
	public WebDriver driver = null; 
	public WebElement elm = null;
	public Select sel = null;

	public void loadConfig(){
		try{
			//System.getProperty("user.dir")+"//src//test//resources//config.properties";
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
			prop = new Properties(); 
			prop.load(fis);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void navigateTo(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
	}
	
	public void click(String locatorKey){
		if(locatorKey.endsWith("_XPATH")){
			driver.findElement(By.xpath(prop.getProperty(locatorKey))).click();
		}else if(locatorKey.endsWith("_ID")){
			driver.findElement(By.id(prop.getProperty(locatorKey))).click();
		}else if (locatorKey.endsWith("_NAME")){
			driver.findElement(By.name(prop.getProperty(locatorKey))).click();
		}else {
			System.out.println("Invalid locator specified !");
		}
	}
	
	public void EndTest(){
		driver.quit();
	}
	public void setText(String locatorKey, String setValue){
			if(locatorKey.endsWith("_XPATH")){
				driver.findElement(By.xpath(prop.getProperty(locatorKey))).sendKeys(setValue);
			}else if(locatorKey.endsWith("_ID")){
				driver.findElement(By.id(prop.getProperty(locatorKey))).sendKeys(setValue);
			}else if (locatorKey.endsWith("_NAME")){
				driver.findElement(By.name(prop.getProperty(locatorKey))).sendKeys(setValue);
			}else {
				System.out.println("Invalid locator specified !");
			}
	}
	
	
	public void takeScreenShot(ExtentTest test){
		try {
			Date d = new Date(); 
			String timeStamp = d.toString().replace(" ", "_").replace(":","_");
			String screenShotPath = System.getProperty("user.dir")+"//src//test//resources//screenshots//s_"+timeStamp+".png";
			TakesScreenshot snapshot = (TakesScreenshot)driver; 
			File f = snapshot.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(f, new File(screenShotPath));
			test.log(LogStatus.INFO, test.addScreenCapture(screenShotPath));

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void selectDropDownValue(String locatorKey,String ddValue){
		if(locatorKey.endsWith("_XPATH"))	{
			elm = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			sel = new Select(elm);
			sel.selectByValue(ddValue);
		}else if(locatorKey.endsWith("_ID")){
			elm = driver.findElement(By.id(prop.getProperty(locatorKey)));
			sel = new Select(elm);
			sel.selectByValue(ddValue);
		}else if (locatorKey.endsWith("_NAME")){
			elm =driver.findElement(By.name(prop.getProperty(locatorKey)));
			sel = new Select(elm);
			sel.selectByValue(ddValue);
		}else {
			System.out.println("Invalid locator specified !");
		}
		
	}

}