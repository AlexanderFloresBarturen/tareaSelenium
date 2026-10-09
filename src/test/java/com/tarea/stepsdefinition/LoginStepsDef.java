package com.tarea.stepsdefinition;

import com.tarea.steps.LoginSteps;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.tarea.core.DriverManager.getDriver;
import static com.tarea.core.DriverManager.screenShot;

public class LoginStepsDef {
    private WebDriver driver;

    @Given("que me encuentro en la página de login de My Store")
    public void queMeEncuentroEnLaPáginaDeLoginDeMyStore() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion");
        screenShot();
    }

    @When("inicio sesión con las credenciales usuario: {string} y contraseña: {string}")
    public void inicioSesiónConLasCredencialesUsuarioYContraseña(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        screenShot();
        loginSteps.loginClick();
    }

    @Then("se muestra el mensaje {string}")
    public void seMuestraElMensaje(String errorMsg) {
        LoginSteps loginSteps = new LoginSteps(driver);
        Assertions.assertEquals(loginSteps.getErrorMsg(), errorMsg);
        screenShot();
    }

    @Then("se muestra el nombre del usuario {string}")
    public void seMuestraElNombreDelUsuario(String username) {
        LoginSteps loginSteps = new LoginSteps(driver);
        Assertions.assertEquals(loginSteps.getUsername(), username);
        screenShot();
    }
}
