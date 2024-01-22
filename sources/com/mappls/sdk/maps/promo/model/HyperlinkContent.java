package com.mappls.sdk.maps.promo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
/* loaded from: classes11.dex */
public class HyperlinkContent {
    @SerializedName("title")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f12821a;
    @SerializedName("iconSource")
    @Expose
    private String b;
    @SerializedName("triggerUrl")
    @Expose
    private String c;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    private String d;

    public String getDescription() {
        return this.d;
    }

    public String getIconSource() {
        return this.b;
    }

    public String getTitle() {
        return this.f12821a;
    }

    public String getTriggerUrl() {
        return this.c;
    }

    public void setDescription(String str) {
        this.d = str;
    }

    public void setIconSource(String str) {
        this.b = str;
    }

    public void setTitle(String str) {
        this.f12821a = str;
    }

    public void setTriggerUrl(String str) {
        this.c = str;
    }
}
