package com.iLABCareers.browsercapabilities;

import org.openqa.selenium.remote.DesiredCapabilities;


public class MobileDeviceCapabilities extends ACapabilities implements ICapabilities{

	private static ICapabilities INSTANCE;
	
	private MobileDeviceCapabilities()
	{
		
	}
	
	public static ICapabilities getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new MobileDeviceCapabilities();
		}
		return INSTANCE;
	}
	
	protected void initialiseCapabilities()
	{
		DeviceCapabilities deviceCapability = DeviceCapabilities.getDeviceCapability(capabilityName);
		if(deviceCapability==null)
		{
			return;
		}
		desiredCapability = new DesiredCapabilities();
		desiredCapability.setCapability("os_version", deviceCapability.getOsVersion());
		desiredCapability.setCapability("device", deviceCapability.getDevice());
		desiredCapability.setCapability("real_mobile", deviceCapability.getRealMobile());
		desiredCapability.setCapability("browserstack.local", deviceCapability.getBrowserstackLocal());
		desiredCapability.setCapability("browserName", deviceCapability.getBrowserName());
		
	}
	
	protected enum DeviceCapabilities {
		SAMSUNG_GALAXY_TAB_4_CHROME("SAMSUNG_GALAXY_TAB_4_CHROME","4.4","Samsung Galaxy Tab 4","Chrome"),
		SAMSUNG_GALAXY_NOTE_4_CHROME("SAMSUNG_GALAXY_NOTE_4_CHROME","6.0","Samsung Galaxy Note 4","Chrome"),
		
		SAMSUNG_GALAXY_S8_CHROME("SAMSUNG_GALAXY_S8_CHROME","7.0","Samsung Galaxy S8","Chrome"),
		SAMSUNG_GALAXY_S7_CHROME("SAMSUNG_GALAXY_S7_CHROME","6.0","Samsung Galaxy S7","Chrome"),
		IPHONE_6("IPHONE_6","11.2","iPhone 6","iPhone"),
		IPHONE_8("IPHONE_8","11.0","iPhone 8","Safari"),
		IPAD_5_TH_SAFARI("IPAD_5_TH_SAFARI","11.0","iPad 5th","Safari"),
		IPAD_PRO_SAFARI("IPAD_PRO_SAFARI","11.2","iPad Pro","Safari");
		private String capabilityName;
		private String osVersion;
		private String device;
		private String browserName;
		private String realMobile;
		private String browserstackLocal;
		
		private DeviceCapabilities(String capabilityName,String osVersion, String device,
				String browserName) {
			this.capabilityName = capabilityName;
			this.osVersion = osVersion;
			this.device = device;
			this.browserName = browserName;
			this.realMobile = "true";
			this.browserstackLocal = "true";
		}
		
		private DeviceCapabilities(String capabilityName,String osVersion, String device,
				String browserName, String realMobile) {
			this.capabilityName = capabilityName;
			this.osVersion = osVersion;
			this.device = device;
			this.browserName = browserName;
			this.realMobile = realMobile;
			this.browserstackLocal = "true";
		}
		
		private DeviceCapabilities(String capabilityName,String osVersion, String device,
				String browserName, String realMobile, String browserstackLocal) {
			this.capabilityName = capabilityName;
			this.osVersion = osVersion;
			this.device = device;
			this.browserName = browserName;
			this.realMobile = realMobile;
			this.browserstackLocal = browserstackLocal;
		}
		
		public String getCapabilityName() {
			return capabilityName;
		}

		public String getOsVersion() {
			return osVersion;
		}
		public String getDevice() {
			return device;
		}
		public String getBrowserName() {
			return browserName;
		}
		public String getRealMobile() {
			return realMobile;
		}
		public String getBrowserstackLocal() {
			return browserstackLocal;
		}
		
		public static DeviceCapabilities getDeviceCapability(
				String capabilityName) {
			if (capabilityName == null || capabilityName.trim().isEmpty()) {
				return null;
			}
			for (DeviceCapabilities capability : DeviceCapabilities.values()) {
				if (capability.getCapabilityName().equalsIgnoreCase(
						capabilityName)) {
					return capability;
				}
			}
			return null;
		}
	}
}

