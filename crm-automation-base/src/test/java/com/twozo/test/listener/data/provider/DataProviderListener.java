package com.twozo.test.listener.data.provider;

import com.twozo.test.listener.TestNgListener;
import org.testng.IDataProviderListener;
import org.testng.IDataProviderMethod;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

public interface DataProviderListener extends TestNgListener, IDataProviderListener {

    default void beforeDataProviderExecution(final IDataProviderMethod dataProviderMethod, final ITestNGMethod method, final ITestContext testContext) {
    }

    default void afterDataProviderExecution(final IDataProviderMethod dataProviderMethod, final ITestNGMethod method, final ITestContext iTestContext) {
    }

    default void onDataProviderFailure(final ITestNGMethod method, final ITestContext ctx, final RuntimeException t) {
    }
}

