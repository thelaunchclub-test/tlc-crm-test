package com.twozo.test.listener.configuration;

import com.twozo.test.listener.TestNgListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public interface ConfigurationListener extends TestNgListener {

    default void onConfigurationSuccess(final ITestResult testResult) {
    }

    default void onConfigurationSuccess(final ITestResult testResult, ITestNGMethod testNGMethod) {
    }

    default void onConfigurationFailure(final ITestResult testResult) {
    }

    default void onConfigurationFailure(final ITestResult testResult, final ITestNGMethod testNGMethod) {
    }

    default void onConfigurationSkip(final ITestResult testResult) {
    }

    default void onConfigurationSkip(final ITestResult testResult, final ITestNGMethod testNGMethod) {
    }

    default void beforeConfiguration(final ITestResult testResult) {
    }

    default void beforeConfiguration(final ITestResult testResult, final ITestNGMethod testNGMethod) {
    }
}
