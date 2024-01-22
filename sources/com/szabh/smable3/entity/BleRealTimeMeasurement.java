package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleRealTimeMeasurement extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 1;
    private int mBOSwitch;
    private int mBPSwitch;
    private int mHRSwitch;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleRealTimeMeasurement() {
        this(0, 0, 0, 7, null);
    }

    public /* synthetic */ BleRealTimeMeasurement(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public static /* synthetic */ BleRealTimeMeasurement copy$default(BleRealTimeMeasurement bleRealTimeMeasurement, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bleRealTimeMeasurement.mHRSwitch;
        }
        if ((i4 & 2) != 0) {
            i2 = bleRealTimeMeasurement.mBOSwitch;
        }
        if ((i4 & 4) != 0) {
            i3 = bleRealTimeMeasurement.mBPSwitch;
        }
        return bleRealTimeMeasurement.copy(i, i2, i3);
    }

    public final int component1() {
        return this.mHRSwitch;
    }

    public final int component2() {
        return this.mBOSwitch;
    }

    public final int component3() {
        return this.mBPSwitch;
    }

    @NotNull
    public final BleRealTimeMeasurement copy(int i, int i2, int i3) {
        return new BleRealTimeMeasurement(i, i2, i3);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        readIntN(5);
        this.mBPSwitch = readIntN(1);
        this.mBOSwitch = readIntN(1);
        this.mHRSwitch = readIntN(1);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeIntN(0, 5);
        writeIntN(this.mBPSwitch, 1);
        writeIntN(this.mBOSwitch, 1);
        writeIntN(this.mHRSwitch, 1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleRealTimeMeasurement) {
            BleRealTimeMeasurement bleRealTimeMeasurement = (BleRealTimeMeasurement) obj;
            return this.mHRSwitch == bleRealTimeMeasurement.mHRSwitch && this.mBOSwitch == bleRealTimeMeasurement.mBOSwitch && this.mBPSwitch == bleRealTimeMeasurement.mBPSwitch;
        }
        return false;
    }

    public final int getMBOSwitch() {
        return this.mBOSwitch;
    }

    public final int getMBPSwitch() {
        return this.mBPSwitch;
    }

    public final int getMHRSwitch() {
        return this.mHRSwitch;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 1;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.mHRSwitch) * 31) + Integer.hashCode(this.mBOSwitch)) * 31) + Integer.hashCode(this.mBPSwitch);
    }

    public final void setMBOSwitch(int i) {
        this.mBOSwitch = i;
    }

    public final void setMBPSwitch(int i) {
        this.mBPSwitch = i;
    }

    public final void setMHRSwitch(int i) {
        this.mHRSwitch = i;
    }

    @NotNull
    public String toString() {
        return "BleRealTimeMeasurement(mHRSwitch=" + this.mHRSwitch + ", mBOSwitch=" + this.mBOSwitch + ", mBPSwitch=" + this.mBPSwitch + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleRealTimeMeasurement(int i, int i2, int i3) {
        this.mHRSwitch = i;
        this.mBOSwitch = i2;
        this.mBPSwitch = i3;
    }
}
