package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetSleepData {
    @SerializedName("data")
    private DataBean dataBean;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("fitnessData")
        private List<SleepDataBean> fitnessData;

        public List<SleepDataBean> getFitnessData() {
            return this.fitnessData;
        }

        public void setFitnessData(List<SleepDataBean> list) {
            this.fitnessData = list;
        }
    }

    public DataBean getDataBean() {
        return this.dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
