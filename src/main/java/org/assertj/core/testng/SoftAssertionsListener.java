package org.assertj.core.testng;

import org.assertj.core.api.SoftAssertions;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SoftAssertionsListener implements IHookable {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        List<Field> fields = getAssertionFields(testResult.getTestClass().getRealClass());
        reset(fields, testResult.getInstance());

        callBack.runTestMethod(testResult);

        for (Field field : fields) {
            try {
                ((SoftAssertions) field.get(testResult.getInstance())).assertAll();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot access field: " + field);
            }
        }
    }

    private void reset(List<Field> fields, Object instance) {
        for (Field field : fields) {
            try {
                field.set(instance, field.getType().newInstance());
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot access field: " + field);
            } catch (InstantiationException e) {
                throw new IllegalStateException("Cannot instantiate: " + field.getType());
            }
        }
    }

    private List<Field> getAssertionFields(Class<?> clazz) {

        List<Field> result = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(SoftAssertions.class)) {
                result.add(field);
            }
        }
        return result;
    }
}
