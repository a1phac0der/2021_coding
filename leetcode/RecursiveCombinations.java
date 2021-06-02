package leetcode;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class RecursiveCombinations {

    private static void print(char[] charArray, int index, String str){
        if (index == charArray.length){
            if (str.isEmpty()){
                System.out.println("empty string");
            }
            System.out.println(str);
            return;
        }
        print(charArray, index+1, str);
        print(charArray, index+1, str + charArray[index]);
    }

    public static void main(String[] args) {
        Deque<String> dq = new LinkedList<String>(){{add("sridhar");}};
        Deque<String> dq1 = new LinkedList<String>(){{add("vinodarao");}};;
        dq1.addAll(dq);
        System.out.println(dq1);
        dq.clear();
        System.out.println(dq1);

    }
}
