package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetDailyFitnessAggregateData extends CoveApiResponseBaseModel {
    private int code;
    public List<FitnessDataAggregate> fitnessDataAggregates;

    public GetDailyFitnessAggregateData(int i, List<FitnessDataAggregate> list) {
        super(i);
        this.fitnessDataAggregates = list;
    }

    @Override // com.coveiot.coveaccess.model.CoveApiResponseBaseModel
    public int getCode() {
        return this.code;
    }

    public List<FitnessDataAggregate> getFitnessDataAggregates() {
        return this.fitnessDataAggregates;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setFitnessDataAggregates(List<FitnessDataAggregate> list) {
        this.fitnessDataAggregates = list;
    }
}
