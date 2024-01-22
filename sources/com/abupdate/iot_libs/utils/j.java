package com.abupdate.iot_libs.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import com.abupdate.trace.Trace;
import com.clevertap.android.sdk.Constants;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
/* loaded from: classes.dex */
public class j {
    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            if (Build.VERSION.SDK_INT >= 23) {
                return context.getExternalCacheDir() + "/iport_log.txt";
            }
            return Environment.getExternalStorageDirectory() + "/iport_log.txt";
        }
        return context.getCacheDir().getAbsolutePath() + File.separator + "iport_log.txt";
    }

    public static long a() {
        return System.currentTimeMillis() / 1000;
    }

    public static boolean a(String str, Date date, Date date2) {
        Trace.d("utils", "timeCompare() cur:" + str + ",form:" + date.toString() + ",to:" + date2.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date parse = simpleDateFormat.parse(str);
            return date.after(date2) ? (parse.after(date) && parse.before(simpleDateFormat.parse("24:00"))) || (parse.after(simpleDateFormat.parse("00:00")) && parse.before(date2)) : parse.after(date) && parse.before(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String a(Map map) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : map.keySet()) {
            sb.append(obj);
            sb.append(":");
            sb.append(map.get(obj));
            sb.append(Constants.SEPARATOR_COMMA);
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
