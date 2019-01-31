package com.iLABCareers.browsercapabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.iLABCareers.utilities.BaseMethods;

public class CapabilityManager {
	
	private String environment;
	private String remoteCapabilityName;
	private DesiredCapabilities capabilities = null;
	private static CapabilityManager INSTANCE = null;
	
	private  CapabilityManager()
	{
		
	}
	
	public DesiredCapabilities  getDesiredBrowseCapabilities()
	{
		
		if (capabilities != null) {
			return capabilities;
		}
		
		if(isMobileEnvironment()){
			MobileDeviceCapabilities.getInstance().setcapabilityName(remoteCapabilityName);
			capabilities = MobileDeviceCapabilities.getInstance().getDesiredBrowseCapabilities();
		}
		else if(isDesktopEnvironment())
		{
			RemoteDriverCapabilities.getInstance().setcapabilityName(remoteCapabilityName);
			capabilities = RemoteDriverCapabilities.getInstance().getDesiredBrowseCapabilities();
		}
		
		String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
		if(browserstackLocal==null || browserstackLocal.trim().isEmpty())
		{
			browserstackLocal= BaseMethods.getConfig().getProperty("BROWSERSTACK_LOCAL","true");
		}
		capabilities.setCapability("browserstack.local", browserstackLocal);
		
		String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
		if(browserstackLocalIdentifier==null||browserstackLocalIdentifier.trim().isEmpty())
		{
			browserstackLocalIdentifier = BaseMethods.getConfig().getProperty("BROWSERSTACK_LOCAL_IDENTIFIER");
		}
		
		if(browserstackLocalIdentifier!=null)
		{
			capabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
		}
		
		return capabilities;
	}

	private boolean isMobileEnvironment() {
		return "iOS".equalsIgnoreCase(environment)
				|| "Android".equalsIgnoreCase(environment)
				|| "mobile".equalsIgnoreCase(environment)
				|| "tablet".equalsIgnoreCase(environment);
	}
	
	private boolean isDesktopEnvironment() {
		return "PC".equalsIgnoreCase(environment)
				|| "Mac".equalsIgnoreCase(environment)
				|| "desktop".equalsIgnoreCase(environment);
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setRemoteCapabilityName(String remoteCapabilityName) {
		this.remoteCapabilityName = remoteCapabilityName;
	}
	
	public static CapabilityManager getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new CapabilityManager();
		}
		return INSTANCE;
	}
}
