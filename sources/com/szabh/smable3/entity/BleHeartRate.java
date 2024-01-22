package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleHeartRate extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mBpm;
    private int mTime;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleHeartRate() {
        this(0, 0, 3, null);
    }

    public /* synthetic */ BleHeartRate(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public static /* synthetic */ BleHeartRate copy$default(BleHeartRate bleHeartRate, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleHeartRate.mTime;
        }
        if ((i3 & 2) != 0) {
            i2 = bleHeartRate.mBpm;
        }
        return bleHeartRate.copy(i, i2);
    }

    public final int component1() {
        return this.mTime;
    }

    public final int component2() {
        return this.mBpm;
    }

    @NotNull
    public final BleHeartRate copy(int i, int i2) {
        return new BleHeartRate(i, i2);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mTime = BleReadable.readInt32$default(this, null, 1, null);
        this.mBpm = readUInt8();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleHeartRate) {
            BleHeartRate bleHeartRate = (BleHeartRate) obj;
            return this.mTime == bleHeartRate.mTime && this.mBpm == bleHeartRate.mBpm;
        }
        return false;
    }

    public final int getMBpm() {
        return this.mBpm;
    }

    public final int getMTime() {
        return this.mTime;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mTime) * 31) + Integer.hashCode(this.mBpm);
    }

    public final void setMBpm(int i) {
        this.mBpm = i;
    }

    public final void setMTime(int i) {
        this.mTime = i;
    }

    @NotNull
    public String toString() {
        return "BleHeartRate(mTime=" + this.mTime + ", mBpm=" + this.mBpm + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleHeartRate(int i, int i2) {
        this.mTime = i;
        this.mBpm = i2;
    }
}
