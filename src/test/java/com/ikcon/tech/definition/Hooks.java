package com.ikcon.tech.definition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikcon.tech.util.TestContextInitilizer;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * The class methods executes before or after all scenarios defined in feature
 * file
 */
public class Hooks {

    private WebDriver driver;
    private TestContextInitilizer initializer;
    private static Logger logger = LoggerFactory.getLogger(Hooks.class);

    /*
     * getting initializer through dependency Injection to access driver object,
     * because we can get driver instance through TestContextInitializer class
     */
    public Hooks(TestContextInitilizer initializer) {
	this.initializer = initializer;
	this.driver = this.initializer.webDriverManager.InitializeSeliniumWebDriver();
    }

    // executes before each scenario
    @Before
    public void beforeScenario() {
	// before each scenario maximize the browser window
	driver.manage().window().maximize();
    }

    // executes after each scenario
    @After
    public void afterScenario() {
	// after each scenario close the browser
	driver.quit();
    }

    // executes after each scenario step
    // Takes screenshot of a failed step in the scenario
    @AfterStep
    public void generateFailedScenarioScreenshot(Scenario scenario) throws IOException {
	if (scenario.isFailed()) {
	    logger.info("Scenario Step failed");
	    logger.debug(scenario.getLine() + " is the failed step of the scenario");
	    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    byte[] fileContent = FileUtils.readFileToByteArray(screenshotFile);
	    scenario.attach(fileContent, "image/jpg", "failedStep");
	}
    }
}
