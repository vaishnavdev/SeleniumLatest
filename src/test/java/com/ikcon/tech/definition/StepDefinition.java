package com.ikcon.tech.definition;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.ikcon.tech.pageobjectlocator.HomePage;
import com.ikcon.tech.pageobjectlocator.LandingPage;
import com.ikcon.tech.util.TestContextInitilizer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

    private static Logger logger = LoggerFactory.getLogger(StepDefinition.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private TestContextInitilizer initializer;
    private LandingPage landingPage;
    private HomePage homePage;

    public StepDefinition(TestContextInitilizer initializer) {
	this.initializer = initializer;
	this.driver = this.initializer.webDriverManager.InitializeSeliniumWebDriver();
	this.landingPage = this.initializer.pageObjectManager.getLandingPageInstance();
	this.homePage = this.initializer.pageObjectManager.getHomePageInstance();
	this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	
    }

    @Given("^User is on IRCTC home page$")
    public void user_is_on_irctc_home_page()  {
	logger.info("User browsed to IRCTC website URL");
	// check weather we have reached to correct landing page
	String homePageTitle = driver.getTitle();
	Assert.assertTrue(homePageTitle.contains("IRCTC Next Generation eTicketing"));
	logger.info("User has reached to Home Page of IRCTC ");
    }

    @When("^User searches for a train with following details (.+),(.+),(.+),(.+),(.+),(.+),(.+)$")
    public void user_searches_for_a_train_with_following_details_(String from, String to, String ticketType,
	    String year, String month, String date, String ticketclass) {
	logger.info("User started searching for trains");
	// set the "from" location that you wish to enter in the search dropdown box
	// String fromLocation = commonLocators.setFromLocation(from);
	String selectedFromOption = null;
	// send the word "HYDERABAD" to be search in the input box
	// driver.findElement(this.from).sendKeys(fromLocation);
	homePage.fromLocation.sendKeys(from);
	// get all the items of the auto-suggestive word
	this.wait.until(ExpectedConditions.visibilityOfAllElements(homePage.actualFrom));
	List<WebElement> fromDropdownOptions = homePage.actualFrom;
	// based on the above word, the from location is retrieved from the search box
	for (WebElement option : fromDropdownOptions) {
	    if (option.getText().contains(from)) {
		// click on selected option
		option.click();
		// store the selected option
		selectedFromOption = option.getText();
	    }
	}
	// check weather the correct "from" option is selected from dropdown
	Assert.assertTrue(selectedFromOption.contains(from));
	// set the "to" location that you wish to enter in the search dropdown box
	String selectedDestOption = null;
	// send the word "SHORANUR" to be search in the input box
	homePage.toLocation.sendKeys(to);
	// get all the items of the auto-suggestive word
	this.wait.until(ExpectedConditions.visibilityOfAllElements(homePage.toLocation));
	List<WebElement> toDropdownOptions = homePage.actualTo;
	// based on the above word, the from location is retrieved from the search box
	for (WebElement option : toDropdownOptions) {
	    if (option.getText().contains(to)) {
		// click on selected option
		option.click();
		// store the selected option
		selectedDestOption = option.getText();
	    }
	}
	// check weather the correct "to" option is selected from dropdown
	Assert.assertTrue(selectedDestOption.contains(to));
	// select the journey quota type from dropdown
	String selectedTicketType = null;
	// open the dropdown
	homePage.journeyQuotaType.click();
	this.wait.until(ExpectedConditions.visibilityOfAllElements(homePage.journeyQuotaTypeOptions));
	List<WebElement> dropdownTypeOptions = homePage.journeyQuotaTypeOptions;
	// select the entered value from dropdown
	for (WebElement option : dropdownTypeOptions) {
	    if (option.getText().equalsIgnoreCase(ticketType)) {
		// click on selected option
		option.click();
		selectedTicketType = option.getText();
	    }
	}
	logger.debug("User selected Ticket type of " + selectedTicketType);
	Assert.assertEquals(selectedTicketType, ticketType);
	// select the boarding date from DateCalendar picker
	// click on calendar
	homePage.dateCalendar.click();
	// select the month
	String monthValueFromUI = homePage.monthSelector.getText();
	// String yearValueFromUI =
	// driver.findElement(By.className("yearSelector")).getText();

	/*
	 * check weather the month value from UI is equal to the month value that we
	 * entered. If not equal then go on to next months until match is found. If
	 * match is found exit the while loop.
	 */
	while (!(monthValueFromUI).contains(month)) {
	    // go to next months until entered month match is found in UI ,
	    // if match is found select the date in next step
	    this.wait.until(ExpectedConditions.visibilityOf(homePage.nextMonthSelector));
	    homePage.nextMonthSelector.click();
	    // getting next month value after clicking on next button
	    monthValueFromUI = homePage.monthSelector.getText();
	}

	// grab the common CSS attribute and grab all dates related to that attribute
	this.wait.until(ExpectedConditions.visibilityOfAllElements(homePage.dateSelector));
	List<WebElement> dates = homePage.dateSelector;
	// get the size of all the dates in a month
	int size = dates.size();
	// iterate until entered date and the date from calendar matches
	String dateValueFromUI = null;
	for (int i = 0; i < size; i++) {
	    // grab the date values from UI
	    dateValueFromUI = homePage.dateSelector.get(i).getText();
	    // check for entered date value and date value from UI matches
	    if (dateValueFromUI.equalsIgnoreCase(date)) {
		// if match is found select and click on the date
		homePage.dateSelector.get(i).click();
		break;
	    } // if
	} // for
	Assert.assertEquals(dateValueFromUI, date);
	Assert.assertEquals(monthValueFromUI, month);
	// select the ticket class from dropdown
	String selectedTicketClass = null;
	// open the dropdown
	this.wait.until(ExpectedConditions.visibilityOf(homePage.journeyClass));
	homePage.journeyClass.click();
	// select the entered value from dropdown
	this.wait.until(ExpectedConditions.visibilityOfAllElements(homePage.journeyClassOptions));
	List<WebElement> dropdownClassOptions = homePage.journeyClassOptions;
	for (WebElement option : dropdownClassOptions) {
	    if (option.getText().contains(ticketclass)) {
		// click on selected option
		option.click();
		selectedTicketClass = option.getText();
	    }
	}
	// search trains
	homePage.searchButton.click();
	System.out.println(
		"Hi you chose to board from " + from + ", on " + month + " " + date + " " + year + " and depart at "
			+ to + ". The ticket type is " + selectedTicketType + " and class is " + selectedTicketClass);
    }

    @Then("^Train results should be loaded and displayed$")
    public void train_results_should_be_loaded_and_displayed() {
	List<String> trainDetailsList = new ArrayList<String>();
	// int size = driver.findElements(trainHeading).size();
	wait.until(ExpectedConditions.visibilityOfAllElements(homePage.trainHeading));
	// get all the train details
	List<WebElement> elements = homePage.trainHeading;
	for (int i = 0; i < elements.size(); i++) {
	    trainDetailsList.add(homePage.trainHeading.get(i).getText());
	}
	System.out.println("Trains Found: " + trainDetailsList.toString());
    }

    // Login Scenario Code
    @Given("^User is at IRCTC Landing Page$")
    public void user_is_at_irctc_landing_page() {
	logger.info("User browsed to IRCTC website URL");
	// check weather we have reached to correct landing page
	String landingPageTitle = driver.getTitle();
	Assert.assertTrue(landingPageTitle.contains("IRCTC Next Generation eTicketing"));
	logger.info("User has reached to Landing Page of IRCTC ");
	// close the alert box of landing page
	landingPage.alertBox.click();
	logger.info("Alert box closed");
	// open dropdown menu to find the Login button
	landingPage.dropdownMenu.click();
	// go to login Page to login
	WebElement loginBtn = wait.until(ExpectedConditions.visibilityOf(landingPage.loginBtn));
	loginBtn.click();
    }

    @When("^User enters the Username \"([^\"]*)\",Password \"([^\"]*)\" and SignIn$")
    public void user_enters_the_username_something_and_password_something(String username, String password)
	    throws InterruptedException {
	// enter credentials
	landingPage.userNameSelector.sendKeys(username);
	landingPage.passwordSelector.sendKeys(password);
	// enter captcha
	// time to enter captcha
	Thread.sleep(8000);
	// landingPage.captchaInput.sendKeys("499");
	landingPage.SignInBtn.click();
	// time to load home page after sign In
	Thread.sleep(4000);
    }

    @Then("^User should be loggedin with Home page displayed$")
    public void user_should_be_loggedin_with_home_page_displayed() throws Exception {
	// validate the Home Page URL
	boolean flag = false;
	InputStream fileInputStream = new FileInputStream(
		"C:\\ECLIPSE_WORKSPACE\\selenium_Testing\\SeleniumTesting-IRCTC\\src\\test\\resources\\globalconfiguration.properties");
	Properties props = new Properties();
	props.load(fileInputStream);
	String url = driver.getCurrentUrl();
	if (url.equals(props.getProperty("HomePageUrl"))) {
	    flag = true;
	}
	// assert actual value with expected value
	Assert.assertEquals(flag, Boolean.TRUE);
    }

}
