package testCases;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginpageObject;
import testBase.BaseClass;

@Listeners(UtilityFiles.ExtentReportManager.class)
public class TC002_Login extends BaseClass {
	

	@Test(groups="sanity")
	
	void testLogin()
	{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		hp.clickLogin();
		LoginpageObject lop;
		lop=new LoginpageObject(driver);
		
		lop.setEmail(p.getProperty("username"));
		lop.setPassword(p.getProperty("password"));
		lop.btnLogin();		
	}

}
