package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common_Pack.CommonFunctions;
import pageObjects.UserRole_Page_Objects;

public class Test_UserRole extends CommonFunctions{
	Logger logger=Logger.getLogger(Test_UserRole.class);
	@Test
	public void checkUserRole()
	{
		PageFactory.initElements(driver,UserRole_Page_Objects.class);
		logger.info("Navigating to the user page");
		UserRole_Page_Objects.adminLink.click();
		
		//UserRole_Page_Objects.userManagementLink.click(); not needed 123
		logger.info("selecting the userRole");
		UserRole_Page_Objects.userRole.click();
		UserRole_Page_Objects.userRole.sendKeys("ESS");
		
		
		
		/*
		 * Select selectUserRole=new Select(UserRole_Page_Objects.userRole);
		 * selectUserRole.selectByVisibleText("Admin");
		 */
		 		
	}
	
	

}
