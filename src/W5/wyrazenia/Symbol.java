package W5.wyrazenia;

import W5.wyrazenia.funkcje.*;
import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.ONP_NieznanySymbol;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Klasa abstrakcyjan reprezentujaca symbol
 */
public abstract class Symbol implements Obliczalny {

    /**
     * sprawdza czy podany argument jest poprawny, czyli czy nie jest NaN lub +/-Infinity
     *
     * @param arg wartosc do sprawdzenia
     * @throws WyjatekONP jesli argument jest NaN lub +/-Infinity
     */
    public static void sprawdz(double arg) throws WyjatekONP {
        if (Double.isNaN(arg) || Double.isInfinite(arg))
            throw new ONP_BladArgumentu("argument poza zakresem typu double");
    }

    /**
     * Rozpoznaje i zwraca nowa funkcje z podanego napisu
     * @param token napis do przetworzenia na obiekt
     * @return nowy obiekt rownoznaczny z tokenem
     * @throws WyjatekONP jesli podano nieznany symbol
     */
    public static Symbol getSymbol(String token) throws WyjatekONP {
        switch (token) {
            case "=":
                return new Przypisz();
            case "abs":
                return new Abs();
            case "acot":
                return new Acot();
            case "+":
                return new Add();
            case "atan":
                return new Atan();
            case "ceil":
                return new Ceil();
            case "cos":
                return new Cos();
            case "/":
                return new Div();
            case "E":
                return new E();
            case "exp":
                return new Exp();
            case "floor":
                return new Floor();
            case "frac":
                return new Frac();
            case "ln":
                return new Ln();
            case "log":
                return new Log();
            case "max":
                return new Max();
            case "min":
                return new Min();
            case "*":
                return new Mult();
            case "PI":
                return new PI();
            case "^":
                return new Pow();
            case "sgn":
                return new Sgn();
            case "sin":
                return new Sin();
            case "-":
                return new Sub();
            default:
                if (token.matches("\\p{Alpha}\\p{Alnum}*"))
                    return new Zmienna(token);
                if (token.matches("^-?\\d*\\.?\\d+$"))
                    return new Liczba(Double.valueOf(token));
                throw new ONP_NieznanySymbol("nieznany symbol: '" + token + "'");
        }
    }

    public abstract String toString();
}
