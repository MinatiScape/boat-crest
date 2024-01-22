package com.coveiot.sdk.ble.api.model;

import java.util.ArrayList;
/* loaded from: classes9.dex */
public class NearbyDevices {
    private ArrayList<NearbyDevice> nearbyDeviceList;

    public NearbyDevices(ArrayList<NearbyDevice> arrayList) {
        this.nearbyDeviceList = arrayList;
    }

    public ArrayList<NearbyDevice> getNearbyDeviceList() {
        return this.nearbyDeviceList;
    }

    public void setNearbyDeviceList(ArrayList<NearbyDevice> arrayList) {
        this.nearbyDeviceList = arrayList;
    }
}
