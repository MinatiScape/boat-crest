package com.coveiot.android.eastapexsdk.error;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0010\u0010\u0011R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/coveiot/android/eastapexsdk/error/EastApexError;", "", "", Constants.KEY_MESSAGE, "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "Lcom/coveiot/android/eastapexsdk/error/EastApexErrorType;", "errorType", "Lcom/coveiot/android/eastapexsdk/error/EastApexErrorType;", "getErrorType", "()Lcom/coveiot/android/eastapexsdk/error/EastApexErrorType;", "setErrorType", "(Lcom/coveiot/android/eastapexsdk/error/EastApexErrorType;)V", "<init>", "(Lcom/coveiot/android/eastapexsdk/error/EastApexErrorType;Ljava/lang/String;)V", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public final class EastApexError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4365a;
    @Nullable
    public EastApexErrorType b;

    public EastApexError(@Nullable EastApexErrorType eastApexErrorType, @Nullable String str) {
        this.f4365a = str;
        this.b = eastApexErrorType;
    }

    @Nullable
    public final EastApexErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f4365a;
    }

    public final void setErrorType(@Nullable EastApexErrorType eastApexErrorType) {
        this.b = eastApexErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f4365a = str;
    }
}
