package common.utils;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateChromeDriver {
    public String currentDirectory = System.getProperty("user.dir");
    public WebDriver driver;

    public WebDriver setDriver() {
        createChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void createChromeDriver() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("ignore-certificate-errors");
            options.addArguments("--ignore-ssl-errors");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--incognito");
//        options.addArguments("--headless");
            options.addArguments("start-maximized");
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//        chromePrefs.put("download.default_directory", currentDirectory + "\\Downloads");
            options.setExperimentalOption("prefs", chromePrefs);
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            //Using Selenium Grid
//            driver= new RemoteWebDriver(new URL("http://localhost:4444"), options);
            driver=new ChromeDriver(options);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

