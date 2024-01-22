package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class BleBaseRequest {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public BleCommand f3476a;
    @Nullable
    public BaseListener b;
    @Nullable
    public String c;
    public boolean d;
    @NotNull
    public DevicePlatformEnum e = DevicePlatformEnum.Nordic;

    @Nullable
    public BleCommand getBleCommand() {
        return this.f3476a;
    }

    @NotNull
    public final DevicePlatformEnum getDevicePlatformEnum() {
        return this.e;
    }

    @Nullable
    public final String getRequId() {
        return this.c;
    }

    @Nullable
    public final BaseListener getResponseListener() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.d;
    }

    public void setBleCommand(@Nullable BleCommand bleCommand) {
        this.f3476a = bleCommand;
    }

    public final void setComplete(boolean z) {
        this.d = z;
    }

    public final void setDevicePlatformEnum(@NotNull DevicePlatformEnum devicePlatformEnum) {
        Intrinsics.checkNotNullParameter(devicePlatformEnum, "<set-?>");
        this.e = devicePlatformEnum;
    }

    public final void setRequId(@Nullable String str) {
        this.c = str;
    }

    public final void setResponseListener(@Nullable BaseListener baseListener) {
        this.b = baseListener;
    }
}
