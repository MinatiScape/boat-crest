package com.crrepa.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.crrepa.ble.conn.CRPBleDevice;
import com.crrepa.ble.scan.callback.CRPScanCallback;
import com.crrepa.h.c;
import com.crrepa.i0.f;
import com.crrepa.y.a;
import java.util.Set;
/* loaded from: classes9.dex */
public class CRPBleClient {
    public static CRPBleClient d;

    /* renamed from: a  reason: collision with root package name */
    public a f7637a;
    public BluetoothManager b;
    public com.crrepa.j0.a c;

    public CRPBleClient(Context context) {
        f.a(context);
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.b = bluetoothManager;
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.c = new com.crrepa.j0.a(adapter);
        this.f7637a = new a(adapter);
    }

    public static CRPBleClient create(@NonNull Context context) {
        if (d == null) {
            synchronized (CRPBleClient.class) {
                if (d == null) {
                    if (context == null) {
                        throw new IllegalArgumentException("the provided context must not be null!");
                    }
                    d = new CRPBleClient(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    public void cancelScan() {
        this.f7637a.a();
    }

    public CRPBleDevice getBleDevice(String str) {
        BluetoothDevice a2;
        if (TextUtils.isEmpty(str) || (a2 = this.c.a(str)) == null) {
            return null;
        }
        com.crrepa.t.a.b(str);
        return new c(f.a(), a2, this.b);
    }

    public Set<BluetoothDevice> getBondedDevices() {
        return this.c.a();
    }

    public boolean isBluetoothEnable() {
        return this.c.c();
    }

    public boolean scanDevice(CRPScanCallback cRPScanCallback, long j) {
        return this.f7637a.a(cRPScanCallback, j);
    }
}
