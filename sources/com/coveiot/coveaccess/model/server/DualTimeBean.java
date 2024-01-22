package com.coveiot.coveaccess.model.server;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class DualTimeBean {
    @SerializedName("timeZones")
    private List<TimeZone> timeZones = null;

    /* loaded from: classes8.dex */
    public static class TimeZone {
        @SerializedName(GeoCodingCriteria.POD_CITY)
        private String city;
        @SerializedName(TypedValues.CycleType.S_WAVE_OFFSET)
        private String offset;
        @SerializedName("preference")
        private int preference;

        public String getCity() {
            return this.city;
        }

        public String getOffset() {
            return this.offset;
        }

        public int getPreference() {
            return this.preference;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setOffset(String str) {
            this.offset = str;
        }

        public void setPreference(int i) {
            this.preference = i;
        }
    }

    public List<TimeZone> getTimeZones() {
        return this.timeZones;
    }

    public void setTimeZones(List<TimeZone> list) {
        this.timeZones = list;
    }
}
