package com.example;

import lombok.SneakyThrows;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


public class Util {
    public class DateOfBirth{
        public String year;
        public String month;
        public String day;
    }

    public static Integer[] JsonArrayToIntArray(JSONArray jsArray){
        Integer[] intArray = new Integer[jsArray.size()];
        for(int i= 0; i< jsArray.size(); i++){
            intArray[i] = (int)jsArray.get(i);
        }
        return intArray;
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
    @SneakyThrows
    public static String GetSha256Hash(String stringText){
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(stringText.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
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
