package com.mappls.sdk.plugin.directions;
/* loaded from: classes11.dex */
public class b {
    public static int a(float f, String str) {
        if (str == null || str.equalsIgnoreCase("left")) {
            if (f <= 45.0f) {
                return 65;
            }
            if (f <= 90.0f) {
                return 66;
            }
            if (f <= 135.0f) {
                return 67;
            }
            if (f <= 180.0f) {
                return 68;
            }
            if (f <= 225.0f) {
                return 69;
            }
            return f <= 270.0f ? 70 : 71;
        } else if (f <= 45.0f) {
            return 58;
        } else {
            if (f <= 90.0f) {
                return 59;
            }
            if (f <= 135.0f) {
                return 60;
            }
            if (f <= 180.0f) {
                return 61;
            }
            if (f <= 225.0f) {
                return 62;
            }
            return f <= 270.0f ? 63 : 64;
        }
    }

    public static Integer a(int i) {
        if (i == 41) {
            return 6;
        }
        return Integer.valueOf(i);
    }
}
