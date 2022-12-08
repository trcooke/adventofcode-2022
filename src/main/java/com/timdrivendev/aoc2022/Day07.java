package com.timdrivendev.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day07 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay07");
        Node fs = new DirNode(null, "/");
        Deque<String> terminalOutput = new ArrayDeque<>();
        for (String line; (line = reader.readLine()) != null;) {
            terminalOutput.add(line);
        }
        fs = populateFileSystemFromOutput(fs, fs, terminalOutput);
//        printFs(fs);
        int sumSizes = sumDirSizesUpTo(fs, 100000, 0);

        return sumSizes;
    }

    private int sumDirSizesUpTo(Node fs, int atMost, int acc) {
        int dirSize = fs.getSize();
        if (dirSize <= atMost) {
//            System.out.println(dirSize + " " + fs.getName());
            acc += dirSize;
        }
        for (Node childDir : fs.getChildDirs()) {
            acc += sumDirSizesUpTo(childDir, atMost, 0);
        }
        return acc;
    }

    private void printFs(Node fs) {
        System.out.println(fs.getName());
        for (Node childFile : fs.getChildFiles()) {
            System.out.println(childFile.getSize() + " " + childFile.getName());
        }

        for (Node childDir : fs.getChildDirs()) {
            printFs(childDir);
        }
    }

    private Node populateFileSystemFromOutput(Node root, Node current, Deque<String> terminalOutput) {
        if (terminalOutput.isEmpty()) {
            return root;
        }
        String[] pop = terminalOutput.pop().split(" ");
        if ("$".equals(pop[0])) {
            String command = pop[1];
            if ("cd".equals(command)) {
                String arg = pop[2];
                if ("/".equals(arg)) {
                    current = root;
                } else if ("..".equals(arg)) {
                    current = current.getParentDir();
                } else {
                    current = findDirNode(current, arg);
                }
            } else if ("ls".equals(command)) {
                while (terminalOutput.peek() != null && (!"$".equals(terminalOutput.peek().split(" ")[0]))) {
                    String[] content = terminalOutput.pop().split(" ");
                    if ("dir".equals(content[0])) {
                        current.setChildDir(content[1]);
                    } else {
                        current.setChildFile(content[0], content[1]);
                    }
                }
            } else {
                System.out.println("Unknown");
            }
        }
        return populateFileSystemFromOutput(root, current, terminalOutput);
    }

    private Node findDirNode(Node current, String arg) {
        for (Node childDir : current.getChildDirs()) {
            if (arg.equals(childDir.getDirName())) {
                return childDir;
            }
        }
        return null;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay07");
        Node fs = new DirNode(null, "/");
        Deque<String> terminalOutput = new ArrayDeque<>();
        for (String line; (line = reader.readLine()) != null;) {
            terminalOutput.add(line);
        }
        fs = populateFileSystemFromOutput(fs, fs, terminalOutput);
        int totalDiskSpace = 70000000;
        int requiredDiskSpace = 30000000;
        int usedSpace = fs.getSize();
        int unusedSpace = totalDiskSpace - usedSpace;
        int minToBeDeleted = requiredDiskSpace - unusedSpace;
        Node dirToBeDeleted = findDirWithSizeOverAndClosestTo(fs, minToBeDeleted, fs);

        return dirToBeDeleted.getSize();
    }

    private Node findDirWithSizeOverAndClosestTo(Node fs, int minToBeDeleted, Node currentSmallest) {
        int dirSize = fs.getSize();
        Node smallestNode = currentSmallest;
        if (dirSize >= minToBeDeleted && dirSize < currentSmallest.getSize()) {
            smallestNode = fs;
        }
        for (Node childDir : fs.getChildDirs()) {
            smallestNode = findDirWithSizeOverAndClosestTo(childDir, minToBeDeleted, smallestNode);
        }
        return smallestNode;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day07 solution = new Day07();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private interface Node {
        List<Node> getChildDirs();

        String getDirName();

        void setChildDir(String s);

        void setChildFile(String size, String name);

        Node getParentDir();

        String getName();

        List<Node> getChildFiles();

        int getSize();
    }

    private class DirNode implements Node {

        private final String dirName;
        List<Node> childDirs = new ArrayList<>();
        List<Node> childFiles = new ArrayList<>();
        private Node parentDir;

        public DirNode(Node parentDir, String dirName) {
            this.parentDir = parentDir;
            this.dirName = dirName;
        }

        @Override
        public List<Node> getChildDirs() {
            return childDirs;
        }

        @Override
        public String getDirName() {
            return this.dirName;
        }

        @Override
        public void setChildDir(String s) {
            childDirs.add(new DirNode(this, s));
        }

        @Override
        public void setChildFile(String size, String name) {
            this.childFiles.add(new FileNode(Integer.parseInt(size), name));
        }

        @Override
        public Node getParentDir() {
            return this.parentDir;
        }

        @Override
        public String getName() {
            return this.dirName;
        }

        @Override
        public List<Node> getChildFiles() {
            return this.childFiles;
        }

        @Override
        public int getSize() {
            int size = 0;
            for (Node childFile : childFiles) {
                size += childFile.getSize();
            }
            for (Node childDir : childDirs) {
                size += childDir.getSize();
            }
            return size;
        }
    }

    class FileNode implements Node {

        private final int size;
        private final String name;

        public FileNode(int size, String name) {
            this.size = size;
            this.name = name;

        }

        @Override
        public List<Node> getChildDirs() {
            return null;
        }

        @Override
        public String getDirName() {
            return null;
        }

        @Override
        public void setChildDir(String s) {

        }

        @Override
        public void setChildFile(String size, String name) {

        }

        @Override
        public Node getParentDir() {
            return null;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<Node> getChildFiles() {
            return null;
        }

        @Override
        public int getSize() {
            return this.size;
        }
    }
}
