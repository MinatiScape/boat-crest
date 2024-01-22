package com.bestmafen.baseble.scanner;

import android.bluetooth.BluetoothDevice;
import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class BleDevice {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f2225a;
    public int b;
    @Nullable
    public byte[] c;
    @NotNull
    public String d;
    public int e;

    public BleDevice(@NotNull BluetoothDevice mBluetoothDevice, int i, @Nullable byte[] bArr, @NotNull String mName, int i2) {
        Intrinsics.checkNotNullParameter(mBluetoothDevice, "mBluetoothDevice");
        Intrinsics.checkNotNullParameter(mName, "mName");
        this.f2225a = mBluetoothDevice;
        this.b = i;
        this.c = bArr;
        this.d = mName;
        this.e = i2;
    }

    public static /* synthetic */ BleDevice copy$default(BleDevice bleDevice, BluetoothDevice bluetoothDevice, int i, byte[] bArr, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bluetoothDevice = bleDevice.f2225a;
        }
        if ((i3 & 2) != 0) {
            i = bleDevice.b;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            bArr = bleDevice.c;
        }
        byte[] bArr2 = bArr;
        if ((i3 & 8) != 0) {
            str = bleDevice.d;
        }
        String str2 = str;
        if ((i3 & 16) != 0) {
            i2 = bleDevice.e;
        }
        return bleDevice.copy(bluetoothDevice, i4, bArr2, str2, i2);
    }

    @NotNull
    public final BluetoothDevice component1() {
        return this.f2225a;
    }

    public final int component2() {
        return this.b;
    }

    @Nullable
    public final byte[] component3() {
        return this.c;
    }

    @NotNull
    public final String component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    @NotNull
    public final BleDevice copy(@NotNull BluetoothDevice mBluetoothDevice, int i, @Nullable byte[] bArr, @NotNull String mName, int i2) {
        Intrinsics.checkNotNullParameter(mBluetoothDevice, "mBluetoothDevice");
        Intrinsics.checkNotNullParameter(mName, "mName");
        return new BleDevice(mBluetoothDevice, i, bArr, mName, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof BleDevice) {
            return Intrinsics.areEqual(this.f2225a, ((BleDevice) obj).f2225a);
        }
        return false;
    }

    @NotNull
    public final BluetoothDevice getMBluetoothDevice() {
        return this.f2225a;
    }

    @NotNull
    public final String getMName() {
        return this.d;
    }

    public final int getMRssi() {
        return this.b;
    }

    @Nullable
    public final byte[] getMScanRecord() {
        return this.c;
    }

    public final int getMType() {
        return this.e;
    }

    public int hashCode() {
        return this.f2225a.hashCode();
    }

    public final void setMBluetoothDevice(@NotNull BluetoothDevice bluetoothDevice) {
        Intrinsics.checkNotNullParameter(bluetoothDevice, "<set-?>");
        this.f2225a = bluetoothDevice;
    }

    public final void setMName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setMRssi(int i) {
        this.b = i;
    }

    public final void setMScanRecord(@Nullable byte[] bArr) {
        this.c = bArr;
    }

    public final void setMType(int i) {
        this.e = i;
    }

    @NotNull
    public String toString() {
        return "BleDevice(type=" + this.e + ", name=" + this.f2225a.getName() + ", address=" + this.f2225a.getAddress() + ", mRssi=" + this.b + ", mScanRecord=" + ByteArrayExtKt.getMHexString(this.c) + HexStringBuilder.COMMENT_END_CHAR;
    }
}
