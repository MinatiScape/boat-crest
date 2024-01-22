package com.coveiot.android.touchsdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetCallNotificationReq extends TouchELXBaseReq {
    @NotNull
    public String e;
    @NotNull
    public String f;

    public TouchSetCallNotificationReq(@NotNull String name, @NotNull String number) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(number, "number");
        this.e = name;
        this.f = number;
    }

    @NotNull
    public final String getName() {
        return this.e;
    }

    @NotNull
    public final String getNumber() {
        return this.f;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }
}
