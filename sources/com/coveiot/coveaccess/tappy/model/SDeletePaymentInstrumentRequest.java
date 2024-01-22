package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SDeletePaymentInstrumentRequest implements Serializable {
    private boolean deleteTokens = true;
    private transient long endUserId;
    private transient long paymentInstrumentId;

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getPaymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    public boolean isDeleteTokens() {
        return this.deleteTokens;
    }

    public void setDeleteTokens(boolean z) {
        this.deleteTokens = z;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setPaymentInstrumentId(long j) {
        this.paymentInstrumentId = j;
    }
}
