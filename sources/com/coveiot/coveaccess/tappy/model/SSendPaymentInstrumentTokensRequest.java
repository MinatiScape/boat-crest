package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SSendPaymentInstrumentTokensRequest implements Serializable {
    @SerializedName("CurrentSEInfo")
    @Expose
    private CurrentSEInfo currentSEInfo;
    @SerializedName("DeviceID")
    @Expose
    private long deviceID;
    private transient long endUserId;
    @SerializedName("EndUserProductRegistrationID")
    @Expose
    private long endUserProductRegistrationID;
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    @SerializedName("Locale")
    @Expose
    private String locale;
    @SerializedName("PaymentInstrumentID")
    @Expose
    private Long paymentInstrumentID;
    @SerializedName("PaymentInstrumentInfo")
    @Expose
    private PaymentInstrumentInfo paymentInstrumentInfo;

    /* loaded from: classes8.dex */
    public static class CurrentSEInfo implements Serializable {
        @SerializedName("CASDCertificate")
        @Expose
        private String cASDCertificate;
        @SerializedName("InitUpdateResponse")
        @Expose
        private String initUpdateResponse;
        @SerializedName("SDScript")
        @Expose
        private String sDScript;
        @SerializedName("SavedAPDUResponseDatas")
        @Expose
        private ArrayList<String> savedAPDUResponseDatas;

        public String getInitUpdateResponse() {
            return this.initUpdateResponse;
        }

        public ArrayList<String> getSavedAPDUResponseDatas() {
            return this.savedAPDUResponseDatas;
        }

        public String getcASDCertificate() {
            return this.cASDCertificate;
        }

        public String getsDScript() {
            return this.sDScript;
        }

        public void setInitUpdateResponse(String str) {
            this.initUpdateResponse = str;
        }

        public void setSavedAPDUResponseDatas(ArrayList<String> arrayList) {
            this.savedAPDUResponseDatas = arrayList;
        }

        public void setcASDCertificate(String str) {
            this.cASDCertificate = str;
        }

        public void setsDScript(String str) {
            this.sDScript = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class PaymentInstrumentInfo implements Serializable {
        @SerializedName("EncryptedPaymentInstrument")
        @Expose
        private String encryptedPaymentInstrument;
        @SerializedName("FriendlyName")
        @Expose
        private String friendlyName;
        @SerializedName("IsCameraCaptured")
        @Expose
        private boolean isCameraCaptured;
        @SerializedName("PaymentNetworkID")
        @Expose
        private int paymentNetworkID;

        public String getEncryptedPaymentInstrument() {
            return this.encryptedPaymentInstrument;
        }

        public String getFriendlyName() {
            return this.friendlyName;
        }

        public int getPaymentNetworkID() {
            return this.paymentNetworkID;
        }

        public boolean isCameraCaptured() {
            return this.isCameraCaptured;
        }

        public void setCameraCaptured(boolean z) {
            this.isCameraCaptured = z;
        }

        public void setEncryptedPaymentInstrument(String str) {
            this.encryptedPaymentInstrument = str;
        }

        public void setFriendlyName(String str) {
            this.friendlyName = str;
        }

        public void setPaymentNetworkID(int i) {
            this.paymentNetworkID = i;
        }
    }

    public CurrentSEInfo getCurrentSEInfo() {
        return this.currentSEInfo;
    }

    public long getDeviceID() {
        return this.deviceID;
    }

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getEndUserProductRegistrationID() {
        return this.endUserProductRegistrationID;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public String getLocale() {
        return this.locale;
    }

    public Long getPaymentInstrumentID() {
        return this.paymentInstrumentID;
    }

    public PaymentInstrumentInfo getPaymentInstrumentInfo() {
        return this.paymentInstrumentInfo;
    }

    public void setCurrentSEInfo(CurrentSEInfo currentSEInfo) {
        this.currentSEInfo = currentSEInfo;
    }

    public void setDeviceID(long j) {
        this.deviceID = j;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setEndUserProductRegistrationID(long j) {
        this.endUserProductRegistrationID = j;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setPaymentInstrumentID(Long l) {
        this.paymentInstrumentID = l;
    }

    public void setPaymentInstrumentInfo(PaymentInstrumentInfo paymentInstrumentInfo) {
        this.paymentInstrumentInfo = paymentInstrumentInfo;
    }
}
