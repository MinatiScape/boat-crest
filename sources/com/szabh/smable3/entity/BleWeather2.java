package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleWeather2 extends BleWritable {
    public static final int CLOUDY = 2;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DRIZZLE = 13;
    public static final int FOGGY = 9;
    public static final int HAZE = 11;
    public static final int HEAVY_RAIN = 14;
    public static final int HEAVY_SNOW = 17;
    public static final int HIGH_WINDY = 7;
    public static final int ITEM_LENGTH = 20;
    public static final int LIGHTNING = 15;
    public static final int LIGHT_SNOW = 16;
    public static final int OTHER = 0;
    public static final int OVERCAST = 3;
    public static final int RAINY = 4;
    public static final int SAND_STORM = 10;
    public static final int SLEET = 18;
    public static final int SNOWSTORM = 20;
    public static final int SNOWY = 8;
    public static final int SUNNY = 1;
    public static final int THUNDER = 5;
    public static final int THUNDERSHOWER = 6;
    public static final int TORNADO = 19;
    public static final int WIND = 12;
    private int mCurrentTemperature;
    private int mHumidity;
    private int mMaxTemperature;
    private int mMinTemperature;
    private int mPrecipitation;
    private int mSunrisMinute;
    private int mSunrisSecond;
    private int mSunriseHour;
    private int mSunsetHour;
    private int mSunsetMinute;
    private int mSunsetSecond;
    private int mUltraVioletIntensity;
    private int mVisibility;
    private int mWeatherCode;
    private int mWindSpeed;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleWeather2() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32767, null);
    }

    public /* synthetic */ BleWeather2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, DefaultConstructorMarker defaultConstructorMarker) {
        this((i16 & 1) != 0 ? 0 : i, (i16 & 2) != 0 ? 0 : i2, (i16 & 4) != 0 ? 0 : i3, (i16 & 8) != 0 ? 0 : i4, (i16 & 16) != 0 ? 0 : i5, (i16 & 32) != 0 ? 0 : i6, (i16 & 64) != 0 ? 0 : i7, (i16 & 128) != 0 ? 0 : i8, (i16 & 256) != 0 ? 0 : i9, (i16 & 512) != 0 ? 0 : i10, (i16 & 1024) != 0 ? 0 : i11, (i16 & 2048) != 0 ? 0 : i12, (i16 & 4096) != 0 ? 0 : i13, (i16 & 8192) != 0 ? 0 : i14, (i16 & 16384) == 0 ? i15 : 0);
    }

    public final int component1() {
        return this.mCurrentTemperature;
    }

    public final int component10() {
        return this.mSunriseHour;
    }

    public final int component11() {
        return this.mSunrisMinute;
    }

    public final int component12() {
        return this.mSunrisSecond;
    }

    public final int component13() {
        return this.mSunsetHour;
    }

    public final int component14() {
        return this.mSunsetMinute;
    }

    public final int component15() {
        return this.mSunsetSecond;
    }

    public final int component2() {
        return this.mMaxTemperature;
    }

    public final int component3() {
        return this.mMinTemperature;
    }

    public final int component4() {
        return this.mWeatherCode;
    }

    public final int component5() {
        return this.mWindSpeed;
    }

    public final int component6() {
        return this.mHumidity;
    }

    public final int component7() {
        return this.mVisibility;
    }

    public final int component8() {
        return this.mUltraVioletIntensity;
    }

    public final int component9() {
        return this.mPrecipitation;
    }

    @NotNull
    public final BleWeather2 copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        return new BleWeather2(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mCurrentTemperature);
        writeInt8(this.mMaxTemperature);
        writeInt8(this.mMinTemperature);
        int i = this.mWeatherCode;
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt16(i, LITTLE_ENDIAN);
        writeInt8(this.mWindSpeed);
        writeInt8(this.mHumidity);
        writeInt8(this.mVisibility);
        writeInt8(this.mUltraVioletIntensity);
        int i2 = this.mPrecipitation;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt16(i2, LITTLE_ENDIAN);
        writeInt8(this.mSunriseHour);
        writeInt8(this.mSunrisMinute);
        writeInt8(this.mSunrisSecond);
        writeInt8(this.mSunsetHour);
        writeInt8(this.mSunsetMinute);
        writeInt8(this.mSunsetSecond);
        BleWritable.writeInt24$default(this, 0, null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleWeather2) {
            BleWeather2 bleWeather2 = (BleWeather2) obj;
            return this.mCurrentTemperature == bleWeather2.mCurrentTemperature && this.mMaxTemperature == bleWeather2.mMaxTemperature && this.mMinTemperature == bleWeather2.mMinTemperature && this.mWeatherCode == bleWeather2.mWeatherCode && this.mWindSpeed == bleWeather2.mWindSpeed && this.mHumidity == bleWeather2.mHumidity && this.mVisibility == bleWeather2.mVisibility && this.mUltraVioletIntensity == bleWeather2.mUltraVioletIntensity && this.mPrecipitation == bleWeather2.mPrecipitation && this.mSunriseHour == bleWeather2.mSunriseHour && this.mSunrisMinute == bleWeather2.mSunrisMinute && this.mSunrisSecond == bleWeather2.mSunrisSecond && this.mSunsetHour == bleWeather2.mSunsetHour && this.mSunsetMinute == bleWeather2.mSunsetMinute && this.mSunsetSecond == bleWeather2.mSunsetSecond;
        }
        return false;
    }

    public final int getMCurrentTemperature() {
        return this.mCurrentTemperature;
    }

    public final int getMHumidity() {
        return this.mHumidity;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 20;
    }

    public final int getMMaxTemperature() {
        return this.mMaxTemperature;
    }

    public final int getMMinTemperature() {
        return this.mMinTemperature;
    }

    public final int getMPrecipitation() {
        return this.mPrecipitation;
    }

    public final int getMSunrisMinute() {
        return this.mSunrisMinute;
    }

    public final int getMSunrisSecond() {
        return this.mSunrisSecond;
    }

    public final int getMSunriseHour() {
        return this.mSunriseHour;
    }

    public final int getMSunsetHour() {
        return this.mSunsetHour;
    }

    public final int getMSunsetMinute() {
        return this.mSunsetMinute;
    }

    public final int getMSunsetSecond() {
        return this.mSunsetSecond;
    }

    public final int getMUltraVioletIntensity() {
        return this.mUltraVioletIntensity;
    }

    public final int getMVisibility() {
        return this.mVisibility;
    }

    public final int getMWeatherCode() {
        return this.mWeatherCode;
    }

    public final int getMWindSpeed() {
        return this.mWindSpeed;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((Integer.hashCode(this.mCurrentTemperature) * 31) + Integer.hashCode(this.mMaxTemperature)) * 31) + Integer.hashCode(this.mMinTemperature)) * 31) + Integer.hashCode(this.mWeatherCode)) * 31) + Integer.hashCode(this.mWindSpeed)) * 31) + Integer.hashCode(this.mHumidity)) * 31) + Integer.hashCode(this.mVisibility)) * 31) + Integer.hashCode(this.mUltraVioletIntensity)) * 31) + Integer.hashCode(this.mPrecipitation)) * 31) + Integer.hashCode(this.mSunriseHour)) * 31) + Integer.hashCode(this.mSunrisMinute)) * 31) + Integer.hashCode(this.mSunrisSecond)) * 31) + Integer.hashCode(this.mSunsetHour)) * 31) + Integer.hashCode(this.mSunsetMinute)) * 31) + Integer.hashCode(this.mSunsetSecond);
    }

    public final void setMCurrentTemperature(int i) {
        this.mCurrentTemperature = i;
    }

    public final void setMHumidity(int i) {
        this.mHumidity = i;
    }

    public final void setMMaxTemperature(int i) {
        this.mMaxTemperature = i;
    }

    public final void setMMinTemperature(int i) {
        this.mMinTemperature = i;
    }

    public final void setMPrecipitation(int i) {
        this.mPrecipitation = i;
    }

    public final void setMSunrisMinute(int i) {
        this.mSunrisMinute = i;
    }

    public final void setMSunrisSecond(int i) {
        this.mSunrisSecond = i;
    }

    public final void setMSunriseHour(int i) {
        this.mSunriseHour = i;
    }

    public final void setMSunsetHour(int i) {
        this.mSunsetHour = i;
    }

    public final void setMSunsetMinute(int i) {
        this.mSunsetMinute = i;
    }

    public final void setMSunsetSecond(int i) {
        this.mSunsetSecond = i;
    }

    public final void setMUltraVioletIntensity(int i) {
        this.mUltraVioletIntensity = i;
    }

    public final void setMVisibility(int i) {
        this.mVisibility = i;
    }

    public final void setMWeatherCode(int i) {
        this.mWeatherCode = i;
    }

    public final void setMWindSpeed(int i) {
        this.mWindSpeed = i;
    }

    @NotNull
    public String toString() {
        return "BleWeather(mCurrentTemperature=" + this.mCurrentTemperature + ", mMaxTemperature=" + this.mMaxTemperature + ", mMinTemperature=" + this.mMinTemperature + ", mWeatherCode=" + this.mWeatherCode + ", mWindSpeed=" + this.mWindSpeed + ", mHumidity=" + this.mHumidity + ", mVisibility=" + this.mVisibility + ", mUltraVioletIntensity=" + this.mUltraVioletIntensity + ", mPrecipitation=" + this.mPrecipitation + ", mSunriseHour=" + this.mSunriseHour + ", mSunrisMinute=" + this.mSunrisMinute + ", mSunrisSecond=" + this.mSunrisSecond + ", mSunsetHour=" + this.mSunsetHour + ", mSunsetMinute=" + this.mSunsetMinute + ", mSunsetSecond=" + this.mSunsetSecond + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleWeather2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        this.mCurrentTemperature = i;
        this.mMaxTemperature = i2;
        this.mMinTemperature = i3;
        this.mWeatherCode = i4;
        this.mWindSpeed = i5;
        this.mHumidity = i6;
        this.mVisibility = i7;
        this.mUltraVioletIntensity = i8;
        this.mPrecipitation = i9;
        this.mSunriseHour = i10;
        this.mSunrisMinute = i11;
        this.mSunrisSecond = i12;
        this.mSunsetHour = i13;
        this.mSunsetMinute = i14;
        this.mSunsetSecond = i15;
    }
}
