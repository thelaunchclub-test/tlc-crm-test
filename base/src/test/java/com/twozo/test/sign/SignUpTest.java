package com.twozo.test.sign;

import com.twozo.commons.json.JsonObject;
import com.twozo.page.sign.Account;
import com.twozo.page.sign.SignUp;
import com.twozo.test.BaseTest;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public final class SignUpTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(SignUpTest.class);

    @DataProvider(name = "signUpData")
    public static Object[][] getSearchData() {
        return new TestDataProvider().getTestCases("SignUpData.json");
    }

    @Test(dataProvider = "signUpData")
    public void signUpWithValidDetails(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final Account account = new Account();

        account.setEmail(input.getString("email"));
        account.setName(input.getString("name"));
        account.setPassword(input.getString("password"));
        account.setConfirmPassword(input.getString("confirmPassword"));
        account.setCompany(input.getString("company"));
        account.setJobRole(input.getString("jobRole"));
        LOG.info("Signed up successfully");
        SignUp.getInstance(automationDriver).signUp(account);
    }
}
