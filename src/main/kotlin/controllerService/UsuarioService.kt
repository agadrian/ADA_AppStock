package appstock.controllerService


import repository.UsuarioRepository

class UsuarioService(private val userRepository: UsuarioRepository) {

    fun login() {
        val datos = pedirDatos()
        val usuario = userRepository.buscarUsuario(datos[0])
        if (usuario != null) {
            if (checkPassword(usuario.password, datos[1])){
                println("Logeado correctamente. Bienvenid@ ${usuario.nombreUsuario}")
            }else{
                println("Contraseña incorrecta.")
            }
        }else{
            println("Usuario incorrecto.")
        }
    }


    private fun checkPassword(originalPass: String, passToCheck: String): Boolean {
        return (passToCheck == originalPass)
    }



    private fun pedirDatos(): List<String> {
        print("Introduce usuario: ")
        val user = readln()
        print("Introduce password: ")
        val password = readln()

        return listOf(user, password)
    }
}