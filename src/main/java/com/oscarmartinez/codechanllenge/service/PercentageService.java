package com.oscarmartinez.codechanllenge.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service that handles obtaining and caching the percentage value from an external service.
 * The percentage is cached for 30 minutes, and if the external service fails, the last stored value is returned.
 */
@Service
public class PercentageService {
	
	@Autowired
	private PercentageClient percentageClient;
	
	@Autowired
    private CacheManager cacheManager;
	
	/**
     * Retrieves the percentage from the external service and caches it.
     * The percentage is cached for 30 minutes.
     * 
     * @return The percentage obtained from the external service.
     * @throws RuntimeException If an error occurs while fetching the percentage from the external service.
     */
    @Cacheable(value = "percentageCache", key = "'percentage'", unless = "#result == null", cacheManager = "cacheManager")
    public double getPercentage() {
        try {
            return percentageClient.getPercentage();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el porcentaje del servicio externo.");
        }
    }

    /**
     * Manually evicts the cached percentage.
     * This method removes the value stored in the cache under the 'percentage' key.
     * 
     * @see CacheEvict
     */
    @CacheEvict(value = "percentageCache", key = "'percentage'")
    public void evictCache() {

    }
    
    public Double getCachedPercentage() {
        Cache cache = cacheManager.getCache("percentageCache");
        if (cache != null) {
            return cache.get("percentage", Double.class);
        }
        return null;
    }

}
