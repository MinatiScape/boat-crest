package com.realsil.sdk.dfu.p;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.m.a;
import com.realsil.sdk.dfu.m.f;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.r.g;
import com.realsil.sdk.dfu.utils.ConnectParams;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
/* loaded from: classes12.dex */
public class a extends com.realsil.sdk.dfu.m.a {
    public BluetoothGattCharacteristic B;
    public BluetoothGattCharacteristic C;
    public BluetoothGattCharacteristic D;

    public a() {
        this.h = 20;
    }

    @Override // com.realsil.sdk.dfu.m.a
    public void a(ConnectParams connectParams, String str, BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, BluetoothGattService bluetoothGattService2, a.c cVar) {
        super.a(connectParams, str, bluetoothGatt, bluetoothGattService, bluetoothGattService2, cVar);
        k();
        j();
    }

    @Override // com.realsil.sdk.dfu.m.a
    public void g() {
        super.g();
        b(257);
        if (this.l != null) {
            b(258);
            boolean a2 = a(this.l);
            ZLogger.v(false, "read battery level :" + a2);
        }
        if (this.m != null) {
            b(259);
            boolean a3 = a(this.m);
            ZLogger.v(false, "read PnP_ID :" + a3);
        }
        if (this.D != null) {
            b(260);
            boolean a4 = a(this.D);
            ZLogger.v(false, "read device info :" + a4);
            if (!a4) {
                this.n.clear();
                b(2);
                return;
            }
        }
        if (this.C != null) {
            b(261);
            boolean a5 = a(this.C);
            ZLogger.v(false, "read device mac :" + a5);
        }
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.n) {
            int shortValue = BluetoothUuid.toShortValue(bluetoothGattCharacteristic.getUuid());
            ZLogger.v(false, String.format("uuidShortValue=0x%4x", Integer.valueOf(shortValue)));
            if (shortValue >= 65472 && shortValue <= 65487) {
                b(DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR);
                boolean a6 = a(bluetoothGattCharacteristic);
                ZLogger.v(false, "read debug info :" + a6);
            } else if (shortValue >= 65504 && shortValue <= 65519) {
                b(DfuException.ERROR_WRITE_CHARAC_ERROR);
                boolean a7 = a(bluetoothGattCharacteristic);
                ZLogger.v(false, "read image version :" + a7);
            } else if (shortValue >= 65524 && shortValue <= 65524) {
                b(DfuException.ERROR_WRITE_CHARAC_ERROR);
                boolean a8 = a(bluetoothGattCharacteristic);
                ZLogger.v(false, "read section size :" + a8);
            }
        }
        l();
        this.n.clear();
        b(1);
    }

    public final void j() {
        if (this.k != null) {
            this.p.add(new OtaModeInfo(16));
        }
        if (this.B != null) {
            this.p.add(new OtaModeInfo(0));
        }
    }

    public final void k() {
        BluetoothGattService bluetoothGattService = this.j;
        if (bluetoothGattService == null) {
            ZLogger.d("mOtaService is null");
            return;
        }
        UUID uuid = g.f13647a;
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        this.B = characteristic;
        if (characteristic == null) {
            ZLogger.d("OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC not found");
        } else {
            ZLogger.v(true, "find OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = " + uuid);
        }
        BluetoothGattService bluetoothGattService2 = this.j;
        UUID uuid2 = g.b;
        BluetoothGattCharacteristic characteristic2 = bluetoothGattService2.getCharacteristic(uuid2);
        this.C = characteristic2;
        if (characteristic2 == null) {
            ZLogger.d("OTA_DEVICE_MAC_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.d("find OTA_DEVICE_MAC_CHARACTERISTIC_UUID = " + uuid2);
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.C.getProperties()));
        }
        BluetoothGattCharacteristic characteristic3 = this.j.getCharacteristic(g.c);
        this.D = characteristic3;
        if (characteristic3 == null) {
            ZLogger.d("OTA_DEVICE_INFO_CHARACTERISTIC_UUID not found");
        }
        int i = 65472;
        while (true) {
            if (i > 65487) {
                break;
            }
            UUID fromShortValue = BluetoothUuid.fromShortValue(i);
            BluetoothGattCharacteristic characteristic4 = this.j.getCharacteristic(fromShortValue);
            if (characteristic4 == null) {
                ZLogger.d("not found debug characteristic:" + fromShortValue.toString());
                break;
            }
            ZLogger.d(true, "find debug characteristic: " + fromShortValue.toString());
            this.n.add(characteristic4);
            i++;
        }
        int i2 = 65504;
        while (true) {
            if (i2 > 65519) {
                break;
            }
            UUID fromShortValue2 = BluetoothUuid.fromShortValue(i2);
            BluetoothGattCharacteristic characteristic5 = this.j.getCharacteristic(fromShortValue2);
            if (characteristic5 == null) {
                ZLogger.d(false, "not found image version characteristic:" + fromShortValue2.toString());
                break;
            }
            ZLogger.d(false, "find image version characteristic: " + fromShortValue2.toString());
            this.n.add(characteristic5);
            i2++;
        }
        for (int i3 = 65524; i3 <= 65526; i3++) {
            UUID fromShortValue3 = BluetoothUuid.fromShortValue(i3);
            BluetoothGattCharacteristic characteristic6 = this.j.getCharacteristic(fromShortValue3);
            if (characteristic6 == null) {
                ZLogger.v(false, "not found image session size characteristic:" + fromShortValue3.toString());
                return;
            }
            ZLogger.v(false, "find image session size characteristic: " + fromShortValue3.toString());
            this.n.add(characteristic6);
        }
    }

    public final void l() {
        this.p.clear();
        if (this.k != null) {
            this.p.add(new OtaModeInfo(16));
        }
        if (!b().leNormalModeSupported || this.B == null) {
            return;
        }
        this.p.add(new OtaModeInfo(0));
    }

    @Override // com.realsil.sdk.dfu.m.a
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.a(bluetoothGatt, bluetoothGattCharacteristic, i);
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (i == 0) {
            int length = value != null ? value.length : 0;
            if (f.a.b.equals(uuid)) {
                a(value);
                h();
                return;
            } else if (f.b.e.equals(uuid)) {
                ZLogger.v("PNP_ID: " + DataConverter.bytes2Hex(value));
                b().setPnpId(value);
                h();
                return;
            } else if (g.c.equals(uuid)) {
                b().parseX0014(value);
                h();
                return;
            } else if (g.b.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer wrap = ByteBuffer.wrap(value);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    if (length >= 6) {
                        byte[] bArr = new byte[6];
                        wrap.get(bArr, 0, 6);
                        b().setDeviceMac(bArr);
                    }
                }
                h();
                return;
            } else {
                int shortValue = BluetoothUuid.toShortValue(uuid);
                if (shortValue >= 65504 && shortValue <= 65519) {
                    b().appendImageVersionBytes(value);
                } else if (shortValue >= 65472 && shortValue <= 65487) {
                    b().appendDebugCharacteristicInfo(shortValue, value);
                } else if (shortValue >= 65524 && shortValue <= 65526) {
                    b().appendImageSectionSizeBytes(value);
                }
                h();
                return;
            }
        }
        ZLogger.w(true, "Characteristic read error: " + i);
        if (g.c.equals(uuid)) {
            b(2);
            h();
            return;
        }
        ZLogger.d("ignore exctption when read other info");
    }
}
