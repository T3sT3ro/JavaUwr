package W6;

import W6.algorithms.BST;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class TestBST {


    static BST<String> D = new BST<>();
    static BST<Integer> T = new BST<>();
    static BST<Integer> R = new BST<>();

    static void add(int val) {
        T.insert(val);
        System.out.println("a" + val + "\t" + T.toString());
    }

    static void rem(int val) {
        T.remove(val);
        System.out.println("r" + val + "\t" + T.toString());
    }

    static void cl() {
        T.clear();
        System.out.println(T.toString());
    }

    static void is(int val) {
        System.out.println(val + ": " + T.search(val));
    }

    public static void main(String[] args) {
        integerTest1();
        stringTest();
        randomTest();
    }

    private static void randomTest() {
        Random random = new Random();
        int ops = Math.abs(random.nextInt()) % 10000;
        LinkedList<Integer> Dq = new LinkedList<>();
        while (ops-- > 0) {
            int thing = random.nextInt();
            if (thing % 2 == 0) {
                int elem = random.nextInt();
                R.insert(elem);
                Dq.add(elem);
                Collections.sort(Dq);
                if (!R.min().equals(Dq.getFirst())) {
                    System.err.println("R: " + R.toString());
                    System.err.println("Dq:" + Dq.toString());
                    return;
                }
                if (!R.max().equals(Dq.getLast())) {
                    System.err.println("R: " + R.toString());
                    System.err.println("Dq:" + Dq.toString());
                    return;
                }
            } else if (R.size() > 0) {
                int elem = Math.abs(random.nextInt()) % Dq.size();
                int val = Dq.get(elem);
                R.remove(val);
                Dq.remove(elem);
                if (!R.toString().equals(Dq.toString())) {
                    System.err.println("R: " + R.toString());
                    System.err.println("Dq:" + Dq.toString());
                    return;
                }
            }
        }
        System.out.println("Random insertion deletion test passed.");
    }

    private static void stringTest() {
        D.insert("Kamil");
        D.insert("Andrzej");
        D.insert("Grzegoz");
        D.insert("Kunegunda");
        D.insert("Zbyszek");
        D.insert("Kamila");
        D.insert("Wiesiek");
        D.insert("Wieslawa");
        D.insert("Ewaryst");
        System.out.println(D.toString());
    }

    private static void integerTest1() {
        add(10);
        add(5);
        add(15);
        add(2);
        add(29);
        add(17);
        add(7);
        add(0);
        add(-21);
        add(-12);
        add(-7);
        add(17);
        add(17);
        is(7);
        rem(7);
        is(7);
        rem(7);
        is(7);
        rem(17);
        rem(10);
        is(10);
        is(29);
        is(-21);
        is(0);
        rem(29);
        rem(17);
        rem(17);
        is(17);
        rem(-21);
        is(0);
        is(15);
        is(-21);
        rem(15);
        rem(0);
        is(15);
        is(0);
        rem(-12);
        rem(-7);
        rem(0);
        rem(2);
        rem(5);
        rem(15);
        rem(2);
        cl();
        for (int i = 0; i < 20; i++)
            add(2);
        for (int i = 0; i < 20; i++)
            rem(2);
        for (int i = 0; i < 20; i++)
            add(i);
        for (int i = 0; i < 20; i++)
            rem(i);
        cl();
        try {
            T.insert(null);
        } catch (NullPointerException e) {
            System.out.println("Inserting null produced NPE");
        }
    }
}
