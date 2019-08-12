package com.jackting.common.util;

import java.text.DecimalFormat;

public class PriceUtil {
    public static String getShowPrice(double price){
        DecimalFormat df = new DecimalFormat("#.00");
        String str=df.format(price);
        if(str.startsWith(".")){
            str=0+str;
        }
        return str;
    }
}
