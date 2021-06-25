import java.util.Locale;

public class Roman {
    private static final Integer[] arabicValues = {0, 1, 4, 5, 9, 10, 40, 50, 90, 100};
    private static final String[] romanValues = {"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};

    private static boolean check(String roman) {
        for (Character ch : roman.toCharArray()) {
            ch = Character.toUpperCase(ch);
            if (ch != 'L' && ch != 'C' && ch != 'V' && ch != 'X' && ch != 'I') {
                return false;
            }
        }
        return true;
    }

    private final static int findMinValue(final int number, int firstIndex, int lastIndex) {
        if (firstIndex == lastIndex) {
            return firstIndex;
        }
        if (arabicValues[firstIndex] == number) {
            return firstIndex;
        }
        if (arabicValues[lastIndex] == number) {
            return lastIndex;
        }
        final int median = (lastIndex + firstIndex) / 2;
        if (median == firstIndex) {
            return firstIndex;
        }
        if (number == arabicValues[median]) {
            return median;
        }
        if (number > arabicValues[median]) {
            return findMinValue(number, median, lastIndex);
        } else {
            return findMinValue(number, firstIndex, median);
        }
    }

    public final static String arabicToRoman(final int number) {
        int minIndex = findMinValue(number, 0, 9);
        if (number == arabicValues[minIndex])
            return romanValues[minIndex];
        return romanValues[minIndex] + arabicToRoman(number - arabicValues[minIndex]);
    }

    public static int romanToArabic(String roman) {

        if (!check(roman)) {
            throw new IllegalArgumentException("Таких символов нет в римских цифрах, либо выражение ушло за пределы допустимых значений");
        }
        roman = roman.toUpperCase();
        int result = 0;

        for (int i = arabicValues.length - 1; i >= 0; i--) {
            while (roman.indexOf(romanValues[i]) == 0 && romanValues[i].length() > 0) {
                result += arabicValues[i];
                roman = roman.substring(romanValues[i].length());
            }
        }
        return result;
    }
}