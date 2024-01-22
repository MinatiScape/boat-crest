package com.ido.ble.business.multidevice;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
/* loaded from: classes11.dex */
class a implements ConnectCallBack.ICallBack {
    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onConnectBreak(String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onConnectStart(String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onConnectSuccess(String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onConnecting(String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onDeviceInNotBindStatus(String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onInDfuMode(BLEDevice bLEDevice) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onInitCompleted(String str) {
    }

    @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
    public void onRetry(int i, String str) {
    }
}
