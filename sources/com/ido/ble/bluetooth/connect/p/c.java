package com.ido.ble.bluetooth.connect.p;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
/* loaded from: classes11.dex */
public abstract class c extends com.ido.ble.bluetooth.connect.p.a {

    /* renamed from: a  reason: collision with root package name */
    private BluetoothAdapter.LeScanCallback f12089a = new a();

    /* loaded from: classes11.dex */
    public class a implements BluetoothAdapter.LeScanCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            c.this.a(bluetoothDevice, i, bArr);
        }
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void a() {
        BluetoothAdapter.getDefaultAdapter().startLeScan(this.f12089a);
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void b() {
        BluetoothAdapter.getDefaultAdapter().stopLeScan(this.f12089a);
    }
}
