package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;
/* loaded from: classes13.dex */
public class EventRecodingLogger implements Logger {
    public String h;
    public SubstituteLogger i;
    public Queue<SubstituteLoggingEvent> j;

    public EventRecodingLogger(SubstituteLogger substituteLogger, Queue<SubstituteLoggingEvent> queue) {
        this.i = substituteLogger;
        this.h = substituteLogger.getName();
        this.j = queue;
    }

    public final void a(Level level, String str, Object[] objArr, Throwable th) {
        b(level, null, str, objArr, th);
    }

    public final void b(Level level, Marker marker, String str, Object[] objArr, Throwable th) {
        SubstituteLoggingEvent substituteLoggingEvent = new SubstituteLoggingEvent();
        substituteLoggingEvent.setTimeStamp(System.currentTimeMillis());
        substituteLoggingEvent.setLevel(level);
        substituteLoggingEvent.setLogger(this.i);
        substituteLoggingEvent.setLoggerName(this.h);
        substituteLoggingEvent.setMessage(str);
        substituteLoggingEvent.setArgumentArray(objArr);
        substituteLoggingEvent.setThrowable(th);
        substituteLoggingEvent.setThreadName(Thread.currentThread().getName());
        this.j.add(substituteLoggingEvent);
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        a(Level.TRACE, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        a(Level.ERROR, str, null, null);
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.h;
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        a(Level.INFO, str, null, null);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        a(Level.TRACE, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        a(Level.WARN, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        a(Level.DEBUG, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        a(Level.ERROR, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        a(Level.INFO, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        a(Level.TRACE, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        a(Level.WARN, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        a(Level.DEBUG, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        a(Level.ERROR, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        a(Level.INFO, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        a(Level.TRACE, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        a(Level.WARN, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        a(Level.DEBUG, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        a(Level.ERROR, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        a(Level.INFO, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        a(Level.TRACE, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        a(Level.WARN, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        a(Level.DEBUG, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        a(Level.ERROR, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        a(Level.INFO, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        a(Level.TRACE, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        a(Level.WARN, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str) {
        b(Level.DEBUG, marker, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str) {
        b(Level.ERROR, marker, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str) {
        b(Level.INFO, marker, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str) {
        b(Level.TRACE, marker, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str) {
        a(Level.WARN, str, null, null);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj) {
        b(Level.DEBUG, marker, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj) {
        b(Level.ERROR, marker, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj) {
        b(Level.INFO, marker, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj) {
        b(Level.TRACE, marker, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj) {
        a(Level.WARN, str, new Object[]{obj}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2) {
        b(Level.DEBUG, marker, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2) {
        b(Level.ERROR, marker, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2) {
        b(Level.INFO, marker, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2) {
        b(Level.TRACE, marker, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2) {
        b(Level.WARN, marker, str, new Object[]{obj, obj2}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object... objArr) {
        b(Level.DEBUG, marker, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object... objArr) {
        b(Level.ERROR, marker, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object... objArr) {
        b(Level.INFO, marker, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object... objArr) {
        b(Level.TRACE, marker, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object... objArr) {
        b(Level.WARN, marker, str, objArr, null);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Throwable th) {
        b(Level.DEBUG, marker, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Throwable th) {
        b(Level.ERROR, marker, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Throwable th) {
        b(Level.INFO, marker, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Throwable th) {
        b(Level.TRACE, marker, str, null, th);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Throwable th) {
        b(Level.WARN, marker, str, null, th);
    }
}
