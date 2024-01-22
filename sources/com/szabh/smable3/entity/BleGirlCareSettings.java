package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleGirlCareSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 10;
    private int mEnabled;
    private int mLatestDay;
    private int mLatestMonth;
    private int mLatestYear;
    private int mMenstruationDuration;
    private int mMenstruationPeriod;
    private int mMenstruationReminderAdvance;
    private int mOvulationReminderAdvance;
    private int mReminderHour;
    private int mReminderMinute;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleGirlCareSettings() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1023, null);
    }

    public /* synthetic */ BleGirlCareSettings(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this((i11 & 1) != 0 ? 0 : i, (i11 & 2) != 0 ? 0 : i2, (i11 & 4) != 0 ? 0 : i3, (i11 & 8) != 0 ? 0 : i4, (i11 & 16) != 0 ? 0 : i5, (i11 & 32) != 0 ? 0 : i6, (i11 & 64) != 0 ? 0 : i7, (i11 & 128) != 0 ? 0 : i8, (i11 & 256) != 0 ? 0 : i9, (i11 & 512) == 0 ? i10 : 0);
    }

    public final int component1() {
        return this.mEnabled;
    }

    public final int component10() {
        return this.mMenstruationPeriod;
    }

    public final int component2() {
        return this.mReminderHour;
    }

    public final int component3() {
        return this.mReminderMinute;
    }

    public final int component4() {
        return this.mMenstruationReminderAdvance;
    }

    public final int component5() {
        return this.mOvulationReminderAdvance;
    }

    public final int component6() {
        return this.mLatestYear;
    }

    public final int component7() {
        return this.mLatestMonth;
    }

    public final int component8() {
        return this.mLatestDay;
    }

    public final int component9() {
        return this.mMenstruationDuration;
    }

    @NotNull
    public final BleGirlCareSettings copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        return new BleGirlCareSettings(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mEnabled);
        writeInt8(this.mReminderHour);
        writeInt8(this.mReminderMinute);
        writeInt8(this.mMenstruationReminderAdvance);
        writeInt8(this.mOvulationReminderAdvance);
        writeInt8(this.mLatestYear - 2000);
        writeInt8(this.mLatestMonth);
        writeInt8(this.mLatestDay);
        writeInt8(this.mMenstruationDuration);
        writeInt8(this.mMenstruationPeriod);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleGirlCareSettings) {
            BleGirlCareSettings bleGirlCareSettings = (BleGirlCareSettings) obj;
            return this.mEnabled == bleGirlCareSettings.mEnabled && this.mReminderHour == bleGirlCareSettings.mReminderHour && this.mReminderMinute == bleGirlCareSettings.mReminderMinute && this.mMenstruationReminderAdvance == bleGirlCareSettings.mMenstruationReminderAdvance && this.mOvulationReminderAdvance == bleGirlCareSettings.mOvulationReminderAdvance && this.mLatestYear == bleGirlCareSettings.mLatestYear && this.mLatestMonth == bleGirlCareSettings.mLatestMonth && this.mLatestDay == bleGirlCareSettings.mLatestDay && this.mMenstruationDuration == bleGirlCareSettings.mMenstruationDuration && this.mMenstruationPeriod == bleGirlCareSettings.mMenstruationPeriod;
        }
        return false;
    }

    public final int getMEnabled() {
        return this.mEnabled;
    }

    public final int getMLatestDay() {
        return this.mLatestDay;
    }

    public final int getMLatestMonth() {
        return this.mLatestMonth;
    }

    public final int getMLatestYear() {
        return this.mLatestYear;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 10;
    }

    public final int getMMenstruationDuration() {
        return this.mMenstruationDuration;
    }

    public final int getMMenstruationPeriod() {
        return this.mMenstruationPeriod;
    }

    public final int getMMenstruationReminderAdvance() {
        return this.mMenstruationReminderAdvance;
    }

    public final int getMOvulationReminderAdvance() {
        return this.mOvulationReminderAdvance;
    }

    public final int getMReminderHour() {
        return this.mReminderHour;
    }

    public final int getMReminderMinute() {
        return this.mReminderMinute;
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.mEnabled) * 31) + Integer.hashCode(this.mReminderHour)) * 31) + Integer.hashCode(this.mReminderMinute)) * 31) + Integer.hashCode(this.mMenstruationReminderAdvance)) * 31) + Integer.hashCode(this.mOvulationReminderAdvance)) * 31) + Integer.hashCode(this.mLatestYear)) * 31) + Integer.hashCode(this.mLatestMonth)) * 31) + Integer.hashCode(this.mLatestDay)) * 31) + Integer.hashCode(this.mMenstruationDuration)) * 31) + Integer.hashCode(this.mMenstruationPeriod);
    }

    public final void setMEnabled(int i) {
        this.mEnabled = i;
    }

    public final void setMLatestDay(int i) {
        this.mLatestDay = i;
    }

    public final void setMLatestMonth(int i) {
        this.mLatestMonth = i;
    }

    public final void setMLatestYear(int i) {
        this.mLatestYear = i;
    }

    public final void setMMenstruationDuration(int i) {
        this.mMenstruationDuration = i;
    }

    public final void setMMenstruationPeriod(int i) {
        this.mMenstruationPeriod = i;
    }

    public final void setMMenstruationReminderAdvance(int i) {
        this.mMenstruationReminderAdvance = i;
    }

    public final void setMOvulationReminderAdvance(int i) {
        this.mOvulationReminderAdvance = i;
    }

    public final void setMReminderHour(int i) {
        this.mReminderHour = i;
    }

    public final void setMReminderMinute(int i) {
        this.mReminderMinute = i;
    }

    @NotNull
    public String toString() {
        return "BleGirlCareSettings(mEnabled=" + this.mEnabled + ", mReminderHour=" + this.mReminderHour + ", mReminderMinute=" + this.mReminderMinute + ", mMenstruationReminderAdvance=" + this.mMenstruationReminderAdvance + ", mOvulationReminderAdvance=" + this.mOvulationReminderAdvance + ", mLatestYear=" + this.mLatestYear + ", mLatestMonth=" + this.mLatestMonth + ", mLatestDay=" + this.mLatestDay + ", mMenstruationDuration=" + this.mMenstruationDuration + ", mMenstruationPeriod=" + this.mMenstruationPeriod + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleGirlCareSettings(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.mEnabled = i;
        this.mReminderHour = i2;
        this.mReminderMinute = i3;
        this.mMenstruationReminderAdvance = i4;
        this.mOvulationReminderAdvance = i5;
        this.mLatestYear = i6;
        this.mLatestMonth = i7;
        this.mLatestDay = i8;
        this.mMenstruationDuration = i9;
        this.mMenstruationPeriod = i10;
    }
}
