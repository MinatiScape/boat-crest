package com.airbnb.lottie.utils;

import com.airbnb.lottie.LottieLogger;
/* loaded from: classes.dex */
public class Logger {

    /* renamed from: a  reason: collision with root package name */
    public static LottieLogger f2099a = new LogcatLogger();

    public static void debug(String str) {
        f2099a.debug(str);
    }

    public static void error(String str, Throwable th) {
        f2099a.error(str, th);
    }

    public static void setInstance(LottieLogger lottieLogger) {
        f2099a = lottieLogger;
    }

    public static void warning(String str) {
        f2099a.warning(str);
    }

    public static void debug(String str, Throwable th) {
        f2099a.debug(str, th);
    }

    public static void warning(String str, Throwable th) {
        f2099a.warning(str, th);
    }
}
