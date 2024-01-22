package com.coveiot.android.matrixsdk.error;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class MatrixError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f5469a;
    @Nullable
    public MatrixErrorType b;

    public MatrixError(@Nullable MatrixErrorType matrixErrorType, @Nullable String str) {
        this.f5469a = str;
        this.b = matrixErrorType;
    }

    @Nullable
    public final MatrixErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f5469a;
    }

    public final void setErrorType(@Nullable MatrixErrorType matrixErrorType) {
        this.b = matrixErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f5469a = str;
    }

    @NotNull
    public String toString() {
        return "CRPError(message=" + this.f5469a + ", errorType=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
