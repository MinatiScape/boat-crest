package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.leanplum.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitCurrency {
    @SerializedName(FirebaseAnalytics.Param.CURRENCY)
    private String currency;
    @SerializedName(Constants.CURRENCY_CODE_PARAM)
    private String currencyCode;
    @SerializedName("defaultFractionDigits")
    private Integer defaultFractionDigits;
    @SerializedName("symbol")
    private String symbol;

    public String getCurrency() {
        return this.currency;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public Integer getDefaultFractionDigits() {
        return this.defaultFractionDigits;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public void setDefaultFractionDigits(Integer num) {
        this.defaultFractionDigits = num;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }
}
