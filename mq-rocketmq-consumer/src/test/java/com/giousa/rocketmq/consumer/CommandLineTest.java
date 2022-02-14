package com.giousa.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.giousa.rocketmq.BaseTest;
import org.apache.commons.cli.*;
import org.junit.Test;

public class CommandLineTest extends BaseTest {

    @Test
    public void systemGetTest() {
        Options options = createOptions();
        System.out.println("options: " + JSON.toJSONString(options));

    }

    private Options createOptions() {
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


}