package W2;

import java.util.Arrays;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public final class LiczbyPierwsze {

    private final static int POTEGA2 = 21;
    private final static int[] SITO = new int[1 << POTEGA2];
    private final static long LIMIT = 1 << 21;
    private final static long BRUTE_LIMIT = 1000000009;

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
            for (long i = 2; i <= (long) sqrt(x) + 1; i++)
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
        long[] factors = new long[70]; // max number of prime factors is 65: LONG_MIN = -1*2^64
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

        // factorization
        if (x < 0) { // convert negatives to positives
            factors[it++] = -1;
            x *= -1;
        }


        if (x < LIMIT) { // case: x < LIMIT
            while (x > 1) {
                factors[it++] = SITO[(int) x];
                x /= SITO[(int) x];
            }
        } else if (x > LIMIT) { // case: x > LIMIT and has prime factors in range [0, LIMIT)
            for (long i = 2; x > 1 && i < min(BRUTE_LIMIT, (long) sqrt(x)); i++) {
                while ((i < LIMIT && SITO[(int) i] == i && x % i == 0) || (i > LIMIT && x % i == 0)) {
                    factors[it++] = i;
                    x /= i;
                }
            }
        }

        if (czyPierwsza(x)) { // if prime return it
            factors[it++] = x;
            return Arrays.copyOfRange(factors, 0, it);
        }

        if (x > LIMIT) { // case: x > LIMIT and has prime factors near sqrt(x)
            long i = (long) sqrt(x), j = i;
            if ((i + 1) * (i + 1) == x) { // for assumption that (double)sqrt(x) is slightly smaller than real sqrt
                i++;
                j++;
            }
            while (i * j != x) { // works also for i = exact sqrt(x)
                if (i * j > x || i * j < 0)// if bigger or overshot on negatives
                    i--;
                else
                    j++;
                if (i <= 0 || j > x || j < 0)
                    break;
            }
            if (i <= 1 || j >= x || j < 0) { // so x is prime after all...
                factors[it++] = x;
            } else { // and here it is a product of two big primes
                factors[it++] = i;
                factors[it++] = j;
            }
        }
        return Arrays.copyOfRange(factors, 0, it);
    }
}

