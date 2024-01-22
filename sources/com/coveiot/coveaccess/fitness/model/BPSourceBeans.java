package com.coveiot.coveaccess.fitness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BPSourceBeans {
    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("type")
    @Expose
    private String type;

    public String getIdentifier() {
        return this.identifier;
    }

    public String getType() {
        return this.type;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
