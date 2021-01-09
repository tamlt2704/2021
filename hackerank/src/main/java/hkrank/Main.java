package hkrank;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hacker rank");
    }

    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        int alicePoint = 0;
        int bobPoint = 0;
        for (int i = 0; i < a.size(); i++) {
            int c = a.get(i).compareTo(b.get(i));
            if (c >= 1) {
                alicePoint += 1;
            }
            if (c < 0) {
                bobPoint += 1;
            }
        }
        return Arrays.asList(alicePoint, bobPoint);
    }

    //https://www.hackerrank.com/challenges/diagonal-difference/problem
    public static int diagonalDifference(List<List<Integer>> arr) {
        int n = arr.get(0).size();
        int leftD = 0;
        int rightD = 0;
        for (int i = 0, j = n-1; i < n; i++, j--) {
            leftD += arr.get(i).get(i);
            rightD += arr.get(i).get(j);
        }
        return Math.abs(leftD - rightD);
    }

    //https://www.hackerrank.com/challenges/plus-minus
    static void plusMinus(int[] arr) {
        double nbPlus = 0, nbMinus = 0, nbZero = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                nbPlus += 1;
            }
            if (arr[i] == 0) {
                nbZero += 1;
            }
            if (arr[i] < 0) {
                nbMinus += 1;
            }
        }

        System.out.printf("%.6f\n", nbPlus / arr.length);
        System.out.printf("%.6f\n", nbMinus / arr.length);
        System.out.printf("%.6f\n", nbZero / arr.length);
    }
}