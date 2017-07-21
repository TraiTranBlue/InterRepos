package main;

/**
 * Created by cpu11118-local on 20/07/2017.
 */
public class TestMain {

    public static void main(String[] args){
        Integer val = Integer.valueOf(20);
        changeValue(val);
        System.out.println(val);
    }


    public static  Integer changeValue (Integer value){
      value = 4321984;
      return value;
    }
}
