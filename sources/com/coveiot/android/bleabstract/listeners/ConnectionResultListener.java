package com.coveiot.android.bleabstract.listeners;

import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface ConnectionResultListener extends BaseListener {
    void onConnectionResponse(@NotNull ConnectionStatus connectionStatus);

    void onError(@NotNull Error error);
}
