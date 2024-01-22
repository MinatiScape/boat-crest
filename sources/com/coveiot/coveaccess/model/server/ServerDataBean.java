package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ServerDataBean {
    @SerializedName("hp")
    private Integer hp;
    @SerializedName("hrva")
    private Integer hrva;

    public Integer getHp() {
        return this.hp;
    }

    public Integer getHrva() {
        return this.hrva;
    }

    public void setHp(Integer num) {
        this.hp = num;
    }

    public void setHrva(Integer num) {
        this.hrva = num;
    }
}
