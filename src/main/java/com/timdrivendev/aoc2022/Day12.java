package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day12 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay12");
        Map<Point, Character> heightMap = new HashMap<>();
        Point currentPos = null;
        Point destinationPos = null;
        int rowIndex = 0;
        for (String line; (line = reader.readLine()) != null;) {
            int colIndex = 0;
            for (char c : line.toCharArray()) {
                Point pos = new Point(rowIndex, colIndex);
                if ('S' == c) {
                    currentPos = pos;
                    heightMap.put(pos, 'a');
                } else if ('E' == c) {
                    destinationPos = pos;
                    heightMap.put(pos, 'z');
                } else {
                    heightMap.put(pos, c);
                }
                colIndex++;
            }
            rowIndex++;
        }
        System.out.println(currentPos + " " + destinationPos);
        List<Point> route = findRoute(heightMap, currentPos, Integer.MAX_VALUE, destinationPos);
        return route.size() - 1;
    }

    private List<Point> findRoute(Map<Point, Character> heightMap, Point currentPos, int abandonIfGreaterThan, Point destinationPos) {
        return findRouteIter(heightMap, currentPos, destinationPos, new HashMap<>(), abandonIfGreaterThan, new ArrayList<>());
    }

    private List<Point> findRouteIter(Map<Point, Character> heightMap, Point currentPos, Point destinationPos, Map<Point, Integer> shortestRt, int abandonIfGreaterThan, List<Point> acc) {
        if (acc.contains(currentPos)) {
            return null;
        }
        if (!heightMap.containsKey(currentPos)) {
            return null;
        }
        if (!acc.isEmpty() && (int) heightMap.get(currentPos) > (int) heightMap.get(acc.get(acc.size() - 1)) + 1) {
            return null;
        }
        acc.add(currentPos);
        if (acc.size() > abandonIfGreaterThan) {
            return null;
        }
        if (acc.size() < shortestRt.getOrDefault(currentPos, Integer.MAX_VALUE)) {
            shortestRt.put(currentPos, acc.size());
        } else {
            return null;
        }
        if (currentPos.equals(destinationPos)) {
            return acc;
        }

        Point up = new Point(currentPos.rowIndex - 1, currentPos.colIndex);
        List<Point> upRoute = findRouteIter(heightMap, up, destinationPos, shortestRt, abandonIfGreaterThan, new ArrayList<>(acc));

        Point down = new Point(currentPos.rowIndex + 1, currentPos.colIndex);
        List<Point> downRoute = findRouteIter(heightMap, down, destinationPos, shortestRt, abandonIfGreaterThan, new ArrayList<>(acc));

        Point left = new Point(currentPos.rowIndex, currentPos.colIndex - 1);
        List<Point> leftRoute = findRouteIter(heightMap, left, destinationPos, shortestRt, abandonIfGreaterThan, new ArrayList<>(acc));

        Point right = new Point(currentPos.rowIndex, currentPos.colIndex + 1);
        List<Point> rightRoute = findRouteIter(heightMap, right, destinationPos, shortestRt, abandonIfGreaterThan, new ArrayList<>(acc));

        List<Point> shortest = null;
        if (upRoute != null) {
            shortest = upRoute;
        }
        if (downRoute != null) {
            if (shortest != null) {
                if (downRoute.size() < shortest.size()) {
                    shortest = downRoute;
                }
            } else {
                shortest = downRoute;
            }
        }
        if (leftRoute != null) {
            if (shortest != null) {
                if (leftRoute.size() < shortest.size()) {
                    shortest = leftRoute;
                }
            } else {
                shortest = leftRoute;
            }
        }
        if (rightRoute != null) {
            if (shortest != null) {
                if (rightRoute.size() < shortest.size()) {
                    shortest = rightRoute;
                }
            } else {
                shortest = rightRoute;
            }
        }
        return shortest;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay12");
        Map<Point, Character> heightMap = new HashMap<>();
        Point currentPos = null;
        Point destinationPos = null;
        int rowIndex = 0;
        for (String line; (line = reader.readLine()) != null;) {
            int colIndex = 0;
            for (char c : line.toCharArray()) {
                Point pos = new Point(rowIndex, colIndex);
                if ('S' == c) {
                    currentPos = pos;
                    heightMap.put(pos, 'a');
                } else if ('E' == c) {
                    destinationPos = pos;
                    heightMap.put(pos, 'z');
                } else {
                    heightMap.put(pos, c);
                }
                colIndex++;
            }
            rowIndex++;
        }
        List<Point> startingPoints = new ArrayList<>();
        for (Point point : heightMap.keySet()) {
            if (heightMap.get(point) == 'a') {
                startingPoints.add(point);
            }
        }
        int shortestRoute = Integer.MAX_VALUE;
        for (Point startingPoint : startingPoints) {
            List<Point> route = findRoute(heightMap, startingPoint, shortestRoute, destinationPos);
            if (route != null && route.size() < shortestRoute) {
                shortestRoute = route.size();
            }
        }
        return shortestRoute - 1;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day12 solution = new Day12();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Point {
        private final int rowIndex;
        private final int colIndex;

        public Point(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + "," + colIndex + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return rowIndex == point.rowIndex && colIndex == point.colIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIndex, colIndex);
        }
    }
}
