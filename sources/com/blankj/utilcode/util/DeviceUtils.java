package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import com.blankj.utilcode.util.ShellUtils;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;
/* loaded from: classes.dex */
public final class DeviceUtils {

    /* renamed from: a  reason: collision with root package name */
    public static volatile String f2252a;

    public DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static InetAddress a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && nextElement2.getHostAddress().indexOf(58) < 0) {
                            return nextElement2;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b() {
        String str;
        String str2;
        ShellUtils.CommandResult B = b.B("getprop wifi.interface", false);
        if (B.result != 0 || (str = B.successMsg) == null) {
            return "02:00:00:00:00:00";
        }
        ShellUtils.CommandResult B2 = b.B("cat /sys/class/net/" + str + "/address", false);
        return (B2.result != 0 || (str2 = B2.successMsg) == null || str2.length() <= 0) ? "02:00:00:00:00:00" : str2;
    }

    public static String c() {
        NetworkInterface byInetAddress;
        byte[] hardwareAddress;
        try {
            InetAddress a2 = a();
            if (a2 == null || (byInetAddress = NetworkInterface.getByInetAddress(a2)) == null || (hardwareAddress = byInetAddress.getHardwareAddress()) == null || hardwareAddress.length <= 0) {
                return "02:00:00:00:00:00";
            }
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x:", Byte.valueOf(hardwareAddress[i])));
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    public static String d() {
        byte[] hardwareAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement != null && nextElement.getName().equalsIgnoreCase("wlan0") && (hardwareAddress = nextElement.getHardwareAddress()) != null && hardwareAddress.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02x:", Byte.valueOf(hardwareAddress[i])));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String e() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "02:00:00:00:00:00";
            }
            String macAddress = connectionInfo.getMacAddress();
            return !TextUtils.isEmpty(macAddress) ? macAddress : "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    public static String f(String str, String str2) {
        if (str2.equals("")) {
            return str + UUID.randomUUID().toString().replace("-", "");
        }
        return str + UUID.nameUUIDFromBytes(str2.getBytes()).toString().replace("-", "");
    }

    public static String g(String str) {
        try {
            String androidID = getAndroidID();
            if (!TextUtils.isEmpty(androidID)) {
                return j(str + 2, androidID);
            }
        } catch (Exception unused) {
        }
        return j(str + 9, "");
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        String str = Build.CPU_ABI2;
        return !TextUtils.isEmpty(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID() {
        String string = Settings.Secure.getString(Utils.getApp().getContentResolver(), "android_id");
        return ("9774d56d682e549c".equals(string) || string == null) ? "" : string;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE"})
    public static String getMacAddress() {
        String macAddress = getMacAddress(null);
        if (!TextUtils.isEmpty(macAddress) || h()) {
            return macAddress;
        }
        k(true);
        k(false);
        return getMacAddress(null);
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String str = Build.MODEL;
        return str != null ? str.trim().replaceAll("\\s*", "") : "";
    }

    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static String getUniqueDeviceId() {
        return getUniqueDeviceId("", true);
    }

    public static boolean h() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static boolean i(String str, String... strArr) {
        if (TextUtils.isEmpty(str) || "02:00:00:00:00:00".equals(str)) {
            return false;
        }
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (str2 != null && str2.equals(str)) {
                    return false;
                }
            }
        }
        return true;
    }

    @RequiresApi(api = 17)
    public static boolean isAdbEnabled() {
        return Settings.Secure.getInt(Utils.getApp().getContentResolver(), "adb_enabled", 0) > 0;
    }

    @RequiresApi(api = 17)
    public static boolean isDevelopmentSettingsEnabled() {
        return Settings.Global.getInt(Utils.getApp().getContentResolver(), "development_settings_enabled", 0) > 0;
    }

    public static boolean isDeviceRooted() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"};
        for (int i = 0; i < 11; i++) {
            if (new File(strArr[i] + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isEmulator() {
        /*
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.lang.String r1 = "generic"
            boolean r2 = r0.startsWith(r1)
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L63
            java.lang.String r2 = r0.toLowerCase()
            java.lang.String r5 = "vbox"
            boolean r2 = r2.contains(r5)
            if (r2 != 0) goto L63
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r2 = "test-keys"
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L63
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r2 = "google_sdk"
            boolean r5 = r0.contains(r2)
            if (r5 != 0) goto L63
            java.lang.String r5 = "Emulator"
            boolean r5 = r0.contains(r5)
            if (r5 != 0) goto L63
            java.lang.String r5 = "Android SDK built for x86"
            boolean r0 = r0.contains(r5)
            if (r0 != 0) goto L63
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r5 = "Genymotion"
            boolean r0 = r0.contains(r5)
            if (r0 != 0) goto L63
            java.lang.String r0 = android.os.Build.BRAND
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L58
            java.lang.String r0 = android.os.Build.DEVICE
            boolean r0 = r0.startsWith(r1)
            if (r0 != 0) goto L63
        L58:
            java.lang.String r0 = android.os.Build.PRODUCT
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L61
            goto L63
        L61:
            r0 = r3
            goto L64
        L63:
            r0 = r4
        L64:
            if (r0 == 0) goto L67
            return r4
        L67:
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L7c
            java.lang.String r0 = r0.getNetworkOperatorName()
            if (r0 == 0) goto L7c
            goto L7e
        L7c:
            java.lang.String r0 = ""
        L7e:
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r1 = "android"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L8b
            return r4
        L8b:
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "tel:123456"
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r0.setData(r1)
            java.lang.String r1 = "android.intent.action.DIAL"
            r0.setAction(r1)
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            android.content.ComponentName r0 = r0.resolveActivity(r1)
            if (r0 != 0) goto Lae
            r0 = r4
            goto Laf
        Lae:
            r0 = r3
        Laf:
            if (r0 == 0) goto Lb2
            return r4
        Lb2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.DeviceUtils.isEmulator():boolean");
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET", "android.permission.CHANGE_WIFI_STATE"})
    public static boolean isSameDevice(String str) {
        if (!TextUtils.isEmpty(str) || str.length() >= 33) {
            if (str.equals(f2252a) || str.equals(b.d0().getString("KEY_UDID", null))) {
                return true;
            }
            int length = str.length() - 33;
            int i = length + 1;
            String substring = str.substring(length, i);
            if (substring.startsWith("1")) {
                String macAddress = getMacAddress();
                if (macAddress.equals("")) {
                    return false;
                }
                return str.substring(i).equals(f("", macAddress));
            } else if (substring.startsWith("2")) {
                String androidID = getAndroidID();
                if (TextUtils.isEmpty(androidID)) {
                    return false;
                }
                return str.substring(i).equals(f("", androidID));
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isTablet() {
        return (Resources.getSystem().getConfiguration().screenLayout & 15) >= 3;
    }

    public static String j(String str, String str2) {
        f2252a = f(str, str2);
        b.d0().put("KEY_UDID", f2252a);
        return f2252a;
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void k(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null || z == wifiManager.isWifiEnabled()) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    public static String getUniqueDeviceId(String str) {
        return getUniqueDeviceId(str, true);
    }

    public static String getUniqueDeviceId(boolean z) {
        return getUniqueDeviceId("", z);
    }

    public static String getUniqueDeviceId(String str, boolean z) {
        if (!z) {
            return g(str);
        }
        if (f2252a == null) {
            synchronized (DeviceUtils.class) {
                if (f2252a == null) {
                    String string = b.d0().getString("KEY_UDID", null);
                    if (string != null) {
                        f2252a = string;
                        return f2252a;
                    }
                    return g(str);
                }
            }
        }
        return f2252a;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE"})
    public static String getMacAddress(String... strArr) {
        String d = d();
        if (i(d, strArr)) {
            return d;
        }
        String c = c();
        if (i(c, strArr)) {
            return c;
        }
        String e = e();
        if (i(e, strArr)) {
            return e;
        }
        String b = b();
        return i(b, strArr) ? b : "";
    }
}
