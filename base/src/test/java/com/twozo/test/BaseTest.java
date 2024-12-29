package com.twozo.test;

import com.github.javafaker.Faker;
import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.page.sign.SignIn;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import org.openqa.selenium.OutputType;
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
    private static final Map<String, String> CONFIG = ConfigFileReader.get("config.properties");
    protected static final Map<String, String> MAP = ConfigFileReader.get("locator/locator.Properties");
    protected static final String link = CONFIG.get("Domain");
    protected static Set<BrowserCookie> cookies;

    protected WebAutomationDriver automationDriver;
    protected SignIn signIn;
    protected Faker faker;

    @BeforeSuite
    public void setUp() {
        automationDriver = WebAutomationDriver.get();

        //link = CONFIG.get("Domain");
        signIn = SignIn.getInstance(automationDriver);
        faker = signIn.getFaker();
        signIn.navigateTo(link);
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(3));
        signIn.waitTillVisible(new Element(LocatorType.XPATH, "//*[text()='Sign In']", true));
        signIn.signIn(MAP.get("login.id"), MAP.get("login.password"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        cookies = automationDriver.getSessionCookie().getCookies();

        automationDriver.close();
    }

    public String takeScreenShot(final String TestName, final WebAutomationDriver driver) throws IOException {
        final File sourceFile = driver.getScreenCapturer().getScreenshotAs(OutputType.FILE);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        final String timeStamp = dateFormat.format(new Date());
        final String path = System.getProperty("user.dir") + "\\reports\\" + TestName + "_" + timeStamp + ".png";
        final File file = new File(path);

        return path;
    }

    public static String getFilePath(final String directory, final String fileName) {
        return Paths.get(directory, fileName).toString();
    }
}
