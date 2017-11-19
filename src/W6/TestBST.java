package W6;

import W6.algorithms.BST;

public class TestBST {

    private static BST<Integer> t = new BST<>();

    static void add(int val) {
        t.insert(val);
        System.out.println(t.toString());
    }

    static void rem(int val) {
        t.remove(val);
        System.out.println(t.toString());
    }

    static void cl() {
        t.clear();
        System.out.println(t.toString());
    }

    static void is(int val) {
        System.out.println(val + ": " + t.search(val));
    }

    public static void main(String[] args) {
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
        cl();
    }
}
