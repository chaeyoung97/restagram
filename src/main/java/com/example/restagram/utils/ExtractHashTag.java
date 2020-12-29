package com.example.restagram.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class ExtractHashTag {

    public static List<String> extract(String text){

        List<String>tags = new ArrayList<>();

        Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
        Matcher m = p.matcher(text);
        String tag = null;

        while(m.find()){
            tag = replaceChar(m.group());

            if(tag != null){
                tags.add(tag);
                System.out.println(">>>>추출된 해시태그 :" + tag);
            }
        }
        return tags;
    }
    public static String replaceChar(String str){
        str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ","");

        if(str.length() < 1){
            return null;
        }
        return str;
    }
}
