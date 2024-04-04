package utils;

import java.util.List;

public class Utils {

    public static <T> T[] listToArray(List<T> list){
        T[] array = (T[])new Object[list.size()];

        int i = 0;
        for (T iterator : list){
            array[i++] = iterator;
        }
        return array;

    }

    public static String numberToLetter(int number){
        String seat = "";
        switch (number){
            case 0:
                seat = "A";
                break;
            case 1:
                seat = "B";
                break;
            case 2:
                seat = "C";
                break;
            case 3:
                seat = "D";
                break;
        }
        return seat;
    }
    public static int letterToNumber(String letter){
        int seat = 0;
        switch (letter){
            case "A":
                seat = 0;
                break;
            case "B":
                seat = 1;
                break;
            case "C":
                seat = 2;
                break;
            case "D":
                seat = 3;
                break;
        }
        return seat;
    }

}
