package com.twozo.test.listener.method;

import com.twozo.test.listener.TestNgListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public interface InvokeMethodListener extends TestNgListener, IInvokedMethodListener {

    default void beforeInvocation(final IInvokedMethod method, ITestResult testResult) {
    }

    default void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    default void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    default void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }
}
