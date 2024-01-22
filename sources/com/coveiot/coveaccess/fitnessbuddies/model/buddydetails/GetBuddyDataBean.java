package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class GetBuddyDataBean implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<GetBuddyItems> itemsList;

    public List<GetBuddyItems> getItemsList() {
        return this.itemsList;
    }

    public void setItemsList(List<GetBuddyItems> list) {
        this.itemsList = list;
    }
}
