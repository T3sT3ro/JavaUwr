package w1;

/**
 * Program converts arabic numbers from range 1-4999 to roman numbers
 *
 * @author Tooster
 */
public class Roman {

    private static String[] rzymskie = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static int[] arabskie = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /**
     * convert arabic number to roman number
     *
     * @param number number to be converted
     * @return string representing number in roman system
     */
    public static String toRoman(String number) {

        int x = new Integer(number);
        if (x <= 0 || x >= 5000)
            throw new IllegalArgumentException("liczba " + x + " spoza zakresu 1-4999");

        String xRoman = "";
        int i = 0;
        while (x > 0 && i < arabskie.length) {
            if (arabskie[i] <= x) {
                xRoman += rzymskie[i];
                x -= arabskie[i];
            } else
                i++;
        }
        return xRoman;
    }

    public static void main(String[] args) {
        for (String number : args) {
            System.out.println(number + " " + toRoman(number));
        }
    }
}
