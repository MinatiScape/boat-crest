package com.coveiot.covepreferences.data;

import java.io.Serializable;
import java.util.HashMap;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SmartAlertAppServerConfData implements Serializable {
    @Nullable
    private String configUrl;
    @Nullable
    private DeviceData deviceData;
    @Nullable
    private HashMap<String, String> jsonFileMap;
    @Nullable
    private String name;
    @Nullable
    private String packageName;
    @Nullable
    private String templateUrl;

    /* loaded from: classes8.dex */
    public static final class DeviceData implements Serializable {
        @Nullable
        private Integer appId;
        @Nullable
        private String fontColor;
        @Nullable
        private Integer fontSize;
        @Nullable
        private String iconUrl;
        @Nullable
        private Integer imageRefId;

        @Nullable
        public final Integer getAppId() {
            return this.appId;
        }

        @Nullable
        public final String getFontColor() {
            return this.fontColor;
        }

        @Nullable
        public final Integer getFontSize() {
            return this.fontSize;
        }

        @Nullable
        public final String getIconUrl() {
            return this.iconUrl;
        }

        @Nullable
        public final Integer getImageRefId() {
            return this.imageRefId;
        }

        public final void setAppId(@Nullable Integer num) {
            this.appId = num;
        }

        public final void setFontColor(@Nullable String str) {
            this.fontColor = str;
        }

        public final void setFontSize(@Nullable Integer num) {
            this.fontSize = num;
        }

        public final void setIconUrl(@Nullable String str) {
            this.iconUrl = str;
        }

        public final void setImageRefId(@Nullable Integer num) {
            this.imageRefId = num;
        }
    }

    @Nullable
    public final String getConfigUrl() {
        return this.configUrl;
    }

    @Nullable
    public final DeviceData getDeviceData() {
        return this.deviceData;
    }

    @Nullable
    public final HashMap<String, String> getJsonFileMap() {
        return this.jsonFileMap;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getPackageName() {
        return this.packageName;
    }

    @Nullable
    public final String getTemplateUrl() {
        return this.templateUrl;
    }

    public final void setConfigUrl(@Nullable String str) {
        this.configUrl = str;
    }

    public final void setDeviceData(@Nullable DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    public final void setJsonFileMap(@Nullable HashMap<String, String> hashMap) {
        this.jsonFileMap = hashMap;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setPackageName(@Nullable String str) {
        this.packageName = str;
    }

    public final void setTemplateUrl(@Nullable String str) {
        this.templateUrl = str;
    }
}
