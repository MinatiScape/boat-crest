package com.coveiot.android.leonardo.dashboard.health.spo2.model;

import com.coveiot.android.bleabstract.models.BleDevice;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2BluetoothDeviceInfo {
    @Nullable
    private BleDevice bleDevice;
    private boolean isConnecting;
    @NotNull
    private String previousDeviceName = "";

    @Nullable
    public final BleDevice getBleDevice() {
        return this.bleDevice;
    }

    @NotNull
    public final String getPreviousDeviceName() {
        return this.previousDeviceName;
    }

    public final boolean isConnecting() {
        return this.isConnecting;
    }

    public final void setBleDevice(@Nullable BleDevice bleDevice) {
        this.bleDevice = bleDevice;
    }

    public final void setConnecting(boolean z) {
        this.isConnecting = z;
    }

    public final void setPreviousDeviceName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.previousDeviceName = str;
    }
}
