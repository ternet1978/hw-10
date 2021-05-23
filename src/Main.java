import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 1 - List to list with odd numerated names");
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("Jonny");
        listOfNames.add("Ivan");
        listOfNames.add("Taras");
        listOfNames.add("Peter");
        listOfNames.add("Natali");
        listOfNames.add("Jessica");
        listOfNames.add("Luke");
        listOfNames.add("Alex");
        System.out.println(odd(listOfNames));

        System.out.println("\nTask 2 - to upper case and reverse sorted order:");
        System.out.println(task2(listOfNames));
        String[] array = {"1, 2, 0", "4, 5"};
        System.out.println("\nTask 3 - string array to sorted string:");
        System.out.println(task3(array));


        System.out.println("\nTask 4 - RANDOM NUMBERS:");


        System.out.println(task4(25214903917L, 11L, 2 ^ 48L, 28L).collect(Collectors.toList()));

        System.out.println("\nTask 5 - Two streams to one but, equal parts from both :");
        List<String> listOfTowns = new ArrayList<>();
        listOfTowns.add("Moskov");
        listOfTowns.add("Kiev");
        listOfTowns.add("Zhitomir");
        listOfTowns.add("Vinnica");
        listOfTowns.add("Herson");

        Stream<String> first = listOfNames.stream();
        Stream<String> second = listOfTowns.stream();
        Stream<String> twoStreams = zip(first, second);
        List<String> twoLists = twoStreams.collect(Collectors.toList());

        System.out.println(twoLists);


    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());
        long a = firstList.size();
        long b = secondList.size();
        long limit = a > b ? b : a;
        first = firstList.stream();
        second = secondList.stream();
        Stream<T> both = Stream.concat(first.limit(limit), second.limit(limit));
        List<T> bothLists = both.collect(Collectors.toList());
        Collections.shuffle(bothLists);
        return bothLists.stream();
    }

    public static String odd(List<String> list) {
        return list.stream()
                .filter(s -> list.indexOf(s) % 2 == 0)
                .map(s -> (list.indexOf(s) + 1) + ". " + s)
                .collect(Collectors.joining(", "));
    }

    public static List<String> task2(List<String> list) {
        return (List) list.stream()
                .map(s -> s.toUpperCase())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }


    public static String task3(String[] array) {
        return Arrays.stream(array)
                .flatMap(e -> Arrays.stream(e.split(",")))
                .map(s -> s.trim())
                .sorted((o1, o2) -> (Integer.valueOf(o1) - Integer.valueOf(o2)))
                .collect(Collectors.joining(", "));
    }


    public static Stream<Long> task4(Long a, Long c, Long m, Long seed) {
        return Stream.iterate(seed, n -> (a * n + c) % m).limit(10);
    }
}
