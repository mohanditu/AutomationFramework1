package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import UtilityFiles.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginpageObject;
import pageObjects.MyAccountsPage;
import testBase.BaseClass;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "sanity")
	void LoginDDTWay(String email, String pws, String exp) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();

		hp.clickLogin();
		LoginpageObject lop;
		lop = new LoginpageObject(driver);

		lop.setEmail(email);
		lop.setPassword(pws);
		lop.btnLogin();
		MyAccountsPage myacc = new MyAccountsPage(driver);

		boolean status = myacc.isMyAccountPageExists();

		//login was valid - valid - passed
		//login was valid - invalid - 
		
		
		if (exp.equalsIgnoreCase("Valid")) {
			if (status) {
				myacc.clickLogout();
				myacc.clickaContinue();
				System.out.println("Test passed");
				Assert.assertTrue(true,"passed");

			} else {
				System.out.println("test failed");
				Assert.assertTrue(false,"failed");
			}
		}
		else if (exp.equalsIgnoreCase("InValid")) {
			if (status) {
				myacc.clickLogout();
				System.out.println("test failed");
				Assert.assertTrue(false);
			}
			
		} else {
			System.out.println("Test passed");
			Assert.assertTrue(true);
		}

	}

}
