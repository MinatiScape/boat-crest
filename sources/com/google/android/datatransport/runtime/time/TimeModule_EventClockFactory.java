package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class TimeModule_EventClockFactory implements Factory<Clock> {

    /* loaded from: classes6.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final TimeModule_EventClockFactory f8164a = new TimeModule_EventClockFactory();
    }

    public static TimeModule_EventClockFactory create() {
        return a.f8164a;
    }

    public static Clock eventClock() {
        return (Clock) Preconditions.checkNotNull(TimeModule.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Clock get() {
        return eventClock();
    }
}
