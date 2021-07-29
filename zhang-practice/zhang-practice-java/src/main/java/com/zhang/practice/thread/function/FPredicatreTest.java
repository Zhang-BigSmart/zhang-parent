package com.zhang.practice.thread.function;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/5/25
 */
public class FPredicatreTest {


    public static void main(String[] args) {
        Predicate<Integer> predicate = (integer) -> {
            if (integer > 5) {
                return true;
            }
            return false;
        };
        predicate = (i) -> i > 5;

        System.out.println(predicate.test(6));
        System.out.println(predicate.test(1));
    }
}
