package com.coveiot.android.leonardo.more.listeners;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface GenericBandAppCoveApiListener {
    void onFailure(@NotNull String str);

    <T> void onSuccess(T t);
}
