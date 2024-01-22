package com.ido.ble;

import android.app.Application;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Application f12037a;
    private static boolean b;

    static {
        System.loadLibrary("VeryFitMulti");
        b = false;
    }

    public static void a() {
        LogTool.d();
        com.ido.ble.bluetooth.a.b();
        com.ido.ble.bluetooth.c.c(f12037a);
    }

    public static void a(Application application) {
        if (application == null) {
            throw new RuntimeException("application can not be null !");
        }
        f12037a = application;
    }

    private static void a(InitParam initParam) {
        CustomConfig.getConfig().setEnableLog(initParam.isEnableLog).setIsSaveDeviceDataToDB(initParam.isSaveDeviceDataToDB).setEncryptedDBData(initParam.isEncryptedDBData).setEncryptedSPData(initParam.isEncryptedSPData).setLogSavePath(initParam.log_save_path).setLogSaveDays(initParam.log_save_days).setDatabaseName(initParam.databaseName);
        com.ido.ble.i.a.a.b(initParam.isNeedSoLibAutoSyncConfigIfReboot);
    }

    public static Application b() {
        return f12037a;
    }

    public static void b(InitParam initParam) {
        a(initParam);
        com.ido.ble.f.a.f.c.c.a();
        d();
        LogTool.i();
        if (!b) {
            LogTool.d("SDK", "solib init ok.");
            d.a(initParam);
            b = true;
        }
        e();
        com.ido.ble.event.stat.one.c.d();
        LogTool.d("SDK", "ido-ble-sdk init ok, version is 2.66.69,2022/0805/11:34");
        LogTool.d("SDK", "initParam " + initParam.toString());
    }

    public static void c() {
        b(new InitParam());
    }

    private static void d() {
    }

    private static void e() {
        com.ido.ble.bluetooth.c.a(f12037a);
    }
}
