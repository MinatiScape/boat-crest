package com.coveiot.coveaccess.fitnesschallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class JoinChallengeReq implements Serializable {
    @SerializedName("challengeId")
    @Expose
    private Object challengeId;
    @SerializedName("type")
    @Expose
    private String type;

    public JoinChallengeReq(Object obj, String str) {
        this.challengeId = obj;
        this.type = str;
    }

    public Object getChallengeId() {
        return this.challengeId;
    }

    public String getType() {
        return this.type;
    }

    public void setChallengeId(Object obj) {
        this.challengeId = obj;
    }

    public void setType(String str) {
        this.type = str;
    }
}
