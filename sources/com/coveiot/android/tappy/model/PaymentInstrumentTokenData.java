package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PaymentInstrumentTokenData implements Serializable {
    @Nullable
    private String expiryDate;
    @Nullable
    private String friendlyName;
    @Nullable
    private String last4;
    @Nullable
    private PaymentInstrumentData paymentInstrument;
    @Nullable
    private Long paymentInstrumentTokenID;
    @Nullable
    private String provisioningStatus;
    @Nullable
    private String status;

    @Nullable
    public final String getExpiryDate() {
        return this.expiryDate;
    }

    @Nullable
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    @Nullable
    public final String getLast4() {
        return this.last4;
    }

    @Nullable
    public final PaymentInstrumentData getPaymentInstrument() {
        return this.paymentInstrument;
    }

    @Nullable
    public final Long getPaymentInstrumentTokenID() {
        return this.paymentInstrumentTokenID;
    }

    @Nullable
    public final String getProvisioningStatus() {
        return this.provisioningStatus;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final void setExpiryDate(@Nullable String str) {
        this.expiryDate = str;
    }

    public final void setFriendlyName(@Nullable String str) {
        this.friendlyName = str;
    }

    public final void setLast4(@Nullable String str) {
        this.last4 = str;
    }

    public final void setPaymentInstrument(@Nullable PaymentInstrumentData paymentInstrumentData) {
        this.paymentInstrument = paymentInstrumentData;
    }

    public final void setPaymentInstrumentTokenID(@Nullable Long l) {
        this.paymentInstrumentTokenID = l;
    }

    public final void setProvisioningStatus(@Nullable String str) {
        this.provisioningStatus = str;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }
}
