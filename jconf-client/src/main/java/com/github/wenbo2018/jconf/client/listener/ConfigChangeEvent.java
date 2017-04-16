package com.github.wenbo2018.jconf.client.listener;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class ConfigChangeEvent {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConfigChangeEvent() {
    }

    public ConfigChangeEvent(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
