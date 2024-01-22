package com.coveiot.android.idoSdk.error;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\b\u0010\u0003\u001a\u00020\u0002H\u0016R$\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u0012\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/coveiot/android/idoSdk/error/IDOError;", "", "", "toString", "a", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", Constants.KEY_MESSAGE, "Lcom/coveiot/android/idoSdk/error/IDOErrorType;", "b", "Lcom/coveiot/android/idoSdk/error/IDOErrorType;", "getErrorType", "()Lcom/coveiot/android/idoSdk/error/IDOErrorType;", "setErrorType", "(Lcom/coveiot/android/idoSdk/error/IDOErrorType;)V", "errorType", "<init>", "(Lcom/coveiot/android/idoSdk/error/IDOErrorType;Ljava/lang/String;)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4603a;
    @Nullable
    public IDOErrorType b;

    public IDOError(@Nullable IDOErrorType iDOErrorType, @Nullable String str) {
        this.f4603a = str;
        this.b = iDOErrorType;
    }

    @Nullable
    public final IDOErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f4603a;
    }

    public final void setErrorType(@Nullable IDOErrorType iDOErrorType) {
        this.b = iDOErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f4603a = str;
    }

    @NotNull
    public String toString() {
        return "IDOError(message=" + ((Object) this.f4603a) + ", errorType=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}