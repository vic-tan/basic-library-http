package com.tlf.basic.http.okhttp.bean;

import java.io.Serializable;

/**
 * Created by tanlifei on 16/10/17.
 */

public class OkHttpConsole implements Serializable{


    private String appName;
    private boolean on_of_level;
    private int random_max;
    private boolean is_random;
    private String timer_start;
    private String timer_end;
    private boolean is_start_timer;

    public OkHttpConsole() {
    }


    public OkHttpConsole(String appName, boolean on_of_level, int random_max, boolean is_random, String timer_start, String timer_end, boolean is_start_timer) {
        this.appName = appName;
        this.on_of_level = on_of_level;
        this.random_max = random_max;
        this.is_random = is_random;
        this.timer_start = timer_start;
        this.timer_end = timer_end;
        this.is_start_timer = is_start_timer;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isOn_of_level() {
        return on_of_level;
    }

    public void setOn_of_level(boolean on_of_level) {
        this.on_of_level = on_of_level;
    }

    public int getRandom_max() {
        return random_max;
    }

    public void setRandom_max(int random_max) {
        this.random_max = random_max;
    }

    public boolean is_random() {
        return is_random;
    }

    public void setIs_random(boolean is_random) {
        this.is_random = is_random;
    }

    public String getTimer_start() {
        return timer_start;
    }

    public void setTimer_start(String timer_start) {
        this.timer_start = timer_start;
    }

    public String getTimer_end() {
        return timer_end;
    }

    public void setTimer_end(String timer_end) {
        this.timer_end = timer_end;
    }

    public boolean is_start_timer() {
        return is_start_timer;
    }

    public void setIs_start_timer(boolean is_start_timer) {
        this.is_start_timer = is_start_timer;
    }
}
