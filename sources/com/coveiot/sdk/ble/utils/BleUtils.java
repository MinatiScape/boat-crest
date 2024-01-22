package com.coveiot.sdk.ble.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.coveiot.sdk.ble.R;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.NotificationChannelInfo;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.libcomx.logger.RingLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.codec.CharEncoding;
/* loaded from: classes9.dex */
public class BleUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Gson f7585a = new Gson();

    public static byte[] convertByteArrayListToByteArray(ArrayList<Byte> arrayList) {
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bArr;
    }

    public static int convertDpToPx(Context context, int i) {
        return Math.round(i * (context.getResources().getDisplayMetrics().densityDpi / 160));
    }

    public static int convertHexTo565(String str) {
        Integer.parseInt(str.substring(1, 3), 16);
        Integer.parseInt(str.substring(3, 5), 16);
        Integer.parseInt(str.substring(5, 7), 16);
        int parseInt = Integer.parseInt(str.substring(1), 16);
        return ((parseInt & RingLogger.EVT_UPDATE) >> 3) | ((16252928 & parseInt) >> 8) | ((64512 & parseInt) >> 5);
    }

    public static String convertToCurrentTimeZone(String str) {
        try {
            Locale locale = Locale.ENGLISH;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT, locale);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parse = simpleDateFormat.parse(str);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM, hh:mm a", locale);
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone(getCurrentTimeZone()));
            return simpleDateFormat2.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int countSetBits(long j) {
        int i = 0;
        while (j > 0) {
            i = (int) (i + (1 & j));
            j >>= 1;
        }
        return i;
    }

    public static short crc16(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            int i2 = (((i << 8) | (i >>> 8)) & 65535) ^ (b & 255);
            int i3 = i2 ^ ((i2 & 255) >> 4);
            int i4 = i3 ^ ((i3 << 12) & 65535);
            i = i4 ^ (65535 & ((i4 & 255) << 5));
        }
        return (short) (i & 65535);
    }

    public static short dataFromRGB(byte b, byte b2, byte b3) {
        return (short) (((short) ((((char) (b & 248)) + ((char) (b2 >> 5))) * 256)) + ((short) (((char) ((b2 << 3) & 224)) + ((char) (b3 >> 3)))));
    }

    public static boolean doesExist(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.toLowerCase().contains(str2.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static int findDateDifference(Date date, Date date2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        long time = simpleDateFormat.parse(simpleDateFormat.format(date2)).getTime() - simpleDateFormat.parse(simpleDateFormat.format(date)).getTime();
        long j = (time / 1000) % 60;
        long j2 = (time / com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS) % 60;
        return (int) ((time / 3600000) / 24);
    }

    public static int getCalories(int i, Context context) {
        int i2 = 165;
        int i3 = 55;
        try {
            int intValue = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_WEIGHT, 55)).intValue();
            i2 = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_HEIGHT, 165)).intValue();
            i3 = intValue;
        } catch (Exception unused) {
        }
        try {
            return Integer.parseInt(String.format(Locale.ENGLISH, "%.0f", Double.valueOf(Math.floor(((float) ((i * i3) / (160934.0d / (i2 * 0.413d)))) * 0.9f))));
        } catch (NumberFormatException unused2) {
            return 0;
        }
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap createBitmap;
        int width;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            createBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (bitmap.getWidth() > bitmap.getHeight()) {
            width = bitmap.getHeight() / 2;
        } else {
            width = bitmap.getWidth() / 2;
        }
        float f = width;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static ConnectionType getConnectionType(Context context) {
        ConnectionType connectionType = ConnectionType.RECONNECT_ON_DISCONNECT;
        String str = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_CONNECTION_TYPE, connectionType.name());
        return !isEmpty(str) ? (ConnectionType) new GsonBuilder().registerTypeAdapterFactory(new LowercaseEnumTypeAdapterFactory()).create().fromJson(str.toLowerCase(), (Class<Object>) ConnectionType.class) : connectionType;
    }

    public static String getCurrentTimeZone() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        System.out.println(timeZone.getDisplayName());
        return timeZone.getID();
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static long getDateDiff(Date date, Date date2) {
        long time = date2.getTime() - date.getTime();
        PrintStream printStream = System.out;
        printStream.println("startDate : " + date);
        PrintStream printStream2 = System.out;
        printStream2.println("endDate : " + date2);
        PrintStream printStream3 = System.out;
        printStream3.println("different : " + time);
        long j = time / com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS;
        long j2 = (time % com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS) / 1000;
        return j;
    }

    public static String getDateDifference(Date date, Date date2) {
        long time = date2.getTime() - date.getTime();
        PrintStream printStream = System.out;
        printStream.println("startDate : " + date);
        PrintStream printStream2 = System.out;
        printStream2.println("endDate : " + date2);
        PrintStream printStream3 = System.out;
        printStream3.println("different : " + time);
        long j = time / 86400000;
        long j2 = time % 86400000;
        long j3 = j2 / 3600000;
        long j4 = j2 % 3600000;
        long j5 = j4 / com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS;
        long j6 = (j4 % com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS) / 1000;
        if (j > 0) {
            return String.format(Locale.ENGLISH, "%d days, %d hours, %d min", Long.valueOf(j), Long.valueOf(j3), Long.valueOf(j5));
        }
        if (j3 > 0) {
            return String.format(Locale.ENGLISH, "%d hours, %d min", Long.valueOf(j3), Long.valueOf(j5));
        }
        return String.format(Locale.ENGLISH, "%d min", Long.valueOf(j5));
    }

    public static SimpleDateFormat getDateFormater(String str) {
        return new SimpleDateFormat(str, Locale.US);
    }

    public static String getDateInddMMMYY() {
        return new SimpleDateFormat("dd MMM yy", Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static String getDateTime() {
        return new SimpleDateFormat("dd-MMM, HH:mm", Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static List<Integer> getEachBit(int i) {
        ArrayList arrayList = new ArrayList();
        if (i > 0) {
            while (i > 0) {
                arrayList.add(Integer.valueOf(i & 1));
                i >>= 1;
            }
            while (arrayList.size() < 8) {
                arrayList.add(0);
            }
        } else {
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
        }
        System.out.println(arrayList.toString());
        return arrayList;
    }

    public static double getMeters(int i, int i2) {
        return (((int) (i2 * 0.413d)) * i) / 100.0d;
    }

    public static double getMeters(int i, Context context) {
        try {
            int intValue = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.USER_HEIGHT, 165)).intValue();
            int intValue2 = ((Integer) BlePreferenceManager.getPreference(context, CommonPreference.STRIDE_LENGTH, 0)).intValue();
            if (intValue2 == 0) {
                intValue2 = (int) (intValue * 0.413d);
            }
            return ((intValue2 * i) / 100.0d) / 1000.0d;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static NotificationChannelInfo getNotificationChanneInfo(Context context) {
        String str = (String) BlePreferenceManager.getPreference(context, CommonPreference.NOTIFICATION_CH_INFO, "");
        try {
            if (!isEmpty(str)) {
                return (NotificationChannelInfo) f7585a.fromJson(str, (Class<Object>) NotificationChannelInfo.class);
            }
            NotificationChannelInfo notificationChannelInfo = new NotificationChannelInfo();
            notificationChannelInfo.setAppName("Ble Sdk");
            notificationChannelInfo.setChannelDefaultId("1");
            notificationChannelInfo.setAppName("Ble Sdk");
            notificationChannelInfo.setDrawable(R.mipmap.ic_launcher);
            notificationChannelInfo.setDescription("Background service to keep your Ble Sdk connected to the app.");
            return notificationChannelInfo;
        } catch (Exception unused) {
            NotificationChannelInfo notificationChannelInfo2 = new NotificationChannelInfo();
            notificationChannelInfo2.setAppName("Ble Sdk");
            notificationChannelInfo2.setChannelDefaultId("1");
            notificationChannelInfo2.setAppName("Ble Sdk");
            notificationChannelInfo2.setDrawable(R.mipmap.ic_launcher);
            notificationChannelInfo2.setDescription("Background service to keep your Ble Sdk connected to the app.");
            return notificationChannelInfo2;
        }
    }

    public static String getYesterdayDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(Calendar.getInstance().getTime().getTime() - 86400000));
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 8) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static void hideKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isFutureday(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).before(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean isStartDateGreaterThanEnd(String str, String str2) {
        try {
            return findDateDifference(str2, str) >= 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isToday(String str) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date()).equalsIgnoreCase(str);
    }

    public static Boolean parseBoolean(String str, Boolean bool) {
        if (str == null) {
            return bool;
        }
        try {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        } catch (NumberFormatException unused) {
            return bool;
        }
    }

    public static float parseFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static int parseInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long parseLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static void setHtmlText(TextView textView, String str) {
        textView.setText(Html.fromHtml(str));
    }

    public static void setNotificationChannelInfo(Context context, NotificationChannelInfo notificationChannelInfo) {
        BlePreferenceManager.savePreference(context, CommonPreference.NOTIFICATION_CH_INFO, f7585a.toJson(notificationChannelInfo));
    }

    public static byte[] stringToUnicode(String str) {
        try {
            return str.getBytes(CharEncoding.UTF_16LE);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static boolean isEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isStartDateGreaterThanEnd(Date date, Date date2) {
        try {
            return findDateDifference(date2, date) >= 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isToday(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return simpleDateFormat.format(new Date()).equalsIgnoreCase(simpleDateFormat.format(date));
    }

    public static String getDate(long j, String str) {
        return AppUtils.formatDate(new Date(j), str);
    }

    public static boolean isFutureday(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).before(simpleDateFormat.parse(simpleDateFormat.format(date)));
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static int getCalories(int i, int i2, int i3) {
        try {
            return Integer.parseInt(String.format(Locale.ENGLISH, "%.0f", Double.valueOf(Math.floor(((float) ((i * i3) / (160934.0d / (i2 * 0.413d)))) * 0.9f))));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static ConnectionType getConnectionType(Context context, String str) {
        return !isEmpty(str) ? (ConnectionType) new GsonBuilder().registerTypeAdapterFactory(new LowercaseEnumTypeAdapterFactory()).create().fromJson(str.toLowerCase(), (Class<Object>) ConnectionType.class) : ConnectionType.RECONNECT_ON_DISCONNECT;
    }

    public static int findDateDifference(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        long time = simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
        long j = (time / 1000) % 60;
        long j2 = (time / com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS) % 60;
        return (int) ((time / 3600000) / 24);
    }
}
