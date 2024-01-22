package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SResumePaymentInstrumentTokensRequest implements Serializable {
    private transient long endUserId;
    private transient long paymentInstrumentTokenId;
    @SerializedName("Reason")
    @Expose
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
