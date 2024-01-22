package com.coveiot.khsmadb.workout;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mStart", DeviceKey.MacAddress}, tableName = "khBleWorkout")
/* loaded from: classes8.dex */
public final class KhBleWorkout {

    /* renamed from: a  reason: collision with root package name */
    public int f7164a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    @NotNull
    public String m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    @ColumnInfo(name = "isProcessed")
    public boolean v;

    public KhBleWorkout(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, @NotNull String macAddress, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7164a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
        this.i = i9;
        this.j = i10;
        this.k = i11;
        this.l = i12;
        this.m = macAddress;
        this.n = i13;
        this.o = i14;
        this.p = i15;
        this.q = i16;
        this.r = i17;
        this.s = i18;
        this.t = i19;
        this.u = i20;
    }

    public final int component1() {
        return this.f7164a;
    }

    public final int component10() {
        return this.j;
    }

    public final int component11() {
        return this.k;
    }

    public final int component12() {
        return this.l;
    }

    @NotNull
    public final String component13() {
        return this.m;
    }

    public final int component14() {
        return this.n;
    }

    public final int component15() {
        return this.o;
    }

    public final int component16() {
        return this.p;
    }

    public final int component17() {
        return this.q;
    }

    public final int component18() {
        return this.r;
    }

    public final int component19() {
        return this.s;
    }

    public final int component2() {
        return this.b;
    }

    public final int component20() {
        return this.t;
    }

