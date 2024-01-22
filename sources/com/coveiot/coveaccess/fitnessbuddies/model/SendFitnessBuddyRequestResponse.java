package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddyRequest;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SendFitnessBuddyRequestResponse extends CoveApiResponseBaseModel {
    @SerializedName("buddyRequests")
    @Expose
    private List<BuddyRequest> buddyRequests;

    public SendFitnessBuddyRequestResponse(int i, List<BuddyRequest> list) {
        super(i);
        this.buddyRequests = null;
        this.buddyRequests = list;
    }

    public List<BuddyRequest> getBuddyRequests() {
        return this.buddyRequests;
    }
}
