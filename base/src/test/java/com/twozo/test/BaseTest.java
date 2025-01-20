package com.twozo.test;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.page.settings.sales.activities.WebAutomationListener;
import com.twozo.page.homepage.HomePage;

import com.twozo.page.sign.SignIn;
//import com.twozo.test.settings.data.fields.PropertyLogExample;
import com.twozo.test.settings.data.fields.PropertyLogExample;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BaseTest {
    private static final Map<String, String> CONFIG = ConfigFileReader.get("config.Properties");

    private static final Map<String, String> CONFIG = ConfigFileReader.get("config.Properties");

    protected WebAutomationDriver automationDriver;
    protected SignIn signIn;
    protected static Set<BrowserCookie> cookies;
    protected static String link = CONFIG.get("Domain");

    @BeforeSuite
    public void setUp() {
        automationDriver = WebAutomationDriver.get();
        link = CONFIG.get("Domain");

        automationDriver.getWebNavigator().to(link);
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(5));

        SignIn.getInstance(automationDriver).signIn("2s@gmail.com", "A$12345a");

        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        SignIn.getInstance(automationDriver).signIn("2p@gmail.com", "A$12345a");
        SignIn.getInstance(automationDriver).signIn("2s@gmail.com", "A$12345a");
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(3));
        automationDriver.getExplicitWaitHandler().waitTillVisible(new Element(LocatorType.XPATH, "//*[text()='Sign In']", true));
        SignIn.getInstance(automationDriver).signIn(MAP.get("login.id"), MAP.get("login.password"));
        cookies = automationDriver.getSessionCookie().getCookies();
        automationDriver.close();
    }

    public String takeScreenShot(final String TestName, final WebAutomationDriver webAutomationDriver) throws IOException {
        final File sourceFile = webAutomationDriver.getScreenCapturer().getScreenshotAs(OutputType.FILE);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        final String timeStamp = dateFormat.format(new Date());
        final String path = System.getProperty("user.dir") + "\\reports\\" + TestName + "_" + timeStamp + ".png";
        final File file = new File(path);

        FileUtils.copyFile(sourceFile, file);

        return path;
    }
}