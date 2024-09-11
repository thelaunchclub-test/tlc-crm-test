package com.twozo.test.listener.annotation;

import com.twozo.test.listener.TestNgListener;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public interface AnnotationListener extends TestNgListener {

    default void transformer(final ITestAnnotation annotation, final Class<?> testClass, final Constructor<?> testConstructor, final Method testMethod) {
    }

    default void transform(final IConfigurationAnnotation annotation, final Class<?> testClass, final Constructor<?> testConstructor, final Method testMethod) {
    }

    default void transform(final IDataProviderAnnotation annotation, final Method method) {
    }

    default void transform(final IFactoryAnnotation annotation, final Method method) {
    }

    default void transform(final IListenersAnnotation annotation, final Class<?> testClass) {
    }
}