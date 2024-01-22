package com.coveiot.android.khperformancecalculator.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class KHPActivityData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4640a;
    public final long b;
    public final long c;
    public final float d;
    public final int e;
    public final int f;
    public int g;
    public int h;
    public int i;
    public float j;
    @Nullable
    public String k;
    public int l;
    public int m;
    public int n;
    public float o;
    public int p;
    public float q;
    public int r;
    @Nullable
    public String s;
    public int t;
    public int u;
    public int v;
    @Nullable
    public final String w;
    public int x;
    public int y;
    public float z;

    public KHPActivityData(@NotNull String type, long j, long j2, float f, int i, int i2, int i3, int i4, int i5, float f2, @Nullable String str, int i6, int i7, int i8, float f3, int i9, float f4, int i10, @Nullable String str2, int i11, int i12, int i13, @Nullable String str3, int i14, int i15, float f5) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.f4640a = type;
        this.b = j;
        this.c = j2;
        this.d = f;
        this.e = i;
        this.f = i2;
        this.g = i3;
        this.h = i4;
        this.i = i5;
        this.j = f2;
        this.k = str;
        this.l = i6;
        this.m = i7;
        this.n = i8;
        this.o = f3;
        this.p = i9;
        this.q = f4;
        this.r = i10;
        this.s = str2;
        this.t = i11;
        this.u = i12;
        this.v = i13;
        this.w = str3;
        this.x = i14;
        this.y = i15;
        this.z = f5;
    }

    @NotNull
    public final String component1() {
        return this.f4640a;
    }

    public final float component10() {
        return this.j;
    }

    @Nullable
    public final String component11() {
        return this.k;
    }

    public final int component12() {
        return this.l;
    }

    public final int component13() {
        return this.m;
    }

    public final int component14() {
        return this.n;
    }

    public final float component15() {
        return this.o;
    }

    public final int component16() {
        return this.p;
    }

    public final float component17() {
        return this.q;
    }

    public final int component18() {
        return this.r;
    }

    @Nullable
    public final String component19() {
        return this.s;
    }

    public final long component2() {
        return this.b;
    }

    public final int component20() {
        return this.t;
    }

    public final int component21() {
        return this.u;
    }

    public final int component22() {
        return this.v;
    }

    @Nullable
    public final String component23() {
        return this.w;
    }

    public final int component24() {
        return this.x;
    }

    public final int component25() {
        return this.y;
    }

    public final float component26() {
        return this.z;
    }

    public final long component3() {
        return this.c;
    }

    public final float component4() {
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
    public final KHPActivityData copy(@NotNull String type, long j, long j2, float f, int i, int i2, int i3, int i4, int i5, float f2, @Nullable String str, int i6, int i7, int i8, float f3, int i9, float f4, int i10, @Nullable String str2, int i11, int i12, int i13, @Nullable String str3, int i14, int i15, float f5) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new KHPActivityData(type, j, j2, f, i, i2, i3, i4, i5, f2, str, i6, i7, i8, f3, i9, f4, i10, str2, i11, i12, i13, str3, i14, i15, f5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KHPActivityData) {
            KHPActivityData kHPActivityData = (KHPActivityData) obj;
            return Intrinsics.areEqual(this.f4640a, kHPActivityData.f4640a) && this.b == kHPActivityData.b && this.c == kHPActivityData.c && Float.compare(this.d, kHPActivityData.d) == 0 && this.e == kHPActivityData.e && this.f == kHPActivityData.f && this.g == kHPActivityData.g && this.h == kHPActivityData.h && this.i == kHPActivityData.i && Float.compare(this.j, kHPActivityData.j) == 0 && Intrinsics.areEqual(this.k, kHPActivityData.k) && this.l == kHPActivityData.l && this.m == kHPActivityData.m && this.n == kHPActivityData.n && Float.compare(this.o, kHPActivityData.o) == 0 && this.p == kHPActivityData.p && Float.compare(this.q, kHPActivityData.q) == 0 && this.r == kHPActivityData.r && Intrinsics.areEqual(this.s, kHPActivityData.s) && this.t == kHPActivityData.t && this.u == kHPActivityData.u && this.v == kHPActivityData.v && Intrinsics.areEqual(this.w, kHPActivityData.w) && this.x == kHPActivityData.x && this.y == kHPActivityData.y && Float.compare(this.z, kHPActivityData.z) == 0;
        }
        return false;
    }

    public final int getActivityId() {
        return this.m;
    }

    public final float getAvgSpeed() {
        return this.o;
    }

    public final int getAvgStepFreq() {
        return this.y;
    }

    public final int getAvgStrideLength() {
        return this.p;
    }

    public final int getAvgStrokeFreq() {
        return this.x;
    }

    public final int getAvgSwolf() {
        return this.v;
    }

    public final float getCalorie() {
        return this.d;
    }

    public final int getCategoryID() {
        return this.n;
    }

    public final int getDistance() {
        return this.e;
    }

    public final int getDuration() {
        return this.l;
    }

    public final long getEndTime() {
        return this.c;
    }

    public final int getHeartRate() {
        return this.g;
    }

    @Nullable
    public final String getIndoorOutdoor() {
        return this.k;
    }

    public final int getMaxHeartRate() {
        return this.i;
    }

    public final float getMaxPace() {
        return this.q;
    }

    public final int getMaxSPM() {
        return this.r;
    }

    public final int getMinHeartRate() {
        return this.h;
    }

    public final float getMinPace() {
        return this.z;
    }

    public final float getPace() {
        return this.j;
    }

    @Nullable
    public final String getSessionId() {
        return this.w;
    }

    public final long getStartTime() {
        return this.b;
    }

    public final int getSteps() {
        return this.f;
    }

    @Nullable
    public final String getSwimStroke() {
        return this.s;
    }

    public final int getTotalLaps() {
        return this.u;
    }

    public final int getTotalStrokes() {
        return this.t;
    }

    @NotNull
    public final String getType() {
        return this.f4640a;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((((this.f4640a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + Long.hashCode(this.c)) * 31) + Float.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h)) * 31) + Integer.hashCode(this.i)) * 31) + Float.hashCode(this.j)) * 31;
        String str = this.k;
        int hashCode2 = (((((((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.l)) * 31) + Integer.hashCode(this.m)) * 31) + Integer.hashCode(this.n)) * 31) + Float.hashCode(this.o)) * 31) + Integer.hashCode(this.p)) * 31) + Float.hashCode(this.q)) * 31) + Integer.hashCode(this.r)) * 31;
        String str2 = this.s;
        int hashCode3 = (((((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.t)) * 31) + Integer.hashCode(this.u)) * 31) + Integer.hashCode(this.v)) * 31;
        String str3 = this.w;
        return ((((((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.hashCode(this.x)) * 31) + Integer.hashCode(this.y)) * 31) + Float.hashCode(this.z);
    }

    public final void setActivityId(int i) {
        this.m = i;
    }

    public final void setAvgSpeed(float f) {
        this.o = f;
    }

    public final void setAvgStepFreq(int i) {
        this.y = i;
    }

    public final void setAvgStrideLength(int i) {
        this.p = i;
    }

    public final void setAvgStrokeFreq(int i) {
        this.x = i;
    }

    public final void setAvgSwolf(int i) {
        this.v = i;
    }

    public final void setCategoryID(int i) {
        this.n = i;
    }

    public final void setDuration(int i) {
        this.l = i;
    }

    public final void setHeartRate(int i) {
        this.g = i;
    }

    public final void setIndoorOutdoor(@Nullable String str) {
        this.k = str;
    }

    public final void setMaxHeartRate(int i) {
        this.i = i;
    }

    public final void setMaxPace(float f) {
        this.q = f;
    }

    public final void setMaxSPM(int i) {
        this.r = i;
    }

    public final void setMinHeartRate(int i) {
        this.h = i;
    }

    public final void setMinPace(float f) {
        this.z = f;
    }

    public final void setPace(float f) {
        this.j = f;
    }

    public final void setSwimStroke(@Nullable String str) {
        this.s = str;
    }

    public final void setTotalLaps(int i) {
        this.u = i;
    }

    public final void setTotalStrokes(int i) {
        this.t = i;
    }

    @NotNull
    public String toString() {
        return "KHPActivityData(type=" + this.f4640a + ", startTime=" + this.b + ", endTime=" + this.c + ", calorie=" + this.d + ", distance=" + this.e + ", steps=" + this.f + ", heartRate=" + this.g + ", minHeartRate=" + this.h + ", maxHeartRate=" + this.i + ", pace=" + this.j + ", indoorOutdoor=" + this.k + ", duration=" + this.l + ", activityId=" + this.m + ", categoryID=" + this.n + ", avgSpeed=" + this.o + ", avgStrideLength=" + this.p + ", maxPace=" + this.q + ", maxSPM=" + this.r + ", swimStroke=" + this.s + ", totalStrokes=" + this.t + ", totalLaps=" + this.u + ", avgSwolf=" + this.v + ", sessionId=" + this.w + ", avgStrokeFreq=" + this.x + ", avgStepFreq=" + this.y + ", minPace=" + this.z + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KHPActivityData(String str, long j, long j2, float f, int i, int i2, int i3, int i4, int i5, float f2, String str2, int i6, int i7, int i8, float f3, int i9, float f4, int i10, String str3, int i11, int i12, int i13, String str4, int i14, int i15, float f5, int i16, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, j2, f, i, i2, i3, i4, i5, (i16 & 512) != 0 ? 0.0f : f2, str2, i6, (i16 & 4096) != 0 ? 0 : i7, (i16 & 8192) != 0 ? 0 : i8, (i16 & 16384) != 0 ? 0.0f : f3, (32768 & i16) != 0 ? 0 : i9, (65536 & i16) != 0 ? 0.0f : f4, (131072 & i16) != 0 ? 0 : i10, (262144 & i16) != 0 ? null : str3, (524288 & i16) != 0 ? 0 : i11, (1048576 & i16) != 0 ? 0 : i12, (2097152 & i16) != 0 ? 0 : i13, (4194304 & i16) != 0 ? null : str4, (8388608 & i16) != 0 ? 0 : i14, (16777216 & i16) != 0 ? 0 : i15, (i16 & 33554432) != 0 ? 0.0f : f5);
    }
}
