package com.coveiot.android.crpsdk.api;

import com.crrepa.ble.conn.bean.CRPUserInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPSetUserProfileReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "Lcom/crrepa/ble/conn/bean/CRPUserInfo;", "userProfile", "Lcom/crrepa/ble/conn/bean/CRPUserInfo;", "getUserProfile", "()Lcom/crrepa/ble/conn/bean/CRPUserInfo;", "setUserProfile", "(Lcom/crrepa/ble/conn/bean/CRPUserInfo;)V", "", "stepLength", "B", "getStepLength", "()B", "setStepLength", "(B)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPSetUserProfileReq extends CRPBaseReq {
    @Nullable
    public CRPUserInfo c;
    public byte d = -1;

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPSetUserProfileReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    public final byte getStepLength() {
        return this.d;
    }

    @Nullable
    public final CRPUserInfo getUserProfile() {
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

    public final void setStepLength(byte b) {
        this.d = b;
    }

    public final void setUserProfile(@Nullable CRPUserInfo cRPUserInfo) {
        this.c = cRPUserInfo;
    }
}
