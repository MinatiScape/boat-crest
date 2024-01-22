package com.coveiot.leaderboard.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.coveiot.coveaccess.SetupException;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.utils.utility.AppUtils;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class LeaderboardUtils {
    public static String API_KEY;

    public static String a(String str) {
        return str.equals("com.google.android.geo.API_KEY") ? ErrorConstants.SETUP_ERR_MALFORMED_API_KEY : ErrorConstants.SETUP_ERR_MISSING_DATA;
    }

    public static String b(String str) {
        return str.equals("com.google.android.geo.API_KEY") ? ErrorConstants.SETUP_ERR_MISSING_API_KEY : ErrorConstants.SETUP_ERR_MISSING_DATA;
    }

    public static String formattedDate(String str, String str2) {
        try {
            return AppUtils.getSimpleDateFormat(str2).format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String formattedRankGraphDate(String str) {
        try {
            str = AppUtils.getSimpleDateFormat("dd/MM").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
            System.out.println(str);
            return str;
        } catch (ParseException unused) {
            return str;
        }
    }

    public static String getLocalDate(String str) {
        try {
            return AppUtils.getSimpleDateFormat("dd MMM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-mm-dd").parse(str));
        } catch (ParseException e) {
            PrintStream printStream = System.out;
            printStream.println("Parse Exception : " + e);
            return str;
        }
    }

    public static void getMetadata(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null && !bundle.isEmpty()) {
                if (bundle.containsKey("com.google.android.geo.API_KEY")) {
                    String string = bundle.getString("com.google.android.geo.API_KEY");
                    if (string != null && string.length() > 0) {
                        API_KEY = string;
                        return;
                    }
                    throw new SetupException(a("com.google.android.geo.API_KEY"));
                }
                throw new SetupException(b("com.google.android.geo.API_KEY"));
            }
            throw new SetupException(ErrorConstants.SETUP_ERR_MISSING_DATA);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            e.printStackTrace();
            throw new SetupException(ErrorConstants.SETUP_ERR_GENERIC);
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static HashMap<String, String> loadMap(Context context) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(LeaderBoardDataUtiils.getBadgeLevels(context));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, (String) jSONObject.get(next));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
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

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