    public final int component21() {
        return this.u;
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

    public final int component6() {
        return this.f;
    }

    public final int component7() {
        return this.g;
    }

    public final int component8() {
        return this.h;
    }

    public final int component9() {
        return this.i;
    }

    @NotNull
    public final KhBleWorkout copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, @NotNull String macAddress, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return new KhBleWorkout(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, macAddress, i13, i14, i15, i16, i17, i18, i19, i20);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhBleWorkout) {
            KhBleWorkout khBleWorkout = (KhBleWorkout) obj;
            return this.f7164a == khBleWorkout.f7164a && this.b == khBleWorkout.b && this.c == khBleWorkout.c && this.d == khBleWorkout.d && this.e == khBleWorkout.e && this.f == khBleWorkout.f && this.g == khBleWorkout.g && this.h == khBleWorkout.h && this.i == khBleWorkout.i && this.j == khBleWorkout.j && this.k == khBleWorkout.k && this.l == khBleWorkout.l && Intrinsics.areEqual(this.m, khBleWorkout.m) && this.n == khBleWorkout.n && this.o == khBleWorkout.o && this.p == khBleWorkout.p && this.q == khBleWorkout.q && this.r == khBleWorkout.r && this.s == khBleWorkout.s && this.t == khBleWorkout.t && this.u == khBleWorkout.u;
        }
        return false;
    }

    @NotNull
    public final String getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, this.b);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public final int getMAirPressure() {
        return this.e;
    }

    public final int getMAltitude() {
        return this.d;
    }

    public final int getMAvgBpm() {
        return this.n;
    }

    public final int getMCalorie() {
        return this.j;
    }

    public final int getMDistance() {
        return this.i;
    }

    public final int getMDuration() {
        return this.c;
    }

    public final int getMEnd() {
        return this.b;
    }

    public final int getMMaxBpm() {
        return this.o;
    }

    public final int getMMaxPace() {
        return this.t;
    }

    public final int getMMaxSpm() {
        return this.r;
    }

    public final int getMMinBpm() {
        return this.p;
    }

    public final int getMMinPace() {
        return this.u;
    }

    public final int getMMinSpm() {
        return this.s;
    }

    public final int getMMode() {
        return this.g;
    }

    public final int getMPace() {
        return this.l;
    }

    public final int getMSpeed() {
        return this.k;
    }

    public final int getMSpm() {
        return this.f;
    }

    public final int getMStart() {
        return this.f7164a;
    }

    public final int getMStep() {
        return this.h;
    }

    public final int getMUndefined() {
        return this.q;
    }

    @NotNull
    public final String getMacAddress() {
        return this.m;
    }

    @NotNull
    public final String getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, this.f7164a);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((Integer.hashCode(this.f7164a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h)) * 31) + Integer.hashCode(this.i)) * 31) + Integer.hashCode(this.j)) * 31) + Integer.hashCode(this.k)) * 31) + Integer.hashCode(this.l)) * 31) + this.m.hashCode()) * 31) + Integer.hashCode(this.n)) * 31) + Integer.hashCode(this.o)) * 31) + Integer.hashCode(this.p)) * 31) + Integer.hashCode(this.q)) * 31) + Integer.hashCode(this.r)) * 31) + Integer.hashCode(this.s)) * 31) + Integer.hashCode(this.t)) * 31) + Integer.hashCode(this.u);
    }

    public final boolean isProcessed() {
        return this.v;
    }

    public final void setEndDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
    }

    public final void setMAirPressure(int i) {
        this.e = i;
    }

    public final void setMAltitude(int i) {
        this.d = i;
    }

    public final void setMAvgBpm(int i) {
        this.n = i;
    }

    public final void setMCalorie(int i) {
        this.j = i;
    }

    public final void setMDistance(int i) {
        this.i = i;
    }

    public final void setMDuration(int i) {
        this.c = i;
    }

    public final void setMEnd(int i) {
        this.b = i;
    }

    public final void setMMaxBpm(int i) {
        this.o = i;
    }

    public final void setMMaxPace(int i) {
        this.t = i;
    }

    public final void setMMaxSpm(int i) {
        this.r = i;
    }

    public final void setMMinBpm(int i) {
        this.p = i;
    }

    public final void setMMinPace(int i) {
        this.u = i;
    }

    public final void setMMinSpm(int i) {
        this.s = i;
    }

    public final void setMMode(int i) {
        this.g = i;
    }

    public final void setMPace(int i) {
        this.l = i;
    }

    public final void setMSpeed(int i) {
        this.k = i;
    }

    public final void setMSpm(int i) {
        this.f = i;
    }

    public final void setMStart(int i) {
        this.f7164a = i;
    }

    public final void setMStep(int i) {
        this.h = i;
    }

    public final void setMUndefined(int i) {
        this.q = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.m = str;
    }

    public final void setProcessed(boolean z) {
        this.v = z;
    }

    public final void setStartDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
    }

    @NotNull
    public String toString() {
        return "KhBleWorkout(mStart=" + this.f7164a + ", mEnd=" + this.b + ", mDuration=" + this.c + ", mAltitude=" + this.d + ", mAirPressure=" + this.e + ", mSpm=" + this.f + ", mMode=" + this.g + ", mStep=" + this.h + ", mDistance=" + this.i + ", mCalorie=" + this.j + ", mSpeed=" + this.k + ", mPace=" + this.l + ", macAddress=" + this.m + ", mAvgBpm=" + this.n + ", mMaxBpm=" + this.o + ", mMinBpm=" + this.p + ", mUndefined=" + this.q + ", mMaxSpm=" + this.r + ", mMinSpm=" + this.s + ", mMaxPace=" + this.t + ", mMinPace=" + this.u + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhBleWorkout(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, String str, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, DefaultConstructorMarker defaultConstructorMarker) {
        this((i21 & 1) != 0 ? 0 : i, (i21 & 2) != 0 ? 0 : i2, (i21 & 4) != 0 ? 0 : i3, (i21 & 8) != 0 ? 0 : i4, (i21 & 16) != 0 ? 0 : i5, (i21 & 32) != 0 ? 0 : i6, (i21 & 64) != 0 ? 0 : i7, (i21 & 128) != 0 ? 0 : i8, (i21 & 256) != 0 ? 0 : i9, (i21 & 512) != 0 ? 0 : i10, (i21 & 1024) != 0 ? 0 : i11, (i21 & 2048) != 0 ? 0 : i12, str, (i21 & 8192) != 0 ? 0 : i13, (i21 & 16384) != 0 ? 0 : i14, (32768 & i21) != 0 ? 0 : i15, (65536 & i21) != 0 ? 0 : i16, (131072 & i21) != 0 ? 0 : i17, (262144 & i21) != 0 ? 0 : i18, (524288 & i21) != 0 ? 0 : i19, (i21 & 1048576) != 0 ? 0 : i20);
    }
}
