package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.coveiot.android.leonardo.firebaseservices.enums.InboxMessageTypes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class InboxMessageModel implements Serializable {
    @SerializedName("cloveUserId")
    @Expose
    public String cloveUserId;
    @SerializedName("clovenetUserId")
    @Expose
    public String clovenetUserId;
    @SerializedName("epochTimeStamp")
    @Expose
    public long epochTimeStamp;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("nomineeId")
    @Expose
    public String nomineeId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("type")
    @Expose
    public InboxMessageTypes type;

    public InboxMessageModel(String str, InboxMessageTypes inboxMessageTypes, String str2, String str3, String str4, String str5, long j) {
        this.message = str;
        this.type = inboxMessageTypes;
        this.epochTimeStamp = j;
        this.nomineeId = str2;
        this.cloveUserId = str3;
        this.clovenetUserId = str4;
        this.epochTimeStamp = j;
        this.title = str5;
    }

    public String getCloveUserId() {
        return this.cloveUserId;
    }

    public String getClovenetUserId() {
        return this.clovenetUserId;
    }

    public long getEpochTimeStamp() {
        return this.epochTimeStamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNomineeId() {
        return this.nomineeId;
    }

    public String getTitle() {
        return this.title;
    }

    public InboxMessageTypes getType() {
        return this.type;
    }

    public void setCloveUserId(String str) {
        this.cloveUserId = str;
    }

    public void setClovenetUserId(String str) {
        this.clovenetUserId = str;
    }

    public void setEpochTimeStamp(long j) {
        this.epochTimeStamp = j;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNomineeId(String str) {
        this.nomineeId = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setType(InboxMessageTypes inboxMessageTypes) {
        this.type = inboxMessageTypes;
    }
}
