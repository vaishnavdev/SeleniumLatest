package com.ikcon.tech.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @author VaishnavPuthiyanampa The runner class which is responsible to run all
 *         the scenarios prepared in feature file.
 */
@CucumberOptions(features = "src/test/java/com/ikcon/tech/feature", glue = "com/ikcon/tech/definition", monochrome = true, plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:")
public class IrctcTestNgTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
	return super.scenarios();
    }

}
