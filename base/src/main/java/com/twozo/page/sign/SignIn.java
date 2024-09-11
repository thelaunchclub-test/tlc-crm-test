package com.twozo.page.sign;

import com.twozo.page.BasePage;
import com.twozo.page.deal.Deal;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.xpath.AttributeName;
import com.twozo.page.xpath.TagName;
import com.twozo.page.xpath.XPath;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.Arrays;
import java.util.List;

public class SignIn extends BasePage {

    private static SignIn signIn;

    private Deal deal;
    private SignUp signUp;
    private WebPageElement greetingText;
    private WebPageElement passwordText;
    private WebPageElement workEmailIdText;
    private WebPageElement rememberMeCheckBoxToCheckIfDisplayed;
    private WebPageElement rememberMeCheckBox;
    private WebPageElement signInButton;
    private WebPageElement emailField;
    private WebPageElement passwordField;
    private WebPageElement forgotPasswordButton;
    private WebPageElement signUpButton;
    private WebPageElement emailIdPlaceholder;
    private WebPageElement passwordPlaceholder;
    private WebPageElement emailValidationText;
    private WebPageElement passwordValidationText;
    private WebPageElement eyeIcon;
    private WebPageElement resetPasswordButton;
    private WebPageElement twozoLogo;
    private WebPageElement forgotPasswordPageStatement;
    private WebPageElement forgotPasswordPageInvalidEmailIdValidationText;
    private WebPageElement forgotPasswordPageNoEmailIdValidationText;

