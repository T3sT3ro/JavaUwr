package W5.wyrazenia;

import W5.narzedzia.Lista;
import W5.narzedzia.Para;
import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.wyjatki.ONP_NieznanySymbol;
import W5.wyrazenia.wyjatki.WyjatekONP;

public class Zmienna extends Operand {

    private static final Lista<Para<String, Double>> globals;
    public static boolean UNASIGNED_FLAG = false;
    public static String PENDING_NAME = null;

    static {
        globals = new Lista<>();
    }

    private final String name;

    public Zmienna(String name) {
        this.name = name;
    }

    public static Lista<Para<String, Double>> getGlobals() {
        return globals;
    }

    public static void ustaw(String key, Double val) throws WyjatekKontenera, WyjatekONP {
        sprawdz(val);
        Para<String, Double> found = globals.find(new Para<>(key, val));
        if (found == null)
            globals.addLast(new Para<>(key, val));
        else
            found.setValue(val);
    }

    public void ustaw(Double val) throws WyjatekKontenera, WyjatekONP {
        Zmienna.ustaw(name, val);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public double obliczWartosc() throws WyjatekONP, WyjatekKontenera {
        Para<String, Double> found = globals.find(new Para<>(name, 0D));
        //mechanizm wymagany do przypisania, takie podłożenie sztucznej wartości
        if (found == null) {
            if (UNASIGNED_FLAG) { // poprzednio wystapila nieprzypisana zmienna
                UNASIGNED_FLAG = false;
                PENDING_NAME = null;
                throw new ONP_NieznanySymbol("zmienna '" + name + "' nie zostala zdefiniowana");
            }
            UNASIGNED_FLAG = true;
            PENDING_NAME = name;
            return 0D;

        }
        UNASIGNED_FLAG = false;
        PENDING_NAME = name;
        return found.getValue();
    }
}
