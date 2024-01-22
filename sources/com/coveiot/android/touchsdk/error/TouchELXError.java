package com.coveiot.android.touchsdk.error;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TouchELXError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f6108a;
    @Nullable
    public TouchELXErrorType b;

    public TouchELXError(@Nullable TouchELXErrorType touchELXErrorType, @Nullable String str) {
        this.f6108a = str;
        this.b = touchELXErrorType;
    }

    @Nullable
    public final TouchELXErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f6108a;
    }

    public final void setErrorType(@Nullable TouchELXErrorType touchELXErrorType) {
        this.b = touchELXErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f6108a = str;
    }

    @NotNull
    public String toString() {
        return "TouchError(message=" + this.f6108a + ", errorType=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
