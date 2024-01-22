package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class RelaxBean {
    @SerializedName("gte")
    private Integer gte;
    @SerializedName("lte")
    private Integer lte;

    public Integer getGte() {
        return this.gte;
    }

    public Integer getLte() {
        return this.lte;
    }

    public void setGte(Integer num) {
        this.gte = num;
    }

    public void setLte(Integer num) {
        this.lte = num;
    }
}
