package com.coveiot.coveaccess.edgegateway.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class EGLoginResponse extends CoveApiResponseBaseModel {
    private String message;

    public EGLoginResponse(int i) {
        super(i);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "EGLoginResponse{message='" + this.message + "'}";
    }
}
