package com.zhang.practice.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;

/**
 * @author : zzh
 * create at:  2020/3/2
 * @description:
 */
public class JsonSql {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark WordCount Application (java)")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);


        SQLContext sqlContext = new SQLContext(sc);
        HiveContext hiveCtx = new HiveContext(sc);
    }
}
