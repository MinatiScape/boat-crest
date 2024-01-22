package com.coveiot.coveaccess.bp;

import com.coveiot.coveaccess.bp.model.BpData;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetBpRateDataRes extends CoveApiResponseBaseModel {
    public List<BpData> bpDataList;

    public GetBpRateDataRes(int i) {
        super(i);
    }

    public List<BpData> getBpDataList() {
        return this.bpDataList;
    }

    public void setBpDataList(List<BpData> list) {
        this.bpDataList = list;
    }

    public String toString() {
        return "GetBpRateDataRes{bpDataList=" + this.bpDataList + '}';
    }
}
