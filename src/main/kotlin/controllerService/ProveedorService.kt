package appstock.controllerService

import appstock.model.Proveedor
import appstock.repository.ProveedorRepository

class ProveedorService(private val proveedorRepository : ProveedorRepository) {


    fun insertPrueba(){
        val lista = listOf<Proveedor>(
            Proveedor("Alimentacion Pedro", "C/ El bosque"),
            Proveedor("Ferreteria SL", "C/ Malamanera 34"),
            Proveedor("Ferrocarriles", "Avda. Gran Vía"),
            Proveedor("InformaticTech S.A", "C/ TermoJul")
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