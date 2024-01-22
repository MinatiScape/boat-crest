package com.coveiot.android.leonardo.listener;

import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface DeviceRemoteConfigFetchListener {
    void onFailure(@Nullable String str);

    void onSuccess(@Nullable DeviceRemoteConfig deviceRemoteConfig);
}
