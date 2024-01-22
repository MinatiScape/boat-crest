package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleRecordPacket extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int TRANSFER_STATUS_END = 2;
    public static final int TRANSFER_STATUS_GO_ON = 1;
    public static final int TRANSFER_STATUS_START = 0;
    public static final int TRANSFER_STATUS_SUSPEND = 3;
    private int mDay;
    private int mHour;
    private int mLength;
    private int mMinute;
    private int mMonth;
    @NotNull
    private byte[] mPacket;
    private int mSecond;
    private int mStatus;
    private int mYear;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleRecordPacket() {
        this(0, 0, 0, 0, 0, 0, 0, 0, null, 511, null);
    }

    public /* synthetic */ BleRecordPacket(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, byte[] bArr, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this((i9 & 1) != 0 ? 0 : i, (i9 & 2) != 0 ? 0 : i2, (i9 & 4) != 0 ? 0 : i3, (i9 & 8) != 0 ? 0 : i4, (i9 & 16) != 0 ? 0 : i5, (i9 & 32) != 0 ? 0 : i6, (i9 & 64) != 0 ? 0 : i7, (i9 & 128) != 0 ? 0 : i8, (i9 & 256) != 0 ? new byte[0] : bArr);
    }

    public final int component1() {
        return this.mYear;
    }

    public final int component2() {
        return this.mMonth;
    }

    public final int component3() {
        return this.mDay;
    }

    public final int component4() {
        return this.mHour;
    }

    public final int component5() {
        return this.mMinute;
    }

    public final int component6() {
        return this.mSecond;
    }

    public final int component7() {
        return this.mLength;
    }

    public final int component8() {
        return this.mStatus;
    }

    @NotNull
    public final byte[] component9() {
        return this.mPacket;
    }

    @NotNull
    public final BleRecordPacket copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @NotNull byte[] mPacket) {
        Intrinsics.checkNotNullParameter(mPacket, "mPacket");
        return new BleRecordPacket(i, i2, i3, i4, i5, i6, i7, i8, mPacket);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mYear = readUInt8() + 2000;
        this.mMonth = readUInt8();
        this.mDay = readUInt8();
        this.mHour = readUInt8();
        this.mMinute = readUInt8();
        this.mSecond = readUInt8();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mLength = readUInt16(LITTLE_ENDIAN);
        this.mStatus = readUInt8();
        readBytes(23);
        this.mPacket = readBytes(this.mLength);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleRecordPacket) {
            BleRecordPacket bleRecordPacket = (BleRecordPacket) obj;
            return this.mYear == bleRecordPacket.mYear && this.mMonth == bleRecordPacket.mMonth && this.mDay == bleRecordPacket.mDay && this.mHour == bleRecordPacket.mHour && this.mMinute == bleRecordPacket.mMinute && this.mSecond == bleRecordPacket.mSecond && this.mLength == bleRecordPacket.mLength && this.mStatus == bleRecordPacket.mStatus && Intrinsics.areEqual(this.mPacket, bleRecordPacket.mPacket);
        }
        return false;
    }

    public final int getMDay() {
        return this.mDay;
    }

    public final int getMHour() {
        return this.mHour;
    }

    public final int getMLength() {
        return this.mLength;
    }

    public final int getMMinute() {
        return this.mMinute;
    }

    public final int getMMonth() {
        return this.mMonth;
    }

    @NotNull
    public final byte[] getMPacket() {
        return this.mPacket;
    }

    public final int getMSecond() {
        return this.mSecond;
    }

    public final int getMStatus() {
        return this.mStatus;
    }

    public final int getMYear() {
        return this.mYear;
    }

    public int hashCode() {
        return (((((((((((((((Integer.hashCode(this.mYear) * 31) + Integer.hashCode(this.mMonth)) * 31) + Integer.hashCode(this.mDay)) * 31) + Integer.hashCode(this.mHour)) * 31) + Integer.hashCode(this.mMinute)) * 31) + Integer.hashCode(this.mSecond)) * 31) + Integer.hashCode(this.mLength)) * 31) + Integer.hashCode(this.mStatus)) * 31) + Arrays.hashCode(this.mPacket);
    }

    public final void setMDay(int i) {
        this.mDay = i;
    }

    public final void setMHour(int i) {
        this.mHour = i;
    }

    public final void setMLength(int i) {
        this.mLength = i;
    }

    public final void setMMinute(int i) {
        this.mMinute = i;
    }

    public final void setMMonth(int i) {
        this.mMonth = i;
    }

    public final void setMPacket(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mPacket = bArr;
    }

    public final void setMSecond(int i) {
        this.mSecond = i;
    }

    public final void setMStatus(int i) {
        this.mStatus = i;
    }

    public final void setMYear(int i) {
        this.mYear = i;
    }

    @NotNull
    public String toString() {
        return "BleRecordPacket(mYear=" + this.mYear + ", mMonth=" + this.mMonth + ", mDay=" + this.mDay + ", mHour=" + this.mHour + ", mMinute=" + this.mMinute + ", mSecond=" + this.mSecond + ", mLength=" + this.mLength + ", mStatus=" + this.mStatus + ", mPacket=" + this.mPacket.length + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleRecordPacket(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @NotNull byte[] mPacket) {
        Intrinsics.checkNotNullParameter(mPacket, "mPacket");
        this.mYear = i;
        this.mMonth = i2;
        this.mDay = i3;
        this.mHour = i4;
        this.mMinute = i5;
        this.mSecond = i6;
        this.mLength = i7;
        this.mStatus = i8;
        this.mPacket = mPacket;
    }
}
