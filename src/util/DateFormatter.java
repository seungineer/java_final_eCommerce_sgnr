package util;

public class DateFormatter {
    public static String koreanDate(String rawDate) {
        if (rawDate == null || rawDate.length() != 8) {
            return "날짜 형식 오류";
        }

        String year = rawDate.substring(0, 4);
        String month = rawDate.substring(4, 6);
        String day = rawDate.substring(6, 8);

        return String.format("%s년 %d월 %d일", year, Integer.parseInt(month), Integer.parseInt(day));
    }
}
