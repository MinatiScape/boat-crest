package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes9.dex */
public class BleGetSmartAlertConfigData implements Serializable {
    private List<BleSmartAlertAppData> bleSmartAlertAppDataList;
    private boolean isEnabled;

    public BleGetSmartAlertConfigData(boolean z, List<BleSmartAlertAppData> list) {
        this.isEnabled = z;
        this.bleSmartAlertAppDataList = list;
    }

    public List<BleSmartAlertAppData> getBleSmartAlertAppDataList() {
        return this.bleSmartAlertAppDataList;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setBleSmartAlertAppDataList(List<BleSmartAlertAppData> list) {
        this.bleSmartAlertAppDataList = list;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public String toString() {
        return "BleGetSmartAlertConfigData{isEnabled=" + this.isEnabled + ", bleSmartAlertAppDataList=" + this.bleSmartAlertAppDataList + '}';
    }
}
