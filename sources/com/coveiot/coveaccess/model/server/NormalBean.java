package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class NormalBean {
    @SerializedName("gt")
    private Integer gt;
    @SerializedName("lte")
    private Integer lte;

    public Integer getGt() {
        return this.gt;
    }

    public Integer getLte() {
        return this.lte;
    }

    public void setGt(Integer num) {
        this.gt = num;
    }

    public void setLte(Integer num) {
        this.lte = num;
    }
}
