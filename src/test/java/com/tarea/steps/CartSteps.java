package com.tarea.steps;

import com.tarea.page.CartPage;
import com.tarea.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartSteps {
    private WebDriver driver;
    private double price;
    private int quantity;

    public CartSteps(WebDriver driver){
        this.driver = driver;
    }

    /*public void setPrice() {
        WebElement element = driver.findElement( By.xpath("//p[@class='product-price']"));
        this.price = Double.parseDouble(element.getText().replace("S/", "").trim());
    }

    public void setQuantity() {
        WebElement element = driver.findElement( By.xpath("//span[@class='product-quantity']"));
        this.quantity = Integer.parseInt(element.getText().replace("Cantidad:", "").trim());
    }*/

    public void selectCategory() {
        //Coloca el puntero sobre la categoría para que se despliegue la subcategoría
        Actions actions = new Actions(driver);
        WebElement category = driver.findElement(By.xpath("(//a[@class='dropdown-item'])[6]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='dropdown-item'])[6]")));
        actions.moveToElement(category).perform();
    }

    public void selectSubCategory() {
        By subCategory = By.xpath("(//a[@class='dropdown-item dropdown-submenu'])[1]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(subCategory));
        this.driver.findElement(subCategory).click();
    }

    public void selectProduct() {
        By product = By.xpath("//a[@class='thumbnail product-thumbnail']");
        this.driver.findElement(product).click();
    }

    public void increaseQuantity(int quantity) {
        By button = By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']");
        for (int i=0; i<quantity-1; i++)
            this.driver.findElement(button).click();
    }

    public void addProduct() {
        By add = By.xpath("//button[@class='btn btn-primary add-to-cart']");
        this.driver.findElement(add).click();
    }

    public String getConfirmationMsgPopup() {
        By msg = By.id("myModalLabel");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(msg));
        return this.driver.findElement(msg).getText().trim();
    }

    public double getAmountPopup() {
        //Precio
        WebElement elementPrice = driver.findElement( By.xpath("//p[@class='product-price']"));
        this.price = Double.parseDouble(elementPrice.getText().replace("S/", "").trim());

        //Cantidad
        WebElement elementQuantity = driver.findElement( By.xpath("//span[@class='product-quantity']"));
        this.quantity = Integer.parseInt(elementQuantity.getText().replace("Cantidad:", "").trim());


        WebElement element = driver.findElement(By.xpath("(//span[@class='value'])[1]"));
        double amount = Double.parseDouble(element.getText().replace("S/", "").trim());
        return amount;
    }

    public double calculateAmount() {
        return this.quantity * this.price;
    }

    public void endPurchase(){
        this.driver.findElement(By.xpath("(//a[@class='btn btn-primary'])[2]")).click();
    }

    public String getTiteCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='h1']")));
        return driver.findElement(By.xpath("//h1[@class='h1']")).getText();
    }

    public double finalValidation() {
        //Precio
        WebElement elementPrice = driver.findElement( By.xpath("//span[@class='price']"));
        this.price = Double.parseDouble(elementPrice.getText().replace("S/", "").trim());

        //Cantidad
        WebElement elementQuantity = driver.findElement( By.xpath("//span[@class='label js-subtotal']"));
        this.quantity = Integer.parseInt(elementQuantity.getText().replaceAll("[^0-9]", "").trim());

        WebElement element = driver.findElement(By.xpath("(//span[@class='value'])[5]"));
        double amount = Double.parseDouble(element.getText().replace("S/", "").trim());
        return amount;
    }
}
