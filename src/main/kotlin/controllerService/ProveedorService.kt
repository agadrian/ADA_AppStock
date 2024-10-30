package appstock.controllerService

import appstock.model.Producto
import appstock.model.Proveedor
import appstock.repository.ProveedorRepository

class ProveedorService(private val proveedorRepository : ProveedorRepository) {


    fun insertPrueba(){
        val lista = listOf<Proveedor>(
            Proveedor("EmpresaSL", "Calle dos"),
            Proveedor("Ferreteria SL", "Calle las madres"),
            Proveedor("Panaderia juani", "Calle isabel")
        )

        proveedorRepository.cargarProveedoresPrueba(lista)
    }



    fun getAllProveedores(): List<Proveedor> {
        val proveedores = proveedorRepository.getAllProveedores()
        if (!proveedores.isNullOrEmpty()){
            println("\nListado de proveedores:")
            proveedores.forEachIndexed { index, proveedor ->
                println("${index+1} - $proveedor")
            }
            return proveedores
        }
        return listOf()
    }


    /**
     * TEST
     */
    fun insertarProveedor(nombre: String, direccion: String): Proveedor {
        val prov = Proveedor(nombre, direccion)
        proveedorRepository.insertProveedor(prov)
        return prov
    }
}