package W2;

public class Primes {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please, run the program with numbers separated with spaces as arguments.\n e.g. java Primes 100 256");
            return;
        }
        for (String arg : args) {
            long x = Long.valueOf(arg);
            long[] factors = LiczbyPierwsze.naCzynnikPierwsze(x);
            System.out.print(x + "=");
            for (int i = 0; i < factors.length; i++) {
                System.out.print(factors[i]);
                if (i + 1 < factors.length)
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}
