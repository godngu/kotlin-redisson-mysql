package com.godngu.cart

import org.redisson.api.MapOptions
import org.redisson.api.RMapCache
import org.redisson.api.RedissonClient
import org.redisson.api.map.MapWriter
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class RedisConfig(
    private val cartRepository: CartRepository,
    private val redissonClient: RedissonClient,
) {

    @Bean
    fun cartRMapCache(): RMapCache<Long, Cart> {
        return redissonClient.getMapCache(
            "Cart1",
            MapOptions.defaults<Long, Cart>()
                .writer(getCartMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND)
        )
    }

    private fun getCartMapWriter(): MapWriter<Long, Cart> {
        return object : MapWriter<Long, Cart> {

            override fun write(map: MutableMap<Long, Cart>) {
                println(">>> map = $map")
                map.forEach { (k, v) ->
                    cartRepository.save(v)
                }
            }

            override fun delete(keys: MutableCollection<Long>) {
                println(">>> keys = $keys")
            }

        }
    }
}
