package com.zhang.practice.rocketmq;

import org.apache.commons.cli.*;

/**
 * @ClassName CommandTest
 * @Description:
 * @Author: zhangzh
 * @Date 2019/4/12 11:36
 */
public class CommandTest {

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        Option opt = new Option("n", "namesrvAddr", true,
                "Name server address list, eg: 192.168.0.1:9876;192.168.0.2:9876");
        opt.setRequired(false);
        options.addOption(opt);

        opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();

        }

        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        if (commandLine.hasOption('h')) {
            hf.printHelp("appName", options, true);
            return;
        }

        String optNmae = "n";
        if (commandLine.hasOption(optNmae)) {
            System.out.println(commandLine.getOptionValue(optNmae));
            for (String s : commandLine.getOptionValues(optNmae)) {
                System.out.print(s+" ");
            }
        }
    }
}
