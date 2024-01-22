package com.coveiot.android.bleabstract.models;

import android.bluetooth.BluetoothDevice;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ScanDevice {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f3445a;
    public int b;

    public ScanDevice(@Nullable BluetoothDevice bluetoothDevice, int i) {
        this.f3445a = bluetoothDevice;
        this.b = i;
    }

    public final int compareTo(@NotNull com.coveiot.sdk.ble.scan.model.BleDevice o2) {
        Intrinsics.checkNotNullParameter(o2, "o2");
        if (this.b > o2.getRssi()) {
            return 1;
        }
        return this.b < o2.getRssi() ? -1 : 0;
    }

    public boolean equals(@Nullable Object obj) {
        ScanDevice scanDevice = (ScanDevice) obj;
        BluetoothDevice bluetoothDevice = this.f3445a;
        Intrinsics.checkNotNull(bluetoothDevice);
        String address = bluetoothDevice.getAddress();
        Intrinsics.checkNotNull(scanDevice);
        BluetoothDevice bluetoothDevice2 = scanDevice.f3445a;
        Intrinsics.checkNotNull(bluetoothDevice2);
        return m.equals(address, bluetoothDevice2.getAddress(), true);
    }

    public final int getRssi() {
        return this.b;
    }

    @Nullable
    public final BluetoothDevice getmDevice() {
        return this.f3445a;
    }

    public int hashCode() {
        return Objects.hash(this.f3445a);
    }

    public final void setRssi(int i) {
        this.b = i;
    }

    public final void setmDevice(@NotNull BluetoothDevice mDevice) {
        Intrinsics.checkNotNullParameter(mDevice, "mDevice");
        this.f3445a = mDevice;
    }

    @NotNull
    public String toString() {
        return "BleDevice{mDevice=" + this.f3445a + ", rssi=" + this.b + '}';
    }
}
