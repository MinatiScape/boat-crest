package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothDevice;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.BleRemoteDevice;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes5.dex */
public class BondRemove extends BleBaseProcedure implements IEventListener<BleIntState> {
    public boolean z;

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        BluetoothDevice bluetoothDevice = this.remoteDevice.getBluetoothDevice();
        if (bluetoothDevice == null) {
            str = "Abort removing bond for null device.";
        } else if (bluetoothDevice.getBondState() == 10) {
            str = "Device is not bonded: " + bluetoothDevice.getName();
        } else {
            BleRemoteDevice bleRemoteDevice = this.remoteDevice;
            bleRemoteDevice.expectConnection = false;
            this.z = false;
            if (bleRemoteDevice.isDisconnected()) {
                this.z = true;
                this.gattX.setupReceiver();
            }
            this.gattX.evtBondStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
            if (this.gattX.tryRemoveBond()) {
                return BleBaseProcedure.COMMUNICATION_TIMEOUT;
            }
            str = "Failed to remove bond.";
        }
        finishedWithError(str);
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX = this.gattX;
        if (bleGattX != null) {
            bleGattX.evtBondStateChanged().clear(this);
            if (this.z && this.remoteDevice.isDisconnected()) {
                this.z = false;
                this.gattX.cleanReceiver();
            }
        }
        super.onCleanup();
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, BleIntState bleIntState) {
        int i2 = bleIntState.state;
        if (i2 == 10) {
            finishedWithDone();
        } else if (i2 != 12) {
        } else {
            finishedWithError("Bond has been created.");
        }
    }
}
