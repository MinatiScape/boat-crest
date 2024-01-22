package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.WristbandContacts;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixContactReq extends MatrixBaseReq {
    @NotNull
    public final List<WristbandContacts> e;

    /* JADX WARN: Multi-variable type inference failed */
    public MatrixContactReq(@NotNull List<? extends WristbandContacts> wContacts) {
        Intrinsics.checkNotNullParameter(wContacts, "wContacts");
        this.e = wContacts;
    }

    @NotNull
    public final List<WristbandContacts> getWContacts() {
        return this.e;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }
}
