package com.coveiot.coveaccess.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.szabh.smable3.entity.Languages;
/* loaded from: classes8.dex */
public final class PreferenceManager {

    /* renamed from: a  reason: collision with root package name */
    public static PreferenceManager f6680a = null;
    public static Context b = null;
    public static String c = "coveaccess_prefs";
    public static String d = "authtoken";
    public static String e = "sessionid";
    public static String f = "access_token";
    public static String g = "refresh_token";
    public static String h = "pref_token_expiry_period";
    public static String i = "pref_token_refresh_time";
    public static String j = "device_agent";
    public static String k = "user_id";
    public static String l = "user_device_id";
    public static String m = "pref_language";
    public static String n = "guest_user_session_id";

    public static PreferenceManager getInstance() {
        return f6680a;
    }

    public static void initPreferenceMgr(Context context) {
        if (f6680a == null) {
            f6680a = new PreferenceManager();
            b = context;
        }
    }

    public void clearLoginDetails() {
        b.getSharedPreferences(c, 0).edit().remove(f).remove(g).remove(h).apply();
    }

    public void clearPreferences() {
        if (f6680a != null) {
            SharedPreferences sharedPreferences = b.getSharedPreferences(c, 0);
            sharedPreferences.edit().clear();
            sharedPreferences.edit().apply();
        }
    }

    public String getAccessToken() {
        return b.getSharedPreferences(c, 0).getString(f, "");
    }

    public String getAuthToken() {
        return b.getSharedPreferences(c, 0).getString(d, null);
    }

    public String getDeviceAgent() {
        return b.getSharedPreferences(c, 0).getString(j, null);
    }

    public String getGuestUserSessionId() {
        return b.getSharedPreferences(c, 0).getString(n, null);
    }

    public String getLanguage() {
        return b.getSharedPreferences(c, 0).getString(m, Languages.DEFAULT_LANGUAGE);
    }

    public String getRefreshToken() {
        return b.getSharedPreferences(c, 0).getString(g, "");
    }

    public String getSessionId() {
        return b.getSharedPreferences(c, 0).getString(e, null);
    }

    public Long getTokenExpiryDuration() {
        return Long.valueOf(b.getSharedPreferences(c, 0).getLong(h, -1L));
    }

    public Long getTokenRefreshTime() {
        return Long.valueOf(b.getSharedPreferences(c, 0).getLong(i, -1L));
    }

    public String getUserDeviceID() {
        return b.getSharedPreferences(c, 0).getString(l, "");
    }

    public Integer getUserId() {
        return Integer.valueOf(b.getSharedPreferences(c, 0).getInt(k, 0));
    }

    public void saveAccessToken(String str) {
        b.getSharedPreferences(c, 0).edit().putString(f, str).apply();
    }

    public void saveAuthToken(String str) {
        b.getSharedPreferences(c, 0).edit().putString(d, str).apply();
    }

    public void saveDeviceAgent(String str) {
        b.getSharedPreferences(c, 0).edit().putString(j, str).apply();
    }

    public void saveGuestUserSessionId(String str) {
        b.getSharedPreferences(c, 0).edit().putString(n, str).apply();
    }

    public void saveLanguage(String str) {
        b.getSharedPreferences(c, 0).edit().putString(m, str).apply();
    }

    public void saveRefreshToken(String str) {
        b.getSharedPreferences(c, 0).edit().putString(g, str).apply();
    }

    public void saveSessionId(String str) {
        b.getSharedPreferences(c, 0).edit().putString(e, str).apply();
    }

    public void saveTokenExpiryDuration(Long l2) {
        b.getSharedPreferences(c, 0).edit().putLong(h, l2.longValue()).apply();
    }

    public void saveTokenRefreshTime(long j2) {
        b.getSharedPreferences(c, 0).edit().putLong(i, j2).apply();
    }

    public void saveUserDeviceId(String str) {
        b.getSharedPreferences(c, 0).edit().putString(l, str).apply();
    }

    public void saveUserId(Integer num) {
        b.getSharedPreferences(c, 0).edit().putInt(k, num.intValue()).apply();
    }
}
