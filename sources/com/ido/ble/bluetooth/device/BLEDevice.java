package com.ido.ble.bluetooth.device;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BLEDevice implements Serializable, Comparable<BLEDevice> {
    public static final int TYPE_BRACELET = 2;
    public static final int TYPE_FROM_PHONE_PAIRED = -1;
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_WATCH = 1;
    private static final long serialVersionUID = -5217710157640312976L;
    public int bootload_version;
    public String mDeviceAddress;
    public int mDeviceId;
    public String mDeviceName;
    public boolean mIsInDfuMode;
    public int mRssi;
    @Deprecated
    public a otaFactoryDeviceInfo;
    public int type;
    @Deprecated
    public int version = -1;

    /* loaded from: classes11.dex */
    public static class a implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        public int f12113a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;

        public String toString() {
            return "OTAFactoryDeviceInfo{version=" + this.f12113a + ", bootload_version=" + this.b + ", special_version=" + this.c + ", flash_bin_version=" + this.d + ", internal_version=" + this.e + '}';
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(BLEDevice bLEDevice) {
        return Integer.compare(bLEDevice.mRssi, this.mRssi);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.mDeviceAddress.equals(((BLEDevice) obj).mDeviceAddress);
    }

    public String toFactoryString() {
        return "BLEDevice{mDeviceName='" + this.mDeviceName + "', mDeviceAddress='" + this.mDeviceAddress + "', mRssi=" + this.mRssi + ", mDeviceId=" + this.mDeviceId + ", mIsInDfuMode=" + this.mIsInDfuMode + ", type=" + this.type + ", otaFactoryDeviceInfo=" + this.otaFactoryDeviceInfo + '}';
    }

    public String toString() {
        return "BLEDevice{mDeviceName='" + this.mDeviceName + "', mDeviceAddress='" + this.mDeviceAddress + "', mRssi=" + this.mRssi + ", mDeviceId=" + this.mDeviceId + ", mIsInDfuMode=" + this.mIsInDfuMode + ", type=" + this.type + ", bootload_version=" + this.bootload_version + ", version=" + this.version + ", otaFactoryDeviceInfo=" + this.otaFactoryDeviceInfo + '}';
    }
}
