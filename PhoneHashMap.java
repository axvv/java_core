package lesson4;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneHashMap {
    private static HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public static void add(String lastName, String phoneNumber) {
        ArrayList<String> phonesForLastName = phoneBook.getOrDefault(lastName, new ArrayList<>());
        phonesForLastName.add(phoneNumber);
        phoneBook.put(lastName, phonesForLastName);
        System.out.println(phoneBook);
    }

    public static void get(String lastName) {
        if (phoneBook.containsKey(lastName)) {
            System.out.println(lastName + "  " + phoneBook.get(lastName));
        } else System.out.println(lastName + "  нет в телефонной книге");
    }
}

