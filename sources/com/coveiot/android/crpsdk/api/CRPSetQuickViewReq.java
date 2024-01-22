package com.coveiot.android.crpsdk.api;

import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\"\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPSetQuickViewReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "isOn", "Z", "()Z", "setOn", "(Z)V", "Lcom/crrepa/ble/conn/bean/CRPPeriodTimeInfo;", "cRPPeriodTimeInfo", "Lcom/crrepa/ble/conn/bean/CRPPeriodTimeInfo;", "getCRPPeriodTimeInfo", "()Lcom/crrepa/ble/conn/bean/CRPPeriodTimeInfo;", "setCRPPeriodTimeInfo", "(Lcom/crrepa/ble/conn/bean/CRPPeriodTimeInfo;)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPSetQuickViewReq extends CRPBaseReq {
    public boolean c;
    @Nullable
    public CRPPeriodTimeInfo d;

    @Nullable
    public final CRPPeriodTimeInfo getCRPPeriodTimeInfo() {
        return this.d;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPSetQuickViewReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    public final boolean isOn() {
        return this.c;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setCRPPeriodTimeInfo(@Nullable CRPPeriodTimeInfo cRPPeriodTimeInfo) {
        this.d = cRPPeriodTimeInfo;
    }

    public final void setOn(boolean z) {
        this.c = z;
    }
}
