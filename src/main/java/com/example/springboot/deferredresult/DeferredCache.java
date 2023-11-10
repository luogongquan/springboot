package com.example.springboot.deferredresult;
/**
 * @description: TODO
 * @author: lgq
 * @date: 2023/11/10 15:20
 */

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DeferredCache
 * @author: luogongquan
 * @since: 2023/11/10 15:20
 */
public class DeferredCache {
    public static final Cache<String, DeferredResult> DEFERRED_CACHE = Caffeine.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build();
}
