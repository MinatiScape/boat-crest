package com.coveiot.coveaccess.fitnesschallenge.model;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class RemoveParticipantsReq implements Serializable {
    @SerializedName("challengeId")
    @Expose
    private Object challengeId;
    @SerializedName("participants")
    @Expose
    private List<Participants> participants;

    /* loaded from: classes8.dex */
    public static class Participants implements Serializable {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;
        @SerializedName(Constants.END_USER_GLOBAL_ID)
        @Expose
        private String userId;

        public Participants(String str, String str2) {
            this.name = str;
            this.userId = str2;
        }

        public String getName() {
            return this.name;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    public RemoveParticipantsReq(Object obj, List<Participants> list) {
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
