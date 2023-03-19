package br.edu.ifpb.upcensus.infrastructure.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectionUtils {
	
	private CollectionUtils() {}
	
    public static Boolean isEmpty(Object obj) {
        if (ObjectUtils.isNull(obj)) return true;

        if (obj instanceof Collection)
            return ((Collection<?> )obj).isEmpty();
        if (obj instanceof Object[])
            return ((Object[]) obj).length == 0;
        if (obj instanceof Map)
            return ((Map<?, ?>) obj).isEmpty();
        if (obj instanceof Iterator)
            return !((Iterator<?>) obj).hasNext();
        return true;
    }

    public static Boolean notEmpty(Object obj) {
        return !isEmpty(obj);
    }
    
    public static <F, T, R extends Collection<T>> R filter(Collection<F> collection, Predicate<F> predicate, Collector<F, ?, R> collector) {
        if(isEmpty(collection)) return Stream.<F>empty().collect(collector);
        return collection.stream().filter(predicate).collect(collector);
    }
    public static <F, T, R extends Collection<T>> R map(Collection<F> collection, Function<F, T> mapper, Collector<T, ?, R> collector) {
        if(isEmpty(collection)) return Stream.<T>empty().collect(collector);
        return collection.stream().map(mapper).collect(collector);
    }
    
    public static <F> void forEach(Collection<F> collection, Consumer<F> consumer) {
        if(isEmpty(collection)) return;
        collection.forEach(consumer);
    }

}
