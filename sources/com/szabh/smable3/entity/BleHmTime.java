package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleHmTime extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 2;
    private int mHour;
    private int mMinute;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleHmTime() {
        this(0, 0, 3, null);
    }

    public /* synthetic */ BleHmTime(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public static /* synthetic */ BleHmTime copy$default(BleHmTime bleHmTime, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleHmTime.mHour;
        }
        if ((i3 & 2) != 0) {
            i2 = bleHmTime.mMinute;
        }
        return bleHmTime.copy(i, i2);
    }

    public final int component1() {
        return this.mHour;
    }

    public final int component2() {
        return this.mMinute;
    }

    @NotNull
    public final BleHmTime copy(int i, int i2) {
        return new BleHmTime(i, i2);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mHour = readUInt8();
        this.mMinute = readUInt8();
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mHour);
        writeInt8(this.mMinute);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleHmTime) {
            BleHmTime bleHmTime = (BleHmTime) obj;
            return this.mHour == bleHmTime.mHour && this.mMinute == bleHmTime.mMinute;
        }
        return false;
    }

    public final int getMHour() {
        return this.mHour;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 2;
    }

    public final int getMMinute() {
        return this.mMinute;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mHour) * 31) + Integer.hashCode(this.mMinute);
    }

    public final void setMHour(int i) {
        this.mHour = i;
    }

    public final void setMMinute(int i) {
        this.mMinute = i;
    }

    @NotNull
    public String toString() {
        return "BleHmTime(mHour=" + this.mHour + ", mMinute=" + this.mMinute + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleHmTime(int i, int i2) {
        this.mHour = i;
        this.mMinute = i2;
    }
}
