package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SUserDataResponseNew implements Serializable {
    @SerializedName("tappyResponse")
    @Expose
    private TappyResponse tappyResponse;
    @SerializedName("vUserId")
    @Expose
    private String vUserID;

    /* loaded from: classes8.dex */
    public static class TappyResponse implements Serializable {
        @SerializedName("EmailAddress")
        @Expose
        private String emailAddress;
        @SerializedName("GlobalUserID")
        @Expose
        private Long globalUserID;

        public String getEmailAddress() {
            return this.emailAddress;
        }

        public Long getGlobalUserID() {
            return this.globalUserID;
        }

        public void setEmailAddress(String str) {
            this.emailAddress = str;
        }

        public void setGlobalUserID(Long l) {
            this.globalUserID = l;
        }
    }

    public TappyResponse getTappyResponse() {
        return this.tappyResponse;
    }

    public String getvUserID() {
        return this.vUserID;
    }

    public void setTappyResponse(TappyResponse tappyResponse) {
        this.tappyResponse = tappyResponse;
    }

    public void setvUserID(String str) {
        this.vUserID = str;
    }
}
