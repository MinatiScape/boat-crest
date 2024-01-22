package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class DeviceSettingsInfoResponse implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3594a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public int g;
    public int h = 5;
    public int i;
    public boolean j;

    public final int getBaseHeartRate() {
        return this.i;
    }

    public final int getDisPlayScreenBrightness() {
        return this.h;
    }

    public final int getWatchFacePosition() {
        return this.g;
    }

    public final boolean isAlarmMasterSwitchON() {
        return this.j;
    }

    public final boolean isDistanceUnitInMile() {
        return this.c;
    }

    public final boolean isLiftWristON() {
        return this.f3594a;
    }

    public final boolean isNightModeEnabled() {
        return this.e;
    }

    public final boolean isSilentModeEnabled() {
        return this.f;
    }

    public final boolean isTemperatureUnitInFahrenheit() {
        return this.b;
    }

    public final boolean isTimeIn12HRFormat() {
        return this.d;
    }

    public final void setAlarmMasterSwitchON(boolean z) {
        this.j = z;
    }

    public final void setBaseHeartRate(int i) {
        this.i = i;
    }

    public final void setDisPlayScreenBrightness(int i) {
        this.h = i;
    }

    public final void setDistanceUnitInMile(boolean z) {
        this.c = z;
    }

    public final void setLiftWristON(boolean z) {
        this.f3594a = z;
    }

    public final void setNightModeEnabled(boolean z) {
        this.e = z;
    }

    public final void setSilentModeEnabled(boolean z) {
        this.f = z;
    }

    public final void setTemperatureUnitInFahrenheit(boolean z) {
        this.b = z;
    }

    public final void setTimeIn12HRFormat(boolean z) {
        this.d = z;
    }

    public final void setWatchFacePosition(int i) {
        this.g = i;
    }

    @NotNull
    public String toString() {
        return "DeviceSettingsInfoResponse(isLiftWristON=" + this.f3594a + ", isTemperatureUnitInFahrenheit=" + this.b + ", isDistanceUnitInMile=" + this.c + ", isTimeIn12HRFormat=" + this.d + ", isNightModeEnabled=" + this.e + ", isSilentModeEnabled=" + this.f + ", watchFacePosition=" + this.g + ", disPlayScreenBrightness=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
