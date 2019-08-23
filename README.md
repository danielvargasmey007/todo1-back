# Prueba de Concepto Todo1 - Anderson Vargas

 Para satisfacer las necesidades de esta prueba de concepto este proyecto se desarrollo en *JAVA* utilizando [Spring FrameWork](https://spring.io/), uno de los frameWorks más robustos de JAVA.
 Partiendo de uno de los requisitos plateados: "Desarrollo de interfaces básicas para el procesamiento de la información.", se desarrollaron las interfaces
 dentro del mismo proyecto haciendo uso de la biblioteca [Thymeleaf](https://www.thymeleaf.org/)

---

# Contenido

1) Acontinuación se presenta el respectivo diagrama de clases:
	
	![](image/entity-model.png)
	

2) Se toma como  base el método de promedio ponderado para el control de inventarios, vea más [aquí](https://actualicese.com/metodo-del-promedio-ponderado-para-el-control-de-inventarios/)


3) Acontinuación se muestra la tabla de lo que sería un registro Kardex por promedio ponderado:

	![](image/prom.png)
	

	Y lo que seria una tabla resultante de un software kardex:

	![](image/sw-kardex.png)

4) Para realizar adiciones a los registros de su kardex usted podra: Crear un nuevo producto, Ingresar más unidades a ese producto, Vender unidades de ese producto y además podra visualizar cada uno de los movimientos que tiene asociados.
	
	![](image/p1.png)
	
5) Una tabla del kardex en nuestro sistema tendra la siguiente estructura:
	
	![](image/p2.png)
	
	Cada uno de los items de esa estructura significan:

		**Fecha:** fecha en la que se realizo la operación

		**Tipo:** Buy(si se estan ingresando productos al sistema), Sale(si se esta realizando una venta de productos), Existencia(primer registro con los datos iniciales de un producto).

		**Stock Operación**: Corresponse a la cantidad que se esta manipulando en esa operación(ej: si esta vendiendo 4 articulos, el **Stock Operación** será igual a 4).

		**Precio Unitario Operacion:** Corresponse al precio unitario del articulo que se esta manipulando en esa operación(ej: si esta vendiendo un articulo cuyo precio unitario es 10, el **Precio Unitario Operacion:** será igual a 10).

		**Precio Total Operación:** Corresponde al producto de **Precio Unitario Operacion** * **Stock Operación**.

		**Stock Inventario**: Corresponse al numero de unidades total que se tiene registrada de ese articulo.

		**Precio Unitario Inventario:** Corresponse precio unitario del articulo como tal, se calcula solo cuando hay un ingreso de productos. 
			
			Formula:
			
				![](image/calc.png)

		**Precio Total Inevntario:** Corresponde al producto de **Precio Unitario Inevntario** * **Stock Inventario**

		**Número de Factura:** Corresponse al numero de la factura asociada a ese movimiento.