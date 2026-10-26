 package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BaseClass;

public class LoginPage extends BaseClass {
	
	
	public LoginPage() {
		initpagefactory(this);
	}
	
	@FindBy(id="user-name")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(name="login-button")
	WebElement loginbutton;
	
	@FindBy(xpath="//h3[contains(text(),'Epic sadface: Username and password do not match a')]")
	WebElement errormessage;
	
	//Action
	
	public void enterusername(String user) {
		
		username.sendKeys(user);	
	}
	
	public void enterpassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickloginbutton() {
		loginbutton.click();
	}
	
	public boolean isErrorMessageDisplayed() {
		
		
		return errormessage.isDisplayed();
		
		
		
	}
	
	}
	
	
	


