package com.bestmafen.baseble.scanner;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class AddressFilter implements BleScanFilter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f2224a;

    public AddressFilter(@NotNull String mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.f2224a = mAddress;
    }

    @NotNull
    public final String getMAddress() {
        return this.f2224a;
    }

    @Override // com.bestmafen.baseble.scanner.BleScanFilter
    public boolean match(@NotNull BleDevice device) {
        Intrinsics.checkNotNullParameter(device, "device");
        return m.equals(this.f2224a, device.getMBluetoothDevice().getAddress(), true);
    }

    public final void setMAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f2224a = str;
    }
}
