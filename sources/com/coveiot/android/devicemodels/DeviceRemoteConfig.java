package com.coveiot.android.devicemodels;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class DeviceRemoteConfig implements Serializable {
    @SerializedName("device_capability_config")
    private List<DeviceCapabilityConfig> deviceCapabilityConfigList;
    @SerializedName("device_categories")
    private List<DeviceCategories> deviceCategories;
    @SerializedName("device_list")
    private List<DeviceModelsBean> deviceList;
    @SerializedName("device_models")
    private List<DeviceModelsBean> deviceModels;

    /* loaded from: classes4.dex */
    public static class DeviceCapabilityConfig implements Serializable {
        @SerializedName("auto_activity_detection")
        private AutoActivityDetection autoActivityDetection;
        @SerializedName("extended_notifications")
        private ExtendedNotifications extendedNotifications;
        @SerializedName("lift_wrist_schedule")
        private LiftWristSchedule liftWristSchedule;
        private String name;
        @SerializedName("repiratory_rate")
        private RespiratoryRate respiratoryRate;
        private String type;

        /* loaded from: classes4.dex */
        public static class AutoActivityDetection implements Serializable {
            @SerializedName("fw_to_exclude")
            private ArrayList<String> fwToExclude;
            @SerializedName("min_fw_version")
            private String minFwVersion;

            public ArrayList<String> getFwToExclude() {
                return this.fwToExclude;
            }

            public String getMinFwVersion() {
                return this.minFwVersion;
            }

            public void setFwToExclude(ArrayList<String> arrayList) {
                this.fwToExclude = arrayList;
            }

            public void setMinFwVersion(String str) {
                this.minFwVersion = str;
            }
        }

        /* loaded from: classes4.dex */
        public static class ExtendedNotifications implements Serializable {
            @SerializedName("fw_to_exclude")
            private ArrayList<String> fwToExclude;
            @SerializedName("min_fw_version")
            private String minFwVersion;

            public ArrayList<String> getFwToExclude() {
                return this.fwToExclude;
            }

            public String getMinFwVersion() {
                return this.minFwVersion;
            }

            public void setFwToExclude(ArrayList<String> arrayList) {
                this.fwToExclude = arrayList;
            }

            public void setMinFwVersion(String str) {
                this.minFwVersion = str;
            }
        }

        /* loaded from: classes4.dex */
        public static class LiftWristSchedule implements Serializable {
            @SerializedName("fw_to_exclude")
            private ArrayList<String> fwToExclude;
            @SerializedName("min_fw_version")
            private String minFwVersion;

            public ArrayList<String> getFwToExclude() {
                return this.fwToExclude;
            }

            public String getMinFwVersion() {
                return this.minFwVersion;
            }

            public void setFwToExclude(ArrayList<String> arrayList) {
                this.fwToExclude = arrayList;
            }

            public void setMinFwVersion(String str) {
                this.minFwVersion = str;
            }
        }

        /* loaded from: classes4.dex */
        public static class RespiratoryRate {
            @SerializedName("min_fw_version")

            /* renamed from: a  reason: collision with root package name */
            private String f4299a;
            @SerializedName("fw_to_exclude")
            private ArrayList<String> b;

            public ArrayList<String> getFwToExclude() {
                return this.b;
            }

            public String getMinFwVersion() {
                return this.f4299a;
            }

            public void setFwToExclude(ArrayList<String> arrayList) {
                this.b = arrayList;
            }

            public void setMinFwVersion(String str) {
                this.f4299a = str;
            }
        }

        public AutoActivityDetection getAutoActivityDetection() {
            return this.autoActivityDetection;
        }

        public ExtendedNotifications getExtendedNotifications() {
            return this.extendedNotifications;
        }

        public LiftWristSchedule getLiftWristSchedule() {
            return this.liftWristSchedule;
        }

        public String getName() {
            return this.name;
        }

        public RespiratoryRate getRespiratoryRate() {
            return this.respiratoryRate;
        }

        public String getType() {
            return this.type;
        }

        public void setAutoActivityDetection(AutoActivityDetection autoActivityDetection) {
            this.autoActivityDetection = autoActivityDetection;
        }

        public void setExtendedNotifications(ExtendedNotifications extendedNotifications) {
            this.extendedNotifications = extendedNotifications;
        }

        public void setLiftWristSchedule(LiftWristSchedule liftWristSchedule) {
            this.liftWristSchedule = liftWristSchedule;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setRespiratoryRate(RespiratoryRate respiratoryRate) {
            this.respiratoryRate = respiratoryRate;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    /* loaded from: classes4.dex */
    public static class DeviceCategories {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

        /* renamed from: a  reason: collision with root package name */
        private String f4300a;
        @SerializedName("list_filter")
        private List<String> b;

        public List<String> getList_filter() {
            return this.b;
        }

        public String getName() {
            return this.f4300a;
        }

        public void setList_filter(List<String> list) {
            this.b = list;
        }

        public void setName(String str) {
            this.f4300a = str;
        }
    }

    /* loaded from: classes4.dex */
    public static class DeviceModelsBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

        /* renamed from: a  reason: collision with root package name */
        private String f4301a;
        @SerializedName("type")
        private String b;
        @SerializedName("remoteCommandFrameWork")
        private RemoteCommandFrameWork c;
        @SerializedName("isRemoteFrameworkSupported")
        private Boolean d;
        @SerializedName("isDiyWatchFaceSupported")
        private Boolean e;
        @SerializedName("isWatchDiagnosticsSupported")
        private Boolean f;
        @SerializedName("scanFilter")
        private List<String> g;
        @SerializedName(Constants.KEY_ICON)
        private String h;
        @SerializedName("qrCode")
        private QRCode i;
        @SerializedName("isTapAndPaySupported")
        private Boolean j;
        @SerializedName("is1kActivitySupported")
        private Boolean k;
        @SerializedName("isSleepAndEnergyScoreSupported")
        private Boolean l;
        @SerializedName("disableAutoHRFor")
        private List<String> m;
        @SerializedName("minBatteryPerForWatchFaceUpload")
        private Integer n = 5;
        @SerializedName("minTimeGapForShowingNextAutoHRPopup")
        private Integer o;
        @SerializedName("logoType")
        private String p;
        @SerializedName("isQRCodeOnboardingSupported")
        private Boolean q;

        /* loaded from: classes4.dex */
        public static class QRCode {
            @SerializedName("max_allowed")

            /* renamed from: a  reason: collision with root package name */
            private Integer f4302a;
            @SerializedName("max_char_limit")
            private Integer b;
            @SerializedName("resolution")
            private String c;

            public Integer getMax_allowed() {
                return this.f4302a;
            }

            public Integer getMax_char_limit() {
                return this.b;
            }

            public String getResolution() {
                return this.c;
            }

            public void setMax_allowed(Integer num) {
                this.f4302a = num;
            }

            public void setMax_char_limit(Integer num) {
                this.b = num;
            }

            public void setResolution(String str) {
                this.c = str;
            }
        }

        /* loaded from: classes4.dex */
        public static class RemoteCommandFrameWork {
            @SerializedName("enable")

            /* renamed from: a  reason: collision with root package name */
            private Boolean f4303a;
            @SerializedName("min_fw_version")
            private String b;
            @SerializedName("min_android_app_version")
            private String c;
            @SerializedName("min_ios_app_version")
            private String d;

            public Boolean getEnable() {
                return this.f4303a;
            }

            public String getMin_android_app_version() {
                return this.c;
            }

            public String getMin_fw_version() {
                return this.b;
            }

            public String getMin_ios_app_version() {
                return this.d;
            }

            public void setEnable(Boolean bool) {
                this.f4303a = bool;
            }

            public void setMin_android_app_version(String str) {
                this.c = str;
            }

            public void setMin_fw_version(String str) {
                this.b = str;
            }

            public void setMin_ios_app_version(String str) {
                this.d = str;
            }
        }

        public List<String> getDisableAutoHRFor() {
            return this.m;
        }

        public Boolean getDiyWatchFaceSupported() {
            return this.e;
        }

        public String getIcon() {
            return this.h;
        }

        public Boolean getIs1kActivitySupported() {
            return this.k;
        }

        public Boolean getIsDiyWatchFaceSupported() {
            return this.e;
        }

        public Boolean getIsQRCodeOnboardingSupported() {
            return this.q;
        }

        public Boolean getIsWatchDiagnosticsSupported() {
            return this.f;
        }

        public String getLogoType() {
            return this.p;
        }

        public Integer getMinBatteryPerForWatchFaceUpload() {
            return this.n;
        }

        public Integer getMinTimeGapForShowingNextAutoHRPopup() {
            return this.o;
        }

        public String getName() {
            return this.f4301a;
        }

        public QRCode getQrCode() {
            return this.i;
        }

        public RemoteCommandFrameWork getRemoteCommandFrameWork() {
            return this.c;
        }

        public Boolean getRemoteFrameworkSupported() {
            return this.d;
        }

        public List<String> getScanFilter() {
            return this.g;
        }

        public Boolean getSleepAndEnergyScoreSupported() {
            return this.l;
        }

        public Boolean getTapAndPaySupported() {
            return this.j;
        }

        public String getType() {
            return this.b;
        }

        public Boolean getWatchDiagnosticsSupported() {
            return this.f;
        }

        public void setDisableAutoHRFor(List<String> list) {
            this.m = list;
        }

        public void setDiyWatchFaceSupported(Boolean bool) {
            this.e = bool;
        }

        public void setIcon(String str) {
            this.h = str;
        }

        public void setIs1kActivitySupported(Boolean bool) {
            this.k = bool;
        }

        public void setIsDiyWatchFaceSupported(Boolean bool) {
            this.e = bool;
        }

        public void setIsQRCodeOnboardingSupported(Boolean bool) {
            this.q = bool;
        }

        public void setIsWatchDiagnosticsSupported(Boolean bool) {
            this.f = bool;
        }

        public void setLogoType(String str) {
            this.p = str;
        }

        public void setMinBatteryPerForWatchFaceUpload(Integer num) {
            this.n = num;
        }

        public void setMinTimeGapForShowingNextAutoHRPopup(Integer num) {
            this.o = num;
        }

        public void setName(String str) {
            this.f4301a = str;
        }

        public void setQrCode(QRCode qRCode) {
            this.i = qRCode;
        }

        public void setRemoteCommandFrameWork(RemoteCommandFrameWork remoteCommandFrameWork) {
            this.c = remoteCommandFrameWork;
        }

        public void setRemoteFrameworkSupported(Boolean bool) {
            this.d = bool;
        }

        public void setScanFilter(List<String> list) {
            this.g = list;
        }

        public void setSleepAndEnergyScoreSupported(Boolean bool) {
            this.l = bool;
        }

        public void setTapAndPaySupported(Boolean bool) {
            this.j = bool;
        }

        public void setType(String str) {
            this.b = str;
        }

        public void setWatchDiagnosticsSupported(Boolean bool) {
            this.f = bool;
        }

        public String toString() {
            return "DeviceModelsBean{name='" + this.f4301a + "', type='" + this.b + "', remoteCommandFrameWork=" + this.c + ", isRemoteFrameworkSupported=" + this.d + ", isDiyWatchFaceSupported=" + this.e + ", isWatchDiagnosticsSupported=" + this.f + ", scanFilter=" + this.g + ", icon='" + this.h + "', isTapAndPaySupported=" + this.j + ", is1kActivitySupported=" + this.k + ", isSleepAndEnergyScoreSupported=" + this.l + ", disableAutoHRFor=" + this.m + ", minBatteryPerForWatchFaceUpload=" + this.n + ", minTimeGapForShowingNextAutoHRPopup=" + this.o + ", logoType='" + this.p + "'}";
        }
    }

    public List<DeviceCapabilityConfig> getDeviceCapabilityConfigList() {
        return this.deviceCapabilityConfigList;
    }

    public List<DeviceCategories> getDeviceCategories() {
        return this.deviceCategories;
    }

    public List<DeviceModelsBean> getDeviceList() {
        return this.deviceList;
    }

    public List<DeviceModelsBean> getDeviceModels() {
        return this.deviceModels;
    }

    public void setDeviceCapabilityConfigList(List<DeviceCapabilityConfig> list) {
        this.deviceCapabilityConfigList = list;
    }

    public void setDeviceCategories(List<DeviceCategories> list) {
        this.deviceCategories = list;
    }

    public void setDeviceList(List<DeviceModelsBean> list) {
        this.deviceList = list;
    }

    public void setDeviceModels(List<DeviceModelsBean> list) {
        this.deviceModels = list;
    }

    public String toString() {
        return "DeviceRemoteConfig{deviceModels=" + this.deviceModels + ", deviceList=" + this.deviceList + ", deviceCapabilityConfigList=" + this.deviceCapabilityConfigList + ", deviceCategories=" + this.deviceCategories + '}';
    }
}
