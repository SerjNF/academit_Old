package ru.inbox.foreman.deleteEvenNumbers;

import java.util.ArrayList;
import java.util.Arrays;

public class DeleteEvenNumbers {
    public static void main(String[] arg) {


        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 7, 5, 6, 7, 6, 8, 9));
        numbers.removeIf(x -> x % 2 == 0);

//        for (int i = 0; i < numbers.size(); ++i){
//            if(numbers.get(i)%2 == 0){
//                numbers.remove(i);
//                i--;
//            }
//        }
        System.out.println(numbers.toString());
    }
}
