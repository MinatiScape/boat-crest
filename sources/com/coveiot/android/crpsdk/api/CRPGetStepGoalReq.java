package com.coveiot.android.crpsdk.api;

import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPGetStepGoalReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "", DeviceKey.Goal, "I", "getGoal", "()I", "setGoal", "(I)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPGetStepGoalReq extends CRPBaseReq {
    public int c;

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPGetStepGoalReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    public final int getGoal() {
        return this.c;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setGoal(int i) {
        this.c = i;
    }
}
