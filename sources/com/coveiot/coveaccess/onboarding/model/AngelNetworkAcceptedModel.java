package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.android.tappy.utils.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class AngelNetworkAcceptedModel implements Serializable {
    @SerializedName("id")
    public long id;
    @SerializedName("organizationDetails")
    public AngelNetwork organizationDetails;
    @SerializedName("organizationId")
    public long organizationId;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    public String userId;

    public AngelNetworkAcceptedModel(long j, String str, long j2, AngelNetwork angelNetwork) {
        this.id = j;
        this.userId = str;
        this.organizationId = j2;
        this.organizationDetails = angelNetwork;
    }

    public long getId() {
        return this.id;
    }

    public AngelNetwork getOrganizationDetails() {
        return this.organizationDetails;
    }

    public long getOrganizationId() {
        return this.organizationId;
    }

    public String getUserId() {
        return this.userId;
    }
}
