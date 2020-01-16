package com.zhang.practice.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author : zzh
 * create at:  2020/1/15
 * @description:
 */
public class Demo {


    /**
     *
     * Mapper参数：输入键，输入值，输出键，输出值
     */
    public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {

        String input = "hdfs://localhost:9000/user/wordcount";
        String output = "hdfs://localhost:9000/user/wordcount/result";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(Demo.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setJar("/Users/edison/Documents/git/zhang-parent/zhang-practice/zhang-practice-hadoop/target/zhang-practice-hadoop-1.0.0.jar");
        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        System.exit(job.waitForCompletion(true) ? 0 : 1);



//
//        JobConf conf = new JobConf(Demo.class);
//        conf.setJobName("WordCount");
//        conf.addResource("classpath:/core-site.xml");
//        conf.addResource("classpath:/hdfs-site.xml");
//        conf.addResource("classpath:/hadoop/mapred-site.xml");
//
//        conf.setJarByClass(Demo.class);
//
//        // 设置输出类型和值
//        conf.setOutputKeyClass(Text.class);
//        conf.setOutputValueClass(IntWritable.class);
//
//        // 设置map和reduce类型
//        conf.setMapperClass(WordCountMapper.class);
//        conf.setCombinerClass(WordCountReducer.class);
//        conf.setReducerClass(WordCountReducer.class);
//
//        conf.setInputFormat(TextInputFormat.class);
//        conf.setOutputFormat(TextOutputFormat.class);
//
//        FileInputFormat.addInputPath(conf, new Path(input));
//        FileOutputFormat.setOutputPath(conf, new Path(output));
//        //FileInputFormat.setInputPaths(conf, new Path(input));
//        //FileOutputFormat.setOutputPath(conf, new Path(output));
//
//        JobClient.runJob(conf);
//        System.exit(0);
    }
}
