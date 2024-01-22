package com.coveiot.coveaccess.tappy.model;

import com.google.common.net.HttpHeaders;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SPutStepUpOptionsRequest implements Serializable {
    @SerializedName(HttpHeaders.DATE)
    @Expose
    private long date;
    @SerializedName("DeviceID")
    @Expose
    private long deviceId;
    private long endUserId;
    @SerializedName("ID")
    @Expose
    private String id;
    private long paymentInstrumentTokenId;

    public long getDate() {
        return this.date;
    }

    public long getDeviceId() {
        return this.deviceId;
    }

    public long getEndUserId() {
        return this.endUserId;
    }

    public String getId() {
        return this.id;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public void setDeviceId(long j) {
        this.deviceId = j;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }
}
