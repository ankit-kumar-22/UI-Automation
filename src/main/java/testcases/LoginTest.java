package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.base.BaseTest;
import common.helper.LoginHelper;

public class LoginTest extends BaseTest {
	LoginHelper loginHelper;

	@BeforeClass(alwaysRun = true)
	public void setUpDriver(){
		setUp();
		loginHelper=new LoginHelper(driver);
	}

	@Test(description = "LoginTest FLow",groups = "Login")
	public void doLogin(){
		loginHelper.doLoginOnly();
	}
}
