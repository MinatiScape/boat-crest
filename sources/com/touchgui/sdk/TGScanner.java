package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGScanDevice;
/* loaded from: classes12.dex */
public interface TGScanner {

    /* loaded from: classes12.dex */
    public interface OnScanListener {
        void onScanFailed(int i);

        void onScanFinished();

        void onScanResult(TGScanDevice tGScanDevice);
    }

    void addOnScanListener(OnScanListener onScanListener);

    boolean isScanning();

    void removeOnScanListener(OnScanListener onScanListener);

    void setContainsKeyword(String str);

    void setFilterEmptyName(boolean z);

    boolean startScan();

    boolean stopScan();
}
