package com.goodix.ble.libble.v2.impl;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import androidx.annotation.Nullable;
import com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic;
import com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureRead;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite;
import com.goodix.ble.libble.v2.impl.procedure.DescriptorRead;
import com.goodix.ble.libble.v2.impl.procedure.DescriptorWrite;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import java.util.ArrayList;
import java.util.UUID;
/* loaded from: classes5.dex */
public class BleDescriptorX implements GBGattDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public UUID f8015a;
    public BleCharacteristicX b;
    public BleRemoteDevice c;
    public boolean d = false;
    public boolean e = false;
    public Event<byte[]> f = null;
    public Event<byte[]> g = null;
    @Nullable
    public BluetoothGattDescriptor h;

    public BleDescriptorX(BleRemoteDevice bleRemoteDevice, BleCharacteristicX bleCharacteristicX, UUID uuid) {
        this.c = bleRemoteDevice;
        this.b = bleCharacteristicX;
        this.f8015a = uuid;
    }

    public void a(BluetoothGattDescriptor bluetoothGattDescriptor, ArrayList<String> arrayList) {
        if (bluetoothGattDescriptor == null) {
            this.h = null;
        } else if (this.f8015a.equals(bluetoothGattDescriptor.getUuid())) {
            this.h = bluetoothGattDescriptor;
        } else {
            ILogger logger = this.c.getLogger();
            if (logger != null) {
                logger.w("GBGattDescriptor", this.f8015a + " is not match to " + bluetoothGattDescriptor.getUuid());
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public void clearEventListener(Object obj) {
        Event<byte[]> event = this.f;
        if (event != null) {
            event.clear(obj);
        }
        Event<byte[]> event2 = this.g;
        if (event2 != null) {
            event2.clear(obj);
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public Event<byte[]> evtRead() {
        if (this.f == null) {
            synchronized (this) {
                if (this.f == null) {
                    this.f = new Event<>(this, GBGattDescriptor.EVT_READ);
                }
            }
        }
        return this.f;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public Event<byte[]> evtWritten() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    this.g = new Event<>(this, GBGattDescriptor.EVT_WRITTEN);
                }
            }
        }
        return this.g;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public GBGattCharacteristic getCharacteristic() {
        return this.b;
    }

    @Nullable
    public BluetoothGattDescriptor getGattDescriptor() {
        return this.h;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public int getInstanceId() {
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public UUID getUuid() {
        return this.f8015a;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public byte[] getValue() {
        BluetoothGattDescriptor bluetoothGattDescriptor = this.h;
        return bluetoothGattDescriptor != null ? bluetoothGattDescriptor.getValue() : new byte[0];
    }

    public boolean onCharacteristicChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return false;
    }

    public boolean onDescriptorChanged(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        BluetoothGattDescriptor bluetoothGattDescriptor2 = this.h;
        if (bluetoothGattDescriptor2 == null || !bluetoothGattDescriptor2.equals(bluetoothGattDescriptor)) {
            return false;
        }
        Event<byte[]> event = null;
        if (i == 1) {
            event = this.f;
        } else if (i == 2) {
            event = this.g;
        }
        if (event != null) {
            event.postEvent(this.h.getValue());
        }
        return true;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public GBGattProcedureRead read() {
        DescriptorRead descriptorRead = new DescriptorRead();
        descriptorRead.setRemoteDevice(this.c);
        descriptorRead.setTargetDescriptor(this);
        ILogger logger = this.c.getLogger();
        if (logger != null) {
            descriptorRead.setLogger(logger);
        }
        return descriptorRead;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public void setValue(byte[] bArr) {
        BluetoothGattDescriptor bluetoothGattDescriptor = this.h;
        if (bluetoothGattDescriptor != null) {
            bluetoothGattDescriptor.setValue(bArr);
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor
    public GBGattProcedureWrite write(byte[] bArr) {
        DescriptorWrite descriptorWrite = new DescriptorWrite();
        descriptorWrite.setRemoteDevice(this.c);
        descriptorWrite.setTargetDescriptor(this);
        descriptorWrite.setValue(bArr);
        ILogger logger = this.c.getLogger();
        if (logger != null) {
            descriptorWrite.setLogger(logger);
        }
        return descriptorWrite;
    }
}
