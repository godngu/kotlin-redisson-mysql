package com.godngu.cart

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CartServiceTest {

    @Autowired
    private lateinit var cartService: CartService

    private val dealProductNo = 111L

    private val memberNo = 111001L

    @Test
    @Order(1)
    @DisplayName("save")
    fun save() {
        val cart = Cart(memberNo)
        cart.addItem(CartItem(dealProductNo, 11))
        cart.addItem(CartItem(222L, 22))

        val savedCart = cartService.save(cart)
        println("savedCart = $savedCart")
    }

    @Test
    @Order(2)
    @DisplayName("save")
    fun save2() {
        val cart = Cart(memberNo + 1)
        cart.addItem(CartItem(dealProductNo + 1, 11 + 1))
        cart.addItem(CartItem(222L + 1, 22 + 1))

        val savedCart = cartService.save(cart)
        println("savedCart2 = $savedCart")
    }

    @Test
    @Order(3)
    @DisplayName("findById")
    fun findById() {
        val cart = cartService.findById(memberNo)
        println("cart = $cart")
    }
}
