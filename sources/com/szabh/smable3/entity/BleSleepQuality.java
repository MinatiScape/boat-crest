package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleSleepQuality extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mDeep;
    private int mLight;
    private int mTotal;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleSleepQuality() {
        this(0, 0, 0, 7, null);
    }

    public /* synthetic */ BleSleepQuality(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public static /* synthetic */ BleSleepQuality copy$default(BleSleepQuality bleSleepQuality, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bleSleepQuality.mLight;
        }
        if ((i4 & 2) != 0) {
            i2 = bleSleepQuality.mDeep;
        }
        if ((i4 & 4) != 0) {
            i3 = bleSleepQuality.mTotal;
        }
        return bleSleepQuality.copy(i, i2, i3);
    }

    public final int component1() {
        return this.mLight;
    }

    public final int component2() {
        return this.mDeep;
    }

    public final int component3() {
        return this.mTotal;
    }

    @NotNull
    public final BleSleepQuality copy(int i, int i2, int i3) {
        return new BleSleepQuality(i, i2, i3);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mLight = readUInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mDeep = readUInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mTotal = readUInt16(LITTLE_ENDIAN);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        int i = this.mLight;
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt16(i, LITTLE_ENDIAN);
        int i2 = this.mDeep;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt16(i2, LITTLE_ENDIAN);
        int i3 = this.mTotal;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt16(i3, LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleSleepQuality) {
            BleSleepQuality bleSleepQuality = (BleSleepQuality) obj;
            return this.mLight == bleSleepQuality.mLight && this.mDeep == bleSleepQuality.mDeep && this.mTotal == bleSleepQuality.mTotal;
        }
        return false;
    }

    public final int getMDeep() {
        return this.mDeep;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 6;
    }

    public final int getMLight() {
        return this.mLight;
    }

    public final int getMTotal() {
        return this.mTotal;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.mLight) * 31) + Integer.hashCode(this.mDeep)) * 31) + Integer.hashCode(this.mTotal);
    }

    public final void setMDeep(int i) {
        this.mDeep = i;
    }

    public final void setMLight(int i) {
        this.mLight = i;
    }

    public final void setMTotal(int i) {
        this.mTotal = i;
    }

    @NotNull
    public String toString() {
        return "BleSleepQuality(mLight=" + this.mLight + ", mDeep=" + this.mDeep + ", mTotal=" + this.mTotal + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleSleepQuality(int i, int i2, int i3) {
        this.mLight = i;
        this.mDeep = i2;
        this.mTotal = i3;
    }
}
