package tcm.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Leo on 18/1/11.
 */
public class StringUtils {

    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isInt(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }

        return str.matches("[0-9]+");
    }

    public static String getQuarterByMonth(String month) {
        if ("".equals(month) || month == null)
            return null;
        Integer mon = Integer.parseInt(month);
        if (mon.compareTo(0) > 0 && mon.compareTo(4) < 0)
            return "1";
        else if (mon.compareTo(3) > 0 && mon.compareTo(7) < 0)
            return "2";
        else if (mon.compareTo(6) > 0 && mon.compareTo(10) < 0)
            return "3";
        else if (mon.compareTo(9) > 0 && mon.compareTo(13) < 0)
            return "4";
        else
            return null;
    }

    public static void main(String args[]) {
        Double dd = 0.0;
        String str = DecimalFormat.getNumberInstance().format(dd);
        System.out.println(str);
        String currecy = NumberFormat.getCurrencyInstance().format(1245600000);
        System.out.println(currecy);
    }

    public static boolean checkPastMonth(String dateStr, int pDay) {
        /*在 day 号之前属于过去月份*/
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
        try {
            Date pDate = sd.parse(dateStr);
            String currStr = sd.format(new Date());

            Calendar cale = Calendar.getInstance();
            int dateInt = cale.get(Calendar.DATE);
            if (pDay != 0 & dateInt > pDay) {
                cale.add(Calendar.MONTH, 1);
                currStr = sd.format(cale.getTime());
            }
            Date currDate = sd.parse(currStr);
            if (pDate.before(currDate))
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static int getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.get(cal.DATE);
    }

    public static String getValueFromStr(String str, String key) {
        String findKey = "#" + key + ":";
        String tmpStr;
        if (str.indexOf(findKey) >= 0) {
            tmpStr = str.substring(str.indexOf(findKey) + 1);
            tmpStr = tmpStr.substring(tmpStr.indexOf(":") + 1, tmpStr.indexOf("#"));
            return tmpStr;
        } else {
            return null;
        }
    }


    public static String appendString(String sourceStr, int length, String direction, String appendText) {
        String result = sourceStr;
        if ("R".equals(direction)) {
            for (int i = 0; i < length - sourceStr.length(); i++) {
                result = result + appendText;
            }
        } else {
            for (int i = 0; i < length - sourceStr.length(); i++) {
                result = appendText + result;
            }
        }
        return result;
    }
}
