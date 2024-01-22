package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PaymentInstrumentData implements Serializable {
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
    private String contactWebSite;
    @Nullable
    private String encryptedPaymentInstrument;
    @Nullable
    private String foregroundColor;
    @Nullable
    private String friendlyName;
    private boolean isCameraCaptured;
    @Nullable
    private Boolean isDeleted;
    @Nullable
    private Boolean isPIFinalized;
    @Nullable
    private String issuerType;
    @Nullable
    private String labelColor;
    @Nullable
    private String last4;
    @Nullable
    private Long paymentInstrumentID;
    @Nullable
    private Integer paymentNetworkID;
    @Nullable
    private String paymentNetworkName;
    @Nullable
    private String privacyPolicyUrl;
    @Nullable
    private String termsAndConditionsFileUrl;
    @Nullable
    private String termsAndConditionsID;
    @Nullable
    private String termsAndConditionsUrl;

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
    public final String getContactWebSite() {
        return this.contactWebSite;
    }

    @Nullable
    public final String getEncryptedPaymentInstrument() {
        return this.encryptedPaymentInstrument;
    }

    @Nullable
    public final String getForegroundColor() {
        return this.foregroundColor;
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
    public final Long getPaymentInstrumentID() {
        return this.paymentInstrumentID;
    }

    @Nullable
    public final Integer getPaymentNetworkID() {
        return this.paymentNetworkID;
    }

    @Nullable
    public final String getPaymentNetworkName() {
        return this.paymentNetworkName;
    }

    @Nullable
    public final String getPrivacyPolicyUrl() {
        return this.privacyPolicyUrl;
    }

    @Nullable
    public final String getTermsAndConditionsFileUrl() {
        return this.termsAndConditionsFileUrl;
    }

    @Nullable
    public final String getTermsAndConditionsID() {
        return this.termsAndConditionsID;
    }

    @Nullable
    public final String getTermsAndConditionsUrl() {
        return this.termsAndConditionsUrl;
    }

    public final boolean isCameraCaptured() {
        return this.isCameraCaptured;
    }

    @Nullable
    public final Boolean isDeleted() {
        return this.isDeleted;
    }

    @Nullable
    public final Boolean isPIFinalized() {
        return this.isPIFinalized;
    }

    public final void setBackgroundColor(@Nullable String str) {
        this.backgroundColor = str;
    }

    public final void setCameraCaptured(boolean z) {
        this.isCameraCaptured = z;
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

    public final void setContactWebSite(@Nullable String str) {
        this.contactWebSite = str;
    }

    public final void setDeleted(@Nullable Boolean bool) {
        this.isDeleted = bool;
    }

    public final void setEncryptedPaymentInstrument(@Nullable String str) {
        this.encryptedPaymentInstrument = str;
    }

    public final void setForegroundColor(@Nullable String str) {
        this.foregroundColor = str;
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

    public final void setPIFinalized(@Nullable Boolean bool) {
        this.isPIFinalized = bool;
    }

    public final void setPaymentInstrumentID(@Nullable Long l) {
        this.paymentInstrumentID = l;
    }

    public final void setPaymentNetworkID(@Nullable Integer num) {
        this.paymentNetworkID = num;
    }

    public final void setPaymentNetworkName(@Nullable String str) {
        this.paymentNetworkName = str;
    }

    public final void setPrivacyPolicyUrl(@Nullable String str) {
        this.privacyPolicyUrl = str;
    }

    public final void setTermsAndConditionsFileUrl(@Nullable String str) {
        this.termsAndConditionsFileUrl = str;
    }

    public final void setTermsAndConditionsID(@Nullable String str) {
        this.termsAndConditionsID = str;
    }

    public final void setTermsAndConditionsUrl(@Nullable String str) {
        this.termsAndConditionsUrl = str;
    }
}
