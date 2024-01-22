package com.coveiot.android.crpsdk.error;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0016R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/crpsdk/error/CRPError;", "", "", "toString", Constants.KEY_MESSAGE, "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "Lcom/coveiot/android/crpsdk/error/CRPErrorType;", "errorType", "Lcom/coveiot/android/crpsdk/error/CRPErrorType;", "getErrorType", "()Lcom/coveiot/android/crpsdk/error/CRPErrorType;", "setErrorType", "(Lcom/coveiot/android/crpsdk/error/CRPErrorType;)V", "<init>", "(Lcom/coveiot/android/crpsdk/error/CRPErrorType;Ljava/lang/String;)V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4115a;
    @Nullable
    public CRPErrorType b;

    public CRPError(@Nullable CRPErrorType cRPErrorType, @Nullable String str) {
        this.f4115a = str;
        this.b = cRPErrorType;
    }

    @Nullable
    public final CRPErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f4115a;
    }

    public final void setErrorType(@Nullable CRPErrorType cRPErrorType) {
        this.b = cRPErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f4115a = str;
    }

    @NotNull
    public String toString() {
        return "CRPError(message=" + ((Object) this.f4115a) + ", errorType=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
