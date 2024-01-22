package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.jieli.watchtesttool.util.WatchTestConstant;
import java.util.List;
/* loaded from: classes8.dex */
public class SOSSettings {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

    /* renamed from: a  reason: collision with root package name */
    private boolean f6895a;
    @SerializedName(WatchTestConstant.DIR_CONTACTS)
    private List<ContactItem> b;

    /* loaded from: classes8.dex */
    public static class ContactItem {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

        /* renamed from: a  reason: collision with root package name */
        private String f6896a;
        @SerializedName("mobileNumber")
        private String b;

        public String getMobileNumber() {
            return this.b;
        }

        public String getName() {
            return this.f6896a;
        }

        public void setMobileNumber(String str) {
            this.b = str;
        }

        public void setName(String str) {
            this.f6896a = str;
        }

        public String toString() {
            return "ContactItem{name='" + this.f6896a + "', mobileNumber='" + this.b + "'}";
        }
    }

    public List<ContactItem> getContactsList() {
        return this.b;
    }

    public boolean isActive() {
        return this.f6895a;
    }

    public void setActive(boolean z) {
        this.f6895a = z;
    }

    public void setContactsList(List<ContactItem> list) {
        this.b = list;
    }

    public String toString() {
        return "SOSSettings{active=" + this.f6895a + ", contactsList=" + this.b + '}';
    }
}
