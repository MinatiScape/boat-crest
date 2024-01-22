package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleWorkout2 extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 128;
    private int mAirPressure;
    private int mAltitude;
    private int mAvgBpm;
    private int mCalorie;
    private int mDistance;
    private int mDuration;
    private int mEnd;
    private int mMaxAltitude;
    private int mMaxBpm;
    private int mMaxPace;
    private int mMaxSpm;
    private int mMinAltitude;
    private int mMinBpm;
    private int mMinPace;
    private int mMinSpm;
    private int mMode;
    private int mPace;
    private int mSpeed;
    private int mSpm;
    private int mStart;
    private int mStep;
    private int mUndefined;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleWorkout2() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4194303, null);
    }

    public /* synthetic */ BleWorkout2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, DefaultConstructorMarker defaultConstructorMarker) {
        this((i23 & 1) != 0 ? 0 : i, (i23 & 2) != 0 ? 0 : i2, (i23 & 4) != 0 ? 0 : i3, (i23 & 8) != 0 ? 0 : i4, (i23 & 16) != 0 ? 0 : i5, (i23 & 32) != 0 ? 0 : i6, (i23 & 64) != 0 ? 0 : i7, (i23 & 128) != 0 ? 0 : i8, (i23 & 256) != 0 ? 0 : i9, (i23 & 512) != 0 ? 0 : i10, (i23 & 1024) != 0 ? 0 : i11, (i23 & 2048) != 0 ? 0 : i12, (i23 & 4096) != 0 ? 0 : i13, (i23 & 8192) != 0 ? 0 : i14, (i23 & 16384) != 0 ? 0 : i15, (i23 & 32768) != 0 ? 0 : i16, (i23 & 65536) != 0 ? 0 : i17, (i23 & 131072) != 0 ? 0 : i18, (i23 & 262144) != 0 ? 0 : i19, (i23 & 524288) != 0 ? 0 : i20, (i23 & 1048576) != 0 ? 0 : i21, (i23 & 2097152) != 0 ? 0 : i22);
    }

    public final int component1() {
        return this.mStart;
    }

    public final int component10() {
        return this.mCalorie;
    }

    public final int component11() {
        return this.mSpeed;
    }

    public final int component12() {
        return this.mPace;
    }

    public final int component13() {
        return this.mAvgBpm;
    }

    public final int component14() {
        return this.mMaxBpm;
    }

    public final int component15() {
        return this.mMinBpm;
    }

    public final int component16() {
        return this.mUndefined;
    }

    public final int component17() {
        return this.mMaxSpm;
    }

    public final int component18() {
        return this.mMinSpm;
    }

    public final int component19() {
        return this.mMaxPace;
    }

    public final int component2() {
        return this.mEnd;
    }

    public final int component20() {
        return this.mMinPace;
    }

    public final int component21() {
        return this.mMaxAltitude;
    }

    public final int component22() {
        return this.mMinAltitude;
    }

    public final int component3() {
        return this.mDuration;
    }

    public final int component4() {
        return this.mAltitude;
    }

    public final int component5() {
        return this.mAirPressure;
    }

    public final int component6() {
        return this.mSpm;
    }

    public final int component7() {
        return this.mMode;
    }

    public final int component8() {
        return this.mStep;
    }

    public final int component9() {
        return this.mDistance;
    }

    @NotNull
    public final BleWorkout2 copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22) {
        return new BleWorkout2(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mStart = BleReadable.readInt32$default(this, null, 1, null);
        this.mEnd = BleReadable.readInt32$default(this, null, 1, null);
        this.mDuration = BleReadable.readUInt16$default(this, null, 1, null);
        this.mAltitude = BleReadable.readInt16$default(this, null, 1, null);
        this.mAirPressure = BleReadable.readUInt16$default(this, null, 1, null);
        this.mSpm = readUInt8();
        this.mMode = readUInt8();
        this.mStep = BleReadable.readInt32$default(this, null, 1, null);
        this.mDistance = BleReadable.readInt32$default(this, null, 1, null);
        this.mCalorie = BleReadable.readInt32$default(this, null, 1, null);
        this.mSpeed = BleReadable.readInt32$default(this, null, 1, null);
        this.mPace = BleReadable.readInt32$default(this, null, 1, null);
        this.mAvgBpm = readUInt8();
        this.mMaxBpm = readUInt8();
        this.mMinBpm = readInt8();
        this.mUndefined = readInt8();
        this.mMaxSpm = BleReadable.readInt16$default(this, null, 1, null);
        this.mMinSpm = BleReadable.readInt16$default(this, null, 1, null);
        this.mMaxPace = BleReadable.readInt32$default(this, null, 1, null);
        this.mMinPace = BleReadable.readInt32$default(this, null, 1, null);
        this.mMaxAltitude = BleReadable.readInt16$default(this, null, 1, null);
        this.mMinAltitude = BleReadable.readInt16$default(this, null, 1, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleWorkout2) {
            BleWorkout2 bleWorkout2 = (BleWorkout2) obj;
            return this.mStart == bleWorkout2.mStart && this.mEnd == bleWorkout2.mEnd && this.mDuration == bleWorkout2.mDuration && this.mAltitude == bleWorkout2.mAltitude && this.mAirPressure == bleWorkout2.mAirPressure && this.mSpm == bleWorkout2.mSpm && this.mMode == bleWorkout2.mMode && this.mStep == bleWorkout2.mStep && this.mDistance == bleWorkout2.mDistance && this.mCalorie == bleWorkout2.mCalorie && this.mSpeed == bleWorkout2.mSpeed && this.mPace == bleWorkout2.mPace && this.mAvgBpm == bleWorkout2.mAvgBpm && this.mMaxBpm == bleWorkout2.mMaxBpm && this.mMinBpm == bleWorkout2.mMinBpm && this.mUndefined == bleWorkout2.mUndefined && this.mMaxSpm == bleWorkout2.mMaxSpm && this.mMinSpm == bleWorkout2.mMinSpm && this.mMaxPace == bleWorkout2.mMaxPace && this.mMinPace == bleWorkout2.mMinPace && this.mMaxAltitude == bleWorkout2.mMaxAltitude && this.mMinAltitude == bleWorkout2.mMinAltitude;
        }
        return false;
    }

    public final int getMAirPressure() {
        return this.mAirPressure;
    }

    public final int getMAltitude() {
        return this.mAltitude;
    }

    public final int getMAvgBpm() {
        return this.mAvgBpm;
    }

    public final int getMCalorie() {
        return this.mCalorie;
    }

    public final int getMDistance() {
        return this.mDistance;
    }

    public final int getMDuration() {
        return this.mDuration;
    }

    public final int getMEnd() {
        return this.mEnd;
    }

    public final int getMMaxAltitude() {
        return this.mMaxAltitude;
    }

    public final int getMMaxBpm() {
        return this.mMaxBpm;
    }

    public final int getMMaxPace() {
        return this.mMaxPace;
    }

    public final int getMMaxSpm() {
        return this.mMaxSpm;
    }

    public final int getMMinAltitude() {
        return this.mMinAltitude;
    }

    public final int getMMinBpm() {
        return this.mMinBpm;
    }

    public final int getMMinPace() {
        return this.mMinPace;
    }

    public final int getMMinSpm() {
        return this.mMinSpm;
    }

    public final int getMMode() {
        return this.mMode;
    }

    public final int getMPace() {
        return this.mPace;
    }

    public final int getMSpeed() {
        return this.mSpeed;
    }

    public final int getMSpm() {
        return this.mSpm;
    }

    public final int getMStart() {
        return this.mStart;
    }

    public final int getMStep() {
        return this.mStep;
    }

    public final int getMUndefined() {
        return this.mUndefined;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((Integer.hashCode(this.mStart) * 31) + Integer.hashCode(this.mEnd)) * 31) + Integer.hashCode(this.mDuration)) * 31) + Integer.hashCode(this.mAltitude)) * 31) + Integer.hashCode(this.mAirPressure)) * 31) + Integer.hashCode(this.mSpm)) * 31) + Integer.hashCode(this.mMode)) * 31) + Integer.hashCode(this.mStep)) * 31) + Integer.hashCode(this.mDistance)) * 31) + Integer.hashCode(this.mCalorie)) * 31) + Integer.hashCode(this.mSpeed)) * 31) + Integer.hashCode(this.mPace)) * 31) + Integer.hashCode(this.mAvgBpm)) * 31) + Integer.hashCode(this.mMaxBpm)) * 31) + Integer.hashCode(this.mMinBpm)) * 31) + Integer.hashCode(this.mUndefined)) * 31) + Integer.hashCode(this.mMaxSpm)) * 31) + Integer.hashCode(this.mMinSpm)) * 31) + Integer.hashCode(this.mMaxPace)) * 31) + Integer.hashCode(this.mMinPace)) * 31) + Integer.hashCode(this.mMaxAltitude)) * 31) + Integer.hashCode(this.mMinAltitude);
    }

    public final void setMAirPressure(int i) {
        this.mAirPressure = i;
    }

    public final void setMAltitude(int i) {
        this.mAltitude = i;
    }

    public final void setMAvgBpm(int i) {
        this.mAvgBpm = i;
    }

    public final void setMCalorie(int i) {
        this.mCalorie = i;
    }

    public final void setMDistance(int i) {
        this.mDistance = i;
    }

    public final void setMDuration(int i) {
        this.mDuration = i;
    }

    public final void setMEnd(int i) {
        this.mEnd = i;
    }

    public final void setMMaxAltitude(int i) {
        this.mMaxAltitude = i;
    }

    public final void setMMaxBpm(int i) {
        this.mMaxBpm = i;
    }

    public final void setMMaxPace(int i) {
        this.mMaxPace = i;
    }

    public final void setMMaxSpm(int i) {
        this.mMaxSpm = i;
    }

    public final void setMMinAltitude(int i) {
        this.mMinAltitude = i;
    }

    public final void setMMinBpm(int i) {
        this.mMinBpm = i;
    }

    public final void setMMinPace(int i) {
        this.mMinPace = i;
    }

    public final void setMMinSpm(int i) {
        this.mMinSpm = i;
    }

    public final void setMMode(int i) {
        this.mMode = i;
    }

    public final void setMPace(int i) {
        this.mPace = i;
    }

    public final void setMSpeed(int i) {
        this.mSpeed = i;
    }

    public final void setMSpm(int i) {
        this.mSpm = i;
    }

    public final void setMStart(int i) {
        this.mStart = i;
    }

    public final void setMStep(int i) {
        this.mStep = i;
    }

    public final void setMUndefined(int i) {
        this.mUndefined = i;
    }

    @NotNull
    public String toString() {
        return "BleWorkout2(mStart=" + this.mStart + ", mEnd=" + this.mEnd + ", mDuration=" + this.mDuration + ", mAltitude=" + this.mAltitude + ", mAirPressure=" + this.mAirPressure + ", mSpm=" + this.mSpm + ", mMode=" + this.mMode + ", mStep=" + this.mStep + ", mDistance=" + this.mDistance + ", mCalorie=" + this.mCalorie + ", mSpeed=" + this.mSpeed + ", mPace=" + this.mPace + ", mAvgBpm=" + this.mAvgBpm + ", mMaxBpm=" + this.mMaxBpm + ", mMinBpm=" + this.mMinBpm + ", mUndefined=" + this.mUndefined + ", mMaxSpm=" + this.mMaxSpm + ", mMinSpm=" + this.mMinSpm + ", mMaxPace=" + this.mMaxPace + ", mMinPace=" + this.mMinPace + ", mMaxAltitude=" + this.mMaxAltitude + ", mMinAltitude=" + this.mMinAltitude + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleWorkout2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22) {
        this.mStart = i;
        this.mEnd = i2;
        this.mDuration = i3;
        this.mAltitude = i4;
        this.mAirPressure = i5;
        this.mSpm = i6;
        this.mMode = i7;
        this.mStep = i8;
        this.mDistance = i9;
        this.mCalorie = i10;
        this.mSpeed = i11;
        this.mPace = i12;
        this.mAvgBpm = i13;
        this.mMaxBpm = i14;
        this.mMinBpm = i15;
        this.mUndefined = i16;
        this.mMaxSpm = i17;
        this.mMinSpm = i18;
        this.mMaxPace = i19;
        this.mMinPace = i20;
        this.mMaxAltitude = i21;
        this.mMinAltitude = i22;
    }
}
