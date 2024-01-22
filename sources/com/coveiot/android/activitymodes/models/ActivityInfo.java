package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityInfo implements Serializable {
    @Nullable
    private String activityBaseUnit;
    @Nullable
    private String activityCode;
    @Nullable
    private String activityType;
    @Nullable
    private String target;
    @Nullable
    private String targetAchieved;
    @Nullable
    private String title;

    public ActivityInfo() {
        this(null, null, null, null, null, null, 63, null);
    }

    public ActivityInfo(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        this.activityType = str;
        this.title = str2;
        this.activityCode = str3;
        this.target = str4;
        this.targetAchieved = str5;
        this.activityBaseUnit = str6;
    }

    public static /* synthetic */ ActivityInfo copy$default(ActivityInfo activityInfo, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = activityInfo.activityType;
        }
        if ((i & 2) != 0) {
            str2 = activityInfo.title;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = activityInfo.activityCode;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = activityInfo.target;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = activityInfo.targetAchieved;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = activityInfo.activityBaseUnit;
        }
        return activityInfo.copy(str, str7, str8, str9, str10, str6);
    }

    @Nullable
    public final String component1() {
        return this.activityType;
    }

    @Nullable
    public final String component2() {
        return this.title;
    }

    @Nullable
    public final String component3() {
        return this.activityCode;
    }

    @Nullable
    public final String component4() {
        return this.target;
    }

    @Nullable
    public final String component5() {
        return this.targetAchieved;
    }

    @Nullable
    public final String component6() {
        return this.activityBaseUnit;
    }

    @NotNull
    public final ActivityInfo copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        return new ActivityInfo(str, str2, str3, str4, str5, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityInfo) {
            ActivityInfo activityInfo = (ActivityInfo) obj;
            return Intrinsics.areEqual(this.activityType, activityInfo.activityType) && Intrinsics.areEqual(this.title, activityInfo.title) && Intrinsics.areEqual(this.activityCode, activityInfo.activityCode) && Intrinsics.areEqual(this.target, activityInfo.target) && Intrinsics.areEqual(this.targetAchieved, activityInfo.targetAchieved) && Intrinsics.areEqual(this.activityBaseUnit, activityInfo.activityBaseUnit);
        }
        return false;
    }

    @Nullable
    public final String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    @Nullable
    public final String getActivityCode() {
        return this.activityCode;
    }

    @Nullable
    public final String getActivityType() {
        return this.activityType;
    }

    @Nullable
    public final String getTarget() {
        return this.target;
    }

    @Nullable
    public final String getTargetAchieved() {
        return this.targetAchieved;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String str = this.activityType;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.activityCode;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.target;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.targetAchieved;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.activityBaseUnit;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public final void setActivityBaseUnit(@Nullable String str) {
        this.activityBaseUnit = str;
    }

    public final void setActivityCode(@Nullable String str) {
        this.activityCode = str;
    }

    public final void setActivityType(@Nullable String str) {
        this.activityType = str;
    }

    public final void setTarget(@Nullable String str) {
        this.target = str;
    }

    public final void setTargetAchieved(@Nullable String str) {
        this.targetAchieved = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    @NotNull
    public String toString() {
        return "ActivityInfo(activityType=" + this.activityType + ", title=" + this.title + ", activityCode=" + this.activityCode + ", target=" + this.target + ", targetAchieved=" + this.targetAchieved + ", activityBaseUnit=" + this.activityBaseUnit + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ ActivityInfo(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6);
    }
}
