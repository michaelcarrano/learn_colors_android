package com.michaelcarrano.learn_colors;


/**
 * This Class holds the values from the String Resources so that we can easily access the paired
 * name and hex value.
 *
 * Created by michaelcarrano on 1/11/14.
 */
public class ColorsModel {

    private String name;
    private String hex;

    public ColorsModel(String name, String hex) {
        this.name = name;
        this.hex = hex;
    }

    public String getName() {
        return name;
    }

    public String getHex() {
        return hex;
    }
}
