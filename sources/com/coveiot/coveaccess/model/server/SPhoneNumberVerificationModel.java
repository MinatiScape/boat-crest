package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public class SPhoneNumberVerificationModel {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private String from;
        private String lastUpdatedDate;
        private String smsId;
        private String status;
        private String to;
        private String verificationCode;

        public String getFrom() {
            return this.from;
        }

        public String getLastUpdatedDate() {
            return this.lastUpdatedDate;
        }

        public String getSmsId() {
            return this.smsId;
        }

        public String getStatus() {
            return this.status;
        }

        public String getTo() {
            return this.to;
        }

        public String getVerificationCode() {
            return this.verificationCode;
        }

        public void setFrom(String str) {
            this.from = str;
        }

        public void setLastUpdatedDate(String str) {
            this.lastUpdatedDate = str;
        }

        public void setSmsId(String str) {
            this.smsId = str;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public void setTo(String str) {
            this.to = str;
        }

        public void setVerificationCode(String str) {
            this.verificationCode = str;
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
