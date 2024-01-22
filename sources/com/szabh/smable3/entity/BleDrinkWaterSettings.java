package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleDrinkWaterSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mEnabled;
    private int mEndHour;
    private int mEndMinute;
    private int mInterval;
    private int mRepeat;
    private int mStartHour;
    private int mStartMinute;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleDrinkWaterSettings() {
        this(0, 0, 0, 0, 0, 0, 0, 127, null);
    }

    public /* synthetic */ BleDrinkWaterSettings(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? 0 : i, (i8 & 2) != 0 ? 0 : i2, (i8 & 4) != 0 ? 0 : i3, (i8 & 8) != 0 ? 0 : i4, (i8 & 16) != 0 ? 0 : i5, (i8 & 32) != 0 ? 0 : i6, (i8 & 64) != 0 ? 0 : i7);
    }

    public static /* synthetic */ BleDrinkWaterSettings copy$default(BleDrinkWaterSettings bleDrinkWaterSettings, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i = bleDrinkWaterSettings.mEnabled;
        }
        if ((i8 & 2) != 0) {
            i2 = bleDrinkWaterSettings.mRepeat;
        }
        int i9 = i2;
        if ((i8 & 4) != 0) {
            i3 = bleDrinkWaterSettings.mStartHour;
        }
        int i10 = i3;
        if ((i8 & 8) != 0) {
            i4 = bleDrinkWaterSettings.mStartMinute;
        }
        int i11 = i4;
        if ((i8 & 16) != 0) {
            i5 = bleDrinkWaterSettings.mEndHour;
        }
        int i12 = i5;
        if ((i8 & 32) != 0) {
            i6 = bleDrinkWaterSettings.mEndMinute;
        }
        int i13 = i6;
        if ((i8 & 64) != 0) {
            i7 = bleDrinkWaterSettings.mInterval;
        }
        return bleDrinkWaterSettings.copy(i, i9, i10, i11, i12, i13, i7);
    }

    public final int component1() {
        return this.mEnabled;
    }

    public final int component2() {
        return this.mRepeat;
    }

    public final int component3() {
        return this.mStartHour;
    }

    public final int component4() {
        return this.mStartMinute;
    }

    public final int component5() {
        return this.mEndHour;
    }

    public final int component6() {
        return this.mEndMinute;
    }

    public final int component7() {
        return this.mInterval;
    }

    @NotNull
    public final BleDrinkWaterSettings copy(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return new BleDrinkWaterSettings(i, i2, i3, i4, i5, i6, i7);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mEnabled = readIntN(1);
        this.mRepeat = readIntN(7);
        this.mStartHour = readUInt8();
        this.mStartMinute = readUInt8();
        this.mEndHour = readUInt8();
        this.mEndMinute = readUInt8();
        this.mInterval = readUInt8();
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeIntN(this.mEnabled, 1);
        writeIntN(this.mRepeat, 7);
        writeInt8(this.mStartHour);
        writeInt8(this.mStartMinute);
        writeInt8(this.mEndHour);
        writeInt8(this.mEndMinute);
        writeInt8(this.mInterval);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleDrinkWaterSettings) {
            BleDrinkWaterSettings bleDrinkWaterSettings = (BleDrinkWaterSettings) obj;
            return this.mEnabled == bleDrinkWaterSettings.mEnabled && this.mRepeat == bleDrinkWaterSettings.mRepeat && this.mStartHour == bleDrinkWaterSettings.mStartHour && this.mStartMinute == bleDrinkWaterSettings.mStartMinute && this.mEndHour == bleDrinkWaterSettings.mEndHour && this.mEndMinute == bleDrinkWaterSettings.mEndMinute && this.mInterval == bleDrinkWaterSettings.mInterval;
        }
        return false;
    }

    public final int getMEnabled() {
        return this.mEnabled;
    }

    public final int getMEndHour() {
        return this.mEndHour;
    }

    public final int getMEndMinute() {
        return this.mEndMinute;
    }

    public final int getMInterval() {
        return this.mInterval;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 6;
    }

    public final int getMRepeat() {
        return this.mRepeat;
    }

    public final int getMStartHour() {
        return this.mStartHour;
    }

    public final int getMStartMinute() {
        return this.mStartMinute;
    }

    public int hashCode() {
        return (((((((((((Integer.hashCode(this.mEnabled) * 31) + Integer.hashCode(this.mRepeat)) * 31) + Integer.hashCode(this.mStartHour)) * 31) + Integer.hashCode(this.mStartMinute)) * 31) + Integer.hashCode(this.mEndHour)) * 31) + Integer.hashCode(this.mEndMinute)) * 31) + Integer.hashCode(this.mInterval);
    }

    public final void setMEnabled(int i) {
        this.mEnabled = i;
    }

    public final void setMEndHour(int i) {
        this.mEndHour = i;
    }

    public final void setMEndMinute(int i) {
        this.mEndMinute = i;
    }

    public final void setMInterval(int i) {
        this.mInterval = i;
    }

    public final void setMRepeat(int i) {
        this.mRepeat = i;
    }

    public final void setMStartHour(int i) {
        this.mStartHour = i;
    }

    public final void setMStartMinute(int i) {
        this.mStartMinute = i;
    }

    @NotNull
    public String toString() {
        return "BleDrinkWaterSettings(mEnabled=" + this.mEnabled + ", mRepeat=" + this.mRepeat + ", mStartHour=" + this.mStartHour + ", mStartMinute=" + this.mStartMinute + ", mEndHour=" + this.mEndHour + ", mEndMinute=" + this.mEndMinute + ", mInterval=" + this.mInterval + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleDrinkWaterSettings(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mEnabled = i;
        this.mRepeat = i2;
        this.mStartHour = i3;
        this.mStartMinute = i4;
        this.mEndHour = i5;
        this.mEndMinute = i6;
        this.mInterval = i7;
    }
}
