package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {

    BigInteger DIV_BY = BigInteger.ONE;
    long part1() throws IOException {
        BufferedReader reader = getInput("InputDay11");
        Map<Integer, Monkey> monkeys = new HashMap<>();
        String line = reader.readLine();
        while (line != null) {
            int monkeyNum = Integer.parseInt(line.split(" ")[1].replace(":", ""));
            Queue<BigInteger> items = new ArrayDeque<>();
            Stream<BigInteger> bigIntegerStream = Arrays.stream(reader.readLine().split(":")[1].split(",")).map(s -> new BigInteger(s.trim()));
            bigIntegerStream.forEach(items::add);
            String operation = reader.readLine().trim().split(":")[1];
            String operator = operation.split(" ")[4];
            String opVal = operation.split(" ")[5];

            Function<BigInteger, BigInteger> operationFunction;
            if ("*".equals(operator)) {
                if ("old".equals(opVal)) {
                    operationFunction = old -> old.pow(2).mod(DIV_BY);
                } else {
                    operationFunction = old -> old.multiply(new BigInteger(opVal)).mod(DIV_BY);
                }
            } else {
                if ("old".equals(opVal)) {
                    operationFunction = old -> old.add(old).mod(DIV_BY);
                } else {
                    operationFunction = old -> old.add(new BigInteger(opVal)).mod(DIV_BY);
                }
            }

            int divBy = Integer.parseInt(reader.readLine().trim().split(" ")[3]);
            Function<BigInteger, Boolean> test = (BigInteger worryLevel) -> worryLevel.mod(BigInteger.valueOf(divBy)).equals(BigInteger.ZERO);
            DIV_BY = DIV_BY.multiply(BigInteger.valueOf(divBy));

            int trueAction = Integer.parseInt(reader.readLine().trim().split(" ")[5]);
            int falseAction = Integer.parseInt(reader.readLine().trim().split(" ")[5]);
            monkeys.put(monkeyNum, new Monkey(items, operationFunction, test, trueAction, falseAction));
            reader.readLine();
            line = reader.readLine();
        }
        Map<Integer, Long> monkeyInspections = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            for (int monkeyNum = 0; monkeyNum < monkeys.size(); monkeyNum++) {
                Monkey thisMonkey = monkeys.get(monkeyNum);
                Queue<BigInteger> items = thisMonkey.getItems();
                while (items.peek() != null) {
                    BigInteger item = items.poll();
                    monkeyInspections.put(monkeyNum, monkeyInspections.getOrDefault(monkeyNum,0L) + 1);
                    item = thisMonkey.operation(item);
                    item = item.divide(BigInteger.valueOf(3));
                    if (thisMonkey.worryLevelTest(item)) {
                        monkeys.get(thisMonkey.monkeyIfTrue).addItem(item);
                    } else {
                        monkeys.get(thisMonkey.monkeyIfFalse).addItem(item);
                    }
                }
            }
        }
        List<Long> collect = monkeyInspections.values().stream().sorted().collect(Collectors.toList());
        long l = collect.get(collect.size() - 1) * collect.get(collect.size() - 2);
        return l;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("InputDay11");
        Map<Integer, Monkey> monkeys = new HashMap<>();
        String line = reader.readLine();
        while (line != null) {
            int monkeyNum = Integer.parseInt(line.split(" ")[1].replace(":", ""));
            Queue<BigInteger> items = new ArrayDeque<>();
            Stream<BigInteger> bigIntegerStream = Arrays.stream(reader.readLine().split(":")[1].split(",")).map(s -> new BigInteger(s.trim()));
            bigIntegerStream.forEach(items::add);
            String operation = reader.readLine().trim().split(":")[1];
            String operator = operation.split(" ")[4];
            String opVal = operation.split(" ")[5];

            Function<BigInteger, BigInteger> operationFunction;
            if ("*".equals(operator)) {
                if ("old".equals(opVal)) {
                    operationFunction = old -> old.pow(2).mod(DIV_BY);
                } else {
                    operationFunction = old -> old.multiply(new BigInteger(opVal)).mod(DIV_BY);
                }
            } else {
                if ("old".equals(opVal)) {
                    operationFunction = old -> old.add(old).mod(DIV_BY);
                } else {
                    operationFunction = old -> old.add(new BigInteger(opVal)).mod(DIV_BY);
                }
            }

            int divBy = Integer.parseInt(reader.readLine().trim().split(" ")[3]);
            Function<BigInteger, Boolean> test = (BigInteger worryLevel) -> worryLevel.mod(BigInteger.valueOf(divBy)).equals(BigInteger.ZERO);
            DIV_BY = DIV_BY.multiply(BigInteger.valueOf(divBy));

            int trueAction = Integer.parseInt(reader.readLine().trim().split(" ")[5]);
            int falseAction = Integer.parseInt(reader.readLine().trim().split(" ")[5]);
            monkeys.put(monkeyNum, new Monkey(items, operationFunction, test, trueAction, falseAction));
            reader.readLine();
            line = reader.readLine();
        }
        Map<Integer, Long> monkeyInspections = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            for (int monkeyNum = 0; monkeyNum < monkeys.size(); monkeyNum++) {
                Monkey thisMonkey = monkeys.get(monkeyNum);
                Queue<BigInteger> items = thisMonkey.getItems();
                while (items.peek() != null) {
                    BigInteger item = items.poll();
                    monkeyInspections.put(monkeyNum, monkeyInspections.getOrDefault(monkeyNum,0L) + 1);
                    item = thisMonkey.operation(item);
                    if (thisMonkey.worryLevelTest(item)) {
                        monkeys.get(thisMonkey.monkeyIfTrue).addItem(item);
                    } else {
                        monkeys.get(thisMonkey.monkeyIfFalse).addItem(item);
                    }
                }
            }
        }
        List<Long> collect = monkeyInspections.values().stream().sorted().collect(Collectors.toList());
        long l = collect.get(collect.size() - 1) * collect.get(collect.size() - 2);
        return l;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day11 solution = new Day11();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Monkey {
        private final Queue<BigInteger> items;
        private final Function<BigInteger, BigInteger> operationFunction;
        private final Function<BigInteger, Boolean> test;
        private final int monkeyIfTrue;
        private final int monkeyIfFalse;

        public Monkey(Queue<BigInteger> items, Function<BigInteger, BigInteger> operationFunction, Function<BigInteger, Boolean> test, int monkeyIfTrue, int monkeyIfFalse) {
            this.items = items;
            this.operationFunction = operationFunction;
            this.test = test;
            this.monkeyIfTrue = monkeyIfTrue;
            this.monkeyIfFalse = monkeyIfFalse;
        }

        public Queue<BigInteger> getItems() {
            return this.items;
        }

        public void addItem(BigInteger item) {
            this.items.add(item);
        }
        public BigInteger operation(BigInteger item) {
            return this.operationFunction.apply(item);
        }

        public boolean worryLevelTest(BigInteger item) {
            return this.test.apply(item);
        }
    }
}
