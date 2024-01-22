package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.List;
/* loaded from: classes8.dex */
public class SDrowbsinessFeedbackReq {
    @SerializedName("age")
    private String age;
    @SerializedName("deviceType")
    private String deviceType;
    @SerializedName("features")
    private List<FeaturesBean> features;
    @SerializedName("gender")
    private String gender;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    private String height;
    @SerializedName("platform")
    private String platform;
    @SerializedName("ppgType")
    private Integer ppgType;
    @SerializedName("signalStr")
    private String signalStr;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    private String time;
    @SerializedName("weight")
    private String weight;

    /* loaded from: classes8.dex */
    public static class FeaturesBean {
        @SerializedName("estimationAccurate")
        private Integer estimationAccurate;
        @SerializedName("featureType")
        private Integer featureType;
        @SerializedName(d.O)
        private Integer feedback;
        @SerializedName("resultType")
        private Integer resultType;
        @SerializedName("serverData")
        private JsonObject serverData;

        public Integer getEstimationAccurate() {
            return this.estimationAccurate;
        }

        public Integer getFeatureType() {
            return this.featureType;
        }

        public Integer getFeedback() {
            return this.feedback;
        }

        public Integer getResultType() {
            return this.resultType;
        }

        public JsonObject getServerData() {
            return this.serverData;
        }

        public void setEstimationAccurate(Integer num) {
            this.estimationAccurate = num;
        }

        public void setFeatureType(Integer num) {
            this.featureType = num;
        }

        public void setFeedback(Integer num) {
            this.feedback = num;
        }

        public void setResultType(Integer num) {
            this.resultType = num;
        }

        public void setServerData(JsonObject jsonObject) {
            this.serverData = jsonObject;
        }
    }

    public SDrowbsinessFeedbackReq(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Integer num) {
        this.signalStr = str;
        this.age = str2;
        this.gender = str3;
        this.weight = str4;
        this.height = str5;
        this.deviceType = str6;
        this.time = str7;
        this.platform = str8;
        this.ppgType = num;
    }

    public String getAge() {
        return this.age;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public List<FeaturesBean> getFeatures() {
        return this.features;
    }

    public String getGender() {
        return this.gender;
    }

    public String getHeight() {
        return this.height;
    }

    public String getPlatform() {
        return this.platform;
    }

    public Integer getPpgType() {
        return this.ppgType;
    }

    public String getSignalStr() {
        return this.signalStr;
    }

    public String getTime() {
        return this.time;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setAge(String str) {
        this.age = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setFeatures(List<FeaturesBean> list) {
        this.features = list;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public void setPpgType(Integer num) {
        this.ppgType = num;
    }

    public void setSignalStr(String str) {
        this.signalStr = str;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setWeight(String str) {
        this.weight = str;
    }
}
