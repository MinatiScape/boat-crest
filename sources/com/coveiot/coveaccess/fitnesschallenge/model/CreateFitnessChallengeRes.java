package com.coveiot.coveaccess.fitnesschallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class CreateFitnessChallengeRes implements Serializable {
    @SerializedName("challengeId")
    @Expose
    private Object challengeId;

    public Object getChallengeId() {
        return this.challengeId;
    }

    public void setChallengeId(Object obj) {
        this.challengeId = obj;
    }
}
