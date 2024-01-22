package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class PairingFlowRequest extends BleBaseRequest {
    public int f;

    public PairingFlowRequest(int i) {
        this.f = i;
    }

    public int getFlowStatus() {
        return this.f;
    }
}
