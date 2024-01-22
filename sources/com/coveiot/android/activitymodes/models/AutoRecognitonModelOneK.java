package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AutoRecognitonModelOneK {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f2848a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public int f;
    @Nullable
    public String g;
    public int h;
    @Nullable
    public final String i;

    public AutoRecognitonModelOneK(@NotNull String activityName, boolean z, boolean z2, int i, int i2, int i3, @Nullable String str, int i4, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(activityName, "activityName");
        this.f2848a = activityName;
        this.b = z;
        this.c = z2;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = str;
        this.h = i4;
        this.i = str2;
    }

    @NotNull
    public final String component1() {
        return this.f2848a;
    }

    public final boolean component2() {
        return this.b;
    }

    public final boolean component3() {
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

    @Nullable
    public final String component7() {
        return this.g;
    }

    public final int component8() {
        return this.h;
    }

    @Nullable
    public final String component9() {
        return this.i;
    }

    @NotNull
    public final AutoRecognitonModelOneK copy(@NotNull String activityName, boolean z, boolean z2, int i, int i2, int i3, @Nullable String str, int i4, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(activityName, "activityName");
        return new AutoRecognitonModelOneK(activityName, z, z2, i, i2, i3, str, i4, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AutoRecognitonModelOneK) {
            AutoRecognitonModelOneK autoRecognitonModelOneK = (AutoRecognitonModelOneK) obj;
            return Intrinsics.areEqual(this.f2848a, autoRecognitonModelOneK.f2848a) && this.b == autoRecognitonModelOneK.b && this.c == autoRecognitonModelOneK.c && this.d == autoRecognitonModelOneK.d && this.e == autoRecognitonModelOneK.e && this.f == autoRecognitonModelOneK.f && Intrinsics.areEqual(this.g, autoRecognitonModelOneK.g) && this.h == autoRecognitonModelOneK.h && Intrinsics.areEqual(this.i, autoRecognitonModelOneK.i);
        }
        return false;
    }

    public final int getActivityCategoryId() {
        return this.d;
    }

    @Nullable
    public final String getActivityCode() {
        return this.g;
    }

    public final int getActivityId() {
        return this.e;
    }

    @NotNull
    public final String getActivityName() {
        return this.f2848a;
    }

    public final int getByteOrderInFW() {
        return this.h;
    }

    public final int getFwActivityId() {
        return this.f;
    }

    @Nullable
    public final String getIconUrl() {
        return this.i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.f2848a.hashCode() * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.c;
        int hashCode2 = (((((((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31;
        String str = this.g;
        int hashCode3 = (((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.h)) * 31;
        String str2 = this.i;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isFromOneKActivity() {
        return this.c;
    }

    public final boolean isSelected() {
        return this.b;
    }

    public final void setActivityCategoryId(int i) {
        this.d = i;
    }

    public final void setActivityCode(@Nullable String str) {
        this.g = str;
    }

    public final void setActivityId(int i) {
        this.e = i;
    }

    public final void setActivityName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f2848a = str;
    }

    public final void setByteOrderInFW(int i) {
        this.h = i;
    }

    public final void setFromOneKActivity(boolean z) {
        this.c = z;
    }

    public final void setFwActivityId(int i) {
        this.f = i;
    }

    public final void setSelected(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "AutoRecognitonModelOneK(activityName=" + this.f2848a + ", isSelected=" + this.b + ", isFromOneKActivity=" + this.c + ", activityCategoryId=" + this.d + ", activityId=" + this.e + ", fwActivityId=" + this.f + ", activityCode=" + this.g + ", byteOrderInFW=" + this.h + ", iconUrl=" + this.i + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AutoRecognitonModelOneK(String str, boolean z, boolean z2, int i, int i2, int i3, String str2, int i4, String str3, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i5 & 4) != 0 ? false : z2, (i5 & 8) != 0 ? 0 : i, (i5 & 16) != 0 ? 0 : i2, (i5 & 32) != 0 ? 0 : i3, (i5 & 64) != 0 ? null : str2, i4, str3);
    }
}
