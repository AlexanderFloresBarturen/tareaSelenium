package com.tarea.page;

import org.openqa.selenium.By;

public class LoginPage {
    public static By userInput = By.id("field-email");
    public static By userPassword = By.id("field-password");
    public static By loginButton = By.id("submit-login");
    public static By errorMsg = By.xpath("//li[@class='alert alert-danger']");
}
