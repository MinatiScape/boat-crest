package com.coveiot.android.activitymodes.models;

import com.coveiot.android.activitymodes.database.entities.SampleData;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivitySampleData {

    /* renamed from: a  reason: collision with root package name */
    public int f2846a;
    @NotNull
    public SampleData b = new SampleData();

    @NotNull
    public final SampleData getSampleData() {
        return this.b;
    }

    public final int getStepCount() {
        return this.f2846a;
    }

    public final void setSampleData(@NotNull SampleData sampleData) {
        Intrinsics.checkNotNullParameter(sampleData, "<set-?>");
        this.b = sampleData;
    }

    public final void setStepCount(int i) {
        this.f2846a = i;
    }
}
