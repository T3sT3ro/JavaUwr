package W5.wyrazenia;

import W5.narzedzia.Kolejka;
import W5.narzedzia.Lista;
import W5.narzedzia.Para;
import W5.narzedzia.Stos;
import W5.narzedzia.wyjatki.Kontener_PustyKontener;
import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.funkcje.Binary;
import W5.wyrazenia.funkcje.Unary;
import W5.wyrazenia.wyjatki.ONP_BledneWyrazenie;
import W5.wyrazenia.wyjatki.ONP_PustyStos;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Wyrazenie ONP
 */
public class Wyrazenie implements Obliczalny {
    final String wyrazenie;

    private final Kolejka<Symbol> symbole;
    private final Stos<Double> wyniki;
    private final Lista<Para<String, Double>> zmienne;

    public Wyrazenie(String wyrazenie) throws WyjatekONP {

        this.wyrazenie = wyrazenie;

        symbole = new Kolejka<>();
        wyniki = new Stos<>();
        this.zmienne = Zmienna.getGlobals();

        String[] tokens = wyrazenie.split(" ");

        Zmienna.UNASIGNED_FLAG = false;
        Zmienna.PENDING_NAME = null;

        for (String token : tokens) {
            try {
                symbole.add(Symbol.getSymbol(token));
            } catch (WyjatekKontenera e) {
                e.printStackTrace();
                return;
            }
        }

    }

    @Override
    public double obliczWartosc() throws WyjatekONP {

        try {
            while (!symbole.isEmpty()) {
                Symbol x = symbole.pop();
                if (x instanceof Funkcja) {
                    while (((Funkcja) x).brakujaceArgumenty() > 0) {
                        try {
                            double arg = wyniki.pop();
                            if (x instanceof Unary)
                                ((Unary) x).dodajArgument(arg);
                            if (x instanceof Binary)
                                ((Binary) x).dodajArgument(arg);
                        } catch (Kontener_PustyKontener e) {
                            throw new ONP_PustyStos("stos jest pusty, brakuje argumentow do funkcji");
                        }
                    }
                }
                if (x instanceof Przypisz) {
                    if (Zmienna.PENDING_NAME == null)
                        throw new ONP_BledneWyrazenie("zadna zmienna nie oczekuje na przypisanie");
                    try {
                        wyniki.remove(); //zdejmuje dummy
                        double rhs = wyniki.pop(); // bierze rhs
                        ((Przypisz) x).setRhs(rhs);
                        ((Przypisz) x).setLhs(new Zmienna(Zmienna.PENDING_NAME)); // albo nowa, ale istniejąca zmienna
                        Zmienna.UNASIGNED_FLAG = false;
                        Zmienna.PENDING_NAME = null;
                    } catch (Kontener_PustyKontener e) {
                        throw new ONP_PustyStos("stos jest pusty, brakuje argumentow do przypisania");
                    }
                }
                try {
                    if (Zmienna.UNASIGNED_FLAG) { // jesli mamy oczekujaca na przypisanie zmienna
                        throw new ONP_BledneWyrazenie("zmienna niezainicjalizowana");
                    }
                    wyniki.add(x.obliczWartosc());
                } catch (WyjatekKontenera e) {
                    throw new ONP_BledneWyrazenie("nie mozna dodac wyniku do stosu: " + e.getMessage()) {
                        @Override
                        public void setStackTrace(StackTraceElement[] stackTrace) {
                            super.setStackTrace(e.getStackTrace());
                        }
                    };
                }
            }
            if (wyniki.size() > 1 || symbole.size() > 0 || Zmienna.UNASIGNED_FLAG) {
                if (Zmienna.UNASIGNED_FLAG)
                    throw new ONP_BledneWyrazenie("zmienna niezainicjalizowana");
                throw new ONP_BledneWyrazenie("wyrazenie jest zle sformulowane");
            }
            return wyniki.pop();
        } catch (WyjatekKontenera e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }


}
