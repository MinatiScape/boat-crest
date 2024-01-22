package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class NavigationWatchStateData implements Serializable {
    public String destination;
    public Long distance;
    public int etaDay;
    public int etaHour;
    public int etaMin;
    public Boolean isStarted;
    public int mode;
    public String source;

    public NavigationWatchStateData(Boolean bool) {
        this.isStarted = bool;
    }

    public String getDestination() {
        return this.destination;
    }

    public Long getDistance() {
        return this.distance;
    }

    public int getEtaDay() {
        return this.etaDay;
    }

    public int getEtaHour() {
        return this.etaHour;
    }

    public int getEtaMin() {
        return this.etaMin;
    }

    public int getMode() {
        return this.mode;
    }

    public String getSource() {
        return this.source;
    }

    public Boolean getStarted() {
        return this.isStarted;
    }

    public void setDestination(String str) {
        this.destination = str;
    }

    public void setDistance(Long l) {
        this.distance = l;
    }

    public void setEtaDay(int i) {
        this.etaDay = i;
    }

    public void setEtaHour(int i) {
        this.etaHour = i;
    }

    public void setEtaMin(int i) {
        this.etaMin = i;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setStarted(Boolean bool) {
        this.isStarted = bool;
    }

    public NavigationWatchStateData() {
    }

    public NavigationWatchStateData(Boolean bool, String str, String str2, int i, int i2, int i3, Long l, int i4) {
        this.isStarted = bool;
        this.source = str;
        this.destination = str2;
        this.etaDay = i;
        this.etaHour = i2;
        this.etaMin = i3;
        this.distance = l;
        this.mode = i4;
    }
}
