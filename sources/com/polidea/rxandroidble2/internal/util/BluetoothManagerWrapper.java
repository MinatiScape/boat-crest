package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import bleshadow.javax.inject.Inject;
import java.util.List;
/* loaded from: classes12.dex */
public class BluetoothManagerWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothManager f13500a;

    @Inject
    public BluetoothManagerWrapper(BluetoothManager bluetoothManager) {
        this.f13500a = bluetoothManager;
    }

    public List<BluetoothDevice> getConnectedPeripherals() {
        return this.f13500a.getConnectedDevices(8);
    }
}
