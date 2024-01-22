package com.coveiot.android.bleabstract.listeners;

import com.coveiot.android.bleabstract.response.BleBaseResponse;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface ScanResultListener extends BaseListener {
    void onError(@NotNull String str);

    void onResponse(@NotNull BleBaseResponse bleBaseResponse);
}
