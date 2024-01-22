package com.coveiot.coveaccess.device.model;

import com.coveiot.coveaccess.utils.CoveUtil;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BleDeviceInfo implements Serializable {
    private static BleDeviceInfo ourInstance = new BleDeviceInfo();
    private String deviceColor;
    private String mAppearance;
    private String mDeviceName;
    private String mFirmwareRevision;
    private String mHwRevision;
    private String mManufacturerName;
    private String mModelNumber;
    private String mSerialNumber;
    private String mSoftwareRevision;
    private String mSystemId;
    private String macAddress;

    private BleDeviceInfo() {
    }

    public static void clearInstance() {
        ourInstance = new BleDeviceInfo();
    }

    public static BleDeviceInfo getInstance() {
        return ourInstance;
    }

    public String getDeviceColor() {
        return this.deviceColor;
    }

    public String getFirmwareRevision() {
        return this.mFirmwareRevision;
    }

    public String getHwRevision() {
        return this.mHwRevision;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public String getSoftwareRevision() {
        return this.mSoftwareRevision;
    }

    public String getmAppearance() {
        return this.mAppearance;
    }

    public String getmDeviceName() {
        return this.mDeviceName;
    }

    public String getmManufacturerName() {
        return this.mManufacturerName;
    }

    public String getmModelNumber() {
        return this.mModelNumber;
    }

    public String getmSystemId() {
        return this.mSystemId;
    }

    public boolean isDataFilled() {
        return (CoveUtil.isEmpty(this.mDeviceName) || CoveUtil.isEmpty(this.macAddress) || CoveUtil.isEmpty(this.mSerialNumber) || CoveUtil.isEmpty(this.mModelNumber) || CoveUtil.isEmpty(this.mFirmwareRevision) || CoveUtil.isEmpty(this.mHwRevision)) ? false : true;
    }

    public void setDeviceColor(String str) {
        this.deviceColor = str;
    }

    public void setFirmwareRevision(String str) {
        this.mFirmwareRevision = str;
    }

    public void setHwRevision(String str) {
        this.mHwRevision = str;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setSerialNumber(String str) {
        this.mSerialNumber = str;
    }

    public void setSoftwareRevision(String str) {
        this.mSoftwareRevision = str;
    }

    public void setmAppearance(String str) {
        this.mAppearance = str;
    }

    public void setmDeviceName(String str) {
        this.mDeviceName = str;
    }

    public void setmManufacturerName(String str) {
        this.mManufacturerName = str;
    }

    public void setmModelNumber(String str) {
        this.mModelNumber = str;
    }

    public void setmSystemId(String str) {
        this.mSystemId = str;
    }
}
