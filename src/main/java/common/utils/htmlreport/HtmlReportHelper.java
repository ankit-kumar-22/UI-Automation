package common.utils.htmlreport;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class HtmlReportHelper {

	public static void getTestCaseExecutionTimeValues(ITestResult tr,ReportMapper reportMapper) {
		String pattern = "hh:mm:ss a";
		Date startTime = new Date(tr.getStartMillis());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		reportMapper.setTestStartTime(simpleDateFormat.format(startTime));
		long time = tr.getEndMillis() - tr.getStartMillis();
		reportMapper.setTestCaseExecutionTime(String.valueOf(time));
		reportMapper.setTestCaseDescription(tr.getMethod().getDescription());
	}

	public static void takeScreenshot(WebDriver driver,ReportMapper reportMapper) {
		String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		reportMapper.setScreenShotBase64String(screenshotBase64);
	}
}
