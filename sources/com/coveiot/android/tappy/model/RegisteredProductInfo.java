package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class RegisteredProductInfo implements Serializable {
    @Nullable
    private Long endUserID;
    @Nullable
    private Long endUserProductRegistrationID;
    @Nullable
    private String friendlyName;
    @Nullable
    private List<PaymentInstrumentTokenData> paymentInstrumentTokens;
    @Nullable
    private ProductDetails product;
    @Nullable
    private String productSerialNumber;

    @Nullable
    public final Long getEndUserID() {
        return this.endUserID;
    }

    @Nullable
    public final Long getEndUserProductRegistrationID() {
        return this.endUserProductRegistrationID;
    }

    @Nullable
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    @Nullable
    public final List<PaymentInstrumentTokenData> getPaymentInstrumentTokens() {
        return this.paymentInstrumentTokens;
    }

    @Nullable
    public final ProductDetails getProduct() {
        return this.product;
    }

    @Nullable
    public final String getProductSerialNumber() {
        return this.productSerialNumber;
    }

    public final void setEndUserID(@Nullable Long l) {
        this.endUserID = l;
    }

    public final void setEndUserProductRegistrationID(@Nullable Long l) {
        this.endUserProductRegistrationID = l;
    }

    public final void setFriendlyName(@Nullable String str) {
        this.friendlyName = str;
    }

    public final void setPaymentInstrumentTokens(@Nullable List<PaymentInstrumentTokenData> list) {
        this.paymentInstrumentTokens = list;
    }

    public final void setProduct(@Nullable ProductDetails productDetails) {
        this.product = productDetails;
    }

    public final void setProductSerialNumber(@Nullable String str) {
        this.productSerialNumber = str;
    }
}
