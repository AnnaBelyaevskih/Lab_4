import java.util.List;

public class MaxFinder {

    public static double findMax(List<Box<? extends Number>> boxes) {
        double max = Double.NEGATIVE_INFINITY;
        for (Box<? extends Number> box : boxes) {
            Number value = box.get();
            if (value != null && value.doubleValue() > max) {
                max = value.doubleValue();
            }
        }
        return max;
    }
}