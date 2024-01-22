package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleThirdPartyData extends BleWritable {
    public static final int ALIPAY_TYPE = 1;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private byte[] mData;
    private int mIndex;
    private int mSize;
    private int mType;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleThirdPartyData() {
        this(0, 0, 0, null, 15, null);
    }

    public /* synthetic */ BleThirdPartyData(int i, int i2, int i3, byte[] bArr, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3, (i4 & 8) != 0 ? new byte[0] : bArr);
    }

    public static /* synthetic */ BleThirdPartyData copy$default(BleThirdPartyData bleThirdPartyData, int i, int i2, int i3, byte[] bArr, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bleThirdPartyData.mType;
        }
        if ((i4 & 2) != 0) {
            i2 = bleThirdPartyData.mSize;
        }
        if ((i4 & 4) != 0) {
            i3 = bleThirdPartyData.mIndex;
        }
        if ((i4 & 8) != 0) {
            bArr = bleThirdPartyData.mData;
        }
        return bleThirdPartyData.copy(i, i2, i3, bArr);
    }

    public final int component1() {
        return this.mType;
    }

    public final int component2() {
        return this.mSize;
    }

    public final int component3() {
        return this.mIndex;
    }

    @NotNull
    public final byte[] component4() {
        return this.mData;
    }

    @NotNull
    public final BleThirdPartyData copy(int i, int i2, int i3, @NotNull byte[] mData) {
        Intrinsics.checkNotNullParameter(mData, "mData");
        return new BleThirdPartyData(i, i2, i3, mData);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mType = readInt8();
        this.mSize = BleReadable.readInt32$default(this, null, 1, null);
        this.mIndex = BleReadable.readInt32$default(this, null, 1, null);
        byte[] mBytes = getMBytes();
        Intrinsics.checkNotNull(mBytes);
        if (mBytes.length > 9) {
            byte[] mBytes2 = getMBytes();
            Intrinsics.checkNotNull(mBytes2);
            this.mData = readBytes(mBytes2.length - 9);
        }
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mType);
        BleWritable.writeInt32$default(this, this.mSize, null, 2, null);
        BleWritable.writeInt32$default(this, this.mIndex, null, 2, null);
        BleWritable.writeBytes$default(this, this.mData, null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleThirdPartyData) {
            BleThirdPartyData bleThirdPartyData = (BleThirdPartyData) obj;
            return this.mType == bleThirdPartyData.mType && this.mSize == bleThirdPartyData.mSize && this.mIndex == bleThirdPartyData.mIndex && Intrinsics.areEqual(this.mData, bleThirdPartyData.mData);
        }
        return false;
    }

    @NotNull
    public final byte[] getMData() {
        return this.mData;
    }

    public final int getMIndex() {
        return this.mIndex;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return this.mData.length + 9;
    }

    public final int getMSize() {
        return this.mSize;
    }

    public final int getMType() {
        return this.mType;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mType) * 31) + Integer.hashCode(this.mSize)) * 31) + Integer.hashCode(this.mIndex)) * 31) + Arrays.hashCode(this.mData);
    }

    public final void setMData(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mData = bArr;
    }

    public final void setMIndex(int i) {
        this.mIndex = i;
    }

    public final void setMSize(int i) {
        this.mSize = i;
    }

    public final void setMType(int i) {
        this.mType = i;
    }

    @NotNull
    public String toString() {
        return "BleThirdPartyData(mType=" + this.mType + ", mSize=" + this.mSize + ", mIndex=" + this.mIndex + ", mPacket=" + this.mData.length + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleThirdPartyData(int i, int i2, int i3, @NotNull byte[] mData) {
        Intrinsics.checkNotNullParameter(mData, "mData");
        this.mType = i;
        this.mSize = i2;
        this.mIndex = i3;
        this.mData = mData;
    }
}
