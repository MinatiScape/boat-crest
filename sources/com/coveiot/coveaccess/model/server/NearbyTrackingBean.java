package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class NearbyTrackingBean {
    @SerializedName("batchMinTimeGap")
    private int batchMinTimeGap;
    @SerializedName("beaconIdPrefixes")
    private List<String> beaconIdPrefixes;
    @SerializedName("broadcastInterval")
    private int broadcastInterval;
    @SerializedName("broadcastServiceUuid")
    private String broadcastServiceUuid;
    @SerializedName("enableContactTracing")
    private boolean enableContactTracing;
    @SerializedName("enableSocialDistancing")
    private boolean enableSocialDistancing;
    @SerializedName("minRssi")
    private int minRssi;
    @SerializedName("scanInterval")
    private int scanInterval;
    @SerializedName("scanWindow")
    private int scanWindow;
    @SerializedName("trackerIdPrefixes")
    private List<String> trackerIdPrefixes;

    public int getBatchMinTimeGap() {
        return this.batchMinTimeGap;
    }

    public List<String> getBeaconIdPrefixes() {
        return this.beaconIdPrefixes;
    }

    public int getBroadcastInterval() {
        return this.broadcastInterval;
    }

    public String getBroadcastServiceUuid() {
        return this.broadcastServiceUuid;
    }

    public int getMinRssi() {
        return this.minRssi;
    }

    public int getScanInterval() {
        return this.scanInterval;
    }

    public int getScanWindow() {
        return this.scanWindow;
    }

    public List<String> getTrackerIdPrefixes() {
        return this.trackerIdPrefixes;
    }

    public boolean isEnableContactTracing() {
        return this.enableContactTracing;
    }

    public boolean isEnableSocialDistancing() {
        return this.enableSocialDistancing;
    }

    public void setBatchMinTimeGap(int i) {
        this.batchMinTimeGap = i;
    }

    public void setBeaconIdPrefixes(List<String> list) {
        this.beaconIdPrefixes = list;
    }

    public void setBroadcastInterval(int i) {
        this.broadcastInterval = i;
    }

    public void setBroadcastServiceUuid(String str) {
        this.broadcastServiceUuid = str;
    }

    public void setEnableContactTracing(boolean z) {
        this.enableContactTracing = z;
    }

    public void setEnableSocialDistancing(boolean z) {
        this.enableSocialDistancing = z;
    }

    public void setMinRssi(int i) {
        this.minRssi = i;
    }

    public void setScanInterval(int i) {
        this.scanInterval = i;
    }

    public void setScanWindow(int i) {
        this.scanWindow = i;
    }

    public void setTrackerIdPrefixes(List<String> list) {
        this.trackerIdPrefixes = list;
    }
}
