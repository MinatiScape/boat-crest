package com.coveiot.android.tappy.model;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class GetTransactionDetailsByIdResponse {
    private double amount;
    private boolean cannotBeGeocoded;
    @Nullable
    private String currency;
    @Nullable
    private String industryCategoryCode;
    @Nullable
    private String industryCategoryName;
    @Nullable
    private String industryCode;
    @Nullable
    private String industryName;
    @Nullable
    private String merchantCity;
    @Nullable
    private String merchantCountry;
    @Nullable
    private Double merchantLatitude;
    @Nullable
    private Double merchantLongitude;
    @Nullable
    private String merchantName;
    @Nullable
    private String merchantZipCode;
    private long transactionDate;
    private long transactionID;
    @Nullable
    private String transactionStatus;
    @Nullable
    private String transactionType;

    public final double getAmount() {
        return this.amount;
    }

    public final boolean getCannotBeGeocoded() {
        return this.cannotBeGeocoded;
    }

    @Nullable
    public final String getCurrency() {
        return this.currency;
    }

    @Nullable
    public final String getIndustryCategoryCode() {
        return this.industryCategoryCode;
    }

    @Nullable
    public final String getIndustryCategoryName() {
        return this.industryCategoryName;
    }

    @Nullable
    public final String getIndustryCode() {
        return this.industryCode;
    }

    @Nullable
    public final String getIndustryName() {
        return this.industryName;
    }

    @Nullable
    public final String getMerchantCity() {
        return this.merchantCity;
    }

    @Nullable
    public final String getMerchantCountry() {
        return this.merchantCountry;
    }

    @Nullable
    public final Double getMerchantLatitude() {
        return this.merchantLatitude;
    }

    @Nullable
    public final Double getMerchantLongitude() {
        return this.merchantLongitude;
    }

    @Nullable
    public final String getMerchantName() {
        return this.merchantName;
    }

    @Nullable
    public final String getMerchantZipCode() {
        return this.merchantZipCode;
    }

    public final long getTransactionDate() {
        return this.transactionDate;
    }

    public final long getTransactionID() {
        return this.transactionID;
    }

    @Nullable
    public final String getTransactionStatus() {
        return this.transactionStatus;
    }

    @Nullable
    public final String getTransactionType() {
        return this.transactionType;
    }

    public final void setAmount(double d) {
        this.amount = d;
    }

    public final void setCannotBeGeocoded(boolean z) {
        this.cannotBeGeocoded = z;
    }

    public final void setCurrency(@Nullable String str) {
        this.currency = str;
    }

    public final void setIndustryCategoryCode(@Nullable String str) {
        this.industryCategoryCode = str;
    }

    public final void setIndustryCategoryName(@Nullable String str) {
        this.industryCategoryName = str;
    }

    public final void setIndustryCode(@Nullable String str) {
        this.industryCode = str;
    }

    public final void setIndustryName(@Nullable String str) {
        this.industryName = str;
    }

    public final void setMerchantCity(@Nullable String str) {
        this.merchantCity = str;
    }

    public final void setMerchantCountry(@Nullable String str) {
        this.merchantCountry = str;
    }

    public final void setMerchantLatitude(@Nullable Double d) {
        this.merchantLatitude = d;
    }

    public final void setMerchantLongitude(@Nullable Double d) {
        this.merchantLongitude = d;
    }

    public final void setMerchantName(@Nullable String str) {
        this.merchantName = str;
    }

    public final void setMerchantZipCode(@Nullable String str) {
        this.merchantZipCode = str;
    }

    public final void setTransactionDate(long j) {
        this.transactionDate = j;
    }

    public final void setTransactionID(long j) {
        this.transactionID = j;
    }

    public final void setTransactionStatus(@Nullable String str) {
        this.transactionStatus = str;
    }

    public final void setTransactionType(@Nullable String str) {
        this.transactionType = str;
    }
}
