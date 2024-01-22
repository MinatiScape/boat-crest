package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleTemperatureDetecting extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    @NotNull
    private BleTimeRange mBleTimeRange;
    private int mInterval;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleTemperatureDetecting() {
        this(null, 0, 3, null);
    }

    public /* synthetic */ BleTemperatureDetecting(BleTimeRange bleTimeRange, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new BleTimeRange(0, 0, 0, 0, 0, 31, null) : bleTimeRange, (i2 & 2) != 0 ? 60 : i);
    }

    public static /* synthetic */ BleTemperatureDetecting copy$default(BleTemperatureDetecting bleTemperatureDetecting, BleTimeRange bleTimeRange, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bleTimeRange = bleTemperatureDetecting.mBleTimeRange;
        }
        if ((i2 & 2) != 0) {
            i = bleTemperatureDetecting.mInterval;
        }
        return bleTemperatureDetecting.copy(bleTimeRange, i);
    }

    @NotNull
    public final BleTimeRange component1() {
        return this.mBleTimeRange;
    }

    public final int component2() {
        return this.mInterval;
    }

    @NotNull
    public final BleTemperatureDetecting copy(@NotNull BleTimeRange mBleTimeRange, int i) {
        Intrinsics.checkNotNullParameter(mBleTimeRange, "mBleTimeRange");
        return new BleTemperatureDetecting(mBleTimeRange, i);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        BleReadable t = (BleReadable) BleTimeRange.class.newInstance();
        t.setMBytes(readBytes(5));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        this.mBleTimeRange = (BleTimeRange) t;
        this.mInterval = readUInt8() * 5;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeObject(this.mBleTimeRange);
        writeInt8(this.mInterval / 5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleTemperatureDetecting) {
            BleTemperatureDetecting bleTemperatureDetecting = (BleTemperatureDetecting) obj;
            return Intrinsics.areEqual(this.mBleTimeRange, bleTemperatureDetecting.mBleTimeRange) && this.mInterval == bleTemperatureDetecting.mInterval;
        }
        return false;
    }

    @NotNull
    public final BleTimeRange getMBleTimeRange() {
        return this.mBleTimeRange;
    }

    public final int getMInterval() {
        return this.mInterval;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 6;
    }

    public int hashCode() {
        return (this.mBleTimeRange.hashCode() * 31) + Integer.hashCode(this.mInterval);
    }

    public final void setMBleTimeRange(@NotNull BleTimeRange bleTimeRange) {
        Intrinsics.checkNotNullParameter(bleTimeRange, "<set-?>");
        this.mBleTimeRange = bleTimeRange;
    }

    public final void setMInterval(int i) {
        this.mInterval = i;
    }

    @NotNull
    public String toString() {
        return "BleTemperatureDetecting(mBleTimeRange=" + this.mBleTimeRange + ", mInterval=" + this.mInterval + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleTemperatureDetecting(@NotNull BleTimeRange mBleTimeRange, int i) {
        Intrinsics.checkNotNullParameter(mBleTimeRange, "mBleTimeRange");
        this.mBleTimeRange = mBleTimeRange;
        this.mInterval = i;
    }
}