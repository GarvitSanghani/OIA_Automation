package com.oIAAutomation.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.oIAAutomation.pageObjects.LoginPO;

public class TC_LoginTest_001 extends BaseClass{
	
	private static final Logger logger = LogManager.getLogger(TC_LoginTest_001.class);
	
	@Test
	public void loginTest() throws InterruptedException
	{
		LoginPO lp = new LoginPO(driver);
		lp.setUsername(username);
        logger.debug("Username is entered");
        lp.setPswd(password);
        logger.debug("Password is entered");
        lp.clickLoginButton();
        logger.debug("Login Button is clicked");
        Thread.sleep(2000);
        lp.clickCloseButtonOfRegulatoryNotice();
        logger.debug("Close button is clicked of regulatory notice");
        if (lp.isVerifySuccessfullLogin()) {
            Assert.assertTrue(true);
            logger.info("Login test passed");
        } else {
            logger.error("Login test failed");
            //captureScreen(driver, "logintest");
            Assert.assertFalse(true);
        }
		
	}
	

}
