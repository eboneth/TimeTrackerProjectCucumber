Feature: Como usuario debo poder registrar Horas en TimeTracker
  @smoke
  Scenario Outline: Registrar Horas en TimeTracker
    Given abrir el navegador ingresar a la url <baseurl>
    When cuando le de clic a el link acceder debera ingresar el usuario <user> y el password <pass>
    Then debera registrar su reporte de horas diarias
    And Finalizar la sesion

    Examples:
      | baseurl                                               | user    | pass       |
      | http://greenway.greensqa.com/trackerservice/login.php | rbonett | 1081789560 |