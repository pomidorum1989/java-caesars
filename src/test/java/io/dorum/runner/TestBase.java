package io.dorum.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public abstract class TestBase extends AbstractTestNGCucumberTests {

}
