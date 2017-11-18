package com.example.vipshah.espressodemo;

import java.util.ArrayList;
import java.util.List;

public final class PersonUtils {

    public static List<Person> getKids(List<Person> persons) {
        List<Person> kids = new ArrayList<>();
        for (Person person : persons) {
            if (person.age < 18) {
                kids.add(person);
            }
        }
        return kids;
    }

    public static Integer[] getEvenNumbers(Integer[] numbers) {
        List<Integer> evenNumbers = new ArrayList<>();
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            }
        }
        return evenNumbers.toArray(new Integer[evenNumbers.size()]);
    }
}
