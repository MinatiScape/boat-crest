package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetTransactionDetailsResponse implements Serializable {
    @SerializedName("CurrentPageIndex")
    @Expose
    private int currentPageIndex;
    @SerializedName("Items")
    @Expose
    private List<Transaction> items;
    @SerializedName("PageSize")
    @Expose
    private int pageSize;
    @SerializedName("TotalItemsCount")
    @Expose
    private int totalItemsCount;
    @SerializedName("TotalPagesCount")
    @Expose
    private int totalPagesCount;

    /* loaded from: classes8.dex */
    public static class Transaction implements Serializable {
        @SerializedName("Amount")
        @Expose
        private double amount;
        @SerializedName("Currency")
        @Expose
        private String currency;
        @SerializedName("IndustryCategoryCode")
        @Expose
        private String industryCategoryCode;
        @SerializedName("IndustryCode")
        @Expose
        private String industryCode;
        @SerializedName("MerchantLatitude")
        @Expose
        private double merchantLatitude;
        @SerializedName("MerchantLongitude")
        @Expose
        private double merchantLongitude;
        @SerializedName("MerchantName")
        @Expose
        private String merchantName;
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

        public String getIndustryCode() {
            return this.industryCode;
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

        public void setAmount(double d) {
            this.amount = d;
        }

        public void setCurrency(String str) {
            this.currency = str;
        }

        public void setIndustryCategoryCode(String str) {
            this.industryCategoryCode = str;
        }

        public void setIndustryCode(String str) {
            this.industryCode = str;
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

    public int getCurrentPageIndex() {
        return this.currentPageIndex;
    }

    public List<Transaction> getItems() {
        return this.items;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalItemsCount() {
        return this.totalItemsCount;
    }

    public int getTotalPagesCount() {
        return this.totalPagesCount;
    }

    public void setCurrentPageIndex(int i) {
        this.currentPageIndex = i;
    }

    public void setItems(List<Transaction> list) {
        this.items = list;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public void setTotalItemsCount(int i) {
        this.totalItemsCount = i;
    }

    public void setTotalPagesCount(int i) {
        this.totalPagesCount = i;
    }
}
