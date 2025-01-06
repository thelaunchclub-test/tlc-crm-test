package com.twozo.test.listener.suite;

import com.twozo.test.listener.TestNgListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public interface TestSuiteListener extends TestNgListener {

    default void alterSuite(final List<XmlSuite> suites) {
    }
}