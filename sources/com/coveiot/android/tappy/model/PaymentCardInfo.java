package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PaymentCardInfo implements Serializable {
    @Nullable
    private String accountNumber;
    @Nullable
    private String cvv2;
    @Nullable
    private ExpiryDate expirationDate;

    @Nullable
    public final String getAccountNumber() {
        return this.accountNumber;
    }

    @Nullable
    public final String getCvv2() {
        return this.cvv2;
    }

    @Nullable
    public final ExpiryDate getExpirationDate() {
        return this.expirationDate;
    }

    public final void setAccountNumber(@Nullable String str) {
        this.accountNumber = str;
    }

    public final void setCvv2(@Nullable String str) {
        this.cvv2 = str;
    }

    public final void setExpirationDate(@Nullable ExpiryDate expiryDate) {
        this.expirationDate = expiryDate;
    }
}
