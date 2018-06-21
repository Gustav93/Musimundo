package com.musimundo.utilities;

import java.util.Date;

public class Utils {
	
	public static String getDateString(Date date) {
    	String todate = "";

    	if(date == null)
    		return todate;

    	todate+=(date.getYear()+1900)+"/";
    	todate+=(date.getMonth()+1)+"/";
    	todate+=(date.getDate())+" ";
    	
    	todate+=(date.getHours()+":");
    	todate+=(date.getMinutes()+":");
    	todate+=(date.getSeconds());
    	return todate;
    }

}
