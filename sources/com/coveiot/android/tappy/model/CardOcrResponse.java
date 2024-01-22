package com.coveiot.android.tappy.model;

import com.coveiot.textrecognition.base.BaseOcrResponse;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CardOcrResponse extends BaseOcrResponse {
    @Nullable
    private String cardHolderName;
    @Nullable
    private String cardNumber;
    @Nullable
    private String cvv;
    @Nullable
    private String expiryMonth;
    @Nullable
    private String expiryYear;

    @Nullable
    public final String getCardHolderName() {
        return this.cardHolderName;
    }

    @Nullable
    public final String getCardNumber() {
        return this.cardNumber;
    }

    @Nullable
    public final String getCvv() {
        return this.cvv;
    }

    @Nullable
    public final String getExpiryMonth() {
        return this.expiryMonth;
    }

    @Nullable
    public final String getExpiryYear() {
        return this.expiryYear;
    }

    public final void setCardHolderName(@Nullable String str) {
        this.cardHolderName = str;
    }

    public final void setCardNumber(@Nullable String str) {
        this.cardNumber = str;
    }

    public final void setCvv(@Nullable String str) {
        this.cvv = str;
    }

    public final void setExpiryMonth(@Nullable String str) {
        this.expiryMonth = str;
    }

    public final void setExpiryYear(@Nullable String str) {
        this.expiryYear = str;
    }
}
