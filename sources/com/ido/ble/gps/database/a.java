package com.ido.ble.gps.database;

import android.text.TextUtils;
import com.ido.ble.c;
import com.ido.ble.gps.database.b.b;
import com.ido.ble.logs.LogTool;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class a {
    public static List<HealthGps> a() {
        return com.ido.ble.gps.database.b.a.c().a(b());
    }

    public static List<HealthGps> a(int i) {
        return b(i, 0);
    }

    public static List<HealthGps> a(int i, int i2) {
        return com.ido.ble.gps.database.b.a.c().b(b(), i, i2);
    }

    public static List<HealthGpsItem> a(long j) {
        return b.c().a(b(), j);
    }

    public static List<HealthGps> a(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.gps.database.b.a.c().a(b(), whereCondition, whereConditionArr);
    }

    public static void a(int i, int i2, int i3) {
        com.ido.ble.gps.database.b.a.c().b(b(), i, i2, i3);
    }

    public static void a(HealthGps healthGps) {
        com.ido.ble.gps.database.b.a.c().b(b(), healthGps);
    }

    public static void a(HealthGpsItem healthGpsItem) {
        b.c().a(b(), healthGpsItem);
    }

    public static void a(List<HealthGpsItem> list) {
        b.c().a(b(), list);
    }

    private static long b() {
        return c.a() ? com.ido.ble.f.a.f.b.e().c().mDeviceId : c();
    }

    public static HealthGps b(int i, int i2, int i3) {
        return com.ido.ble.gps.database.b.a.c().a(b(), i, i2, i3);
    }

    public static List<HealthGps> b(int i) {
        return com.ido.ble.gps.database.b.a.c().a(b(), i);
    }

    public static List<HealthGps> b(int i, int i2) {
        return com.ido.ble.gps.database.b.a.c().a(b(), i, i2);
    }

    public static List<HealthGpsItem> b(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return b.c().a(b(), whereCondition, whereConditionArr);
    }

    public static void b(HealthGps healthGps) {
        com.ido.ble.gps.database.b.a.c().a(b(), healthGps);
    }

    private static long c() {
        String str = com.ido.ble.f.a.f.b.e().c().mDeviceAddress;
        if (TextUtils.isEmpty(str)) {
            LogTool.b("DataBaseManager", "getBindId:macAddress is null.");
            return -1L;
        }
        try {
            return Long.parseLong(str.replaceAll("[a-zA-Z:]", ""));
        } catch (Exception e) {
            LogTool.b("DataBaseManager", e.getMessage());
            return -1L;
        }
    }
}
