import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите задание (0 - выход):");
            System.out.println("1. Box");
            System.out.println("2. ComparableItem");
            System.out.println("3. MaxFinder");
            System.out.println("4. FunctionApplier");
            System.out.println("5. Filter");
            System.out.println("6. Reducer");
            System.out.println("7. Collector");

            int choice = scanner.nextInt();
            switch (choice) {
                case 0: return;

                case 1: {
                    Box<Integer> box = new Box<>();
                    box.put(3);
                    System.out.println("Box содержит: " + box.get());
                    break;
                }

                case 2: {
                    IntegerComparable item = new IntegerComparable(5);
                    System.out.println("Сравниваем 5 с 3: " + item.compare(3));
                    break;
                }

                case 3: {
                    List<Box<? extends Number>> boxes = new ArrayList<>();
                    Box<Integer> b1 = new Box<>(); b1.put(5);
                    Box<Integer> b2 = new Box<>(); b2.put(10);
                    Box<Double> b3 = new Box<>(); b3.put(7.5);

                    boxes.add(b1);
                    boxes.add(b2);
                    boxes.add(b3);

                    System.out.println("Максимальное значение: " + MaxFinder.findMax(boxes));
                    break;
                }

                case 4: {
                    // 4.1 длина строк
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
                    List<Integer> lengths =
                            FunctionApplier.applyFunction(strings, String::length);
                    System.out.println("Длины строк: " + lengths);

                    // 4.2 отрицательные числа → положительные
                    List<Integer> numbers = Arrays.asList(1, -3, 7);
                    List<Integer> absNumbers =
                            FunctionApplier.applyFunction(numbers, n -> n < 0 ? -n : n);
                    System.out.println("Абсолютные значения: " + absNumbers);

                    // 4.3 максимумы массивов
                    List<int[]> arrays = Arrays.asList(new int[]{1, 5, 3}, new int[]{-2, -8}, new int[]{7});
                    List<Integer> maxValues =
                            FunctionApplier.applyFunction(arrays, arr -> Arrays.stream(arr).max().orElse(Integer.MIN_VALUE));
                    System.out.println("Максимальные значения массивов: " + maxValues);
                    break;
                }

                case 5: {
                    // 5.1 строки длиной ≥3
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
                    List<String> filteredStrings =
                            Filter.filterList(strings, s -> s.length() >= 3);
                    System.out.println("Строки ≥3 символов: " + filteredStrings);

                    // 5.2 положительные числа убрать
                    List<Integer> numbers = Arrays.asList(1, -3, 7);
                    List<Integer> filteredNumbers =
                            Filter.filterList(numbers, n -> n <= 0);
                    System.out.println("Отфильтрованные числа (≤0): " + filteredNumbers);

                    // 5.3 массивы без положительных элементов
                    List<int[]> arrays = Arrays.asList(new int[]{-1, -5}, new int[]{0}, new int[]{2, -2});
                    List<int[]> filteredArrays =
                            Filter.filterList(arrays, arr -> Arrays.stream(arr).allMatch(n -> n <= 0));
                    System.out.println("Массивы без положительных элементов:");
                    for (int[] arr : filteredArrays) {
                        System.out.println(Arrays.toString(arr));
                    }
                    break;
                }

                case 6: {
                    // 6.1 объединение строк
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
                    String combined =
                            Reducer.reduceList(strings, String::concat, "");
                    System.out.println("Объединённые строки: " + combined);

                    // 6.2 сумма чисел
                    List<Integer> numbers = Arrays.asList(1, -3, 7);
                    Integer sum = Reducer.reduceList(numbers, Integer::sum, 0);
                    System.out.println("Сумма чисел: " + sum);

                    // 6.3 общее количество элементов во вложенных списках
                    List<List<Integer>> listOfLists = Arrays.asList(
                            Arrays.asList(1, 2, 3),
                            Arrays.asList(4),
                            Arrays.asList()
                    );
                    int totalElements = Reducer.reduceList(
                            FunctionApplier.applyFunction(listOfLists, List::size),
                            Integer::sum,
                            0
                    );
                    System.out.println("Общее количество элементов во вложенных списках: " + totalElements);
                    break;
                }

                case 7: {
                    // 7.1 разделение положительные/отрицательные
                    List<Integer> numbers = Arrays.asList(1, -3, 7, -2);
                    Map<String, List<Integer>> splitNumbers = new HashMap<>();
                    splitNumbers.put("Положительные",
                            Collector.collect(numbers, ArrayList::new, n -> n > 0 ? n : null));
                    splitNumbers.put("Отрицательные",
                            Collector.collect(numbers, ArrayList::new, n -> n < 0 ? n : null));
                    System.out.println("Разделение положительные/отрицательные: " + splitNumbers);

                    // 7.2 строки по длине
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx", "qw");
                    Map<Integer, List<String>> byLength = new HashMap<>();
                    for (String s : strings) {
                        byLength.computeIfAbsent(s.length(), k -> new ArrayList<>()).add(s);
                    }
                    System.out.println("Разделение по длине: " + byLength);

                    // 7.3 уникальные строки
                    List<String> stringsWithDuplicates = Arrays.asList("qwerty", "asdfg", "qwerty", "qw");
                    Set<String> uniqueStrings = Collector.collect(stringsWithDuplicates, HashSet::new, x -> x);
                    System.out.println("Уникальные строки: " + uniqueStrings);
                    break;
                }

                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}