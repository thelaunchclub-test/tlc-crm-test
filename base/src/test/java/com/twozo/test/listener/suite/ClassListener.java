package com.twozo.test.listener.suite;

import com.twozo.test.listener.TestNgListener;
import org.testng.ITestClass;

public interface ClassListener extends TestNgListener {

    default void onBeforeClass(final ITestClass testClass) {
    }

    default void onAfterClass(final ITestClass testClass) {
    }
}
