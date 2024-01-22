package com.coveiot.coveaccess.userappsetting;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveNotificationSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public DndBean f6845a;
    public SmsBean b;
    public CallBean c;
    public AppBean d;

    /* loaded from: classes8.dex */
    public static class AppBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6846a;
        @SerializedName("androidApps")
        private List<AndroidApps> b;
        public List<Apps> c;

        /* loaded from: classes8.dex */
        public static class AndroidApps {
            @SerializedName("package")

            /* renamed from: a  reason: collision with root package name */
            private String f6847a;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private Boolean b;

            public Boolean getActive() {
                return this.b;
            }

            public String getPackageX() {
                return this.f6847a;
            }

            public void setActive(Boolean bool) {
                this.b = bool;
            }

            public void setPackageX(String str) {
                this.f6847a = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class Apps {

            /* renamed from: a  reason: collision with root package name */
            public String f6848a;
            public Boolean b;

            public Boolean getActive() {
                return this.b;
            }

            public String getAppId() {
                return this.f6848a;
            }

            public void setActive(Boolean bool) {
                this.b = bool;
            }

            public void setAppId(String str) {
                this.f6848a = str;
            }
        }

        public List<AndroidApps> getAndroidApps() {
            return this.b;
        }

        public List<Apps> getApps() {
            return this.c;
        }

        public boolean isEnableAll() {
            return this.f6846a;
        }

        public void setAndroidApps(List<AndroidApps> list) {
            this.b = list;
        }

        public void setApps(List<Apps> list) {
            this.c = list;
        }

        public void setEnableAll(boolean z) {
            this.f6846a = z;
        }
    }

    /* loaded from: classes8.dex */
    public static class CallBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6849a;
        public List<FavContactsBean> b;

        /* loaded from: classes8.dex */
        public static class FavContactsBean {

            /* renamed from: a  reason: collision with root package name */
            public String f6850a;
            public String b;
            public int c;

            public int getHourIndex() {
                return this.c;
            }

            public String getMobileNumber() {
                return this.f6850a;
            }

            public String getName() {
                return this.b;
            }

            public void setHourIndex(int i) {
                this.c = i;
            }

            public void setMobileNumber(String str) {
                this.f6850a = str;
            }

            public void setName(String str) {
                this.b = str;
            }
        }

        public List<FavContactsBean> getFavContacts() {
            return this.b;
        }

        public boolean isActive() {
            return this.f6849a;
        }

        public void setActive(boolean z) {
            this.f6849a = z;
        }

        public void setFavContacts(List<FavContactsBean> list) {
            this.b = list;
        }
    }

    /* loaded from: classes8.dex */
    public static class DndBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6851a;

        public boolean isActive() {
            return this.f6851a;
        }

        public void setActive(boolean z) {
            this.f6851a = z;
        }
    }

    /* loaded from: classes8.dex */
    public static class SmsBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6852a;

        public boolean isActive() {
            return this.f6852a;
        }

        public void setActive(boolean z) {
            this.f6852a = z;
        }
    }

    public CallBean getCall() {
        return this.c;
    }

    public DndBean getDnd() {
        return this.f6845a;
    }

    public AppBean getOtherApps() {
        return this.d;
    }

    public SmsBean getSms() {
        return this.b;
    }

    public void setCall(CallBean callBean) {
        this.c = callBean;
    }

    public void setDnd(DndBean dndBean) {
        this.f6845a = dndBean;
    }

    public void setOtherApps(AppBean appBean) {
        this.d = appBean;
    }

    public void setSms(SmsBean smsBean) {
        this.b = smsBean;
    }
}
