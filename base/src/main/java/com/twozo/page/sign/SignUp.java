package com.twozo.page.sign;

import com.twozo.commons.exception.ErrorCode;
import com.twozo.page.BasePage;
import com.twozo.page.deal.DealPage;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.url.URL;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.error.code.WebDriverErrorCode;

import java.util.Collection;
import java.util.Objects;


public class SignUp extends BasePage {

    private static SignUp signUp;
    private final DealPage deal;
    private WebPageElement signUpButton;
    private WebPageElement emailField;
    private WebPageElement nextButton;
    private WebPageElement signInButton;
    private WebPageElement nameField;
    private WebPageElement passwordField;
    private WebPageElement confirmPasswordField;
    private WebPageElement companyField;
    private WebPageElement createAccountButton;
    private WebPageElement jobRoleDropDown;
    private Collection<WebPageElement> jobRoles;
    private WebPageElement completeSignUpButton;

    private SignUp(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
        click(getSignUpButton());

        if (!getURL().equals(URL.SIGN_UP)) {
            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND);
        }

        this.deal = DealPage.getInstance(webAutomationDriver);
    }

    public static SignUp getInstance(final WebAutomationDriver webAutomationDriver) {

      //  if (Objects.isNull(signUp)) {
            signUp = new SignUp(webAutomationDriver);
        //}

        return signUp;
    }

    public WebPageElement getSignUpButton() {

        if (Objects.isNull(signUpButton)) {
            signUpButton = findByText("Sign Up");
        }

        return signUpButton;
    }

    public WebPageElement getEmailField() {

        if (Objects.isNull(emailField)) {
            emailField = findByXpath("//*[@name='email']");
        }

        return emailField;
    }

    public WebPageElement getNextButton() {

        if (Objects.isNull(nextButton)) {
            nextButton = findByText("Next");
        }

        return nextButton;
    }

    public WebPageElement getSignInButton() {

        if (Objects.isNull(signInButton)) {
            signInButton = findByText("Sign In");
        }

        return signInButton;
    }

    public WebPageElement getNameField() {

        if (Objects.isNull(nameField)) {
            nameField = findByXpath("//*[contains(@placeholder,'name')]");
        }

        return nameField;
    }

    public WebPageElement getPasswordField() {

        if (Objects.isNull(passwordField)) {
            passwordField = findByXpath("//*[@name='password']");
        }

        return passwordField;
    }

    public WebPageElement getConfirmPasswordField() {

        if (Objects.isNull(confirmPasswordField)) {
            confirmPasswordField = findByXpath("//*[@name='confirmPassword']");
        }

        return confirmPasswordField;
    }

    public WebPageElement getCreateAccountButton() {

        if (Objects.isNull(createAccountButton)) {
            createAccountButton = findByText("Create Account");
        }

        return createAccountButton;
    }

    public WebPageElement getCompanyField() {

        if (Objects.isNull(companyField)) {
            companyField = findByXpath("//*[contains(@placeholder,'company')]");
        }

        return companyField;
    }

    public WebPageElement getJobRoleDropDown() {

        if (Objects.isNull(jobRoleDropDown)) {
            jobRoleDropDown = findByText("Select");
        }

        return jobRoleDropDown;
    }

    public Collection<WebPageElement> getJobRoles() {

        if (Objects.isNull(jobRoles)) {
            jobRoles = findElements(new Element(LocatorType.TAG_NAME, "li", true));
        }

        return jobRoles;
    }

    public WebPageElement getCompleteSignUpButton() {

        if (Objects.isNull(completeSignUpButton)) {
            completeSignUpButton = findByText("Complete Signup");
        }

        return completeSignUpButton;
    }

    public HomePage signUp(final Account account) {
        send(getEmailField(), account.email);
        click(getNextButton());
        send(getNameField(), account.getName());
        send(getPasswordField(), account.getPassword());
        send(getConfirmPasswordField(), account.getConfirmPassword());
        click(getCreateAccountButton());
        send(getCompanyField(), account.getCompany());
        click(getJobRoleDropDown());
        dropdown(account.getJobRole());
        click(getCompleteSignUpButton());

        return HomePage.getInstance(webAutomationDriver);
    }

    public SignIn signIn() {
        click(getSignInButton());

        return SignIn.getInstance(webAutomationDriver);

    }
}
