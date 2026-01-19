import task1.Box;
import task1.ComparableItem;
import task2.MaxFinder;
import task3.Collector;
import task3.Filter;
import task3.FunctionApplier;
import task3.Reducer;

import java.util.*;
import java.util.function.Function;


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
                    ComparableItem<Integer> item = new ComparableItem<>() {
                        @Override
                        public int compare(Integer other) {
                            return 5 - other;
                        }
                    };

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

                    // функция для получения длины строки
                    FunctionApplier.Applier<String, Integer> getStringLength = value -> value.length();

                    // применяем функцию к списку
                    List<Integer> lengths = FunctionApplier.applyFunction(strings, getStringLength);

                    System.out.println("Длины строк: " + lengths);

                    // 4.2 отрицательные числа в положительные
                    List<Integer> numbers = Arrays.asList(1, -3, 7);

                    // функция для получения абсолютного значения
                    FunctionApplier.Applier<Integer, Integer> makePositive = number -> {
                        if (number < 0) {
                            return -number;
                        } else {
                            return number;
                        }
                    };

                    // применяем функцию к списку
                    List<Integer> absNumbers = FunctionApplier.applyFunction(numbers, makePositive);

                    System.out.println("Абсолютные значения: " + absNumbers);

                    // 4.3 максимумы массивов
                    List<int[]> arrays = Arrays.asList(
                            new int[]{1, 5, 3},
                            new int[]{-2, -8},
                            new int[]{7}
                    );

                    // функция для нахождения максимума в массиве
                    FunctionApplier.Applier<int[], Integer> findMaxInArray = array -> {
                        int max = array[0];
                        for (int i = 1; i < array.length; i++) {
                            if (array[i] > max) {
                                max = array[i];
                            }
                        }
                        return max;
                    };

                    // применяем функцию к списку массивов
                    List<Integer> maxValues = FunctionApplier.applyFunction(arrays, findMaxInArray);

                    System.out.println("Максимальные значения массивов: " + maxValues);

                    break;
                }


                case 5: {
                    // 5.1 строки длиной не менее 3
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");

                    List<String> filteredStrings = Filter.filterList(
                            strings,
                            s -> {
                                int length = s.length();
                                return length >= 3;
                            }
                    );

                    System.out.println("Строки длиной не менее 3 символов: " + filteredStrings);

                    // 5.2 только положительные числа
                    List<Integer> numbers = Arrays.asList(1, -3, 7);

                    List<Integer> filteredNumbers = Filter.filterList(
                            numbers,
                            n -> {
                                boolean isNonPositive;
                                if (n > 0) {
                                    isNonPositive = true;
                                } else {
                                    isNonPositive = false;
                                }
                                return isNonPositive;
                            }
                    );

                    System.out.println("Отфильтрованные числа: " + filteredNumbers);

                    // 5.3 массивы без положительных элементов
                    List<int[]> arrays = Arrays.asList(
                            new int[]{-1, -5,8},
                            new int[]{-99},
                            new int[]{2, -2}
                    );

                    List<int[]> filteredArrays = Filter.filterList(
                            arrays,
                            arr -> {
                                boolean allNonPositive = true;
                                for (int n : arr) {
                                    if (n >= 0) {
                                        allNonPositive = false;
                                        break;
                                    }
                                }
                                return allNonPositive;
                            }
                    );

                    System.out.println("Массивы без положительных элементов:");
                    for (int[] arr : filteredArrays) {
                        System.out.println(Arrays.toString(arr));
                    }

                    break;
                }


                case 6: {
                    // 6.1 объединение строк
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");

                    Reducer.ReducerFunction<String> concatFunction = (a, b) -> a + b;

                    String combined = Reducer.reduceList(strings, concatFunction, "");
                    System.out.println("Объединённые строки: " + combined);

                    // 6.2 сумма чисел
                    List<Integer> numbers = Arrays.asList(1, -3, 7);

                    Reducer.ReducerFunction<Integer> sumFunction = (a, b) -> a + b;

                    Integer sum = Reducer.reduceList(numbers, sumFunction, 0);
                    System.out.println("Сумма чисел: " + sum);

                    // 6.3 общее количество элементов во вложенных списках
                    List<List<Integer>> listOfLists = Arrays.asList(
                            Arrays.asList(1, 2, 3),
                            Arrays.asList(4),
                            Arrays.asList()
                    );

                    // сначала создаём список размеров каждого вложенного списка
                    FunctionApplier.Applier<List<Integer>, Integer> sizeApplier = list -> list.size();

                    List<Integer> sizes = FunctionApplier.applyFunction(listOfLists, sizeApplier);

                    // потом суммируем все размеры
                    Reducer.ReducerFunction<Integer> sumSizesFunction = (a, b) -> a + b;

                    int totalElements = Reducer.reduceList(sizes, sumSizesFunction, 0);
                    System.out.println("Общее количество элементов во вложенных списках: " + totalElements);

                    break;
                }



                case 7: {
                    // 7.1 разделение положительные/отрицательные
                    List<Integer> numbers = Arrays.asList(1, -3, 7, -2);
                    Map<String, List<Integer>> splitNumbers = new HashMap<>();

                    // функция для положительных чисел
                    Function<Integer, Integer> positiveMapper = n -> {
                        if (n > 0) {
                            return n;
                        } else {
                            return null;
                        }
                    };
                    List<Integer> positiveNumbers = Collector.collect(numbers, ArrayList::new, positiveMapper);
                    splitNumbers.put("Положительные", positiveNumbers);

                    // функция для отрицательных чисел
                    Function<Integer, Integer> negativeMapper = n -> {
                        if (n < 0) {
                            return n;
                        } else {
                            return null;
                        }
                    };
                    List<Integer> negativeNumbers = Collector.collect(numbers, ArrayList::new, negativeMapper);
                    splitNumbers.put("Отрицательные", negativeNumbers);

                    System.out.println("Разделение положительные/отрицательные: " + splitNumbers);

                    // 7.2 строки по длине
                    List<String> strings = Arrays.asList("qwerty", "asdfg", "zx", "qw");
                    Map<Integer, List<String>> byLength = new HashMap<>();

                    for (String s : strings) {
                        List<String> group = byLength.computeIfAbsent(s.length(), k -> new ArrayList<>());
                        group.add(s);
                    }

                    System.out.println("Разделение по длине: " + byLength);

                    // 7.3 уникальные строки
                    List<String> stringsWithDuplicates = Arrays.asList("qwerty", "asdfg", "qwerty", "qw");

                    // функция для добавления элемента в коллекцию
                    Function<String, String> identityMapper = x -> x;
                    Set<String> uniqueStrings = Collector.collect(stringsWithDuplicates, HashSet::new, identityMapper);

                    System.out.println("Уникальные строки: " + uniqueStrings);

                    break;
                }

                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}
