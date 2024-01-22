package no.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import androidx.annotation.NonNull;
import no.nordicsemi.android.dfu.BaseDfuImpl;
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
abstract class BaseButtonlessDfuImpl extends BaseDfuImpl {
    private final ButtonlessBluetoothCallback mBluetoothCallback;

    /* loaded from: classes12.dex */
    public class ButtonlessBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback {
        public ButtonlessBluetoothCallback() {
            super();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            DfuBaseService dfuBaseService = BaseButtonlessDfuImpl.this.mService;
            dfuBaseService.sendLogBroadcast(5, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
            BaseButtonlessDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
            BaseButtonlessDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BaseButtonlessDfuImpl baseButtonlessDfuImpl = BaseButtonlessDfuImpl.this;
            baseButtonlessDfuImpl.mRequestCompleted = true;
            baseButtonlessDfuImpl.notifyLock();
        }
    }

    public BaseButtonlessDfuImpl(@NonNull Intent intent, @NonNull DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        this.mBluetoothCallback = new ButtonlessBluetoothCallback();
    }

    public void finalize(@NonNull Intent intent, boolean z, boolean z2) {
        boolean z3 = false;
        this.mService.refreshDeviceCache(this.mGatt, (z || !intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false)) ? true : true);
        this.mService.close(this.mGatt);
        logi("Restarting to bootloader mode");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, z2);
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
        return this.mBluetoothCallback;
    }
}
