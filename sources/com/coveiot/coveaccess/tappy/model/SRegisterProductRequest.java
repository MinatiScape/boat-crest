package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SRegisterProductRequest implements Serializable {
    private long endUserId;
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    @SerializedName("ProductID")
    @Expose
    private long productID;
    @SerializedName("ProductSerialNumber")
    @Expose
    private String productSerialNumber;

    public long getEndUserId() {
        return this.endUserId;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public long getProductID() {
        return this.productID;
    }

    public String getProductSerialNumber() {
        return this.productSerialNumber;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setProductID(long j) {
        this.productID = j;
    }

    public void setProductSerialNumber(String str) {
        this.productSerialNumber = str;
    }
}
