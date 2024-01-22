package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes9.dex */
public class BleGetSOSRecords implements Serializable {
    public int numberCount;
    public List<SOSRecordItem> sosRecordItemList;

    public BleGetSOSRecords() {
    }

    public int getNumberCount() {
        return this.numberCount;
    }

    public List<SOSRecordItem> getSosRecordItemList() {
        return this.sosRecordItemList;
    }

    public void setNumberCount(int i) {
        this.numberCount = i;
    }

    public void setSosRecordItemList(List<SOSRecordItem> list) {
        this.sosRecordItemList = list;
    }

    public String toString() {
        return "BleGetSOSRecords{numberCount=" + this.numberCount + ", sosRecordItemList=" + this.sosRecordItemList + '}';
    }

    public BleGetSOSRecords(int i, List<SOSRecordItem> list) {
        this.numberCount = i;
        this.sosRecordItemList = list;
    }
}
