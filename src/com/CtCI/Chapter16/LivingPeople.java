package com.CtCI.Chapter16;

// Problem: Given a list of people with their birth and death years, implement a method to compute the year with the
// most number of people alive. You may assume that all people were born between 1900 and 2000 (inclusive). If a person
// was alive during any portion of that year, they should be included in that year's count. For example,
// Person (birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.

// Started: 3:24 PM

// Hints: #475, #489, #506, #513, #522, #531, #540, #548, #575

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LivingPeople {


    // IMPORTANT NOTE: We're only guaranteed that people are BORN between 1900 and 2000, not that they die in there too.


    public static void main (String[] args) {

        // Set A
        Person[] setA = {
                new Person(1900, 1952),
                new Person(1952, 1984),
                new Person(1999, 2075),
                new Person(1947, 1964)
        };
        int maxYearA = FindLargestYear(setA);
        System.out.format("The largest year for set A is %d.%n", maxYearA);

        // Set B


    }


    private static int FindLargestYear(Person[] people) {
        ArrayList<Person> population = new ArrayList<Person>(Arrays.asList(people));
        int oldestPerson = Collections.max(population).deathAge;
        Integer[] popCount = new Integer[100 + oldestPerson];
        Arrays.fill(popCount, 0);

        for(Person individual : population) {
            individual.printPerson();
            for(int i = individual.birthIndex; i < individual.deathIndex; i++) {
                System.out.format("    Increasing population for year %d. Value was %d, is now %d.%n", i + 1900, popCount[i], ++popCount[i]);
                popCount[i] = ++popCount[i];
            }
        }

        int maxYear = 0;
        for(int i = 0; i < popCount.length; i++) {

            System.out.format("    Current Year: %5d, Current Year Pop: %3d%n", i + 1900, popCount[i]);
            if (popCount[i] > popCount[maxYear]) {
                System.out.format("    Found new biggest year %d with population of %d.%n", i + 1900, popCount[i] + i);
            }

            maxYear = popCount[i] > popCount[maxYear] ? i : maxYear;


        }

        // What if multiple years have the same count? Currently, we'll just return the latest year.
        return maxYear + 1900;
    }




    private static class Person implements Comparable<Person> {
        public int birthYear;
        public int birthIndex;
        public int deathYear;
        public int deathIndex;
        public int deathAge;

        Person(int birth, int death) {
            if (birth < 1900 || birth > 2000) {
                throw new IllegalArgumentException(String.format("The person's birth year (%d) must be between 1900 and 2000 inclusive."));
            } else if (death < birth) {
                throw new IllegalArgumentException(String.format("A person cannot die (%d) before they are born (%d)."));
            }

            birthYear = birth;
            birthIndex = birth - 1900;
            deathYear = death;
            deathIndex = death - 1899;
            deathAge = death - birth;
        }

        @Override
        public int compareTo(Person o) {
            // Older people are 'greater than' younger people, because age-ism!
            return Integer.compare(deathAge, o.deathAge);
        }

        public void printPerson() {
            System.out.format("Birth Year: %5d   Death Year: %5d%n", birthYear, deathYear);
        }
    }

}
