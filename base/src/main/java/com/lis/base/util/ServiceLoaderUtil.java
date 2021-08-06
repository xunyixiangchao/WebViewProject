package com.lis.base.util;

import java.util.ServiceLoader;

/**
 * Created by lis on 2021/7/30.
 */
public class ServiceLoaderUtil {

    public static <T> T getService(Class<T> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}