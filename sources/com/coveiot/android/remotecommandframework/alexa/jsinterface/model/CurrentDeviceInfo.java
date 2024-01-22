package com.coveiot.android.remotecommandframework.alexa.jsinterface.model;
/* loaded from: classes6.dex */
public class CurrentDeviceInfo {
    private Data data;
    private String reqId;
    private String resType;
    private Integer resVer;
    private String status;

    /* loaded from: classes6.dex */
    public class Data {
        private Boolean alexaSupported;
        private Integer customerId;
        private String modelNumber;
        private String serialNumber;
        private Long userDeviceId;

        public Data() {
        }

        public Boolean getAlexaSupported() {
            return this.alexaSupported;
        }

        public Integer getCustomerId() {
            return this.customerId;
        }

        public String getModelNumber() {
            return this.modelNumber;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public Long getUserDeviceId() {
            return this.userDeviceId;
        }

        public void setAlexaSupported(Boolean bool) {
            this.alexaSupported = bool;
        }

        public void setCustomerId(Integer num) {
            this.customerId = num;
        }

        public void setModelNumber(String str) {
            this.modelNumber = str;
        }

        public void setSerialNumber(String str) {
            this.serialNumber = str;
        }

        public void setUserDeviceId(Long l) {
            this.userDeviceId = l;
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

    public Integer getResVer() {
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

    public void setResVer(Integer num) {
        this.resVer = num;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
