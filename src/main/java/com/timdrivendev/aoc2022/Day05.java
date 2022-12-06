package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day05 {

    String part1() throws IOException {
        BufferedReader reader = getInput("InputDay05");
        List<String> inputLines = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            inputLines.add(line);
        }
        int lineIndex = 0;
        String currentLine = inputLines.get(lineIndex);
        List<String> cratesInputLines = new ArrayList<>();
        while (!currentLine.isEmpty()) {
            cratesInputLines.add(currentLine);
            lineIndex++;
            currentLine = inputLines.get(lineIndex);
        }
        Map<Integer, Stack<Character>> crates = new HashMap<>();
        for (int i = cratesInputLines.size() - 2; i >= 0; i--) {
            String crateInputLine = cratesInputLines.get(i);
            int crateNumber = 1;
            for (int j = 1; j < crateInputLine.length(); j += 4) {
                Character item = crateInputLine.charAt(j);
                if (!item.equals(' ')) {
                    Stack<Character> crate = crates.getOrDefault(crateNumber, new Stack<>());
                    crate.push(item);
                    crates.put(crateNumber, crate);
                }
                crateNumber++;
            }
        }



        lineIndex++;
        while (lineIndex < inputLines.size()) {
            String[] tokens = inputLines.get(lineIndex).split(" ");
            int count = Integer.parseInt(tokens[1]);
            int orig = Integer.parseInt(tokens[3]);
            int dest = Integer.parseInt(tokens[5]);

            Stack<Character> origCrate = crates.get(orig);
            Stack<Character> destCrate = crates.get(dest);
            for (int i = 0; i < count; i++) {
                Character pop = origCrate.pop();
                destCrate.push(pop);
            }
            lineIndex++;
        }
        String output = "";
        for (Integer integer : crates.keySet()) {
            output += crates.get(integer).pop();
        }

        return output;
    }

    String part2() throws IOException {
        BufferedReader reader = getInput("InputDay05");
        List<String> inputLines = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            inputLines.add(line);
        }
        int lineIndex = 0;
        String currentLine = inputLines.get(lineIndex);
        List<String> cratesInputLines = new ArrayList<>();
        while (!currentLine.isEmpty()) {
            cratesInputLines.add(currentLine);
            lineIndex++;
            currentLine = inputLines.get(lineIndex);
        }
        Map<Integer, Stack<Character>> crates = new HashMap<>();
        for (int i = cratesInputLines.size() - 2; i >= 0; i--) {
            String crateInputLine = cratesInputLines.get(i);
            int crateNumber = 1;
            for (int j = 1; j < crateInputLine.length(); j += 4) {
                Character item = crateInputLine.charAt(j);
                if (!item.equals(' ')) {
                    Stack<Character> crate = crates.getOrDefault(crateNumber, new Stack<>());
                    crate.push(item);
                    crates.put(crateNumber, crate);
                }
                crateNumber++;
            }
        }



        lineIndex++;
        while (lineIndex < inputLines.size()) {
            String[] tokens = inputLines.get(lineIndex).split(" ");
            int count = Integer.parseInt(tokens[1]);
            int orig = Integer.parseInt(tokens[3]);
            int dest = Integer.parseInt(tokens[5]);

            Stack<Character> origCrate = crates.get(orig);
            Stack<Character> destCrate = crates.get(dest);
            Stack<Character> intermediaryStack = new Stack<>();
            for (int i = 0; i < count; i++) {
                Character pop = origCrate.pop();
                intermediaryStack.push(pop);
            }
            for (int i = 0; i < count; i++) {
                destCrate.push(intermediaryStack.pop());
            }
            lineIndex++;
        }
        String output = "";
        for (Integer integer : crates.keySet()) {
            output += crates.get(integer).pop();
        }

        return output;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day05 solution = new Day05();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
