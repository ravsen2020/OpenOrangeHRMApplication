package common_Pack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions {
	public static WebDriver driver=null;
	public static Properties properties=null;
	Logger logger=Logger.getLogger(CommonFunctions.class);  //factory design pattern info,debug,warn,fatel, error
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
		PropertyConfigurator.configure("log4j.properties");
		logger.info("loding the property file");
		loadPropertiesFile();
		
		String url=properties.getProperty("url");
		String browser=properties.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			logger.info("launching chrome browse");
			driver=new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("fireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			logger.info("launching firefox browse");
			driver=new FirefoxDriver();
			
		}
		driver.manage().window().maximize();
		logger.info("navigating to the application");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		
	} 
	
	
	@AfterSuite
	public void tearDown()
	{
		logger.info("After the execution, closing the browse");
	//driver.quit();	
	}

}
