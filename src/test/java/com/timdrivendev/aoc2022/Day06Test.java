package com.timdrivendev.aoc2022;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Day06Test {
    Day06 day06 = new Day06();
    @Test
    public void part1TestCase1() {
        assertThat(day06.startOfPacketMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"), is(7));
    }
    @Test
    public void part1TestCase2() {
        assertThat(day06.startOfPacketMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"), is(5));
    }
    @Test
    public void part1TestCase3() {
        assertThat(day06.startOfPacketMarker("nppdvjthqldpwncqszvftbrmjlhg"), is(6));
    }
    @Test
    public void part1TestCase4() {
        assertThat(day06.startOfPacketMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), is(10));
    }
    @Test
    public void part1TestCase5() {
        assertThat(day06.startOfPacketMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), is(11));
    }

    @Test
    public void part2TestCase1() {
        assertThat(day06.startOfMessageMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"), is(19));
    }
    @Test
    public void part2TestCase2() {
        assertThat(day06.startOfMessageMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"), is(23));
    }
    @Test
    public void part2TestCase3() {
        assertThat(day06.startOfMessageMarker("nppdvjthqldpwncqszvftbrmjlhg"), is(23));
    }
    @Test
    public void part2TestCase4() {
        assertThat(day06.startOfMessageMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), is(29));
    }
    @Test
    public void part2TestCase5() {
        assertThat(day06.startOfMessageMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), is(26));
    }
}
