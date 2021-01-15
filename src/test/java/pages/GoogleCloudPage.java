package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPage extends AbstractPage{
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

    public GoogleCloudPage(WebDriver driver){
        super(driver);
    }

    public GoogleCloudPage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchButton));
        return this;
    }

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchButton;

    public GoogleCloudPage enterValueForSearching(String valueForSearching){
        new Actions(driver).click(searchButton).build().perform();

        searchButton.sendKeys(valueForSearching);
        return this;
    }

    public GoogleCloudPage pressEnter(){
        searchButton.sendKeys(Keys.ENTER);
        return this;
    }
}
