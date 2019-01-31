package com.iLABCareers.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.iLABCareers.browsercapabilities.CapabilityManager;

import com.iLABCareers.database.entities.BaseEntity;

public class BaseMethods {

	protected WebDriver driver;
	protected List<BaseEntity> currentTestData;
	
	protected static Properties prop = null;

	static PageController controller = new PageController();
	private Logger log = Logger.getLogger(BaseMethods.class.getName());

	private DesiredCapabilities BROWSER_CAPABILITIES = null;
	private String remoteUrl;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Gets the entity used when executing the testcase.
	 * 
	 * @return An instance of {@link BaseEntity}
	 */

	public List<BaseEntity> getCurrentEntity() {
		return currentTestData;
	}

	/**
	 * Gets the formatted string which contains the data used when executing the
	 * testcase.
	 * 
	 * @return String containing test data.
	 */
	public String getCurrentTestData() {
		if (currentTestData == null) {
			return "";
		}
		String testData = "{";
		for(BaseEntity entity: currentTestData)
		{
			testData = testData + entity.toString()+", ";
		}
		return testData+"}";
	}
	
	/**
	 * 
	 * @return
	 * @throws MalformedURLException 
	 */
	private WebDriver initiaseDriver() throws MalformedURLException {
		try {
			if (prop == null) {
				prop = new Properties();
				FileInputStream fls = new FileInputStream("main/resources/data.properties");
				prop.load(fls);
		}
		} catch (FileNotFoundException e) {
			log.severe("Exception encountered : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.severe("Exception encountered : " + e.getMessage());
			e.printStackTrace();
		}

		String env = prop.getProperty("env");
		if ("remote".equalsIgnoreCase(env)) 
		{
			if(BROWSER_CAPABILITIES == null)
			{
				String property = null;
				for(Object key: prop.keySet())
				{ 
					property = (String) key;
					if(System.getProperty(property)!=null)
					{
						prop.setProperty(property, System.getProperty(property));
					}
				}
				CapabilityManager.getInstance().setEnvironment(prop.getProperty("remote.driver.env").trim());
				CapabilityManager.getInstance().setRemoteCapabilityName(prop.getProperty("remote.driver.capability").trim());
				DesiredCapabilities caps = CapabilityManager.getInstance().getDesiredBrowseCapabilities();
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				BROWSER_CAPABILITIES = caps;
				remoteUrl = prop.getProperty("remote.driver.url");
				if(remoteUrl.startsWith("@"))
				{
					remoteUrl = "https://" + prop.getProperty("remote.driver.username") + ":" + prop.getProperty("remote.driver.automation.key") + remoteUrl;
				}
			}
			
			driver = new RemoteWebDriver(new URL(remoteUrl), BROWSER_CAPABILITIES);
			try
			{
				//wait 15 seconds for the browser to load
				TimeUnit.SECONDS.sleep(15);
			}
			catch (InterruptedException e)
			{
				log.info(e.getLocalizedMessage());
			}
			
			if("desktop".equals(prop.getProperty("remote.driver.env")))
			{
				driver.manage().window().maximize();
			}
		} 
		else
		{
			String browserName = prop.getProperty("browser");
			if ("chrome".equalsIgnoreCase(browserName)) {
				ChromeOptions options = new ChromeOptions();
				
				System.setProperty("webdriver.chrome.driver",
						"main/resources/chromedriver" + driverExtention());
				driver = new ChromeDriver(options);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				
				org.openqa.selenium.Dimension dm = new org.openqa.selenium.Dimension((int)screenSize.getWidth(),(int)screenSize.getHeight());
				driver.manage().window().setSize(dm);
			} 
			
			else if (browserName.equals("firefox")) {
				driver = new HtmlUnitDriver(false);
	
			} else if (browserName.equals("ie")) {
				System.setProperty("webdriver.ie.driver", "drivers/ie" + driverExtention());
				driver = new InternetExplorerDriver();
	
			}
		}
		return driver;
	}

	private String driverExtention() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			return ".exe";
		}
		return "";
	}

	/**
	 * 
	 */
	public void initialise() {
		// Initialize browser from the Base class
		try {
			currentTestData = new ArrayList<BaseEntity>();
			driver = initiaseDriver();
			driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
			BaseUtil.getController().setDriver(driver);
		} catch (MalformedURLException e) {
			log.info(e.getMessage());
		}
		// fetch url from the property file located in resources folder
		driver.get(prop.getProperty("url"));
		String env = prop.getProperty("env");
		if ("remote".equalsIgnoreCase(env))
		{
			try
			{
				//wait 15 seconds for the browser to load
				TimeUnit.SECONDS.sleep(15);
			}
			catch (InterruptedException e)
			{
				log.info(e.getLocalizedMessage());
			}
		}
	}
	
	public static Properties getConfig() {
		try {
			if (prop == null) {
				prop = new Properties();
				FileInputStream fls = new FileInputStream(
						"main/resources/data.properties");
				prop.load(fls);
			}
		} catch (Exception e) {
		}
		return prop;
	}
}