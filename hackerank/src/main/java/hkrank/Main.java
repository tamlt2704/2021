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
}