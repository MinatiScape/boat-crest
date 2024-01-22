package com.coveiot.android.crpsdk.api;

import com.crrepa.ble.conn.bean.CRPCustomizeWatchFaceInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPSendWatchFaceReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "Lcom/crrepa/ble/conn/bean/CRPCustomizeWatchFaceInfo;", "crpCustomizeWatchFaceInfo", "Lcom/crrepa/ble/conn/bean/CRPCustomizeWatchFaceInfo;", "getCrpCustomizeWatchFaceInfo", "()Lcom/crrepa/ble/conn/bean/CRPCustomizeWatchFaceInfo;", "setCrpCustomizeWatchFaceInfo", "(Lcom/crrepa/ble/conn/bean/CRPCustomizeWatchFaceInfo;)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPSendWatchFaceReq extends CRPBaseReq {
    @Nullable
    public CRPCustomizeWatchFaceInfo c;

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPSendWatchFaceReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @Nullable
    public final CRPCustomizeWatchFaceInfo getCrpCustomizeWatchFaceInfo() {
        return this.c;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setCrpCustomizeWatchFaceInfo(@Nullable CRPCustomizeWatchFaceInfo cRPCustomizeWatchFaceInfo) {
        this.c = cRPCustomizeWatchFaceInfo;
    }
}