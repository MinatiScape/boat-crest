package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
/* loaded from: classes.dex */
public final class RomUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f2281a = {"huawei"};
    public static final String[] b = {"vivo"};
    public static final String[] c = {"xiaomi"};
    public static final String[] d = {"oppo"};
    public static final String[] e = {"leeco", "letv"};
    public static final String[] f = {"360", "qiku"};
    public static final String[] g = {"zte"};
    public static final String[] h = {"oneplus"};
    public static final String[] i = {"nubia"};
    public static final String[] j = {"coolpad", "yulong"};
    public static final String[] k = {"lg", "lge"};
    public static final String[] l = {"google"};
    public static final String[] m = {"samsung"};
    public static final String[] n = {"meizu"};
    public static final String[] o = {"lenovo"};
    public static final String[] p = {"smartisan", "deltainno"};
    public static final String[] q = {"htc"};
    public static final String[] r = {"sony"};
    public static final String[] s = {"gionee", "amigo"};
    public static final String[] t = {"motorola"};
    public static RomInfo u = null;

    /* loaded from: classes.dex */
    public static class RomInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f2282a;
        public String b;

        public String getName() {
            return this.f2282a;
        }

        public String getVersion() {
            return this.b;
        }

        public String toString() {
            return "RomInfo{name=" + this.f2282a + ", version=" + this.b + "}";
        }
    }

    public RomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String a() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static String b() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static String c(String str) {
        String d2 = !TextUtils.isEmpty(str) ? d(str) : "";
        if (TextUtils.isEmpty(d2) || d2.equals("unknown")) {
            try {
                String str2 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str2)) {
                    d2 = str2.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        return TextUtils.isEmpty(d2) ? "unknown" : d2;
    }

    public static String d(String str) {
        String f2 = f(str);
        if (TextUtils.isEmpty(f2)) {
            String g2 = g(str);
            return (TextUtils.isEmpty(g2) && Build.VERSION.SDK_INT < 28) ? e(str) : g2;
        }
        return f2;
    }

    public static String e(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String f(String str) {
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                Runtime runtime = Runtime.getRuntime();
                bufferedReader = new BufferedReader(new InputStreamReader(runtime.exec("getprop " + str).getInputStream()), 1024);
            } catch (IOException unused) {
                return "";
            }
        } catch (IOException unused2) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            readLine = bufferedReader.readLine();
        } catch (IOException unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
                return "";
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        if (readLine != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
            return readLine;
        }
        bufferedReader.close();
        return "";
    }

    public static String g(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static RomInfo getRomInfo() {
        RomInfo romInfo = u;
        if (romInfo != null) {
            return romInfo;
        }
        u = new RomInfo();
        String a2 = a();
        String b2 = b();
        String[] strArr = f2281a;
        if (h(a2, b2, strArr)) {
            u.f2282a = strArr[0];
            String c2 = c("ro.build.version.emui");
            String[] split = c2.split("_");
            if (split.length <= 1) {
                u.b = c2;
            } else {
                u.b = split[1];
            }
            return u;
        }
        String[] strArr2 = b;
        if (h(a2, b2, strArr2)) {
            u.f2282a = strArr2[0];
            u.b = c("ro.vivo.os.build.display.id");
            return u;
        }
        String[] strArr3 = c;
        if (h(a2, b2, strArr3)) {
            u.f2282a = strArr3[0];
            u.b = c("ro.build.version.incremental");
            return u;
        }
        String[] strArr4 = d;
        if (h(a2, b2, strArr4)) {
            u.f2282a = strArr4[0];
            u.b = c("ro.build.version.opporom");
            return u;
        }
        String[] strArr5 = e;
        if (h(a2, b2, strArr5)) {
            u.f2282a = strArr5[0];
            u.b = c("ro.letv.release.version");
            return u;
        }
        String[] strArr6 = f;
        if (h(a2, b2, strArr6)) {
            u.f2282a = strArr6[0];
            u.b = c("ro.build.uiversion");
            return u;
        }
        String[] strArr7 = g;
        if (h(a2, b2, strArr7)) {
            u.f2282a = strArr7[0];
            u.b = c("ro.build.MiFavor_version");
            return u;
        }
        String[] strArr8 = h;
        if (h(a2, b2, strArr8)) {
            u.f2282a = strArr8[0];
            u.b = c("ro.rom.version");
            return u;
        }
        String[] strArr9 = i;
        if (h(a2, b2, strArr9)) {
            u.f2282a = strArr9[0];
            u.b = c("ro.build.rom.id");
            return u;
        }
        String[] strArr10 = j;
        if (h(a2, b2, strArr10)) {
            u.f2282a = strArr10[0];
        } else {
            String[] strArr11 = k;
            if (h(a2, b2, strArr11)) {
                u.f2282a = strArr11[0];
            } else {
                String[] strArr12 = l;
                if (h(a2, b2, strArr12)) {
                    u.f2282a = strArr12[0];
                } else {
                    String[] strArr13 = m;
                    if (h(a2, b2, strArr13)) {
                        u.f2282a = strArr13[0];
                    } else {
                        String[] strArr14 = n;
                        if (h(a2, b2, strArr14)) {
                            u.f2282a = strArr14[0];
                        } else {
                            String[] strArr15 = o;
                            if (h(a2, b2, strArr15)) {
                                u.f2282a = strArr15[0];
                            } else {
                                String[] strArr16 = p;
                                if (h(a2, b2, strArr16)) {
                                    u.f2282a = strArr16[0];
                                } else {
                                    String[] strArr17 = q;
                                    if (h(a2, b2, strArr17)) {
                                        u.f2282a = strArr17[0];
                                    } else {
                                        String[] strArr18 = r;
                                        if (h(a2, b2, strArr18)) {
                                            u.f2282a = strArr18[0];
                                        } else {
                                            String[] strArr19 = s;
                                            if (h(a2, b2, strArr19)) {
                                                u.f2282a = strArr19[0];
                                            } else {
                                                String[] strArr20 = t;
                                                if (!h(a2, b2, strArr20)) {
                                                    u.f2282a = b2;
                                                } else {
                                                    u.f2282a = strArr20[0];
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        u.b = c("");
        return u;
    }

    public static boolean h(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean is360() {
        return f[0].equals(getRomInfo().f2282a);
    }

    public static boolean isCoolpad() {
        return j[0].equals(getRomInfo().f2282a);
    }

    public static boolean isGionee() {
        return s[0].equals(getRomInfo().f2282a);
    }

    public static boolean isGoogle() {
        return l[0].equals(getRomInfo().f2282a);
    }

    public static boolean isHtc() {
        return q[0].equals(getRomInfo().f2282a);
    }

    public static boolean isHuawei() {
        return f2281a[0].equals(getRomInfo().f2282a);
    }

    public static boolean isLeeco() {
        return e[0].equals(getRomInfo().f2282a);
    }

    public static boolean isLenovo() {
        return o[0].equals(getRomInfo().f2282a);
    }

    public static boolean isLg() {
        return k[0].equals(getRomInfo().f2282a);
    }

    public static boolean isMeizu() {
        return n[0].equals(getRomInfo().f2282a);
    }

    public static boolean isMotorola() {
        return t[0].equals(getRomInfo().f2282a);
    }

    public static boolean isNubia() {
        return i[0].equals(getRomInfo().f2282a);
    }

    public static boolean isOneplus() {
        return h[0].equals(getRomInfo().f2282a);
    }

    public static boolean isOppo() {
        return d[0].equals(getRomInfo().f2282a);
    }

    public static boolean isSamsung() {
        return m[0].equals(getRomInfo().f2282a);
    }

    public static boolean isSmartisan() {
        return p[0].equals(getRomInfo().f2282a);
    }

    public static boolean isSony() {
        return r[0].equals(getRomInfo().f2282a);
    }

    public static boolean isVivo() {
        return b[0].equals(getRomInfo().f2282a);
    }

    public static boolean isXiaomi() {
        return c[0].equals(getRomInfo().f2282a);
    }

    public static boolean isZte() {
        return g[0].equals(getRomInfo().f2282a);
    }
}
