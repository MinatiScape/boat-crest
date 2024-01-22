package com.coveiot.coveaccess.userappsetting;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveDualTimeZoneSettingsReq {
    @SerializedName("timeZones")

    /* renamed from: a  reason: collision with root package name */
    private List<TimeZone> f6841a = null;

    /* loaded from: classes8.dex */
    public static class TimeZone {
        @SerializedName("preference")

        /* renamed from: a  reason: collision with root package name */
        private int f6842a;
        @SerializedName(GeoCodingCriteria.POD_CITY)
        private String b;
        @SerializedName(TypedValues.CycleType.S_WAVE_OFFSET)
        private String c;

        public String getCity() {
            return this.b;
        }

        public String getOffset() {
            return this.c;
        }

        public int getPreference() {
            return this.f6842a;
        }

        public void setCity(String str) {
            this.b = str;
        }

        public void setOffset(String str) {
            this.c = str;
        }

        public void setPreference(int i) {
            this.f6842a = i;
        }
    }

    public List<TimeZone> getTimeZones() {
        return this.f6841a;
    }

    public void setTimeZones(List<TimeZone> list) {
        this.f6841a = list;
    }
}
