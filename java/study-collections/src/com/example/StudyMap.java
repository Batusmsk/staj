package com.example;

import java.util.HashMap;
import java.util.Map;

public class StudyMap {
    public static void main(String[] args) {
        Map<String, Integer> areaCodes = new HashMap<>();
        areaCodes.put("İstanbul-anadolu", 216);
        areaCodes.put("İstanbul-avrupa", 212);
        areaCodes.put("Ankara", 210);
        System.out.println(areaCodes.get("ankara"));

        for (String city : areaCodes.keySet()) {
            System.out.println("Şehir: " + city);
        }

        for(int code : areaCodes.values()) {
            System.out.println("Kod: " + code);
        }
        for(Map.Entry<String, Integer> entry : areaCodes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }
}
