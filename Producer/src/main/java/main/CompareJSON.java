package main;

/**
 * Created by cpu11118-local on 04/08/2017.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CompareJSON {
    private ObjectMapper objectMapper;
    private HashMap json1;
    private HashMap json2;

    public CompareJSON() {
        objectMapper = new ObjectMapper();
    }


    public void setJson1(String str) {
        try {
            json1 = objectMapper.readValue(str, HashMap.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setJson2(String str) {
        try {
            json1 = objectMapper.readValue(str, HashMap.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    HashMap getNode(String str) {
        try {
            return objectMapper.readValue(str, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean compare(String JSON1, String JSON2) throws IOException {
        HashMap x = getNode(JSON1);
        HashMap y = getNode(JSON2);
        if (x == null || y == null) {
            return false;
        }
        return algorithmCompare(x, y);
    }

    /**
     * @param object1
     * @param object2
     * @return
     * @throws IOException
     * @dieudv
     */
    private boolean algorithmCompare(Object object1, Object object2) throws IOException {
        System.out.println(object1.getClass());
        if (!object1.getClass().equals(object2.getClass())) {
            return false;
        }
        if (object1 instanceof ArrayList && object2 instanceof ArrayList) {
            if (((ArrayList) object1).size() == ((ArrayList) object2).size()) {
                for (int i = 0; i < ((ArrayList) object1).size(); i++) {
                    if (!algorithmCompare(((ArrayList) object1).get(i), ((ArrayList) object2).get(i))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        if (object2 instanceof HashMap && object1 instanceof HashMap) {
            for (HashMap.Entry<String, Object> obEntry : ((HashMap<String, Object>) object2).entrySet()) {
                if (((HashMap) object1).get(obEntry.getKey()) == null) {
                    return false;
                }
                if (!algorithmCompare(obEntry.getValue(), ((HashMap) object1).get(obEntry.getKey()))) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        CompareJSON compareJSON = new CompareJSON();
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
        long milisecond = System.nanoTime();
        if(compareJSON.compare(str1, str2)){
            System.out.println("OK");
        }
        System.out.println((System.nanoTime()-milisecond)/1e6);
    }
}
