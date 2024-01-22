package com.coveiot.coveaccess.hrv;

import com.coveiot.coveaccess.hrv.model.HrvData;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetHrvDataRes extends CoveApiResponseBaseModel {
    public List<HrvData> mHrvDataList;

    public GetHrvDataRes(int i) {
        super(i);
        this.mHrvDataList = this.mHrvDataList;
    }

    public List<HrvData> getmHrvDataList() {
        return this.mHrvDataList;
    }

    public void setmHrvDataList(List<HrvData> list) {
        this.mHrvDataList = list;
    }

    public String toString() {
        return "GetHrvDataRes{mHrvDataList=" + this.mHrvDataList + '}';
    }
}
