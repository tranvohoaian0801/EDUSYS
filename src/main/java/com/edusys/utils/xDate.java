/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dell
 */
public class xDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static Date toDate(String date,String...pattern){
        try {
            if(pattern.length>0)formater.applyPattern(pattern[0]);
            if(date==null)return xDate.now();
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex); 
        }
    }
    public static String toString(Date date, String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    public static  Date addDays(Date date, long days){
       date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    public static Date now() {
        return new Date();   
    }
    public static Date add(int days) {
        Date now = xDate.now();
        now.setTime(now.getTime() + days*24*60*60*1000);
        return now;
    }
    
}
