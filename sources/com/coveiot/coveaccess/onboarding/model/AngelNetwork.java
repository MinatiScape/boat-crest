package com.coveiot.coveaccess.onboarding.model;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class AngelNetwork implements Serializable {
    private static final int NOT_RESTRICTED = 0;
    @SerializedName("dateCreated")
    public String dateCreated;
    @SerializedName("dateModified")
    public String dateModified;
    @SerializedName("iconUrl")
    public String iconUrl;
    @SerializedName("id")
    public long id;
    @SerializedName("logoUrl")
    public String logoUrl;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String name;
    @SerializedName("isRestricted")
    public long restricted;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    public long status;

    public AngelNetwork(long j, String str, long j2, String str2, long j3, String str3, String str4) {
        this.id = j;
        this.name = str;
        this.status = j2;
        this.iconUrl = str2;
        this.restricted = j3;
        this.dateCreated = str3;
        this.dateModified = str4;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public String getDateModified() {
        return this.dateModified;
    }

    public String getFabiconUrl() {
        return this.logoUrl;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getStatus() {
        return this.status;
    }

    public boolean isRestricted() {
        return this.restricted != 0;
    }

    public void setFabiconUrl(String str) {
        this.logoUrl = str;
    }
}
