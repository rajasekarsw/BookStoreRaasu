package com.bookstoreapi.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListenerClass implements ITestListener {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context) {
        var simpleDateFormat=new SimpleDateFormat("dd_MMM_HH_mm_ss");
        var name=simpleDateFormat.format(Calendar.getInstance().getTime());
        var reporter=new ExtentSparkReporter("./extentreports/"+name+"_reports.html");
        reporter.config().setReportName("BookStoreAPITestResults");
        reporter.config().setDocumentTitle("Report by Rajasekar");
        extentReports=new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Automation Tester","Rajasekar");
        extentReports.setSystemInfo("Email","Rajrams104@gmail.com");
        extentReports.setSystemInfo("Contact number","+91 7904245935");
    }

    @Override
    public void onFinish(ITestContext context) {
    extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest=extentReports.createTest(result.getMethod().getDescription());
        extentTest.info(result.getMethod().getMethodName()+" started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,"Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL,"Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP,"Skipped");
    }

}