    private SignIn(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);

//        if (!getURL().equals(URL.LOG_IN)) {
//            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND, "exp page not found");
//        }
    }

    public static SignIn getInstance(final WebAutomationDriver webAutomationDriver) {
        return new SignIn(webAutomationDriver);
    }

    public Deal getDeal() {
        return Deal.getInstance(webAutomationDriver);
    }

    public SignUp getSignUp() {
        return SignUp.getInstance(webAutomationDriver);
    }

    public WebPageElement getTwozoLogo() {
        return findByXpath(new XPath(TagName.IMG, AttributeName.ALT, "twozo", 1));
    }

    public WebPageElement getGreetingText() {
        return findByText("Welcome Back!");
    }

    public WebPageElement getWorkEmailIdText() {
        return findByText("Work Email Id");
    }

    public WebPageElement getPasswordText() {
        return findByText("Password");
    }

    public WebPageElement getRememberMeCheckBox() {
        return findByXpath("//*[@type='checkbox']");
    }

    public WebPageElement getRememberMeCheckBoxToCheckIfDisplayed() {
        return findLeftElement(List.of(new Element(LocatorType.XPATH, "//*[text()='Remember Me']"
                , true), new Element(LocatorType.TAG_NAME, "svg", false)));
    }

    public WebPageElement getSignInButton() {
        return findByText("Sign In");
    }

    public WebPageElement getEmailField() {
        emailField = initializeElement(emailField, () -> findByXpath("//input[@name='email']"));

        return emailField;
    }

    public WebPageElement getPasswordField() {
        return findByXpath("//*[@name='password']");
    }

    public WebPageElement getForgotPasswordButton() {
        return findByText("Forgot Password?");
    }

    public WebPageElement getResetPasswordButton() {
        return findByText("Reset Password");
    }

    public WebPageElement getForgotPasswordPageStatement() {
        return findByXpath("//*[contains(text(),'worries')]");
    }

    public WebPageElement getSignUpButton() {
        return findByText("Sign Up");
    }

    public WebPageElement getEmailIdPlaceholder() {
        return findByXpath("//*[@placeholder='Enter your work email id']");
    }

    public WebPageElement getPasswordPlaceholder() {
        return findByXpath("//*[@placeholder='Enter password']");
    }

    public HomePage signIn(final String email, final String password) {
        send(getEmailField(), email);
        send(getPasswordField(), password);
        click(getSignInButton());

        return HomePage.getInstance(webAutomationDriver);
    }

    public void facebookLogin() {
        send(findByXpath("//*[@name='email']"), "9566452216");
        send(findByXpath("//*[@type='password']"), "Shab$2015");
        click(findByText("Log in"));
    }

    public WebPageElement getEmailValidationText() {
        emailValidationText = initializeElement(emailValidationText, () -> findByText("Please enter a valid work email id"));
        return emailValidationText;
    }

    public WebPageElement getForgotPasswordPageInvalidEmailIdValidationText() {
        forgotPasswordPageInvalidEmailIdValidationText = initializeElement(forgotPasswordPageInvalidEmailIdValidationText, () -> findByText("Please enter a valid email id"));
        return forgotPasswordPageInvalidEmailIdValidationText;
    }

    public WebPageElement getForgotPasswordPageNoEmailIdValidationText() {
        forgotPasswordPageNoEmailIdValidationText = initializeElement(forgotPasswordPageNoEmailIdValidationText, () -> findByText("Please enter your email address"));
        return forgotPasswordPageNoEmailIdValidationText;
    }

    public WebPageElement getPasswordValidationText() {
        passwordValidationText = initializeElement(passwordValidationText, () -> findByText("Invalid Username / Password"));
        return passwordValidationText;
    }

    public WebPageElement getEyeIcon() {
        // EyeIcon initialization logic
        return eyeIcon;
    }

    public SignUp switchToSignUp() {
        return getSignUp();
    }

    public boolean isLogInPageDetailsDisplayed() {
        final List<WebPageElement> elementsToCheck = Arrays.asList(
                getGreetingText(),
                getWorkEmailIdText(),
                getPasswordText(),
                getRememberMeCheckBoxToCheckIfDisplayed(),
                getSignInButton()
        );

        for (final WebPageElement element : elementsToCheck) {

            if (!isDisplayed(element)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPlaceholderForWorkEmailIdDisplayed() {
        return isDisplayed(getEmailIdPlaceholder());
    }

    public void isEmailValidationDisplayed(final String invalidEmailId) {
        send(getEmailField(), invalidEmailId);
        click(getSignInButton());
    }

    public boolean isPlaceholderForPasswordDisplayed() {
        return isDisplayed(getPasswordPlaceholder());
    }

    public boolean isEyeIconDisplayed() {
        return isDisplayed(getEyeIcon());
    }

    public boolean isPasswordHidden(final String password) {
        send(getPasswordField(), password);
        return getAttribute(getPasswordField(), "type").equals("password");
    }

    public boolean isPasswordUnhidden(final String password) {
        send(getPasswordField(), password);
        click(getEyeIcon());
        return getAttribute(getPasswordField(), "type").equals("text");
    }

    public boolean isPasswordHiddenAgain(final String password) {
        send(getPasswordField(), password);
        click(getEyeIcon());
        click(getEyeIcon());
        return getAttribute(getPasswordField(), "type").equals("password");
    }

    public boolean isForgotPasswordButtonDisplayed() {
        return isDisplayed(getForgotPasswordButton());
    }

    public boolean isResetPasswordPageDisplayed() {
        click(getForgotPasswordButton());
        return isDisplayed(getForgotPasswordPageStatement());
    }

    public boolean isResetPasswordPageDetailsDisplayed() {
        click(getForgotPasswordButton());
        isDisplayed(getTwozoLogo());
        isDisplayed(getWorkEmailIdText());
        isDisplayed(getEmailField());
        return isDisplayed(getResetPasswordButton());
    }

    public boolean isForgotPasswordPageStatementDisplayed() {
        click(getForgotPasswordButton());
        return isDisplayed(getForgotPasswordPageStatement());
    }

    public void resetPassword(final String emailId) {
        click(getForgotPasswordButton());
        send(getEmailField(), emailId);
        click(getResetPasswordButton());
    }

    public void isResetLinkReceivedIfDifferentEmailIdProvided(final String emailId, final String differentEmailId) {
        send(getEmailField(), emailId);
        click(getForgotPasswordButton());
        send(getEmailField(), differentEmailId);
        click(getResetPasswordButton());  //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    public boolean isRememberMeCheckBoxDisplayed() {
        return isDisplayed(getRememberMeCheckBoxToCheckIfDisplayed());
    }

    public boolean isCheckboxIsUncheckedByDefault() {
        return getAttribute(getRememberMeCheckBoxToCheckIfDisplayed(), "style").equals("fill: none;");
    }

    public void check() {
        hover(new Element(LocatorType.XPATH, "//*[text()='Sign In']", true));
    }
}
