package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleAlipaySettings extends BleWritable {
    public static final int ADDRESS_LENGTH = 6;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 10;
    private int isActivate;
    @NotNull
    private byte[] mAddress;
    private int mAlipayVersion;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleAlipaySettings() {
        this(0, 0, null, 7, null);
    }

    public /* synthetic */ BleAlipaySettings(int i, int i2, byte[] bArr, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? new byte[6] : bArr);
    }

    public static /* synthetic */ BleAlipaySettings copy$default(BleAlipaySettings bleAlipaySettings, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleAlipaySettings.mAlipayVersion;
        }
        if ((i3 & 2) != 0) {
            i2 = bleAlipaySettings.isActivate;
        }
        if ((i3 & 4) != 0) {
            bArr = bleAlipaySettings.mAddress;
        }
        return bleAlipaySettings.copy(i, i2, bArr);
    }

    public final int component1() {
        return this.mAlipayVersion;
    }

    public final int component2() {
        return this.isActivate;
    }

    @NotNull
    public final byte[] component3() {
        return this.mAddress;
    }

    @NotNull
    public final BleAlipaySettings copy(int i, int i2, @NotNull byte[] mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        return new BleAlipaySettings(i, i2, mAddress);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mAlipayVersion = readUInt8();
        this.isActivate = readUInt8();
        this.mAddress = readBytes(6);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mAlipayVersion);
        writeInt8(this.isActivate);
        BleWritable.writeBytes$default(this, ArraysKt___ArraysKt.reversedArray(this.mAddress), null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleAlipaySettings) {
            BleAlipaySettings bleAlipaySettings = (BleAlipaySettings) obj;
            return this.mAlipayVersion == bleAlipaySettings.mAlipayVersion && this.isActivate == bleAlipaySettings.isActivate && Intrinsics.areEqual(this.mAddress, bleAlipaySettings.mAddress);
        }
        return false;
    }

    @NotNull
    public final byte[] getMAddress() {
        return this.mAddress;
    }

    public final int getMAlipayVersion() {
        return this.mAlipayVersion;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 10;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.mAlipayVersion) * 31) + Integer.hashCode(this.isActivate)) * 31) + Arrays.hashCode(this.mAddress);
    }

    public final int isActivate() {
        return this.isActivate;
    }

    public final void setActivate(int i) {
        this.isActivate = i;
    }

    public final void setMAddress(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mAddress = bArr;
    }

    public final void setMAlipayVersion(int i) {
        this.mAlipayVersion = i;
    }

    @NotNull
    public String toString() {
        return "BleAlipaySettings(mAlipayVersion=" + this.mAlipayVersion + ", isActivate=" + this.isActivate + ", mAddress=" + Arrays.toString(this.mAddress) + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleAlipaySettings(int i, int i2, @NotNull byte[] mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.mAlipayVersion = i;
        this.isActivate = i2;
        this.mAddress = mAddress;
    }
}
