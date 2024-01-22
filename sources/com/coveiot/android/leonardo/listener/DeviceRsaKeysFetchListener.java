package com.coveiot.android.leonardo.listener;

import com.coveiot.android.devicemodels.RSAKeysRemoteConfig;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface DeviceRsaKeysFetchListener {
    void onFailure(@Nullable String str);

    void onSuccess(@Nullable RSAKeysRemoteConfig rSAKeysRemoteConfig);
}
