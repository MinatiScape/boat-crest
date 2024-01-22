package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchSetGoalReq extends TouchELXBaseReq {
    public int e;
    public int f;

    public TouchSetGoalReq(int i, int i2) {
        this.e = i;
        this.f = i2;
    }

    public final int getGoal() {
        return this.e;
    }

    public final int getGoalType() {
        return this.f;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setGoal(int i) {
        this.e = i;
    }

    public final void setGoalType(int i) {
        this.f = i;
    }
}
