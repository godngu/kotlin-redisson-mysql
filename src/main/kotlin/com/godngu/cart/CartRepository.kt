package com.godngu.cart

import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository: JpaRepository<Cart, Long>
