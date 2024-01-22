package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.interceptor.Interceptor;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.SystemCompat;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class Logger {

    /* renamed from: a  reason: collision with root package name */
    public LogConfiguration f7865a;
    public Printer b;

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f7866a;
        public String b;
        public boolean c;
        public boolean d;
        public boolean e;
        public String f;
        public int g;
        public boolean h;
        public boolean i;
        public boolean j;
        public JsonFormatter k;
        public XmlFormatter l;
        public ThrowableFormatter m;
        public ThreadFormatter n;
        public StackTraceFormatter o;
        public BorderFormatter p;
        public Map<Class<?>, ObjectFormatter<?>> q;
        public List<Interceptor> r;
        public Printer s;

        public Builder() {
            XLog.a();
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (this.r == null) {
                this.r = new ArrayList();
            }
            this.r.add(interceptor);
            return this;
        }

        public <T> Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
            if (this.q == null) {
                this.q = new HashMap(DefaultsFactory.builtinObjectFormatters());
            }
            this.q.put(cls, objectFormatter);
            return this;
        }

        public Builder borderFormatter(BorderFormatter borderFormatter) {
            this.p = borderFormatter;
            return this;
        }

        public Logger build() {
            return new Logger(this);
        }

        public Builder disableBorder() {
            this.i = false;
            this.j = true;
            return this;
        }

        public Builder disableStackTrace() {
            this.e = false;
            this.f = null;
            this.g = 0;
            this.h = true;
            return this;
        }

        public Builder disableThreadInfo() {
            this.c = false;
            this.d = true;
            return this;
        }

        public Builder enableBorder() {
            this.i = true;
            this.j = true;
            return this;
        }

        public Builder enableStackTrace(int i) {
            this.e = true;
            this.g = i;
            this.h = true;
            return this;
        }

        public Builder enableThreadInfo() {
            this.c = true;
            this.d = true;
            return this;
        }

        public void json(String str) {
            build().json(str);
        }

        public Builder jsonFormatter(JsonFormatter jsonFormatter) {
            this.k = jsonFormatter;
            return this;
        }

        public void log(int i, Object obj) {
            build().log(i, obj);
        }

        public Builder logLevel(int i) {
            this.f7866a = i;
            return this;
        }

        @Deprecated
        public Builder nb() {
            return disableBorder();
        }

        @Deprecated
        public Builder nst() {
            return disableStackTrace();
        }

        @Deprecated
        public Builder nt() {
            return disableThreadInfo();
        }

        public Builder printers(Printer... printerArr) {
            if (printerArr.length == 0) {
                this.s = null;
            } else if (printerArr.length == 1) {
                this.s = printerArr[0];
            } else {
                this.s = new PrinterSet(printerArr);
            }
            return this;
        }

        @Deprecated
        public Builder st(int i) {
            return enableStackTrace(i);
        }

        public Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
            this.o = stackTraceFormatter;
            return this;
        }

        @Deprecated
        public Builder t() {
            return enableThreadInfo();
        }

        public Builder tag(String str) {
            this.b = str;
            return this;
        }

        public Builder threadFormatter(ThreadFormatter threadFormatter) {
            this.n = threadFormatter;
            return this;
        }

        public Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
            this.m = throwableFormatter;
            return this;
        }

        public void v(Object obj) {
            build().v(obj);
        }

        public void w(Object obj) {
            build().w(obj);
        }

        public void xml(String str) {
            build().xml(str);
        }

        public Builder xmlFormatter(XmlFormatter xmlFormatter) {
            this.l = xmlFormatter;
            return this;
        }

        @Deprecated
        public Builder b() {
            return enableBorder();
        }

        public void d(Object obj) {
            build().d(obj);
        }

        public void e(Object obj) {
            build().e(obj);
        }

        public void i(Object obj) {
            build().i(obj);
        }

        public void log(int i, Object[] objArr) {
            build().log(i, objArr);
        }

        @Deprecated
        public Builder st(String str, int i) {
            return enableStackTrace(str, i);
        }

        public void v(Object[] objArr) {
            build().v(objArr);
        }

        public void w(Object[] objArr) {
            build().w(objArr);
        }

        public void d(Object[] objArr) {
            build().d(objArr);
        }

        public void e(Object[] objArr) {
            build().e(objArr);
        }

        public void i(Object[] objArr) {
            build().i(objArr);
        }

        public void log(int i, String str, Object... objArr) {
            build().log(i, str, objArr);
        }

        public void v(String str, Object... objArr) {
            build().v(str, objArr);
        }

        public void w(String str, Object... objArr) {
            build().w(str, objArr);
        }

        public void d(String str, Object... objArr) {
            build().d(str, objArr);
        }

        public void e(String str, Object... objArr) {
            build().e(str, objArr);
        }

        public Builder enableStackTrace(String str, int i) {
            this.e = true;
            this.f = str;
            this.g = i;
            this.h = true;
            return this;
        }

        public void i(String str, Object... objArr) {
            build().i(str, objArr);
        }

        public void log(int i, String str) {
            build().log(i, str);
        }

        public void v(String str) {
            build().v(str);
        }

        public void w(String str) {
            build().w(str);
        }

        public void d(String str) {
            build().d(str);
        }

        public void e(String str) {
            build().e(str);
        }

        public void i(String str) {
            build().i(str);
        }

        public void log(int i, String str, Throwable th) {
            build().log(i, str, th);
        }

        public void v(String str, Throwable th) {
            build().v(str, th);
        }

        public void w(String str, Throwable th) {
            build().w(str, th);
        }

        public void d(String str, Throwable th) {
            build().d(str, th);
        }

        public void e(String str, Throwable th) {
            build().e(str, th);
        }

        public void i(String str, Throwable th) {
            build().i(str, th);
        }
    }

    public Logger(LogConfiguration logConfiguration, Printer printer) {
        this.f7865a = logConfiguration;
        this.b = printer;
    }

    public final String a(String str, Object... objArr) {
        if (str != null) {
            return String.format(str, objArr);
        }
        StringBuilder sb = new StringBuilder();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(objArr[i]);
        }
        return sb.toString();
    }

    public final <T> void b(int i, T t) {
        String str;
        LogConfiguration logConfiguration = this.f7865a;
        if (i < logConfiguration.logLevel) {
            return;
        }
        if (t != null) {
            ObjectFormatter<? super T> objectFormatter = logConfiguration.getObjectFormatter(t);
            if (objectFormatter != null) {
                str = objectFormatter.format(t);
            } else {
                str = t.toString();
            }
        } else {
            str = "null";
        }
        j(i, str);
    }

    public void c(int i, String str) {
        if (i < this.f7865a.logLevel) {
            return;
        }
        if (str == null) {
            str = "";
        }
        j(i, str);
    }

    public void d(Object obj) {
        b(3, obj);
    }

    public void e(Object obj) {
        b(6, obj);
    }

    public final void f(int i, String str, Throwable th) {
        String str2;
        if (i < this.f7865a.logLevel) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (str == null || str.length() == 0) {
            str2 = "";
        } else {
            str2 = str + SystemCompat.lineSeparator;
        }
        sb.append(str2);
        sb.append(this.f7865a.throwableFormatter.format(th));
        j(i, sb.toString());
    }

    public final void g(int i, String str, Object... objArr) {
        if (i < this.f7865a.logLevel) {
            return;
        }
        j(i, a(str, objArr));
    }

    public final void h(int i, Object[] objArr) {
        if (i < this.f7865a.logLevel) {
            return;
        }
        j(i, Arrays.deepToString(objArr));
    }

    public void i(Object obj) {
        b(4, obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(int r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elvishew.xlog.Logger.j(int, java.lang.String):void");
    }

    public void json(String str) {
        LogConfiguration logConfiguration = this.f7865a;
        if (3 < logConfiguration.logLevel) {
            return;
        }
        j(3, logConfiguration.jsonFormatter.format(str));
    }

    public void log(int i, Object obj) {
        b(i, obj);
    }

    public void v(Object obj) {
        b(2, obj);
    }

    public void w(Object obj) {
        b(5, obj);
    }

    public void xml(String str) {
        LogConfiguration logConfiguration = this.f7865a;
        if (3 < logConfiguration.logLevel) {
            return;
        }
        j(3, logConfiguration.xmlFormatter.format(str));
    }

    public void d(Object[] objArr) {
        h(3, objArr);
    }

    public void e(Object[] objArr) {
        h(6, objArr);
    }

    public void i(Object[] objArr) {
        h(4, objArr);
    }

    public void log(int i, Object[] objArr) {
        h(i, objArr);
    }

    public void v(Object[] objArr) {
        h(2, objArr);
    }

    public void w(Object[] objArr) {
        h(5, objArr);
    }

    public void d(String str, Object... objArr) {
        g(3, str, objArr);
    }

    public void e(String str, Object... objArr) {
        g(6, str, objArr);
    }

    public void i(String str, Object... objArr) {
        g(4, str, objArr);
    }

    public void log(int i, String str, Object... objArr) {
        g(i, str, objArr);
    }

    public void v(String str, Object... objArr) {
        g(2, str, objArr);
    }

    public void w(String str, Object... objArr) {
        g(5, str, objArr);
    }

    public Logger(Builder builder) {
        LogConfiguration.Builder builder2 = new LogConfiguration.Builder(XLog.b);
        if (builder.f7866a != 0) {
            builder2.logLevel(builder.f7866a);
        }
        if (builder.b != null) {
            builder2.tag(builder.b);
        }
        if (builder.d) {
            if (builder.c) {
                builder2.enableThreadInfo();
            } else {
                builder2.disableThreadInfo();
            }
        }
        if (builder.h) {
            if (builder.e) {
                builder2.enableStackTrace(builder.f, builder.g);
            } else {
                builder2.disableStackTrace();
            }
        }
        if (builder.j) {
            if (builder.i) {
                builder2.enableBorder();
            } else {
                builder2.disableBorder();
            }
        }
        if (builder.k != null) {
            builder2.jsonFormatter(builder.k);
        }
        if (builder.l != null) {
            builder2.xmlFormatter(builder.l);
        }
        if (builder.m != null) {
            builder2.throwableFormatter(builder.m);
        }
        if (builder.n != null) {
            builder2.threadFormatter(builder.n);
        }
        if (builder.o != null) {
            builder2.stackTraceFormatter(builder.o);
        }
        if (builder.p != null) {
            builder2.borderFormatter(builder.p);
        }
        if (builder.q != null) {
            builder2.r(builder.q);
        }
        if (builder.r != null) {
            builder2.q(builder.r);
        }
        this.f7865a = builder2.build();
        if (builder.s != null) {
            this.b = builder.s;
        } else {
            this.b = XLog.c;
        }
    }

    public void d(String str) {
        c(3, str);
    }

    public void e(String str) {
        c(6, str);
    }

    public void i(String str) {
        c(4, str);
    }

    public void log(int i, String str) {
        c(i, str);
    }

    public void v(String str) {
        c(2, str);
    }

    public void w(String str) {
        c(5, str);
    }

    public void d(String str, Throwable th) {
        f(3, str, th);
    }

    public void e(String str, Throwable th) {
        f(6, str, th);
    }

    public void i(String str, Throwable th) {
        f(4, str, th);
    }

    public void log(int i, String str, Throwable th) {
        f(i, str, th);
    }

    public void v(String str, Throwable th) {
        f(2, str, th);
    }

    public void w(String str, Throwable th) {
        f(5, str, th);
    }
}
