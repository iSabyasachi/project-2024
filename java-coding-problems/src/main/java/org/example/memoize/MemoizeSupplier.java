package org.example.memoize;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
/*
function memoized-call (F is a function object parameter)
    if F has no attached array values then
        allocate an associative array called values;
        attach values to F;
    end if;

    if F.values[arguments] is empty then
        F.values[arguments] = F(arguments);
    end if;

    return F.values[arguments];
end function
* */
public class MemoizeSupplier{
    private static final Object UNDEFINED = new Object();

    public static <T> Supplier<T> supplier(final Supplier<T> supplier){
        AtomicReference cache = new AtomicReference(UNDEFINED);
        return () -> {
            Object value = cache.get();
            if(value == UNDEFINED){
                synchronized (cache){
                    value = supplier.get();
                    System.out.println("Caching..."+value);
                    cache.set(value);
                }
            }
            return (T)value;
        };
    }

}