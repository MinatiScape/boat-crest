package com.coveiot.coveaccess.fitness.model;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class BPFitnessRecordData extends FitnessRecordData {
    @SerializedName("device")
    private DeviceBean device;
    @SerializedName("hr")
    private Integer hr;
    @SerializedName("meanArterialPressure")
    private Integer meanArterialPressure;
    @SerializedName("source")
    public SourceBean source;
    @SerializedName("value")
    @Expose
    private List<Integer> value;

    /* loaded from: classes8.dex */
    public static class DeviceBean {
        @SerializedName("firmwareVersion")
        private String firmwareVersion;
        @SerializedName("hardwareVersion")
        private String hardwareVersion;
        @SerializedName("manufacturer")
        private String manufacturer;
        @SerializedName("model")
        private String model;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @SerializedName(Constants.SERIAL_NUMBER)
        private String serialNumber;

        public String getFirmwareVersion() {
            return this.firmwareVersion;
        }

        public String getHardwareVersion() {
            return this.hardwareVersion;
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

    /* loaded from: classes8.dex */
    public static class SourceBean {
        @SerializedName("identifier")
        public String identifier;
        @SerializedName("type")
        public String typeX;

        public String getIdentifier() {
            return this.identifier;
        }

        public String getTypeX() {
            return this.typeX;
        }

        public void setIdentifier(String str) {
            this.identifier = str;
        }

        public void setTypeX(String str) {
            this.typeX = str;
        }
    }

    public DeviceBean getDevice() {
        return this.device;
    }

    public Integer getHr() {
        return this.hr;
    }

    public Integer getMeanArterialPressure() {
        return this.meanArterialPressure;
    }

    public List<Integer> getValue() {
        return this.value;
    }

    public void setDevice(DeviceBean deviceBean) {
        this.device = deviceBean;
    }

    public void setHr(Integer num) {
        this.hr = num;
    }

    public void setMeanArterialPressure(Integer num) {
        this.meanArterialPressure = num;
    }

    public void setValue(List<Integer> list) {
        this.value = list;
    }
}
