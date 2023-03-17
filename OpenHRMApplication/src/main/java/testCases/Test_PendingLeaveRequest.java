package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import common_Pack.CommonFunctions;
import pageObjects.Dashboard_Page_Objects;
import pageObjects.Login_Page_Objects;

public class Test_PendingLeaveRequest extends CommonFunctions{
	String actualMessage;
	Logger logger=Logger.getLogger(Test_PendingLeaveRequest.class);
	
	public void login()
	{	
		logger.info("Loging in to the application");
		PageFactory.initElements(driver,Login_Page_Objects.class );
		Login_Page_Objects.userName.sendKeys(properties.getProperty("username"));	
		Login_Page_Objects.password.sendKeys(properties.getProperty("password"));
		Login_Page_Objects.submitButton.click();
	}
	public void getPendingLeaveRequest() {
		PageFactory.initElements(driver,Dashboard_Page_Objects.class );
		actualMessage=Dashboard_Page_Objects.employeesLeaveStatus.getText();	
	}
	@Test
	public void verifyPendingLeaveRequest()
	{	
		login();
		logger.info("Getting the leave reuest details");
		getPendingLeaveRequest();
		System.out.println(actualMessage);
		Assert.assertEquals(actualMessage, "No Employees are on Leave Today");
			
	}

}
