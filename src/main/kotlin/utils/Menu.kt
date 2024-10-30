package appstock.utils

import appstock.controllerService.ProductService
import appstock.controllerService.ProveedorService
import appstock.controllerService.UsuarioService
import appstock.model.Proveedor

/**
 * Clase que representa el menú principal de la aplicación.
 */
class Menu(
    private val userService: UsuarioService,
    private val productService: ProductService,
    private val proveedorService: ProveedorService
    ) {

    /**
     * Función interna para pedir una opción dentro de un rango específico.
     * @param min El valor mínimo permitido.
     * @param max El valor máximo permitido.
     * @return La opción seleccionada por el usuario.
     */
    private fun pedirOpcion(min: Int, max: Int): Int {
        var opcion: Int

        do {
            print("Introduce opción -> ")
            opcion = readln().toIntOrNull() ?: -1

            if (opcion == -1) {
                println("Opción no válida. ")
            } else if (opcion !in min..max) {
                println("Opción fuera de rango. ")
            }
        } while (opcion !in min..max)
        return opcion
    }


    /**
     * Función que muestra y gestiona el menú principal.
     */
    fun menuPrincipal() {
        var opc: Int
        do {
            imprimirMenuPrincipal()
            opc = pedirOpcion(1, 3)

            when (opc) {
                1 -> userService.login()
                2 -> menuStock()
                3 -> println("Saliendo del programa...")
            }
        } while (opc != 3)
    }

    private fun menuStock(){
        var opc: Int
        do {
            imprimirMenuStock()
            opc = pedirOpcion(0, 11)

            when (opc) {
                0 -> proveedorService.insertPrueba()
                1 -> productService.crearProd()
                2 -> productService.eliminarProd()
                3 -> productService.modificarNombreProducto()
                4 -> productService.modificarStockProducto()
                5 -> productService.obtenerProducto()
                6 -> productService.obtenerProductosConStock()
                7 -> productService.obtenerProductosSinStock()
                8 -> productService.obtenerProveedorDeProducto()
                9 -> proveedorService.getAllProveedores()
                10 -> productService.getAllProducts()
                11 -> println("Saliendo del programa...")
            }
        } while (opc != 11)
    }


    /**
     * Función interna para imprimir las opciones del menú principal.
     */
    private fun imprimirMenuPrincipal() {
        println("\nMenú Principal")
        println("1.- Login")
        println("2.- App Stock Control")
        println("3.- Salir")
    }


    private fun imprimirMenuStock(){
        println("\nMenú App Stock Control")
        println("0 -*-* Dev- Insertar proveedores prueba")
        println("1.- Alta producto")
        println("2.- Baja producto")
        println("3.- Modificar nombre producto")
        println("4.- Modificar stock producto")
        println("5.- Obtener producto")
        println("6.- Obtener productos con stock")
        println("7.- Obtener productos sin stock")
        println("8.- Obtener proveedor del producto")
        println("9.- Obtener todos los proveedores")
        println("10.- Obtener todos los productos")
        println("11.- Salir")
    }


}


