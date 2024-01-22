package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SDeletePaymentInstrumentTokensRequest implements Serializable {
    private transient long endUserId;
    private transient long paymentInstrumentTokenId;
    private String reason;

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public String getReason() {
        return this.reason;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }

    public void setReason(String str) {
        this.reason = str;
    }
}
