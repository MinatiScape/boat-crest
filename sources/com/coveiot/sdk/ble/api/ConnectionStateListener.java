package com.coveiot.sdk.ble.api;

import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.error.Error;
/* loaded from: classes9.dex */
public interface ConnectionStateListener {
    void onConnectionStateChanged(CloveBleState.BleState bleState);

    void onFailure(Error error);
}
