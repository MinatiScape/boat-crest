package com.coveiot.android.bleabstract.models;

import com.coveiot.android.bleabstract.request.BleBaseRequest;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ProgressData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ProgressType f3441a;
    public int b;
    @NotNull
    public BleBaseRequest c;

    public ProgressData(@NotNull ProgressType progressType, int i, @NotNull BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(progressType, "progressType");
        Intrinsics.checkNotNullParameter(bleBaseRequest, "bleBaseRequest");
        this.f3441a = progressType;
        this.b = i;
        this.c = bleBaseRequest;
    }

    @NotNull
    public final BleBaseRequest getBleBaseRequest() {
        return this.c;
    }

    public final int getProgress() {
        return this.b;
    }

    @NotNull
    public final ProgressType getProgressType() {
        return this.f3441a;
    }

    public final void setBleBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
        this.c = bleBaseRequest;
    }

    public final void setProgress(int i) {
        this.b = i;
    }

    public final void setProgressType(@NotNull ProgressType progressType) {
        Intrinsics.checkNotNullParameter(progressType, "<set-?>");
        this.f3441a = progressType;
    }
}
