package common.base.screen;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import common.base.BaseScreen;
import common.helper.AllLocators;

public class HomeScreen extends BaseScreen {
	public HomeScreen(WebDriver driver) {
		super(driver);
	}

	private void addProductToCartByHighPrice(){
     fluentWaitAfterLoading(AllLocators.filterButton);
     selectValuesFromDropDown("Price (high to low)",AllLocators.filterButton);
     fluentWaitAfterLoading(AllLocators.addToCartButton);
     click(AllLocators.addToCartButton);
     fluentWaitTillElementClickable(AllLocators.removeButton);
     isDisplayedOnScreen(AllLocators.removeButton);
	}

	private void selectProduct(){
		fluentWaitAfterLoading(AllLocators.filterButton);
		selectValuesFromDropDown("Price (high to low)",AllLocators.filterButton);
		fluentWaitAfterLoading(AllLocators.addToCartButton);
		click(AllLocators.addToCartButton);
		isDisplayedOnScreen(AllLocators.removeButton);
	}

	public void addProductToCartHPriceOnly(){
		try{
			addProductToCartByHighPrice();
			Assert.assertTrue(true,"Add Product to Cart Success");
		}
		catch (Exception e){
			System.out.println(e.getLocalizedMessage());
			Assert.fail("Add to product cart Failed!! StackTrace : "+ e.getLocalizedMessage());
		}
	}


}
