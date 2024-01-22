package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleNavigationItem implements Serializable {
    private BleDynamicSportFieldTextV2 bleDynamicSportFieldTextV2;
    private long distance;
    private int imageId;

    public BleDynamicSportFieldTextV2 getBleDynamicSportFieldText() {
        return this.bleDynamicSportFieldTextV2;
    }

    public long getDistance() {
        return this.distance;
    }

    public int getImageId() {
        return this.imageId;
    }

    public void setBleDynamicSportFieldText(BleDynamicSportFieldTextV2 bleDynamicSportFieldTextV2) {
        this.bleDynamicSportFieldTextV2 = bleDynamicSportFieldTextV2;
    }

    public void setDistance(long j) {
        this.distance = j;
    }

    public void setImageId(int i) {
        this.imageId = i;
    }

    public String toString() {
        return "BleNavigationItem{imageId=" + this.imageId + ", bleDynamicSportFieldText=" + this.bleDynamicSportFieldTextV2 + ", distance=" + this.distance + '}';
    }
}
