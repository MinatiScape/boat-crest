package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SInstallFoundationToSecureElementRequest implements Serializable {
    @SerializedName("CommandSetIndex")
    @Expose
    private int commandSetIndex;
    @SerializedName("EndUserID")
    @Expose
    private long endUserID;
    @SerializedName("EndUserProductRegistrationId")
    @Expose
    private long endUserProductRegistrationId;
    @SerializedName("InitUpdateResponse")
    @Expose
    private String initUpdateResponse;
    @SerializedName("PaymentNetworkID")
    @Expose
    private int paymentNetworkID;

    public int getCommandSetIndex() {
        return this.commandSetIndex;
    }

    public long getEndUserID() {
        return this.endUserID;
    }

    public long getEndUserProductRegistrationId() {
        return this.endUserProductRegistrationId;
    }

    public String getInitUpdateResponse() {
        return this.initUpdateResponse;
    }

    public int getPaymentNetworkID() {
        return this.paymentNetworkID;
    }

    public void setCommandSetIndex(int i) {
        this.commandSetIndex = i;
    }

    public void setEndUserID(long j) {
        this.endUserID = j;
    }

    public void setEndUserProductRegistrationId(long j) {
        this.endUserProductRegistrationId = j;
    }

    public void setInitUpdateResponse(String str) {
        this.initUpdateResponse = str;
    }

    public void setPaymentNetworkID(int i) {
        this.paymentNetworkID = i;
    }
}
