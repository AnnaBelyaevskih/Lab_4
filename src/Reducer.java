import java.util.List;

public class Reducer {

    public interface ReducerFunction<T> {
        T apply(T a, T b);
    }

    public static <T> T reduceList(List<T> list, ReducerFunction<T> function, T identity) {
        T result = identity;
        for (T item : list) {
            result = function.apply(result, item);
        }
        return result;
    }
}