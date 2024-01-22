package com.coveiot.coveaccess.fitnessbuddies.model.common;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class FitnessBuddy {
    @SerializedName("dpUrl")
    @Expose
    public String dpUrl;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("mobileNumber")
    @Expose
    public String mobileNumber;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String name;
    @SerializedName("profilePictureName")
    @Expose
    public String profilePictureName;
}
