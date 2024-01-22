package com.coveiot.coveaccess.stress;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.coveiot.coveaccess.model.server.PeriodicStressData;
import java.util.List;
/* loaded from: classes8.dex */
public class GetStressDataRes extends CoveApiResponseBaseModel {
    public List<PeriodicStressData> periodicStressDataList;

    public GetStressDataRes(int i) {
        super(i);
    }

    public List<PeriodicStressData> getStressDataList() {
        return this.periodicStressDataList;
    }

    public void setStressDataList(List<PeriodicStressData> list) {
        this.periodicStressDataList = list;
    }

    public String toString() {
        return "GetStressDataRes{periodicStressDataList=" + this.periodicStressDataList + '}';
    }
}
