package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.base.BaseTest;
import common.base.screen.HomeScreen;
import common.helper.LoginHelper;

public class HomeTest extends BaseTest {
	LoginHelper loginHelper;
	HomeScreen homeScreen;

	@BeforeClass(alwaysRun = true)
	public void setUpDriver(){
		setUp();
		loginHelper=new LoginHelper(driver);
	}

	@Test(description = "Add Product to Cart By High Price",groups = "Home")
	public void addProductToCartByHighPrice(){
     homeScreen=loginHelper.doLoginFlow();
     homeScreen.addProductToCartHPriceOnly();
	}
}
