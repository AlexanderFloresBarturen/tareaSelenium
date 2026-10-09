Feature: Login

  @loginFallido
  Scenario: Login fallido
    Given que me encuentro en la página de login de My Store
    When inicio sesión con las credenciales usuario: "test.one@abc.com" y contraseña: "contraseñaunica"
    Then se muestra el mensaje "Error de autenticación."

  @loginExitoso
  Scenario: Login exitoso
    Given que me encuentro en la página de login de My Store
    When inicio sesión con las credenciales usuario: "test.one@abc.com" y contraseña: "passwordcontraseña"
    Then se muestra el nombre del usuario "Test One"