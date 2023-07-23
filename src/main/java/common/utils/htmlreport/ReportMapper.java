package common.utils.htmlreport;

public class ReportMapper {
    public String testCaseName="NA";
    public String testCaseStatus="NA";
    public String testCaseFailedReason="NA";
    public String testCaseExecutionTime="NA";
    public String testStartTime="NA";
    public String testCaseDescription="NA";
    public String screenShotBase64String ="NA";
    public String groupName="NA";

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestCaseStatus() {
        return testCaseStatus;
    }

    public void setTestCaseStatus(String testCaseStatus) {
        this.testCaseStatus = testCaseStatus;
    }

    public String getTestCaseFailedReason() {
        return testCaseFailedReason;
    }

    public void setTestCaseFailedReason(String testCaseFailedReason) {
        this.testCaseFailedReason = testCaseFailedReason;
    }

    public String getTestCaseExecutionTime() {
        return testCaseExecutionTime;
    }

    public void setTestCaseExecutionTime(String testCaseExecutionTime) {
        this.testCaseExecutionTime = testCaseExecutionTime;
    }

    public String getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public String getScreenShotBase64String() {
        return screenShotBase64String;
    }

    public void setScreenShotBase64String(String screenShotBase64String) {
        this.screenShotBase64String = screenShotBase64String;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
