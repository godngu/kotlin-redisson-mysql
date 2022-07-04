package com.godngu.cart

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@Rollback(false)
@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CartRepositoryTest {

    @Autowired
    private lateinit var cartRepository: CartRepository

    private lateinit var savedCart: Cart

    private val dealProductNo = 111L

    @Test
    @Order(1)
    fun insert() {
        val cart = Cart(111000)
        cart.addItem(CartItem(dealProductNo, 11))
        cart.addItem(CartItem(222L, 22))
        savedCart = cartRepository.save(cart)
    }

    @Test
    @Order(2)
    fun select() {
        val foundCart = cartRepository.findById(savedCart.memberNo).orElseThrow()
        assertThat(foundCart.memberNo).isEqualTo(savedCart.memberNo)
    }

    @Test
    @Order(3)
    fun update() {
        cartRepository.findById(savedCart.memberNo)
            .orElseThrow()
            .updateQuantity(dealProductNo, 99)
    }

    @Test
    @Order(4)
    fun delete() {
        cartRepository.findById(savedCart.memberNo)
            .orElseThrow()
            .removeItem(dealProductNo)
    }
}
