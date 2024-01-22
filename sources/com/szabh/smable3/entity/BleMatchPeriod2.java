package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchPeriod2 extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 14;
    private int mAvgBpm;
    private int mCalorie;
    private int mDistance;
    private int mDuration;
    private int mMaxBpm;
    private int mMaxSpeed;
    private int mSpeed;
    private int mStep;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchPeriod2() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 255, null);
    }

    public /* synthetic */ BleMatchPeriod2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this((i9 & 1) != 0 ? 0 : i, (i9 & 2) != 0 ? 0 : i2, (i9 & 4) != 0 ? 0 : i3, (i9 & 8) != 0 ? 0 : i4, (i9 & 16) != 0 ? 0 : i5, (i9 & 32) != 0 ? 0 : i6, (i9 & 64) != 0 ? 0 : i7, (i9 & 128) == 0 ? i8 : 0);
    }

    public final int component1() {
        return this.mDuration;
    }

    public final int component2() {
        return this.mDistance;
    }

    public final int component3() {
        return this.mStep;
    }

    public final int component4() {
        return this.mCalorie;
    }

    public final int component5() {
        return this.mSpeed;
    }

    public final int component6() {
        return this.mAvgBpm;
    }

    public final int component7() {
        return this.mMaxBpm;
    }

    public final int component8() {
        return this.mMaxSpeed;
    }

    @NotNull
    public final BleMatchPeriod2 copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new BleMatchPeriod2(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mDuration = readUInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mDistance = readUInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mStep = readUInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mCalorie = readUInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mSpeed = readUInt16(LITTLE_ENDIAN);
        this.mAvgBpm = readUInt8();
        this.mMaxBpm = readUInt8();
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mMaxSpeed = readUInt16(LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchPeriod2) {
            BleMatchPeriod2 bleMatchPeriod2 = (BleMatchPeriod2) obj;
            return this.mDuration == bleMatchPeriod2.mDuration && this.mDistance == bleMatchPeriod2.mDistance && this.mStep == bleMatchPeriod2.mStep && this.mCalorie == bleMatchPeriod2.mCalorie && this.mSpeed == bleMatchPeriod2.mSpeed && this.mAvgBpm == bleMatchPeriod2.mAvgBpm && this.mMaxBpm == bleMatchPeriod2.mMaxBpm && this.mMaxSpeed == bleMatchPeriod2.mMaxSpeed;
        }
        return false;
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

    public final int getMMaxBpm() {
        return this.mMaxBpm;
    }

    public final int getMMaxSpeed() {
        return this.mMaxSpeed;
    }

    public final int getMSpeed() {
        return this.mSpeed;
    }

    public final int getMStep() {
        return this.mStep;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.mDuration) * 31) + Integer.hashCode(this.mDistance)) * 31) + Integer.hashCode(this.mStep)) * 31) + Integer.hashCode(this.mCalorie)) * 31) + Integer.hashCode(this.mSpeed)) * 31) + Integer.hashCode(this.mAvgBpm)) * 31) + Integer.hashCode(this.mMaxBpm)) * 31) + Integer.hashCode(this.mMaxSpeed);
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

    public final void setMMaxBpm(int i) {
        this.mMaxBpm = i;
    }

    public final void setMMaxSpeed(int i) {
        this.mMaxSpeed = i;
    }

    public final void setMSpeed(int i) {
        this.mSpeed = i;
    }

    public final void setMStep(int i) {
        this.mStep = i;
    }

    @NotNull
    public String toString() {
        return "BleMatchPeriod2(mDuration=" + this.mDuration + ", mDistance=" + this.mDistance + ", mStep=" + this.mStep + ", mCalorie=" + this.mCalorie + ", mSpeed=" + this.mSpeed + ", mAvgBpm=" + this.mAvgBpm + ", mMaxBpm=" + this.mMaxBpm + ", mMaxSpeed=" + this.mMaxSpeed + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchPeriod2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mDuration = i;
        this.mDistance = i2;
        this.mStep = i3;
        this.mCalorie = i4;
        this.mSpeed = i5;
        this.mAvgBpm = i6;
        this.mMaxBpm = i7;
        this.mMaxSpeed = i8;
    }
}
