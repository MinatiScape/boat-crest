package com.coveiot.android.weathersdk.server.network.model;

import com.coveiot.android.femalewellness.wellnesscalendar.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
/* loaded from: classes8.dex */
public class SWeather {
    @SerializedName("id")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Integer f6233a;
    @SerializedName(Constants.MAIN_TAG)
    @Expose
    public String b;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    public String c;
    @SerializedName(com.clevertap.android.sdk.Constants.KEY_ICON)
    @Expose
    public String d;

    public String getDescription() {
        return this.c;
    }

    public String getIcon() {
        return this.d;
    }

    public Integer getId() {
        return this.f6233a;
    }

    public String getMain() {
        return this.b;
    }

    public void setDescription(String str) {
        this.c = str;
    }

    public void setIcon(String str) {
        this.d = str;
    }

    public void setId(Integer num) {
        this.f6233a = num;
    }

    public void setMain(String str) {
        this.b = str;
    }
}
