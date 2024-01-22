package com.coveiot.android.sportsnotification.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.realsil.sdk.dfu.DfuException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class WatchCricketUIModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f5878a;
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
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;

    public WatchCricketUIModel() {
        this(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, null);
    }

    public WatchCricketUIModel(@NotNull String backGroundFileName, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
        Intrinsics.checkNotNullParameter(backGroundFileName, "backGroundFileName");
        this.f5878a = backGroundFileName;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = i9;
        this.k = i10;
        this.l = i11;
        this.m = i12;
        this.n = i13;
        this.o = i14;
        this.p = i15;
        this.q = i16;
        this.r = i17;
        this.s = i18;
    }

    @NotNull
    public final String component1() {
        return this.f5878a;
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

    public final int component13() {
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
    public final WatchCricketUIModel copy(@NotNull String backGroundFileName, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
        Intrinsics.checkNotNullParameter(backGroundFileName, "backGroundFileName");
        return new WatchCricketUIModel(backGroundFileName, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchCricketUIModel) {
            WatchCricketUIModel watchCricketUIModel = (WatchCricketUIModel) obj;
            return Intrinsics.areEqual(this.f5878a, watchCricketUIModel.f5878a) && this.b == watchCricketUIModel.b && this.c == watchCricketUIModel.c && this.d == watchCricketUIModel.d && this.e == watchCricketUIModel.e && this.f == watchCricketUIModel.f && this.g == watchCricketUIModel.g && this.h == watchCricketUIModel.h && this.i == watchCricketUIModel.i && this.j == watchCricketUIModel.j && this.k == watchCricketUIModel.k && this.l == watchCricketUIModel.l && this.m == watchCricketUIModel.m && this.n == watchCricketUIModel.n && this.o == watchCricketUIModel.o && this.p == watchCricketUIModel.p && this.q == watchCricketUIModel.q && this.r == watchCricketUIModel.r && this.s == watchCricketUIModel.s;
        }
        return false;
    }

    @NotNull
    public final String getBackGroundFileName() {
        return this.f5878a;
    }

    public final int getBackgroundFileHeight() {
        return this.c;
    }

    public final int getBackgroundFileWidth() {
        return this.b;
    }

    public final int getGameStatusXPosition() {
        return this.l;
    }

    public final int getGameStatusXPosition2() {
        return this.m;
    }

    public final int getGameStatusYPosition() {
        return this.n;
    }

    public final int getMatchFormatXPosition() {
        return this.q;
    }

    public final int getMatchFormatXPosition2() {
        return this.r;
    }

    public final int getMatchFormatYPosition() {
        return this.s;
    }

    public final int getTeamANameXPosition() {
        return this.d;
    }

    public final int getTeamANameYPosition() {
        return this.e;
    }

    public final int getTeamAScoreXPosition() {
        return this.h;
    }

    public final int getTeamAScoreXPosition2() {
        return this.i;
    }

    public final int getTeamAScoreYPosition() {
        return this.o;
    }

    public final int getTeamBNameXPosition() {
        return this.f;
    }

    public final int getTeamBNameYPosition() {
        return this.g;
    }

    public final int getTeamBScoreXPosition() {
        return this.j;
    }

    public final int getTeamBScoreXPosition2() {
        return this.k;
    }

    public final int getTeamBScoreYPosition() {
        return this.p;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((this.f5878a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h)) * 31) + Integer.hashCode(this.i)) * 31) + Integer.hashCode(this.j)) * 31) + Integer.hashCode(this.k)) * 31) + Integer.hashCode(this.l)) * 31) + Integer.hashCode(this.m)) * 31) + Integer.hashCode(this.n)) * 31) + Integer.hashCode(this.o)) * 31) + Integer.hashCode(this.p)) * 31) + Integer.hashCode(this.q)) * 31) + Integer.hashCode(this.r)) * 31) + Integer.hashCode(this.s);
    }

    public final void setBackGroundFileName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f5878a = str;
    }

    public final void setBackgroundFileHeight(int i) {
        this.c = i;
    }

    public final void setBackgroundFileWidth(int i) {
        this.b = i;
    }

    public final void setGameStatusXPosition(int i) {
        this.l = i;
    }

    public final void setGameStatusXPosition2(int i) {
        this.m = i;
    }

    public final void setGameStatusYPosition(int i) {
        this.n = i;
    }

    public final void setMatchFormatXPosition(int i) {
        this.q = i;
    }

    public final void setMatchFormatXPosition2(int i) {
        this.r = i;
    }

    public final void setMatchFormatYPosition(int i) {
        this.s = i;
    }

    public final void setTeamANameXPosition(int i) {
        this.d = i;
    }

    public final void setTeamANameYPosition(int i) {
        this.e = i;
    }

    public final void setTeamAScoreXPosition(int i) {
        this.h = i;
    }

    public final void setTeamAScoreXPosition2(int i) {
        this.i = i;
    }

    public final void setTeamAScoreYPosition(int i) {
        this.o = i;
    }

    public final void setTeamBNameXPosition(int i) {
        this.f = i;
    }

    public final void setTeamBNameYPosition(int i) {
        this.g = i;
    }

    public final void setTeamBScoreXPosition(int i) {
        this.j = i;
    }

    public final void setTeamBScoreXPosition2(int i) {
        this.k = i;
    }

    public final void setTeamBScoreYPosition(int i) {
        this.p = i;
    }

    @NotNull
    public String toString() {
        return "WatchCricketUIModel(backGroundFileName=" + this.f5878a + ", backgroundFileWidth=" + this.b + ", backgroundFileHeight=" + this.c + ", teamANameXPosition=" + this.d + ", teamANameYPosition=" + this.e + ", teamBNameXPosition=" + this.f + ", teamBNameYPosition=" + this.g + ", teamAScoreXPosition=" + this.h + ", teamAScoreXPosition2=" + this.i + ", teamBScoreXPosition=" + this.j + ", teamBScoreXPosition2=" + this.k + ", gameStatusXPosition=" + this.l + ", gameStatusXPosition2=" + this.m + ", gameStatusYPosition=" + this.n + ", teamAScoreYPosition=" + this.o + ", teamBScoreYPosition=" + this.p + ", matchFormatXPosition=" + this.q + ", matchFormatXPosition2=" + this.r + ", matchFormatYPosition=" + this.s + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WatchCricketUIModel(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, DefaultConstructorMarker defaultConstructorMarker) {
        this((i19 & 1) != 0 ? "cricket_bg.bmp" : str, (i19 & 2) != 0 ? 240 : i, (i19 & 4) != 0 ? DfuException.ERROR_ENTER_OTA_MODE_FAILED : i2, (i19 & 8) != 0 ? 25 : i3, (i19 & 16) != 0 ? 140 : i4, (i19 & 32) == 0 ? i5 : 25, (i19 & 64) != 0 ? 179 : i6, (i19 & 128) != 0 ? 120 : i7, (i19 & 256) != 0 ? 105 : i8, (i19 & 512) != 0 ? 120 : i9, (i19 & 1024) == 0 ? i10 : 105, (i19 & 2048) != 0 ? 115 : i11, (i19 & 4096) != 0 ? 115 : i12, (i19 & 8192) != 0 ? 235 : i13, (i19 & 16384) != 0 ? 140 : i14, (i19 & 32768) != 0 ? 179 : i15, (i19 & 65536) == 0 ? i16 : 115, (i19 & 131072) != 0 ? 120 : i17, (i19 & 262144) != 0 ? 47 : i18);
    }
}
