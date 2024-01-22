package com.coveiot.android.tappy.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TransactionBeanInfo {
    @Nullable
    private Double amount;
    @Nullable
    private String currency;
    @Nullable
    private String industryCategoryCode;
    @Nullable
    private String industryCode;
    @Nullable
    private Double merchantLatitude;
    @Nullable
    private Double merchantLongitude;
    @Nullable
    private String merchantName;
    @Nullable
    private Long transactionDate;
    @Nullable
    private Long transactionId;
    @Nullable
    private String transactionStatus;
    @Nullable
    private String transactionType;

    public TransactionBeanInfo() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    public TransactionBeanInfo(@Nullable Long l, @Nullable String str, @Nullable String str2, @Nullable Long l2, @Nullable Double d, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Double d2, @Nullable Double d3) {
        this.transactionId = l;
        this.transactionStatus = str;
        this.transactionType = str2;
        this.transactionDate = l2;
        this.amount = d;
        this.currency = str3;
        this.industryCategoryCode = str4;
        this.industryCode = str5;
        this.merchantName = str6;
        this.merchantLatitude = d2;
        this.merchantLongitude = d3;
    }

    @Nullable
    public final Long component1() {
        return this.transactionId;
    }

    @Nullable
    public final Double component10() {
        return this.merchantLatitude;
    }

    @Nullable
    public final Double component11() {
        return this.merchantLongitude;
    }

    @Nullable
    public final String component2() {
        return this.transactionStatus;
    }

    @Nullable
    public final String component3() {
        return this.transactionType;
    }

    @Nullable
    public final Long component4() {
        return this.transactionDate;
    }

    @Nullable
    public final Double component5() {
        return this.amount;
    }

    @Nullable
    public final String component6() {
        return this.currency;
    }

    @Nullable
    public final String component7() {
        return this.industryCategoryCode;
    }

    @Nullable
    public final String component8() {
        return this.industryCode;
    }

    @Nullable
    public final String component9() {
        return this.merchantName;
    }

    @NotNull
    public final TransactionBeanInfo copy(@Nullable Long l, @Nullable String str, @Nullable String str2, @Nullable Long l2, @Nullable Double d, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Double d2, @Nullable Double d3) {
        return new TransactionBeanInfo(l, str, str2, l2, d, str3, str4, str5, str6, d2, d3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TransactionBeanInfo) {
            TransactionBeanInfo transactionBeanInfo = (TransactionBeanInfo) obj;
            return Intrinsics.areEqual(this.transactionId, transactionBeanInfo.transactionId) && Intrinsics.areEqual(this.transactionStatus, transactionBeanInfo.transactionStatus) && Intrinsics.areEqual(this.transactionType, transactionBeanInfo.transactionType) && Intrinsics.areEqual(this.transactionDate, transactionBeanInfo.transactionDate) && Intrinsics.areEqual((Object) this.amount, (Object) transactionBeanInfo.amount) && Intrinsics.areEqual(this.currency, transactionBeanInfo.currency) && Intrinsics.areEqual(this.industryCategoryCode, transactionBeanInfo.industryCategoryCode) && Intrinsics.areEqual(this.industryCode, transactionBeanInfo.industryCode) && Intrinsics.areEqual(this.merchantName, transactionBeanInfo.merchantName) && Intrinsics.areEqual((Object) this.merchantLatitude, (Object) transactionBeanInfo.merchantLatitude) && Intrinsics.areEqual((Object) this.merchantLongitude, (Object) transactionBeanInfo.merchantLongitude);
        }
        return false;
    }

    @Nullable
    public final Double getAmount() {
        return this.amount;
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
    public final String getIndustryCode() {
        return this.industryCode;
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
    public final Long getTransactionDate() {
        return this.transactionDate;
    }

    @Nullable
    public final Long getTransactionId() {
        return this.transactionId;
    }

    @Nullable
    public final String getTransactionStatus() {
        return this.transactionStatus;
    }

    @Nullable
    public final String getTransactionType() {
        return this.transactionType;
    }

    public int hashCode() {
        Long l = this.transactionId;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        String str = this.transactionStatus;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.transactionType;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l2 = this.transactionDate;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Double d = this.amount;
        int hashCode5 = (hashCode4 + (d == null ? 0 : d.hashCode())) * 31;
        String str3 = this.currency;
        int hashCode6 = (hashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.industryCategoryCode;
        int hashCode7 = (hashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.industryCode;
        int hashCode8 = (hashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.merchantName;
        int hashCode9 = (hashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Double d2 = this.merchantLatitude;
        int hashCode10 = (hashCode9 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.merchantLongitude;
        return hashCode10 + (d3 != null ? d3.hashCode() : 0);
    }

    public final void setAmount(@Nullable Double d) {
        this.amount = d;
    }

    public final void setCurrency(@Nullable String str) {
        this.currency = str;
    }

    public final void setIndustryCategoryCode(@Nullable String str) {
        this.industryCategoryCode = str;
    }

    public final void setIndustryCode(@Nullable String str) {
        this.industryCode = str;
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

    public final void setTransactionDate(@Nullable Long l) {
        this.transactionDate = l;
    }

    public final void setTransactionId(@Nullable Long l) {
        this.transactionId = l;
    }

    public final void setTransactionStatus(@Nullable String str) {
        this.transactionStatus = str;
    }

    public final void setTransactionType(@Nullable String str) {
        this.transactionType = str;
    }

    @NotNull
    public String toString() {
        return "TransactionBeanInfo(transactionId=" + this.transactionId + ", transactionStatus=" + this.transactionStatus + ", transactionType=" + this.transactionType + ", transactionDate=" + this.transactionDate + ", amount=" + this.amount + ", currency=" + this.currency + ", industryCategoryCode=" + this.industryCategoryCode + ", industryCode=" + this.industryCode + ", merchantName=" + this.merchantName + ", merchantLatitude=" + this.merchantLatitude + ", merchantLongitude=" + this.merchantLongitude + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ TransactionBeanInfo(Long l, String str, String str2, Long l2, Double d, String str3, String str4, String str5, String str6, Double d2, Double d3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : l2, (i & 16) != 0 ? null : d, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : d2, (i & 1024) == 0 ? d3 : null);
    }
}
