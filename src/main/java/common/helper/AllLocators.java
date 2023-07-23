package common.helper;

import org.openqa.selenium.By;

public class AllLocators {
    public static final By username= By.cssSelector("input[id='user-name']");
    public static final By password= By.cssSelector("input[id='password']");
    public static final By loginButton= By.cssSelector("input[id='login-button']");
    public static final By productTitleText = By.cssSelector("//span[contains(text(),'Products')]");
    public static final By filterButton= By.cssSelector("select[class='product_sort_container']");
    public static final By addToCartButton=By.xpath("(//button[contains(text(),'Add to cart')])[1]");
    public static final By removeButton=By.xpath("(//button[contains(text(),'Remove')])");
}

