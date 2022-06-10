package com.oIAAutomation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPO {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
    public LoginPO(WebDriver rdriver) {
    	driver = rdriver;
        PageFactory.initElements(rdriver, this);
        wait = new WebDriverWait(driver, 20);
    }
	
    private By txtUsername = By.xpath("//*[@id='signInForm']//input[@id='email']");
    private By txtPswd = By.xpath("//*[@id='signInForm']//input[@id='password']");
    private By loginButton = By.xpath("//*[@id='signInForm']//button[@type='submit']");
    private By closeButtonOfRegulatoryNotice = By.xpath("(//button[text()=' Close '])[2]");
    private By verifySuccessfullLogin = By.xpath("//*[@id='dropdownMenuLink'][@class='mr-1 dropdown-toggle']");
    private By invalidCredentialLoginError = By.xpath("//*[text()='Incorrect username or password.']");
    private By okButtonForLoginError = By.xpath("//button[contains(@class, 'swal2-confirm swal2-styled')]");
    private By logoutButton = By.xpath("//*[@class='dropdown-menu show']//a[text()=' Logout ']");
    
    public void setUsername(String uname) {
        WebElement element;
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsername));
        element.clear();
        element.sendKeys(uname);
    }
    
    public void setPswd(String pswd) {
        WebElement element;
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPswd));
        element.clear();
        element.sendKeys(pswd);
    }
    
    public void clickLoginButton() {
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)))
                .click().perform();
    }
    
    public void clickCloseButtonOfRegulatoryNotice() {
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(closeButtonOfRegulatoryNotice));
        //js.executeScript("arguments[0].scrollIntoView();", element);
        //js.executeScript("arguments[0].click();", element);
       
        /*WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(closeButtonOfRegulatoryNotice));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);*/
        
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(closeButtonOfRegulatoryNotice)))
        .click().perform();
    }
    
    public boolean isVerifySuccessfullLogin() {
        try {
            driver.findElement(verifySuccessfullLogin);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    
    public boolean isInvalidCredentialLoginErrorPresent() {
        try {
            driver.findElement(invalidCredentialLoginError);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    
    public void clickOkButtonForLoginError() {
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(okButtonForLoginError)))
                .click().perform();
    }
    
    public void clickUsernameDropdownLink() {
    	WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(verifySuccessfullLogin));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
//        new Actions(driver).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(verifySuccessfullLogin)))
//                .doubleClick().perform();
    }
    
    public void clickLogoutButton() {
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(logoutButton)))
                .click().perform();
    }
    
}
