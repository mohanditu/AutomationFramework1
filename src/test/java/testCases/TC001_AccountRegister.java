package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegister extends BaseClass {
	
	
	Faker fake = new Faker();
	@Test(groups="sanity")
	public void verify_account_registration()
	{
		
		logger.info("************Test case started****************");
		logger.debug("this is a debug log mesage");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName(fake.name().firstName());
		regpage.setLastName(fake.name().lastName());
		
		String email=fake.name().username()+"@gmail.com";
		regpage.setEmail(email);// randomly generated the email
		regpage.setTelephone(fake.phoneNumber().phoneNumber());
		
		String password=randomAlphaNumeric();
		
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		logger.info("Test passed");
		
		System.out.println("email ===>"+email);
		System.out.println("password ==>"+password);
		
	}

}
