package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class ConfigTests {
    protected WebDriver driver;
    String FIRST_NODE = "http://192.168.100.7:5557/wd/hub";
    String SECOND_NODE = "http://192.168.100.7:5558/wd/hub";

    @BeforeClass(alwaysRun = true)
    @Parameters("myBrowser")
    public void browserSetUp(String myBrowser) throws MalformedURLException {
        if(myBrowser.equals("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.setCapability("platform", "WIN10");
            options.setCapability("platformName", "windows");
            driver = new RemoteWebDriver(new URL(FIRST_NODE), options);
        }
        else if(myBrowser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("platform", "WIN10");
            options.setCapability("platformName", "windows");
            driver = new RemoteWebDriver(new URL(SECOND_NODE), options);
        }

       /* ChromeOptions options = new ChromeOptions();
        options.setCapability("platform", "WIN10");
        options.setCapability("platformName", "windows");

        try {
            driver = new RemoteWebDriver(new URL(FIRST_NODE), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */

        driver.manage().window().maximize();
    }
    @AfterClass(alwaysRun = true)
    public void browserQuit(){
       // driver.quit();
       // driver = null;
    }
}
