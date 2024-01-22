package com.coveiot.android.leonardo.websupport.model;

import java.io.Serializable;
/* loaded from: classes5.dex */
public class ContactSupportInfo implements Serializable {
    private DataBean data;
    private String reqId;
    private String reqType;
    private int reqVer;

    /* loaded from: classes5.dex */
    public static class DataBean implements Serializable {
        private String method;
        private String to;

        public String getMethod() {
            return this.method;
        }

        public String getTo() {
            return this.to;
        }

        public void setMethod(String str) {
            this.method = str;
        }

        public void setTo(String str) {
            this.to = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getReqId() {
        return this.reqId;
    }

    public String getReqType() {
        return this.reqType;
    }

    public int getReqVer() {
        return this.reqVer;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public void setReqType(String str) {
        this.reqType = str;
    }

    public void setReqVer(int i) {
        this.reqVer = i;
    }
}
