package com.coveiot.coveaccess.breachalert;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class SaveLocationBreachInfoResponse extends CoveApiResponseBaseModel {
    public String message;
    public String status;

    public SaveLocationBreachInfoResponse(int i) {
        super(i);
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}