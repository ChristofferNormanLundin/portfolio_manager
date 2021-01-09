package com.stock.portfoliomanager.utils;

public final class Utils {

    public static double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
