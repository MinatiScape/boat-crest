package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.CheckType;
/* loaded from: classes.dex */
public class EABleReportMonitorData {
    public CheckType checkType;
    public int monitorData;
    public int monitorStatus;

    public CheckType getCheckType() {
        return this.checkType;
    }

    public int getMonitorData() {
        return this.monitorData;
    }

    public int getMonitorStatus() {
        return this.monitorStatus;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    public void setMonitorData(int i) {
        this.monitorData = i;
    }

    public void setMonitorStatus(int i) {
        this.monitorStatus = i;
    }
}
