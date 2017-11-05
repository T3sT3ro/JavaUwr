package W4;

import W4.wyrazenia.Wyrazenie;
import W4.wyrazenia.Zmienna;
import W4.wyrazenia.unary.Abs;
import W4.wyrazenia.unary.Odwrotnosc;

public class Test {
    public static void main(String args[]) {

        Zmienna.set("a", 1);
        Zmienna.set("b", 2);
        Zmienna.set("c", 7);

        Wyrazenie w1 = new binary.Dodawanie(
                new Abs(
                        new binary.Dodawanie(
                                new Zmienna("a"),
                                new Odwrotnosc(
                                        new Zmienna("c")
                                )
                        )
                ),
                new Zmienna("b")
        );

        System.out.println(w1.oblicz());
        System.out.println(w1.toString());
    }
}
