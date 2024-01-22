package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PaymentInstrumentTokenResponseData implements Serializable {
    @Nullable
    private PaymentInstrumentTokenData paymentInstrumentTokenData;
    @Nullable
    private String termsAndConditionsFileUrl;
    @Nullable
    private String termsAndConditionsID;

    @Nullable
    public final PaymentInstrumentTokenData getPaymentInstrumentTokenData() {
        return this.paymentInstrumentTokenData;
    }

    @Nullable
    public final String getTermsAndConditionsFileUrl() {
        return this.termsAndConditionsFileUrl;
    }

    @Nullable
    public final String getTermsAndConditionsID() {
        return this.termsAndConditionsID;
    }

    public final void setPaymentInstrumentTokenData(@Nullable PaymentInstrumentTokenData paymentInstrumentTokenData) {
        this.paymentInstrumentTokenData = paymentInstrumentTokenData;
    }

    public final void setTermsAndConditionsFileUrl(@Nullable String str) {
        this.termsAndConditionsFileUrl = str;
    }

    public final void setTermsAndConditionsID(@Nullable String str) {
        this.termsAndConditionsID = str;
    }
}
