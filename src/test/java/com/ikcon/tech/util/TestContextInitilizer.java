package com.ikcon.tech.util;

import com.ikcon.tech.pageobjectlocator.PageObjectManager;

/**
 * The class manages the PageObjectManager(POM) class instance, so that other
 * classes will request the POM instance though this class to access the
 * POM-locator classes from POM. It also initializes the WebDriverManager class
 * which will return an instance of WebDriver that is binded to POM instance.POM
 * instance can delegate the driver to POM-locator classes.
 */
public class TestContextInitilizer {

    public PageObjectManager pageObjectManager;
    public WebDriverManager webDriverManager;

    public TestContextInitilizer() {
	webDriverManager = new WebDriverManager();
	this.pageObjectManager = new PageObjectManager(webDriverManager.InitializeSeliniumWebDriver());

    }

}
