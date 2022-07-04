package com.godngu.cart

import org.redisson.api.RMapCache
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val cartRMapCache: RMapCache<Long, Cart>,
) {

    @Transactional
    fun save(cart: Cart): Cart {
        cartRMapCache[cart.memberNo] = cart
        return cart
    }

    fun findById(memberNo: Long): Cart? {
        return cartRMapCache[memberNo]
    }
}
