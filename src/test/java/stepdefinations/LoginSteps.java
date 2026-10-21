package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import basePage.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.LoginPage;

public class LoginSteps extends BaseClass {

  LoginPage login = new LoginPage();

	@Given("user is on login page")
	public void user_is_on_login_page () {
	}
	
	@When("user enter correct username")
	public void user_enter_correct_username() {
		login.enterusername(prop.getProperty("username"));
	}
	
	@And("user enter correct password")
	public void user_enter_correct_password() {
		login.enterpassword(prop.getProperty("password"));
	}
	
	@And("user click on login button")
	public void user_click_on_login_button() {
		login.clickloginbutton();
	}
	
	@Then("user should redirect to dashbaord")
	public void user_should_redirect_to_dashbaord() {
		
		String pagetext = driver.findElement(By.xpath("//*[@class=\"app_logo\"]")).getText();
		System.out.println("Page title : " +pagetext);
		
		boolean  islogovisible =driver.findElement(By.xpath("//*[@class=\"app_logo\"]")).isDisplayed();
		
		//Assertion for validation
		
		Assert.assertTrue("Logo not visible", islogovisible);
         System.out.print("Login Successfully");
	}
	
         //Invalid case
         
         @When("user enter invalid username")
         public void user_enter_invalid_username() {
        	 login.enterusername("Wrong username");
         }
         
         @And("user enter invalid password")
         public void user_enter_invalid_password() {
        	 login.enterpassword("Wrong password");
         }
         
         @And("user click on login2 button")
         public void user_click_on_login2_button() {
        	 login.clickloginbutton();
         }
         
         @Then("error message should be displayed")
         public void error_message_should_be_displayed() {
        	 
        	 boolean isErrorDisplayed = login.isErrorMessageDisplayed();
        	 
        	 Assert.assertTrue("Error message is not displayed", isErrorDisplayed);
        	 
        	 System.out.println("Error Message displyed");
         }
	
	}

