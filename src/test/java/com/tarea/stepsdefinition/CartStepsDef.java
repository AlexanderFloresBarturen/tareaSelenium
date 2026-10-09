package com.tarea.stepsdefinition;

import com.tarea.steps.CartSteps;
import com.tarea.steps.LoginSteps;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.tarea.core.DriverManager.getDriver;
import static com.tarea.core.DriverManager.screenShot;

public class CartStepsDef {
    private WebDriver driver;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y contraseña {string}")
    public void meLogueoConMiUsuarioYContraseña(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        screenShot();
        loginSteps.loginClick();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String category, String subcategory) {
        CartSteps cartSteps = new CartSteps(driver);
        cartSteps.selectCategory();
        screenShot();
        cartSteps.selectSubCategory();
        screenShot();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int quantity) {
        CartSteps cartSteps = new CartSteps(driver);
        cartSteps.selectProduct();
        cartSteps.increaseQuantity(quantity);
        screenShot();
        cartSteps.addProduct();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        CartSteps cartSteps = new CartSteps(driver);
        Assertions.assertTrue(cartSteps.getConfirmationMsgPopup().contains("Producto añadido correctamente a su carrito de compra"));
        screenShot();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        CartSteps cartSteps = new CartSteps(driver);
        Assertions.assertEquals(cartSteps.getAmountPopup(), cartSteps.calculateAmount());
        screenShot();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        CartSteps cartSteps = new CartSteps(driver);
        cartSteps.endPurchase();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        CartSteps cartSteps = new CartSteps(driver);
        Assertions.assertEquals(cartSteps.getTiteCartPage(), "CARRITO");
        screenShot();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        CartSteps cartSteps = new CartSteps(driver);
        Assertions.assertEquals(cartSteps.finalValidation(), cartSteps.calculateAmount());
    }
}
