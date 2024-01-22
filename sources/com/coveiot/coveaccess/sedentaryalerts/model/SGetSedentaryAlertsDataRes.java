package com.coveiot.coveaccess.sedentaryalerts.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetSedentaryAlertsDataRes {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private DataBean f6700a;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String b;
    @SerializedName(Constants.KEY_MESSAGE)
    private String c;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("fitnessData")

        /* renamed from: a  reason: collision with root package name */
        private List<SedentaryAlertsDataBean> f6701a;

        public List<SedentaryAlertsDataBean> getSedentaryAlertsDataBeans() {
            return this.f6701a;
        }

        public void setSedentaryAlertsDataBeans(List<SedentaryAlertsDataBean> list) {
            this.f6701a = list;
        }
    }

    public DataBean getData() {
        return this.f6700a;
    }

    public String getMessage() {
        return this.c;
    }

    public String getStatus() {
        return this.b;
    }

    public void setData(DataBean dataBean) {
        this.f6700a = dataBean;
    }

    public void setMessage(String str) {
        this.c = str;
    }

    public void setStatus(String str) {
        this.b = str;
    }
}
