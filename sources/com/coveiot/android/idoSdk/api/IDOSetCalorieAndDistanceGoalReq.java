package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.google.android.material.color.c;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDOSetCalorieAndDistanceGoalReq;", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Lcom/ido/ble/protocol/model/CalorieAndDistanceGoal;", c.f10260a, "Lcom/ido/ble/protocol/model/CalorieAndDistanceGoal;", "getCalorieAndDistanceGoal", "()Lcom/ido/ble/protocol/model/CalorieAndDistanceGoal;", "calorieAndDistanceGoal", "<init>", "(Lcom/ido/ble/protocol/model/CalorieAndDistanceGoal;)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOSetCalorieAndDistanceGoalReq extends IDOBaseReq {
    @NotNull
    public final CalorieAndDistanceGoal c;

    public IDOSetCalorieAndDistanceGoalReq(@NotNull CalorieAndDistanceGoal calorieAndDistanceGoal) {
        Intrinsics.checkNotNullParameter(calorieAndDistanceGoal, "calorieAndDistanceGoal");
        this.c = calorieAndDistanceGoal;
    }

    @NotNull
    public final CalorieAndDistanceGoal getCalorieAndDistanceGoal() {
        return this.c;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    @NotNull
    public IDOBleCommandName getCommandName() {
        return IDOBleCommandName.SET_CALORIE_DISTANCE_TARGET;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public int getTimeOut() {
        return 30000;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public boolean isPriority() {
        return true;
    }
}
