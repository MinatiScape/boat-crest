package com.coveiot.utils.utility;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.os.ConfigurationCompat;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.PhoneNumberParseException;
import com.coveiot.utils.R;
import com.coveiot.utils.model.CountryCodeModel;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.phonevaildation.model.PhoneNoValidationState;
import com.coveiot.utils.utility.phonevaildation.model.PhoneValidationResponse;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.jstyle.blesdk1860.constant.BleConst;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes9.dex */
public class AppUtils {

    /* renamed from: a  reason: collision with root package name */
    public static String[] f7623a = null;
    public static boolean isBuddies = false;

    public static String a(@NonNull Context context, @NonNull String str, @NonNull String str2) throws PhoneNumberParseException {
        if (!isEmpty(str2) && !isEmpty(str)) {
            try {
                String c = c(retrieveCountryCodesFromIsd(context, str), str, str2, context);
                if (c != null) {
                    return c;
                }
                throw new PhoneNumberParseException(PhoneNoValidationState.INVALID_PHONE_NO);
            } catch (Exception unused) {
                throw new PhoneNumberParseException(PhoneNoValidationState.INVALID_PHONE_NO);
            }
        }
        throw new PhoneNumberParseException(PhoneNoValidationState.INVALID_PHONE_NO);
    }

    public static String b(String str) {
        return str.replaceAll("\\s+", "").trim();
    }

    public static String c(@NonNull ArrayList<String> arrayList, @NonNull String str, @NonNull String str2, Context context) throws PhoneNumberParseException {
        String str3 = b(str) + b(str2);
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_un_code);
        String[] stringArray2 = context.getResources().getStringArray(R.array.country_isd_iso_code);
        Iterator<String> it = arrayList.iterator();
        String str4 = "";
        boolean z = false;
        while (it.hasNext()) {
            String next = it.next();
            try {
                if (next.length() > 2) {
                    for (int i = 0; i < stringArray.length; i++) {
                        if (next.equals(stringArray[i].split(Constants.SEPARATOR_COMMA)[1])) {
                            str4 = stringArray[i].split(Constants.SEPARATOR_COMMA)[0];
                        }
                    }
                    for (int i2 = 0; i2 < stringArray2.length; i2++) {
                        if (str4.equals(stringArray2[i2].split(Constants.SEPARATOR_COMMA)[0])) {
                            next = stringArray2[i2].split(Constants.SEPARATOR_COMMA)[1];
                        }
                    }
                    Phonenumber.PhoneNumber parse = phoneNumberUtil.parse(str2, next);
                    str3 = "+" + parse.getCountryCode() + parse.getNationalNumber();
                } else {
                    Phonenumber.PhoneNumber parse2 = phoneNumberUtil.parse(str2, next);
                    str3 = "+" + parse2.getCountryCode() + parse2.getNationalNumber();
                }
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (z) {
            return str3;
        }
        LogHelper.d("ContentValues", "None of the numbers/country combo returned valid phone number");
        throw new PhoneNumberParseException(PhoneNoValidationState.INVALID_PHONE_NO);
    }

    public static double calculateCalories(int i, int i2, int i3) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", Float.valueOf((float) ((i * ((i3 * 0.7d) * 2.20462d)) / (160934.0d / (i2 * 0.413d))))));
    }

    public static int calculateDistance(int i, int i2) {
        return (((int) (i2 * 0.413d)) * i) / 100;
    }

    public static int calculateDistanceInCentimeters(int i, int i2) {
        return ((int) (i2 * 0.413d)) * i;
    }

    public static int calculateDistanceInCentimeters(int i, int i2, int i3) {
        if (i3 <= 0) {
            i3 = (int) (i2 * 0.413d);
        }
        return i3 * i;
    }

    public static int calculateDistanceInCentimeters(int i, int i2, String str) {
        double d = i2;
        int i3 = (int) (d * 0.413d);
        if (str.equalsIgnoreCase(CoveApiConstants.RUN)) {
            i3 = (int) (d * 1.3d * 0.413d);
        }
        return i3 * i;
    }

    public static float calucatePace(int i, int i2) {
        return 0.0f;
    }

    public static int caluclateRunningStrideLenght(int i) {
        return (int) (i * 1.3d * 0.413d);
    }

