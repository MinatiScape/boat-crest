package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SConfirmProvisioningRequest implements Serializable {
    @SerializedName("APDUResponses")
    @Expose
    private ArrayList<APDUResponse> aPDUResponses;
    private long endUserId;
    @SerializedName("FailureReason")
    @Expose
    private String failureReason;
    @SerializedName("HasSucceeded")
    @Expose
    private boolean hasSucceeded;
    @SerializedName("InitUpdateResponse")
    @Expose
    private String initUpdateResponse;
    private long paymentInstrumentTokenId;

    /* loaded from: classes8.dex */
    public static class APDUResponse implements Serializable {
        @SerializedName("APDUID")
        @Expose
        private String aPDUID;
        @SerializedName("APDUReasonCode")
        @Expose
        private String aPDUReasonCode;
        @SerializedName("APDUResponse")
        @Expose
        private String aPDUResponse;

        public String getaPDUID() {
            return this.aPDUID;
        }

        public String getaPDUReasonCode() {
            return this.aPDUReasonCode;
        }

        public String getaPDUResponse() {
            return this.aPDUResponse;
        }

        public void setaPDUID(String str) {
            this.aPDUID = str;
        }

        public void setaPDUReasonCode(String str) {
            this.aPDUReasonCode = str;
        }

        public void setaPDUResponse(String str) {
            this.aPDUResponse = str;
        }
    }

    public long getEndUserId() {
        return this.endUserId;
    }

    public String getFailureReason() {
        return this.failureReason;
    }

    public String getInitUpdateResponse() {
        return this.initUpdateResponse;
    }

    public long getPaymentInstrumentTokenId() {
        return this.paymentInstrumentTokenId;
    }

    public ArrayList<APDUResponse> getaPDUResponses() {
        return this.aPDUResponses;
    }

    public boolean isHasSucceeded() {
        return this.hasSucceeded;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setFailureReason(String str) {
        this.failureReason = str;
    }

    public void setHasSucceeded(boolean z) {
        this.hasSucceeded = z;
    }

    public void setInitUpdateResponse(String str) {
        this.initUpdateResponse = str;
    }

    public void setPaymentInstrumentTokenId(long j) {
        this.paymentInstrumentTokenId = j;
    }

    public void setaPDUResponses(ArrayList<APDUResponse> arrayList) {
        this.aPDUResponses = arrayList;
    }
}
