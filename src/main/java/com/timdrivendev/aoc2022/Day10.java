package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class Day10 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay10");
        int cycle = 0;
        int x = 1;
        int sumSignalStrength = 0;
        Set<Integer> cyclesOfInterest = Set.of(20, 60, 100, 140, 180, 220);
        for (String line; (line = reader.readLine()) != null;) {
            if ("noop".equals(line)) {
                cycle++;
                if (cyclesOfInterest.contains(cycle)) {
                    sumSignalStrength += cycle * x;
                }
            } else {
                cycle++;
                if (cyclesOfInterest.contains(cycle)) {
                    sumSignalStrength += cycle * x;
                }
                cycle++;
                if (cyclesOfInterest.contains(cycle)) {
                    sumSignalStrength += cycle * x;
                }
                x += Integer.parseInt(line.split(" ")[1]);
            }
        }
        return sumSignalStrength;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay10");
        int cycle = 0;
        int x = 1;
        int sumSignalStrength = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if ("noop".equals(line)) {
                cycle++;
                draw(cycle, x);
            } else {
                cycle++;
                draw(cycle, x);
                cycle++;
                draw(cycle, x);
                x += Integer.parseInt(line.split(" ")[1]);
            }
        }
        System.out.println();
        return sumSignalStrength;
    }

    private void draw(int cycle, int x) {
        int tmpCycle = (cycle - 1) % 40;
        if (tmpCycle == 0) {
            System.out.println();
        }
        if (tmpCycle == x || tmpCycle == x - 1 || tmpCycle == x + 1) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day10 solution = new Day10();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
