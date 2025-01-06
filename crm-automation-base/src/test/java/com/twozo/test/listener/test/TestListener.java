package com.twozo.test.listener.test;

import com.twozo.test.listener.TestNgListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public interface TestListener extends TestNgListener, ITestListener {

    default void onTestStart(final ITestResult result) {
    }

    default void onTestSuccess(final ITestResult result) {
    }

    default void onTestFailure(final ITestResult result) {
    }

    default void onTestSkipped(final ITestResult result) {
    }

    default void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    }

    default void onTestFailedWithTimeout(final ITestResult result) {
        this.onTestFailure(result);
    }

    default void onStart(final ITestContext context) {
    }

    default void onFinish(final ITestContext context) {
    }
}