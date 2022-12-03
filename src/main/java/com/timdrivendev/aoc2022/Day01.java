package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 {

    int part1() throws IOException {
        Map<Integer, Integer> elfCalories = new HashMap<>();

        BufferedReader reader = getInput("InputDay01");
        int currentElf = 1;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.trim().isEmpty()) {
                currentElf++;
            } else {
                if (elfCalories.containsKey(currentElf)) {
                    elfCalories.put(currentElf, elfCalories.get(currentElf) + Integer.parseInt(line));
                } else {
                    elfCalories.put(currentElf, Integer.parseInt(line));
                }
            }
        }
        System.out.println(elfCalories);
        List<Integer> sorted = elfCalories.values().stream().sorted().collect(Collectors.toList());
        return sorted.get(sorted.size() - 1);
    }

    int part2() throws IOException {
        Map<Integer, Integer> elfCalories = new HashMap<>();

        BufferedReader reader = getInput("InputDay01");
        int currentElf = 1;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.trim().isEmpty()) {
                currentElf++;
            } else {
                if (elfCalories.containsKey(currentElf)) {
                    elfCalories.put(currentElf, elfCalories.get(currentElf) + Integer.parseInt(line));
                } else {
                    elfCalories.put(currentElf, Integer.parseInt(line));
                }
            }
        }
        System.out.println(elfCalories);
        List<Integer> sorted = elfCalories.values().stream().sorted().collect(Collectors.toList());
        return sorted.get(sorted.size() - 1) + sorted.get(sorted.size() - 2) + sorted.get(sorted.size() - 3);
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day01 solution = new Day01();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
