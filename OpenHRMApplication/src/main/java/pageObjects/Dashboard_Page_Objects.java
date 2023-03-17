package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_Page_Objects {
	
	@FindBy(xpath="//*[text()='No Employees are on Leave Today']")
	public static WebElement employeesLeaveStatus;

}
