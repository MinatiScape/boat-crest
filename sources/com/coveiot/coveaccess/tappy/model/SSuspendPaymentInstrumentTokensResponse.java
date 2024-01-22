package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SSuspendPaymentInstrumentTokensResponse implements Serializable {
    @SerializedName("RegisteredProduct")
    @Expose
    private SRegisteredProduct registeredProduct;

    public SRegisteredProduct getRegisteredProduct() {
        return this.registeredProduct;
    }

    public void setRegisteredProduct(SRegisteredProduct sRegisteredProduct) {
        this.registeredProduct = sRegisteredProduct;
    }
}
