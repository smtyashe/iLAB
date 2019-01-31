package com.iLABCareers.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PageController {
	WebDriver driver;
	private static Logger log = Logger.getLogger(PageController.class.getName());

	public PageController() {
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	
	public void clickElement(By locator) {
		WebElement element = getElement(locator);
		element.click();
	}
	

	public String getElementsText(By locator) {
		WebElement element = getElement(locator);
		return element.getText();
	}

	public void enterRobot() throws AWTException {
		Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    robot.delay(200);
	}

}
