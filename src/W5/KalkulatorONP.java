package W5;

import W5.narzedzia.Para;
import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.Wyrazenie;
import W5.wyrazenia.Zmienna;
import W5.wyrazenia.wyjatki.WyjatekONP;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.*;

/**
 * Klasa interaktywnego kalkulatora ONP
 * dostepne funkcje:
 * = + - * / ^ min max log pow abs sgn floor ceil frac sin cos atan acot ln exp PI E
 * oraz obsluga zmiennych i stalych
 */
public class KalkulatorONP {

    private static final Logger logger = Logger.getLogger(KalkulatorONP.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Handler fileHandler = new FileHandler("calc.log", true);
            fileHandler.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("[program started]");

        System.out.println("Kalkulator ONP by Tooster");
        System.out.println("dostepne funkcje:");
        System.out.println("+ - * / ^ min max log pow abs sgn floor ceil frac sin cos atan acot ln exp PI E");
        System.out.println("wprowadzanie stalych przez wpisanie wartosci");
        System.out.println("wprowadzanie zmiennych przez przypisanie r x = to inczej x := r");

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            if (command.length <= 0)
                continue;
            assert (command[0].matches("calc") ||
                    command[0].matches("clear") ||
                    command[0].matches("exit"));

            if (command[0].matches("exit")) {
                break;
            } else if (command[0].matches("clear")) {
                if (command.length == 1)
                    Zmienna.getGlobals().clear();
                else {
                    for (int i = 1; i < command.length; i++) {
                        try {
                            Para<String, Double> found = Zmienna.getGlobals().find(new Para<>(command[i], 0D));
                            if (found != null) {
                                logger.info("usunieto zmienna '" + command[i] + "'");
                                Zmienna.getGlobals().remove(found);
                            }
                        } catch (WyjatekKontenera e) {
                            logger.warning("proba usuniecia nieistniejacej zmiennej '" + command[i] + "'");
                        }
                    }

                }
            } else if (command[0].matches("calc")) {
                if (command.length < 2) {
                    logger.warning("proba obliczenia pustego wyrazenia");
                    continue;
                }
                try {
                    logger.info("obliczanie wyrazenia '" + input.substring(5) + "'");
                    Wyrazenie w = new Wyrazenie(input.substring(5));
                    logger.info("wynik wyrazenia = " + w.obliczWartosc());
                } catch (WyjatekONP wyjatekONP) {
                    logger.warning("Blad obliczania wyrazenia: " + wyjatekONP.getMessage());
                    wyjatekONP.printStackTrace();
                }
            }
        }

        logger.info("[program ended]");
    }
}

