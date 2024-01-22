package com.coveiot.android.watchfacecore.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class WatchFaceLayoutInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f6113a;
    @Nullable
    public Integer b;
    @Nullable
    public Integer c;
    @Nullable
    public Integer d;
    @Nullable
    public Integer e;
    @Nullable
    public String f;
    @Nullable
    public File g;
    @Nullable
    public Integer h;
    @Nullable
    public Integer i;
    @Nullable
    public Integer j;
    @Nullable
    public Integer k;
    @Nullable
    public Integer l;
    @Nullable
    public Integer m;
    @Nullable
    public String n;
    @Nullable
    public String o;

    public WatchFaceLayoutInfo() {
        this(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
    }

    public WatchFaceLayoutInfo(int i, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable String str, @Nullable File file, @Nullable Integer num5, @Nullable Integer num6, @Nullable Integer num7, @Nullable Integer num8, @Nullable Integer num9, @Nullable Integer num10, @Nullable String str2, @Nullable String str3) {
        this.f6113a = i;
        this.b = num;
        this.c = num2;
        this.d = num3;
        this.e = num4;
        this.f = str;
        this.g = file;
        this.h = num5;
        this.i = num6;
        this.j = num7;
        this.k = num8;
        this.l = num9;
        this.m = num10;
        this.n = str2;
        this.o = str3;
    }

    public final int component1() {
        return this.f6113a;
    }

    @Nullable
    public final Integer component10() {
        return this.j;
    }

    @Nullable
    public final Integer component11() {
        return this.k;
    }

    @Nullable
    public final Integer component12() {
        return this.l;
    }

    @Nullable
    public final Integer component13() {
        return this.m;
    }

    @Nullable
    public final String component14() {
        return this.n;
    }

    @Nullable
    public final String component15() {
        return this.o;
    }

    @Nullable
    public final Integer component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @Nullable
    public final Integer component4() {
        return this.d;
    }

    @Nullable
    public final Integer component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @Nullable
    public final File component7() {
        return this.g;
    }

    @Nullable
    public final Integer component8() {
        return this.h;
    }

    @Nullable
    public final Integer component9() {
        return this.i;
    }

    @NotNull
    public final WatchFaceLayoutInfo copy(int i, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable String str, @Nullable File file, @Nullable Integer num5, @Nullable Integer num6, @Nullable Integer num7, @Nullable Integer num8, @Nullable Integer num9, @Nullable Integer num10, @Nullable String str2, @Nullable String str3) {
        return new WatchFaceLayoutInfo(i, num, num2, num3, num4, str, file, num5, num6, num7, num8, num9, num10, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchFaceLayoutInfo) {
            WatchFaceLayoutInfo watchFaceLayoutInfo = (WatchFaceLayoutInfo) obj;
            return this.f6113a == watchFaceLayoutInfo.f6113a && Intrinsics.areEqual(this.b, watchFaceLayoutInfo.b) && Intrinsics.areEqual(this.c, watchFaceLayoutInfo.c) && Intrinsics.areEqual(this.d, watchFaceLayoutInfo.d) && Intrinsics.areEqual(this.e, watchFaceLayoutInfo.e) && Intrinsics.areEqual(this.f, watchFaceLayoutInfo.f) && Intrinsics.areEqual(this.g, watchFaceLayoutInfo.g) && Intrinsics.areEqual(this.h, watchFaceLayoutInfo.h) && Intrinsics.areEqual(this.i, watchFaceLayoutInfo.i) && Intrinsics.areEqual(this.j, watchFaceLayoutInfo.j) && Intrinsics.areEqual(this.k, watchFaceLayoutInfo.k) && Intrinsics.areEqual(this.l, watchFaceLayoutInfo.l) && Intrinsics.areEqual(this.m, watchFaceLayoutInfo.m) && Intrinsics.areEqual(this.n, watchFaceLayoutInfo.n) && Intrinsics.areEqual(this.o, watchFaceLayoutInfo.o);
        }
        return false;
    }

    @Nullable
    public final File getBackgroundPictureFile() {
        return this.g;
    }

    @Nullable
    public final String getBackgroundPictureMd5() {
        return this.f;
    }

    @Nullable
    public final Integer getBottomContent() {
        return this.d;
    }

    @Nullable
    public final String getCompressionType() {
        return this.n;
    }

    @Nullable
    public final Integer getHeight() {
        return this.j;
    }

    @Nullable
    public final Integer getImageId() {
        return this.i;
    }

    public final int getPICTURE_MD5_LENGTH() {
        return this.f6113a;
    }

    @Nullable
    public final Integer getPosition() {
        return this.b;
    }

    @Nullable
    public final Integer getTextColor() {
        return this.e;
    }

    @Nullable
    public final Integer getThumbHeight() {
        return this.l;
    }

    @Nullable
    public final Integer getThumbWidth() {
        return this.m;
    }

    @Nullable
    public final String getTimePosition() {
        return this.o;
    }

    @Nullable
    public final Integer getTopContent() {
        return this.c;
    }

    @Nullable
    public final Integer getWatchFaceId() {
        return this.h;
    }

    @Nullable
    public final Integer getWidth() {
        return this.k;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f6113a) * 31;
        Integer num = this.b;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.c;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.d;
        int hashCode4 = (hashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.e;
        int hashCode5 = (hashCode4 + (num4 == null ? 0 : num4.hashCode())) * 31;
        String str = this.f;
        int hashCode6 = (hashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        File file = this.g;
        int hashCode7 = (hashCode6 + (file == null ? 0 : file.hashCode())) * 31;
        Integer num5 = this.h;
        int hashCode8 = (hashCode7 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.i;
        int hashCode9 = (hashCode8 + (num6 == null ? 0 : num6.hashCode())) * 31;
        Integer num7 = this.j;
        int hashCode10 = (hashCode9 + (num7 == null ? 0 : num7.hashCode())) * 31;
        Integer num8 = this.k;
        int hashCode11 = (hashCode10 + (num8 == null ? 0 : num8.hashCode())) * 31;
        Integer num9 = this.l;
        int hashCode12 = (hashCode11 + (num9 == null ? 0 : num9.hashCode())) * 31;
        Integer num10 = this.m;
        int hashCode13 = (hashCode12 + (num10 == null ? 0 : num10.hashCode())) * 31;
        String str2 = this.n;
        int hashCode14 = (hashCode13 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.o;
        return hashCode14 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setBackgroundPictureFile(@Nullable File file) {
        this.g = file;
    }

    public final void setBackgroundPictureMd5(@Nullable String str) {
        this.f = str;
    }

    public final void setBottomContent(@Nullable Integer num) {
        this.d = num;
    }

    public final void setCompressionType(@Nullable String str) {
        this.n = str;
    }

    public final void setHeight(@Nullable Integer num) {
        this.j = num;
    }

    public final void setImageId(@Nullable Integer num) {
        this.i = num;
    }

    public final void setPICTURE_MD5_LENGTH(int i) {
        this.f6113a = i;
    }

    public final void setPosition(@Nullable Integer num) {
        this.b = num;
    }

    public final void setTextColor(@Nullable Integer num) {
        this.e = num;
    }

    public final void setThumbHeight(@Nullable Integer num) {
        this.l = num;
    }

    public final void setThumbWidth(@Nullable Integer num) {
        this.m = num;
    }

    public final void setTimePosition(@Nullable String str) {
        this.o = str;
    }

    public final void setTopContent(@Nullable Integer num) {
        this.c = num;
    }

    public final void setWatchFaceId(@Nullable Integer num) {
        this.h = num;
    }

    public final void setWidth(@Nullable Integer num) {
        this.k = num;
    }

    @NotNull
    public String toString() {
        return "WatchFaceLayoutInfo(PICTURE_MD5_LENGTH=" + this.f6113a + ", position=" + this.b + ", topContent=" + this.c + ", bottomContent=" + this.d + ", textColor=" + this.e + ", backgroundPictureMd5=" + this.f + ", backgroundPictureFile=" + this.g + ", watchFaceId=" + this.h + ", imageId=" + this.i + ", height=" + this.j + ", width=" + this.k + ", thumbHeight=" + this.l + ", thumbWidth=" + this.m + ", compressionType=" + this.n + ", timePosition=" + this.o + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WatchFaceLayoutInfo(int i, Integer num, Integer num2, Integer num3, Integer num4, String str, File file, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 32 : i, (i2 & 2) != 0 ? 0 : num, (i2 & 4) != 0 ? 0 : num2, (i2 & 8) != 0 ? 0 : num3, (i2 & 16) != 0 ? 0 : num4, (i2 & 32) != 0 ? null : str, (i2 & 64) != 0 ? null : file, (i2 & 128) != 0 ? null : num5, (i2 & 256) != 0 ? null : num6, (i2 & 512) != 0 ? 0 : num7, (i2 & 1024) != 0 ? 0 : num8, (i2 & 2048) != 0 ? 0 : num9, (i2 & 4096) != 0 ? 0 : num10, (i2 & 8192) != 0 ? null : str2, (i2 & 16384) == 0 ? str3 : null);
    }
}
