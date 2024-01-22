package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetTransactionDetailsByTransactionIdResponse implements Serializable {
    @SerializedName("Amount")
    @Expose
    private double amount;
    @SerializedName("CannotBeGeocoded")
    @Expose
    private boolean cannotBeGeocoded;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("IndustryCategoryCode")
    @Expose
    private String industryCategoryCode;
    @SerializedName("IndustryCategoryName")
    @Expose
    private String industryCategoryName;
    @SerializedName("IndustryCode")
    @Expose
    private String industryCode;
    @SerializedName("IndustryName")
    @Expose
    private String industryName;
    @SerializedName("MerchantCity")
    @Expose
    private String merchantCity;
    @SerializedName("MerchantCountry")
    @Expose
    private String merchantCountry;
    @SerializedName("MerchantLatitude")
    @Expose
    private double merchantLatitude;
    @SerializedName("MerchantLongitude")
    @Expose
    private double merchantLongitude;
    @SerializedName("MerchantName")
    @Expose
    private String merchantName;
    @SerializedName("MerchantZipCode")
    @Expose
    private String merchantZipCode;
    @SerializedName("TransactionDate")
    @Expose
    private long transactionDate;
    @SerializedName("TransactionID")
    @Expose
    private long transactionID;
    @SerializedName("TransactionStatus")
    @Expose
    private String transactionStatus;
    @SerializedName("TransactionType")
    private String transactionType;

    public double getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getIndustryCategoryCode() {
        return this.industryCategoryCode;
    }

    public String getIndustryCategoryName() {
        return this.industryCategoryName;
    }

    public String getIndustryCode() {
        return this.industryCode;
    }

    public String getIndustryName() {
        return this.industryName;
    }

    public String getMerchantCity() {
        return this.merchantCity;
    }

    public String getMerchantCountry() {
        return this.merchantCountry;
    }

    public double getMerchantLatitude() {
        return this.merchantLatitude;
    }

    public double getMerchantLongitude() {
        return this.merchantLongitude;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getMerchantZipCode() {
        return this.merchantZipCode;
    }

    public long getTransactionDate() {
        return this.transactionDate;
    }

    public long getTransactionID() {
        return this.transactionID;
    }

    public String getTransactionStatus() {
        return this.transactionStatus;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public boolean isCannotBeGeocoded() {
        return this.cannotBeGeocoded;
    }

    public void setAmount(double d) {
        this.amount = d;
    }

    public void setCannotBeGeocoded(boolean z) {
        this.cannotBeGeocoded = z;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setIndustryCategoryCode(String str) {
        this.industryCategoryCode = str;
    }

    public void setIndustryCategoryName(String str) {
        this.industryCategoryName = str;
    }

    public void setIndustryCode(String str) {
        this.industryCode = str;
    }

    public void setIndustryName(String str) {
        this.industryName = str;
    }

    public void setMerchantCity(String str) {
        this.merchantCity = str;
    }

    public void setMerchantCountry(String str) {
        this.merchantCountry = str;
    }

    public void setMerchantLatitude(double d) {
        this.merchantLatitude = d;
    }

    public void setMerchantLongitude(double d) {
        this.merchantLongitude = d;
    }

    public void setMerchantName(String str) {
        this.merchantName = str;
    }

    public void setMerchantZipCode(String str) {
        this.merchantZipCode = str;
    }

    public void setTransactionDate(long j) {
        this.transactionDate = j;
    }

    public void setTransactionID(long j) {
        this.transactionID = j;
    }

    public void setTransactionStatus(String str) {
        this.transactionStatus = str;
    }

    public void setTransactionType(String str) {
        this.transactionType = str;
    }
}
