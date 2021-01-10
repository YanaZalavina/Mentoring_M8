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
import tests.GoogleCloudPlatformPricingCalculatorTest;
import utilsForDropdownList.utilForDropdownList;

import java.util.ArrayList;

import static waits.Waits.waitToBeClickableForElement;
import static waits.Waits.waitToBeVisibleForElement;

public class TempMailPage extends AbstractPage{
    private static final String TEMP_EMAIL_SERVICE_URL = "https://10minutemail.com/";
    private String startPartOfPath = "//*[contains(text(), '";
    private String endPartOfPath = "')]";

    public TempMailPage(WebDriver driver){
        super(driver);
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(iconForCopyTempEmail));
        //PageFactory.initElements(driver, this);
    }

    public TempMailPage loadPage(){
        PageFactory.initElements(driver, this);
        waitToBeVisibleForElement(driver, adsBlock);
        return this;
    }
    public TempMailPage openPage() {
        driver.get(TEMP_EMAIL_SERVICE_URL);
        System.out.println("openPage TempMailPage");
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(iconForCopyTempEmail));
        //PageFactory.initElements(driver, this);
        return this;
    }

    @FindBy(xpath = "//span[@class='copy_icon']")
    private WebElement iconForCopyTempEmail;
    @FindBy(xpath = "//span[@id='inbox_count_number']")
    private WebElement labelCountOfEmailInTempService;
    @FindBy(xpath = "//span[contains(text(), 'Google Cloud Platform Price Estimate')]")
    private WebElement headerEmailInTempService;
    @FindBy(xpath = "//h3[text()='Total Estimated Monthly Cost']/../following-sibling::td/h3")
    private WebElement labelEstimateCostInEmail;
    @FindBy(xpath = "//div[@class='adsbyvli']")
    private WebElement adsBlock;

    public TempMailPage clickOnIconForCopyTempEmail(){
        waitToBeVisibleForElement(driver, iconForCopyTempEmail);
        waitToBeVisibleForElement(driver, adsBlock);
        new utilForDropdownList().findAndClickOnElement(driver, iconForCopyTempEmail);
        iconForCopyTempEmail.click();
        System.out.println("clickOnIconForCopyTempEmail");
        return this;
    }

    public TempMailPage clickOnheaderEmailInTempService(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(labelCountOfEmailInTempService, "1"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(headerEmailInTempService));
        //headerEmailInTempService.click();
        new utilForDropdownList().findAndClickOnElement(driver, headerEmailInTempService);
        return this;
    }

    public String getTextAboutCostFromGoogleEmail(){
        waitToBeClickableForElement(driver, labelEstimateCostInEmail);
        return labelEstimateCostInEmail.getText().trim();
    }

    public TempMailPage openFirstTab(ArrayList<String> tabs){
        new utilForDropdownList().switchToPreviousTab(driver, tabs);
        return this;
    }

    public TempMailPage openSecondTab(ArrayList<String> tabs){
        new utilForDropdownList().switchToNextTab(driver, tabs);
        return this;
    }

}
