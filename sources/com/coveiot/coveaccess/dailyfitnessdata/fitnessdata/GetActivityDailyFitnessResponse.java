package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetActivityDailyFitnessResponse extends CoveApiResponseBaseModel {
    private int code;
    public List<FitnessDatum> requestData;

    public GetActivityDailyFitnessResponse(int i, List<FitnessDatum> list) {
        super(i);
        this.requestData = list;
    }

    @Override // com.coveiot.coveaccess.model.CoveApiResponseBaseModel
    public int getCode() {
        return this.code;
    }

    public List<FitnessDatum> getRequestData() {
        return this.requestData;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setRequestData(List<FitnessDatum> list) {
        this.requestData = list;
    }
}
