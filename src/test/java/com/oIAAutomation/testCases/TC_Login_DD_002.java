package com.oIAAutomation.testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oIAAutomation.pageObjects.LoginPO;
import com.oIAAutomation.utilities.XLUtils;

public class TC_Login_DD_002 extends BaseClass{
	private static final Logger logger = LogManager.getLogger(TC_Login_DD_002.class);

	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPO lp = new LoginPO(driver);
		lp.setUsername(user);
        logger.debug("Username is "+ user);
        lp.setPswd(pwd);
        logger.debug("Password is "+pwd);
        lp.clickLoginButton();
        logger.debug("Login Button is clicked");
		Thread.sleep(3000);
		
        if(lp.isInvalidCredentialLoginErrorPresent()==true)
		{
			//click on ok button 
        	lp.clickOkButtonForLoginError();
        	logger.debug("Ok Button for invalid credential error is clicked");
			driver.switchTo().defaultContent();
			logger.debug("Login failed");
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			logger.debug("Login passed");
			Thread.sleep(2000);
			lp.clickCloseButtonOfRegulatoryNotice();
	        logger.debug("Close button is clicked of regulatory notice");
	        Thread.sleep(1000);
	        lp.clickUsernameDropdownLink();
	        logger.debug("Username Dropdown is clicked");
	        lp.clickLogoutButton();
	        logger.debug("Logout button is clicked");
			driver.switchTo().defaultContent();
		}
        
        
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		///home/rao/eclipse-workspace/oIAAutomation/src/test/java/com/oIAAutomation/testData/loginDD.xlsx
		String path=System.getProperty("user.dir")+"/src/test/java/com/oIAAutomation/testData/loginDD.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
}
