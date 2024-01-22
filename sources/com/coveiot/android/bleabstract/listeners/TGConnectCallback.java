package com.coveiot.android.bleabstract.listeners;

import com.touchgui.sdk.bean.TGDevice;
/* loaded from: classes2.dex */
public interface TGConnectCallback {
    void onConnectionState(String str, int i);

    void onReady(TGDevice tGDevice);
}
