package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day03 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay03");
        int score = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String compartment1 = line.substring(0, line.length()/2);
            String compartment2 = line.substring(line.length()/2, line.length());
            for (int i = 0; i < compartment1.length(); i++) {
                char x = compartment1.charAt(i);
                if (compartment2.contains(String.valueOf(x))) {
                    if (Character.isUpperCase(x)) {
                        score += (int) x - 38;
                    } else {
                        score += (int) x - 96;
                    }
                    break;
                }
            }
        }
        return score;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay03Test");
        int score = 0;
        int lineNumber = 1;
        for (String line; (line = reader.readLine()) != null;) {
            System.out.println(line);
        }
        return score;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day03 solution = new Day03();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
