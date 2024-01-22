package com.szabh.smable3.entity;

import com.szabh.smable3.BleKey;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleStream {
    @NotNull
    private final BleKey mBleKey;
    @NotNull
    private final byte[] mData;
    private final int mType;

    public BleStream(@NotNull BleKey mBleKey, int i, @NotNull byte[] mData) {
        Intrinsics.checkNotNullParameter(mBleKey, "mBleKey");
        Intrinsics.checkNotNullParameter(mData, "mData");
        this.mBleKey = mBleKey;
        this.mType = i;
        this.mData = mData;
    }

    @NotNull
    public final BleKey getMBleKey() {
        return this.mBleKey;
    }

    @Nullable
    public final BleStreamPacket getPacket(int i, long j) {
        byte[] bArr;
        byte[] bArr2;
        if (i >= this.mData.length) {
            return null;
        }
        long j2 = i;
        long j3 = j + j2;
        if (j3 > bArr.length) {
            int i2 = this.mType;
            byte[] bArr3 = this.mData;
            return new BleStreamPacket(i2, bArr3.length, j2, ArraysKt___ArraysJvmKt.copyOfRange(bArr3, i, bArr3.length));
        }
        return new BleStreamPacket(this.mType, bArr2.length, j2, ArraysKt___ArraysJvmKt.copyOfRange(this.mData, i, (int) j3));
    }
}
