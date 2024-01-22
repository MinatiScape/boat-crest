package com.coveiot.android.activitymodes.database.entities;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EntityPreparationActivity implements Serializable {
    @Nullable
    private String activityBaseUnit;
    @Nullable
    private String activityCode;
    @Nullable
    private String activityType;
    @Nullable
    private String hrZoneId;
    @Nullable
    private String target;
    @Nullable
    private String targetAchieved;
    @Nullable
    private String title;

    public boolean equals(@Nullable Object obj) {
        String str = this.activityCode;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity");
        return Intrinsics.areEqual(str, ((EntityPreparationActivity) obj).activityCode);
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
    public final String getHrZoneId() {
        return this.hrZoneId;
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
        String str = this.title;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.activityType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.activityCode;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.target;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.activityBaseUnit;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.targetAchieved;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.hrZoneId;
        return hashCode6 + (str7 != null ? str7.hashCode() : 0);
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

    public final void setHrZoneId(@Nullable String str) {
        this.hrZoneId = str;
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
}
