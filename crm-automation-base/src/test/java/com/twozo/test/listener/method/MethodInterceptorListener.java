package com.twozo.test.listener.method;

import com.twozo.test.listener.TestNgListener;
import org.testng.IMethodInstance;
import org.testng.ITestContext;

import java.util.List;

public interface MethodInterceptorListener extends TestNgListener {

    List<IMethodInstance> intercept(final List<IMethodInstance> methodInstanceList, final ITestContext testContext);
}
