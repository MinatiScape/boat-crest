package com.coveiot.android.tappy.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class RegCardBeanInfo implements Serializable {
    @Nullable
    private String backgroundColor;
    @Nullable
    private String cardArtImageUrl;
    @Nullable
    private String cardSymbolImageUrl;
    @Nullable
    private String contactEmail;
    @Nullable
    private String contactName;
    @Nullable
    private String contactNumber;
    @Nullable
    private String contactWebsite;
    @Nullable
    private Long endUserID;
    @Nullable
    private Long endUserProductRegistrationID;
    @Nullable
    private String expiryDate;
    @Nullable
    private String friendlyName;
    private boolean isDeleted;
    private boolean isPIFinalized;
    private boolean isSelected;
    @Nullable
    private String issuerType;
    @Nullable
    private String labelColor;
    @Nullable
    private String last4;
    @Nullable
    private Long paymentInstrumentId;
    @Nullable
    private Long paymentInstrumentTokenId;
    @Nullable
    private Integer paymentNetworkId;
    @Nullable
    private String paymentNetworkName;
    @Nullable
    private String productImageUrl;
    @Nullable
    private String productName;
    @Nullable
    private String productSerialNumber;
    @Nullable
    private String provisioningStatus;
    @Nullable
    private String status;
    @Nullable
    private String termsAndConditionId;
    @Nullable
    private String termsAndConditionsFileUrl;

    public RegCardBeanInfo() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, false, null, null, 268435455, null);
    }

    public RegCardBeanInfo(@Nullable Long l, @Nullable Long l2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Long l3, @Nullable String str5, @Nullable String str6, @Nullable Long l4, @Nullable String str7, @Nullable Integer num, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, boolean z, boolean z2, @Nullable String str18, boolean z3, @Nullable String str19, @Nullable String str20) {
        this.endUserProductRegistrationID = l;
        this.endUserID = l2;
        this.friendlyName = str;
        this.productSerialNumber = str2;
        this.productName = str3;
        this.productImageUrl = str4;
        this.paymentInstrumentTokenId = l3;
        this.status = str5;
        this.provisioningStatus = str6;
        this.paymentInstrumentId = l4;
        this.last4 = str7;
        this.paymentNetworkId = num;
        this.paymentNetworkName = str8;
        this.cardArtImageUrl = str9;
        this.cardSymbolImageUrl = str10;
        this.backgroundColor = str11;
        this.labelColor = str12;
        this.contactName = str13;
        this.contactNumber = str14;
        this.contactEmail = str15;
        this.contactWebsite = str16;
        this.termsAndConditionId = str17;
        this.isPIFinalized = z;
        this.isDeleted = z2;
        this.issuerType = str18;
        this.isSelected = z3;
        this.termsAndConditionsFileUrl = str19;
        this.expiryDate = str20;
    }

    @Nullable
    public final Long component1() {
        return this.endUserProductRegistrationID;
    }

    @Nullable
    public final Long component10() {
        return this.paymentInstrumentId;
    }

    @Nullable
    public final String component11() {
        return this.last4;
    }

    @Nullable
    public final Integer component12() {
        return this.paymentNetworkId;
    }

    @Nullable
    public final String component13() {
        return this.paymentNetworkName;
    }

    @Nullable
    public final String component14() {
        return this.cardArtImageUrl;
    }

    @Nullable
    public final String component15() {
        return this.cardSymbolImageUrl;
    }

    @Nullable
    public final String component16() {
        return this.backgroundColor;
    }

    @Nullable
    public final String component17() {
        return this.labelColor;
    }

    @Nullable
    public final String component18() {
        return this.contactName;
    }

    @Nullable
    public final String component19() {
        return this.contactNumber;
    }

    @Nullable
    public final Long component2() {
        return this.endUserID;
    }

    @Nullable
    public final String component20() {
        return this.contactEmail;
    }

    @Nullable
    public final String component21() {
        return this.contactWebsite;
    }

    @Nullable
    public final String component22() {
        return this.termsAndConditionId;
    }

    public final boolean component23() {
        return this.isPIFinalized;
    }

    public final boolean component24() {
        return this.isDeleted;
    }

    @Nullable
    public final String component25() {
        return this.issuerType;
    }

    public final boolean component26() {
        return this.isSelected;
    }

    @Nullable
    public final String component27() {
        return this.termsAndConditionsFileUrl;
    }

    @Nullable
    public final String component28() {
        return this.expiryDate;
    }

    @Nullable
    public final String component3() {
        return this.friendlyName;
    }

    @Nullable
    public final String component4() {
        return this.productSerialNumber;
    }

    @Nullable
    public final String component5() {
        return this.productName;
    }

    @Nullable
    public final String component6() {
        return this.productImageUrl;
    }

    @Nullable
    public final Long component7() {
        return this.paymentInstrumentTokenId;
    }

    @Nullable
    public final String component8() {
        return this.status;
    }

    @Nullable
    public final String component9() {
        return this.provisioningStatus;
    }

    @NotNull
    public final RegCardBeanInfo copy(@Nullable Long l, @Nullable Long l2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Long l3, @Nullable String str5, @Nullable String str6, @Nullable Long l4, @Nullable String str7, @Nullable Integer num, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, boolean z, boolean z2, @Nullable String str18, boolean z3, @Nullable String str19, @Nullable String str20) {
        return new RegCardBeanInfo(l, l2, str, str2, str3, str4, l3, str5, str6, l4, str7, num, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, z, z2, str18, z3, str19, str20);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegCardBeanInfo) {
            RegCardBeanInfo regCardBeanInfo = (RegCardBeanInfo) obj;
            return Intrinsics.areEqual(this.endUserProductRegistrationID, regCardBeanInfo.endUserProductRegistrationID) && Intrinsics.areEqual(this.endUserID, regCardBeanInfo.endUserID) && Intrinsics.areEqual(this.friendlyName, regCardBeanInfo.friendlyName) && Intrinsics.areEqual(this.productSerialNumber, regCardBeanInfo.productSerialNumber) && Intrinsics.areEqual(this.productName, regCardBeanInfo.productName) && Intrinsics.areEqual(this.productImageUrl, regCardBeanInfo.productImageUrl) && Intrinsics.areEqual(this.paymentInstrumentTokenId, regCardBeanInfo.paymentInstrumentTokenId) && Intrinsics.areEqual(this.status, regCardBeanInfo.status) && Intrinsics.areEqual(this.provisioningStatus, regCardBeanInfo.provisioningStatus) && Intrinsics.areEqual(this.paymentInstrumentId, regCardBeanInfo.paymentInstrumentId) && Intrinsics.areEqual(this.last4, regCardBeanInfo.last4) && Intrinsics.areEqual(this.paymentNetworkId, regCardBeanInfo.paymentNetworkId) && Intrinsics.areEqual(this.paymentNetworkName, regCardBeanInfo.paymentNetworkName) && Intrinsics.areEqual(this.cardArtImageUrl, regCardBeanInfo.cardArtImageUrl) && Intrinsics.areEqual(this.cardSymbolImageUrl, regCardBeanInfo.cardSymbolImageUrl) && Intrinsics.areEqual(this.backgroundColor, regCardBeanInfo.backgroundColor) && Intrinsics.areEqual(this.labelColor, regCardBeanInfo.labelColor) && Intrinsics.areEqual(this.contactName, regCardBeanInfo.contactName) && Intrinsics.areEqual(this.contactNumber, regCardBeanInfo.contactNumber) && Intrinsics.areEqual(this.contactEmail, regCardBeanInfo.contactEmail) && Intrinsics.areEqual(this.contactWebsite, regCardBeanInfo.contactWebsite) && Intrinsics.areEqual(this.termsAndConditionId, regCardBeanInfo.termsAndConditionId) && this.isPIFinalized == regCardBeanInfo.isPIFinalized && this.isDeleted == regCardBeanInfo.isDeleted && Intrinsics.areEqual(this.issuerType, regCardBeanInfo.issuerType) && this.isSelected == regCardBeanInfo.isSelected && Intrinsics.areEqual(this.termsAndConditionsFileUrl, regCardBeanInfo.termsAndConditionsFileUrl) && Intrinsics.areEqual(this.expiryDate, regCardBeanInfo.expiryDate);
        }
        return false;
    }

    @Nullable
    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    @Nullable
    public final String getCardArtImageUrl() {
        return this.cardArtImageUrl;
    }

    @Nullable
    public final String getCardSymbolImageUrl() {
        return this.cardSymbolImageUrl;
    }

    @Nullable
    public final String getContactEmail() {
        return this.contactEmail;
    }

    @Nullable
    public final String getContactName() {
        return this.contactName;
    }

    @Nullable
    public final String getContactNumber() {
        return this.contactNumber;
    }

    @Nullable
    public final String getContactWebsite() {
        return this.contactWebsite;
    }

    @Nullable
    public final Long getEndUserID() {
        return this.endUserID;
    }

    @Nullable
    public final Long getEndUserProductRegistrationID() {
        return this.endUserProductRegistrationID;
    }

    @Nullable
    public final String getExpiryDate() {
        return this.expiryDate;
    }

    @Nullable
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    @Nullable
    public final String getIssuerType() {
        return this.issuerType;
    }

    @Nullable
    public final String getLabelColor() {
        return this.labelColor;
    }

    @Nullable
    public final String getLast4() {
        return this.last4;
    }

    @Nullable
    public final Long getPaymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    @Nullable
    public final Long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    @Nullable
    public final Integer getPaymentNetworkId() {
        return this.paymentNetworkId;
    }

    @Nullable
    public final String getPaymentNetworkName() {
        return this.paymentNetworkName;
    }

    @Nullable
    public final String getProductImageUrl() {
        return this.productImageUrl;
    }

    @Nullable
    public final String getProductName() {
        return this.productName;
    }

    @Nullable
    public final String getProductSerialNumber() {
        return this.productSerialNumber;
    }

    @Nullable
    public final String getProvisioningStatus() {
        return this.provisioningStatus;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    @Nullable
    public final String getTermsAndConditionId() {
        return this.termsAndConditionId;
    }

    @Nullable
    public final String getTermsAndConditionsFileUrl() {
        return this.termsAndConditionsFileUrl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Long l = this.endUserProductRegistrationID;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        Long l2 = this.endUserID;
        int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str = this.friendlyName;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.productSerialNumber;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.productName;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.productImageUrl;
        int hashCode6 = (hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Long l3 = this.paymentInstrumentTokenId;
        int hashCode7 = (hashCode6 + (l3 == null ? 0 : l3.hashCode())) * 31;
        String str5 = this.status;
        int hashCode8 = (hashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.provisioningStatus;
        int hashCode9 = (hashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Long l4 = this.paymentInstrumentId;
        int hashCode10 = (hashCode9 + (l4 == null ? 0 : l4.hashCode())) * 31;
        String str7 = this.last4;
        int hashCode11 = (hashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Integer num = this.paymentNetworkId;
        int hashCode12 = (hashCode11 + (num == null ? 0 : num.hashCode())) * 31;
        String str8 = this.paymentNetworkName;
        int hashCode13 = (hashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.cardArtImageUrl;
        int hashCode14 = (hashCode13 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.cardSymbolImageUrl;
        int hashCode15 = (hashCode14 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.backgroundColor;
        int hashCode16 = (hashCode15 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.labelColor;
        int hashCode17 = (hashCode16 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.contactName;
        int hashCode18 = (hashCode17 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.contactNumber;
        int hashCode19 = (hashCode18 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.contactEmail;
        int hashCode20 = (hashCode19 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.contactWebsite;
        int hashCode21 = (hashCode20 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.termsAndConditionId;
        int hashCode22 = (hashCode21 + (str17 == null ? 0 : str17.hashCode())) * 31;
        boolean z = this.isPIFinalized;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode22 + i) * 31;
        boolean z2 = this.isDeleted;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        String str18 = this.issuerType;
        int hashCode23 = (i4 + (str18 == null ? 0 : str18.hashCode())) * 31;
        boolean z3 = this.isSelected;
        int i5 = (hashCode23 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
        String str19 = this.termsAndConditionsFileUrl;
        int hashCode24 = (i5 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.expiryDate;
        return hashCode24 + (str20 != null ? str20.hashCode() : 0);
    }

    public final boolean isDeleted() {
        return this.isDeleted;
    }

    public final boolean isPIFinalized() {
        return this.isPIFinalized;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setBackgroundColor(@Nullable String str) {
        this.backgroundColor = str;
    }

    public final void setCardArtImageUrl(@Nullable String str) {
        this.cardArtImageUrl = str;
    }

    public final void setCardSymbolImageUrl(@Nullable String str) {
        this.cardSymbolImageUrl = str;
    }

    public final void setContactEmail(@Nullable String str) {
        this.contactEmail = str;
    }

    public final void setContactName(@Nullable String str) {
        this.contactName = str;
    }

    public final void setContactNumber(@Nullable String str) {
        this.contactNumber = str;
    }

    public final void setContactWebsite(@Nullable String str) {
        this.contactWebsite = str;
    }

    public final void setDeleted(boolean z) {
        this.isDeleted = z;
    }

    public final void setEndUserID(@Nullable Long l) {
        this.endUserID = l;
    }

    public final void setEndUserProductRegistrationID(@Nullable Long l) {
        this.endUserProductRegistrationID = l;
    }

    public final void setExpiryDate(@Nullable String str) {
        this.expiryDate = str;
    }

    public final void setFriendlyName(@Nullable String str) {
        this.friendlyName = str;
    }

    public final void setIssuerType(@Nullable String str) {
        this.issuerType = str;
    }

    public final void setLabelColor(@Nullable String str) {
        this.labelColor = str;
    }

    public final void setLast4(@Nullable String str) {
        this.last4 = str;
    }

    public final void setPIFinalized(boolean z) {
        this.isPIFinalized = z;
    }

    public final void setPaymentInstrumentId(@Nullable Long l) {
        this.paymentInstrumentId = l;
    }

    public final void setPaymentInstrumentTokenId(@Nullable Long l) {
        this.paymentInstrumentTokenId = l;
    }

    public final void setPaymentNetworkId(@Nullable Integer num) {
        this.paymentNetworkId = num;
    }

    public final void setPaymentNetworkName(@Nullable String str) {
        this.paymentNetworkName = str;
    }

    public final void setProductImageUrl(@Nullable String str) {
        this.productImageUrl = str;
    }

    public final void setProductName(@Nullable String str) {
        this.productName = str;
    }

    public final void setProductSerialNumber(@Nullable String str) {
        this.productSerialNumber = str;
    }

    public final void setProvisioningStatus(@Nullable String str) {
        this.provisioningStatus = str;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    public final void setTermsAndConditionId(@Nullable String str) {
        this.termsAndConditionId = str;
    }

    public final void setTermsAndConditionsFileUrl(@Nullable String str) {
        this.termsAndConditionsFileUrl = str;
    }

    @NotNull
    public String toString() {
        return "RegCardBeanInfo(endUserProductRegistrationID=" + this.endUserProductRegistrationID + ", endUserID=" + this.endUserID + ", friendlyName=" + this.friendlyName + ", productSerialNumber=" + this.productSerialNumber + ", productName=" + this.productName + ", productImageUrl=" + this.productImageUrl + ", paymentInstrumentTokenId=" + this.paymentInstrumentTokenId + ", status=" + this.status + ", provisioningStatus=" + this.provisioningStatus + ", paymentInstrumentId=" + this.paymentInstrumentId + ", last4=" + this.last4 + ", paymentNetworkId=" + this.paymentNetworkId + ", paymentNetworkName=" + this.paymentNetworkName + ", cardArtImageUrl=" + this.cardArtImageUrl + ", cardSymbolImageUrl=" + this.cardSymbolImageUrl + ", backgroundColor=" + this.backgroundColor + ", labelColor=" + this.labelColor + ", contactName=" + this.contactName + ", contactNumber=" + this.contactNumber + ", contactEmail=" + this.contactEmail + ", contactWebsite=" + this.contactWebsite + ", termsAndConditionId=" + this.termsAndConditionId + ", isPIFinalized=" + this.isPIFinalized + ", isDeleted=" + this.isDeleted + ", issuerType=" + this.issuerType + ", isSelected=" + this.isSelected + ", termsAndConditionsFileUrl=" + this.termsAndConditionsFileUrl + ", expiryDate=" + this.expiryDate + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ RegCardBeanInfo(Long l, Long l2, String str, String str2, String str3, String str4, Long l3, String str5, String str6, Long l4, String str7, Integer num, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, boolean z, boolean z2, String str18, boolean z3, String str19, String str20, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : l2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : l3, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : l4, (i & 1024) != 0 ? null : str7, (i & 2048) != 0 ? null : num, (i & 4096) != 0 ? null : str8, (i & 8192) != 0 ? null : str9, (i & 16384) != 0 ? null : str10, (i & 32768) != 0 ? null : str11, (i & 65536) != 0 ? null : str12, (i & 131072) != 0 ? null : str13, (i & 262144) != 0 ? null : str14, (i & 524288) != 0 ? null : str15, (i & 1048576) != 0 ? null : str16, (i & 2097152) != 0 ? null : str17, (i & 4194304) != 0 ? false : z, (i & 8388608) != 0 ? false : z2, (i & 16777216) != 0 ? null : str18, (i & 33554432) == 0 ? z3 : false, (i & 67108864) != 0 ? null : str19, (i & 134217728) != 0 ? null : str20);
    }
}
