package com.polidea.rxandroidble2.internal.util;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public class RxBleAdapterWrapper {
    public static BleException b = new BleException("bluetoothAdapter is null");

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothAdapter f13522a;

    @Inject
    public RxBleAdapterWrapper(@Nullable BluetoothAdapter bluetoothAdapter) {
        this.f13522a = bluetoothAdapter;
    }

    public Set<BluetoothDevice> getBondedDevices() {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getBondedDevices();
        }
        throw b;
    }

    public BluetoothDevice getRemoteDevice(String str) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getRemoteDevice(str);
        }
        throw b;
    }

    public boolean hasBluetoothAdapter() {
        return this.f13522a != null;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    @TargetApi(21)
    public void startLeScan(List<ScanFilter> list, ScanSettings scanSettings, ScanCallback scanCallback) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getBluetoothLeScanner().startScan(list, scanSettings, scanCallback);
            return;
        }
        throw b;
    }

    public boolean startLegacyLeScan(BluetoothAdapter.LeScanCallback leScanCallback) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.startLeScan(leScanCallback);
        }
        throw b;
    }

    @RequiresApi(26)
    public void stopLeScan(PendingIntent pendingIntent) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getBluetoothLeScanner().stopScan(pendingIntent);
            return;
        }
        throw b;
    }

    public void stopLegacyLeScan(BluetoothAdapter.LeScanCallback leScanCallback) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.stopLeScan(leScanCallback);
            return;
        }
        throw b;
    }

    @RequiresApi(26)
    public int startLeScan(List<ScanFilter> list, ScanSettings scanSettings, PendingIntent pendingIntent) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getBluetoothLeScanner().startScan(list, scanSettings, pendingIntent);
        }
        throw b;
    }

    @TargetApi(21)
    public void stopLeScan(ScanCallback scanCallback) {
        BluetoothAdapter bluetoothAdapter = this.f13522a;
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                RxBleLog.v("BluetoothAdapter is disabled, calling BluetoothLeScanner.stopScan(ScanCallback) may cause IllegalStateException", new Object[0]);
                return;
            }
            BluetoothLeScanner bluetoothLeScanner = this.f13522a.getBluetoothLeScanner();
            if (bluetoothLeScanner == null) {
                RxBleLog.w("Cannot call BluetoothLeScanner.stopScan(ScanCallback) on 'null' reference; BluetoothAdapter.isEnabled() == %b", Boolean.valueOf(this.f13522a.isEnabled()));
                return;
            } else {
                bluetoothLeScanner.stopScan(scanCallback);
                return;
            }
        }
        throw b;
    }
}
