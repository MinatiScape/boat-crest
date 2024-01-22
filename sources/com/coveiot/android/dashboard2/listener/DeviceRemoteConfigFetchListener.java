package com.coveiot.android.dashboard2.listener;

import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public interface DeviceRemoteConfigFetchListener {
    void onFailure(@Nullable String str);

    void onSuccess(@Nullable DeviceRemoteConfig deviceRemoteConfig);
}
