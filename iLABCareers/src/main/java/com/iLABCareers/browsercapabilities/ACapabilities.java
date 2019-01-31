package com.iLABCareers.browsercapabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class ACapabilities implements ICapabilities {

	protected DesiredCapabilities desiredCapability;
	protected String capabilityName;
	
	@Override
	public DesiredCapabilities getDesiredBrowseCapabilities() {
		if (desiredCapability == null) {
			initialiseCapabilities();
		}
		return desiredCapability;
	}

	public void setcapabilityName(String capabilityName)
	{
		this.capabilityName = capabilityName;
		initialiseCapabilities();
	}
	
	protected void initialiseCapabilities()
	{
		BrowserCapabilities browserCapabilities = BrowserCapabilities
				.getBrowserCapability(capabilityName);
		if(browserCapabilities == null)
		{
			return;
		}
		
		desiredCapability = new DesiredCapabilities();
		desiredCapability.setCapability("browser", browserCapabilities.getBrowser());
		desiredCapability.setCapability("browser_version", browserCapabilities.getBrowserVersion());
		desiredCapability.setCapability("os", browserCapabilities.getOs());
		desiredCapability.setCapability("os_version", browserCapabilities.getOsVersion());
		desiredCapability.setCapability("resolution", browserCapabilities.getResolution());
		desiredCapability.setCapability("browserstack.local", browserCapabilities.getBrowserstackLocal());
	}
	
	public enum BrowserCapabilities {
		
		WIN_7_IE_10("WIN_7_IE_10", "IE", "10.0", "Windows", "7"),
		WIN_7_IE_11("WIN_7_IE_11", "IE", "11.0", "Windows", "7"),
		WIN_8_IE_10("WIN_8_IE_10", "IE", "10.0", "Windows", "8"),
		WIN_10_IE_11("WIN_10_IE_11", "IE", "11.0", "Windows", "10"),
		WIN_7_CHROME_66("WIN_7_CHROME_66", "Chrome", "66.0", "Windows", "7"),
		WIN_7_CHROME_67("WIN_7_CHROME_67", "Chrome", "67.0", "Windows", "7"),
		WIN_8_CHROME_66("WIN_8_CHROME_66", "Chrome", "66.0", "Windows", "8"),
		WIN_8_CHROME_67("WIN_7_CHROME_67", "Chrome", "67.0", "Windows", "8"),
		WIN_10_CHROME_66("WIN_10_CHROME_66", "Chrome", "66.0", "Windows", "10"),
		WIN_10_CHROME_67("WIN_10_CHROME_67", "Chrome", "67.0", "Windows", "10"),
		WIN_7_FIREFOX_61("WIN_7_FIREFOX_61", "Firefox", "61.0", "Windows", "7"),
		WIN_7_FIREFOX_62("WIN_7_FIREFOX_62", "Firefox", "62.0", "Windows", "7"),
		WIN_8_FIREFOX_61("WIN_8_FIREFOX_61", "Firefox", "61.0", "Windows", "8"),
		WIN_8_FIREFOX_62("WIN_8_FIREFOX_62", "Firefox", "62.0", "Windows", "8"),
		WIN_10_FIREFOX_61("WIN_10_FIREFOX_61", "Firefox", "61.0", "Windows", "10"),
		WIN_10_FIREFOX_62("WIN_10_FIREFOX_62", "Firefox", "62.0", "Windows", "10"),
		
		MAC_EL_CAPTAIN_CHROME_66("MAC_EL_CAPTAIN_CHROME_66", "Chrome", "66.0", "OS X", "El Capitan", "1024x768"),
		MAC_EL_CAPTAIN_CHROME_67("MAC_EL_CAPTAIN_CHROME_67", "Chrome", "67.0", "OS X", "El Capitan", "1024x768"),
		MAC_SIERRA_CHROME_66("MAC_SIERRA_CHROME_66", "Chrome", "66.0", "OS X", "Sierra", "1024x768"),
		MAC_SIERRA_CHROME_67("MAC_HIGH_SIERRA_CHROME_66", "Chrome", "67.0", "OS X", "Sierra", "1024x768"),
		MAC_HIGH_SIERRA_CHROME_66("WIN_10_CHROME_66", "Chrome", "66.0", "OS X", "High Sierra", "1024x768"),
		MAC_HIGH_SIERRA_CHROME_67("MAC_HIGH_SIERRA_CHROME_67", "Chrome", "67.0", "OS X", "High Sierra", "1024x768"),
		MAC_EL_CAPTAIN_SAFARI_9("MAC_EL_CAPTAIN_SAFARI_9", "Safari", "9.1", "OS X", "El Capitan", "1024x768"),
		MAC_SIERRA_SAFARI_10("MAC_SIERRA_SAFARI_10", "Safari", "10.1", "OS X", "Sierra", "1024x768"),
		MAC_HIGH_SIERRA_SAFARI_11("MAC_HIGH_SIERRA_SAFARI_11", "Safari", "11.1", "OS X", "High Sierra", "1024x768");

		
		private String capabilityName;
		private String browser;
		private String browserVersion;
		private String os;
		private String osVersion;
		private String resolution;
		private String browserstackLocal;
		
		private BrowserCapabilities(String capabilityName, String browser,
				String browserVersion, String os, String osVersion) {
			this.capabilityName = capabilityName;
			this.browser = browser;
			this.browserVersion = browserVersion;
			this.os = os;
			this.osVersion = osVersion;
			this.resolution = "1366x768";
			browserstackLocal = "true";
		}

		private BrowserCapabilities(String capabilityName, String browser,
				String browserVersion, String os, String osVersion,
				String resolution) {
			this.capabilityName = capabilityName;
			this.browser = browser;
			this.browserVersion = browserVersion;
			this.os = os;
			this.osVersion = osVersion;
			this.resolution = resolution;
			browserstackLocal = "true";
		}

		public String getCapabilityName() {
			return capabilityName;
		}

		public String getBrowser() {
			return browser;
		}

		public String getBrowserVersion() {
			return browserVersion;
		}

		public String getOs() {
			return os;
		}

		public String getOsVersion() {
			return osVersion;
		}

		public String getResolution() {
			return resolution;
		}

		public String getBrowserstackLocal() {
			return browserstackLocal;
		}
		
		public static BrowserCapabilities getBrowserCapability(
				String capabilityName) {
			if (capabilityName == null || capabilityName.trim().isEmpty()) {
				return null;
			}
			for (BrowserCapabilities capability : BrowserCapabilities.values()) {
				if (capability.getCapabilityName().equalsIgnoreCase(
						capabilityName)) {
					return capability;
				}
			}
			return null;
		}
	}
}
