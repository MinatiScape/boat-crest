package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleActivityDataHolder {
    private BleActivityDetailData bleActivityDetailData;
    private BleActivitySummaryData bleActivitySummaryData;
    private long sessionId;

    public BleActivityDataHolder(long j, BleActivitySummaryData bleActivitySummaryData) {
        this.sessionId = j;
        this.bleActivitySummaryData = bleActivitySummaryData;
    }

    public BleActivityDetailData getBleActivityDetailData() {
        return this.bleActivityDetailData;
    }

    public BleActivitySummaryData getBleActivitySummaryData() {
        return this.bleActivitySummaryData;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public void setBleActivityDetailData(BleActivityDetailData bleActivityDetailData) {
        this.bleActivityDetailData = bleActivityDetailData;
    }

    public void setSessionId(long j) {
        this.sessionId = j;
    }
}
