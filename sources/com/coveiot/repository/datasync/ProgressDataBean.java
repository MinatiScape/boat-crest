package com.coveiot.repository.datasync;

import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.repository.ActivityType;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class ProgressDataBean {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ProgressType f7358a;
    public int b;
    @NotNull
    public ActivityType c;
    public int d;
    public int e;
    public int f;

    public ProgressDataBean(@NotNull ProgressType progressType, int i, @NotNull ActivityType activityType, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(progressType, "progressType");
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        this.f7358a = progressType;
        this.b = i;
        this.c = activityType;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    @NotNull
    public final ActivityType getActivityType() {
        return this.c;
    }

    public final int getPercentByStage() {
        return this.f;
    }

    public final int getProgress() {
        return this.b;
    }

    @NotNull
    public final ProgressType getProgressType() {
        return this.f7358a;
    }

    public final int getStageProgress() {
        return this.e;
    }

    public final int getTotalStage() {
        return this.d;
    }

    public final void setActivityType(@NotNull ActivityType activityType) {
        Intrinsics.checkNotNullParameter(activityType, "<set-?>");
        this.c = activityType;
    }

    public final void setPercentByStage(int i) {
        this.f = i;
    }

    public final void setProgress(int i) {
        this.b = i;
    }

    public final void setProgressType(@NotNull ProgressType progressType) {
        Intrinsics.checkNotNullParameter(progressType, "<set-?>");
        this.f7358a = progressType;
    }

    public final void setStageProgress(int i) {
        this.e = i;
    }

    public final void setTotalStage(int i) {
        this.d = i;
    }
}
