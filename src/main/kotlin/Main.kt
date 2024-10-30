package appstock

import appstock.controllerService.ProductService
import appstock.controllerService.ProveedorService
import appstock.controllerService.UsuarioService
import appstock.repository.ProductoRepository
import appstock.repository.ProveedorRepository
import appstock.utils.Menu
import repository.UsuarioRepository


fun main() {
    val userRepository = UsuarioRepository()
    val userService = UsuarioService(userRepository)

    val proveedorRepository = ProveedorRepository()
    val proveedorService = ProveedorService(proveedorRepository)

    val productoRepository = ProductoRepository()
    val productoService = ProductService(proveedorService, productoRepository)


    val menu = Menu(userService, productoService, proveedorService)
    menu.menuPrincipal()


}