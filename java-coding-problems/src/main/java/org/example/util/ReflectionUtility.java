package org.example.util;

import java.lang.reflect.Field;
import java.util.function.Function;

public class ReflectionUtility {
    public static <T, U> Function<T, U> getFieldByName(Class<T> cls, String field){
        return object -> {
            try {
                Field f = cls.getDeclaredField(field);
                f.setAccessible(true);
                return (U)f.get(object);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
