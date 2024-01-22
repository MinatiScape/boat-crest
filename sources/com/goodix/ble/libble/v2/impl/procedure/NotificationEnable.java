package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import com.goodix.ble.libble.BleUuid;
import com.goodix.ble.libble.v2.impl.BleCharacteristicX;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes5.dex */
public class NotificationEnable extends BleBaseProcedure implements IEventListener<BleIntState> {
    public boolean A;
    public boolean B = false;
    public a C;
    public BleCharacteristicX z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            ILogger iLogger = NotificationEnable.this.logger;
            if (!bluetoothGattDescriptor.getCharacteristic().getUuid().equals(NotificationEnable.this.z.getUuid())) {
                if (iLogger != null) {
                    iLogger.w("NotificationEnable", "Unexpected onDescriptorWrite(): " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                }
            } else if (!BleUuid.CCCD.equals(bluetoothGattDescriptor.getUuid())) {
                if (iLogger != null) {
                    iLogger.w("NotificationEnable", "Unexpected descriptor while enable CCCD: " + bluetoothGattDescriptor.getUuid());
                }
            } else if (i != 0) {
                if (i == 5 || i == 8 || i == 137) {
                    String str = "Authentication required while modifying CCCD: " + BleGattX.gattStatusToString(i);
                    if (iLogger != null) {
                        iLogger.e("NotificationEnable", str);
                    }
                    NotificationEnable.this.finishedWithError(str);
                    return;
                }
                String str2 = "Error on modifying CCCD: " + BleGattX.gattStatusToString(i);
                if (iLogger != null) {
                    iLogger.e("NotificationEnable", str2);
                }
                NotificationEnable.this.finishedWithError(str2);
            } else {
                byte[] value = bluetoothGattDescriptor.getValue();
                if (value != null && value.length == 2 && value[1] == 0) {
                    byte b = value[0];
                    if (b == 0) {
                        if (NotificationEnable.this.A) {
                            return;
                        }
                        NotificationEnable.this.finishedWithDone();
                    } else if (b == 1) {
                        if (NotificationEnable.this.B || !NotificationEnable.this.A) {
                            return;
                        }
                        NotificationEnable.this.finishedWithDone();
                    } else if (b == 2 && NotificationEnable.this.B && NotificationEnable.this.A) {
                        NotificationEnable.this.finishedWithDone();
                    }
                }
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        BleCharacteristicX bleCharacteristicX = this.z;
        if (bleCharacteristicX == null) {
            str = "Target characteristic is null.";
        } else {
            BluetoothGattCharacteristic gattCharacteristic = bleCharacteristicX.getGattCharacteristic();
            if (gattCharacteristic == null) {
                str = "Target characteristic is not discovered.";
            } else {
                if (((this.B ? 32 : 16) & gattCharacteristic.getProperties()) != 0) {
                    if (!this.gattX.isConnected()) {
                        finishedWithError(this.A ? "Failed to enable notify. The connection is not established." : "Failed to disable notify. The connection is not established.");
                        return 0;
                    }
                    a aVar = new a();
                    this.C = aVar;
                    this.gattX.register(aVar);
                    if (!this.gattX.tryEnableNotification(gattCharacteristic, this.B, this.A)) {
                        finishedWithError(this.A ? "Failed to enable notify." : "Failed to disable notify.");
                        return 0;
                    } else if (Build.VERSION.SDK_INT < 26) {
                        this.gattX.evtBondStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
                        return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                    } else {
                        return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                    }
                }
                str = "Not found required property " + (this.B ? "INDICATE" : "NOTIFY") + " in " + gattCharacteristic.getUuid();
            }
        }
        finishedWithError(str);
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX = this.gattX;
        if (bleGattX != null) {
            bleGattX.evtBondStateChanged().clear(this);
            a aVar = this.C;
            if (aVar != null) {
                this.gattX.remove(aVar);
            }
        }
        super.onCleanup();
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, BleIntState bleIntState) {
        if (bleIntState.state == 12) {
            ILogger iLogger = this.logger;
            if (iLogger != null) {
                iLogger.v("NotificationEnable", "Retry to set notification after bonded");
            }
            if (this.gattX.tryEnableNotification(this.z.getGattCharacteristic(), this.B, this.A)) {
                return;
            }
            finishedWithError(this.A ? "Failed to enable notify after bonded." : "Failed to disable notify after bonded.");
        }
    }

    public void setEnable(boolean z) {
        this.A = z;
    }

    public void setForIndicate() {
        this.B = true;
    }

    public void setTargetCharacteristic(BleCharacteristicX bleCharacteristicX) {
        this.z = bleCharacteristicX;
    }
}
