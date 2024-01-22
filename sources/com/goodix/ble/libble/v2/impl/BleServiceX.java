package com.goodix.ble.libble.v2.impl;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import androidx.annotation.Nullable;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic;
import com.goodix.ble.libble.v2.gb.gatt.GBGattService;
import com.goodix.ble.libcomx.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes5.dex */
public class BleServiceX implements GBGattService {

    /* renamed from: a  reason: collision with root package name */
    public BleRemoteDevice f8020a;
    public UUID b;
    public BluetoothGattService c;
    public boolean d = false;
    public boolean e = false;
    @Nullable
    public ArrayList<BleCharacteristicX> f;

    public BleServiceX(BleRemoteDevice bleRemoteDevice, UUID uuid) {
        this.f8020a = bleRemoteDevice;
        this.b = uuid;
    }

    public void a(BluetoothGattService bluetoothGattService, ArrayList<String> arrayList) {
        if (bluetoothGattService == null) {
            this.c = null;
            ArrayList<BleCharacteristicX> arrayList2 = this.f;
            if (arrayList2 != null) {
                Iterator<BleCharacteristicX> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(null, arrayList);
                }
                return;
            }
            return;
        }
        ILogger logger = this.f8020a.getLogger();
        if (!this.b.equals(bluetoothGattService.getUuid())) {
            if (logger != null) {
                logger.w("GBGattService", this.b + " is not match to " + bluetoothGattService.getUuid());
                return;
            }
            return;
        }
        this.c = bluetoothGattService;
        List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
        if (characteristics.isEmpty()) {
            return;
        }
        ArrayList<BleCharacteristicX> arrayList3 = this.f;
        if (arrayList3 == null) {
            this.f = new ArrayList<>(characteristics.size());
        } else {
            arrayList3.ensureCapacity(characteristics.size());
        }
        HashMap hashMap = new HashMap(this.f.size());
        Iterator<BleCharacteristicX> it2 = this.f.iterator();
        while (it2.hasNext()) {
            BleCharacteristicX next = it2.next();
            UUID uuid = next.getUuid();
            ArrayList arrayList4 = (ArrayList) hashMap.get(uuid);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList(4);
                hashMap.put(uuid, arrayList4);
            }
            arrayList4.add(next);
        }
        this.f.clear();
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
            UUID uuid2 = bluetoothGattCharacteristic.getUuid();
            ArrayList arrayList5 = (ArrayList) hashMap.get(uuid2);
            if (arrayList5 == null || arrayList5.isEmpty()) {
                Log.d("onDiscovered", "  C +->  " + uuid2);
                BleCharacteristicX bleCharacteristicX = new BleCharacteristicX(this.f8020a, this, uuid2);
                bleCharacteristicX.a(bluetoothGattCharacteristic, arrayList);
                this.f.add(bleCharacteristicX);
            } else {
                Log.d("onDiscovered", "  C =->  " + uuid2);
                BleCharacteristicX bleCharacteristicX2 = (BleCharacteristicX) arrayList5.remove(0);
                bleCharacteristicX2.a(bluetoothGattCharacteristic, arrayList);
                this.f.add(bleCharacteristicX2);
                if (arrayList5.isEmpty()) {
                    hashMap.remove(uuid2);
                }
            }
        }
        for (ArrayList arrayList6 : hashMap.values()) {
            Iterator it3 = arrayList6.iterator();
            while (it3.hasNext()) {
                BleCharacteristicX bleCharacteristicX3 = (BleCharacteristicX) it3.next();
                if (bleCharacteristicX3.f) {
                    arrayList.add("Service " + this.b + " does not find required characteristic: " + bleCharacteristicX3.getUuid());
                }
                if (bleCharacteristicX3.e) {
                    this.f.add(bleCharacteristicX3);
                }
                bleCharacteristicX3.a(null, arrayList);
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public void clearEventListener(Object obj) {
        ArrayList<BleCharacteristicX> arrayList = this.f;
        if (arrayList != null) {
            Iterator<BleCharacteristicX> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().clearEventListener(obj);
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public GBGattCharacteristic defineCharacteristic(UUID uuid, boolean z, boolean z2, boolean z3) {
        if (this.f == null) {
            this.f = new ArrayList<>();
        }
        BleCharacteristicX bleCharacteristicX = new BleCharacteristicX(this.f8020a, this, uuid);
        bleCharacteristicX.e = true;
        bleCharacteristicX.f = z;
        bleCharacteristicX.needEnableNotify = z3;
        bleCharacteristicX.needEnableIndicate = z2;
        this.f.add(bleCharacteristicX);
        return bleCharacteristicX;
    }

    public boolean equals(BluetoothGattService bluetoothGattService) {
        BluetoothGattService bluetoothGattService2;
        if (bluetoothGattService == null || (bluetoothGattService2 = this.c) == null) {
            return false;
        }
        if (bluetoothGattService == bluetoothGattService2) {
            return true;
        }
        return bluetoothGattService.getUuid().equals(this.c.getUuid()) && bluetoothGattService.getInstanceId() == this.c.getInstanceId();
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public List<GBGattCharacteristic> getCharacteristic(UUID uuid) {
        ArrayList<BleCharacteristicX> arrayList = this.f;
        if (arrayList == null || arrayList.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<BleCharacteristicX> it = this.f.iterator();
        while (it.hasNext()) {
            BleCharacteristicX next = it.next();
            if (next.getUuid().equals(uuid)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public ArrayList<BleCharacteristicX> getCharacteristicList() {
        ArrayList<BleCharacteristicX> arrayList = this.f;
        return arrayList == null ? new ArrayList<>(0) : arrayList;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public List<GBGattCharacteristic> getCharacteristics() {
        ArrayList<BleCharacteristicX> arrayList = this.f;
        return (arrayList == null || arrayList.isEmpty()) ? Collections.emptyList() : new ArrayList(this.f);
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public List<GBGattService> getIncludeService() {
        ArrayList arrayList = new ArrayList();
        BluetoothGattService bluetoothGattService = this.c;
        if (bluetoothGattService != null) {
            for (BluetoothGattService bluetoothGattService2 : bluetoothGattService.getIncludedServices()) {
                for (GBGattService gBGattService : this.f8020a.getService(bluetoothGattService2.getUuid())) {
                    if (!arrayList.contains(gBGattService)) {
                        arrayList.add(gBGattService);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public int getInstanceId() {
        BluetoothGattService bluetoothGattService = this.c;
        if (bluetoothGattService != null) {
            return bluetoothGattService.getInstanceId();
        }
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public GBRemoteDevice getRemoteDevice() {
        return this.f8020a;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public UUID getUuid() {
        return this.b;
    }

    public boolean isDiscovered() {
        return this.c != null;
    }

    public boolean onCharacteristicChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        BluetoothGattService bluetoothGattService;
        if (this.f == null || (bluetoothGattService = this.c) == null || !bluetoothGattService.equals(bluetoothGattCharacteristic.getService())) {
            return false;
        }
        Iterator<BleCharacteristicX> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().onCharacteristicChanged(bluetoothGattCharacteristic, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean onDescriptorChanged(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        BluetoothGattService bluetoothGattService;
        if (this.f == null || (bluetoothGattService = this.c) == null || !bluetoothGattService.equals(bluetoothGattDescriptor.getCharacteristic().getService())) {
            return false;
        }
        Iterator<BleCharacteristicX> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().onDescriptorChanged(bluetoothGattDescriptor, i)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public GBGattCharacteristic requireCharacteristic(UUID uuid, boolean z, boolean z2, boolean z3) {
        BleCharacteristicX bleCharacteristicX;
        synchronized (this) {
            ArrayList<BleCharacteristicX> arrayList = this.f;
            if (arrayList != null) {
                Iterator<BleCharacteristicX> it = arrayList.iterator();
                while (it.hasNext()) {
                    bleCharacteristicX = it.next();
                    if (bleCharacteristicX.getUuid().equals(uuid)) {
                        if (bleCharacteristicX instanceof BleCharacteristicX) {
                            BleCharacteristicX bleCharacteristicX2 = bleCharacteristicX;
                            bleCharacteristicX2.e = true;
                            bleCharacteristicX2.f = z;
                            bleCharacteristicX2.needEnableNotify = z3;
                            bleCharacteristicX2.needEnableIndicate = z2;
                        }
                    }
                }
            }
            bleCharacteristicX = null;
        }
        return bleCharacteristicX == null ? defineCharacteristic(uuid, z, z2, z3) : bleCharacteristicX;
    }

    @Override // com.goodix.ble.libble.v2.gb.gatt.GBGattService
    public boolean undefineService() {
        if (this.d) {
            this.d = false;
            this.e = false;
            a(null, null);
            return this.f8020a.getServiceList().remove(this);
        }
        return false;
    }
}