    public static int caluclateWalkingStrideLenght(int i) {
        return (int) (i * 0.413d);
    }

    public static boolean checkIfLocationPermissionExists(Context context) {
        return PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}).length == 0;
    }

    public static String convertCmInches(double d) {
        return String.valueOf((int) (d * 0.3937d));
    }

    public static String convertCmToFeetAndInches(double d) {
        double d2 = d * 0.3937d;
        return ((int) (d2 / 12.0d)) + "' " + ((int) Math.round(d2 % 12.0d)) + "''";
    }

    public static String convertKgToPounds(double d) {
        return String.format("%d", Integer.valueOf((int) Math.round(d * 2.2046d)), Locale.ENGLISH);
    }

    public static double convertPoundsToKg(double d) {
        return d * 0.45359237d;
    }

    public static String convertStringToMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = BleConst.GetDeviceTime + hexString;
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int[] covertMinutesToHours(int i) {
        int[] iArr = new int[2];
        if (i > 0) {
            iArr[0] = i / 60;
            iArr[1] = i % 60;
        }
        return iArr;
    }

    public static int findDateDifference(Date date, Date date2) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return (int) ((calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 86400000);
    }

    public static String formatDate(Date date, String str) {
        return getSimpleDateFormat(str).format(date);
    }

    public static Date formatDateToDate(Date date, String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(str);
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }

    public static String formatDateUTC(Date date, String str) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    public static String getAge(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.set(i, i2, i3);
        int i4 = calendar2.get(1) - calendar.get(1);
        if (calendar2.get(6) < calendar.get(6)) {
            i4--;
        }
        return String.valueOf(i4);
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (Build.VERSION.SDK_INT < 21) {
            drawable = DrawableCompat.wrap(drawable).mutate();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static String getCaloriesValue(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        return decimalFormat.format(d);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        int width;
        Bitmap bitmap2 = null;
        try {
            if (bitmap.getWidth() > bitmap.getHeight()) {
                bitmap2 = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            } else {
                bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(bitmap2);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap2;
    }

    public static ArrayList<CountryCodeModel> getCodeModelArrayList(Context context) {
        ArrayList<CountryCodeModel> arrayList = new ArrayList<>();
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_iso_code);
        for (int i = 0; i < stringArray.length; i++) {
            arrayList.add(new CountryCodeModel(ISDFormatUtils.getIsdCode(stringArray[i]), ISDFormatUtils.getTwoCharISOCode(stringArray[i])));
        }
        return arrayList;
    }

    public static ArrayList<CountryCodeModel> getCodeModelArrayListFromThreeDigits(Context context) {
        ArrayList<CountryCodeModel> arrayList = new ArrayList<>();
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_un_code);
        for (int i = 0; i < stringArray.length; i++) {
            arrayList.add(new CountryCodeModel(ISDFormatUtils.getIsdCode(stringArray[i]), ISDFormatUtils.getThreeCharUNCode(stringArray[i])));
        }
        return arrayList;
    }

    public static ArrayList<CoveContact> getContacts(Context context) {
        ArrayList<CoveContact> arrayList = new ArrayList<>();
        LogHelper.d("ContentValues", " Get Contacts Statrt");
        Cursor query = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, "display_name ASC");
        long j = 0;
        while (query.moveToNext()) {
            long j2 = j + 1;
            CoveContact coveContact = new CoveContact(query.getString(query.getColumnIndex("contact_id")), query.getString(query.getColumnIndex("display_name")), query.getString(query.getColumnIndex("data1")), j);
            if (!arrayList.contains(coveContact)) {
                arrayList.add(coveContact);
            }
            j = j2;
        }
        query.close();
        LogHelper.d("ContentValues", "Get Contacts" + arrayList.size());
        return arrayList;
    }

    public static String getFormattedPace(float f) {
        if (f == 0.0f) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        int i = (int) (f / 60.0f);
        int i2 = (int) (f % 60.0f);
        if (i > 99) {
            i = 99;
        }
        return i != 0 ? i != 99 ? String.format(Locale.ENGLISH, "%d'%d''", Integer.valueOf(i), Integer.valueOf(i2)) : "99'99''" : WorkoutConstants.EMPTY_PACE_VALUE;
    }

    public static String getPhoneBatteryLevel(Context context) {
        return String.valueOf(((BatteryManager) context.getSystemService("batterymanager")).getIntProperty(4));
    }

    public static SimpleDateFormat getSimpleDateFormat(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH);
    }

    public static CountryCodeModel getTargetCountryCodeModel(Context context, String str) {
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_iso_code);
        int indexOf = Arrays.asList(stringArray).indexOf(str);
        return new CountryCodeModel(ISDFormatUtils.getIsdCode(stringArray[indexOf]), ISDFormatUtils.getTwoCharISOCode(stringArray[indexOf]));
    }

    public static CountryCodeModel getTargetCountryCodeModelFromThreeDigits(Context context, String str) {
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_un_code);
        int indexOf = Arrays.asList(stringArray).indexOf(str);
        return new CountryCodeModel(ISDFormatUtils.getIsdCode(stringArray[indexOf]), ISDFormatUtils.getThreeCharUNCode(stringArray[indexOf]));
    }

    public static CountryCodeModel getTargetCountryCodeModelUsingLocale(Context context) {
        Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        String retrieveIsdFromCountryCode = retrieveIsdFromCountryCode(context, locale.getCountry());
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_iso_code);
        List asList = Arrays.asList(stringArray);
        int indexOf = asList.indexOf(retrieveIsdFromCountryCode + Constants.SEPARATOR_COMMA + locale.getCountry());
        return new CountryCodeModel(ISDFormatUtils.getIsdCode(stringArray[indexOf]), ISDFormatUtils.getTwoCharISOCode(stringArray[indexOf]));
    }

    public static HashMap<String, UNIT_TYPE> getUnitMapping(String str) {
        HashMap<String, UNIT_TYPE> hashMap = new HashMap<>();
        if (!str.equals("1") && !str.equals("95") && !str.equals("231")) {
            UNIT_TYPE unit_type = UNIT_TYPE.METRIC;
            hashMap.put("WEIGHT", unit_type);
            hashMap.put("HEIGHT", unit_type);
            hashMap.put("DISTANCE", unit_type);
            if (!str.equals("91") && !str.equals("880") && !str.equals("94") && !str.equals("977")) {
                hashMap.put("TEMPERATURE", unit_type);
            } else {
                hashMap.put("TEMPERATURE", UNIT_TYPE.IMPERIAL);
            }
        } else {
            UNIT_TYPE unit_type2 = UNIT_TYPE.IMPERIAL;
            hashMap.put("WEIGHT", unit_type2);
            hashMap.put("HEIGHT", unit_type2);
            hashMap.put("TEMPERATURE", unit_type2);
            hashMap.put("DISTANCE", unit_type2);
        }
        return hashMap;
    }

    public static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isBluetoothEnabled(Context context) {
        return ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter() != null && ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter().isEnabled();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean isGpsEnabled(@NonNull Context context) {
        boolean z;
        boolean z2;
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        try {
            z = locationManager.isProviderEnabled("gps");
        } catch (Exception unused) {
            z = false;
        }
        try {
            z2 = locationManager.isProviderEnabled("network");
        } catch (Exception unused2) {
            z2 = false;
        }
        return z || z2;
    }

    public static boolean isNetConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public static boolean isServiceRunning(String str, Context context) {
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (str.equals(runningServiceInfo.service.getClassName()) && runningServiceInfo.started) {
                z = true;
            }
        }
        return z;
    }

    public static boolean isValidEmail(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public static boolean isValidPhoneNumber(String str) {
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty() && ((!trim.startsWith("+91") || trim.matches("\\+91[6-9][0-9]{9}")) && trim.matches("^\\+[1-9]([0-9]){6,13}[0-9]$"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUrl(String str) {
        return URLUtil.isValidUrl(str) && Patterns.WEB_URL.matcher(str).matches();
    }

    public static void openAppSettings(@NonNull Activity activity, int i) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        if (i > 0) {
            activity.startActivityForResult(intent, i);
        } else {
            activity.startActivity(intent);
        }
    }

    public static void openBluetoothSettings(Context context) {
        Intent intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
        intent.setFlags(268435456);
        try {
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setComponent(new ComponentName("com.android.settings", "com.android.settings.bluetooth.BluetoothSettings"));
                context.startActivity(intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent3 = new Intent("android.settings.SETTINGS");
            if (intent3.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent3);
            }
        }
    }

    public static void openGenericMessageSharingApp(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, null));
    }

    public static void openLocationSettings(@NonNull Activity activity, int i) {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        if (i > 0) {
            activity.startActivityForResult(intent, i);
        } else {
            activity.startActivity(intent);
        }
    }

    public static void openMessageSharingAppWithPhoneNumber(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        StringBuilder sb = new StringBuilder("sms:");
        if (str != null) {
            sb.append(str);
        }
        intent.setData(Uri.parse(sb.toString()));
        intent.putExtra("sms_body", str2);
        context.startActivity(Intent.createChooser(intent, null));
    }

    public static Date parseDate(String str, String str2) throws ParseException {
        return getSimpleDateFormat(str2).parse(str);
    }

    public static Date parseDateUTC(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(str2);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = simpleDateFormat.parse(str);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.parse(simpleDateFormat.format(parse));
    }

    public static String removeLeadingZero(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.replace(0, i, "");
        return stringBuffer.toString();
    }

    public static ArrayList<String> retrieveCountryCodesFromIsd(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_iso_code);
        }
        int i = 0;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (str.equals(ISDFormatUtils.getIsdCode(strArr[i]))) {
                arrayList.add(ISDFormatUtils.getTwoCharISOCode(f7623a[i]));
            }
            i++;
        }
    }

    public static ArrayList<String> retrieveCountryCodesFromIsdFromThreeDigits(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_un_code);
        }
        int i = 0;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (str.equals(ISDFormatUtils.getIsdCode(strArr[i]))) {
                arrayList.add(ISDFormatUtils.getThreeCharUNCode(f7623a[i]));
            }
            i++;
        }
    }

    public static ArrayList<String> retrieveCountryNamesFromIsd(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_iso_code);
        }
        int i = 0;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (str.equals(ISDFormatUtils.getIsdCode(strArr[i]))) {
                arrayList.add(ISDFormatUtils.getCountryFullName(f7623a[i]));
            }
            i++;
        }
    }

    public static ArrayList<String> retrieveCountryNamesFromIsdFromThreeDigits(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_un_code);
        }
        int i = 0;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (str.equals(ISDFormatUtils.getIsdCode(strArr[i]))) {
                arrayList.add(ISDFormatUtils.getCountryFullName(f7623a[i]));
            }
            i++;
        }
    }

    public static ArrayList<String> retrieveCountryNamesFromIsdThreeChar(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_iso_code);
        }
        int i = 0;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (str.equals(ISDFormatUtils.getIsdCode(strArr[i]))) {
                arrayList.add(ISDFormatUtils.getThreeCharUNCode(f7623a[i]));
            }
            i++;
        }
    }

    public static ArrayList<String> retrieveCountryNamesFromIsdThreeCharForThreeDigits(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_un_code);
        }
        int i = 0;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return arrayList;
            }
            if (str.equals(ISDFormatUtils.getIsdCode(strArr[i]))) {
                arrayList.add(ISDFormatUtils.getThreeCharUNCode(f7623a[i]));
            }
            i++;
        }
    }

    public static ArrayList<String> retrieveCountryUNNamesFromIsd(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] stringArray = context.getResources().getStringArray(R.array.country_isd_un_code);
        if (str.equals("1")) {
            arrayList.add(ISDFormatUtils.getThreeCharUNCode("1,USA"));
        } else {
            for (int i = 0; i < stringArray.length; i++) {
                if (str.equals(ISDFormatUtils.getIsdCode(stringArray[i]))) {
                    arrayList.add(ISDFormatUtils.getThreeCharUNCode(stringArray[i]));
                }
            }
        }
        return arrayList;
    }

    public static String retrieveIsdFromCountryCode(Context context, String str) {
        Resources resources = context.getResources();
        if (f7623a == null) {
            f7623a = resources.getStringArray(R.array.country_isd_iso_code);
        }
        int i = 0;
        String str2 = null;
        while (true) {
            String[] strArr = f7623a;
            if (i >= strArr.length) {
                return str2;
            }
            if (str.equals(ISDFormatUtils.getCountryCode(strArr[i]))) {
                str2 = ISDFormatUtils.getIsdCode(f7623a[i]);
            }
            i++;
        }
    }

    public static Drawable retriveCountryFlag(Context context, String str) {
        Resources resources = context.getResources();
        try {
            resources.getIdentifier(str, "drawable", context.getPackageName());
            String[] stringArray = resources.getStringArray(R.array.country_isd_un_code);
            String[] stringArray2 = resources.getStringArray(R.array.country_isd_iso_code);
            try {
                if (str.length() > 2) {
                    String str2 = "";
                    for (int i = 0; i < stringArray.length; i++) {
                        if (str.toUpperCase().equals(stringArray[i].split(Constants.SEPARATOR_COMMA)[1])) {
                            str2 = stringArray[i].split(Constants.SEPARATOR_COMMA)[0];
                        }
                    }
                    for (int i2 = 0; i2 < stringArray2.length; i2++) {
                        if (str2.equals(stringArray2[i2].split(Constants.SEPARATOR_COMMA)[0])) {
                            str = stringArray2[i2].split(Constants.SEPARATOR_COMMA)[1].toLowerCase();
                        }
                    }
                    return Drawable.createFromStream(context.getAssets().open("flags/" + str + ".png"), null);
                }
                return Drawable.createFromStream(context.getAssets().open("flags/" + str + ".png"), null);
            } catch (Exception e) {
                e.printStackTrace();
                return new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
            }
        } catch (Resources.NotFoundException unused) {
            return new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    public static void setLocale(String str, Context context) {
        Locale locale = new Locale(str);
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static void showForegroundNotification(Service service, String str, int i, int i2, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 26) {
            int i3 = R.string.titan_notification_fg_msg;
            ((NotificationManager) service.getSystemService("notification")).createNotificationChannel(new NotificationChannel(UtilConstants.ID_NOTIFICATION_CHANNEL, service.getString(i3), 2));
            service.startForeground(1, new Notification.Builder(service, UtilConstants.ID_NOTIFICATION_CHANNEL).setContentTitle(str).setSmallIcon(i).setColor(ContextCompat.getColor(service, i2)).setContentIntent(pendingIntent).setContentText(service.getString(i3)).setAutoCancel(true).build());
        }
    }

    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public static double calculateCalories(int i, int i2, int i3, int i4) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", Float.valueOf(i4 > 0 ? (float) ((i * ((i3 * 0.7d) * 2.20462d)) / (160934 / i4)) : (float) ((i * ((i3 * 0.7d) * 2.20462d)) / (160934.0d / (i2 * 0.413d))))));
    }

    public static int calculateDistance(int i, int i2, int i3) {
        if (i3 <= 0) {
            i3 = (int) (i2 * 0.413d);
        }
        return (i3 * i) / 100;
    }

    public static int calculateDistanceInCentimeters(int i, int i2, String str, int i3, int i4) {
        if (i3 <= 0) {
            i3 = (int) (i2 * 0.413d);
        }
        if (!str.equalsIgnoreCase(CoveApiConstants.RUN)) {
            i4 = i3;
        } else if (i4 <= 0) {
            i4 = (int) (i2 * 1.3d * 0.413d);
        }
        return i4 * i;
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() <= 0;
    }

    public static int calculateDistance(int i, int i2, String str) {
        double d = i2;
        int i3 = (int) (d * 0.413d);
        if (str.equalsIgnoreCase(CoveApiConstants.RUN)) {
            i3 = (int) (d * 1.3d * 0.413d);
        }
        return (i3 * i) / 100;
    }

    public static double calculateCalories(int i, int i2, int i3, String str) {
        double d = i * 0.7d * i3 * 2.20462d;
        double d2 = i2;
        float f = (float) (d / (160934.0d / (0.413d * d2)));
        if (str.equalsIgnoreCase(CoveApiConstants.RUN)) {
            f = (float) (d / (160934.0d / (d2 * 0.415d)));
        }
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", Float.valueOf(f)));
    }

    public static PhoneValidationResponse isValidPhoneNumber(Context context, String str, String str2) {
        if (str.length() > 15) {
            PhoneNoValidationState phoneNoValidationState = PhoneNoValidationState.INVALID_PHONE_NO;
            return new PhoneValidationResponse(phoneNoValidationState, context.getString(R.string.max_didgits_allowed) + 15);
        } else if (str2.equals("91") && str.length() > 10) {
            return new PhoneValidationResponse(PhoneNoValidationState.INVALID_PHONE_NO, context.getString(R.string.please_enter_valid_phone_no));
        } else {
            try {
                String a2 = a(context, str2, str);
                if (a2 != null) {
                    String trim = a2.trim();
                    if (!trim.isEmpty()) {
                        if (trim.startsWith("+91") && !trim.matches("\\+91[6-9][0-9]{9}")) {
                            return new PhoneValidationResponse(PhoneNoValidationState.INVALID_PHONE_NO, context.getString(R.string.please_enter_valid_phone_no));
                        }
                        if (trim.startsWith("+65")) {
                            if (trim.matches("^\\+[1-9]([0-9]){8,13}[0-9]$")) {
                                PhoneValidationResponse phoneValidationResponse = new PhoneValidationResponse(PhoneNoValidationState.VALID_PHONE_NO, context.getString(R.string.valid));
                                phoneValidationResponse.setParsedMobileNumber(trim);
                                return phoneValidationResponse;
                            }
                        } else if (trim.matches("^\\+[1-9]([0-9]){6,13}[0-9]$")) {
                            PhoneValidationResponse phoneValidationResponse2 = new PhoneValidationResponse(PhoneNoValidationState.VALID_PHONE_NO, context.getString(R.string.valid));
                            phoneValidationResponse2.setParsedMobileNumber(trim);
                            return phoneValidationResponse2;
                        }
                        return new PhoneValidationResponse(PhoneNoValidationState.INVALID_PHONE_NO, context.getString(R.string.please_enter_valid_phone_no));
                    }
                }
                return new PhoneValidationResponse(PhoneNoValidationState.INVALID_PHONE_NO, context.getString(R.string.please_enter_valid_phone_no));
            } catch (PhoneNumberParseException e) {
                e.printStackTrace();
                return new PhoneValidationResponse(PhoneNoValidationState.INVALID_PHONE_NO, context.getString(R.string.please_enter_valid_phone_no));
            }
        }
    }

    public static int calculateDistance(int i, int i2, String str, int i3, int i4) {
        if (i3 <= 0) {
            i3 = (int) (i2 * 0.413d);
        }
        if (!str.equalsIgnoreCase(CoveApiConstants.RUN)) {
            i4 = i3;
        } else if (i4 <= 0) {
            i4 = (int) (i2 * 1.3d * 0.413d);
        }
        return (i4 * i) / 100;
    }

    public static double calculateCalories(int i, int i2, int i3, String str, int i4, int i5) {
        double d;
        double d2;
        if (i4 > 0) {
            d = i * i3 * 0.7d * 2.20462d;
            d2 = 160934 / i4;
        } else {
            d = i * i3 * 0.7d * 2.20462d;
            d2 = 160934.0d / (i2 * 0.413d);
        }
        float f = (float) (d / d2);
        if (str.equalsIgnoreCase(CoveApiConstants.RUN)) {
            f = i5 > 0 ? (float) ((i * ((i3 * 0.7d) * 2.20462d)) / (160934 / i5)) : (float) ((i * ((i3 * 0.7d) * 2.20462d)) / (160934.0d / ((i2 * 0.415d) * 1.3d)));
        }
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", Float.valueOf(f)));
    }

    public static int getAge(String str) {
        Date date;
        try {
            date = parseDate(str, "yyyy-mm-dd");
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar2.get(1) - calendar.get(1);
        return calendar2.get(6) < calendar.get(6) ? i - 1 : i;
    }

    public static void openBluetoothSettings(Activity activity, int i) {
        Intent intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
        intent.setFlags(268435456);
        try {
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivityForResult(intent, i);
            } else {
                Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setComponent(new ComponentName("com.android.settings", "com.android.settings.bluetooth.BluetoothSettings"));
                activity.startActivityForResult(intent2, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent3 = new Intent("android.settings.SETTINGS");
            if (intent3.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivityForResult(intent3, i);
            }
        }
    }
}
