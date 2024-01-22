package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetTransactionDetailsByTransactionIdRequest implements Serializable {
    private transient long endUserId;
    private transient long paymentInstrumentTokenId;
    private transient long transactionId;

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public long getTransactionId() {
        return this.transactionId;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }

    public void setTransactionId(long j) {
        this.transactionId = j;
    }
}
