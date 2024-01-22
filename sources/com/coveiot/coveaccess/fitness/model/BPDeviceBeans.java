package com.coveiot.coveaccess.fitness.model;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class BPDeviceBeans {
    @SerializedName("firmwareVersion")
    @Expose
    private String firmwareVersion;
    @SerializedName("hardwareVersion")
    @Expose
    private String hardwareVersion;
    @SerializedName(DeviceKey.MacAddress)
    @Expose
    private String macAddress;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName(Constants.SERIAL_NUMBER)
    @Expose
    private String serialNumber;

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public String getName() {
        return this.name;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSerialNumber(String str) {
        this.serialNumber = str;
    }
}
