package appstock.repository

import appstock.model.Producto
import appstock.utils.HibernateUtils

/**
 * Aqui se hace el crud, pero se le apsa al de insertgar t odo ya hecho, solo para insertar
 */
class ProductoRepository {

    fun altaProducto(producto: Producto) {
        val em = HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
        }catch (e:Exception){
            em.transaction.rollback()
            throw e
        }finally {
            em.close()
        }
    }


    fun bajaProducto(producto: Producto) {
        val em = HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            val prod = em.find(Producto::class.java, producto.id)

            if (prod != null) {
                em.remove(prod)
            }else{
                throw Exception("Producto no encontrado")
            }

            em.transaction.commit()
        }catch (e:Exception){
            em.transaction.rollback()
            throw e
        }finally {
            em.close()
        }
    }

    fun getAllProductos(): List<Producto> {
        val em = HibernateUtils.getEntityManager()

        try {
            // Query usando JPQL
            val query = em.createQuery("SELECT u FROM Producto u ORDER BY u.fechaAlta ASC", Producto::class.java)

            // Ejecutamos la query. Devuelve la lista de productos
            val res = query.resultList

            // Cerramos el entityManager
            HibernateUtils.closeEntityManager(em)

            return res

        }catch (e: Exception) {
            em.transaction.rollback()
            throw e
        }finally {
            em.close()
        }
    }

    fun modificarNombreProducto(producto: Producto, nombreNuevo: String) {
        val em = HibernateUtils.getEntityManager()

        try {
            em.transaction.begin()

            val prod = em.find(Producto::class.java, producto.id)

            if (prod != null) {
                val newProduct = producto.copy(nombre = nombreNuevo)
                em.merge(newProduct)
            } else{
                throw Exception("Producto no encontrado")
            }

            em.transaction.commit()
        }catch (e:Exception){
            em.transaction.rollback()
            throw e
        }finally {
            em.close()
        }
    }

    fun modificarStock(producto: Producto, nuevoStock: Int) {
        val em = HibernateUtils.getEntityManager()

        try {
            em.transaction.begin()

            val prod = em.find(Producto::class.java, producto.id)
            if (prod != null) {
                val newProduct = prod.copy(stock = nuevoStock)
                em.merge(newProduct)
            }else {
                throw Exception("Producto no encontrado")
            }

            em.transaction.commit()
        }catch (e:Exception){
            em.transaction.rollback()
            throw e
        }finally {
            em.close()
        }
    }

}