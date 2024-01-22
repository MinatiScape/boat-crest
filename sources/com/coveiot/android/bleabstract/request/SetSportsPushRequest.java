package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetSportsPushRequest extends BleBaseRequest {
    @NotNull
    public final String f;

    public SetSportsPushRequest(@NotNull String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.f = filePath;
    }

    @NotNull
    public final String getFilePath() {
        return this.f;
    }
}
