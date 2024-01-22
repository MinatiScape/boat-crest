package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.request.BleBaseRequest;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class BleBaseResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public BleBaseRequest f3582a;
    @Nullable
    public Object b;
    public boolean c;

    public BleBaseResponse(@NotNull BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "bleBaseRequest");
        this.f3582a = bleBaseRequest;
    }

    @NotNull
    public final BleBaseRequest getBleBaseRequest() {
        return this.f3582a;
    }

    @Nullable
    public final Object getResponseData() {
        return this.b;
    }

    public final boolean isCompleted() {
        return this.c;
    }

    public final void setBleBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
        this.f3582a = bleBaseRequest;
    }

    public final void setCompleted(boolean z) {
        this.c = z;
    }

    public final void setResponseData(@Nullable Object obj) {
        this.b = obj;
    }
}
