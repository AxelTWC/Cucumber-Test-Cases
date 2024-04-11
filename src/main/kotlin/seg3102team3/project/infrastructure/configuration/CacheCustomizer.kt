package seg3102team3.project.infrastructure.configuration

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.stereotype.Component

@Component
class CacheCustomizer: CacheManagerCustomizer<ConcurrentMapCacheManager> {
    override fun customize(cacheManager: ConcurrentMapCacheManager?) {
        cacheManager?.setCacheNames(listOf("users"))
    }
}
