package appstock

import appstock.controllerService.ProductService
import appstock.controllerService.ProveedorService
import appstock.controllerService.UsuarioService
import appstock.model.Producto
import appstock.model.Proveedor
import appstock.model.Usuario
import appstock.repository.ProductoRepository
import appstock.repository.ProveedorRepository
import appstock.utils.HibernateUtils
import appstock.utils.Menu
import repository.UsuarioRepository

/**
 * opc 1- login:
 * Pedimos user y pass en el man, se le pasa al controller service, el cual crea el objeto de tipo producto por ejemplo,  una vez con todo bien creado, se le pasa al daorespoitoy/usuariorepository, que solo se encarga de insertar en la base de datos, sin comprobar nada, ya que en el controller service se hace toda la logica de negocio
 *
 *
 */
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