package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleBloodPressure extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mDiastolic;
    private int mSystolic;
    private int mTime;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleBloodPressure() {
        this(0, 0, 0, 7, null);
    }

    public /* synthetic */ BleBloodPressure(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public static /* synthetic */ BleBloodPressure copy$default(BleBloodPressure bleBloodPressure, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bleBloodPressure.mTime;
        }
        if ((i4 & 2) != 0) {
            i2 = bleBloodPressure.mSystolic;
        }
        if ((i4 & 4) != 0) {
            i3 = bleBloodPressure.mDiastolic;
        }
        return bleBloodPressure.copy(i, i2, i3);
    }

    public final int component1() {
        return this.mTime;
    }

    public final int component2() {
        return this.mSystolic;
    }

    public final int component3() {
        return this.mDiastolic;
    }

    @NotNull
    public final BleBloodPressure copy(int i, int i2, int i3) {
        return new BleBloodPressure(i, i2, i3);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mTime = BleReadable.readInt32$default(this, null, 1, null);
        this.mSystolic = readUInt8();
        this.mDiastolic = readUInt8();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleBloodPressure) {
            BleBloodPressure bleBloodPressure = (BleBloodPressure) obj;
            return this.mTime == bleBloodPressure.mTime && this.mSystolic == bleBloodPressure.mSystolic && this.mDiastolic == bleBloodPressure.mDiastolic;
        }
        return false;
    }

    public final int getMDiastolic() {
        return this.mDiastolic;
    }

    public final int getMSystolic() {
        return this.mSystolic;
    }

    public final int getMTime() {
        return this.mTime;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.mTime) * 31) + Integer.hashCode(this.mSystolic)) * 31) + Integer.hashCode(this.mDiastolic);
    }

    public final void setMDiastolic(int i) {
        this.mDiastolic = i;
    }

    public final void setMSystolic(int i) {
        this.mSystolic = i;
    }

    public final void setMTime(int i) {
        this.mTime = i;
    }

    @NotNull
    public String toString() {
        return "BleBloodPressure(mTime=" + this.mTime + ", mSystolic=" + this.mSystolic + ", mDiastolic=" + this.mDiastolic + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleBloodPressure(int i, int i2, int i3) {
        this.mTime = i;
        this.mSystolic = i2;
        this.mDiastolic = i3;
    }
}
