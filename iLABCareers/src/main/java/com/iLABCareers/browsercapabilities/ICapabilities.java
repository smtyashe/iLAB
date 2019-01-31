package com.iLABCareers.browsercapabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface ICapabilities {
	public DesiredCapabilities getDesiredBrowseCapabilities();
	public void setcapabilityName(String capabilityName);
}
