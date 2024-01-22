package com.coveiot.coveaccess.boatcoins.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class CoinsDataRequest {
    @SerializedName("recipients")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Recipients> f6431a;

    /* loaded from: classes8.dex */
    public static class Recipients {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

        /* renamed from: a  reason: collision with root package name */
        private String f6432a;
        @SerializedName("mobileNumber")
        private String b;
        @SerializedName("coins")
        private Integer c;

        public Recipients(String str, String str2, Integer num) {
            this.f6432a = str;
            this.b = str2;
            this.c = num;
        }

        public Integer getCoins() {
            return this.c;
        }

        public String getMobileNumber() {
            return this.b;
        }

        public String getName() {
            return this.f6432a;
        }

        public void setCoins(Integer num) {
            this.c = num;
        }

        public void setMobileNumber(String str) {
            this.b = str;
        }

        public void setName(String str) {
            this.f6432a = str;
        }
    }

    public CoinsDataRequest(List<Recipients> list) {
        this.f6431a = null;
        this.f6431a = list;
    }

    public List<Recipients> getRecipients() {
        return this.f6431a;
    }

    public void setRecipients(List<Recipients> list) {
        this.f6431a = list;
    }
}
