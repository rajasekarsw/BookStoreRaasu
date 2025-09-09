package com.bookstoreapi.data;

import java.util.Random;

public class Datagenerator {

    public static String randomEmail() {
        return "username" + new Random().nextInt(9999) + "@example.com";
    }

    public static int randomID() {
        return new Random().nextInt(99999);
    }

    public static String randomPwd() {
        return "paswd" + new Random().nextInt(999);
    }
    public static int randomYear() {
        int startYear = 1900;
        int endYear = 2025;
        return startYear +new Random().nextInt(endYear - startYear + 1);
    }
}
