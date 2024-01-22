package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
import java.util.List;
/* loaded from: classes8.dex */
public class SMediaListResponse {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private List<MediaListBean> mediaList;

        public List<MediaListBean> getMediaList() {
            return this.mediaList;
        }

        public void setMediaList(List<MediaListBean> list) {
            this.mediaList = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
