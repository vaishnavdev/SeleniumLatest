package com.ikcon.tech.pageobjectlocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * This class initializes IRCTC LandingPage WebElements that are used to perform
 * activities on actual HTML elements of LandingPage on IRCTC webapp using
 * driver instance. PageFactory is used to initialize the WebElements actually
 * in this class. This class is managed By PageObjectManager and also receives
 * the driver instance through it.
 */
public class LandingPage {

    public WebDriver driver;

    @FindBy(how = How.XPATH, using = "//div/button[contains(text(),'OK')]")
    public WebElement alertBox;

    @FindBy(how = How.XPATH, using = "//div[@class='h_menu_drop_button hidden-xs']")
    public WebElement dropdownMenu;

    @FindBy(how = How.XPATH, using = "//label/button[@class='search_btn']")
    public WebElement loginBtn;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='userid']")
    public WebElement userNameSelector;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='password']")
    public WebElement passwordSelector;

    @FindBy(how = How.XPATH, using = "(//div/img[@border='0'])[2]")
    public WebElement captchaSelector;

    @FindBy(how = How.XPATH, using = "nlpAnswer")
    public WebElement captchaInput;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'SIGN IN')]")
    public WebElement SignInBtn;

    @FindBy(how = How.XPATH, using = "(//nav)[2]/div/div/label[1]")
    public WebElement profileNameSelector;

    public LandingPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
}
