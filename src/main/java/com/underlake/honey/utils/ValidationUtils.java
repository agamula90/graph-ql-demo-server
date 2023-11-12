package com.underlake.honey.utils;

import java.util.Collection;

public class ValidationUtils {
    public static void requireTextNotEmpty(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Empty string supplied");
    }

    public static void requireCollectionNotEmpty(Collection<?> collection) {
        if (collection.isEmpty()) throw new IllegalArgumentException("Empty collection supplied");
    }
}
