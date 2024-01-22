package com.coveiot.android.theme.model;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SmallHealthCardInfo {
    @Nullable
    private Drawable drawable;
    private boolean isProgressSupported;
    private int progress;
    @Nullable
    private Drawable progressBackground;
    @Nullable
    private Drawable progressDrawable;
    @Nullable
    private String value1;
    @Nullable
    private String value2;
    @Nullable
    private String value3;
    @Nullable
    private String value4;
    @Nullable
    private String value5;
    @Nullable
    private String value6;

    public SmallHealthCardInfo() {
        this(null, null, null, null, null, null, null, 0, null, null, false, 2047, null);
    }

    public SmallHealthCardInfo(@Nullable Drawable drawable, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, int i, @Nullable Drawable drawable2, @Nullable Drawable drawable3, boolean z) {
        this.drawable = drawable;
        this.value1 = str;
        this.value2 = str2;
        this.value3 = str3;
        this.value4 = str4;
        this.value5 = str5;
        this.value6 = str6;
        this.progress = i;
        this.progressDrawable = drawable2;
        this.progressBackground = drawable3;
        this.isProgressSupported = z;
    }

    @Nullable
    public final Drawable component1() {
        return this.drawable;
    }

    @Nullable
    public final Drawable component10() {
        return this.progressBackground;
    }

    public final boolean component11() {
        return this.isProgressSupported;
    }

    @Nullable
    public final String component2() {
        return this.value1;
    }

    @Nullable
    public final String component3() {
        return this.value2;
    }

    @Nullable
    public final String component4() {
        return this.value3;
    }

    @Nullable
    public final String component5() {
        return this.value4;
    }

    @Nullable
    public final String component6() {
        return this.value5;
    }

    @Nullable
    public final String component7() {
        return this.value6;
    }

    public final int component8() {
        return this.progress;
    }

    @Nullable
    public final Drawable component9() {
        return this.progressDrawable;
    }

    @NotNull
    public final SmallHealthCardInfo copy(@Nullable Drawable drawable, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, int i, @Nullable Drawable drawable2, @Nullable Drawable drawable3, boolean z) {
        return new SmallHealthCardInfo(drawable, str, str2, str3, str4, str5, str6, i, drawable2, drawable3, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SmallHealthCardInfo) {
            SmallHealthCardInfo smallHealthCardInfo = (SmallHealthCardInfo) obj;
            return Intrinsics.areEqual(this.drawable, smallHealthCardInfo.drawable) && Intrinsics.areEqual(this.value1, smallHealthCardInfo.value1) && Intrinsics.areEqual(this.value2, smallHealthCardInfo.value2) && Intrinsics.areEqual(this.value3, smallHealthCardInfo.value3) && Intrinsics.areEqual(this.value4, smallHealthCardInfo.value4) && Intrinsics.areEqual(this.value5, smallHealthCardInfo.value5) && Intrinsics.areEqual(this.value6, smallHealthCardInfo.value6) && this.progress == smallHealthCardInfo.progress && Intrinsics.areEqual(this.progressDrawable, smallHealthCardInfo.progressDrawable) && Intrinsics.areEqual(this.progressBackground, smallHealthCardInfo.progressBackground) && this.isProgressSupported == smallHealthCardInfo.isProgressSupported;
        }
        return false;
    }

    @Nullable
    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final int getProgress() {
        return this.progress;
    }

    @Nullable
    public final Drawable getProgressBackground() {
        return this.progressBackground;
    }

    @Nullable
    public final Drawable getProgressDrawable() {
        return this.progressDrawable;
    }

    @Nullable
    public final String getValue1() {
        return this.value1;
    }

    @Nullable
    public final String getValue2() {
        return this.value2;
    }

    @Nullable
    public final String getValue3() {
        return this.value3;
    }

    @Nullable
    public final String getValue4() {
        return this.value4;
    }

    @Nullable
    public final String getValue5() {
        return this.value5;
    }

    @Nullable
    public final String getValue6() {
        return this.value6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Drawable drawable = this.drawable;
        int hashCode = (drawable == null ? 0 : drawable.hashCode()) * 31;
        String str = this.value1;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.value2;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.value3;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.value4;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.value5;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.value6;
        int hashCode7 = (((hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31) + Integer.hashCode(this.progress)) * 31;
        Drawable drawable2 = this.progressDrawable;
        int hashCode8 = (hashCode7 + (drawable2 == null ? 0 : drawable2.hashCode())) * 31;
        Drawable drawable3 = this.progressBackground;
        int hashCode9 = (hashCode8 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31;
        boolean z = this.isProgressSupported;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode9 + i;
    }

    public final boolean isProgressSupported() {
        return this.isProgressSupported;
    }

    public final void setDrawable(@Nullable Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final void setProgressBackground(@Nullable Drawable drawable) {
        this.progressBackground = drawable;
    }

    public final void setProgressDrawable(@Nullable Drawable drawable) {
        this.progressDrawable = drawable;
    }

    public final void setProgressSupported(boolean z) {
        this.isProgressSupported = z;
    }

    public final void setValue1(@Nullable String str) {
        this.value1 = str;
    }

    public final void setValue2(@Nullable String str) {
        this.value2 = str;
    }

    public final void setValue3(@Nullable String str) {
        this.value3 = str;
    }

    public final void setValue4(@Nullable String str) {
        this.value4 = str;
    }

    public final void setValue5(@Nullable String str) {
        this.value5 = str;
    }

    public final void setValue6(@Nullable String str) {
        this.value6 = str;
    }

    @NotNull
    public String toString() {
        return "SmallHealthCardInfo(drawable=" + this.drawable + ", value1=" + this.value1 + ", value2=" + this.value2 + ", value3=" + this.value3 + ", value4=" + this.value4 + ", value5=" + this.value5 + ", value6=" + this.value6 + ", progress=" + this.progress + ", progressDrawable=" + this.progressDrawable + ", progressBackground=" + this.progressBackground + ", isProgressSupported=" + this.isProgressSupported + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SmallHealthCardInfo(Drawable drawable, String str, String str2, String str3, String str4, String str5, String str6, int i, Drawable drawable2, Drawable drawable3, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : drawable, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? null : str6, (i2 & 128) != 0 ? 0 : i, (i2 & 256) != 0 ? null : drawable2, (i2 & 512) == 0 ? drawable3 : null, (i2 & 1024) == 0 ? z : false);
    }
}
