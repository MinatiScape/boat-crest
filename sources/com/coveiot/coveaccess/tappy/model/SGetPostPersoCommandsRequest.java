package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetPostPersoCommandsRequest implements Serializable {
    private long endUserId;
    private String initUpdateResponse;
    private long paymentInstrumentTokenId;

    public long getEndUserId() {
        return this.endUserId;
    }

    public String getInitUpdateResponse() {
        return this.initUpdateResponse;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setInitUpdateResponse(String str) {
        this.initUpdateResponse = str;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }
}
