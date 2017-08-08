package main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by cpu11118-local on 20/07/2017.
 */
public class TestMain implements Cloneable{

    public static void main(String[] args){

        String str1 = "{\n" +
                "  \"id\": \"\",\n" +
                "  \"plants\": [\n" +
                "    {\n" +
                "      \"number_users\": 1,\n" +
                "      \"abc\": {\n" +
                "        \"x\": \"y\",\n" +
                "        \"d\": \"e\",\n" +
                "        \"g\": [\n" +
                "          {\n" +
                "            \"h\": 1\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"public_number\": [\n" +
                "        {\n" +
                "          \"number_in1\": \"19001080\",\n" +
                "          \"number_out1\": \"028888888\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"number_in_2\": \"19001080\",\n" +
                "          \"number_out_2\": \"028888888\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"public_number2\": [\n" +
                "        {\n" +
                "          \"number_in3\": \"19001080\",\n" +
                "          \"number_out3\": \"028888888\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"number_in_4\": \"19001080\",\n" +
                "          \"number_out_4\": \"028888888\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"number_in_4\": \"19001080\",\n" +
                "          \"number_out_4\": \"028888888\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String str2 = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"plants\": [\n" +
                "    {\n" +
                "      \"number_users\": 1,\n" +
                "      \"abc\": {\n" +
                "        \"x\": \"y\",\n" +
                "        \"d\": \"e\",\n" +
                "        \"g\": [\n" +
                "          {\n" +
                "            \"h\": 1\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"public_number\": [\n" +
                "        {\n" +
                "          \"number_out1\": \"19001080\",\n" +
                "          \"number_in1\": \"028888888\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"number_in_2\": \"19001080\",\n" +
                "          \"number_out_2\": \"028888888\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"public_number2\": [\n" +
                "        {\n" +
                "          \"number_in3\": \"19001080\",\n" +
                "          \"number_out3\": \"028888888\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"number_in_4\": \"19001080\",\n" +
                "          \"number_out_4\": \"028888888\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"number_in_4\": \"19001080\",\n" +
                "          \"number_out_4\": \"028888888\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CompareJSONObject json = new CompareJSONObject(objectMapper.readValue(str1, HashMap.class));
            CompareJSONObject jsonCompare = new CompareJSONObject(objectMapper.readValue(str2, HashMap.class));
            long timeStart = System.nanoTime();
//            if(json.compare(jsonCompare)){
//                System.out.println("Compare result : sample");
//            }else {
//                System.out.println("Compare result : not sample");
//            }

//            System.out.println((System.nanoTime() - timeStart));

//            minNumber(21, 11);
            maxNumber(21,11);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void minNumber (int x, int y){
        System.out.println( y + ((x - y) & ((x - y) >>31)));
    }

    private static void maxNumber (int x, int y){
//        System.out.println(Integer.toBinaryString(5));
//        System.out.println(5);
//        System.out.println(Integer.toBinaryString(128));
////        System.out.println(~5);
//        System.out.println(Integer.toBinaryString(-128));
//        System.out.println(~(((x - y)>>31)));
//        System.out.println( y + ((x - y) & ~((x - y) >>31)));
    }
}
