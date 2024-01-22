package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitFare {
    @SerializedName("rupees")
    private Integer amount;
    @SerializedName(FirebaseAnalytics.Param.CURRENCY)
    private TransitCurrency currency;

    public Integer getAmount() {
        return this.amount;
    }

    public TransitCurrency getCurrency() {
        return this.currency;
    }

    public void setAmount(Integer num) {
        this.amount = num;
    }

    public void setCurrency(TransitCurrency transitCurrency) {
        this.currency = transitCurrency;
    }
}
