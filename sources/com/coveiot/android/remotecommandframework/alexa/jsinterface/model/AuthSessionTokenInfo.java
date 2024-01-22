package com.coveiot.android.remotecommandframework.alexa.jsinterface.model;
/* loaded from: classes6.dex */
public class AuthSessionTokenInfo {
    private Data data;
    private String reqId;
    private String resType;
    private int resVer;
    private String status;

    /* loaded from: classes6.dex */
    public class Data {
        private String authSessionToken;
        private String ctx;

        public Data() {
        }

        public String getAuthSessionToken() {
            return this.authSessionToken;
        }

        public String getCtx() {
            return this.ctx;
        }

        public void setAuthSessionToken(String str) {
            this.authSessionToken = str;
        }

        public void setCtx(String str) {
            this.ctx = str;
        }
    }

    public Data getData() {
        return this.data;
    }

    public String getReqId() {
        return this.reqId;
    }

    public String getResType() {
        return this.resType;
    }

    public int getResVer() {
        return this.resVer;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public void setResType(String str) {
        this.resType = str;
    }

    public void setResVer(int i) {
        this.resVer = i;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
