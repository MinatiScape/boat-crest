package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.android.tappy.utils.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class AngelNetworkBelongsToModel implements Serializable {
    @SerializedName("id")
    public long id;
    @SerializedName("locationId")
    public long locationId;
    @SerializedName("organizationId")
    public long organizationId;
    @SerializedName("organizationLocation")
    public AngelNetworkLocation organizationLocation;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    public String userId;

    public long getId() {
        return this.id;
    }

    public long getLocationId() {
        return this.locationId;
    }

    public long getOrganizationId() {
        return this.organizationId;
    }

    public AngelNetworkLocation getOrganizationLocation() {
        return this.organizationLocation;
    }

    public String getUserId() {
        return this.userId;
    }
}
