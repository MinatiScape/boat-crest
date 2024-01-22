package com.touchgui.sdk;

import androidx.annotation.NonNull;
import com.touchgui.sdk.bean.TGDevice;
/* loaded from: classes12.dex */
public interface TGConnectCallback {
    void onConnectStateChange(String str, int i);

    void onError(String str, int i);

    void onReady(@NonNull TGDevice tGDevice);
}
