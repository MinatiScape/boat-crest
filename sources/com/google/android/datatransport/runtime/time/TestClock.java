package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes6.dex */
public class TestClock implements Clock {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicLong f8163a;

    public TestClock(long j) {
        this.f8163a = new AtomicLong(j);
    }

    public void advance(long j) {
        if (j >= 0) {
            this.f8163a.addAndGet(j);
            return;
        }
        throw new IllegalArgumentException("cannot advance time backwards.");
    }

    @Override // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return this.f8163a.get();
    }

    public void tick() {
        advance(1L);
    }
}
