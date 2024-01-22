package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SendCallStatusRequest extends BleBaseRequest {
    @NotNull
    public final CallStatusType f;

    public SendCallStatusRequest(@NotNull CallStatusType callStatus) {
        Intrinsics.checkNotNullParameter(callStatus, "callStatus");
        this.f = callStatus;
    }

    @NotNull
    public final CallStatusType getCallStatus() {
        return this.f;
    }
}
