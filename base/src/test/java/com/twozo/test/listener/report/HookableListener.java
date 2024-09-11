package com.twozo.test.listener.report;

import com.twozo.test.listener.TestNgListener;
import org.testng.IHookCallBack;
import org.testng.ITestResult;

public interface HookableListener extends TestNgListener {

    void run(final IHookCallBack var1, ITestResult var2);
}
