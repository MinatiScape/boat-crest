package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class V3AppExchangeDataIngPara implements Serializable {
    private static final long serialVersionUID = 1;
    public int calories;
    public int distance;
    public int duration;
    public int plan_type;
    public int realTimeSpeed;
    public int signalFlag;
    public int type;
    public int version;

    public String toString() {
        return "V3AppExchangeDataIngPara{type=" + this.type + ", signalFlag=" + this.signalFlag + ", distance=" + this.distance + ", realTimeSpeed=" + this.realTimeSpeed + ", version=" + this.version + ", duration=" + this.duration + ", calories=" + this.calories + ", plan_type=" + this.plan_type + '}';
    }
}
