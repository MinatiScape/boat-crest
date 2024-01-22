package com.coveiot.coveaccess.tappy.model;

import com.google.common.net.HttpHeaders;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SValidateOTPRequest implements Serializable {
    @SerializedName(HttpHeaders.DATE)
    @Expose
    private long date;
    @SerializedName("DeviceID")
    @Expose
    private long deviceId;
    private long endUserId;
    @SerializedName("OTPValue")
    @Expose
    private String oTPValue;
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

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public String getoTPValue() {
        return this.oTPValue;
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

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }

    public void setoTPValue(String str) {
        this.oTPValue = str;
    }
}
