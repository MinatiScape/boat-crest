package com.mappls.sdk.maps;
/* loaded from: classes11.dex */
public class MapStrictMode {

    /* renamed from: a  reason: collision with root package name */
    public static volatile boolean f12630a;

    public static synchronized void setStrictModeEnabled(boolean z) {
        synchronized (MapStrictMode.class) {
            f12630a = z;
        }
    }

    public static void strictModeViolation(String str) {
        if (f12630a) {
            throw new n(str);
        }
    }

    public static void strictModeViolation(String str, Throwable th) {
        if (f12630a) {
            throw new n(String.format("%s - %s", str, th));
        }
    }

    public static void strictModeViolation(Throwable th) {
        if (f12630a) {
            throw new n(String.format("%s", th));
        }
    }
}
