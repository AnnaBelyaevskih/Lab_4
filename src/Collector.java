import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Collector {

    public static <T, C extends Collection<T>> C collect(
            List<T> list, Supplier<C> collectionSupplier, Function<T, T> mapper) {
        C collection = collectionSupplier.get();
        for (T item : list) {
            collection.add(mapper.apply(item));
        }
        return collection;
    }
}