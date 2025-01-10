package com.twozo.page.settings.sales.activities;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.service.ElementInteraction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface WebAutomationListener {
    void beforeClickOn(ElementInteraction element, WebAutomationDriver driver);

    void afterClickOn(WebElement element, WebAutomationDriver driver);

//    default void beforeCall(Object target, Method method, Object[] args) {
//    }
//
//    default void afterCall(Object target, Method method, Object[] args, Object result) {
//    }
//
//    default void onErrors(Object target, Method method, Object[] args, InvocationTargetException e) {
//    }
//
//    default void beforeAnyWebAutomationDriverCall(WebAutomationDriver driver, Method method, Object[] args) {
//    }
//
//    default void afterAnyWebAutomationDriverDriverCall(WebAutomationDriver driver, Method method, Object[] args, Object result) {
//    }
//
//    default void onBeforeGet(WebAutomationDriver driver, String url) {
//    }
//
//    default void onAfterGet(WebAutomationDriver driver, String url) {
//    }
//
//    default void onBeforeGetCurrentUrl(WebAutomationDriver driver) {
//    }
//
//    default void onAfterGetCurrentUrl(WebAutomationDriver driver, String result) {
//    }
//
//    default void onBeforeGetTitle(WebAutomationDriver driver) {
//    }
//
//    default void onAfterGetTitle(WebAutomationDriver driver, String result) {
//    }
//
//    default void onBeforeFindElement(WebAutomationDriver driver, Element locator) {
//    }

//    default void afterFindElement(WebAutomationDriver driver, Element locator, WebElement result) {
//    }

//    default void onBeforeFindElements(WebAutomationDriver driver, Element locator) {
//    }

//    default void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
//    }
//
//    default void beforeGetPageSource(WebDriver driver) {
//    }
//
//    default void afterGetPageSource(WebDriver driver, String result) {
//    }
//
//    default void beforeClose(WebDriver driver) {
//    }
//
//    default void afterClose(WebDriver driver) {
//    }
//
//    default void beforeQuit(WebDriver driver) {
//    }
//
//    default void afterQuit(WebDriver driver) {
//    }
//
//    default void beforeGetWindowHandles(WebDriver driver) {
//    }
//
//    default void afterGetWindowHandles(WebDriver driver, Set<String> result) {
//    }
//
//    default void beforeGetWindowHandle(WebDriver driver) {
//    }
//
//    default void afterGetWindowHandle(WebDriver driver, String result) {
//    }
//
//    default void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
//    }
//
//    default void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
//    }
//
//    default void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
//    }
//
//    default void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
//    }
//
//    default void beforePerform(WebDriver driver, Collection<Sequence> actions) {
//    }
//
//    default void afterPerform(WebDriver driver, Collection<Sequence> actions) {
//    }
//
//    default void beforeResetInputState(WebDriver driver) {
//    }
//
//    default void afterResetInputState(WebDriver driver) {
//    }
//
//    default void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
//    }
//
//    default void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
//    }

//    default void beforeClick(ElementInteraction element) {
//    }
//
//    default void afterClick(ElementInteraction element) {
//    }
//
//    void beforeClickOn(ElementInteraction element, WebAutomationDriver driver);
//
//    void afterClickOn(WebElement element, WebAutomationDriver driver);

//    void afterClickOn(ElementInteraction element, WebAutomationDriver driver);

//    default void beforeSubmit(WebElement element) {
//    }
//
//    default void afterSubmit(WebElement element) {
//    }
//
//    default void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
//    }
//
//    default void afterSendKeys(WebElement element, CharSequence... keysToSend) {
//    }
//
//    default void beforeClear(WebElement element) {
//    }
//
//    default void afterClear(WebElement element) {
//    }
//
//    default void beforeGetTagName(WebElement element) {
//    }
//
//    default void afterGetTagName(WebElement element, String result) {
//    }
//
//    default void beforeGetAttribute(WebElement element, String name) {
//    }
//
//    default void afterGetAttribute(WebElement element, String name, String result) {
//    }
//
//    default void beforeIsSelected(WebElement element) {
//    }
//
//    default void afterIsSelected(WebElement element, boolean result) {
//    }
//
//    default void beforeIsEnabled(WebElement element) {
//    }
//
//    default void afterIsEnabled(WebElement element, boolean result) {
//    }
//
//    default void beforeGetText(WebElement element) {
//    }
//
//    default void afterGetText(WebElement element, String result) {
//    }
//
//    default void beforeFindElement(WebElement element, By locator) {
//    }
//
//    default void afterFindElement(WebElement element, By locator, WebElement result) {
//    }
//
//    default void beforeFindElements(WebElement element, By locator) {
//    }
//
//    default void afterFindElements(WebElement element, By locator, List<WebElement> result) {
//    }
//
//    default void beforeIsDisplayed(WebElement element) {
//    }
//
//    default void afterIsDisplayed(WebElement element, boolean result) {
//    }
//
//    default void beforeGetLocation(WebElement element) {
//    }
//
//    default void afterGetLocation(WebElement element, Point result) {
//    }
//
//    default void beforeGetSize(WebElement element) {
//    }
//
//    default void afterGetSize(WebElement element, Dimension result) {
//    }
//
//    default void beforeGetCssValue(WebElement element, String propertyName) {
//    }
//
//    default void afterGetCssValue(WebElement element, String propertyName, String result) {
//    }
//
//    default void beforeAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args) {
//    }
//
//    default void afterAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args, Object result) {
//    }
//
//    default void beforeTo(WebDriver.Navigation navigation, String url) {
//    }
//
//    default void afterTo(WebDriver.Navigation navigation, String url) {
//    }
//
//    default void beforeTo(WebDriver.Navigation navigation, URL url) {
//    }
//
//    default void afterTo(WebDriver.Navigation navigation, URL url) {
//    }
//
//    default void beforeBack(WebDriver.Navigation navigation) {
//    }
//
//    default void afterBack(WebDriver.Navigation navigation) {
//    }
//
//    default void beforeForward(WebDriver.Navigation navigation) {
//    }
//
//    default void afterForward(WebDriver.Navigation navigation) {
//    }
//
//    default void beforeRefresh(WebDriver.Navigation navigation) {
//    }
//
//    default void afterRefresh(WebDriver.Navigation navigation) {
//    }
//
//    default void beforeAnyAlertCall(Alert alert, Method method, Object[] args) {
//    }
//
//    default void afterAnyAlertCall(Alert alert, Method method, Object[] args, Object result) {
//    }
//
//    default void beforeAccept(Alert alert) {
//    }
//
//    default void afterAccept(Alert alert) {
//    }
//
//    default void beforeDismiss(Alert alert) {
//    }
//
//    default void afterDismiss(Alert alert) {
//    }
//
//    default void beforeGetText(Alert alert) {
//    }
//
//    default void afterGetText(Alert alert, String result) {
//    }
//
//    default void beforeSendKeys(Alert alert, String text) {
//    }
//
//    default void afterSendKeys(Alert alert, String text) {
//    }
//
//    default void beforeAnyOptionsCall(WebDriver.Options options, Method method, Object[] args) {
//    }
//
//    default void afterAnyOptionsCall(WebDriver.Options options, Method method, Object[] args, Object result) {
//    }
//
//    default void beforeAddCookie(WebDriver.Options options, Cookie cookie) {
//    }
//
//    default void afterAddCookie(WebDriver.Options options, Cookie cookie) {
//    }
//
//    default void beforeDeleteCookieNamed(WebDriver.Options options, String name) {
//    }
//
//    default void afterDeleteCookieNamed(WebDriver.Options options, String name) {
//    }
//
//    default void beforeDeleteCookie(WebDriver.Options options, Cookie cookie) {
//    }
//
//    default void afterDeleteCookie(WebDriver.Options options, Cookie cookie) {
//    }
//
//    default void beforeDeleteAllCookies(WebDriver.Options options) {
//    }
//
//    default void afterDeleteAllCookies(WebDriver.Options options) {
//    }
//
//    default void beforeGetCookies(WebDriver.Options options) {
//    }
//
//    default void afterGetCookies(WebDriver.Options options, Set<Cookie> result) {
//    }
//
//    default void beforeGetCookieNamed(WebDriver.Options options, String name) {
//    }
//
//    default void afterGetCookieNamed(WebDriver.Options options, String name, Cookie result) {
//    }
//
//    default void beforeAnyTimeoutsCall(WebDriver.Timeouts timeouts, Method method, Object[] args) {
//    }
//
//    default void afterAnyTimeoutsCall(WebDriver.Timeouts timeouts, Method method, Object[] args, Object result) {
//    }
//
//    default void beforeImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
//    }
//
//    default void afterImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
//    }
//
//    default void beforeSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
//    }
//
//    default void afterSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
//    }
//
//    default void beforePageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
//    }
//
//    default void afterPageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
//    }
//
//    default void beforeAnyWindowCall(WebDriver.Window window, Method method, Object[] args) {
//    }
//
//    default void afterAnyWindowCall(WebDriver.Window window, Method method, Object[] args, Object result) {
//    }
//
//    default void beforeGetSize(WebDriver.Window window) {
//    }
//
//    default void afterGetSize(WebDriver.Window window, Dimension result) {
//    }
//
//    default void beforeSetSize(WebDriver.Window window, Dimension size) {
//    }
//
//    default void afterSetSize(WebDriver.Window window, Dimension size) {
//    }
//
//    default void beforeGetPosition(WebDriver.Window window) {
//    }
//
//    default void afterGetPosition(WebDriver.Window window, Point result) {
//    }
//
//    default void beforeSetPosition(WebDriver.Window window, Point position) {
//    }
//
//    default void afterSetPosition(WebDriver.Window window, Point position) {
//    }
//
//    default void beforeMaximize(WebDriver.Window window) {
//    }
//
//    default void afterMaximize(WebDriver.Window window) {
//    }
//
//    default void beforeFullscreen(WebDriver.Window window) {
//    }
//
//    default void afterFullscreen(WebDriver.Window window) {
//    }
//
//    default void beforeAnyTargetLocatorCall(WebDriver.TargetLocator targetLocator, Method method, Object[] args) {
//    }
//
//    default void afterAnyTargetLocatorCall(WebDriver.TargetLocator targetLocator, Method method, Object[] args, Object result) {
//    }
//
//    default void beforeFrame(WebDriver.TargetLocator targetLocator, int index) {
//    }
//
//    default void afterFrame(WebDriver.TargetLocator targetLocator, int index, WebDriver driver) {
//    }
//
//    default void beforeFrame(WebDriver.TargetLocator targetLocator, String nameOrId) {
//    }
//
//    default void afterFrame(WebDriver.TargetLocator targetLocator, String nameOrId, WebDriver driver) {
//    }
//
//    default void beforeFrame(WebDriver.TargetLocator targetLocator, WebElement frameElement) {
//    }
//
//    default void afterFrame(WebDriver.TargetLocator targetLocator, WebElement frameElement, WebDriver driver) {
//    }
//
//    default void beforeParentFrame(WebDriver.TargetLocator targetLocator) {
//    }
//
//    default void afterParentFrame(WebDriver.TargetLocator targetLocator, WebDriver driver) {
//    }
//
//    default void beforeWindow(WebDriver.TargetLocator targetLocator, String nameOrHandle) {
//    }
//
//    default void afterWindow(WebDriver.TargetLocator targetLocator, String nameOrHandle, WebDriver driver) {
//    }
//
//    default void beforeNewWindow(WebDriver.TargetLocator targetLocator, WindowType typeHint) {
//    }
//
//    default void afterNewWindow(WebDriver.TargetLocator targetLocator, WindowType typeHint, WebDriver driver) {
//    }
//
//    default void beforeDefaultContent(WebDriver.TargetLocator targetLocator) {
//    }
//
//    default void afterDefaultContent(WebDriver.TargetLocator targetLocator, WebDriver driver) {
//    }
//
//    default void beforeActiveElement(WebDriver.TargetLocator targetLocator) {
//    }
//
//    default void afterActiveElement(WebDriver.TargetLocator targetLocator, WebDriver driver) {
//    }
//
//    default void beforeAlert(WebDriver.TargetLocator targetLocator) {
//    }
//
//    default void afterAlert(WebDriver.TargetLocator targetLocator, Alert alert) {
//    }

}
