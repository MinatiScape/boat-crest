package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SGetEGInfo {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("customerId")
        private Integer customerId;
        @SerializedName("egId")
        private Integer egId;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @SerializedName("shortDesc")
        private String shortDesc;
        @SerializedName("tagId")
        private String tagId;

        public String getCreatedDate() {
            return this.createdDate;
        }

        public Integer getCustomerId() {
            return this.customerId;
        }

        public Integer getEgId() {
            return this.egId;
        }

        public String getName() {
            return this.name;
        }

        public String getShortDesc() {
            return this.shortDesc;
        }

        public String getTagId() {
            return this.tagId;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setCustomerId(Integer num) {
            this.customerId = num;
        }

        public void setEgId(Integer num) {
            this.egId = num;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setShortDesc(String str) {
            this.shortDesc = str;
        }

        public void setTagId(String str) {
            this.tagId = str;
        }

        public String toString() {
            return "DataBean{egId=" + this.egId + ", name='" + this.name + "', shortDesc='" + this.shortDesc + "', tagId='" + this.tagId + "', customerId=" + this.customerId + ", createdDate='" + this.createdDate + "'}";
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

    public String toString() {
        return "SGetEGInfo{data=" + this.data + ", message='" + this.message + "', status='" + this.status + "'}";
    }
}
