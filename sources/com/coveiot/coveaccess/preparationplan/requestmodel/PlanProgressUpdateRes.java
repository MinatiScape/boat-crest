package com.coveiot.coveaccess.preparationplan.requestmodel;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class PlanProgressUpdateRes extends CoveApiResponseBaseModel {
    public String message;

    public PlanProgressUpdateRes(int i) {
        super(i);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
