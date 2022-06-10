package com.oIAAutomation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
// import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
// import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting implements ITestListener {

    private static final Logger logger = LogManager.getLogger(Reporting.class);
    private ReadConfig readconfig = new ReadConfig();

    public ExtentSparkReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentLogger;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timeStamp + ".html";
        // specify location of the report
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/Reports/" + repName);
        try {
            htmlReporter.loadXMLConfig(readconfig.getExtentReportConfigPath());
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host name", "localhost");
        extentReports.setSystemInfo("Environemnt", "QA");
        extentReports.setSystemInfo("user", "Garvit");
        htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // Tile of report
        // name of the report
        htmlReporter.config().setReportName("Functional Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        extentLogger = extentReports.createTest(tr.getName()); // create new entry in th report
        // send the passed information to the report with GREEN color highlighted
        extentLogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        extentLogger = extentReports.createTest(tr.getName()); // create new entry in th report
        extentLogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send
        // the passed information to the report with GREEN color highlighted
        String screenshotPath = "/home/rao/eclipse-workspace/oIAAutomation/Screenshots" + tr.getName() + ".png";
        File f = new File(screenshotPath);
        if (f.exists()) {
            extentLogger.fail("Screenshot is below:" + extentLogger.addScreenCaptureFromPath(screenshotPath));
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        extentLogger = extentReports.createTest(tr.getName()); // create new entry in th report
        extentLogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extentReports.flush();
    }

}
