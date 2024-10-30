package appstock.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(

    @Id
    val nombreUsuario: String,

    @Column(nullable = false, length = 20)
    val password: String
) {
    override fun toString(): String {
        return "Usuario: $nombreUsuario, Contraseña: $password"
    }
}