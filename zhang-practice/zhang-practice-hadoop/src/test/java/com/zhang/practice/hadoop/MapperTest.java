package com.zhang.practice.hadoop;

import com.google.common.collect.Lists;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;

import java.io.IOException;
import java.util.List;

/**
 * @author : zzh
 * create at:  2020/1/16
 * @description:
 */
public class MapperTest {

    private MapDriver mapDriver;
    private ReduceDriver reduceDriver;
    private MapReduceDriver mapReduceDriver;

    @Before
    public void init() {   //初始化
        mapDriver = new MapDriver(new Demo.WordCountMapper());
        reduceDriver = ReduceDriver.newReduceDriver();
        reduceDriver.setReducer(new Demo.WordCountReducer());
        mapReduceDriver = MapReduceDriver.newMapReduceDriver();
        mapReduceDriver
                .withMapper(new Demo.WordCountMapper())
                .withReducer(new Demo.WordCountReducer());
    }

    @Test
    public void test() throws IOException {
        String line = "hh";

        mapDriver.withInput(new LongWritable(), new Text(line))
                // "03103"代表测试文件编号；22代表输入测试数据中第五位数，代表气温值
                .withOutput(new Text("hh"), new IntWritable(1))
                .runTest();

        // 输出
        List<Pair> expectedOutputList = mapDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList) {
            System.out.println(pair.getFirst() + " --- " + pair.getSecond());
        }
    }

    @Test
    public void testReducer() throws IOException {
        List<IntWritable> IntWritableList = Lists.newArrayList();
        IntWritableList.add(new IntWritable(1));
        IntWritableList.add(new IntWritable(1));
        IntWritableList.add(new IntWritable(1));
        IntWritableList.add(new IntWritable(1));
        IntWritableList.add(new IntWritable(1));
        reduceDriver.withInput(new Text("2016"), IntWritableList);
        reduceDriver.withOutput(new Text("2016"), new IntWritable(5));
        reduceDriver.runTest();
        // 输出
        List<Pair> expectedOutputList = reduceDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList){
            System.out.println(pair.getFirst() + " --- " + pair.getSecond());
        }
    }

    @Test
    public void testMapperAndReducer() throws IOException {
        Text text = new Text("hh");
        mapReduceDriver.withInput(new LongWritable(), text);
        mapReduceDriver.withOutput(new Text("hh"), new IntWritable(1));
        mapReduceDriver.runTest();
        // 输出
        List<Pair> expectedOutputList = mapReduceDriver.getExpectedOutputs();
        for (Pair pair : expectedOutputList) {
            System.out.println(pair.getFirst() + " --- " + pair.getSecond()); // 2014 --- 12
        }
    }
}

