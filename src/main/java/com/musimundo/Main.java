package com.musimundo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main
{
    public static Date parseDate(String dateString)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date res = null;

        if(dateString == null || dateString.equals(""))
            return null;

        try {
            res = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            res = null;
        }

        return res;
    }

    public static void main(String[] args) {

//        String auditDate = "";
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        Date res = null;
//        try {
//
//            res = formatter.parse(auditDate);
//            System.out.println(res);
//            System.out.println(formatter.format(res));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            res = null;
//        }


        System.out.println(parseDate("12/05/2018 16:30"));
    }


}
