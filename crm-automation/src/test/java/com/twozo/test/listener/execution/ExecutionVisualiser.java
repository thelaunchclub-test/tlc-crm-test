package com.twozo.test.listener.execution;

import com.twozo.test.listener.TestNgListener;

public interface ExecutionVisualiser extends TestNgListener {

    void consumeDotDefinition(final String var);
}
