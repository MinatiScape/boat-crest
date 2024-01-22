package com.coveiot.android.eastapexsdk.api;

import com.apex.bluetooth.model.EABleGesturesBrightScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\u0019\u0010\b\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/coveiot/android/eastapexsdk/api/EastApexSetLiftWristReq;", "Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "Lcom/apex/bluetooth/model/EABleGesturesBrightScreen;", "input", "Lcom/apex/bluetooth/model/EABleGesturesBrightScreen;", "getInput", "()Lcom/apex/bluetooth/model/EABleGesturesBrightScreen;", "<init>", "(Lcom/apex/bluetooth/model/EABleGesturesBrightScreen;)V", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public final class EastApexSetLiftWristReq extends EastApexBaseReq {
    @NotNull
    public final EABleGesturesBrightScreen c;

    public EastApexSetLiftWristReq(@NotNull EABleGesturesBrightScreen input) {
        Intrinsics.checkNotNullParameter(input, "input");
        this.c = input;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = EastApexSetLiftWristReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @NotNull
    public final EABleGesturesBrightScreen getInput() {
        return this.c;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    public boolean isPriority() {
        return true;
    }
}
