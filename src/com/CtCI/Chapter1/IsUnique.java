package com.CtCI.Chapter1;

// Problem: Implement an algorithm that determines if a string has all unique characters. What if you cannot use
// additional data structures?

import java.util.Arrays;
import java.util.HashSet;

public class IsUnique {
    public static void main(String[] args) {
        String[] TestValues = { "abcdefg", "aabbccddee", "abcdefga", "12345678", "abc 123 -)$"};

        System.out.println("Testing CheckUnique()...");
        for( String test: TestValues)
        {
            System.out.println(CheckUnique(test) ?
                    String.format("All characters in '%s' are unique!", test) :
                    String.format("'%s' contains repeated characters.", test));
        }

        System.out.println("Testing CheckUniqueInPlace()...");
        for( String test: TestValues)
        {
            System.out.println(CheckUniqueInPlace(test) ?
                    String.format("All characters in '%s' are unique!", test) :
                    String.format("'%s' contains repeated characters.", test));
        }
    }

    public static Boolean CheckUnique(String test) {
        HashSet<String> UniqueChars = new HashSet<>(Arrays.asList(test.split("")));
        return UniqueChars.size() == test.length();
    }

    public static Boolean CheckUniqueInPlace(String test) {
        for(int i = 0; i < test.length(); i++) {
            for(int j = i+1; j < test.length(); j++) {
                if (test.charAt(i) == test.charAt(j)) return false;
            }
        }

        return true;
    }
}
