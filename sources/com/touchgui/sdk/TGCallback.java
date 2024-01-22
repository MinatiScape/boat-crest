package com.touchgui.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface TGCallback<T> {
    void onFailure(@NonNull Throwable th);

    void onSuccess(@Nullable T t);
}
