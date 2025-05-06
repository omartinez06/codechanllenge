package com.oscarmartinez.codechanllenge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class PercentageServiceTest {

    private PercentageService percentageService;
    private PercentageClient percentageClientMock;
    private CacheManager cacheManagerMock;
    private Cache cacheMock;

    @BeforeEach
    void setUp() {
        percentageClientMock = mock(PercentageClient.class);
        cacheManagerMock = mock(CacheManager.class);
        cacheMock = mock(Cache.class);

        percentageService = new PercentageService();
        percentageService.percentageClient = percentageClientMock;
        percentageService.cacheManager = cacheManagerMock;
    }

    @Test
    void testGetPercentageReturnsValue() {
        when(percentageClientMock.getPercentage()).thenReturn(25.0);

        double result = percentageService.getPercentage();

        assertEquals(25.0, result);
        verify(percentageClientMock, times(1)).getPercentage();
    }

    @Test
    void testGetPercentageThrowsExceptionWhenClientFails() {
        when(percentageClientMock.getPercentage()).thenThrow(new RuntimeException("Service failed"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            percentageService.getPercentage();
        });

        assertEquals("Error al obtener el porcentaje del servicio externo.", exception.getMessage());
    }

    @Test
    void testGetCachedPercentageReturnsValue() {
        when(cacheManagerMock.getCache("percentageCache")).thenReturn(cacheMock);
        when(cacheMock.get("percentage", Double.class)).thenReturn(15.5);

        Double cachedValue = percentageService.getCachedPercentage();

        assertEquals(15.5, cachedValue);
    }

    @Test
    void testGetCachedPercentageReturnsNullWhenCacheIsEmpty() {
        when(cacheManagerMock.getCache("percentageCache")).thenReturn(null);

        Double cachedValue = percentageService.getCachedPercentage();

        assertNull(cachedValue);
    }
}
