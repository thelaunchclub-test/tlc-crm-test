package com.twozo.test;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.page.settings.currency.service.Currency;
import com.twozo.page.sign.SignIn;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.openqa.selenium.OutputType;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BaseTest {

    private static final Map<String, String> CONFIG = ConfigFileReader.get("config.Properties");

    protected WebAutomationDriver automationDriver;
    protected SignIn signIn;
    protected Set<BrowserCookie> cookies;
    public String link;
    protected Currency currency;

    @BeforeTest
    public void setUp() {
        automationDriver = WebAutomationDriver.get();
        link = CONFIG.get("Domain");

        automationDriver.getWebNavigator().to(link);
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        SignIn.getInstance(automationDriver).signIn("wl@gmail.com", "A$12345a");
        cookies = automationDriver.getSessionCookie().getCookies();

        automationDriver.close();
    }

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
