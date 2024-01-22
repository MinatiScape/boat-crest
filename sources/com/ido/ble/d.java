package com.ido.ble;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.common.e;
import com.ido.ble.firmware.log.f;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d {

    /* loaded from: classes11.dex */
    public static class a implements DeviceGattCallBack.ICallBack {
        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value == null || value.length == 0) {
                return;
            }
            if (f.b().a(value)) {
                f.b().b(value);
            } else if (value.length >= 20 || value[0] == 51) {
                u.a(value);
            } else {
                byte[] bArr = new byte[20];
                e.a(value, bArr);
                u.a(bArr);
            }
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            u.C();
        }
    }

    private static String a() {
        return e.a().getFilesDir().getAbsolutePath();
    }

    public static void a(InitParam initParam) {
        a(initParam.soJinLogSavePath);
        u.b();
        c();
        b();
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            u.a(true, false, "".getBytes());
        } else {
            u.a(true, true, str.getBytes());
        }
        u.c(a());
    }

    private static void b() {
        com.ido.ble.callback.b.N().a(new a());
    }

    private static void c() {
        int i;
        if (com.ido.ble.bluetooth.a.g()) {
            LogTool.d(com.ido.ble.logs.a.b, "[SoLibInit] setMode(SYS_MODE_SET_BIND)");
            i = 1;
        } else {
            LogTool.d(com.ido.ble.logs.a.b, "[SoLibInit] setMode(SYS_MODE_SET_NOBIND)");
            i = 0;
        }
        u.b(i);
    }
}
