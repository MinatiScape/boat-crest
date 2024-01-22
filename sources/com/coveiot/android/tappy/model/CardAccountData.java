package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CardAccountData implements Serializable {
    @Nullable
    private String accountNumber;
    @Nullable
    private String expiryMonth;
    @Nullable
    private String expiryYear;

    @Nullable
    public final String getAccountNumber() {
        return this.accountNumber;
    }

    @Nullable
    public final String getExpiryMonth() {
        return this.expiryMonth;
    }

    @Nullable
    public final String getExpiryYear() {
        return this.expiryYear;
    }

    public final void setAccountNumber(@Nullable String str) {
        this.accountNumber = str;
    }

    public final void setExpiryMonth(@Nullable String str) {
        this.expiryMonth = str;
    }

    public final void setExpiryYear(@Nullable String str) {
        this.expiryYear = str;
    }
}
