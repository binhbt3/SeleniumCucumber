package utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//final -> We do not want any class to extend this class
public final class DateUtils {

    private DateUtils() {
        super();
    }

    /**
     * @return lấy ra ngày tháng năm hiện tại của máy theo cấu trúc mặc định
     */
    public static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }

    /**
     * @return lấy ra ngày tháng năm hiện tại của máy theo cấu trúc dd/MM/yyyy
     */
    public static String getCurrentDatecustomize(String pattern) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(now);
    }

    /**
     * @return lấy ra ngày tháng năm increment n day của máy theo cấu trúc dd/MM/yyyy
     */
    public static String getIncrementDateFromCurrentcustomize(String date, String pattern, int days) {
        try {
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(date));
            calendar.add(Calendar.DATE, days);
            return formatter.format(calendar.getTime());
        } catch (Exception e){
            LogUtils.info(e.getMessage());
            return null;
        }
    }

    /**
     * @return lấy ra ngày tháng năm và giờ phút giây hiện tại của máy theo cấu trúc dd/MM/yyyy HH:mm:ss
     */
    public static String getCurrentDateTime() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(now);
    }

    public static String getCurrentDateTimeCustom(String separator_Character) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String timeStamp = formatter.format(now).replace("/", separator_Character);
        timeStamp = timeStamp.replace(" ", separator_Character);
        timeStamp = timeStamp.replace(":", separator_Character);
        return timeStamp;
    }

}

