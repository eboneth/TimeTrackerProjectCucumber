Feature: Realizar compras de articulos
  como un nuevo cliente
  quiero ingresar a la pagina www.abc.com
  para realizar la compra de un articulo

  Scenario: Agregar articulos al carrito de compra
    Given que un nuevo cliente accede hasta la web de compras
    When el agrega productos al carro de compras
    Then el ve los productos listados en el carro de compras

  Scenario Outline: Agregar articulos al carrito de compra
    Given que un nuevo cliente accede hasta la web de compras
    When el agrega productos al carro de compras
    Then el ve los productos listados en el carro de compras

    Examples:
      | nombreProducto | cantidadIngresar |
      | Tapabocas N95  | 100              |