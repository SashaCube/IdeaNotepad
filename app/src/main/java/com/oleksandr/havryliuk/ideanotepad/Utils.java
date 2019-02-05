package com.oleksandr.havryliuk.ideanotepad;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {
    public static int nextColorIndex;
    public static String getFormatDate(long intDate){
        String newDate;
        Date date = new Date(intDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy hh:mm", new Locale(getCountry()));
            newDate = dateFormat.format(date);

        return newDate;
    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static String getRandomColor() {
        Random random = new Random();
        return String.format("#%06x", random.nextInt(256*256*256));
    }

    public static ColorDrawable getColorByRGB(String rgb){
        return new ColorDrawable(Color.parseColor(rgb));
    }
}
