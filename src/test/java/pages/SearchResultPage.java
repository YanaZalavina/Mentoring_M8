package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends AbstractPage{

    public SearchResultPage(WebDriver driver){
        super(driver);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(areaWithListSearchResults));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a/b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement linkToPricingCalculator;
    @FindBy(xpath = "//div[@class='gsc-results gsc-webResult']")
    private WebElement areaWithListSearchResults;

    public SearchResultPage selectLinkToPricingCalculator(){
        linkToPricingCalculator.click();
        return this;
    }

}
