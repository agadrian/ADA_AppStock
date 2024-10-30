package appstock.model

import jakarta.persistence.*

@Entity
@Table(name = "proveedores")
data class Proveedor(

    @Column(nullable = false, length = 50)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,

    @Column(nullable = false)
    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL], orphanRemoval = true)
    val productos: List<Producto> = mutableListOf<Producto>(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){
    override fun toString(): String {
        return "Nombre: $nombre, Direccion: $direccion, Id: $id"
    }
}
