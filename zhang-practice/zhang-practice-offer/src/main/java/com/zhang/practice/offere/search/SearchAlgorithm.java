package com.zhang.practice.offere.search;

/**
 * @ClassName SearchAlgorithm
 * @Description:
 * @Author: zhangzh
 * @Date 2019/2/17 10:41
 */
public interface SearchAlgorithm {

    /**
     *
     * @param array is an array where the element should be found
     * @param key is an element which should be found
     * @param <T> Comparable type
     * @return first found index of the element
     */
    <T extends Comparable<T>> int find(T[] array, T key);
}
