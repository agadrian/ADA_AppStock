

// Es un objetc para crear una unica vez el entity manager factory, para usarlo el mismo en toda la app
// Esta en el github de diego: https://github.com/Lainezz/ADAT_2425/blob/main/src/main/kotlin/com/es/tema2/orm/hibernateInstituto/utils/HibernateUtils.kt

package appstock.utils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {
    private lateinit var emf: EntityManagerFactory

    private fun getEntityManagerFactory(namePersistenceUnit: String = "unidadMySQL"): EntityManagerFactory {
        return if(this::emf.isInitialized && emf.isOpen) {
            emf;
        } else {
            emf = Persistence.createEntityManagerFactory(namePersistenceUnit)
            emf
        }
    }

    fun getEntityManager(namePersistenceUnit: String = "unidadMySQL"): EntityManager {
        return getEntityManagerFactory(namePersistenceUnit).createEntityManager()
    }


    // Método para cerrar todos los EntityManagerFactory
    fun shutdown() {
        if (emf.isOpen) {
            emf.close()
        }
    }

    // Método para cerrar un EntityManager específico
    fun closeEntityManager(em: EntityManager?) {
        try {
            if (em != null && em.isOpen) {
                em.close()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }



}