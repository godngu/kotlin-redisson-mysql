package com.godngu.cart

import com.godngu.cart.audit.AuditingEntity
import java.io.Serializable
import javax.persistence.CascadeType.PERSIST
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "cart")
class Cart(

    @Id
    @Column(name = "member_no")
    val memberNo: Long,

) : Serializable, AuditingEntity() {

    @OneToMany(mappedBy = "cart", cascade = [PERSIST], fetch = EAGER)
    val items: MutableSet<CartItem> = hashSetOf()

    fun addItem(cartItem: CartItem) {
        cartItem.cart = this
        items.add(cartItem)
    }

    fun removeItem(dealProductNo: Long) {
        items.removeIf { it.dealProductNo == dealProductNo }
    }

    fun updateQuantity(dealProductNo: Long, quantity: Int) {
        items.firstOrNull {
            it.dealProductNo == dealProductNo
        }?.updateQuantity(quantity)
    }

    fun addQuantity(dealProductNo: Long, quantity: Int) {
        items.firstOrNull {
            it.dealProductNo == dealProductNo
        }?.addQuantity(quantity)
    }
}
