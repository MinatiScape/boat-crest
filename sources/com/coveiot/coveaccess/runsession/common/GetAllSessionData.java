package com.coveiot.coveaccess.runsession.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GetAllSessionData implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    public List<Item> items = null;
    @SerializedName("itemsPerPage")
    @Expose
    public Integer itemsPerPage;
    @SerializedName("pageIndex")
    @Expose
    public Integer pageIndex;
}
