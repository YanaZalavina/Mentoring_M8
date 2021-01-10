package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleCloudPage;
import pages.GoogleCloudPricingCalculator;
import pages.SearchResultPage;
import pages.TempMailPage;

import java.util.ArrayList;

import static waits.Waits.waitToBeClickableForElement;

public class GoogleCloudPlatformPricingCalculatorTest extends ConfigTests {

    public static ArrayList<String> tabs;

   /* @Test(dataProvider = "valuesCheckEnteredValues")
    public void checkEnteredDataForPricingCalculator(String visibleNameOfWebElement, String expectedValue) {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();

        String visibleName =
                new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .enterNumberOfInstances("4")
                .selectSeries("N1")
                .selectMachineType("n1-standard-8")
                .activateCheckBoxAddGPUs()
                .enterNumberOfGPUs("1")
                .selectGPUType("NVIDIA Tesla V100")
                .selectLocalSSD("2x375 GB")
                .selectDatacenterLocation("Frankfurt")
                .selectCommittedUsage("1 Year")
                .clickOnAddToEstimate()
                .getWebElementText(visibleNameOfWebElement);
        Assert.assertEquals(visibleName, expectedValue);
    }

    @DataProvider(name = "valuesCheckEnteredValues")
    public Object[][] valuesForCosOperations(){
        return new Object[][]{
                {"VM class", "VM class: regular"},
                {"Instance type", "Instance type: n1-standard-8"},
                {"Region", "Region: Frankfurt"},
                {"Total available local SSD space", "Total available local SSD space 2x375 GiB"},
                {"Commitment term", "Commitment term: 1 Year"}
        };
    }

    @Test
    public void checkCostForPricingCalculatorPerOneMonth() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();

        String visibleName = new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .enterNumberOfInstances("4")
                .selectSeries("N1")
                .selectMachineType("n1-standard-8")
                .activateCheckBoxAddGPUs()
                .enterNumberOfGPUs("1")
                .selectGPUType("NVIDIA Tesla V100")
                .selectLocalSSD("2x375 GB")
                .selectDatacenterLocation("Frankfurt")
                .selectCommittedUsage("1 Year")
                .clickOnAddToEstimate()
                .getWebElementText("Estimated Component Cost");
        Assert.assertEquals(visibleName, "Estimated Component Cost: USD 1,082.77 per 1 month");

    }

    @Test
    public void checkCostForPricingCalculatorPerOneMonthInEmail() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();

        String estimateCost = new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .enterNumberOfInstances("4")
                .selectSeries("N1")
                .selectMachineType("n1-standard-8")
                .activateCheckBoxAddGPUs()
                .enterNumberOfGPUs("1")
                .selectGPUType("NVIDIA Tesla V100")
                .selectLocalSSD("2x375 GB")
                .selectDatacenterLocation("Frankfurt")
                .selectCommittedUsage("1 Year")
                .clickOnAddToEstimate()
                .getWebElementText("Estimated Component Cost");


        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TEMP_EMAIL_SERVICE_URL);

        WebElement iconForCopyTempEmail = driver.findElement(By.xpath(xpathIconForCopyTempEmail));
        waitToBeClickableForElement(driver, iconForCopyTempEmail);
        iconForCopyTempEmail.click();

        driver.switchTo().window(tabs.get(0));
        new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .clickOnEmailEstimateButton()
                .enterCopiedEmail()
                .clickOnSendEmailButton();

        driver.switchTo().window(tabs.get(1));

        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath(labelCountOfEmailInTempService)), "1"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoogleCloudPlatformPricingCalculatorTest.headerEmailInTempService)));
        WebElement headerEmailInTempService = driver.findElement(By.xpath(GoogleCloudPlatformPricingCalculatorTest.headerEmailInTempService));
        headerEmailInTempService.click();
        waitToBeClickableForElement(driver, driver.findElement(By.xpath(GoogleCloudPlatformPricingCalculatorTest.labelEstimateCostInEmail)));
        WebElement labelEstimateCostInEmail = driver.findElement(By.xpath(GoogleCloudPlatformPricingCalculatorTest.labelEstimateCostInEmail));

        Assert.assertTrue(estimateCost.contains(labelEstimateCostInEmail.getText()));

    }*/
    @Test
    public void checkCostForPricingCalculatorPerOneMonthInEmail() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();

        String estimateCost = new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .enterNumberOfInstances("4")
                .selectSeries("N1")
                .selectMachineType("n1-standard-8")
                .activateCheckBoxAddGPUs()
                .enterNumberOfGPUs("1")
                .selectGPUType("NVIDIA Tesla V100")
                .selectLocalSSD("2x375 GB")
                .selectDatacenterLocation("Frankfurt")
                .selectCommittedUsage("1 Year")
                .clickOnAddToEstimate()
                .getWebElementText("Estimated Component Cost");


        ((JavascriptExecutor)driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        //new GoogleCloudPricingCalculator(driver).openNewTab(tabs);

        new TempMailPage(driver)
                .openPage()
                .clickOnIconForCopyTempEmail();
                //.openFirstTab(tabs);
        driver.switchTo().window(tabs.get(0));

        new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .clickOnEmailEstimateButton()
                .enterCopiedEmail()
                .clickOnSendEmailButton();
                //.openSecondTab(tabs);

        driver.switchTo().window(tabs.get(1));

        String estimateCostFromMail = new TempMailPage(driver)
                .loadPage()
                .clickOnheaderEmailInTempService()
                .getTextAboutCostFromGoogleEmail();

        Assert.assertTrue(estimateCost.contains(estimateCostFromMail));
    }
}
