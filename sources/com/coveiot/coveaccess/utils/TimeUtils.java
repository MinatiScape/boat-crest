package com.coveiot.coveaccess.utils;

import com.coveiot.utils.utility.UtilConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/* loaded from: classes8.dex */
public class TimeUtils {
    public static String convertFromUTCToLocalTime(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = simpleDateFormat.parse(str);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat2.format(parse);
    }

    public static String convertFromUTCToLocalTimeStamp(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = simpleDateFormat.parse(str);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat2.parse(simpleDateFormat2.format(parse)).getTime() + "";
    }

    public static String covertISOLocalTimeToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT).parse(str).getTime()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String covertISOLocalTimeToTimeStamp(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
            return simpleDateFormat.parse(str).getTime() + "";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String covertTimeStamptoISOLocalTime(String str) {
        try {
            return new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT).format(new Date(Long.parseLong(str)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String covertTimeStamptoServerFormat(String str) {
        try {
            long parseLong = Long.parseLong(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat.format(new Date(parseLong));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
