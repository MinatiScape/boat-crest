package com.abupdate.iot_libs.utils;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.abupdate.iot_libs.constant.Error;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class e {
    public static boolean a(int i) {
        return i == 1000;
    }

    public static boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                return a(jSONObject.getInt(NotificationCompat.CATEGORY_STATUS));
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("body")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("body");
                return jSONObject2.has(NotificationCompat.CATEGORY_STATUS) ? jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS) : Error.RESPONSE_ERROR;
            }
            return Error.RESPONSE_ERROR;
        } catch (JSONException e) {
            e.printStackTrace();
            return Error.RESPONSE_ERROR;
        }
    }

    public static boolean b(int i) {
        return i == 1000;
    }

    public static int c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                return jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
            }
            return 3003;
        } catch (Exception e) {
            e.printStackTrace();
            return 3003;
        }
    }

    public static int a(String str, Context context) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                return jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
            }
            return 2001;
        } catch (JSONException e) {
            e.printStackTrace();
            return 2001;
        }
    }
}
