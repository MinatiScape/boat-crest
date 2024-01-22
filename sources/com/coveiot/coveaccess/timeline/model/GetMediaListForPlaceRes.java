package com.coveiot.coveaccess.timeline.model;

import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetMediaListForPlaceRes extends CoveApiResponseBaseModel {
    public List<MediaListBean> mediaListBeanList;

    public GetMediaListForPlaceRes(int i) {
        super(i);
    }

    public List<MediaListBean> getMediaListBeanList() {
        return this.mediaListBeanList;
    }

    public void setMediaListBeanList(List<MediaListBean> list) {
        this.mediaListBeanList = list;
    }
}
