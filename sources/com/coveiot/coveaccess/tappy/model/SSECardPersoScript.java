package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SSECardPersoScript implements Serializable {
    @SerializedName("APDULines")
    @Expose
    private ArrayList<APDULine> aPDULines;
    @SerializedName("AppletInstanceAID")
    @Expose
    private String appletInstanceAID;
    @SerializedName("IsTokenPersoScriptPending")
    @Expose
    private boolean isTokenPersoScriptPending;
    @SerializedName("PriorityCode")
    @Expose
    private String priorityCode;

    /* loaded from: classes8.dex */
    public static class APDULine implements Serializable {
        @SerializedName("APDU")
        @Expose
        private String aPDU;
        @SerializedName("APDUID")
        @Expose
        private String aPDUID;

        public String getaPDU() {
            return this.aPDU;
        }

        public String getaPDUID() {
            return this.aPDUID;
        }

        public void setaPDU(String str) {
            this.aPDU = str;
        }

        public void setaPDUID(String str) {
            this.aPDUID = str;
        }
    }

    public String getAppletInstanceAID() {
        return this.appletInstanceAID;
    }

    public String getPriorityCode() {
        return this.priorityCode;
    }

    public ArrayList<APDULine> getaPDULines() {
        return this.aPDULines;
    }

    public boolean isTokenPersoScriptPending() {
        return this.isTokenPersoScriptPending;
    }

    public void setAppletInstanceAID(String str) {
        this.appletInstanceAID = str;
    }

    public void setPriorityCode(String str) {
        this.priorityCode = str;
    }

    public void setTokenPersoScriptPending(boolean z) {
        this.isTokenPersoScriptPending = z;
    }

    public void setaPDULines(ArrayList<APDULine> arrayList) {
        this.aPDULines = arrayList;
    }
}
