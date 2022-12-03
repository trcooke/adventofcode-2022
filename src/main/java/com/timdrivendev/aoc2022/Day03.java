package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
                    score += getScore(x);
                    break;
                }
            }
        }
        return score;
    }

    private static int getScore(char x) {
        if (Character.isUpperCase(x)) {
            return (int) x - 38;
        } else {
            return (int) x - 96;
        }
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay03");
        int score = 0;
        int elfNumber = 0;
        Map<Integer, String> elfGroup = new HashMap<>();
        for (String line; (line = reader.readLine()) != null;) {
            elfGroup.put(elfNumber, line);
            if (elfNumber == 2) {
                System.out.println(elfGroup);
                for (int i = 0; i < elfGroup.get(0).length(); i++) {
                    char x = elfGroup.get(0).charAt(i);
                    if (elfGroup.get(1).contains(String.valueOf(x)) && elfGroup.get(2).contains(String.valueOf(x))) {
                        score += getScore(x);
                        break;
                    }
                }
                System.out.println("work");
            }
            elfNumber = (elfNumber + 1) % 3;
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
