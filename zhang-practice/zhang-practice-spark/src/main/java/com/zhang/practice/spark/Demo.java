package com.zhang.practice.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

/**
 * @author : zzh
 * create at:  2020/2/21
 * @description:
 */
public class Demo {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark WordCount Application (java)")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4));

        JavaRDD<Integer> result = rdd.map(x -> x * x);
        System.out.println(Arrays.toString(result.collect().toArray()));
    }
}
