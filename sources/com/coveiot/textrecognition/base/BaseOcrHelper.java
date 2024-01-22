package com.coveiot.textrecognition.base;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public abstract class BaseOcrHelper {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ResponseCallback<BaseOcrResponse> f7602a;

    public abstract void detectText(@NotNull String str);

    @Nullable
    public final ResponseCallback<BaseOcrResponse> getOcrResponseCallback() {
        return this.f7602a;
    }

    public final void setOcrResponseCallback(@Nullable ResponseCallback<BaseOcrResponse> responseCallback) {
        this.f7602a = responseCallback;
    }

    public final void setResponseCallback(@NotNull ResponseCallback<BaseOcrResponse> ocrResponseCallback) {
        Intrinsics.checkNotNullParameter(ocrResponseCallback, "ocrResponseCallback");
        this.f7602a = ocrResponseCallback;
    }
}
