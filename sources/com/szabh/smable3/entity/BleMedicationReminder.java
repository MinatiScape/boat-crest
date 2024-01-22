package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMedicationReminder extends BleIdObject {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 72;
    private static final int LABLE_LENGTH = 21;
    private static final int NAME_LENGTH = 24;
    public static final int TYPE_CAPSULE = 1;
    public static final int TYPE_CREAM = 3;
    public static final int TYPE_DROPS = 2;
    public static final int TYPE_INJECTABLE = 6;
    public static final int TYPE_SOLUTION = 5;
    public static final int TYPE_SPRAY = 4;
    public static final int TYPE_TABLET = 0;
    public static final int UNIT_GRAM = 2;
    public static final int UNIT_INTERNATIONAL = 6;
    public static final int UNIT_MICROGRAM = 1;
    public static final int UNIT_MILLICURIE = 7;
    public static final int UNIT_MILLIEQUIVALENT = 8;
    public static final int UNIT_MILLIGRAM = 0;
    public static final int UNIT_MILLILITER = 3;
    public static final int UNIT_PERCENTAGE = 4;
    public static final int UNIT_PIECE = 5;
    private int mDosage;
    private int mEndDay;
    private int mEndMonth;
    private int mEndYear;
    @NotNull
    private String mLabel;
    @NotNull
    private String mName;
    @NotNull
    private BleHmTime mRemindTime1;
    @NotNull
    private BleHmTime mRemindTime2;
    @NotNull
    private BleHmTime mRemindTime3;
    @NotNull
    private BleHmTime mRemindTime4;
    @NotNull
    private BleHmTime mRemindTime5;
    @NotNull
    private BleHmTime mRemindTime6;
    private int mRemindTimes;
    private int mRepeat;
    private int mStartDay;
    private int mStartMonth;
    private int mStartYear;
    private int mType;
    private int mUnit;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMedicationReminder() {
        this(0, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, null, null, 524287, null);
    }

    public /* synthetic */ BleMedicationReminder(int i, int i2, int i3, int i4, int i5, BleHmTime bleHmTime, BleHmTime bleHmTime2, BleHmTime bleHmTime3, BleHmTime bleHmTime4, BleHmTime bleHmTime5, BleHmTime bleHmTime6, int i6, int i7, int i8, int i9, int i10, int i11, String str, String str2, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this((i12 & 1) != 0 ? 0 : i, (i12 & 2) != 0 ? 0 : i2, (i12 & 4) != 0 ? 0 : i3, (i12 & 8) != 0 ? 0 : i4, (i12 & 16) != 0 ? 0 : i5, (i12 & 32) != 0 ? new BleHmTime(0, 0, 3, null) : bleHmTime, (i12 & 64) != 0 ? new BleHmTime(0, 0, 3, null) : bleHmTime2, (i12 & 128) != 0 ? new BleHmTime(0, 0, 3, null) : bleHmTime3, (i12 & 256) != 0 ? new BleHmTime(0, 0, 3, null) : bleHmTime4, (i12 & 512) != 0 ? new BleHmTime(0, 0, 3, null) : bleHmTime5, (i12 & 1024) != 0 ? new BleHmTime(0, 0, 3, null) : bleHmTime6, (i12 & 2048) != 0 ? 0 : i6, (i12 & 4096) != 0 ? 0 : i7, (i12 & 8192) != 0 ? 0 : i8, (i12 & 16384) != 0 ? 0 : i9, (i12 & 32768) != 0 ? 0 : i10, (i12 & 65536) != 0 ? 0 : i11, (i12 & 131072) != 0 ? "" : str, (i12 & 262144) == 0 ? str2 : "");
    }

    public final int component1() {
        return this.mType;
    }

    @NotNull
    public final BleHmTime component10() {
        return this.mRemindTime5;
    }

    @NotNull
    public final BleHmTime component11() {
        return this.mRemindTime6;
    }

    public final int component12() {
        return this.mStartYear;
    }

    public final int component13() {
        return this.mStartMonth;
    }

    public final int component14() {
        return this.mStartDay;
    }

    public final int component15() {
        return this.mEndYear;
    }

    public final int component16() {
        return this.mEndMonth;
    }

    public final int component17() {
        return this.mEndDay;
    }

    @NotNull
    public final String component18() {
        return this.mName;
    }

    @NotNull
    public final String component19() {
        return this.mLabel;
    }

    public final int component2() {
        return this.mUnit;
    }

    public final int component3() {
        return this.mDosage;
    }

    public final int component4() {
        return this.mRepeat;
    }

    public final int component5() {
        return this.mRemindTimes;
    }

    @NotNull
    public final BleHmTime component6() {
        return this.mRemindTime1;
    }

    @NotNull
    public final BleHmTime component7() {
        return this.mRemindTime2;
    }

    @NotNull
    public final BleHmTime component8() {
        return this.mRemindTime3;
    }

    @NotNull
    public final BleHmTime component9() {
        return this.mRemindTime4;
    }

    @NotNull
    public final BleMedicationReminder copy(int i, int i2, int i3, int i4, int i5, @NotNull BleHmTime mRemindTime1, @NotNull BleHmTime mRemindTime2, @NotNull BleHmTime mRemindTime3, @NotNull BleHmTime mRemindTime4, @NotNull BleHmTime mRemindTime5, @NotNull BleHmTime mRemindTime6, int i6, int i7, int i8, int i9, int i10, int i11, @NotNull String mName, @NotNull String mLabel) {
        Intrinsics.checkNotNullParameter(mRemindTime1, "mRemindTime1");
        Intrinsics.checkNotNullParameter(mRemindTime2, "mRemindTime2");
        Intrinsics.checkNotNullParameter(mRemindTime3, "mRemindTime3");
        Intrinsics.checkNotNullParameter(mRemindTime4, "mRemindTime4");
        Intrinsics.checkNotNullParameter(mRemindTime5, "mRemindTime5");
        Intrinsics.checkNotNullParameter(mRemindTime6, "mRemindTime6");
        Intrinsics.checkNotNullParameter(mName, "mName");
        Intrinsics.checkNotNullParameter(mLabel, "mLabel");
        return new BleMedicationReminder(i, i2, i3, i4, i5, mRemindTime1, mRemindTime2, mRemindTime3, mRemindTime4, mRemindTime5, mRemindTime6, i6, i7, i8, i9, i10, i11, mName, mLabel);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        setMId(readUInt8());
        this.mType = readUInt8();
        this.mUnit = readUInt8();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mDosage = (int) readUInt32(LITTLE_ENDIAN);
        this.mRepeat = readUInt8();
        this.mRemindTimes = readUInt8();
        BleReadable t = (BleReadable) BleHmTime.class.newInstance();
        t.setMBytes(readBytes(2));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        this.mRemindTime1 = (BleHmTime) t;
        BleReadable t2 = (BleReadable) BleHmTime.class.newInstance();
        t2.setMBytes(readBytes(2));
        t2.decode();
        Intrinsics.checkNotNullExpressionValue(t2, "t");
        this.mRemindTime2 = (BleHmTime) t2;
        BleReadable t3 = (BleReadable) BleHmTime.class.newInstance();
        t3.setMBytes(readBytes(2));
        t3.decode();
        Intrinsics.checkNotNullExpressionValue(t3, "t");
        this.mRemindTime3 = (BleHmTime) t3;
        BleReadable t4 = (BleReadable) BleHmTime.class.newInstance();
        t4.setMBytes(readBytes(2));
        t4.decode();
        Intrinsics.checkNotNullExpressionValue(t4, "t");
        this.mRemindTime4 = (BleHmTime) t4;
        BleReadable t5 = (BleReadable) BleHmTime.class.newInstance();
        t5.setMBytes(readBytes(2));
        t5.decode();
        Intrinsics.checkNotNullExpressionValue(t5, "t");
        this.mRemindTime5 = (BleHmTime) t5;
        BleReadable t6 = (BleReadable) BleHmTime.class.newInstance();
        t6.setMBytes(readBytes(2));
        t6.decode();
        Intrinsics.checkNotNullExpressionValue(t6, "t");
        this.mRemindTime6 = (BleHmTime) t6;
        this.mStartYear = readUInt8() + 2000;
        this.mStartMonth = readUInt8();
        this.mStartDay = readUInt8();
        this.mEndYear = readUInt8() + 2000;
        this.mEndMonth = readUInt8();
        this.mEndDay = readUInt8();
        this.mName = BleReadable.readString$default(this, 24, null, 2, null);
        this.mLabel = BleReadable.readString$default(this, 21, null, 2, null);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(getMId());
        writeInt8(this.mType);
        writeInt8(this.mUnit);
        int i = this.mDosage;
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt32(i, LITTLE_ENDIAN);
        writeInt8(this.mRepeat);
        writeInt8(this.mRemindTimes);
        writeObject(this.mRemindTime1);
        writeObject(this.mRemindTime2);
        writeObject(this.mRemindTime3);
        writeObject(this.mRemindTime4);
        writeObject(this.mRemindTime5);
        writeObject(this.mRemindTime6);
        writeInt8(this.mStartYear - 2000);
        writeInt8(this.mStartMonth);
        writeInt8(this.mStartDay);
        writeInt8(this.mEndYear - 2000);
        writeInt8(this.mEndMonth);
        writeInt8(this.mEndDay);
        BleWritable.writeStringWithFix$default(this, this.mName, 24, null, 4, null);
        BleWritable.writeStringWithFix$default(this, this.mLabel, 21, null, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMedicationReminder) {
            BleMedicationReminder bleMedicationReminder = (BleMedicationReminder) obj;
            return this.mType == bleMedicationReminder.mType && this.mUnit == bleMedicationReminder.mUnit && this.mDosage == bleMedicationReminder.mDosage && this.mRepeat == bleMedicationReminder.mRepeat && this.mRemindTimes == bleMedicationReminder.mRemindTimes && Intrinsics.areEqual(this.mRemindTime1, bleMedicationReminder.mRemindTime1) && Intrinsics.areEqual(this.mRemindTime2, bleMedicationReminder.mRemindTime2) && Intrinsics.areEqual(this.mRemindTime3, bleMedicationReminder.mRemindTime3) && Intrinsics.areEqual(this.mRemindTime4, bleMedicationReminder.mRemindTime4) && Intrinsics.areEqual(this.mRemindTime5, bleMedicationReminder.mRemindTime5) && Intrinsics.areEqual(this.mRemindTime6, bleMedicationReminder.mRemindTime6) && this.mStartYear == bleMedicationReminder.mStartYear && this.mStartMonth == bleMedicationReminder.mStartMonth && this.mStartDay == bleMedicationReminder.mStartDay && this.mEndYear == bleMedicationReminder.mEndYear && this.mEndMonth == bleMedicationReminder.mEndMonth && this.mEndDay == bleMedicationReminder.mEndDay && Intrinsics.areEqual(this.mName, bleMedicationReminder.mName) && Intrinsics.areEqual(this.mLabel, bleMedicationReminder.mLabel);
        }
        return false;
    }

    public final int getMDosage() {
        return this.mDosage;
    }

    public final int getMEndDay() {
        return this.mEndDay;
    }

    public final int getMEndMonth() {
        return this.mEndMonth;
    }

    public final int getMEndYear() {
        return this.mEndYear;
    }

    @NotNull
    public final String getMLabel() {
        return this.mLabel;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 72;
    }

    @NotNull
    public final String getMName() {
        return this.mName;
    }

    @NotNull
    public final BleHmTime getMRemindTime1() {
        return this.mRemindTime1;
    }

    @NotNull
    public final BleHmTime getMRemindTime2() {
        return this.mRemindTime2;
    }

    @NotNull
    public final BleHmTime getMRemindTime3() {
        return this.mRemindTime3;
    }

    @NotNull
    public final BleHmTime getMRemindTime4() {
        return this.mRemindTime4;
    }

    @NotNull
    public final BleHmTime getMRemindTime5() {
        return this.mRemindTime5;
    }

    @NotNull
    public final BleHmTime getMRemindTime6() {
        return this.mRemindTime6;
    }

    public final int getMRemindTimes() {
        return this.mRemindTimes;
    }

    public final int getMRepeat() {
        return this.mRepeat;
    }

    public final int getMStartDay() {
        return this.mStartDay;
    }

    public final int getMStartMonth() {
        return this.mStartMonth;
    }

    public final int getMStartYear() {
        return this.mStartYear;
    }

    public final int getMType() {
        return this.mType;
    }

    public final int getMUnit() {
        return this.mUnit;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((Integer.hashCode(this.mType) * 31) + Integer.hashCode(this.mUnit)) * 31) + Integer.hashCode(this.mDosage)) * 31) + Integer.hashCode(this.mRepeat)) * 31) + Integer.hashCode(this.mRemindTimes)) * 31) + this.mRemindTime1.hashCode()) * 31) + this.mRemindTime2.hashCode()) * 31) + this.mRemindTime3.hashCode()) * 31) + this.mRemindTime4.hashCode()) * 31) + this.mRemindTime5.hashCode()) * 31) + this.mRemindTime6.hashCode()) * 31) + Integer.hashCode(this.mStartYear)) * 31) + Integer.hashCode(this.mStartMonth)) * 31) + Integer.hashCode(this.mStartDay)) * 31) + Integer.hashCode(this.mEndYear)) * 31) + Integer.hashCode(this.mEndMonth)) * 31) + Integer.hashCode(this.mEndDay)) * 31) + this.mName.hashCode()) * 31) + this.mLabel.hashCode();
    }

    public final void setMDosage(int i) {
        this.mDosage = i;
    }

    public final void setMEndDay(int i) {
        this.mEndDay = i;
    }

    public final void setMEndMonth(int i) {
        this.mEndMonth = i;
    }

    public final void setMEndYear(int i) {
        this.mEndYear = i;
    }

    public final void setMLabel(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mLabel = str;
    }

    public final void setMName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mName = str;
    }

    public final void setMRemindTime1(@NotNull BleHmTime bleHmTime) {
        Intrinsics.checkNotNullParameter(bleHmTime, "<set-?>");
        this.mRemindTime1 = bleHmTime;
    }

    public final void setMRemindTime2(@NotNull BleHmTime bleHmTime) {
        Intrinsics.checkNotNullParameter(bleHmTime, "<set-?>");
        this.mRemindTime2 = bleHmTime;
    }

    public final void setMRemindTime3(@NotNull BleHmTime bleHmTime) {
        Intrinsics.checkNotNullParameter(bleHmTime, "<set-?>");
        this.mRemindTime3 = bleHmTime;
    }

    public final void setMRemindTime4(@NotNull BleHmTime bleHmTime) {
        Intrinsics.checkNotNullParameter(bleHmTime, "<set-?>");
        this.mRemindTime4 = bleHmTime;
    }

    public final void setMRemindTime5(@NotNull BleHmTime bleHmTime) {
        Intrinsics.checkNotNullParameter(bleHmTime, "<set-?>");
        this.mRemindTime5 = bleHmTime;
    }

    public final void setMRemindTime6(@NotNull BleHmTime bleHmTime) {
        Intrinsics.checkNotNullParameter(bleHmTime, "<set-?>");
        this.mRemindTime6 = bleHmTime;
    }

    public final void setMRemindTimes(int i) {
        this.mRemindTimes = i;
    }

    public final void setMRepeat(int i) {
        this.mRepeat = i;
    }

    public final void setMStartDay(int i) {
        this.mStartDay = i;
    }

    public final void setMStartMonth(int i) {
        this.mStartMonth = i;
    }

    public final void setMStartYear(int i) {
        this.mStartYear = i;
    }

    public final void setMType(int i) {
        this.mType = i;
    }

    public final void setMUnit(int i) {
        this.mUnit = i;
    }

    @NotNull
    public String toString() {
        return "BleMedicationReminder(mId=" + getMId() + ", mType=" + this.mType + ", mUnit=" + this.mUnit + ", mDosage=" + this.mDosage + ", mRepeat=" + this.mRepeat + ", mRemindTimes=" + this.mRemindTimes + ", mRemindTime1=" + this.mRemindTime1 + ", mRemindTime2=" + this.mRemindTime2 + ", mRemindTime3=" + this.mRemindTime3 + ", mRemindTime4=" + this.mRemindTime4 + ", mRemindTime5=" + this.mRemindTime5 + ", mRemindTime6=" + this.mRemindTime6 + ", mStartYear=" + this.mStartYear + ", mStartMonth=" + this.mStartMonth + ", mStartDay=" + this.mStartDay + ", mEndYear=" + this.mEndYear + ", mEndMonth=" + this.mEndMonth + ", mEndDay=" + this.mEndDay + ", mName='" + this.mName + "', mLabel='" + this.mLabel + "')";
    }

    public BleMedicationReminder(int i, int i2, int i3, int i4, int i5, @NotNull BleHmTime mRemindTime1, @NotNull BleHmTime mRemindTime2, @NotNull BleHmTime mRemindTime3, @NotNull BleHmTime mRemindTime4, @NotNull BleHmTime mRemindTime5, @NotNull BleHmTime mRemindTime6, int i6, int i7, int i8, int i9, int i10, int i11, @NotNull String mName, @NotNull String mLabel) {
        Intrinsics.checkNotNullParameter(mRemindTime1, "mRemindTime1");
        Intrinsics.checkNotNullParameter(mRemindTime2, "mRemindTime2");
        Intrinsics.checkNotNullParameter(mRemindTime3, "mRemindTime3");
        Intrinsics.checkNotNullParameter(mRemindTime4, "mRemindTime4");
        Intrinsics.checkNotNullParameter(mRemindTime5, "mRemindTime5");
        Intrinsics.checkNotNullParameter(mRemindTime6, "mRemindTime6");
        Intrinsics.checkNotNullParameter(mName, "mName");
        Intrinsics.checkNotNullParameter(mLabel, "mLabel");
        this.mType = i;
        this.mUnit = i2;
        this.mDosage = i3;
        this.mRepeat = i4;
        this.mRemindTimes = i5;
        this.mRemindTime1 = mRemindTime1;
        this.mRemindTime2 = mRemindTime2;
        this.mRemindTime3 = mRemindTime3;
        this.mRemindTime4 = mRemindTime4;
        this.mRemindTime5 = mRemindTime5;
        this.mRemindTime6 = mRemindTime6;
        this.mStartYear = i6;
        this.mStartMonth = i7;
        this.mStartDay = i8;
        this.mEndYear = i9;
        this.mEndMonth = i10;
        this.mEndDay = i11;
        this.mName = mName;
        this.mLabel = mLabel;
    }
}
