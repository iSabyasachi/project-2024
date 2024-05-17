package org.example.functions;

import javax.swing.plaf.IconUIResource;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SupplierConsumerExample {
    /*
    * A static nested class may be instantiated without instantiating its outer class.
    * Inner classes can access both static and non-static members of the outer class.
    * A static class can access only the static members of the outer class.
    * */
    static class Counter{
        static int c;
        public static int count(){
            System.out.println(String.format("Incrementing c from %s to %s", c, c+1));
            return c++;
        }
    }

    public static int applySupplyAndConsumerOnCount(String type){
        Supplier<Integer> supplier = () -> Counter.count();
        Consumer<Integer> consumer = c -> {
            c = c + Counter.count();
            System.out.println("Consumer: "+c);
        };
        return switch (type){
            case "counter" -> Counter.c;
            case "supplier" -> supplier.get();
            case "consumer" -> {
                consumer.accept(Counter.c);
                yield Counter.c;
            }
            default -> 0;
        };
    }

}
