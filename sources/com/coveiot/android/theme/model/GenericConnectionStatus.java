package com.coveiot.android.theme.model;

import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class GenericConnectionStatus {
    @NotNull
    private final ConnectionStatus status;

    public GenericConnectionStatus(@NotNull ConnectionStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
    }

    @NotNull
    public final ConnectionStatus getStatus() {
        return this.status;
    }
}
