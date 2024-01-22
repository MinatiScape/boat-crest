package com.polidea.rxandroidble2.internal;

import android.util.Log;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.LogOptions;
import com.polidea.rxandroidble2.internal.logger.LoggerSetup;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class RxBleLog {
    @Deprecated
    public static final int DEBUG = 3;
    @Deprecated
    public static final int ERROR = 6;
    @Deprecated
    public static final int INFO = 4;
    @Deprecated
    public static final int NONE = Integer.MAX_VALUE;
    @Deprecated
    public static final int VERBOSE = 2;
    @Deprecated
    public static final int WARN = 5;

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f13394a = Pattern.compile("\\$\\d+$");
    public static final ThreadLocal<String> b = new ThreadLocal<>();
    public static final LogOptions.Logger c;
    public static LoggerSetup d;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface LogLevel {
    }

    /* loaded from: classes9.dex */
    public interface Logger {
        void log(int i, String str, String str2);
    }

    /* loaded from: classes9.dex */
    public class a implements LogOptions.Logger {
        @Override // com.polidea.rxandroidble2.LogOptions.Logger
        public void log(int i, String str, String str2) {
            Log.println(i, str, str2);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements LogOptions.Logger {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Logger f13395a;

        public b(Logger logger) {
            this.f13395a = logger;
        }

        @Override // com.polidea.rxandroidble2.LogOptions.Logger
        public void log(int i, String str, String str2) {
            this.f13395a.log(i, str, str2);
        }
    }

    static {
        a aVar = new a();
        c = aVar;
        d = new LoggerSetup(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, false, true, aVar);
    }

    public static String a() {
        String substring;
        ThreadLocal<String> threadLocal = b;
        String str = threadLocal.get();
        if (str != null) {
            threadLocal.remove();
            return str;
        }
        int i = 0;
        List asList = Arrays.asList(RxBleLog.class.getName(), LoggerUtil.class.getName(), LoggerUtilBluetoothServices.class.getName());
        Throwable th = new Throwable();
        StackTraceElement[] stackTrace = th.getStackTrace();
        while (i < stackTrace.length && asList.contains(stackTrace[i].getClassName())) {
            i++;
        }
        if (stackTrace.length > i) {
            String className = stackTrace[i].getClassName();
            Matcher matcher = f13394a.matcher(className);
            if (matcher.find()) {
                className = matcher.replaceAll("");
            }
            String replace = className.replace("Impl", "").replace("RxBle", "");
            int indexOf = replace.indexOf(36);
            if (indexOf <= 0) {
                substring = replace.substring(replace.lastIndexOf(46) + 1);
            } else {
                substring = replace.substring(replace.lastIndexOf(46) + 1, indexOf);
            }
            return "RxBle#" + substring;
        }
        throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?", th);
    }

    public static String b(String str, Object... objArr) {
        return objArr.length == 0 ? str : String.format(str, objArr);
    }

    public static void c(int i, String str, String str2) {
        if (str2.length() < 4000) {
            d.logger.log(i, str, str2);
            return;
        }
        for (String str3 : str2.split("\n")) {
            d.logger.log(i, str, str3);
        }
    }

    public static void d(String str, Object... objArr) {
        f(3, null, str, objArr);
    }

    public static void e(String str, Object... objArr) {
        f(6, null, str, objArr);
    }

    public static void f(int i, Throwable th, String str, Object... objArr) {
        if (i < d.logLevel) {
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

    public static int getMacAddressLogSetting() {
        return d.macAddressLogSetting;
    }

    public static boolean getShouldLogAttributeValues() {
        return d.shouldLogAttributeValues;
    }

    public static boolean getShouldLogScannedPeripherals() {
        return d.shouldLogScannedPeripherals;
    }

    public static int getUuidLogSetting() {
        return d.uuidLogSetting;
    }

    public static void i(String str, Object... objArr) {
        f(4, null, str, objArr);
    }

    public static boolean isAtLeast(int i) {
        return d.logLevel <= i;
    }

    @Deprecated
    public static void setLogLevel(int i) {
        updateLogOptions(new LogOptions.Builder().setLogLevel(Integer.valueOf(i)).build());
    }

    @Deprecated
    public static void setLogger(@Nullable Logger logger) {
        LogOptions.Logger bVar;
        if (logger == null) {
            bVar = c;
        } else {
            bVar = new b(logger);
        }
        updateLogOptions(new LogOptions.Builder().setLogger(bVar).build());
    }

    public static void updateLogOptions(LogOptions logOptions) {
        LoggerSetup loggerSetup = d;
        LoggerSetup merge = loggerSetup.merge(logOptions);
        d("Received new options (%s) and merged with old setup: %s. New setup: %s", logOptions, loggerSetup, merge);
        d = merge;
    }

    public static void v(String str, Object... objArr) {
        f(2, null, str, objArr);
    }

    public static void w(String str, Object... objArr) {
        f(5, null, str, objArr);
    }

    public static void d(Throwable th, String str, Object... objArr) {
        f(3, th, str, objArr);
    }

    public static void e(Throwable th, String str, Object... objArr) {
        f(6, th, str, objArr);
    }

    public static void i(Throwable th, String str, Object... objArr) {
        f(4, th, str, objArr);
    }

    public static void v(Throwable th, String str, Object... objArr) {
        f(2, th, str, objArr);
    }

    public static void w(Throwable th, String str, Object... objArr) {
        f(5, th, str, objArr);
    }
}
