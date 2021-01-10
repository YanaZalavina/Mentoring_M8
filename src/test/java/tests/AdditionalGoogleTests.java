package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleCloudPage;
import pages.GoogleCloudPricingCalculator;
import pages.SearchResultPage;
import utilsForDropdownList.utilForDropdownList;

public class AdditionalGoogleTests extends ConfigTests {

    @Test
    public void checkFormComputerEngine() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();

        Assert.assertTrue(
                new GoogleCloudPricingCalculator(driver)
                .loadPage()
                .elementLocatedOnPage("Machine Class"));
    }

    @Test
    public void checkDeleteEstimate() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();
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
                .clickOnDeleteEstimateButton();


        Assert.assertEquals(new GoogleCloudPricingCalculator(driver).countNumberOfElementsEstimateResults(), 0);
    }

    @Test
    public void checkURLOfPageGoogleCloudPricingCalculator() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();
        new GoogleCloudPricingCalculator(driver)
                .loadPage();

        Assert.assertEquals(new GoogleCloudPricingCalculator(driver).getURL(), "https://cloudpricingcalculator.appspot.com/");
    }

    @Test
    public void checkActualData() {
        new GoogleCloudPage(driver)
                .openPage()
                .enterValueForSearching("Google Cloud Platform Pricing Calculator")
                .pressEnter();

        new SearchResultPage(driver).selectLinkToPricingCalculator();

        String dateOnGooglePage = new GoogleCloudPricingCalculator(driver)
                        .loadPage()
                        .getWebElementText("Last update");
        System.out.println("date from field: "+dateOnGooglePage+" current date - "+new utilForDropdownList().getCurrentDateMonthYear());



        Assert.assertTrue(dateOnGooglePage.contains(new utilForDropdownList().getCurrentDateMonthYear()));
    }
}
