package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleGetDiagnosticFeatureTest implements Serializable {
    public int deploy;
    public int feature;
    public int status;

    public int getDeploy() {
        return this.deploy;
    }

    public int getFeature() {
        return this.feature;
    }

    public int getStatus() {
        return this.status;
    }

    public void setDeploy(int i) {
        this.deploy = i;
    }

    public void setFeature(int i) {
        this.feature = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        return "BleGetDiagnosticFeatureTest{deploy=" + this.deploy + ", feature=" + this.feature + ", status=" + this.status + '}';
    }
}
