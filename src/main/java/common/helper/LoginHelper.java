package common.helper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import common.base.BaseScreen;
import common.base.screen.HomeScreen;

public class LoginHelper extends BaseScreen {

	public LoginHelper(WebDriver driver) {
		super(driver);
	}

	private void loginCheck(){
		String loginId = "standard_user";
		String password = "secret_sauce";
		fluentWaitAfterLoading(AllLocators.username);
		clearAndEnterDetails(AllLocators.username, loginId);
		fluentWaitAfterLoading(AllLocators.password);
		clearAndEnterDetails(AllLocators.password, password);
		fluentWait(AllLocators.loginButton);
		click(AllLocators.loginButton);
		isDisplayedOnScreen(AllLocators.productTitleText);
	}

	public void doLoginOnly(){
		 try{
		 	loginCheck();
		 	Assert.assertTrue(true,"Login Flow Success");
		 }
		 catch (Exception e){
		 	Assert.fail("Login Failed!! StackTrace : "+ e.getLocalizedMessage());
		 }
	}

	public HomeScreen doLoginFlow(){
		doLoginOnly();
		return new HomeScreen(driver);

	}
}
