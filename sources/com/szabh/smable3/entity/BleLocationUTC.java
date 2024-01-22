package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleLocationUTC extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 8;
    private int mHour;
    private int mMilli;
    private int mMinute;
    private int mSecond;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleLocationUTC() {
        this(0, 0, 0, 0, 15, null);
    }

    public /* synthetic */ BleLocationUTC(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    public static /* synthetic */ BleLocationUTC copy$default(BleLocationUTC bleLocationUTC, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = bleLocationUTC.mHour;
        }
        if ((i5 & 2) != 0) {
            i2 = bleLocationUTC.mMinute;
        }
        if ((i5 & 4) != 0) {
            i3 = bleLocationUTC.mSecond;
        }
        if ((i5 & 8) != 0) {
            i4 = bleLocationUTC.mMilli;
        }
        return bleLocationUTC.copy(i, i2, i3, i4);
    }

    public final int component1() {
        return this.mHour;
    }

    public final int component2() {
        return this.mMinute;
    }

    public final int component3() {
        return this.mSecond;
    }

    public final int component4() {
        return this.mMilli;
    }

    @NotNull
    public final BleLocationUTC copy(int i, int i2, int i3, int i4) {
        return new BleLocationUTC(i, i2, i3, i4);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mHour = readUInt8();
        this.mMinute = readUInt8();
        this.mSecond = readUInt8();
        skip(8);
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mMilli = readUInt16(LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleLocationUTC) {
            BleLocationUTC bleLocationUTC = (BleLocationUTC) obj;
            return this.mHour == bleLocationUTC.mHour && this.mMinute == bleLocationUTC.mMinute && this.mSecond == bleLocationUTC.mSecond && this.mMilli == bleLocationUTC.mMilli;
        }
        return false;
    }

    public final int getMHour() {
        return this.mHour;
    }

    public final int getMMilli() {
        return this.mMilli;
    }

    public final int getMMinute() {
        return this.mMinute;
    }

    public final int getMSecond() {
        return this.mSecond;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mHour) * 31) + Integer.hashCode(this.mMinute)) * 31) + Integer.hashCode(this.mSecond)) * 31) + Integer.hashCode(this.mMilli);
    }

    public final void setMHour(int i) {
        this.mHour = i;
    }

    public final void setMMilli(int i) {
        this.mMilli = i;
    }

    public final void setMMinute(int i) {
        this.mMinute = i;
    }

    public final void setMSecond(int i) {
        this.mSecond = i;
    }

    @NotNull
    public String toString() {
        return "BleLocationUTC(mHour=" + this.mHour + ", mMinute=" + this.mMinute + ", mSecond=" + this.mSecond + ", mMilli=" + this.mMilli + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleLocationUTC(int i, int i2, int i3, int i4) {
        this.mHour = i;
        this.mMinute = i2;
        this.mSecond = i3;
        this.mMilli = i4;
    }
}
