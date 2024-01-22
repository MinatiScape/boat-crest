package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessBuddiesMessagesResponse extends CoveApiResponseBaseModel {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    public List<Messages> items;

    public GetFitnessBuddiesMessagesResponse(int i, List<Messages> list) {
        super(i);
        this.items = list;
    }
}
