package com.goodix.ble.gr.toolbox.app.libfastdfu;

import com.goodix.ble.gr.libdfu.define.DfuUuid;
import com.goodix.ble.gr.libdfu.dfu.DfuTxRxFactory;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic;
import com.goodix.ble.libble.v2.gb.gatt.GBGattService;
import com.goodix.ble.libble.v2.gb.gatt.profile.GBGattProfile;
import com.goodix.ble.libble.v2.misc.BleTransceiver;
import com.goodix.ble.libcomx.transceiver.Transceiver;
/* loaded from: classes5.dex */
public class DfuProfile implements GBGattProfile {
    public GBRemoteDevice h;
    public GBGattService i;
    public GBGattCharacteristic j;
    public GBGattCharacteristic k;
    public GBGattCharacteristic l;
    public Transceiver m = new DfuTxRxFactory().create();

    @Override // com.goodix.ble.libble.v2.gb.gatt.profile.GBGattProfile
    public boolean bindTo(GBRemoteDevice gBRemoteDevice) {
        if (gBRemoteDevice == null || this.i != null) {
            return false;
        }
        this.h = gBRemoteDevice;
        GBGattService requireService = gBRemoteDevice.requireService(DfuUuid.DFU_SERVICE_UUID, true);
        this.i = requireService;
        this.j = requireService.requireCharacteristic(DfuUuid.DFU_WRITE_CHARACTERISTIC_UUID, true, false, false);
        this.k = this.i.requireCharacteristic(DfuUuid.DFU_RECEIVE_CHARACTERISTIC_UUID, true, false, true);
        this.l = this.i.requireCharacteristic(DfuUuid.DFU_CONTROL_CHARACTERISTIC_UUID, true, false, false);
        new BleTransceiver(this.k).bindTransceiver(this.m).setSender(new BleTransceiver(this.j).getBufferedFrameSender(4096));
        return true;
    }

    public GBGattCharacteristic getCtrl() {
        return this.l;
    }

    public GBGattService getOtas() {
        return this.i;
    }

    public GBRemoteDevice getRemoteDevice() {
        return this.h;
    }

    public GBGattCharacteristic getRx() {
        return this.k;
    }

    public Transceiver getTransceiver() {
        return this.m;
    }
}
