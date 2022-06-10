package com.oIAAutomation.testCases;

import org.testng.annotations.AfterMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import com.oIAAutomation.utilities.ReadConfig;

public class BaseClass {
	private static final Logger logger = LogManager.getLogger(BaseClass.class);

	public ReadConfig readconfig = new ReadConfig();
    protected String baseURL = readconfig.getApplicationURL();
    protected String username = readconfig.getUsername();
    protected String password = readconfig.getPassword();
    protected String currentBrowser;
    protected static WebDriver driver;


    @Parameters("browser")
    @BeforeClass
    public void setup(String br) throws InterruptedException
    {
    	currentBrowser = br;
        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
            driver = new ChromeDriver();
            System.out.println("Inside setup");
        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }
        else {
            logger.error("Wrong parameter of browser");
        }
        driver.manage().window().maximize();
        driver.get(baseURL);
        logger.debug("URL is opened");
        Thread.sleep(1000);
    }
    
	@AfterMethod
	@AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
