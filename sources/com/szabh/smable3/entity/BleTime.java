package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleTime extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private int mSecond;
    private int mYear;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TimeZone getUtcTimeZone() {
            TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
            Intrinsics.checkNotNullExpressionValue(timeZone, "getTimeZone(\"GMT+0\")");
            return timeZone;
        }

        @NotNull
        public final BleTime local() {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            return new BleTime(calendar, null);
        }

        @NotNull
        public final BleTime ofLocal(long j) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            return new BleTime(calendar, null);
        }

        @NotNull
        public final BleTime utc() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(BleTime.Companion.getUtcTimeZone());
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            return new BleTime(calendar, null);
        }
    }

    public BleTime() {
        this(0, 0, 0, 0, 0, 0, 63, null);
    }

    public /* synthetic */ BleTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? 0 : i2, (i7 & 4) != 0 ? 0 : i3, (i7 & 8) != 0 ? 0 : i4, (i7 & 16) != 0 ? 0 : i5, (i7 & 32) != 0 ? 0 : i6);
    }

    public /* synthetic */ BleTime(Calendar calendar, DefaultConstructorMarker defaultConstructorMarker) {
        this(calendar);
    }

    public static /* synthetic */ BleTime copy$default(BleTime bleTime, int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = bleTime.mYear;
        }
        if ((i7 & 2) != 0) {
            i2 = bleTime.mMonth;
        }
        int i8 = i2;
        if ((i7 & 4) != 0) {
            i3 = bleTime.mDay;
        }
        int i9 = i3;
        if ((i7 & 8) != 0) {
            i4 = bleTime.mHour;
        }
        int i10 = i4;
        if ((i7 & 16) != 0) {
            i5 = bleTime.mMinute;
        }
        int i11 = i5;
        if ((i7 & 32) != 0) {
            i6 = bleTime.mSecond;
        }
        return bleTime.copy(i, i8, i9, i10, i11, i6);
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

    @NotNull
    public final BleTime copy(int i, int i2, int i3, int i4, int i5, int i6) {
        return new BleTime(i, i2, i3, i4, i5, i6);
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
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mYear - 2000);
        writeInt8(this.mMonth);
        writeInt8(this.mDay);
        writeInt8(this.mHour);
        writeInt8(this.mMinute);
        writeInt8(this.mSecond);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleTime) {
            BleTime bleTime = (BleTime) obj;
            return this.mYear == bleTime.mYear && this.mMonth == bleTime.mMonth && this.mDay == bleTime.mDay && this.mHour == bleTime.mHour && this.mMinute == bleTime.mMinute && this.mSecond == bleTime.mSecond;
        }
        return false;
    }

    public final int getMDay() {
        return this.mDay;
    }

    public final int getMHour() {
        return this.mHour;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 6;
    }

    public final int getMMinute() {
        return this.mMinute;
    }

    public final int getMMonth() {
        return this.mMonth;
    }

    public final int getMSecond() {
        return this.mSecond;
    }

    public final int getMYear() {
        return this.mYear;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.mYear) * 31) + Integer.hashCode(this.mMonth)) * 31) + Integer.hashCode(this.mDay)) * 31) + Integer.hashCode(this.mHour)) * 31) + Integer.hashCode(this.mMinute)) * 31) + Integer.hashCode(this.mSecond);
    }

    public final void setMDay(int i) {
        this.mDay = i;
    }

    public final void setMHour(int i) {
        this.mHour = i;
    }

    public final void setMMinute(int i) {
        this.mMinute = i;
    }

    public final void setMMonth(int i) {
        this.mMonth = i;
    }

    public final void setMSecond(int i) {
        this.mSecond = i;
    }

    public final void setMYear(int i) {
        this.mYear = i;
    }

    @NotNull
    public String toString() {
        return "BleTime(mYear=" + this.mYear + ", mMonth=" + this.mMonth + ", mDay=" + this.mDay + ", mHour=" + this.mHour + ", mMinute=" + this.mMinute + ", mSecond=" + this.mSecond + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleTime(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mYear = i;
        this.mMonth = i2;
        this.mDay = i3;
        this.mHour = i4;
        this.mMinute = i5;
        this.mSecond = i6;
    }

    private BleTime(Calendar calendar) {
        this(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }
}
