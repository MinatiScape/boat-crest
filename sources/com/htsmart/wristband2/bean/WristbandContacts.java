package com.htsmart.wristband2.bean;

import android.text.TextUtils;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class WristbandContacts {
    public static final int NAME_BYTES_LIMIT = 32;
    public static final int NUMBER_BYTES_LIMIT = 20;

    /* renamed from: a  reason: collision with root package name */
    public String f11969a;
    public String b;

    public WristbandContacts() {
    }

    public WristbandContacts(String str, String str2) {
        this.f11969a = str;
        this.b = str2;
    }

    public static String a(String str, int i) {
        if (str == null || str.length() == 0 || i <= 0) {
            return null;
        }
        if (str.getBytes().length < i) {
            return str;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            i2 += String.valueOf(str.charAt(i3)).getBytes().length;
            if (i2 == i) {
                return str.substring(0, i3 + 1);
            }
            if (i2 > i) {
                if (i3 == 0) {
                    return null;
                }
                return str.substring(0, i3);
            }
        }
        return str;
    }

    @Nullable
    public static WristbandContacts create(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        String a2 = a(str.trim(), 32);
        String a3 = a(str2.trim(), 20);
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3)) {
            return null;
        }
        return new WristbandContacts(a2, a3);
    }

    public String getName() {
        return this.f11969a;
    }

    public String getNumber() {
        return this.b;
    }

    public void setName(String str) {
        this.f11969a = str;
    }

    public void setNumber(String str) {
        this.b = str;
    }
}
