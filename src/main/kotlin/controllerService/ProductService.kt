package appstock.controllerService

import appstock.model.Producto
import appstock.model.Proveedor
import appstock.repository.ProductoRepository

class ProductService(private val proveedorService: ProveedorService, private val productoRepository: ProductoRepository) {

    fun crearProd(){
        try {
            println("\nIntroduce los datos del producto a ingresar: ")

            print("Categoria: ")
            val cat = readln()

            print("Nombre: ")
            val nom = readln()

            print("Descripcion: ")
            val desc = readln()

            print("Precio sin Iva: ")
            val preSinIva = readln().toFloat()

//            print("Cantidad stock: ")
//            val stock = readln().toInt()

            val prov = eleccionProveedor()

            val producto = Producto(cat, nom, desc, preSinIva, prov)

            productoRepository.altaProducto(producto)

            println("\nProducto ingresado correctamente")
        }catch (e:Exception){
            println("** Error al crear el producto. Detalles: ${e.message}")
        }
    }

    private fun eleccionProveedor(): Proveedor {
        val listaProveedores = proveedorService.getAllProveedores()

        print("\nElige un proveedor de la lista: ")
        val num = readln().toInt()
        return listaProveedores[num-1]

//        else{
//            val prov = crearNewProveedor()
//            return prov
//        }

    }

//    private fun crearNewProveedor(): Proveedor {
//        print("Introduce nombre proveedor: ")
//        val nombre = readln()
//
//        print("Introduce direccion proveedor: ")
//        val direccion = readln()
//
//        return proveedorService.insertarProveedor(nombre,direccion)
//    }

    fun eliminarProd() {
        val prod = eleccionProducto()
        if (prod != null) {
            try {
                productoRepository.bajaProducto(prod)
                println("\nProducto eliminado correctamente")
            }catch (e:Exception) {
                println("** ERROR - ${e.message}")
            }
        }
    }

    private fun eleccionProducto(): Producto? {
        val listaProductos = productoRepository.getAllProductos()

        println("\nElige un producto: ")
        listaProductos.forEachIndexed { index, producto ->
            println("${index+1} - $producto")
        }

        try {
            val p = readln().toInt()
            if (p in 1..listaProductos.size){
                return listaProductos[p-1]
            }
            throw Exception("\nValor introducido no válido")
        }catch (e:Exception){
            println("** ERROR ** - ${e.message}")
        }
        return null
    }


    fun modificarNombreProducto(){
        val producto = eleccionProducto()

        if (producto != null) {
            print("\nIntroduce nombre nuevo: ")
            val nombreNuevo = readln()
            try {
                productoRepository.modificarProducto(producto, nombreNuevo)
                println("\nNombre del producto modificado con éxito.")
            }catch (e:Exception){
                println("** ERROR - ${e.message}")
            }
        }
    }

    fun modificarStockProducto(){
        val producto = eleccionProducto()
        if (producto != null) {
            print("\nIntroduce stock nuevo (Entero >= 0): ")

            try {
                val stockNuevo = readln()
                if (stockNuevo.toIntOrNull() != null && stockNuevo.toInt() >= 0) {
                    productoRepository.modificarStock(producto, stockNuevo.toInt())
                    println("\nStock actualizado correctamente.")
                }else{
                    throw Exception("Hay que introducir un numero entero válido ( >= 0).")
                }
            }catch (e:Exception){
                println("** ERROR - ${e.message}")
            }
        }
    }

    fun obtenerProducto(){
        val producto = eleccionProducto()

        if (producto != null){
            println("\nProducto obtenido -> $producto")
        }
    }

    fun obtenerProductosConStock(){
        val productos = productoRepository.getAllProductos().filter { it.stock > 0 }
        if (productos.isNotEmpty()){
            println("\nProductos con stock: ")
            productos.forEach {
                println(it)
            }
        }else{
            println("\nNo hay productos con stock")
        }
    }

    fun obtenerProductosSinStock(){
        val productos = productoRepository.getAllProductos().filter { it.stock == 0 }

        if (productos.isNotEmpty()){
            println("\nProductos sin stock: ")
            productos.forEach {
                println(it)
            }
        }else{
            println("\nNo hay productos sin stock")
        }
    }

    fun obtenerProveedorDeProducto(){
        val producto = eleccionProducto()
        if (producto != null) {
            println("\nInfo del proveedor del producto '${producto.nombre}': ")
            println(producto.proveedor)
        }
    }

    fun getAllProducts(){
        val productos = productoRepository.getAllProductos()

        if (productos.isNotEmpty()){
            println("\nListado de todos los productos: ")
            productos.forEachIndexed {index, producto ->
                println("${index+1} - $producto")
            }
        }else{
            println("\nNo hay ningun producto")
        }

    }
}