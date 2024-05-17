package org.example.util.function;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.function.Predicate;

@FunctionalInterface
public interface LogPredicate<T> extends Predicate<T> {
    Logger logger = LoggerFactory.getLogger(LogPredicate.class);

    default boolean testAndLog(T t, String val){
        boolean result = this.test(t);
        if(!result){
            logger.warn(() -> t + " don't match '" + val + "'");
        }
        return result;
    }
}
