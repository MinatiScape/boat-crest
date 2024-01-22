package com.coveiot.android.bleabstract.request;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetGoalTargetRequest extends BleBaseRequest {
    public final int f;
    public final int g;
    public final int h;
    @Nullable
    public Integer i;
    @Nullable
    public Integer j;

    public SetGoalTargetRequest(int i, int i2, int i3) {
        this.f = i;
        this.g = i2;
        this.h = i3;
    }

    public static /* synthetic */ SetGoalTargetRequest copy$default(SetGoalTargetRequest setGoalTargetRequest, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = setGoalTargetRequest.f;
        }
        if ((i4 & 2) != 0) {
            i2 = setGoalTargetRequest.g;
        }
        if ((i4 & 4) != 0) {
            i3 = setGoalTargetRequest.h;
        }
        return setGoalTargetRequest.copy(i, i2, i3);
    }

    public final int component1() {
        return this.f;
    }

    public final int component2() {
        return this.g;
    }

    public final int component3() {
        return this.h;
    }

    @NotNull
    public final SetGoalTargetRequest copy(int i, int i2, int i3) {
        return new SetGoalTargetRequest(i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SetGoalTargetRequest) {
            SetGoalTargetRequest setGoalTargetRequest = (SetGoalTargetRequest) obj;
            return this.f == setGoalTargetRequest.f && this.g == setGoalTargetRequest.g && this.h == setGoalTargetRequest.h;
        }
        return false;
    }

    public final int getCalorieTarget() {
        return this.h;
    }

    public final int getDistanceTarget() {
        return this.g;
    }

    @Nullable
    public final Integer getExceriseDurationTarget() {
        return this.i;
    }

    @Nullable
    public final Integer getSleepTarget() {
        return this.j;
    }

    public final int getStepTarget() {
        return this.f;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.f) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h);
    }

    public final void setExceriseDurationTarget(@Nullable Integer num) {
        this.i = num;
    }

    public final void setSleepTarget(@Nullable Integer num) {
        this.j = num;
    }

    @NotNull
    public String toString() {
        return "SetGoalTargetRequest(stepTarget=" + this.f + ", distanceTarget=" + this.g + ", calorieTarget=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
