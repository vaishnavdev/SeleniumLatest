package com.ikcon.tech.pageobjectlocator;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * This class initializes IRCTC HomePage WebElements that are used to perform
 * activities on actual HTML elements of HomePage on IRCTC webapp using driver
 * instance. PageFactory is used to initialize the WebElements actually in this
 * class. This class is managed By PageObjectManager and also receives the
 * driver instance through it.
 */
public class HomePage {

    WebDriver driver;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'train-heading')]")
    public List<WebElement> trainHeading;

    @FindBy(xpath = "//p-autocomplete[@id='origin']/span/input")
    public WebElement fromLocation;

    @FindBy(how = How.XPATH, using = "//ul[@id='pr_id_1_list']")
    public List<WebElement> actualFrom;

    @FindBy(how = How.XPATH, using = "//p-autocomplete[@id='destination']/span/input")
    public WebElement toLocation;

    @FindBy(how = How.XPATH, using = "//ul[@id='pr_id_2_list']")
    public List<WebElement> actualTo;

    @FindBy(how = How.XPATH, using = "//p-dropdown[@formcontrolname='journeyQuota']")
    public WebElement journeyQuotaType;

    @FindBy(how = How.CSS, using = "div ul[role='listbox'] p-dropdownitem li span")
    public List<WebElement> journeyQuotaTypeOptions;

    @FindBy(how = How.XPATH, using = "//p-calendar")
    public WebElement dateCalendar;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-year")
    public WebElement yearSelector;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-month")
    public WebElement monthSelector;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'ui-state-default')]")
    public List<WebElement> dateSelector;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-next-icon")
    public WebElement nextMonthSelector;

    @FindBy(how = How.XPATH, using = "//p-dropdown[@formcontrolname='journeyClass']")
    public WebElement journeyClass;

    @FindBy(how = How.CSS, using = "div ul[role='listbox'] p-dropdownitem li span")
    public List<WebElement> journeyClassOptions;

    @FindBy(how = How.XPATH, using = "//button[ @label='Find Trains']")
    public WebElement searchButton;

    public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

}
