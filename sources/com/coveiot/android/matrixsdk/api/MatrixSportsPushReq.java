package com.coveiot.android.matrixsdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixSportsPushReq extends MatrixBaseReq {
    @NotNull
    public final String e;

    public MatrixSportsPushReq(@NotNull String sportPushFilePath) {
        Intrinsics.checkNotNullParameter(sportPushFilePath, "sportPushFilePath");
        this.e = sportPushFilePath;
    }

    @NotNull
    public final String getSportPushFilePath() {
        return this.e;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return false;
    }
}
