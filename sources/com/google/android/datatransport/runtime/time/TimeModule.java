package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
@Module
/* loaded from: classes6.dex */
public abstract class TimeModule {
    @Provides
    @WallTime
    public static Clock a() {
        return new WallTimeClock();
    }

    @Provides
    @Monotonic
    public static Clock b() {
        return new UptimeClock();
    }
}
