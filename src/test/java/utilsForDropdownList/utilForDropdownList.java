package utilsForDropdownList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class utilForDropdownList {

    private static String commonXPathForElementsFromDropdownList = "//*[@class='md-select-menu-container md-active md-clickable']//md-option/div";
    //private ArrayList<String> tabs;

    public utilForDropdownList findAndClickOnElementFromDropDownList(WebDriver driver, String visibleNameOfWebElement){
        System.out.println("Go into findAndClickOnElementFromDropDownList");

        List<WebElement> options = driver.findElements(By.xpath(commonXPathForElementsFromDropdownList));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        for (int i=0; i<options.size(); i++) {
            if(options.get(i).getText().contains(visibleNameOfWebElement)) {
                js.executeScript("arguments[0].click();", options.get(i));
                break;
            }
        }
        return this;
    }

    public utilForDropdownList findAndClickOnElement(WebDriver driver, WebElement element){
        System.out.println("Go into findAndClickOnElement");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        return this;
    }

    public String getCurrentDateMonthYear(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        return now.format(formatter);
    }

    public utilForDropdownList switchingBetweenTwoTabsAndOpenNewTab(WebDriver driver, ArrayList<String> tabs){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        for (String str:
             tabs) {
            System.out.println("switchingBetweenTwoTabsAndOpenNewTab "+str);
        }
        driver.switchTo().window(tabs.get(1));
        return this;
    }
    public utilForDropdownList switchToPreviousTab(WebDriver driver, ArrayList<String> tabs){
        for (String str:
                tabs) {
            System.out.println(str);
        }
        driver.switchTo().window(tabs.get(0));
        return this;
    }
    public utilForDropdownList switchToNextTab(WebDriver driver, ArrayList<String> tabs){
        for (String str:
                tabs) {
            System.out.println(str);
        }
        driver.switchTo().window(tabs.get(1));
        return this;
    }
}
