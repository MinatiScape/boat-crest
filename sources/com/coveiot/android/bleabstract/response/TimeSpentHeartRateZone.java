package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class TimeSpentHeartRateZone {

    /* renamed from: a  reason: collision with root package name */
    public int f3678a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public TimeSpentHeartRateZone(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f3678a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
    }

    public final int getZone0TimeInSecs() {
        return this.f3678a;
    }

    public final int getZone1TimeInSecs() {
        return this.b;
    }

    public final int getZone2TimeInSecs() {
        return this.c;
    }

    public final int getZone3TimeInSecs() {
        return this.d;
    }

    public final int getZone4TimeInSecs() {
        return this.e;
    }

    public final int getZone5TimeInSecs() {
        return this.f;
    }

    public final int getZone6TimeInSecs() {
        return this.g;
    }

    public final void setZone0TimeInSecs(int i) {
        this.f3678a = i;
    }

    public final void setZone1TimeInSecs(int i) {
        this.b = i;
    }

    public final void setZone2TimeInSecs(int i) {
        this.c = i;
    }

    public final void setZone3TimeInSecs(int i) {
        this.d = i;
    }

    public final void setZone4TimeInSecs(int i) {
        this.e = i;
    }

    public final void setZone5TimeInSecs(int i) {
        this.f = i;
    }

    public final void setZone6TimeInSecs(int i) {
        this.g = i;
    }

    @NotNull
    public String toString() {
        return "TimeSpentHeartRateZone(zone0TimeInSecs=" + this.f3678a + ", zone1TimeInSecs=" + this.b + ", zone2TimeInSecs=" + this.c + ", zone3TimeInSecs=" + this.d + ", zone4TimeInSecs=" + this.e + ", zone5TimeInSecs=" + this.f + ", zone6TimeInSecs=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }

    public TimeSpentHeartRateZone() {
        this(0, 0, 0, 0, 0, 0, 0);
    }
}
