package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.SubstituteLoggingEvent;
/* loaded from: classes13.dex */
public class SubstituteLoggerFactory implements ILoggerFactory {

    /* renamed from: a  reason: collision with root package name */
    public boolean f15576a = false;
    public final Map<String, SubstituteLogger> b = new HashMap();
    public final LinkedBlockingQueue<SubstituteLoggingEvent> c = new LinkedBlockingQueue<>();

    public void clear() {
        this.b.clear();
        this.c.clear();
    }

    public LinkedBlockingQueue<SubstituteLoggingEvent> getEventQueue() {
        return this.c;
    }

    @Override // org.slf4j.ILoggerFactory
    public synchronized Logger getLogger(String str) {
        SubstituteLogger substituteLogger;
        substituteLogger = this.b.get(str);
        if (substituteLogger == null) {
            substituteLogger = new SubstituteLogger(str, this.c, this.f15576a);
            this.b.put(str, substituteLogger);
        }
        return substituteLogger;
    }

    public List<String> getLoggerNames() {
        return new ArrayList(this.b.keySet());
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList(this.b.values());
    }

    public void postInitialization() {
        this.f15576a = true;
    }
}
