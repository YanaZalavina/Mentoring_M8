package waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    public static WebElement waitToBeClickableForElement(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitToBeVisibleForElement(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }
}
