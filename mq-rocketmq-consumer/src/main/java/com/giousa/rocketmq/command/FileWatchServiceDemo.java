package com.giousa.rocketmq.command;

import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class FileWatchServiceDemo {

    private Listener listener;
    private List<String> paths;
    private boolean stopped = false;
    private boolean isDaemon = false;

    public FileWatchServiceDemo(List<String> paths, Listener listener) {
        this.paths = paths;
        this.listener = listener;
    }

    public void run() {
        System.out.println("FileWatchServiceDemo start!");
        while (!isStopped()) {
            try {
                Thread.sleep(2000);
                if (!CollectionUtils.isEmpty(paths) && paths.size() > 2) {
                    listener.onChanged("this is size > 2 path");
                } else {
                    System.out.println("无改动");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public interface Listener {
        void onChanged(String path);
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isDaemon() {
        return isDaemon;
    }

    public void setDaemon(boolean daemon) {
        isDaemon = daemon;
    }


    public static void main(String[] args) {
        FileWatchServiceDemo fileWatchServiceDemo = new FileWatchServiceDemo(Lists.newArrayList("a", "b", "c"), new Listener() {
            @Override
            public void onChanged(String path) {
                System.out.println("onChanged path = " + path);
            }
        });

        fileWatchServiceDemo.run();
    }
}
