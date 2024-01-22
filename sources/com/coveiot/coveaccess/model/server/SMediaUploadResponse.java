package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
/* loaded from: classes8.dex */
public class SMediaUploadResponse {
    private MediaListBean data;
    private String message;
    private String status;

    public MediaListBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(MediaListBean mediaListBean) {
        this.data = mediaListBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "SMediaUploadResponse{data=" + this.data.toString() + ", message='" + this.message + "', status='" + this.status + "'}";
    }
}
