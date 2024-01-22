package com.coveiot.coveaccess.fitnesschallenge.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class AddParticipantsReq implements Serializable {
    @SerializedName("challengeId")
    @Expose
    private Object challengeId;
    @SerializedName("participants")
    @Expose
    private List<Participants> participants;

    /* loaded from: classes8.dex */
    public static class Participants implements Serializable {
        @SerializedName("mobileNumber")
        @Expose
        private String mobileNumber;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;

        public Participants(String str, String str2) {
            this.name = str;
            this.mobileNumber = str2;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public String getName() {
            return this.name;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public AddParticipantsReq(Object obj, List<Participants> list) {
        this.challengeId = obj;
        this.participants = list;
    }

    public Object getChallengeId() {
        return this.challengeId;
    }

    public List<Participants> getParticipants() {
        return this.participants;
    }

    public void setChallengeId(Object obj) {
        this.challengeId = obj;
    }

    public void setParticipants(List<Participants> list) {
        this.participants = list;
    }
}
