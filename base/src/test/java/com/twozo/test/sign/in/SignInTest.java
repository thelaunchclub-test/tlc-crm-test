package com.twozo.test.sign.in;

import com.twozo.test.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class SignInTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(SignInTest.class);

    @Test
    public void isLogInPageDetailsDisplayed() {
        Assert.assertTrue(signIn.isLogInPageDetailsDisplayed());
        LOG.info("Log in page details are displayed");
    }

    @Test
    public void isPlaceholderForWorkEmailIdDisplayed() {
        Assert.assertTrue(signIn.isPlaceholderForWorkEmailIdDisplayed());
        LOG.info("Email id placeholder is displayed");
    }

    @Test
    public void checkEmailValidation() {
        signIn.isEmailValidationDisplayed("ravi");
        Assert.assertTrue(signIn.isDisplayed(signIn.getEmailValidationText()));
        LOG.info("Email validation is displayed");
    }

    @Test
    public void isPlaceholderForPasswordDisplayed() {
        Assert.assertTrue(signIn.isPlaceholderForPasswordDisplayed());
        LOG.info("Password placeholder is displayed");
    }

    @Test
    public void isEyeIconDisplayed() {
        Assert.assertTrue(signIn.isEyeIconDisplayed());
    }

    @Test
    public void isPasswordHidden() {
        Assert.assertTrue(signIn.isPasswordHidden("wert"));
    }

    @Test
    public void isPasswordUnhidden() {
        Assert.assertTrue(signIn.isPasswordUnhidden("wert"));
    }

    @Test
    public void isPasswordHiddenAgain() {
        Assert.assertTrue(signIn.isPasswordHiddenAgain("wert"));
    }

    @Test
    public void isForgotPasswordTextDisplayed() {
        Assert.assertTrue(signIn.isForgotPasswordButtonDisplayed());
    }

    @Test
    public void isForgotPasswordPageDisplayed() {
        Assert.assertTrue(signIn.isResetPasswordPageDisplayed());
    }

    @Test
    public void isForgotPasswordPageDetailsDisplayed() {
        Assert.assertTrue(signIn.isResetPasswordPageDetailsDisplayed());
        LOG.info("Reset password page details are displayed");
    }

    @Test
    public void isForgotPasswordPageStatementDisplayed() {
        Assert.assertTrue(signIn.isForgotPasswordPageStatementDisplayed());
        LOG.info("Forgot password page statement is displayed");
    }

    @Test
    public void verifyResetPageValidationWithInvalidData() {
        signIn.resetPassword("677");
        Assert.assertTrue(signIn.isDisplayed(signIn.getForgotPasswordPageInvalidEmailIdValidationText()));
        LOG.info("Reset password page details are displayed");
    }

    @Test
    public void verifyResetPageValidationWithNoData() {
        signIn.resetPassword("");
        Assert.assertTrue(signIn.isDisplayed(signIn.getForgotPasswordPageNoEmailIdValidationText()));
        LOG.info("Reset password page validation with no data is displayed");
    }

    @Test
    public void verifyResetPageValidationWithValidData() {
        signIn.resetPassword("ravi@gmail.com"); //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyInbox() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyTemplateOfTheInbox() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyTheCopyRightInfo() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyLogoInTheMail() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyInvalidLink() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyRequestNewLink() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyNewRequestedLink() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyPlaceholderOfNewPasswordAndConfirmPassword() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyToolTipOfNewPassword() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyEyeIconOnBothFields() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void verifyInvalidPassword() {
        //TODO: Implement the code after the functionality for the sign-in page is completed.
    }

    @Test
    public void isRememberMeCheckBoxDisplayed() {
        Assert.assertTrue(signIn.isRememberMeCheckBoxDisplayed());
    }

    @Test
    public void isCheckboxIsUncheckedByDefault() {
        Assert.assertTrue(signIn.isCheckboxIsUncheckedByDefault());
    }

    @Test
    public void signIn() {
       signIn.signIn("aaaa@gmail.com", "A$12345a");

        //ravi$123.stream().collect(Coll).filter(Deal::isDisplayed).map()forEach(System.out::println);

    }

//    private void s(){
//        final Collection<TestCase> testCases;
//
//        for (final TestCase testCase: testCases) {
//            final Map<String, Object> k = testCase.getInput();
//
//            testCase.getId();
//            k.containsKey("email");
//
//            k.get("password");
//        }
//    }
}

