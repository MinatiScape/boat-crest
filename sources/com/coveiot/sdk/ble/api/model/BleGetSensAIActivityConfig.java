package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes9.dex */
public class BleGetSensAIActivityConfig implements Serializable {
    public int activityNumber;
    public List<String> type;

    public BleGetSensAIActivityConfig() {
    }

    public int getActivityNumber() {
        return this.activityNumber;
    }

    public List<String> getType() {
        return this.type;
    }

    public void setActivityNumber(int i) {
        this.activityNumber = i;
    }

    public void setType(List<String> list) {
        this.type = list;
    }

    public String toString() {
        return "BleGetSensAIActivityConfig{activityNumber=" + this.activityNumber + ", type=" + this.type + '}';
    }

    public BleGetSensAIActivityConfig(int i, List<String> list) {
        this.activityNumber = i;
        this.type = list;
    }
}
