package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.models.BleCommand;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BleBaseError {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3581a;
    @Nullable
    public BleCommand b;
    @Nullable
    public Integer c;

    public BleBaseError(@NotNull String errorMsg, @Nullable BleCommand bleCommand) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        this.f3581a = errorMsg;
        this.b = bleCommand;
        this.c = Integer.valueOf(CommandError.COMMAND_FAILED.value);
    }

    @Nullable
    public final BleCommand getBleCommands() {
        return this.b;
    }

    @Nullable
    public final Integer getErrorCode() {
        return this.c;
    }

    @NotNull
    public final String getErrorMsg() {
        return this.f3581a;
    }

    public final void setBleCommands(@Nullable BleCommand bleCommand) {
        this.b = bleCommand;
    }

    public final void setErrorCode(@Nullable Integer num) {
        this.c = num;
    }

    public final void setErrorMsg(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f3581a = str;
    }

    public BleBaseError(@NotNull String errorMsg) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        this.f3581a = errorMsg;
        this.c = Integer.valueOf(CommandError.COMMAND_FAILED.value);
    }
}
