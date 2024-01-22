package com.touchgui.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.touchgui.sdk.bean.TGDevice;
/* loaded from: classes12.dex */
public interface TGConnectionListener {
    void onConnectionStateChange(int i, String str);

    void onError(int i);

    default void onReady(@NonNull TGDevice tGDevice) {
        onReady(tGDevice.getName(), tGDevice.getAddress(), tGDevice.getVersionCode(), tGDevice.isForceOTA());
    }

    @Deprecated
    default void onReady(@Nullable String str, String str2, int i, boolean z) {
    }
}
