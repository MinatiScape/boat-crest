package com.realsil.sdk.dfu.n;

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
import com.realsil.sdk.dfu.utils.ConnectParams;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class a extends com.realsil.sdk.dfu.m.a {
    public BluetoothGattCharacteristic B;
    public BluetoothGattCharacteristic C;
    public BluetoothGattCharacteristic D;

    public a(int i, boolean z) {
        this.h = i;
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
            ZLogger.v(true, "read battery level :" + a2);
        }
        if (this.m != null) {
            b(259);
            boolean a3 = a(this.m);
            ZLogger.v(true, "read PnP_ID :" + a3);
        }
        if (this.D != null) {
            b(260);
            boolean a4 = a(this.D);
            ZLogger.v(true, "read device info :" + a4);
            if (!a4) {
                this.n.clear();
                b(2);
                return;
            }
        }
        if (this.C != null) {
            b(261);
            boolean a5 = a(this.C);
            ZLogger.v(true, "read device mac :" + a5);
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
                ZLogger.d(true, "read image version :" + a7);
            }
        }
        this.n.clear();
        b(1);
    }

    public final void j() {
        if (this.k == null) {
            ZLogger.w(true, "not find DFU_SERVICE_UUID = " + e.f13635a);
            if (this.B != null) {
                this.p.add(new OtaModeInfo(0));
                return;
            }
            return;
        }
        ZLogger.d(true, "find DFU_SERVICE_UUID = " + this.k.getUuid());
        BluetoothGattService bluetoothGattService = this.k;
        UUID uuid = e.d;
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        if (characteristic != null) {
            ZLogger.d(true, "find DFU_EXTEND_FLASH_CHARACTERISTIC = " + uuid);
            this.p.add(new OtaModeInfo(17));
            ZLogger.d(BluetoothGattImpl.parseProperty2(characteristic.getProperties()));
            return;
        }
        ZLogger.d(true, "DFU_EXTEND_FLASH_CHARACTERISTIC not found");
        this.p.add(new OtaModeInfo(16));
        if (this.B != null) {
            this.p.add(new OtaModeInfo(0));
        }
    }

    public final void k() {
        BluetoothGattService bluetoothGattService = this.j;
        if (bluetoothGattService == null) {
            return;
        }
        UUID uuid = f.f13636a;
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        this.B = characteristic;
        if (characteristic == null) {
            ZLogger.d("OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC not found");
        } else {
            ZLogger.v(true, "find OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = " + uuid);
        }
        BluetoothGattService bluetoothGattService2 = this.j;
        UUID uuid2 = f.b;
        BluetoothGattCharacteristic characteristic2 = bluetoothGattService2.getCharacteristic(uuid2);
        this.C = characteristic2;
        if (characteristic2 == null) {
            ZLogger.d("OTA_DEVICE_MAC_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.v("find OTA_DEVICE_MAC_CHARACTERISTIC_UUID = " + uuid2);
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.C.getProperties()));
        }
        BluetoothGattCharacteristic characteristic3 = this.j.getCharacteristic(f.g);
        this.D = characteristic3;
        if (characteristic3 == null) {
            ZLogger.d("OTA_DEVICE_INFO_CHARACTERISTIC_UUID not found");
        }
        for (int i = 65472; i <= 65487; i++) {
            BluetoothGattCharacteristic characteristic4 = this.j.getCharacteristic(BluetoothUuid.fromShortValue(i));
            if (characteristic4 == null) {
                break;
            }
            this.n.add(characteristic4);
        }
        for (int i2 = 65504; i2 <= 65519; i2++) {
            UUID fromShortValue = BluetoothUuid.fromShortValue(i2);
            BluetoothGattCharacteristic characteristic5 = this.j.getCharacteristic(fromShortValue);
            if (characteristic5 == null) {
                ZLogger.v(false, "not found image version characteristic:" + fromShortValue.toString());
                return;
            }
            ZLogger.v(true, "find image version characteristic: " + fromShortValue.toString());
            this.n.add(characteristic5);
        }
    }

    @Override // com.realsil.sdk.dfu.m.a
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.a(bluetoothGatt, bluetoothGattCharacteristic, i);
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        bluetoothGattCharacteristic.getValue();
        if (i == 0) {
            byte[] value = bluetoothGattCharacteristic.getValue();
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
            } else if (f.g.equals(uuid)) {
                b().parseX0000(value);
                h();
                return;
            } else if (f.b.equals(uuid)) {
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
            } else if (f.c.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer wrap2 = ByteBuffer.wrap(value);
                    wrap2.order(ByteOrder.LITTLE_ENDIAN);
                    if (length == 2) {
                        b().setPatchVersion(wrap2.getShort(0) & UShort.MAX_VALUE);
                    } else if (length >= 4) {
                        b().setPatchVersion(wrap2.getInt(0) & 65535);
                    }
                }
                h();
                return;
            } else if (f.d.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer wrap3 = ByteBuffer.wrap(value);
                    wrap3.order(ByteOrder.LITTLE_ENDIAN);
                    if (length == 2) {
                        b().setAppVersion(wrap3.getShort(0) & UShort.MAX_VALUE);
                    } else if (length >= 4) {
                        b().setAppVersion(wrap3.getInt(0));
                    }
                }
                h();
                return;
            } else if (f.e.equals(uuid)) {
                if (length > 0) {
                    ByteBuffer wrap4 = ByteBuffer.wrap(value);
                    wrap4.order(ByteOrder.LITTLE_ENDIAN);
                    if (length == 1) {
                        b().setPatchExtensionVersion(wrap4.get(0));
                    } else if (length == 2) {
                        b().setPatchExtensionVersion(wrap4.getShort(0) & UShort.MAX_VALUE);
                    }
                }
                h();
                return;
            } else {
                int shortValue = BluetoothUuid.toShortValue(uuid);
                if (shortValue >= 65504 && shortValue <= 65519) {
                    b().appendActiveImageVersionBytes(value);
                } else if (shortValue >= 65472 && shortValue <= 65487) {
                    b().appendDebugCharacteristicInfo(shortValue, value);
                }
                h();
                return;
            }
        }
        ZLogger.e(true, "Characteristic read error: " + i);
        if (f.g.equals(uuid)) {
            b(2);
            h();
            return;
        }
        ZLogger.d("ignore exctption when read other info");
    }
}
