package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public interface OnResponseCallback<T> {
    void onError(int i, String str);

    void onSuccess(T t);
}
