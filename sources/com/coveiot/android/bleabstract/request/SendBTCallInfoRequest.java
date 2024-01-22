package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SendBTCallInfoRequest extends BleBaseRequest {
    public final short f;
    @NotNull
    public final String g;
    @Nullable
    public final String h;

    public /* synthetic */ SendBTCallInfoRequest(short s, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(s, str, (i & 4) != 0 ? null : str2);
    }

    public final short getCallStatus() {
        return this.f;
    }

    @Nullable
    public final String getName() {
        return this.h;
    }

    @NotNull
    public final String getNumber() {
        return this.g;
    }

    public SendBTCallInfoRequest(short s, @NotNull String number, @Nullable String str) {
        Intrinsics.checkNotNullParameter(number, "number");
        this.f = s;
        this.g = number;
        this.h = str;
    }
}
