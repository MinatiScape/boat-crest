package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class GetOverallStatsRes extends CoveApiResponseBaseModel {
    private int code;
    public GetOverallStatsResponse getOverallStatsResponse;

    public GetOverallStatsRes(int i, GetOverallStatsResponse getOverallStatsResponse) {
        super(i);
        this.getOverallStatsResponse = getOverallStatsResponse;
    }

    @Override // com.coveiot.coveaccess.model.CoveApiResponseBaseModel
    public int getCode() {
        return this.code;
    }

    public GetOverallStatsResponse getGetOverallStatsResponse() {
        return this.getOverallStatsResponse;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setGetOverallStatsResponse(GetOverallStatsResponse getOverallStatsResponse) {
        this.getOverallStatsResponse = getOverallStatsResponse;
    }
}
