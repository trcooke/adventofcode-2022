package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day06 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay06");
        String line = reader.readLine();
        return startOfPacketMarker(line);
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay06");
        String line = reader.readLine();
        return startOfMessageMarker(line);
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day06 solution = new Day06();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    public int startOfPacketMarker(String buffer) {
        for (int i = 0; i < buffer.length() - 4; i++) {
            String marker = buffer.substring(i, i + 4);
            if (allUnique(marker)) {
                return i + 4;
            }
        }
        return 0;
    }

    public int startOfMessageMarker(String buffer) {
        for (int i = 0; i < buffer.length() - 14; i++) {
            String marker = buffer.substring(i, i + 14);
            if (allUnique(marker)) {
                return i + 14;
            }
        }
        return 0;
    }

    private boolean allUnique(String marker) {
        boolean unique = true;
        for (int i = 0; i < marker.length(); i++) {
            for (int j = i + 1; j < marker.length(); j++) {
                if (marker.charAt(i) == marker.charAt(j)) {
                    unique = false;
                    break;
                }
            }
        }
        return unique;
    }
}
