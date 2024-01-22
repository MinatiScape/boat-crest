package com.coveiot.coveaccess.userappsetting;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SoftwareUpdateRes implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        @SerializedName("app")
        private AppBean app;
        @SerializedName("appDefaultSettings")
        private DefaultSettingBean appDefaultSettings;
        @SerializedName("firmwares")
        private List<FirmwareBean> firmwares;
        @SerializedName("performAction")
        private String performAction;

        /* loaded from: classes8.dex */
        public static class AppBean implements Serializable {
            @SerializedName("latestVersion")
            private String latestVersion;
            @SerializedName("updateStatus")
            private String updateStatus;
            @SerializedName("updateUrl")
            private String updateUrl;
            @SerializedName("updateVersion")
            private String updateVersion;

            public String getLatestVersion() {
                return this.latestVersion;
            }

            public String getUpdateStatus() {
                return this.updateStatus;
            }

            public String getUpdateUrl() {
                return this.updateUrl;
            }

            public String getUpdateVersion() {
                return this.updateVersion;
            }

            public void setLatestVersion(String str) {
                this.latestVersion = str;
            }

            public void setUpdateStatus(String str) {
                this.updateStatus = str;
            }

            public void setUpdateUrl(String str) {
                this.updateUrl = str;
            }

            public void setUpdateVersion(String str) {
                this.updateVersion = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class DefaultSettingBean implements Serializable {
            @SerializedName("fitness")
            private FitnessBean fitness;
            @SerializedName("timeline")
            private TimelineBean timeline;

            /* loaded from: classes8.dex */
            public static class FitnessBean implements Serializable {
                @SerializedName("uvSuggestionsFile")
                private String uvSuggestionsFile;

                public String getUvSuggestionsFile() {
                    return this.uvSuggestionsFile;
                }

                public void setUvSuggestionsFile(String str) {
                    this.uvSuggestionsFile = str;
                }
            }

            /* loaded from: classes8.dex */
            public static class TimelineBean implements Serializable {
                @SerializedName("dwellRadius")
                private String dwellRadius;
                @SerializedName("dwellThreshold")
                private String dwellThreshold;
                @SerializedName("walkStepsThreshold")
                private String walkStepsThreshold;

                public String getDwellRadius() {
                    return this.dwellRadius;
                }

                public String getDwellThreshold() {
                    return this.dwellThreshold;
                }

                public String getWalkStepsThreshold() {
                    return this.walkStepsThreshold;
                }

                public void setDwellRadius(String str) {
                    this.dwellRadius = str;
                }

                public void setDwellThreshold(String str) {
                    this.dwellThreshold = str;
                }

                public void setWalkStepsThreshold(String str) {
                    this.walkStepsThreshold = str;
                }
            }

            public FitnessBean getFitness() {
                return this.fitness;
            }

            public TimelineBean getTimeline() {
                return this.timeline;
            }

            public void setFitness(FitnessBean fitnessBean) {
                this.fitness = fitnessBean;
            }

            public void setTimeline(TimelineBean timelineBean) {
                this.timeline = timelineBean;
            }
        }

        /* loaded from: classes8.dex */
        public static class FirmwareBean implements Serializable {
            @SerializedName("configUrl")
            private String configUrl;
            @SerializedName("currentVersion")
            private String currentVersion;
            @SerializedName("customerId")
            private String customerId;
            @SerializedName("downloadUrl")
            private String downloadUrl;
            @SerializedName("fileMd5Hash")
            private String fileMd5Hash;
            @SerializedName("latestVersion")
            private String latestVersion;
            @SerializedName("modelNumber")
            private String modelNumber;
            @SerializedName(com.coveiot.android.tappy.utils.Constants.SERIAL_NUMBER)
            private String serialNumber;
            @SerializedName("updateKey")
            private String updateKey;
            @SerializedName("updateStatus")
            private String updateStatus;
            @SerializedName("updateVersion")
            private String updateVersion;

            public String getConfigUrl() {
                return this.configUrl;
            }

            public String getCurrentVersion() {
                return this.currentVersion;
            }

            public String getCustomerId() {
                return this.customerId;
            }

            public String getDownloadUrl() {
                return this.downloadUrl;
            }

            public String getFileMd5Hash() {
                return this.fileMd5Hash;
            }

            public String getLatestVersion() {
                return this.latestVersion;
            }

            public String getModelNumber() {
                return this.modelNumber;
            }

            public String getSerialNumber() {
                return this.serialNumber;
            }

            public String getUpdateKey() {
                return this.updateKey;
            }

            public String getUpdateStatus() {
                return this.updateStatus;
            }

            public String getUpdateVersion() {
                return this.updateVersion;
            }

            public void setConfigUrl(String str) {
                this.configUrl = str;
            }

            public void setCurrentVersion(String str) {
                this.currentVersion = str;
            }

            public void setCustomerId(String str) {
                this.customerId = str;
            }

            public void setDownloadUrl(String str) {
                this.downloadUrl = str;
            }

            public void setFileMd5Hash(String str) {
                this.fileMd5Hash = str;
            }

            public void setLatestVersion(String str) {
                this.latestVersion = str;
            }

            public void setModelNumber(String str) {
                this.modelNumber = str;
            }

            public void setSerialNumber(String str) {
                this.serialNumber = str;
            }

            public void setUpdateKey(String str) {
                this.updateKey = str;
            }

            public void setUpdateStatus(String str) {
                this.updateStatus = str;
            }

            public void setUpdateVersion(String str) {
                this.updateVersion = str;
            }
        }

        public AppBean getApp() {
            return this.app;
        }

        public DefaultSettingBean getAppDefaultSettings() {
            return this.appDefaultSettings;
        }

        public List<FirmwareBean> getFirmwares() {
            return this.firmwares;
        }

        public String getPerformAction() {
            return this.performAction;
        }

        public void setApp(AppBean appBean) {
            this.app = appBean;
        }

        public void setAppDefaultSettings(DefaultSettingBean defaultSettingBean) {
            this.appDefaultSettings = defaultSettingBean;
        }

        public void setFirmwares(List<FirmwareBean> list) {
            this.firmwares = list;
        }

        public void setPerformAction(String str) {
            this.performAction = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
