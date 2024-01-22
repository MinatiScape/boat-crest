package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
/* loaded from: classes6.dex */
public interface Freezable<T> {
    @NonNull
    T freeze();

    boolean isDataValid();
}
