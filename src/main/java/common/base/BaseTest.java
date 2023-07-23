package common.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import common.utils.CreateChromeDriver;
import common.utils.htmlreport.ExtentReport;
import common.utils.htmlreport.HtmlReportHelper;
import common.utils.htmlreport.ReportMapper;


/*TODO: 1.Read Data from excel file
TODO 2.Add API Logs Data into Html Report
 */

public class BaseTest extends CreateChromeDriver {
    WebDriver webDriver;
    ReportMapper reportMapper;
    protected static ArrayList<ReportMapper> reportData =new ArrayList<>();
    public void setUp() {
        webDriver = setDriver();
        String baseURL = "https://www.saucedemo.com/";
        webDriver.get(baseURL);
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void afterTestCaseExecution(ITestResult result) throws IOException {
        reportMapper=new ReportMapper();
        reportMapper.setTestCaseName(result.getMethod().getMethodName());
        if (result.getStatus() == ITestResult.SUCCESS)
            reportMapper.setTestCaseStatus("PASS");
        else if (result.getStatus() == ITestResult.FAILURE) {
            reportMapper.setTestCaseFailedReason(result.getThrowable().getMessage());
            reportMapper.setTestCaseStatus("FAILED");
        } else
            reportMapper.setTestCaseStatus("SKIPPED");
        if (result.getMethod().getGroups().length != 0)
            reportMapper.setGroupName(Arrays.toString(result.getMethod().getGroups()));
        HtmlReportHelper.getTestCaseExecutionTimeValues(result,reportMapper);
        HtmlReportHelper.takeScreenshot(driver,reportMapper);
        reportData.add(reportMapper);
        ExtentReport.dumpDataForExtentReport(result, reportMapper);
    }

    @BeforeSuite
    public void clearOrCreateScreenShotDir() {
        Path defaultDir = Paths.get(System.getProperty("user.dir"), "Screenshots");
        File dir = new File(defaultDir.toString());
        if (dir.exists()) {
            File[] listOfFiles = dir.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile())
                    file.delete();
            }
            dir.delete();
        }
        dir.mkdir();
    }

    @AfterClass(alwaysRun = true)
    public void quiteDriver() {
        System.out.println(reportData.get(0).getTestCaseName());
        if(webDriver!=null)
         webDriver.quit();
        if (ExtentReport.extent != null) {
            ExtentReport.extent.flush();
        }
    }
}

