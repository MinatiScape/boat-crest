package com.coveiot.android.leonardo.bt3call.listener;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface IBT3ConnectionChangeListener {
    void onBT3Connecting();

    void onBT3ConnectionFailure(@Nullable String str);

    void onBT3Disconnected();

    void onInitialCheckFailed(@Nullable String str);
}
