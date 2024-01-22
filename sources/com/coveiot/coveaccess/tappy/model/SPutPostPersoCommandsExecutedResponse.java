package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SPutPostPersoCommandsExecutedResponse implements Serializable {
    @SerializedName("ExpiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    @SerializedName("Last4")
    @Expose
    private String last4;
    @SerializedName("PaymentInstrument")
    @Expose
    private SPaymentInstrument paymentInstrument;
    @SerializedName("PaymentInstrumentTokenID")
    @Expose
    private long paymentInstrumentTokenID;
    @SerializedName("ProvisioningStatus")
    @Expose
    private String provisioningStatus;
    @SerializedName("RegisteredProduct")
    @Expose
    private SRegisteredProduct registeredProduct;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public String getLast4() {
        return this.last4;
    }

    public SPaymentInstrument getPaymentInstrument() {
        return this.paymentInstrument;
    }

    public long getPaymentInstrumentTokenID() {
        return this.paymentInstrumentTokenID;
    }

    public String getProvisioningStatus() {
        return this.provisioningStatus;
    }

    public SRegisteredProduct getRegisteredProduct() {
        return this.registeredProduct;
    }

    public String getStatus() {
        return this.status;
    }

    public void setExpiryDate(String str) {
        this.expiryDate = str;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setLast4(String str) {
        this.last4 = str;
    }

    public void setPaymentInstrument(SPaymentInstrument sPaymentInstrument) {
        this.paymentInstrument = sPaymentInstrument;
    }

    public void setPaymentInstrumentTokenID(long j) {
        this.paymentInstrumentTokenID = j;
    }

    public void setProvisioningStatus(String str) {
        this.provisioningStatus = str;
    }

    public void setRegisteredProduct(SRegisteredProduct sRegisteredProduct) {
        this.registeredProduct = sRegisteredProduct;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
