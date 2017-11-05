package W2;

import java.util.Arrays;

import static java.lang.Math.sqrt;

public final class LiczbyPierwsze {

    private static final int POTEGA2 = 21;
    private static final int[] SITO = new int[1 << POTEGA2];
    private static final long LIMIT = 1 << 21;

    static {
        SITO[1] = 1;
        for (long i = 2; i < LIMIT; i++)
            for (long j = i; j < LIMIT; j += i)
                if (SITO[(int) (j)] == 0)
                    SITO[(int) (j)] = (int) i;
    }

    /**
     * Check primality of x
     *
     * @param x positive number
     * @return true if x is prime, false otherwise
     */
    public static boolean czyPierwsza(long x) {
        if (x < 2)
            return false;
        if (x < LIMIT)
            return (SITO[(int) x] == x);
        else {
            if (x % 2 == 0) return false;
            for (long i = 3; i <= (long) sqrt(x) + 1; i += 2)
                if (x % i == 0)
                    return false;
        }
        return true;
    }

    /**
     * Returns and array of prime factors of x.
     *
     * @param x number in range [LONG_MIN, LONG_MAX]
     * @return array of prime factors of x
     */
    public static long[] naCzynnikPierwsze(long x) {
        long[] factors = new long[64]; // max number of prime factors is 64: LONG_MIN = -1*2^63
        int it = 0; // iterator and indicator of number of prime factors
        // two special cases:
        if (x == -1 || x == 0 || x == 1) { // -1,0,1
            factors[it++] = x;
            return Arrays.copyOfRange(factors, 0, it);
        } else if (x == Long.MIN_VALUE) { // long.min handled separately: long.min*(-1) cannot be converted to positive
            factors[it++] = -1;
            while (x < -1) {
                factors[it++] = 2;
                x /= 2;
            }
            return Arrays.copyOfRange(factors, 0, it);
        }

        if (x < 0) { // convert negatives to positives
            factors[it++] = -1;
            x *= -1;
        }

        if (czyPierwsza(x)) { // if prime return it
            factors[it++] = x;
            return Arrays.copyOfRange(factors, 0, it);
        }

        // factorization
        if (x < LIMIT) { // case: x < LIMIT
            while (x > 1) {
                factors[it++] = SITO[(int) x];
                x /= SITO[(int) x];
            }
        } else {
            while (x % 2 == 0) { // speeds up whole thing 2 times :D
                factors[it++] = 2;
                x /= 2;
            }
            for (long i = 3; x > 1 && i <= (long) sqrt(x) + 1; i += 2) {
                while (
                        (i < LIMIT && SITO[(int) i] == i && x % i == 0) ||
                                (i > LIMIT && x % i == 0)
                        ) {
                    factors[it++] = i;
                    x /= i;
                }
            }
        }
        if (x > 1)
            factors[it++] = x;
        return Arrays.copyOfRange(factors, 0, it);
    }
}

