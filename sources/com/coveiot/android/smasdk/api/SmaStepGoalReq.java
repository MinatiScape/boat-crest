package com.coveiot.android.smasdk.api;
/* loaded from: classes6.dex */
public final class SmaStepGoalReq extends SmaBaseReq {
    public int d;

    public final int getGoal() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setGoal(int i) {
        this.d = i;
    }
}
