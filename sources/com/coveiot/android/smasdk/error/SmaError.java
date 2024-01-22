package com.coveiot.android.smasdk.error;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f5766a;
    @Nullable
    public SmaErrorType b;

    public SmaError(@Nullable SmaErrorType smaErrorType, @Nullable String str) {
        this.f5766a = str;
        this.b = smaErrorType;
    }

    @Nullable
    public final SmaErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f5766a;
    }

    public final void setErrorType(@Nullable SmaErrorType smaErrorType) {
        this.b = smaErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f5766a = str;
    }

    @NotNull
    public String toString() {
        return "SmaError(message=" + this.f5766a + ", errorType=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
