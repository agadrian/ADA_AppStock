package appstock.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "proveedores")
data class Proveedor(

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,

    @Column(nullable = false)
    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL], orphanRemoval = true)
    val productos: List<Producto> = mutableListOf<Producto>(),

    @Id
    val id: String = UUID.randomUUID().toString(),
){
    override fun toString(): String {
        return "Nombre: $nombre, Direccion: $direccion, Id: $id"
    }
}
