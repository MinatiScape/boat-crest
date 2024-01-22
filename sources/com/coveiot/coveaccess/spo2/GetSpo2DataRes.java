package com.coveiot.coveaccess.spo2;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetSpo2DataRes extends CoveApiResponseBaseModel {
    public List<Spo2Data> spo2DataList;

    public GetSpo2DataRes(int i) {
        super(i);
    }

    public List<Spo2Data> getSpo2DataList() {
        return this.spo2DataList;
    }

    public void setSpo2DataList(List<Spo2Data> list) {
        this.spo2DataList = list;
    }
}
