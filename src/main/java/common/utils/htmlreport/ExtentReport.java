package common.utils.htmlreport;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import common.base.BaseTest;

public class ExtentReport extends BaseTest {
	static Path filePath = Paths.get(System.getProperty("user.dir"), "extentReport.html");
	public static ExtentReports extent = common.listener.ExtentManager.createInstance(filePath.toString());
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

	public static void dumpDataForExtentReport(ITestResult result, ReportMapper reportMapper) throws IOException {

		ExtentTest extentTest;
		extentTest = extent.createTest(reportMapper.getTestCaseName()).assignCategory(reportMapper.getGroupName());
		testReport.set(extentTest);

		if (result.getStatus() == ITestResult.SUCCESS) {
			String passLogg = "TESTCASE PASSED";
			Markup m = MarkupHelper.createLabel(passLogg, ExtentColor.GREEN);
			testReport.get().log(Status.PASS, m);
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			String failureLogg = "TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			testReport.get().log(Status.FAIL, m);
			testReport.get()
					.fail("<summary>" + "<b>" + "<font color=" + "white>"
							+ "Exception Occurred" + "</font>" + "</b >" + "</summary>"
							+ reportMapper.getTestCaseFailedReason() + " \n");
			testReport.get().log(Status.INFO,"ScreenShot",MediaEntityBuilder.createScreenCaptureFromBase64String(reportMapper.getScreenShotBase64String()).build());
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			String failureLogg = "TEST CASE SKIPPED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.YELLOW);
			testReport.get().log(Status.SKIP, m);
			testReport.get()
					.fail("<summary>" + "<b>" + "<font color=" + "white>"
							+ "Exception Occurred" + "</font>" + "</b >" + "</summary>"
							+ reportMapper.getTestCaseFailedReason()  + " \n");
		}

		testReport.get()
				.info("<summary>" + "<b>" + "<font color=" + "white>"
						+ "TestCase Description " + "</font>" + "</b >" + "</summary>"
						+ reportMapper.getTestCaseDescription()+ " \n");

		testReport.get()
				.info("<summary>" + "<b>" + "<font color=" + "white>"
						+ "TestCase Execution Time(ms)  " + "</font>" + "</b >" + "</summary>"
						+ reportMapper.getTestCaseExecutionTime() + " \n");
		testReport.get()
				.info("<summary>" + "<b>" + "<font color=" + "white>"
						+ "TestCase Start Timestamp  " + "</font>" + "</b >" + "</summary>"
						+ reportMapper.getTestStartTime()  + " \n");
	}
}
