package com.coveiot.coveaccess.heartrate;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetHeartRateDataRes extends CoveApiResponseBaseModel {
    public List<HeartRateData> heartRateDataList;

    public GetHeartRateDataRes(int i) {
        super(i);
    }

    public List<HeartRateData> getHeartRateDataList() {
        return this.heartRateDataList;
    }

    public void setHeartRateDataList(List<HeartRateData> list) {
        this.heartRateDataList = list;
    }
}
