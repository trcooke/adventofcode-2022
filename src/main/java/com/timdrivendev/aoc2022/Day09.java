package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day09 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay09");
        Set<Point> tailVisited = new HashSet<>();
        Point head = new Point(0,0);
        Point tail = new Point(0,0);
        tailVisited.add(tail);
        for (String line; (line = reader.readLine()) != null;) {
            String[] instruction = line.split(" ");
            String direction = instruction[0];
            int steps = Integer.parseInt(instruction[1]);
            for (int i = 0; i < steps; i++) {
                switch (direction) {
                    case "L": head = new Point(head.x - 1, head.y); break;
                    case "R": head = new Point(head.x + 1, head.y); break;
                    case "U": head = new Point(head.x, head.y + 1); break;
                    case "D": head = new Point(head.x, head.y - 1); break;
                    default:
                        System.out.println("Unknown direction '" + direction + "'");
                }
                tail = followHead(head, tail);
                tailVisited.add(tail);
            }
        }
        return tailVisited.size();
    }

    private Point followHead(Point head, Point tail) {
        if (Math.abs(head.x - tail.x) <= 1 && Math.abs(head.y - tail.y) <= 1) {
            return tail;
        }
        if (head.x - tail.x == 0) {
            Point point = new Point(tail.x, tail.y + (head.y - tail.y) / 2);
            return point;
        }
        if (head.y - tail.y == 0) {
            Point point = new Point(tail.x + (head.x - tail.x) / 2, tail.y);
            return point;
        }
        if (Math.abs(head.x - tail.x) == 1) {
            Point point = new Point(head.x, tail.y + (head.y - tail.y) / 2);
            return point;
        }
        if (Math.abs(head.y - tail.y) == 1) {
            Point point = new Point(tail.x + (head.x - tail.x) / 2, head.y);
            return point;
        }
        if (Math.abs(head.x - tail.x) == 2 && Math.abs(head.y - tail.y) == 2) {
            Point point = new Point(tail.x + (head.x - tail.x) / 2, tail.y + (head.y - tail.y) / 2);
            return point;
        }
        System.out.println("Shouldn't be here");
        return tail;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay09");
        Set<Point> tailVisited = new HashSet<>();
        List<Point> rope = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rope.add(new Point(0,0));
        }
        tailVisited.add(rope.get(9));
        for (String line; (line = reader.readLine()) != null;) {
            String[] instruction = line.split(" ");
            String direction = instruction[0];
            int steps = Integer.parseInt(instruction[1]);
            for (int i = 0; i < steps; i++) {
//                System.out.println("H" + rope.get(0) + ", T " + rope.get(9));
                Point head = rope.get(0);
                switch (direction) {
                    case "L": head = new Point(head.x - 1, head.y); break;
                    case "R": head = new Point(head.x + 1, head.y); break;
                    case "U": head = new Point(head.x, head.y + 1); break;
                    case "D": head = new Point(head.x, head.y - 1); break;
                    default:
                        System.out.println("Unknown direction '" + direction + "'");
                }
                rope.set(0, head);
                for (int j = 1; j < rope.size(); j++) {
                    Point knot = rope.get(j);
                    Point knotInFront = rope.get(j - 1);
                    rope.set(j, followHead(knotInFront, knot));
                }
//                printRope(rope);
                tailVisited.add(rope.get(9));
            }
        }
        return tailVisited.size();
    }

    private void printRope(List<Point> rope) {
        int minx = 0;
        int maxx = 0;
        int miny = 0;
        int maxy = 0;
        for (Point point : rope) {
            if (point.x < minx) {
                minx = point.x;
            }
            if (point.x > maxx) {
                maxx = point.x;
            }
            if (point.y < miny) {
                miny = point.y;
            }
            if (point.y > maxy) {
                maxy = point.y;
            }
        }
        for (int y = maxy; y >= miny; y--) {
            for (int x = minx; x <= maxx; x++) {
                if (!rope.contains(new Point(x,y))) {
                    System.out.print(".");
                } else {
                    System.out.print(rope.indexOf(new Point(x,y)));
                }
            }
            System.out.println();
        }
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day09 solution = new Day09();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point(" + x + "," + y + ')';
        }
    }
}
