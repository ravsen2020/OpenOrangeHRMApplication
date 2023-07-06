package common_Pack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions {
	public static WebDriver driver=null;
	public static Properties properties=null;
	
	public static ExtentReports extent=null;
	public static ExtentSparkReporter spark=null;
	public static ExtentTest testCase=null;
	
	//Logger logger=Logger.getLogger(CommonFunctions.class);  //...factory design pattern info,debug,warn,fatel, error
	
	public Properties loadPropertiesFile() throws IOException
	{	
		
		FileInputStream fileInputStream=new FileInputStream("config.properties");
		
		properties=new Properties();
		properties.load(fileInputStream);
		return properties;
	}
	@BeforeSuite
	public void launchBrowser() throws IOException
	{	
		//PropertyConfigurator.configure("log4j.properties");
		//logger.info("loding the property file");
		loadPropertiesFile();
		
		extent=new ExtentReports();
		spark=new ExtentSparkReporter("D:\\OrageHRMReportFile.html");
		extent.attachReporter(spark);
		//*************************
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("User Name", "Senthil");

		spark.config().setDocumentTitle("Orange HRM Automation");
		spark.config().setReportName("Ornage HRM Test Report");
		spark.config().setTheme(Theme.STANDARD);
		//**************************
		String url=properties.getProperty("url");
		String browser=properties.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("fireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}
		driver.manage().window().maximize();
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);		
		
	} 
	
//	@Test
//	public void tc_01testMethod()
//	{
//		System.out.println("This is for test purpose");
//	}
	
	@AfterSuite
	public void tearDown()
	{
		
		//driver.quit();
		extent.flush();
	}

}
