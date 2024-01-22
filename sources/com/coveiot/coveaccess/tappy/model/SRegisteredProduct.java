package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SRegisteredProduct implements Serializable {
    @SerializedName("EndUserID")
    @Expose
    private long endUserID;
    @SerializedName("EndUserProductRegistrationID")
    @Expose
    private long endUserProductRegistrationID;
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    @SerializedName("PaymentInstrumentTokens")
    @Expose
    private ArrayList<SPaymentInstrumentTokens> paymentInstrumentTokens;
    @SerializedName("Product")
    @Expose
    private SProduct product;
    @SerializedName("ProductSerialNumber")
    @Expose
    private String productSerialNumber;

    public long getEndUserID() {
        return this.endUserID;
    }

    public long getEndUserProductRegistrationID() {
        return this.endUserProductRegistrationID;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public ArrayList<SPaymentInstrumentTokens> getPaymentInstrumentTokens() {
        return this.paymentInstrumentTokens;
    }

    public SProduct getProduct() {
        return this.product;
    }

    public String getProductSerialNumber() {
        return this.productSerialNumber;
    }

    public void setEndUserID(long j) {
        this.endUserID = j;
    }

    public void setEndUserProductRegistrationID(long j) {
        this.endUserProductRegistrationID = j;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setPaymentInstrumentTokens(ArrayList<SPaymentInstrumentTokens> arrayList) {
        this.paymentInstrumentTokens = arrayList;
    }

    public void setProduct(SProduct sProduct) {
        this.product = sProduct;
    }

    public void setProductSerialNumber(String str) {
        this.productSerialNumber = str;
    }
}
