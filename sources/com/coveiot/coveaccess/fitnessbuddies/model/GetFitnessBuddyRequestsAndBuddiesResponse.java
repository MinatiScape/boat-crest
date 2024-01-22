package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessBuddyRequestsAndBuddiesResponse extends CoveApiResponseBaseModel {
    @SerializedName("buddies")
    @Expose
    public List<Requests> buddies;
    @SerializedName("receivedRequests")
    @Expose
    public List<Requests> receivedRequests;
    @SerializedName("sentRequests")
    @Expose
    public List<Requests> sentRequests;

    public GetFitnessBuddyRequestsAndBuddiesResponse(int i, List<Requests> list, List<Requests> list2, List<Requests> list3) {
        super(i);
        this.sentRequests = null;
        this.receivedRequests = null;
        this.buddies = null;
        this.sentRequests = list;
        this.receivedRequests = list2;
        this.buddies = list3;
    }
}
