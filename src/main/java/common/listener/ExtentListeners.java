package common.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListeners implements ITestListener {
    public static int failureCount = 0;
    public static int totalTests = 0;
    public static int totalSkipped = 0;
    public static int totalPass = 0;

    @Override
    public void onTestStart(ITestResult result) {
        totalTests++;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        totalPass++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failureCount++;
        System.out.println(result.getThrowable().getMessage()+" TESTING");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        totalSkipped++;
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}

