package com.elvishew.xlog;

import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.interceptor.Interceptor;
import com.elvishew.xlog.internal.DefaultsFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class LogConfiguration {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, ObjectFormatter<?>> f7863a;
    public final BorderFormatter borderFormatter;
    public final List<Interceptor> interceptors;
    public final JsonFormatter jsonFormatter;
    public final int logLevel;
    public final int stackTraceDepth;
    public final StackTraceFormatter stackTraceFormatter;
    public final String stackTraceOrigin;
    public final String tag;
    public final ThreadFormatter threadFormatter;
    public final ThrowableFormatter throwableFormatter;
    public final boolean withBorder;
    public final boolean withStackTrace;
    public final boolean withThread;
    public final XmlFormatter xmlFormatter;

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f7864a;
        public String b;
        public boolean c;
        public boolean d;
        public String e;
        public int f;
        public boolean g;
        public JsonFormatter h;
        public XmlFormatter i;
        public ThrowableFormatter j;
        public ThreadFormatter k;
        public StackTraceFormatter l;
        public BorderFormatter m;
        public Map<Class<?>, ObjectFormatter<?>> n;
        public List<Interceptor> o;

        public Builder() {
            this.f7864a = Integer.MIN_VALUE;
            this.b = "X-LOG";
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (this.o == null) {
                this.o = new ArrayList();
            }
            this.o.add(interceptor);
            return this;
        }

        public <T> Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
            if (this.n == null) {
                this.n = new HashMap(DefaultsFactory.builtinObjectFormatters());
            }
            this.n.put(cls, objectFormatter);
            return this;
        }

        public Builder borderFormatter(BorderFormatter borderFormatter) {
            this.m = borderFormatter;
            return this;
        }

        public LogConfiguration build() {
            p();
            return new LogConfiguration(this);
        }

        public Builder disableBorder() {
            this.g = false;
            return this;
        }

        public Builder disableStackTrace() {
            this.d = false;
            this.e = null;
            this.f = 0;
            return this;
        }

        public Builder disableThreadInfo() {
            this.c = false;
            return this;
        }

        public Builder enableBorder() {
            this.g = true;
            return this;
        }

        public Builder enableStackTrace(int i) {
            enableStackTrace(null, i);
            return this;
        }

        public Builder enableThreadInfo() {
            this.c = true;
            return this;
        }

        public Builder jsonFormatter(JsonFormatter jsonFormatter) {
            this.h = jsonFormatter;
            return this;
        }

        public Builder logLevel(int i) {
            this.f7864a = i;
            return this;
        }

        public Builder nb() {
            return disableBorder();
        }

        public Builder nst() {
            return disableStackTrace();
        }

        public Builder nt() {
            return disableThreadInfo();
        }

        public final void p() {
            if (this.h == null) {
                this.h = DefaultsFactory.createJsonFormatter();
            }
            if (this.i == null) {
                this.i = DefaultsFactory.createXmlFormatter();
            }
            if (this.j == null) {
                this.j = DefaultsFactory.createThrowableFormatter();
            }
            if (this.k == null) {
                this.k = DefaultsFactory.createThreadFormatter();
            }
            if (this.l == null) {
                this.l = DefaultsFactory.createStackTraceFormatter();
            }
            if (this.m == null) {
                this.m = DefaultsFactory.createBorderFormatter();
            }
            if (this.n == null) {
                this.n = new HashMap(DefaultsFactory.builtinObjectFormatters());
            }
        }

        public Builder q(List<Interceptor> list) {
            this.o = list;
            return this;
        }

        public Builder r(Map<Class<?>, ObjectFormatter<?>> map) {
            this.n = map;
            return this;
        }

        public Builder st(int i) {
            enableStackTrace(i);
            return this;
        }

        public Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
            this.l = stackTraceFormatter;
            return this;
        }

        public Builder t() {
            return enableThreadInfo();
        }

        public Builder tag(String str) {
            this.b = str;
            return this;
        }

        public Builder threadFormatter(ThreadFormatter threadFormatter) {
            this.k = threadFormatter;
            return this;
        }

        public Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
            this.j = throwableFormatter;
            return this;
        }

        public Builder xmlFormatter(XmlFormatter xmlFormatter) {
            this.i = xmlFormatter;
            return this;
        }

        public Builder b() {
            return enableBorder();
        }

        public Builder enableStackTrace(String str, int i) {
            this.d = true;
            this.e = str;
            this.f = i;
            return this;
        }

        public Builder st(String str, int i) {
            return enableStackTrace(str, i);
        }

        public Builder(LogConfiguration logConfiguration) {
            this.f7864a = Integer.MIN_VALUE;
            this.b = "X-LOG";
            this.f7864a = logConfiguration.logLevel;
            this.b = logConfiguration.tag;
            this.c = logConfiguration.withThread;
            this.d = logConfiguration.withStackTrace;
            this.e = logConfiguration.stackTraceOrigin;
            this.f = logConfiguration.stackTraceDepth;
            this.g = logConfiguration.withBorder;
            this.h = logConfiguration.jsonFormatter;
            this.i = logConfiguration.xmlFormatter;
            this.j = logConfiguration.throwableFormatter;
            this.k = logConfiguration.threadFormatter;
            this.l = logConfiguration.stackTraceFormatter;
            this.m = logConfiguration.borderFormatter;
            if (logConfiguration.f7863a != null) {
                this.n = new HashMap(logConfiguration.f7863a);
            }
            if (logConfiguration.interceptors != null) {
                this.o = new ArrayList(logConfiguration.interceptors);
            }
        }
    }

    public LogConfiguration(Builder builder) {
        this.logLevel = builder.f7864a;
        this.tag = builder.b;
        this.withThread = builder.c;
        this.withStackTrace = builder.d;
        this.stackTraceOrigin = builder.e;
        this.stackTraceDepth = builder.f;
        this.withBorder = builder.g;
        this.jsonFormatter = builder.h;
        this.xmlFormatter = builder.i;
        this.throwableFormatter = builder.j;
        this.threadFormatter = builder.k;
        this.stackTraceFormatter = builder.l;
        this.borderFormatter = builder.m;
        this.f7863a = builder.n;
        this.interceptors = builder.o;
    }

    public boolean b(int i) {
        return i >= this.logLevel;
    }

    public <T> ObjectFormatter<? super T> getObjectFormatter(T t) {
        ObjectFormatter<? super T> objectFormatter;
        if (this.f7863a == null) {
            return null;
        }
        Class<?> cls = t.getClass();
        do {
            objectFormatter = (ObjectFormatter<? super T>) this.f7863a.get(cls);
            cls = cls.getSuperclass();
            if (objectFormatter != null) {
                break;
            }
        } while (cls != null);
        return objectFormatter;
    }
}
