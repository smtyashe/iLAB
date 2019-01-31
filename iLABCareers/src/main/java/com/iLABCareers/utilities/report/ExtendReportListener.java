package com.iLABCareers.utilities.report;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.iLABCareers.utilities.BaseMethods;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

/**
 * This class is responsible for updating the report file with the results of
 * the testcase.
 * 
 * 
 */
public class ExtendReportListener extends TestListenerAdapter {
	private static Logger log = Logger.getLogger(ExtendReportListener.class.getName());
	private static ExtentReports extentReports=null;
	
	@Override
	public void onTestFailure(final ITestResult tr) {
		super.onTestFailure(tr);
		addReport(tr, LogStatus.FAIL, true);
		log(tr.getName() + "--Test method failed\n");
	}

	@Override
	public void onTestSkipped(final ITestResult tr) {
		super.onTestSkipped(tr);
		addReport(tr, LogStatus.SKIP, false);
		log(tr.getName() + "--Test method skipped\n");
	}

	@Override
	public void onTestSuccess(final ITestResult tr) {
		super.onTestSuccess(tr);
		addReport(tr, LogStatus.PASS, true);
		log(tr.getName() + "--Test method success\n");
	}

	/**
	 * Responsible for displaying the given message on a console.
	 * 
	 * @param message
	 *            The message to be printed on a console.
	 */
	private void log(final String message) {
		log.info(message);
	}

	/**
	 * Adds the results output of the testcase to the report file.
	 * 
	 * @param tr
	 *            Contains the results of the testcase.
	 * @param logStatus
	 *            The status of the testcase, eg. Failed, passed, etc
	 * @param takeScreenShot
	 *            Boolean value indicating whether the screenshot should be
	 *            taken.
	 */
	public void addReport(final ITestResult tr, final LogStatus logStatus,
			final boolean takeScreenShot) {

		final BaseMethods instance = (BaseMethods) tr.getInstance();
		WebDriver webDriver = instance.getDriver();
		if (extentReports == null) {
			String fileName = System.getProperty("remote.driver.capability","report.html");
			if(!fileName.endsWith(".html"))
			{
				fileName=fileName+".html";
			}
			try
			{
				FileUtils.deleteDirectory(new File("report"));
			}
			catch (IOException e)
			{
				log.info(e.getLocalizedMessage());
			}
			extentReports = new ExtentReports("report/"+fileName, false,
					DisplayOrder.OLDEST_FIRST, NetworkMode.ONLINE);
		}
		
		final ExtentTest test = extentReports.startTest(tr.getInstanceName()+"#"+tr.getName(),
				"Test Description");

		try {
			final Object[] parameters = tr.getParameters();
			final StringBuffer sb = new StringBuffer("_");
			String testMethodSignature = "(";
			if (parameters != null && parameters.length > 0) {
				for (int j = 0; j < parameters.length; j++) {
					testMethodSignature = testMethodSignature + ", "
							+ parameters[j].toString();
					sb.append(parameters[j] == null ? "null" : "_"
							+ parameters[j].toString().replaceAll(
									"[^a-zA-Z0-9]+", ""));
				}
			}
			testMethodSignature = tr.getName() + testMethodSignature + ")";

			if (logStatus == LogStatus.FAIL) {
				test.log(LogStatus.FAIL, testMethodSignature
						+ ": FAILED. Reason: " + tr.getThrowable().getMessage());
			} 
			else if (logStatus == LogStatus.SKIP)
			{
				test.log(LogStatus.SKIP, testMethodSignature + ": SKIPED");
			}
			else 
			{
				test.log(LogStatus.PASS, testMethodSignature + ": PASSED");
			}
			
			String testData = instance.getCurrentTestData();

			if (testData != null && !testData.isEmpty()) {
				test.log(LogStatus.INFO, "inputData: " + testData);
			}

			if (takeScreenShot) {
				test.log(LogStatus.INFO,test.addScreenCapture(captureScreen(webDriver,
								tr.getName() + sb.toString() + "_screenshot")));
			}

		} catch (final Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
			log.info(e.getMessage());
		}
		extentReports.endTest(test);

		extentReports.flush();
	}

	/**
	 * Responsible for taking the screenshots.
	 * 
	 * @param driver
	 *            The webDriver used to execute the test.
	 * @param ImagesPath
	 *            The path where the name image will be saved.
	 * @return A path to the image.
	 */
	private String captureScreen(final WebDriver driver, String ImagesPath) {
		final TakesScreenshot oScn = (TakesScreenshot) driver;
		final File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		ImagesPath = "images/" + ImagesPath
				+ Long.toString(System.currentTimeMillis());
		String ImagesPathtmp = "./report/" + ImagesPath;
		final File oDest = new File(ImagesPathtmp + ".png");
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (final IOException e) {
			log.info(e.getMessage());
		}
		return ImagesPath + ".png";
	}
}

