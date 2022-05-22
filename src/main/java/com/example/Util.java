package com.example;

import net.sf.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Util {
    public class DateOfBirth{
        public String year;
        public String month;
        public String day;
    }
    public static String ConvertToJsonString(Object nesne){
        JSONObject responseBody = new JSONObject();
        responseBody.put("data", nesne);
        return responseBody.toString();
    }
    private static String ConvertDateofBirtToString(DateOfBirth dateofbirt){
       return dateofbirt.day + "." + dateofbirt.month + "." + dateofbirt.year;
    }

    public static LocalDate ConvertToDate(JSONObject tarih){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(ConvertDateofBirtToString(JsonToObje(tarih)), formatter);
        return  date;
    }
    private static DateOfBirth JsonToObje(JSONObject obje)
    {
        DateOfBirth nesne = (DateOfBirth) JSONObject.toBean(obje, DateOfBirth.class);
        return nesne;
    }

        private static String ConvertDateofBirtToString(String dateofbirt){
        String[] dizi = dateofbirt.split("-");
        return  dizi[2] + "." +  dizi[1] + "." +  dizi[0];
    }
    public static LocalDate ConvertToDate(String tarih){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(ConvertDateofBirtToString(tarih), formatter);
        return  date;
    }

}
