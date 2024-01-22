package com.coveiot.android.bleabstract.listeners;

import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface SettingsResultListener extends BaseListener {
    void onSettingsError(@NotNull BleBaseError bleBaseError);

    void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse);
}
