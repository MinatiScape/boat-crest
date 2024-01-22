package com.coveiot.coveaccess.model.server;

import com.coveiot.android.tappy.utils.Constants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class SIOTUserDevicesRequestModel {
    @SerializedName("btMacAddress")
    private String btMacAddress;
    @SerializedName("customerId")
    private String customerId;
    @SerializedName("deviceColor")
    private String deviceColor;
    @SerializedName(DeviceKey.DeviceName)
    private String deviceName;
    @SerializedName("firmwareVersion")
    private String firmwareVersion;
    @SerializedName("hardwareVersion")
    private String hardwareVersion;
    @SerializedName("manufactureDate")
    private String manufactureDate;
    @SerializedName("modelNumber")
    private String modelNumber;
    @SerializedName("registerInDvcMgmt")
    private Boolean registerInDvcMgmt;
    @SerializedName(Constants.SERIAL_NUMBER)
    private String serialNumber;
    @SerializedName("trackerId")
    private String trackerId;

    public String getBtMacAddress() {
        return this.btMacAddress;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceColor() {
        return this.deviceColor;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public String getManufactureDate() {
        return this.manufactureDate;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public Boolean getRegisterInDvcMgmt() {
        return this.registerInDvcMgmt;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public String getTrackerId() {
        return this.trackerId;
    }

    public void setBtMacAddress(String str) {
        this.btMacAddress = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setDeviceColor(String str) {
        this.deviceColor = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public void setManufactureDate(String str) {
        this.manufactureDate = str;
    }

    public void setModelNumber(String str) {
        this.modelNumber = str;
    }

    public void setRegisterInDvcMgmt(Boolean bool) {
        this.registerInDvcMgmt = bool;
    }

    public void setSerialNumber(String str) {
        this.serialNumber = str;
    }

    public void setTrackerId(String str) {
        this.trackerId = str;
    }
}
