package com.zhang.practice.thread;

import org.bytedeco.javacpp.Loader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzh
 * create at:  2022/6/8
 * @description:
 */
public class CvDemo {

    public static void main(String[] args) {
        List<String> command = new ArrayList<>();
        amix(command);
        long start = System.currentTimeMillis();
        execute(command);
        System.out.println("用时:" + (System.currentTimeMillis()-start));
    }

    public static void execute(List<String> command) {
        try {
            String join = String.join(" ", command);
            System.out.println(join);
            ProcessBuilder process = new ProcessBuilder(command);
            process.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void amix(List<String> command) {
        //获取JavaCV中的ffmpeg本地库的调用路径
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        command.add(ffmpeg);
        command.add("-re");
        command.add("-i");
        command.add("/Users/edison/Downloads/f5.aac");
        command.add("-i");
        command.add("/Users/edison/Downloads/f4.aac");
        command.add("-filter_complex");
        command.add("amix=inputs=2:duration=longest:dropout_transition=1");
       /* command.add("-map");
        command.add("0:v");
        command.add("-map");
        command.add("0:a");
        command.add("-map");
        command.add("1:a");
        //-shortest会取视频或音频两者短的一个为准，多余部分则去除不合并
        command.add("-longest");*/
//
        //可以推到 流媒体服务器上。 例如srs
        //command.add("rtmp://8.136.201.114:1935/live/livestream");
        //文件夹需要自己创建。
        //也可以推到其他的文件夹(相当于录像)
        command.add("/Users/edison/Downloads/f6.mp3");
    }


}
