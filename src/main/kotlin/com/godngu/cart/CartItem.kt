package com.godngu.cart

import com.godngu.cart.audit.AuditingEntity
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "cart_item")
class CartItem(

    @Column(name = "dealProductNo")
    val dealProductNo: Long,

    @Column(name = "quantity")
    private var quantity: Int,

) : Serializable, AuditingEntity() {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "memberNo")
    lateinit var cart: Cart

    fun updateQuantity(quantity: Int) {
        this.quantity = quantity
    }

    fun addQuantity(quantity: Int) {
        this.quantity += quantity
    }
}
