package com.coveiot.android.bleabstract.models;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityPauseSegment {

    /* renamed from: a  reason: collision with root package name */
    public int f3393a = -1;
    public int b = -1;

    @NotNull
    public final ActivityPauseSegment copy() {
        ActivityPauseSegment activityPauseSegment = new ActivityPauseSegment();
        activityPauseSegment.f3393a = this.f3393a;
        activityPauseSegment.b = this.b;
        return activityPauseSegment;
    }

    public final int getEndTime() {
        return this.b;
    }

    public final int getStartTime() {
        return this.f3393a;
    }

    public final void setEndTime(int i) {
        this.b = i;
    }

    public final void setStartTime(int i) {
        this.f3393a = i;
    }
}
