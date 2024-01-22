package com.coveiot.android.crpsdk.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPGetSessionHRDataReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPGetSessionHRDataReq extends CRPBaseReq {
    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPGetSessionHRDataReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isPriority() {
        return false;
    }
}
