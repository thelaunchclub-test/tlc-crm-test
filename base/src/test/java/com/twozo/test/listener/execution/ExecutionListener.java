package com.twozo.test.listener.execution;

import com.twozo.test.listener.TestNgListener;
import org.testng.IExecutionListener;

public interface ExecutionListener extends IExecutionListener, TestNgListener {

    default void onExecutionStart() {
    }

    default void onExecutionFinish() {
    }
}
