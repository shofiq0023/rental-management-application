package com.api.rms.utilities;

import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Utility {
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T classInstance = null;

        try {
            classInstance = clazz.getDeclaredConstructor().newInstance();

            BeanUtils.copyProperties(source, classInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classInstance;
    }

    public static String getCurrentMonthName() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public static String getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        return String.valueOf(year);
    }
}
