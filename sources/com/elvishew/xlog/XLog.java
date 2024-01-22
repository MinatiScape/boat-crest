package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.interceptor.Interceptor;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.util.StackTraceUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;
/* loaded from: classes9.dex */
public class XLog {

    /* renamed from: a  reason: collision with root package name */
    public static Logger f7867a;
    public static LogConfiguration b;
    public static Printer c;
    public static boolean d;

    /* loaded from: classes9.dex */
    public static class Log {
        public static void d(String str, String str2) {
            XLog.tag(str).build().d(str2);
        }

        public static void e(String str, String str2) {
            XLog.tag(str).build().e(str2);
        }

        public static String getStackTraceString(Throwable th) {
            return StackTraceUtil.getStackTraceString(th);
        }

        public static void i(String str, String str2) {
            XLog.tag(str).build().i(str2);
        }

        public static boolean isLoggable(String str, int i) {
            return XLog.b.b(i);
        }

        public static void println(int i, String str, String str2) {
            XLog.tag(str).build().c(i, str2);
        }

        public static void v(String str, String str2) {
            XLog.tag(str).build().v(str2);
        }

        public static void w(String str, String str2) {
            XLog.tag(str).build().w(str2);
        }

        public static void wtf(String str, String str2) {
            e(str, str2);
        }

        public static void d(String str, String str2, Throwable th) {
            XLog.tag(str).build().d(str2, th);
        }

        public static void e(String str, String str2, Throwable th) {
            XLog.tag(str).build().e(str2, th);
        }

        public static void i(String str, String str2, Throwable th) {
            XLog.tag(str).build().i(str2, th);
        }

        public static void v(String str, String str2, Throwable th) {
            XLog.tag(str).build().v(str2, th);
        }

        public static void w(String str, String str2, Throwable th) {
            XLog.tag(str).build().w(str2, th);
        }

        public static void wtf(String str, Throwable th) {
            wtf(str, "", th);
        }

        public static void w(String str, Throwable th) {
            XLog.tag(str).build().w("", th);
        }

        public static void wtf(String str, String str2, Throwable th) {
            e(str, str2, th);
        }
    }

    public static void a() {
        if (!d) {
            throw new IllegalStateException("Do you forget to initialize XLog?");
        }
    }

    public static Logger.Builder addInterceptor(Interceptor interceptor) {
        return new Logger.Builder().addInterceptor(interceptor);
    }

