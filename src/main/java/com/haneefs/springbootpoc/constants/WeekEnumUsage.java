package com.haneefs.springbootpoc.constants;

import lombok.val;

public class WeekEnumUsage {
    public static void main(String[] args) {
        String legacuVal = "SD";

        String week = Week.valueOf(legacuVal).name;
        System.out.println(week);
    }
}
