package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SPaymentInstrument implements Serializable {
    @SerializedName("BackgroundColor")
    @Expose
    private String backgroundColor;
    @SerializedName("CardArtImageUrl")
    @Expose
    private String cardArtImageUrl;
    @SerializedName("CardSymbolImageUrl")
    @Expose
    private String cardSymbolImageUrl;
    @SerializedName("ContactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("ContactName")
    @Expose
    private String contactName;
    @SerializedName("ContactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("ContactWebSite")
    @Expose
    private String contactWebSite;
    @SerializedName("ForegroundColor")
    @Expose
    private String foregroundColor;
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    @SerializedName("IsDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("IsPIFinalized")
    @Expose
    private boolean isPIFinalized;
    @SerializedName("IssuerType")
    @Expose
    private String issuerType;
    @SerializedName("LabelColor")
    @Expose
    private String labelColor;
    @SerializedName("Last4")
    @Expose
    private String last4;
    @SerializedName("PaymentInstrumentID")
    @Expose
    private long paymentInstrumentID;
    @SerializedName("PaymentNetworkID")
    @Expose
    private int paymentNetworkID;
    @SerializedName("PaymentNetworkName")
    @Expose
    private String paymentNetworkName;
    @SerializedName("PrivacyPolicyUrl")
    @Expose
    private String privacyPolicyUrl;
    @SerializedName("TermsAndConditionsFileUrl")
    @Expose
    private String termsAndConditionsFileUrl;
    @SerializedName("TermsAndConditionsID")
    @Expose
    private String termsAndConditionsID;
    @SerializedName("TermsAndConditionsUrl")
    @Expose
    private String termsAndConditionsUrl;

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getCardArtImageUrl() {
        return this.cardArtImageUrl;
    }

    public String getCardSymbolImageUrl() {
        return this.cardSymbolImageUrl;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public String getContactWebSite() {
        return this.contactWebSite;
    }

    public String getForegroundColor() {
        return this.foregroundColor;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public String getIssuerType() {
        return this.issuerType;
    }

    public String getLabelColor() {
        return this.labelColor;
    }

    public String getLast4() {
        return this.last4;
    }

    public long getPaymentInstrumentID() {
        return this.paymentInstrumentID;
    }

    public int getPaymentNetworkID() {
        return this.paymentNetworkID;
    }

    public String getPaymentNetworkName() {
        return this.paymentNetworkName;
    }

    public String getPrivacyPolicyUrl() {
        return this.privacyPolicyUrl;
    }

    public String getTermsAndConditionsFileUrl() {
        return this.termsAndConditionsFileUrl;
    }

    public String getTermsAndConditionsID() {
        return this.termsAndConditionsID;
    }

    public String getTermsAndConditionsUrl() {
        return this.termsAndConditionsUrl;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public boolean isPIFinalized() {
        return this.isPIFinalized;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public void setCardArtImageUrl(String str) {
        this.cardArtImageUrl = str;
    }

    public void setCardSymbolImageUrl(String str) {
        this.cardSymbolImageUrl = str;
    }

    public void setContactEmail(String str) {
        this.contactEmail = str;
    }

    public void setContactName(String str) {
        this.contactName = str;
    }

    public void setContactNumber(String str) {
        this.contactNumber = str;
    }

    public void setContactWebSite(String str) {
        this.contactWebSite = str;
    }

    public void setDeleted(boolean z) {
        this.isDeleted = z;
    }

    public void setForegroundColor(String str) {
        this.foregroundColor = str;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setIssuerType(String str) {
        this.issuerType = str;
    }

    public void setLabelColor(String str) {
        this.labelColor = str;
    }

    public void setLast4(String str) {
        this.last4 = str;
    }

    public void setPIFinalized(boolean z) {
        this.isPIFinalized = z;
    }

    public void setPaymentInstrumentID(long j) {
        this.paymentInstrumentID = j;
    }

    public void setPaymentNetworkID(int i) {
        this.paymentNetworkID = i;
    }

    public void setPaymentNetworkName(String str) {
        this.paymentNetworkName = str;
    }

    public void setPrivacyPolicyUrl(String str) {
        this.privacyPolicyUrl = str;
    }

    public void setTermsAndConditionsFileUrl(String str) {
        this.termsAndConditionsFileUrl = str;
    }

    public void setTermsAndConditionsID(String str) {
        this.termsAndConditionsID = str;
    }

    public void setTermsAndConditionsUrl(String str) {
        this.termsAndConditionsUrl = str;
    }
}
