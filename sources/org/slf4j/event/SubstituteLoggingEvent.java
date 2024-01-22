package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;
/* loaded from: classes13.dex */
public class SubstituteLoggingEvent implements LoggingEvent {

    /* renamed from: a  reason: collision with root package name */
    public Level f15572a;
    public Marker b;
    public String c;
    public SubstituteLogger d;
    public String e;
    public String f;
    public Object[] g;
    public long h;
    public Throwable i;

    @Override // org.slf4j.event.LoggingEvent
    public Object[] getArgumentArray() {
        return this.g;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Level getLevel() {
        return this.f15572a;
    }

    public SubstituteLogger getLogger() {
        return this.d;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getLoggerName() {
        return this.c;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Marker getMarker() {
        return this.b;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getMessage() {
        return this.f;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getThreadName() {
        return this.e;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Throwable getThrowable() {
        return this.i;
    }

    @Override // org.slf4j.event.LoggingEvent
    public long getTimeStamp() {
        return this.h;
    }

    public void setArgumentArray(Object[] objArr) {
        this.g = objArr;
    }

    public void setLevel(Level level) {
        this.f15572a = level;
    }

    public void setLogger(SubstituteLogger substituteLogger) {
        this.d = substituteLogger;
    }

    public void setLoggerName(String str) {
        this.c = str;
    }

    public void setMarker(Marker marker) {
        this.b = marker;
    }

    public void setMessage(String str) {
        this.f = str;
    }

    public void setThreadName(String str) {
        this.e = str;
    }

    public void setThrowable(Throwable th) {
        this.i = th;
    }

    public void setTimeStamp(long j) {
        this.h = j;
    }
}
