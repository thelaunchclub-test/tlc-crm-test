package com.twozo.test;

import com.twozo.extent.report.reporter.internal.extent.AbstractExtentReporter;
import com.twozo.extent.report.reporter.internal.extent.ExtentReporterInitializer;
import com.twozo.extent.report.reporter.internal.spark.SparkReporter;
import com.twozo.extent.report.test.service.ReportTest;
import com.twozo.test.listener.test.TestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

public class ReportGenerator extends BaseTest implements TestListener {
    private final AbstractExtentReporter reports = new ExtentReporterInitializer();
    private final ThreadLocal<ReportTest> threadLocal = new ThreadLocal<>();

    @Override
    public void onStart(final ITestContext context) {
        final SparkReporter sparkReporter = new SparkReporter("./DataFields.html");

        sparkReporter.setReportName("Data Fields");
        sparkReporter.setTitle("Data Fields Test Results");
        reports.attachReporter(sparkReporter);
        reports.systemInfo("OS", System.getProperty("os.name"));
        reports.systemInfo("JAVA VERSION", System.getProperty("java.version"));
    }

    @Override
    public void onTestStart(final ITestResult result) {
        System.out.println("test starts " + result.getMethod().getMethodName());
        final ReportTest reportTest = reports.createTest(result.getMethod().getMethodName());

        reportTest.getTabs().assignAuthor("Petchimuthu");
        threadLocal.set(reportTest);
    }

    @Override
    public void onFinish(final ITestContext context) {
        reports.flush();
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        final ReportTest reportTest = threadLocal.get();

        if (Objects.nonNull(reportTest)) {
            reportTest.getLog().pass(result.getMethod().getMethodName() + " is Passed");
        }
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        System.out.println("Test failed: " + result.getThrowable());
        final ReportTest reportTest = threadLocal.get();

        if (Objects.nonNull(reportTest) && Objects.nonNull(reportTest.getLog())) {
            reportTest.getLog().fail(result.getThrowable().getMessage());
        } else {
            System.out.println("ReportTest or ReportTest log is null");
        }

        final Object testObject = result.getInstance();

        try {
            final Field driverField = testObject.getClass().getDeclaredField("automationDriver");

            driverField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            exception.printStackTrace();
            throw new RuntimeException("No such field: 'automationDriver'", exception);
        }

        try {
            if (Objects.nonNull(automationDriver)) {
                assert reportTest != null;
                reportTest.getScreenCapture().fromPath(takeScreenShot(result.getMethod().getMethodName(), automationDriver), result.getMethod().getMethodName());
            } else {
                System.out.println("Driver is null. Cannot capture screenshot.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        result.setStatus(ITestResult.FAILURE);
    }
}

