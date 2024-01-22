package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleWeatherRealtime extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 16;
    private int mTime;
    @Nullable
    private BleWeather mWeather;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ BleWeatherRealtime(int i, BleWeather bleWeather, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : bleWeather);
    }

    public static /* synthetic */ BleWeatherRealtime copy$default(BleWeatherRealtime bleWeatherRealtime, int i, BleWeather bleWeather, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleWeatherRealtime.mTime;
        }
        if ((i2 & 2) != 0) {
            bleWeather = bleWeatherRealtime.mWeather;
        }
        return bleWeatherRealtime.copy(i, bleWeather);
    }

    public final int component1() {
        return this.mTime;
    }

    @Nullable
    public final BleWeather component2() {
        return this.mWeather;
    }

    @NotNull
    public final BleWeatherRealtime copy(int i, @Nullable BleWeather bleWeather) {
        return new BleWeatherRealtime(i, bleWeather);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeObject(BleTime.Companion.ofLocal(this.mTime * 1000));
        writeObject(this.mWeather);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleWeatherRealtime) {
            BleWeatherRealtime bleWeatherRealtime = (BleWeatherRealtime) obj;
            return this.mTime == bleWeatherRealtime.mTime && Intrinsics.areEqual(this.mWeather, bleWeatherRealtime.mWeather);
        }
        return false;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 16;
    }

    public final int getMTime() {
        return this.mTime;
    }

    @Nullable
    public final BleWeather getMWeather() {
        return this.mWeather;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.mTime) * 31;
        BleWeather bleWeather = this.mWeather;
        return hashCode + (bleWeather == null ? 0 : bleWeather.hashCode());
    }

    public final void setMTime(int i) {
        this.mTime = i;
    }

    public final void setMWeather(@Nullable BleWeather bleWeather) {
        this.mWeather = bleWeather;
    }

    @NotNull
    public String toString() {
        return "BleWeatherRealtime(mTime=" + BleTime.Companion.ofLocal(this.mTime * 1000) + ", mWeather=" + this.mWeather + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleWeatherRealtime(int i, @Nullable BleWeather bleWeather) {
        this.mTime = i;
        this.mWeather = bleWeather;
    }
}
