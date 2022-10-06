package com.ocbc.auctionservice;

import java.util.Calendar;

public class Calender {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        System.out.println(calendar.get(Calendar.MONTH));
    }
}
