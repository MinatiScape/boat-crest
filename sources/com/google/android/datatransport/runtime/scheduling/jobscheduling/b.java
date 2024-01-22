package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class b extends SchedulerConfig {

    /* renamed from: a  reason: collision with root package name */
    public final Clock f8123a;
    public final Map<Priority, SchedulerConfig.ConfigValue> b;

    public b(Clock clock, Map<Priority, SchedulerConfig.ConfigValue> map) {
        Objects.requireNonNull(clock, "Null clock");
        this.f8123a = clock;
        Objects.requireNonNull(map, "Null values");
        this.b = map;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    public Clock c() {
        return this.f8123a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    public Map<Priority, SchedulerConfig.ConfigValue> d() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SchedulerConfig) {
            SchedulerConfig schedulerConfig = (SchedulerConfig) obj;
            return this.f8123a.equals(schedulerConfig.c()) && this.b.equals(schedulerConfig.d());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f8123a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "SchedulerConfig{clock=" + this.f8123a + ", values=" + this.b + "}";
    }
}
