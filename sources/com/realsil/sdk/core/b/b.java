package com.realsil.sdk.core.b;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl;
import com.realsil.sdk.core.bluetooth.scanner.ScannerCallback;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Locale;
/* loaded from: classes12.dex */
public class b extends com.realsil.sdk.core.b.a {
    public final BroadcastReceiver p;

    /* loaded from: classes12.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"android.bluetooth.device.action.FOUND".equals(action) && !"android.bluetooth.device.action.CLASS_CHANGED".equals(action)) {
                if (!"android.bluetooth.device.action.NAME_CHANGED".equals(action) && !"android.bluetooth.device.action.UUID".equals(action)) {
                    if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(action)) {
                        b.this.a(2);
                        return;
                    } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                        b.this.a(3);
                        return;
                    } else {
                        return;
                    }
                } else if (b.this.h == 2) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) 0);
                    if (bluetoothDevice != null) {
                        if (b.this.b) {
                            ZLogger.v(String.format("%s %s/%s", action, bluetoothDevice.getName(), bluetoothDevice.toString()));
                        }
                        b.this.a(bluetoothDevice, shortExtra, null);
                        return;
                    } else if (b.this.b) {
                        ZLogger.v(String.format("%s", action));
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            short shortExtra2 = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) 0);
            if (bluetoothDevice2 != null) {
                if (b.this.b) {
                    ZLogger.v(String.format("%s %s", action, BluetoothHelper.dumpBluetoothDevice(bluetoothDevice2)));
                }
                b.this.a(bluetoothDevice2, shortExtra2, null);
            } else if (b.this.b) {
                ZLogger.v(String.format("%s", action));
            }
        }
    }

    public b(Context context) {
        this(context, null, null, null);
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean a() {
        if (super.a()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.FOUND");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            intentFilter.addAction("android.bluetooth.device.action.UUID");
            this.c.registerReceiver(this.p, intentFilter);
            ZLogger.v(this.b, "bredr initialized");
            return true;
        }
        return false;
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean e() {
        d();
        if (this.g.isDiscovering()) {
            ZLogger.v(this.b, "cancelDiscovery");
            if (!this.g.cancelDiscovery()) {
                ZLogger.d("cancelDiscovery failed");
                return false;
            }
        }
        a(0);
        return true;
    }

    @Override // com.realsil.sdk.core.b.a
    public void onDestroy() {
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.p);
            } catch (Exception e) {
                ZLogger.e(this.b, e.toString());
            }
        }
        super.onDestroy();
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean startScan() {
        if (c()) {
            if (this.g.isDiscovering()) {
                this.g.cancelDiscovery();
            }
            boolean z = this.f13545a;
            ZLogger.v(z, "startDiscovery for " + this.d.getScanPeriod() + "ms");
            if (!this.g.startDiscovery()) {
                ZLogger.d("startDiscovery failed");
                stopScan();
                return false;
            }
            b();
            return true;
        }
        return true;
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean stopScan() {
        this.n = false;
        return e();
    }

    public b(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this(context, null, scannerParams, scannerCallback);
    }

    public b(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this.p = new a();
        this.c = context.getApplicationContext();
        this.f = handler;
        this.d = scannerParams;
        this.e = scannerCallback;
        a();
    }

    @Override // com.realsil.sdk.core.b.a
    public boolean a(@NonNull BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 18) {
            if (this.d.getScanMode() == 33) {
                if (bluetoothDevice.getType() != 1) {
                    if (this.b) {
                        ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d", Integer.valueOf(bluetoothDevice.getType()), 1));
                    }
                    return false;
                }
            } else if (this.d.getScanMode() == 32 && bluetoothDevice.getType() != 1 && bluetoothDevice.getType() != 3 && bluetoothDevice.getType() != 0) {
                if (this.b) {
                    ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d/%d/%d", Integer.valueOf(bluetoothDevice.getType()), 0, 1, 3));
                }
                return false;
            }
        }
        if (!TextUtils.isEmpty(this.d.getNameFilter())) {
            if (!DataConverter.equals(this.d.getNameFilter(), bluetoothDevice.getName())) {
                if (this.d.isNameFuzzyMatchEnable()) {
                    if (bluetoothDevice.getName() == null || !bluetoothDevice.getName().contains(this.d.getNameFilter())) {
                        if (this.f13545a) {
                            ZLogger.v(String.format("conflict name: %s", bluetoothDevice.getName()));
                        }
                        return false;
                    }
                } else {
                    if (this.f13545a) {
                        ZLogger.v(String.format("conflict name: %s", bluetoothDevice.getName()));
                    }
                    return false;
                }
            }
        } else if (!this.d.isNameNullable() && TextUtils.isEmpty(bluetoothDevice.getName())) {
            if (this.b) {
                ZLogger.v("name is null, ignore");
            }
            return false;
        }
        if (!TextUtils.isEmpty(this.d.getAddressFilter()) && !DataConverter.equals(this.d.getAddressFilter(), bluetoothDevice.getAddress())) {
            if (this.b) {
                ZLogger.v("address not match:" + bluetoothDevice.getAddress());
            }
            return false;
        }
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (1 == this.d.getFilterProfile()) {
            BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
            if (bluetoothClass.getMajorDeviceClass() != 1024 && (BluetoothClassImpl.doesClassMatch(bluetoothClass, 0) || BluetoothClassImpl.doesClassMatch(bluetoothClass, 1))) {
                if (this.b) {
                    ZLogger.v("major device class filter failed");
                }
                return false;
            } else if (bluetoothDevice.getBondState() == 12 && !BluetoothUuid.containsAnyUuid(uuids, BluetoothUuid.HEADSET_PROFILE_UUIDS)) {
                if (this.b) {
                    ZLogger.v("profile filter failed");
                }
                return false;
            }
        }
        if (bluetoothDevice.getBondState() != 12 || BluetoothUuid.containsAllUuids(uuids, this.d.getFilterUuids())) {
            return true;
        }
        if (this.b) {
            ZLogger.v("uuid filter failed");
        }
        return false;
    }
}
