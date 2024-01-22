package com.coveiot.coveaccess.fitness.config.models.responsemodel;

import java.io.Serializable;
/* loaded from: classes8.dex */
public final class FitnessConfigResponse implements Serializable {
    private String message;
    private String status;

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
