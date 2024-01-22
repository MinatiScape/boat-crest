package org.slf4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.EventRecodingLogger;
import org.slf4j.event.LoggingEvent;
import org.slf4j.event.SubstituteLoggingEvent;
/* loaded from: classes13.dex */
public class SubstituteLogger implements Logger {
    public final String h;
    public volatile Logger i;
    public Boolean j;
    public Method k;
    public EventRecodingLogger l;
    public Queue<SubstituteLoggingEvent> m;
    public final boolean n;

    public SubstituteLogger(String str, Queue<SubstituteLoggingEvent> queue, boolean z) {
        this.h = str;
        this.m = queue;
        this.n = z;
    }

    public Logger a() {
        if (this.i != null) {
            return this.i;
        }
        if (this.n) {
            return NOPLogger.NOP_LOGGER;
        }
        return b();
    }

    public final Logger b() {
        if (this.l == null) {
            this.l = new EventRecodingLogger(this, this.m);
        }
        return this.l;
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        a().debug(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.h.equals(((SubstituteLogger) obj).h);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        a().error(str);
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.h;
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        a().info(str);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return a().isDebugEnabled();
    }

    public boolean isDelegateEventAware() {
        Boolean bool = this.j;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.k = this.i.getClass().getMethod("log", LoggingEvent.class);
            this.j = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.j = Boolean.FALSE;
        }
        return this.j.booleanValue();
    }

    public boolean isDelegateNOP() {
        return this.i instanceof NOPLogger;
    }

    public boolean isDelegateNull() {
        return this.i == null;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return a().isErrorEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return a().isInfoEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return a().isTraceEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return a().isWarnEnabled();
    }

    public void log(LoggingEvent loggingEvent) {
        if (isDelegateEventAware()) {
            try {
                this.k.invoke(this.i, loggingEvent);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }

    public void setDelegate(Logger logger) {
        this.i = logger;
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        a().trace(str);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        a().warn(str);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        a().debug(str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        a().error(str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        a().info(str, obj);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return a().isDebugEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return a().isErrorEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return a().isInfoEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return a().isTraceEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return a().isWarnEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        a().trace(str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        a().warn(str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        a().debug(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        a().error(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        a().info(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        a().trace(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        a().warn(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        a().debug(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        a().error(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        a().info(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        a().trace(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        a().warn(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        a().debug(str, th);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        a().error(str, th);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        a().info(str, th);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        a().trace(str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        a().warn(str, th);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str) {
        a().debug(marker, str);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str) {
        a().error(marker, str);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str) {
        a().info(marker, str);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str) {
        a().trace(marker, str);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str) {
        a().warn(marker, str);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj) {
        a().debug(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj) {
        a().error(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj) {
        a().info(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj) {
        a().trace(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj) {
        a().warn(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2) {
        a().debug(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2) {
        a().error(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2) {
        a().info(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2) {
        a().trace(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2) {
        a().warn(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object... objArr) {
        a().debug(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object... objArr) {
        a().error(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object... objArr) {
        a().info(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object... objArr) {
        a().trace(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object... objArr) {
        a().warn(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Throwable th) {
        a().debug(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Throwable th) {
        a().error(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Throwable th) {
        a().info(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Throwable th) {
        a().trace(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Throwable th) {
        a().warn(marker, str, th);
    }
}
