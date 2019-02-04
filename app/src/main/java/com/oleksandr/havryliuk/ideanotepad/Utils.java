package com.oleksandr.havryliuk.ideanotepad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String getFormatDate(long intDate){
        String newDate;
        Date date = new Date(intDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
            newDate = dateFormat.format(date);

        return newDate;
    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }
}
