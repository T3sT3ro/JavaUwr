package W4;

import W4.wyrazenia.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa testujaca pakiet wyrazenia
 */
public class Test {
    public static void main(String args[]) {

        Zmienna.set("a", 1);
        Zmienna.set("b", 2);
        Zmienna.set("c", 7);
        Zmienna.set("x", -1.618);
        Zmienna.set("y", 23);

        List<Wyrazenie> W = new ArrayList<>();

        W.add(new Dodawanie(
                new Stala(3),
                new Stala(5)
        ));

        W.add(new Dodawanie(
                new Stala(2),
                new Mnozenie(
                        new Zmienna("x"),
                        new Stala(7)
                )
        ));

        W.add(new Dzielenie(
                new Odejmowanie(
                        new Mnozenie(
                                new Stala(3),
                                new Stala(7)
                        ),
                        new Stala(1)

                ),
                new Dodawanie(
                        new Stala(7),
                        new Stala(5)
                )
        ));

        W.add(new Dzielenie(
                new Arctan(
                        new Mnozenie(
                                new Dodawanie(
                                        new Zmienna("x"),
                                        new Stala(13)
                                ),
                                new Zmienna("x")
                        )
                ),
                new Stala(2)
        ));

        W.add(new Dodawanie(
                new Potegowanie(
                        new Stala(2),
                        new Stala(5)
                ),
                new Mnozenie(
                        new Zmienna("x"),
                        new Logarytm(
                                new Stala(2),
                                new Zmienna("y")
                        )
                )
        ));

        for (Wyrazenie w : W) {
            System.out.println(w.oblicz());
            System.out.println(w.toString());
            System.out.println("===============");
        }

        System.out.println(new Dodawanie(
                new Zmienna("x"),
                new Zmienna("y")
        ).oblicz());

        Zmienna.set("x", 7);
        Zmienna.set("y", 200);

        System.out.println(new Dodawanie(
                new Zmienna("x"),
                new Zmienna("y")
        ).oblicz());
    }
}
