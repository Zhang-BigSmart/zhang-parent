package com.zhang.practice.offere.search;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 * @ClassName BinarySearch
 * @Description:
 * @Author: zhangzh
 * @Date 2019/2/17 8:35
 */
class BinarySearch implements SearchAlgorithm{

    @Override
    public  <T extends Comparable<T>> int find(T[] array, T key) {
        return search(array, key, 0, array.length);
    }

    /**
     *
     *
     * @param array
     * @param key
     * @param left
     * @param right
     * @param <T>
     * @return the location of the key
     */
    private <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        if (left > right) return -1;

        int mid = left + ((right - left) >> 1);
        int comp = key.compareTo(array[mid]);

        if (comp < 0) {
            return search(array, key, left, mid - 1);
        }else if (comp > 0) {
            return search(array, key, mid + 1, right);
        }else {
            return mid;
        }
    }

    // Driver Program
    public static void main(String[] args) {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        int[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().toArray();
        // The element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];
        Integer[] integerT = new Integer[integers.length];
        for (int i = 0; i < integers.length;i++) {
            integerT[i] = integers[i];
        }
        BinarySearch search = new BinarySearch();
        int atIndex = search.find(integerT, shouldBeFound);



        System.out.println(format(
                "Should be found: %d. Found %d at index %d. An array length %d",
                shouldBeFound, integers[atIndex], atIndex, size
        ));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(format("Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));

    }


}
