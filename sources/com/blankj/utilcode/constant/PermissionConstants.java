package com.blankj.utilcode.constant;

import android.annotation.SuppressLint;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SuppressLint({"InlinedApi"})
/* loaded from: classes.dex */
public final class PermissionConstants {
    public static final String ACTIVITY_RECOGNITION = "ACTIVITY_RECOGNITION";
    public static final String CALENDAR = "CALENDAR";
    public static final String CAMERA = "CAMERA";
    public static final String CONTACTS = "CONTACTS";
    public static final String LOCATION = "LOCATION";
    public static final String MICROPHONE = "MICROPHONE";
    public static final String PHONE = "PHONE";
    public static final String SENSORS = "SENSORS";
    public static final String SMS = "SMS";
    public static final String STORAGE = "STORAGE";

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f2229a = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
    public static final String[] b = {"android.permission.CAMERA"};
    public static final String[] c = {"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"};
    public static final String[] d = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
    public static final String[] e = {"android.permission.RECORD_AUDIO"};
    public static final String[] f = {"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.ANSWER_PHONE_CALLS"};
    public static final String[] g = {"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS"};
    public static final String[] h = {"android.permission.BODY_SENSORS"};
    public static final String[] i = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"};
    public static final String[] j = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final String[] k = {"android.permission.ACTIVITY_RECOGNITION"};

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PermissionGroup {
    }

    public static String[] getPermissions(String str) {
        if (str == null) {
            return new String[0];
        }
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1611296843:
                if (str.equals("LOCATION")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1596608551:
                if (str.equals(SENSORS)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1166291365:
                if (str.equals(STORAGE)) {
                    c2 = 2;
                    break;
                }
                break;
            case 82233:
                if (str.equals(SMS)) {
                    c2 = 3;
                    break;
                }
                break;
            case 76105038:
                if (str.equals(PHONE)) {
                    c2 = 4;
                    break;
                }
                break;
            case 140654183:
                if (str.equals(ACTIVITY_RECOGNITION)) {
                    c2 = 5;
                    break;
                }
                break;
            case 215175251:
                if (str.equals(CONTACTS)) {
                    c2 = 6;
                    break;
                }
                break;
            case 604302142:
                if (str.equals(CALENDAR)) {
                    c2 = 7;
                    break;
                }
                break;
            case 1856013610:
                if (str.equals(MICROPHONE)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1980544805:
                if (str.equals(CAMERA)) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return d;
            case 1:
                return h;
            case 2:
                return j;
            case 3:
                return i;
            case 4:
                if (Build.VERSION.SDK_INT < 26) {
                    return g;
                }
                return f;
            case 5:
                return k;
            case 6:
                return c;
            case 7:
                return f2229a;
            case '\b':
                return e;
            case '\t':
                return b;
            default:
                return new String[]{str};
        }
    }
}
