package com.stock.portfoliomanager.utils;

import org.springframework.stereotype.Component;

@Component
public final class Utils {

    public double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
