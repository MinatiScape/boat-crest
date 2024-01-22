package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AutoActivityDetectionServerInputData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f2847a;
    public int b;

    public AutoActivityDetectionServerInputData(@NotNull String activityCode, int i) {
        Intrinsics.checkNotNullParameter(activityCode, "activityCode");
        this.f2847a = activityCode;
        this.b = i;
    }

    public static /* synthetic */ AutoActivityDetectionServerInputData copy$default(AutoActivityDetectionServerInputData autoActivityDetectionServerInputData, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = autoActivityDetectionServerInputData.f2847a;
        }
        if ((i2 & 2) != 0) {
            i = autoActivityDetectionServerInputData.b;
        }
        return autoActivityDetectionServerInputData.copy(str, i);
    }

    @NotNull
    public final String component1() {
        return this.f2847a;
    }

    public final int component2() {
        return this.b;
    }

    @NotNull
    public final AutoActivityDetectionServerInputData copy(@NotNull String activityCode, int i) {
        Intrinsics.checkNotNullParameter(activityCode, "activityCode");
        return new AutoActivityDetectionServerInputData(activityCode, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AutoActivityDetectionServerInputData) {
            AutoActivityDetectionServerInputData autoActivityDetectionServerInputData = (AutoActivityDetectionServerInputData) obj;
            return Intrinsics.areEqual(this.f2847a, autoActivityDetectionServerInputData.f2847a) && this.b == autoActivityDetectionServerInputData.b;
        }
        return false;
    }

    @NotNull
    public final String getActivityCode() {
        return this.f2847a;
    }

    public final int getByteOrderInFW() {
        return this.b;
    }

    public int hashCode() {
        return (this.f2847a.hashCode() * 31) + Integer.hashCode(this.b);
    }

    public final void setActivityCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f2847a = str;
    }

    public final void setByteOrderInFW(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "AutoActivityDetectionServerInputData(activityCode=" + this.f2847a + ", byteOrderInFW=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
