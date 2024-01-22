package com.goodix.ble.libble.v2.impl;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import androidx.annotation.Nullable;
import com.goodix.ble.libble.BleUuid;
import com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic;
import com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor;
import com.goodix.ble.libble.v2.gb.gatt.GBGattService;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureRead;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedure;
import com.goodix.ble.libble.v2.impl.procedure.CharacteristicRead;
import com.goodix.ble.libble.v2.impl.procedure.CharacteristicWrite;
import com.goodix.ble.libble.v2.impl.procedure.NotificationEnable;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes5.dex */
public class BleCharacteristicX implements GBGattCharacteristic {

    /* renamed from: a  reason: collision with root package name */
    public BleRemoteDevice f8014a;
    public BleServiceX b;
    public UUID c;
    public BluetoothGattCharacteristic d;
    @Nullable
    public ArrayList<BleDescriptorX> g;
    public boolean e = false;
    public boolean f = false;
    public boolean needEnableNotify = false;
    public boolean needEnableIndicate = false;
    public Event<byte[]> h = null;
    public Event<byte[]> i = null;
    public Event<byte[]> j = null;
    public Event<byte[]> k = null;

    public BleCharacteristicX(BleRemoteDevice bleRemoteDevice, BleServiceX bleServiceX, UUID uuid) {
        this.f8014a = bleRemoteDevice;
        this.b = bleServiceX;
        this.c = uuid;
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, ArrayList<String> arrayList) {
        if (bluetoothGattCharacteristic == null) {
            this.d = null;
            ArrayList<BleDescriptorX> arrayList2 = this.g;
            if (arrayList2 != null) {
                Iterator<BleDescriptorX> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(null, arrayList);
                }
                return;
            }
            return;
        }
        ILogger logger = this.f8014a.getLogger();
        if (!this.c.equals(bluetoothGattCharacteristic.getUuid())) {
            if (logger != null) {
                logger.w("GBGattCharacteristic", this.c + " is not match to " + bluetoothGattCharacteristic.getUuid());
                return;
            }
            return;
        }
        this.d = bluetoothGattCharacteristic;
        List<BluetoothGattDescriptor> descriptors = bluetoothGattCharacteristic.getDescriptors();
        if (descriptors.isEmpty()) {
            return;
        }
        ArrayList<BleDescriptorX> arrayList3 = this.g;
        if (arrayList3 == null) {
            this.g = new ArrayList<>(descriptors.size());
        } else {
            arrayList3.ensureCapacity(descriptors.size());
        }
        HashMap hashMap = new HashMap(this.g.size());
        Iterator<BleDescriptorX> it2 = this.g.iterator();
        while (it2.hasNext()) {
            BleDescriptorX next = it2.next();
            UUID uuid = next.getUuid();
            ArrayList arrayList4 = (ArrayList) hashMap.get(uuid);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList(4);
                hashMap.put(uuid, arrayList4);
            }
            arrayList4.add(next);
        }
        this.g.clear();
        for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
            UUID uuid2 = bluetoothGattDescriptor.getUuid();
            ArrayList arrayList5 = (ArrayList) hashMap.get(uuid2);
            if (arrayList5 == null || arrayList5.isEmpty()) {
                Log.d("onDiscovered", "    D +->" + uuid2);
                BleDescriptorX bleDescriptorX = new BleDescriptorX(this.f8014a, this, uuid2);
                bleDescriptorX.a(bluetoothGattDescriptor, arrayList);
                this.g.add(bleDescriptorX);
            } else {
                Log.d("onDiscovered", "    D =->" + uuid2);
                BleDescriptorX bleDescriptorX2 = (BleDescriptorX) arrayList5.remove(0);
                bleDescriptorX2.a(bluetoothGattDescriptor, arrayList);
                this.g.add(bleDescriptorX2);
                if (arrayList5.isEmpty()) {
                    hashMap.remove(uuid2);
                }
            }
        }
        for (ArrayList arrayList6 : hashMap.values()) {
            Iterator it3 = arrayList6.iterator();
            while (it3.hasNext()) {
                BleDescriptorX bleDescriptorX3 = (BleDescriptorX) it3.next();
                if (bleDescriptorX3.e) {
                    arrayList.add("Characteristic " + this.c + " does not find required descriptor: " + bleDescriptorX3.getUuid());
                }
                if (bleDescriptorX3.d) {
                    this.g.add(bleDescriptorX3);
                }
                bleDescriptorX3.a(null, arrayList);
            }
        }
    }

    public final boolean b(int i) {
        BluetoothGattDescriptor descriptor;
        byte[] value;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.d;
        return bluetoothGattCharacteristic != null && (descriptor = bluetoothGattCharacteristic.getDescriptor(BleUuid.CCCD)) != null && (value = descriptor.getValue()) != null && value.length == 2 && value[1] == 0 && ((value[0] & 255) & i) == i;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public void clearEventListener(Object obj) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(this.h);
        arrayList.add(this.i);
        arrayList.add(this.j);
        arrayList.add(this.k);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Event event = (Event) it.next();
            if (event != null) {
                event.clear(obj);
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBGattDescriptor defineDescriptor(UUID uuid, boolean z) {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        BleDescriptorX bleDescriptorX = new BleDescriptorX(this.f8014a, this, uuid);
        bleDescriptorX.d = true;
        bleDescriptorX.e = z;
        this.g.add(bleDescriptorX);
        return bleDescriptorX;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public Event<byte[]> evtIndicate() {
        if (this.k == null) {
            synchronized (this) {
                if (this.k == null) {
                    this.k = new Event<>(this, 66);
                }
            }
        }
        return this.k;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public Event<byte[]> evtNotify() {
        if (this.j == null) {
            synchronized (this) {
                if (this.j == null) {
                    this.j = new Event<>(this, 55);
                }
            }
        }
        return this.j;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public Event<byte[]> evtRead() {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = new Event<>(this, 33);
                }
            }
        }
        return this.h;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public Event<byte[]> evtWritten() {
        if (this.i == null) {
            synchronized (this) {
                if (this.i == null) {
                    this.i = new Event<>(this, 44);
                }
            }
        }
        return this.i;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public List<GBGattDescriptor> getDescriptor(UUID uuid) {
        ArrayList<BleDescriptorX> arrayList = this.g;
        if (arrayList == null || arrayList.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<BleDescriptorX> it = this.g.iterator();
        while (it.hasNext()) {
            BleDescriptorX next = it.next();
            if (next.getUuid().equals(uuid)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public List<GBGattDescriptor> getDescriptors() {
        ArrayList<BleDescriptorX> arrayList = this.g;
        return (arrayList == null || arrayList.isEmpty()) ? Collections.emptyList() : new ArrayList(this.g);
    }

    public BluetoothGattCharacteristic getGattCharacteristic() {
        return this.d;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public int getInstanceId() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.d;
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getInstanceId();
        }
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public int getProperty() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.d;
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getProperties();
        }
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBGattService getService() {
        return this.b;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public UUID getUuid() {
        return this.c;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public byte[] getValue() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.d;
        return bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.getValue() : new byte[0];
    }

    public boolean isDiscovered() {
        return this.d != null;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public boolean isIndicateEnabled() {
        return b(2);
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public boolean isNotifyEnabled() {
        return b(1);
    }

    public boolean onCharacteristicChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.d;
        if (bluetoothGattCharacteristic2 == null || !bluetoothGattCharacteristic2.equals(bluetoothGattCharacteristic)) {
            return false;
        }
        Event<byte[]> event = null;
        if (i == 1) {
            event = this.h;
        } else if (i == 2) {
            event = this.i;
        } else if (i == 3) {
            event = this.j;
        } else if (i == 4) {
            event = this.k;
        }
        if (event != null) {
            event.postEvent(bluetoothGattCharacteristic.getValue());
        }
        return true;
    }

    public boolean onDescriptorChanged(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        if (this.g == null || (bluetoothGattCharacteristic = this.d) == null || !bluetoothGattCharacteristic.equals(bluetoothGattDescriptor.getCharacteristic())) {
            return false;
        }
        Iterator<BleDescriptorX> it = this.g.iterator();
        while (it.hasNext()) {
            if (it.next().onDescriptorChanged(bluetoothGattDescriptor, i)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBGattProcedureRead read() {
        CharacteristicRead characteristicRead = new CharacteristicRead();
        characteristicRead.setRemoteDevice(this.f8014a);
        characteristicRead.setTargetCharacteristic(this);
        ILogger logger = this.f8014a.getLogger();
        if (logger != null) {
            characteristicRead.setLogger(logger);
        }
        return characteristicRead;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBGattDescriptor requireDescriptor(UUID uuid, boolean z) {
        BleDescriptorX bleDescriptorX;
        synchronized (this) {
            ArrayList<BleDescriptorX> arrayList = this.g;
            if (arrayList != null) {
                Iterator<BleDescriptorX> it = arrayList.iterator();
                while (it.hasNext()) {
                    bleDescriptorX = it.next();
                    if (bleDescriptorX.getUuid().equals(uuid)) {
                        BleDescriptorX bleDescriptorX2 = bleDescriptorX;
                        bleDescriptorX2.d = true;
                        bleDescriptorX2.e = z;
                        break;
                    }
                }
            }
            bleDescriptorX = null;
        }
        return bleDescriptorX == null ? defineDescriptor(uuid, z) : bleDescriptorX;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBProcedure setEnableIndicate(boolean z) {
        NotificationEnable notificationEnable = new NotificationEnable();
        notificationEnable.setRemoteDevice(this.f8014a);
        notificationEnable.setTargetCharacteristic(this);
        notificationEnable.setEnable(z);
        notificationEnable.setForIndicate();
        ILogger logger = this.f8014a.getLogger();
        if (logger != null) {
            notificationEnable.setLogger(logger);
        }
        return notificationEnable;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBProcedure setEnableNotify(boolean z) {
        NotificationEnable notificationEnable = new NotificationEnable();
        notificationEnable.setRemoteDevice(this.f8014a);
        notificationEnable.setTargetCharacteristic(this);
        notificationEnable.setEnable(z);
        ILogger logger = this.f8014a.getLogger();
        if (logger != null) {
            notificationEnable.setLogger(logger);
        }
        return notificationEnable;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public void setValue(byte[] bArr) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.d;
        if (bluetoothGattCharacteristic != null) {
            bluetoothGattCharacteristic.setValue(bArr);
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBGattProcedureWrite writeByCommand(byte[] bArr, boolean z) {
        CharacteristicWrite characteristicWrite = new CharacteristicWrite();
        characteristicWrite.setRemoteDevice(this.f8014a);
        characteristicWrite.setTargetCharacteristic(this);
        characteristicWrite.setValue(bArr);
        characteristicWrite.setWriteType(z ? 4 : 1);
        ILogger logger = this.f8014a.getLogger();
        if (logger != null) {
            characteristicWrite.setLogger(logger);
        }
        return characteristicWrite;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public GBGattProcedureWrite writeByRequest(byte[] bArr) {
        CharacteristicWrite characteristicWrite = new CharacteristicWrite();
        characteristicWrite.setRemoteDevice(this.f8014a);
        characteristicWrite.setTargetCharacteristic(this);
        characteristicWrite.setValue(bArr);
        characteristicWrite.setWriteType(2);
        ILogger logger = this.f8014a.getLogger();
        if (logger != null) {
            characteristicWrite.setLogger(logger);
        }
        return characteristicWrite;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic
    public boolean writeDirectly(boolean z, boolean z2, byte[] bArr) {
        BleServiceX bleServiceX = this.b;
        if (bleServiceX == null || this.d == null) {
            return false;
        }
        BleGattX gatt = ((BleRemoteDevice) bleServiceX.getRemoteDevice()).getGatt();
        int i = 1;
        if (z) {
            i = 2;
        } else if (z2) {
            i = 4;
        }
        this.d.setWriteType(i);
        this.d.setValue(bArr);
        return gatt.tryWriteCharacteristic(this.d);
    }
}
