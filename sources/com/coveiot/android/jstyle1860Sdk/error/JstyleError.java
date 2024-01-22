package com.coveiot.android.jstyle1860Sdk.error;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0016R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/error/JstyleError;", "", "", "toString", Constants.KEY_MESSAGE, "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "Lcom/coveiot/android/jstyle1860Sdk/error/JstyleErrorType;", "errorType", "Lcom/coveiot/android/jstyle1860Sdk/error/JstyleErrorType;", "getErrorType", "()Lcom/coveiot/android/jstyle1860Sdk/error/JstyleErrorType;", "setErrorType", "(Lcom/coveiot/android/jstyle1860Sdk/error/JstyleErrorType;)V", "<init>", "(Lcom/coveiot/android/jstyle1860Sdk/error/JstyleErrorType;Ljava/lang/String;)V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleError {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4605a;
    @Nullable
    public JstyleErrorType b;

    public JstyleError(@Nullable JstyleErrorType jstyleErrorType, @Nullable String str) {
        this.f4605a = str;
        this.b = jstyleErrorType;
    }

    @Nullable
    public final JstyleErrorType getErrorType() {
        return this.b;
    }

    @Nullable
    public final String getMessage() {
        return this.f4605a;
    }

    public final void setErrorType(@Nullable JstyleErrorType jstyleErrorType) {
        this.b = jstyleErrorType;
    }

    public final void setMessage(@Nullable String str) {
        this.f4605a = str;
    }

    @NotNull
    public String toString() {
        return "JstyleError(message=" + ((Object) this.f4605a) + ", errorType=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
