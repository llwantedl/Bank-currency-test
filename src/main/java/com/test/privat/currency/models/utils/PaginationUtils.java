package com.test.privat.currency.models.utils;

public class PaginationUtils {

    public static int formatPage(String page){
        try {
            return Integer.parseInt(page);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}
