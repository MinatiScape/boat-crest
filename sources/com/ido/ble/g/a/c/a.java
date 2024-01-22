package com.ido.ble.g.a.c;

import android.os.Build;
import com.ido.ble.common.m;
/* loaded from: classes11.dex */
public class a {
    public static String a() {
        if (Build.VERSION.SDK_INT >= 31) {
            if (!m.k()) {
                return "[bluetooth switch: off]";
            }
            if (!m.h()) {
                return "[bluetooth scan permission: no]";
            }
            if (!m.f()) {
                return "[bluetooth connect permission: no]";
            }
        } else if (!m.k()) {
            return "[蓝牙开关:关]";
        } else {
            if (!m.e()) {
                return "[蓝牙权限:无]";
            }
            if (!m.l()) {
                return "[定位开关:关]";
            }
            if (!m.i()) {
                return "[定位权限:无]";
            }
        }
        return "";
    }
}
