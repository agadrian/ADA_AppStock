package repository

import appstock.model.Usuario
import appstock.utils.HibernateUtils


class UsuarioRepository {

    fun buscarUsuario(username: String): Usuario? {
        // Obtenemos el entityManager del object
        val em = HibernateUtils.getEntityManager()

        // Query usando JPQL
        val query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuario::class.java)

        // Establecemos el parametro que se la pasa a la funcion, a la consulta
        query.setParameter("nombreUsuario", username)

        // Ejecutamos la query. Devuelve o el user o null
        val res = query.resultList.firstOrNull()

        // Cerramos el entityManager
        HibernateUtils.closeEntityManager(em)

        return res
    }




}