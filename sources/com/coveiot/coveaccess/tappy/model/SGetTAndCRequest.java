package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetTAndCRequest implements Serializable {
    private transient long endUserId;
    private transient long paymentInstrumentTokenId;

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }
}
