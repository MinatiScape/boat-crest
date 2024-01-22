package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGattCallback;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public interface DfuCallback extends DfuController {

    /* loaded from: classes12.dex */
    public static class DfuGattCallback extends BluetoothGattCallback {
        public void onDisconnected() {
        }
    }

    DfuGattCallback getGattCallback();

    void onBondStateChanged(int i);
}
