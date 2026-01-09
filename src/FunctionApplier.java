
import java.util.ArrayList;
import java.util.List;

public class FunctionApplier {

    public interface Applier<T, P> {
        P apply(T item);
    }

    public static <T, P> List<P> applyFunction(List<T> list, Applier<T, P> applier) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(applier.apply(item));
        }
        return result;
    }
}