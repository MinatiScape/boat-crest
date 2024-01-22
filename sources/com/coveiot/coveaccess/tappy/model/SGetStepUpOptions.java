package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetStepUpOptions implements Serializable {
    private long deviceId;
    private long endUserId;
    private long paymentInstrumentTokenId;

    public long getDeviceId() {
        return this.deviceId;
    }

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public void setDeviceId(long j) {
        this.deviceId = j;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }
}
