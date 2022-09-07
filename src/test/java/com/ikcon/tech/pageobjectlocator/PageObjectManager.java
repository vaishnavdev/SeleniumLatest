package com.ikcon.tech.pageobjectlocator;

import org.openqa.selenium.WebDriver;

/**
 * This class receives the Driver instance from TestContextInitializer class and
 * is responsible to provide Driver instance to all the PageObjectLocator
 * classes. (HomePage, LandingPage)
 *
 */
public class PageObjectManager {

    private WebDriver driver;

    public PageObjectManager(WebDriver driver) {
	this.driver = driver;
    }

    /**
     * @return LandingPage instance with driver instance, to perform activities on
     *         actual html elements of LandingPage using this instance,this instance
     *         will be used by StepDefinition class to use the operations performed
     *         by the class.
     */
    public LandingPage getLandingPageInstance() {
	LandingPage landingPage = new LandingPage(driver);
	return landingPage;
    }

    /**
     * @return HomePage instance with driver instance, to perform activities on
     *         actual html elements of HomePage using this instance, this instance
     *         will be used by StepDefinition class to use the operations performed
     *         by the class.
     */
    public HomePage getHomePageInstance() {
	HomePage homePage = new HomePage(driver);
	return homePage;
    }
}
