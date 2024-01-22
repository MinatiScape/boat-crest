package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetSmartAlertAppsData implements Serializable {
    @SerializedName("data")
    @Expose
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public class DataBean implements Serializable {
        @SerializedName("apps")
        @Expose
        private List<App> apps;

        /* loaded from: classes8.dex */
        public class App implements Serializable {
            @SerializedName("configUrl")
            @Expose
            private String configUrl;
            @SerializedName("deviceData")
            @Expose
            private DeviceData deviceData;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            @Expose
            private String name;
            @SerializedName("packageName")
            @Expose
            private String packageName;
            @SerializedName("templateUrl")
            @Expose
            private String templateUrl;

            /* loaded from: classes8.dex */
            public class DeviceData implements Serializable {
                @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_ID)
                @Expose
                private Integer appId;
                @SerializedName("fontColor")
                @Expose
                private String fontColor;
                @SerializedName("fontSize")
                @Expose
                private Integer fontSize;
                @SerializedName("iconUrl")
                @Expose
                private String iconUrl;
                @SerializedName("imageRefId")
                @Expose
                private Integer imageRefId;

                public DeviceData() {
                }

                public Integer getAppId() {
                    return this.appId;
                }

                public String getFontColor() {
                    return this.fontColor;
                }

                public Integer getFontSize() {
                    return this.fontSize;
                }

                public String getIconUrl() {
                    return this.iconUrl;
                }

                public Integer getImageRefId() {
                    return this.imageRefId;
                }

                public void setAppId(Integer num) {
                    this.appId = num;
                }

                public void setFontColor(String str) {
                    this.fontColor = str;
                }

                public void setFontSize(Integer num) {
                    this.fontSize = num;
                }

                public void setIconUrl(String str) {
                    this.iconUrl = str;
                }

                public void setImageRefId(Integer num) {
                    this.imageRefId = num;
                }
            }

            public App() {
            }

            public String getConfigUrl() {
                return this.configUrl;
            }

            public DeviceData getDeviceData() {
                return this.deviceData;
            }

            public String getName() {
                return this.name;
            }

            public String getPackageName() {
                return this.packageName;
            }

            public String getTemplateUrl() {
                return this.templateUrl;
            }

            public void setConfigUrl(String str) {
                this.configUrl = str;
            }

            public void setDeviceData(DeviceData deviceData) {
                this.deviceData = deviceData;
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setPackageName(String str) {
                this.packageName = str;
            }

            public void setTemplateUrl(String str) {
                this.templateUrl = str;
            }
        }

        public DataBean() {
        }

        public List<App> getApps() {
            return this.apps;
        }

        public void setApps(List<App> list) {
            this.apps = list;
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
