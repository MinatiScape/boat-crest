package com.mappls.sdk.maps.location.engine;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface LocationEngineCallback<T> {
    void onFailure(@NonNull Exception exc);

    void onSuccess(T t);
}
