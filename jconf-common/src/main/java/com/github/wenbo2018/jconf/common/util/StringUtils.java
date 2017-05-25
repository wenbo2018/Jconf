package com.github.wenbo2018.jconf.common.util;

/**
 * Created by wenbo.shen on 2017/5/23.
 */
public class StringUtils {


    public static boolean isEmpty(String str) {
        return str==null||str.length()==0;
    }

    public static int parseInt(String str){
        return stringParseInt(str,0);
    }

    public static double parseDouble(String str) {
        return stringParseDouble(str,0);
    }

    public static boolean parseBoolen(String str) {
        return str.equals("yes")||str=="yes";
    }


    public static double stringParseDouble(String str,int defaultValue){
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static int stringParseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

}
