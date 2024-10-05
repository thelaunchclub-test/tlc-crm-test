package com.twozo.test;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.page.sign.SignIn;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.openqa.selenium.OutputType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BaseTest {
    private static final Map<String, String> CONFIG = ConfigFileReader.get("Config.Properties");

    protected WebAutomationDriver automationDriver;
    protected SignIn signIn;
    protected static Set<BrowserCookie> cookies;
    protected static String link;

    @BeforeSuite
    public void setUp() {
        automationDriver = WebAutomationDriver.get();

        link = CONFIG.get("Domain");
        automationDriver.getWebNavigator().to(link);
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(50));
        SignIn.getInstance(automationDriver).signIn("x5@gmail.com", "A$12345a");
        cookies = automationDriver.getSessionCookie().getCookies();

        automationDriver.close();
    }

//    protected Set<BrowserCookie> getCookies(){
//        return cookies;
//    }
//
//    protected String get() {
//        return CONFIG.get("Domain");
//    }

    public String takeScreenShot(final String TestName, final WebAutomationDriver driver) throws IOException {
        final File sourceFile = driver.getScreenCapturer().getScreenshotAs(OutputType.FILE);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        final String timeStamp = dateFormat.format(new Date());
        final String path = System.getProperty("user.dir") + "\\reports\\" + TestName + "_" + timeStamp + ".png";
        final File file = new File(path);

        //FileUtils.

        return path;
    }
}
