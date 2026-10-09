package com.tarea.steps;

import com.tarea.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;

    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }

    public void typeUser(String user) {
        WebElement userInputElement = driver.findElement(LoginPage.userInput);
        userInputElement.sendKeys(user);
    }

    public void typePassword(String password) {
        this.driver.findElement(LoginPage.userPassword).sendKeys(password);
    }

    public void loginClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton));
        this.driver.findElement(LoginPage.loginButton).click();
    }

    public String getErrorMsg() {
        return this.driver.findElement(LoginPage.errorMsg).getText();
    }

    public String getUsername() {
        return this.driver.findElement(LoginPage.userName).getText();
    }
}
