package com.coveiot.android.remotecommandframework.alexa.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SStepsTargetInfo extends SCommandInfo {
    @Nullable
    private transient String activityBaseUnit;
    @Nullable
    private transient String activityType;
    @Nullable
    private transient String createdDate;
    @Nullable
    private transient Integer goalId;
    @Nullable
    private transient String modifiedDate;
    @Nullable
    private transient String startDate;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Nullable
    private Integer target;
    @Nullable
    private transient String targetAchieved;
    @Nullable
    private transient Integer targetedDays;

    @Nullable
    public final String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    @Nullable
    public final String getActivityType() {
        return this.activityType;
    }

    @Nullable
    public final String getCreatedDate() {
        return this.createdDate;
    }

    @Nullable
    public final Integer getGoalId() {
        return this.goalId;
    }

    @Nullable
    public final String getModifiedDate() {
        return this.modifiedDate;
    }

    @Nullable
    public final String getStartDate() {
        return this.startDate;
    }

    @Nullable
    public final Integer getTarget() {
        return this.target;
    }

    @Nullable
    public final String getTargetAchieved() {
        return this.targetAchieved;
    }

    @Nullable
    public final Integer getTargetedDays() {
        return this.targetedDays;
    }

    public final void setActivityBaseUnit(@Nullable String str) {
        this.activityBaseUnit = str;
    }

    public final void setActivityType(@Nullable String str) {
        this.activityType = str;
    }

    public final void setCreatedDate(@Nullable String str) {
        this.createdDate = str;
    }

    public final void setGoalId(@Nullable Integer num) {
        this.goalId = num;
    }

    public final void setModifiedDate(@Nullable String str) {
        this.modifiedDate = str;
    }

    public final void setStartDate(@Nullable String str) {
        this.startDate = str;
    }

    public final void setTarget(@Nullable Integer num) {
        this.target = num;
    }

    public final void setTargetAchieved(@Nullable String str) {
        this.targetAchieved = str;
    }

    public final void setTargetedDays(@Nullable Integer num) {
        this.targetedDays = num;
    }
}
