package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day04 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay04");
        int fullyContainedCount = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String[] split = line.split(",");
            int lowerA = Integer.parseInt(split[0].split("-")[0]);
            int upperA = Integer.parseInt(split[0].split("-")[1]);
            int lowerB = Integer.parseInt(split[1].split("-")[0]);
            int upperB = Integer.parseInt(split[1].split("-")[1]);
            if ((lowerB >= lowerA && upperB <= upperA) || (lowerA >= lowerB && upperA <= upperB)) {
                fullyContainedCount++;
            }
        }
        return fullyContainedCount;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay04");
        int overlapCount = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String[] split = line.split(",");
            int lowerA = Integer.parseInt(split[0].split("-")[0]);
            int upperA = Integer.parseInt(split[0].split("-")[1]);
            int lowerB = Integer.parseInt(split[1].split("-")[0]);
            int upperB = Integer.parseInt(split[1].split("-")[1]);
            if ((lowerA >= lowerB && lowerA <= upperB) || (upperA >= lowerB && upperA <= upperB)
                    || (lowerB >= lowerA && lowerB <= upperA) || (upperB >= lowerA && upperB <= upperA)) {
                overlapCount++;
            }
        }
        return overlapCount;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day04 solution = new Day04();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
