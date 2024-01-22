package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BloodPressureAdjustPara extends BloodPressureBasePara implements Serializable {
    private static final long serialVersionUID = 1;
    public int diastolic;
    public int systolic;

    public BloodPressureAdjustPara() {
        this.flag = 1;
    }

    public String toString() {
        return "BloodPressureAdjustPara{systolic=" + this.systolic + ", diastolic=" + this.diastolic + '}';
    }
}
