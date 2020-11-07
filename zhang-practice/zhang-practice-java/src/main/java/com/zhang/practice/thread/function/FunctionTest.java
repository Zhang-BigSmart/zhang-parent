package com.zhang.practice.thread.function;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author : zzh
 * create at:  2020/11/7
 * @description:
 */
public class FunctionTest {

    public static void main(String[] args) {
        Integer[] i = {1, 2, 4, 5};
        // Function<T,R>  R是返回类型
        Function<Integer[], ArrayList<Integer>> arrayListFunction = FunctionTest::arrToArrayList;
        arrayListFunction.apply(i).forEach(System.out::println);

        ArrayList<Integer> list = FunctionTest.arrToList(i, l -> new ArrayList<>(Arrays.asList(l)));
        list.forEach(System.out::println);

        FunctionTest.arrToList(i, l -> new HashSet<>(Arrays.asList(l))).forEach(System.out::println);
        FunctionTest.arrToList(i, l -> new LinkedList<>(Arrays.asList(l))).forEach(System.out::println);

        FunctionTest.arrToList(i, l -> new LinkedList<>(Arrays.asList(l))).forEach(System.out::println);

        System.out.println(FunctionTest.compute(7, s -> s + "百"));

        System.out.println(FunctionTest.addition("hello", "wolrd", (a, b) -> a + " " + b));

        Function<String, String> function = Function.identity();
        function.apply("hello wolrd");
    }

    /**
     * 将数组转为ArrayList
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> ArrayList<T> arrToArrayList(T[] t) {
        Objects.requireNonNull(t);
        return new ArrayList<>(Arrays.asList(t));
    }

    private static <T, R> R arrToList(T[] t, Function<T[], R> function) {
        Objects.requireNonNull(t);
        return function.apply(t);
    }


    public static String compute(int a, Function<Integer, String> function) {
        return function.apply(a);             //function.apply() 接收实际的行为
    }

    public static String addition(String a, String b, BiFunction<String, String, String> biFunction) {
        return biFunction.apply(a, b);
    }
}
