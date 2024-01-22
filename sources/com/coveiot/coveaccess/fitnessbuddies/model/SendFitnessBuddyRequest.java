package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SendFitnessBuddyRequest extends CoveApiRequestBaseModel {
    @SerializedName("clovers")
    @Expose
    private List<Buddy> buddies;

    /* loaded from: classes8.dex */
    public static class Buddy {
        @SerializedName("mobileNumber")
        @Expose
        public String mobileNumber;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        public String name;

        public Buddy(String str, String str2) {
            this.name = str;
            this.mobileNumber = str2;
        }
    }

    public SendFitnessBuddyRequest(List<Buddy> list) {
        this.buddies = null;
        this.buddies = list;
    }

    public List<Buddy> getBuddies() {
        return this.buddies;
    }
}
