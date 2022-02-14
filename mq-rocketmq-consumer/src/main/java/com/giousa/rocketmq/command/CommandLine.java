package com.giousa.rocketmq.command;

import org.apache.commons.cli.*;

public class CommandLine {

    public static void main(String[] args) {
        try {
            createParser(args);
        } catch (ParseException e) {
            System.out.println("创建指令异常");
            e.printStackTrace();
        }

        //HelpFormatter
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("testHelpFormatter",createOptions());
    }

    private static Options createOptions() {
        Options options = new Options();

        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);

        opt = new Option("n", "namesrvAddr", true,
                "Name server address list, eg: 192.168.0.1:9876;192.168.0.2:9876");
        opt.setRequired(false);
        options.addOption(opt);

        return options;
    }

    private static void createParser(String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        org.apache.commons.cli.CommandLine commandLine = parser.parse(createOptions(), args);
        // Set the appropriate variables based on supplied options
        if (commandLine.hasOption('h')) {
            System.out.println("执行了h指令:   " + commandLine.getOptionValue('h'));

        }
        if (commandLine.hasOption('n')) {
            String n = commandLine.getOptionValue('n');
            System.out.println("执行了n指令:   " + n);
        }
    }
}
