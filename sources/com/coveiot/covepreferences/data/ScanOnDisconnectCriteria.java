package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ScanOnDisconnectCriteria {
    @SerializedName("scanAllDevices")

    /* renamed from: a  reason: collision with root package name */
    private Boolean f7040a;
    @SerializedName("shouldDoBackgroundScan")
    private Boolean b;
    @SerializedName("phoneModels")
    private List<PhoneModelsBean> c;

    /* loaded from: classes8.dex */
    public static class PhoneModelsBean {
        @SerializedName("manufacturer")

        /* renamed from: a  reason: collision with root package name */
        private String f7041a;
        @SerializedName("os_versions")
        private String b;
        @SerializedName("models")
        private String c;
        @SerializedName("exclude_models")
        private String d;

        public String getExcludeModels() {
            return this.d;
        }

        public String getManufacturer() {
            return this.f7041a;
        }

        public String getModels() {
            return this.c;
        }

        public String getOsVersions() {
            return this.b;
        }

        public void setExcludeModels(String str) {
            this.d = str;
        }

        public void setManufacturer(String str) {
            this.f7041a = str;
        }

        public void setModels(String str) {
            this.c = str;
        }

        public void setOsVersions(String str) {
            this.b = str;
        }
    }

    public List<PhoneModelsBean> getPhoneModels() {
        return this.c;
    }

    public Boolean getScanAllDevices() {
        return this.f7040a;
    }

    public Boolean getShouldDoBackgroundScan() {
        return this.b;
    }

    public void setPhoneModels(List<PhoneModelsBean> list) {
        this.c = list;
    }

    public void setScanAllDevices(Boolean bool) {
        this.f7040a = bool;
    }

    public void setShouldDoBackgroundScan(Boolean bool) {
        this.b = bool;
    }
}
