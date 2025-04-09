package util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatPrice {
    public static String koreaWon(int price) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(price);
    }
}
