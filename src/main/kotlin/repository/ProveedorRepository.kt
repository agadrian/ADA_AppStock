package appstock.repository

import appstock.model.Proveedor
import appstock.utils.HibernateUtils

class ProveedorRepository {

    fun cargarProveedoresPrueba(list: List<Proveedor>) {
        val em = HibernateUtils.getEntityManager()

        try {
            em.transaction.begin()
            list.forEach {
                em.persist(it)
            }
            em.transaction.commit()

        }catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
            //throw e
        }finally {
            em.close()
        }
    }


    fun getAllProveedores(): List<Proveedor>? {
        val em = HibernateUtils.getEntityManager()

        try {
            // Query usando JPQL
            val query = em.createQuery("SELECT u FROM Proveedor u", Proveedor::class.java)

            // Ejecutamos la query. Devuelve la lista de proveedores
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

    /**
     * TEST
     */
    fun insertProveedor(proveedor: Proveedor){
        val em = HibernateUtils.getEntityManager()

        try {
            em.transaction.begin()
            em.persist(proveedor)
            em.transaction.commit()
        }catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        }finally {
            em.close()
        }
    }
}