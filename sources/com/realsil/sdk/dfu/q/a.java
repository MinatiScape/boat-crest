package com.realsil.sdk.dfu.q;

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
    public BluetoothGattCharacteristic E;
    public BluetoothGattCharacteristic F;
    public BluetoothGattCharacteristic G;
    public BluetoothGattCharacteristic H;
    public boolean I;
    public boolean J = false;

    public a(int i, boolean z) {
        this.h = i;
        this.I = z;
    }

    @Override // com.realsil.sdk.dfu.m.a
    public void a(ConnectParams connectParams, String str, BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, BluetoothGattService bluetoothGattService2, a.c cVar) {
        super.a(connectParams, str, bluetoothGatt, bluetoothGattService, bluetoothGattService2, cVar);
        if (this.I) {
            ZLogger.v("No Temp OTA, no need to check ota service");
        } else {
            k();
        }
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
        if (b().specVersion == 0) {
            if (this.E != null) {
                b(DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS);
                boolean a6 = a(this.E);
                ZLogger.v(false, "read app version :" + a6);
            }
            if (this.F != null) {
                b(DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS);
                boolean a7 = a(this.F);
                ZLogger.v(false, "attempt to read patch version :" + a7);
            }
            if (this.G != null) {
                b(DfuException.ERROR_CONNECT_ERROR);
                boolean a8 = a(this.G);
                ZLogger.v(false, "attempt to read patch extension version :" + a8);
            }
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.H;
        if (bluetoothGattCharacteristic != null) {
            this.J = true;
            if (a(this.i, bluetoothGattCharacteristic, true)) {
                b(DfuException.ERROR_CANNOT_FIND_DEVICE);
                boolean l = l();
                ZLogger.v(false, "readTempDeviceInfo:" + l);
            } else {
                ZLogger.d(false, "readTempDeviceInfo failed");
            }
        }
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : this.n) {
            int shortValue = BluetoothUuid.toShortValue(bluetoothGattCharacteristic2.getUuid());
            ZLogger.v(true, String.format("uuidShortValue=0x%4x", Integer.valueOf(shortValue)));
            if (shortValue >= 65472 && shortValue <= 65487) {
                b(DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR);
                boolean a9 = a(bluetoothGattCharacteristic2);
                ZLogger.d(false, "read debug info :" + a9);
            } else if (shortValue >= 65504 && shortValue <= 65519 && b().specVersion != 0) {
                b(DfuException.ERROR_WRITE_CHARAC_ERROR);
                boolean a10 = a(bluetoothGattCharacteristic2);
                ZLogger.v(false, "read image version :" + a10);
            }
        }
        this.n.clear();
        b(1);
    }

    public final void j() {
        if (this.k == null) {
            this.H = null;
            ZLogger.w(true, "not find DFU_SERVICE_UUID = " + g.f13644a);
            if (this.B != null) {
                this.p.add(new OtaModeInfo(0));
                return;
            }
            return;
        }
        ZLogger.d(true, "find DFU_SERVICE_UUID = " + this.k.getUuid());
        if (this.I) {
            this.p.add(new OtaModeInfo(18));
            BluetoothGattService bluetoothGattService = this.k;
            UUID uuid = g.c;
            BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
            this.H = characteristic;
            if (characteristic == null) {
                ZLogger.d("not found DFU_CONTROL_POINT_CHARACTERISTIC: " + uuid);
                return;
            }
            ZLogger.d("find DFU_CONTROL_POINT_CHARACTERISTIC: " + uuid);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.H.getProperties()));
            this.H.setWriteType(2);
            return;
        }
        BluetoothGattService bluetoothGattService2 = this.k;
        UUID uuid2 = g.d;
        BluetoothGattCharacteristic characteristic2 = bluetoothGattService2.getCharacteristic(uuid2);
        if (characteristic2 != null) {
            ZLogger.d(true, "find DFU_EXTEND_FLASH_CHARACTERISTIC = " + uuid2);
            this.p.add(new OtaModeInfo(17));
            ZLogger.d(BluetoothGattImpl.parseProperty2(characteristic2.getProperties()));
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
        UUID uuid = h.f13645a;
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        this.B = characteristic;
        if (characteristic == null) {
            ZLogger.d("OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC not found");
        } else {
            ZLogger.v(true, "find OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = " + uuid);
        }
        BluetoothGattService bluetoothGattService2 = this.j;
        UUID uuid2 = h.b;
        BluetoothGattCharacteristic characteristic2 = bluetoothGattService2.getCharacteristic(uuid2);
        this.C = characteristic2;
        if (characteristic2 == null) {
            ZLogger.d("OTA_DEVICE_MAC_CHARACTERISTIC_UUID not found");
        } else {
            ZLogger.v("find OTA_DEVICE_MAC_CHARACTERISTIC_UUID = " + uuid2);
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.C.getProperties()));
        }
        BluetoothGattCharacteristic characteristic3 = this.j.getCharacteristic(h.c);
        this.F = characteristic3;
        if (characteristic3 == null) {
            ZLogger.d("OTA_READ_PATCH_CHARACTERISTIC_UUID not found");
        }
        BluetoothGattCharacteristic characteristic4 = this.j.getCharacteristic(h.d);
        this.E = characteristic4;
        if (characteristic4 == null) {
            ZLogger.d("OTA_APP_VERSION_CHARACTERISTIC_UUID not found");
        }
        BluetoothGattCharacteristic characteristic5 = this.j.getCharacteristic(h.e);
        this.G = characteristic5;
        if (characteristic5 == null) {
            ZLogger.d("OTA_READ_PATCH_EXTENSION_CHARACTERISTIC_UUID not found");
        }
        BluetoothGattCharacteristic characteristic6 = this.j.getCharacteristic(h.g);
        this.D = characteristic6;
        if (characteristic6 == null) {
            ZLogger.d("OTA_DEVICE_INFO_CHARACTERISTIC_UUID not found");
        }
        int i = 65472;
        while (true) {
            if (i > 65487) {
                break;
            }
            UUID fromShortValue = BluetoothUuid.fromShortValue(i);
            BluetoothGattCharacteristic characteristic7 = this.j.getCharacteristic(fromShortValue);
            if (characteristic7 == null) {
                ZLogger.d(true, "not found debug characteristic:" + fromShortValue.toString());
                break;
            }
            ZLogger.d(true, "find debug characteristic: " + fromShortValue.toString());
            this.n.add(characteristic7);
            i++;
        }
        for (int i2 = 65504; i2 <= 65519; i2++) {
            UUID fromShortValue2 = BluetoothUuid.fromShortValue(i2);
            BluetoothGattCharacteristic characteristic8 = this.j.getCharacteristic(fromShortValue2);
            if (characteristic8 == null) {
                ZLogger.d(true, "not found image version characteristic:" + fromShortValue2.toString());
                return;
            }
            ZLogger.d(true, "find image version characteristic: " + fromShortValue2.toString());
            this.n.add(characteristic8);
        }
    }

    public final boolean l() {
        if (this.i != null && this.H != null) {
            ZLogger.v("attempt to read temp device info ....: ");
            this.H.setValue(new byte[]{13});
            boolean writeCharacteristic = this.i.writeCharacteristic(this.H);
            if (writeCharacteristic) {
                i();
            }
            this.J = !writeCharacteristic;
            return writeCharacteristic;
        }
        ZLogger.w("mBtGatt is null maybe disconnected just now");
        return false;
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
            } else if (h.g.equals(uuid)) {
                b().parseX0000(value);
                h();
                return;
            } else if (h.b.equals(uuid)) {
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
            } else if (h.c.equals(uuid)) {
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
            } else if (h.d.equals(uuid)) {
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
            } else if (h.e.equals(uuid)) {
                ByteBuffer wrap4 = ByteBuffer.wrap(value);
                wrap4.order(ByteOrder.LITTLE_ENDIAN);
                if (length == 1) {
                    b().setPatchExtensionVersion(wrap4.get(0));
                } else if (length == 2) {
                    b().setPatchExtensionVersion(wrap4.getShort(0) & UShort.MAX_VALUE);
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
        if (h.g.equals(uuid)) {
            b(2);
            h();
            return;
        }
        ZLogger.d("ignore exctption when read other info");
    }

    @Override // com.realsil.sdk.dfu.m.a
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super.a(bluetoothGatt, bluetoothGattCharacteristic);
        bluetoothGattCharacteristic.getUuid();
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (g.c.equals(bluetoothGattCharacteristic.getUuid())) {
            if (value != null && value.length >= 2) {
                int i = value[0] & 255;
                int i2 = value[1] & 255;
                byte b = value[2];
                ZLogger.v(true, String.format("responseType = %02X , requestOpCode = %02X", Integer.valueOf(i), Integer.valueOf(i2)));
                if (i == 16 && i2 == 13) {
                    if (b == 1) {
                        b().parseX0000(value, 3);
                    } else {
                        ZLogger.w("Get temp dev info failed");
                    }
                    h();
                    return;
                }
                return;
            }
            ZLogger.w("notification data invalid");
        }
    }
}
