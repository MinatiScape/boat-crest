package com.htsmart.wristband2.utils;

import android.util.Log;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public class WristbandLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f12034a = Pattern.compile("\\$\\d+$");
    public static final ThreadLocal<String> b = new ThreadLocal<>();
    public static Logger c;
    public static int d;
    public static Logger e;

    /* loaded from: classes11.dex */
    public interface Logger {
        void log(int i, String str, String str2);
    }

    /* loaded from: classes11.dex */
    public static class a implements Logger {
        @Override // com.htsmart.wristband2.utils.WristbandLog.Logger
        public void log(int i, String str, String str2) {
            Log.println(i, str, str2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface b {
    }

    static {
        a aVar = new a();
        c = aVar;
        d = Integer.MAX_VALUE;
        e = aVar;
    }

    public static String a() {
        ThreadLocal<String> threadLocal = b;
        String str = threadLocal.get();
        if (str != null) {
            threadLocal.remove();
            return str;
        }
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length >= 4) {
            String className = stackTrace[3].getClassName();
            Matcher matcher = f12034a.matcher(className);
            if (matcher.find()) {
                className = matcher.replaceAll("");
            }
            return "Wristband#" + className.substring(className.lastIndexOf(46) + 1);
        }
        throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
    }

    public static String b(String str, Object... objArr) {
        return objArr.length == 0 ? str : String.format(str, objArr);
    }

    public static void c(int i, String str, String str2) {
        if (str2.length() < 4000) {
            e.log(i, str, str2);
            return;
        }
        for (String str3 : str2.split("\n")) {
            e.log(i, str, str3);
        }
    }

    public static void d(int i, Throwable th, String str, Object... objArr) {
        if (i < d) {
            return;
        }
        String b2 = b(str, objArr);
        if (b2 == null || b2.length() == 0) {
            if (th == null) {
                return;
            }
            b2 = Log.getStackTraceString(th);
        } else if (th != null) {
            b2 = b2 + "\n" + Log.getStackTraceString(th);
        }
        c(i, a(), b2);
    }

    public static void d(String str, Object... objArr) {
        d(3, null, str, objArr);
    }

    public static void d(Throwable th, String str, Object... objArr) {
        d(3, th, str, objArr);
    }

    public static void e(String str, Object... objArr) {
        d(6, null, str, objArr);
    }

    public static void e(Throwable th, String str, Object... objArr) {
        d(6, th, str, objArr);
    }

    public static void i(String str, Object... objArr) {
        d(4, null, str, objArr);
    }

    public static void i(Throwable th, String str, Object... objArr) {
        d(4, th, str, objArr);
    }

    public static boolean isAtLeast(int i) {
        return d <= i;
    }

    public static void setLogLevel(int i) {
        d = i;
    }

    public static void setLogger(@Nullable Logger logger) {
        if (logger == null) {
            logger = c;
        }
        e = logger;
    }

    public static void v(String str, Object... objArr) {
        d(2, null, str, objArr);
    }

    public static void v(Throwable th, String str, Object... objArr) {
        d(2, th, str, objArr);
    }

    public static void w(String str, Object... objArr) {
        d(5, null, str, objArr);
    }

    public static void w(Throwable th, String str, Object... objArr) {
        d(5, th, str, objArr);
    }
}