    public static <T> Logger.Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
        return new Logger.Builder().addObjectFormatter(cls, objectFormatter);
    }

    @Deprecated
    public static Logger.Builder b() {
        return enableBorder();
    }

    public static Logger.Builder borderFormatter(BorderFormatter borderFormatter) {
        return new Logger.Builder().borderFormatter(borderFormatter);
    }

    public static void d(Object obj) {
        a();
        f7867a.d(obj);
    }

    public static Logger.Builder disableBorder() {
        return new Logger.Builder().disableBorder();
    }

    public static Logger.Builder disableStackTrace() {
        return new Logger.Builder().disableStackTrace();
    }

    public static Logger.Builder disableThreadInfo() {
        return new Logger.Builder().disableThreadInfo();
    }

    public static void e(Object obj) {
        a();
        f7867a.e(obj);
    }

    public static Logger.Builder enableBorder() {
        return new Logger.Builder().enableBorder();
    }

    public static Logger.Builder enableStackTrace(int i) {
        return new Logger.Builder().enableStackTrace(i);
    }

    public static Logger.Builder enableThreadInfo() {
        return new Logger.Builder().enableThreadInfo();
    }

    public static void i(Object obj) {
        a();
        f7867a.i(obj);
    }

    public static void init() {
        init(new LogConfiguration.Builder().build(), DefaultsFactory.createPrinter());
    }

    public static void json(String str) {
        a();
        f7867a.json(str);
    }

    public static Logger.Builder jsonFormatter(JsonFormatter jsonFormatter) {
        return new Logger.Builder().jsonFormatter(jsonFormatter);
    }

    public static void log(int i, Object obj) {
        a();
        f7867a.log(i, obj);
    }

    public static Logger.Builder logLevel(int i) {
        return new Logger.Builder().logLevel(i);
    }

    @Deprecated
    public static Logger.Builder nb() {
        return disableBorder();
    }

    @Deprecated
    public static Logger.Builder nst() {
        return disableStackTrace();
    }

    @Deprecated
    public static Logger.Builder nt() {
        return disableThreadInfo();
    }

    public static Logger.Builder printers(Printer... printerArr) {
        return new Logger.Builder().printers(printerArr);
    }

    @Deprecated
    public static Logger.Builder st(int i) {
        return enableStackTrace(i);
    }

    public static Logger.Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
        return new Logger.Builder().stackTraceFormatter(stackTraceFormatter);
    }

    @Deprecated
    public static Logger.Builder t() {
        return enableThreadInfo();
    }

    public static Logger.Builder tag(String str) {
        return new Logger.Builder().tag(str);
    }

    public static Logger.Builder threadFormatter(ThreadFormatter threadFormatter) {
        return new Logger.Builder().threadFormatter(threadFormatter);
    }

    public static Logger.Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
        return new Logger.Builder().throwableFormatter(throwableFormatter);
    }

    public static void v(Object obj) {
        a();
        f7867a.v(obj);
    }

    public static void w(Object obj) {
        a();
        f7867a.w(obj);
    }

    public static void xml(String str) {
        a();
        f7867a.xml(str);
    }

    public static Logger.Builder xmlFormatter(XmlFormatter xmlFormatter) {
        return new Logger.Builder().xmlFormatter(xmlFormatter);
    }

    public static Logger.Builder enableStackTrace(String str, int i) {
        return new Logger.Builder().enableStackTrace(str, i);
    }

    public static void init(int i) {
        init(new LogConfiguration.Builder().logLevel(i).build(), DefaultsFactory.createPrinter());
    }

    @Deprecated
    public static Logger.Builder st(String str, int i) {
        return enableStackTrace(str, i);
    }

    public static void d(Object[] objArr) {
        a();
        f7867a.d(objArr);
    }

    public static void e(Object[] objArr) {
        a();
        f7867a.e(objArr);
    }

    public static void i(Object[] objArr) {
        a();
        f7867a.i(objArr);
    }

    public static void log(int i, Object[] objArr) {
        a();
        f7867a.log(i, objArr);
    }

    public static void v(Object[] objArr) {
        a();
        f7867a.v(objArr);
    }

    public static void w(Object[] objArr) {
        a();
        f7867a.w(objArr);
    }

    public static void d(String str, Object... objArr) {
        a();
        f7867a.d(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        a();
        f7867a.e(str, objArr);
    }

    public static void i(String str, Object... objArr) {
        a();
        f7867a.i(str, objArr);
    }

    @Deprecated
    public static void init(int i, LogConfiguration logConfiguration) {
        init(new LogConfiguration.Builder(logConfiguration).logLevel(i).build());
    }

    public static void log(int i, String str, Object... objArr) {
        a();
        f7867a.log(i, str, objArr);
    }

    public static void v(String str, Object... objArr) {
        a();
        f7867a.v(str, objArr);
    }

    public static void w(String str, Object... objArr) {
        a();
        f7867a.w(str, objArr);
    }

    public static void init(LogConfiguration logConfiguration) {
        init(logConfiguration, DefaultsFactory.createPrinter());
    }

    public static void d(String str) {
        a();
        f7867a.d(str);
    }

    public static void e(String str) {
        a();
        f7867a.e(str);
    }

    public static void i(String str) {
        a();
        f7867a.i(str);
    }

    public static void init(Printer... printerArr) {
        init(new LogConfiguration.Builder().build(), printerArr);
    }

    public static void log(int i, String str) {
        a();
        f7867a.log(i, str);
    }

    public static void v(String str) {
        a();
        f7867a.v(str);
    }

    public static void w(String str) {
        a();
        f7867a.w(str);
    }

    public static void init(int i, Printer... printerArr) {
        init(new LogConfiguration.Builder().logLevel(i).build(), printerArr);
    }

    public static void d(String str, Throwable th) {
        a();
        f7867a.d(str, th);
    }

    public static void e(String str, Throwable th) {
        a();
        f7867a.e(str, th);
    }

    public static void i(String str, Throwable th) {
        a();
        f7867a.i(str, th);
    }

    @Deprecated
    public static void init(int i, LogConfiguration logConfiguration, Printer... printerArr) {
        init(new LogConfiguration.Builder(logConfiguration).logLevel(i).build(), printerArr);
    }

    public static void log(int i, String str, Throwable th) {
        a();
        f7867a.log(i, str, th);
    }

    public static void v(String str, Throwable th) {
        a();
        f7867a.v(str, th);
    }

    public static void w(String str, Throwable th) {
        a();
        f7867a.w(str, th);
    }

    public static void init(LogConfiguration logConfiguration, Printer... printerArr) {
        if (d) {
            Platform.get().warn("XLog is already initialized, do not initialize again");
        }
        d = true;
        if (logConfiguration != null) {
            b = logConfiguration;
            PrinterSet printerSet = new PrinterSet(printerArr);
            c = printerSet;
            f7867a = new Logger(b, printerSet);
            return;
        }
        throw new IllegalArgumentException("Please specify a LogConfiguration");
    }
}
