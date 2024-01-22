package com.coveiot.coveaccess.timeline.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class FetchTimeLineDataRes extends CoveApiResponseBaseModel {
    public List<TimeLineData> timeLineDataList;

    public FetchTimeLineDataRes(int i) {
        super(i);
    }

    public List<TimeLineData> getTimeLineDataList() {
        return this.timeLineDataList;
    }

    public void setTimeLineDataList(List<TimeLineData> list) {
        this.timeLineDataList = list;
    }
}
