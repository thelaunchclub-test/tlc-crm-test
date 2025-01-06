package com.twozo.test.listener.report;

import com.twozo.test.listener.TestNgListener;
import org.testng.ISuite;
import org.testng.reporters.IReporterConfig;
import org.testng.reporters.PojoReporterConfig;
import org.testng.xml.XmlSuite;

import java.util.List;

public interface ReporterListener extends TestNgListener {

    default void generateReport(final List<XmlSuite> xmlSuites, final List<ISuite> suites, final String outputDirectory) {
    }

    default IReporterConfig getConfig() {
        return new PojoReporterConfig(this);
    }
}