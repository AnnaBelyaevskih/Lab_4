import java.util.ArrayList;
import java.util.List;

public class Filter {

    public interface Tester<T> {
        boolean test(T item);
    }

    public static <T> List<T> filterList(List<T> list, Tester<T> tester) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (tester.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
