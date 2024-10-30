package appstock.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*


@Entity
@Table(name = "productos")
data class Producto(

    @Column(nullable = false, length = 50)
    val categoria: String,

    @Column(nullable = false, length = 50)
    val nombre: String,

    @Column(nullable = false, length = 50)
    val descripcion: String,

    @Column(nullable = false)
    val precioSinIVA: Float,

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    val proveedor: Proveedor?,

    @Column(nullable = false)
    val stock: Int = 0,

    @Column(nullable = false)
    val fechaAlta: Date = Date.from(Instant.now()),

    @Column(nullable = false)
    val precioConIVA: Float = (precioSinIVA * 1.21).toFloat(),

    @Id
    val id: String = UUID.randomUUID().toString(),

    ){
    override fun toString(): String {
        return "Nombre: $nombre, Stock: $stock, Descripcion: $descripcion, PrecioSinIva: $precioSinIVA, PrecioConIva: $precioConIVA, Fecha alta: $fechaAlta, Proveedor($proveedor), Id: $id, "
    }

}
