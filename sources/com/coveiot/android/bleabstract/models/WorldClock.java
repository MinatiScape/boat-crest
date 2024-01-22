package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorldClock implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f3459a;
    @Nullable
    public String b;
    public int c;
    public int d;
    public int e;
    public double f;
    public double g;

    public WorldClock() {
        this(0, null, 0, 0, 0, 0.0d, 0.0d, 127, null);
    }

    public WorldClock(int i, @Nullable String str, int i2, int i3, int i4, double d, double d2) {
        this.f3459a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = d;
        this.g = d2;
    }

    public final int component1() {
        return this.f3459a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    public final double component6() {
        return this.f;
    }

    public final double component7() {
        return this.g;
    }

    @NotNull
    public final WorldClock copy(int i, @Nullable String str, int i2, int i3, int i4, double d, double d2) {
        return new WorldClock(i, str, i2, i3, i4, d, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WorldClock) {
            WorldClock worldClock = (WorldClock) obj;
            return this.f3459a == worldClock.f3459a && Intrinsics.areEqual(this.b, worldClock.b) && this.c == worldClock.c && this.d == worldClock.d && this.e == worldClock.e && Double.compare(this.f, worldClock.f) == 0 && Double.compare(this.g, worldClock.g) == 0;
        }
        return false;
    }

    @Nullable
    public final String getCityName() {
        return this.b;
    }

    public final int getId() {
        return this.f3459a;
    }

    public final double getLatitude() {
        return this.f;
    }

    public final double getLongitude() {
        return this.g;
    }

    public final int getSunRise() {
        return this.e;
    }

    public final int getSunSet() {
        return this.d;
    }

    public final int getTimeZoneOffsetMinutes() {
        return this.c;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f3459a) * 31;
        String str = this.b;
        return ((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Double.hashCode(this.f)) * 31) + Double.hashCode(this.g);
    }

    public final void setCityName(@Nullable String str) {
        this.b = str;
    }

    public final void setId(int i) {
        this.f3459a = i;
    }

    public final void setLatitude(double d) {
        this.f = d;
    }

    public final void setLongitude(double d) {
        this.g = d;
    }

    public final void setSunRise(int i) {
        this.e = i;
    }

    public final void setSunSet(int i) {
        this.d = i;
    }

    public final void setTimeZoneOffsetMinutes(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "WorldClock(id=" + this.f3459a + ", cityName=" + this.b + ", timeZoneOffsetMinutes=" + this.c + ", sunSet=" + this.d + ", sunRise=" + this.e + ", latitude=" + this.f + ", longitude=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WorldClock(int i, String str, int i2, int i3, int i4, double d, double d2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? null : str, (i5 & 4) != 0 ? 0 : i2, (i5 & 8) != 0 ? 0 : i3, (i5 & 16) == 0 ? i4 : 0, (i5 & 32) != 0 ? 0.0d : d, (i5 & 64) == 0 ? d2 : 0.0d);
    }
}
