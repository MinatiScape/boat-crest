package com.crrepa.ble.conn.listener;

import java.util.Date;
/* loaded from: classes9.dex */
public interface CRPBleECGChangeListener {
    void onCancel();

    void onECGChange(int[] iArr);

    void onFail();

    void onMeasureComplete();

    void onTransCpmplete(Date date);
}
