package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class STimelineCardSaveResponse {
    private List<CardItemsBean> data;
    private String message;
    private String status;

    public List<CardItemsBean> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(List<CardItemsBean> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "TimelineCardSaveResponse{data=" + this.data + ", message='" + this.message + "', status='" + this.status + "'}";
    }
}
