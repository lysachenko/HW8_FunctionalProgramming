package com.lysachenko;

import com.lysachenko.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Executor {

    public static void run() {

        List<User> users = new ArrayList<>();

        users.add(new User("Vasia", 16, "Dnepr"));
        users.add(new User("Petia", 23, "Dnepr"));
        users.add(new User("Helen", 42, "Lutsk"));
        users.add(new User("Helen", 92, "Chernigov"));
        users.add(new User("Sergey", 5, "Kiev"));
        users.add(new User("Marina", 32, "Kiev"));
        users.add(new User("Ivan", 70, "Lvov"));
        users.add(new User("Roman", 20, "Lvov"));

        System.out.println("Users age over 40:");
        showUsersOverForty(users);

        System.out.println("\nUsers age under 50 and from Dnipro:");
        showUsersUnderFiftyAndLiveInDnipro(users, "Dnepr");

        System.out.println("\nAverage age in Lvov: " + getAverageAgeOfUsersInCity(users, "Lvov"));

        System.out.println("\nCount of Users who did not live in Kyiv: " + getCountOfUsersNotLiveInCity(users, "Kiev"));

        System.out.println("\nSorted by age, limit 3 first users: ");
        showSortUsersLimitThree(users);

        List<Integer> integerList = new Random()
                .ints(15, 1, 100)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("\nSource list of integer:");
        integerList.forEach(integer -> System.out.print(integer + " "));

        System.out.println("\n\nMax element: " + getMaxValue(integerList));
        System.out.println("\nMin element: " + getMinValue(integerList));

        System.out.println("\nValue multiple of number two:");
        showValueMultipleOfTwo(integerList);

        System.out.println("\n\nAll values increased by 10:");
        showValuesIncreasedByTen(integerList);
    }

    private static long getCountOfUsersNotLiveInCity(List<User> users, String city) {
        return users.stream()
                .filter(user -> !user.getCity().equals(city))
                .count();
    }

    private static long getAverageAgeOfUsersInCity(List<User> users, String city) {
        return getSumOfAge(users, city) / getCountOfUsers(users, city);
    }

    private static Integer getMinValue(List<Integer> integerList) {
        return integerList.stream().min(Integer::compare).get();
    }

    private static Integer getMaxValue(List<Integer> integerList) {
        return integerList.stream().max(Integer::compare).get();
    }

    private static void showValuesIncreasedByTen(List<Integer> integerList) {
        integerList.stream()
                .mapToInt(value -> value + 10)
                .boxed()
                .forEach(integer -> System.out.print(integer + " "));
    }

    private static void showValueMultipleOfTwo(List<Integer> integerList) {
        integerList.stream()
                .filter(integer -> integer % 2 == 0)
                .forEach(integer -> System.out.print(integer + " "));
    }

    private static void showSortUsersLimitThree(List<User> users) {
        users.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .limit(3)
                .forEach(System.out::println);
    }

    private static int getSumOfAge(List<User> users, String city) {
        return users.stream()
                .filter(user1 -> user1.getCity().equals(city))
                .mapToInt(User::getAge)
                .sum();
    }

    private static long getCountOfUsers(List<User> users, String city) {
        return users.stream()
                .filter(user1 -> user1.getCity().equals(city))
                .count();
    }

    private static void showUsersUnderFiftyAndLiveInDnipro(List<User> users, String city) {
        users.stream()
                .filter(user -> user.getAge() < 50)
                .filter(user -> user.getCity().equals(city))
                .forEach(System.out::println);
    }

    private static void showUsersOverForty(List<User> users) {
        users.stream()
                .filter(user -> user.getAge() > 40)
                .forEach(System.out::println);
    }
}
