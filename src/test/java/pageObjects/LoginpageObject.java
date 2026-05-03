package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginpageObject extends BasePage {

	public LoginpageObject(WebDriver driver) {
		super(driver);		
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPwd;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//div[@class=\"alert alert-danger alert-dismissible\"]")
	WebElement errMsg;
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPassword(String pass) {
		txtPwd.sendKeys(pass);
	}

	public void btnLogin() {
		btnLogin.click();

	}
	
	public Boolean errDis()
	{
		return errMsg.isDisplayed();
	}

}
