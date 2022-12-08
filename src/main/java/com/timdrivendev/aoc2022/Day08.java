package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day08 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay08");
        List<List<Integer>> forest = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            List<Integer> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(Integer.parseInt(String.valueOf(c)));
            }
            forest.add(row);
        }
        int visibleCount = 0;
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                int treeHeight = forest.get(i).get(j);
                boolean visible = false;
                if (i == 0 || i == forest.size() - 1 || j == 0 || j == forest.get(i).size() - 1) {
                    visible = true;
                }
                if (visible) {
                    visibleCount++;
                    continue;
                } else {
                    boolean tmpVis = true;
                    for (int k = j - 1; k >= 0; k--) {
                        if (forest.get(i).get(k) >= treeHeight) {
                            tmpVis = false;
                            break;
                        }
                    }
                    visible = tmpVis;
                }
                if (visible) {
                    visibleCount++;
                    continue;
                } else {
                    boolean tmpVis = true;
                    for (int k = j + 1; k < forest.get(i).size(); k++) {
                        if (forest.get(i).get(k) >= treeHeight) {
                            tmpVis = false;
                            break;
                        }
                    }
                    visible = tmpVis;
                }
                if (visible) {
                    visibleCount++;
                    continue;
                } else {
                    boolean tmpVis = true;
                    for (int k = i - 1; k >= 0; k--) {
                        if (forest.get(k).get(j) >= treeHeight) {
                            tmpVis = false;
                            break;
                        }
                    }
                    visible = tmpVis;
                }
                if (visible) {
                    visibleCount++;
                    continue;
                } else {
                    boolean tmpVis = true;
                    for (int k = i + 1; k < forest.size(); k++) {
                        if (forest.get(k).get(j) >= treeHeight) {
                            tmpVis = false;
                            break;
                        }
                    }
                    visible = tmpVis;
                }
                if (visible) {
                    visibleCount++;
                    continue;
                }
            }
        }
        return visibleCount;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay08");
        List<List<Integer>> forest = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            List<Integer> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(Integer.parseInt(String.valueOf(c)));
            }
            forest.add(row);
        }
        int highestScenicScore = 0;
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                int treesVisibleLeft = 0;
                int treesVisibleRight = 0;
                int treesVisibleUp = 0;
                int treesVisibleDown = 0;
                int treeHeight = forest.get(i).get(j);
                System.out.println("Checking " + i + " " + j);
                // Left
                for (int k = j - 1; k >= 0; k--) {
                    System.out.print(i + " " + k + " ");
                    if (forest.get(i).get(k) >= treeHeight) {
                        System.out.println("too high");
                        treesVisibleLeft++;
                        break;
                    } else {
                        System.out.println("visible");
                        treesVisibleLeft++;
                    }
                }
                // Right
                for (int k = j + 1; k < forest.get(i).size(); k++) {
                    System.out.print(i + " " + k + " ");
                    if (forest.get(i).get(k) >= treeHeight) {
                        System.out.println("too high");
                        treesVisibleRight++;
                        break;
                    } else {
                        System.out.println("visible");
                        treesVisibleRight++;
                    }
                }
                // Up
                for (int k = i - 1; k >= 0; k--) {
                    System.out.print(k + " " + j + " ");
                    if (forest.get(k).get(j) >= treeHeight) {
                        System.out.println("too high");
                        treesVisibleUp++;
                        break;
                    } else {
                        System.out.println("visible");
                        treesVisibleUp++;
                    }
                }
                // Down
                for (int k = i + 1; k < forest.size(); k++) {
                    System.out.print(k + " " + j + " ");
                    if (forest.get(k).get(j) >= treeHeight) {
                        System.out.println("too high");
                        treesVisibleDown++;
                        break;
                    } else {
                        System.out.println("visible");
                        treesVisibleDown++;
                    }
                }
                int scenicScore = treesVisibleLeft * treesVisibleRight * treesVisibleUp * treesVisibleDown;
                System.out.println(scenicScore);
                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }
            }
        }
        return highestScenicScore;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day08 solution = new Day08();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
