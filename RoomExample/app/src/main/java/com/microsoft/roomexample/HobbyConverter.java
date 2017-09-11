package com.microsoft.roomexample;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

public class HobbyConverter {

    @TypeConverter
    public static List<String> fromString(String string) {
        String[] items = string.split(",");
        return Arrays.asList(items);
    }

    @TypeConverter
    public static String toString(List<String> items) {
        return TextUtils.join(",", items);
    }
}
