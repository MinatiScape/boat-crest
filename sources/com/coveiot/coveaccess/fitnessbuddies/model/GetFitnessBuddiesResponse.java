package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessBuddiesResponse extends CoveApiResponseBaseModel {
    @SerializedName("fitnessBuddies")
    @Expose
    public List<Requests> fitnessBuddies;

    public GetFitnessBuddiesResponse(int i, List<Requests> list) {
        super(i);
        this.fitnessBuddies = null;
        this.fitnessBuddies = list;
    }
}
