package com.oIAAutomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties pro;

    public ReadConfig() {

        File src = new File("/home/rao/eclipse-workspace/oIAAutomation/Configuration/config.properties"); // TODO
        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getApplicationURL() {
        String url = pro.getProperty("baseURL");
        return url;
    }

    public String getUsername() {
        String userName = pro.getProperty("username");
        return userName;
    }

    public String getPassword() {
        String password = pro.getProperty("password");
        return password;
    }

    public String getChromePath() {
        String chromePath = pro.getProperty("chrome.driver.path");
        return chromePath;
    }

    public String getFirefoxPath() {
        String firefoxPath = pro.getProperty("firefox.driver.path");
        return firefoxPath;
    }

    public String getChromiumPath() {
        String firefoxPath = pro.getProperty("chromium.driver.path");
        return firefoxPath;
    }

    public String getExtentReportConfigPath() {
        String extentReportConfigPath = pro.getProperty("extent.report.config.file.path");
        return extentReportConfigPath;
    }

    public String getAdministratorAdmin() {
        String administratorAdmin = pro.getProperty("administrator.Admin");
        return administratorAdmin;
    }

    public String getAdministratorPassword() {
        String administratorPassword = pro.getProperty("administrator.Password");
        return administratorPassword;
    }

    public String getTestDataFolderPath() {
        String testDataFolderPath = pro.getProperty("test.Data.folder");
        return testDataFolderPath;
    }

    public String getCoustomUsername() {
        String testDataFolderPath = pro.getProperty("custom.username");
        return testDataFolderPath;
    }


}
