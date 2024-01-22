package com.ido.ble.custom.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.f.a;
import com.ido.ble.bluetooth.f.f;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.custom.CustomBLEUtils;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.custom.connect.CustomGattCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class CustomGattCallBackImp implements CustomGattCallBack.ICallBack {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private String lastConnectedMacAddress = "";

    private void enableHealthNotify(final BluetoothGatt bluetoothGatt) {
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "start to enablePeerDeviceNotifyHealth...");
        if (a.a(bluetoothGatt, true)) {
            LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyHealth ok");
            return;
        }
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyHealth failed, retry...");
        mHandler.postDelayed(new Runnable() { // from class: com.ido.ble.custom.connect.CustomGattCallBackImp.3
            @Override // java.lang.Runnable
            public void run() {
                if (a.a(bluetoothGatt, true)) {
                    LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyHealth reEnable ok");
                    return;
                }
                LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyHealth reEnable failed");
                CustomGattCallBackImp.this.enableNotifyFailed();
            }
        }, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableNormalNotify(final BluetoothGatt bluetoothGatt) {
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "start to enablePeerDeviceNotifyNormal...");
        if (a.b(bluetoothGatt, true)) {
            LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyNormal ok");
            return;
        }
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyNormal failed, retry...");
        mHandler.postDelayed(new Runnable() { // from class: com.ido.ble.custom.connect.CustomGattCallBackImp.2
            @Override // java.lang.Runnable
            public void run() {
                if (a.b(bluetoothGatt, true)) {
                    LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyNormal reEnable ok");
                    return;
                }
                CustomGattCallBackImp.this.enableNotifyFailed();
                LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "enablePeerDeviceNotifyNormal reEnable failed");
            }
        }, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableNotifyFailed() {
        CustomConfig.getConfig().getIEnableNotifyCallback().onEnableNotify(false);
    }

    private void enableNotifySuccess(BluetoothGatt bluetoothGatt) {
        String address = bluetoothGatt.getDevice().getAddress();
        if (!TextUtils.isEmpty(address) && !this.lastConnectedMacAddress.equals(address)) {
            this.lastConnectedMacAddress = address;
            CustomBytesDataSendManager.getManager().reset();
        }
        CustomConfig.getConfig().getIEnableNotifyCallback().onEnableNotify(true);
    }

    @Override // com.ido.ble.custom.connect.CustomGattCallBack.ICallBack
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        CustomBytesDataSendManager.receiverDeviceData(bluetoothGattCharacteristic.getValue());
        DeviceGattCallBack.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
    }

    @Override // com.ido.ble.custom.connect.CustomGattCallBack.ICallBack
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        CustomBytesDataSendManager.getManager().deviceResponseOnLastSend(bluetoothGattCharacteristic.getValue(), i);
        DeviceGattCallBack.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
    }

    @Override // com.ido.ble.custom.connect.CustomGattCallBack.ICallBack
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (f.c.equals(bluetoothGattDescriptor.getUuid())) {
            if (i != 0 || bluetoothGattDescriptor.getValue()[0] != 1) {
                enableNotifyFailed();
            } else if (f.j.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                enableHealthNotify(bluetoothGatt);
            } else if (f.l.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                enableNotifySuccess(bluetoothGatt);
            }
        }
    }

    @Override // com.ido.ble.custom.connect.CustomGattCallBack.ICallBack
    public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, int i) {
        if (i != 0) {
            LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "[BaseConnect:servicesDiscovered()] discoverServices failed");
            return;
        }
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "[BaseConnect:servicesDiscovered()] discoverServices ok!");
        mHandler.postDelayed(new Runnable() { // from class: com.ido.ble.custom.connect.CustomGattCallBackImp.1
            @Override // java.lang.Runnable
            public void run() {
                CustomGattCallBackImp.this.enableNormalNotify(bluetoothGatt);
            }
        }, 100L);
    }
}
