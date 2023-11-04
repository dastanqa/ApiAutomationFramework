package com.dastan.listeners;

import com.dastan.annotations.FrameworkAnnotation;
import com.dastan.reports.ExtentLogger;
import com.dastan.reports.ExtentReport;
import com.dastan.utils.JiraUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener, ISuiteListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());

        String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();
        ExtentReport.addAuthor(authors);

        String[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
        ExtentReport.addCategory(categories);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName() + " has passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //Awaitibility
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
        String issueJira = JiraUtils.createIssueJira(String.valueOf(result.getThrowable()));
        ExtentLogger.fail("Issue created in Jira"+"http://localhost:8080/browse/"+issueJira);
    }


    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.tearDownReports();
    }
}
