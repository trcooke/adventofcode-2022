package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day02 {

    private String roundSubstitution(String deal) {
        switch (deal) {
            case "A X": return "A Z";
            case "A Y": return "A X";
            case "A Z": return "A Y";
            case "B X": return "B X";
            case "B Y": return "B Y";
            case "B Z": return "B Z";
            case "C X": return "C Y";
            case "C Y": return "C Z";
            case "C Z": return "C X";
            default: return deal;
        }
    }

    private int scoreforRound(String deal) {
        switch (deal) {
            case "A X":
            case "B Y":
            case "C Z": return 3;
            case "A Z":
            case "B X":
            case "C Y": return 0;
            default: return 6;
        }
    }

    private int scoreforShape(String deal) {
        switch (deal) {
            case "X": return 1;
            case "Y": return 2;
            case "Z": return 3;
            default: return 0;
        }
    }

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay02");
        int score = 0;
        for (String line; (line = reader.readLine()) != null;) {
            score += scoreforShape(line.substring(line.length() - 1)) + scoreforRound(line);
        }
        return score;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay02");
        int score = 0;
        for (String line; (line = reader.readLine()) != null;) {
            line = roundSubstitution(line);
            score += scoreforShape(line.substring(line.length() - 1)) + scoreforRound(line);
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
        Day02 solution = new Day02();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
