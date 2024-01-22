package com.goodix.ble.libble.v2.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.goodix.ble.libble.BleUuid;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.gb.gatt.GBGattService;
import com.goodix.ble.libble.v2.gb.pojo.GBCI;
import com.goodix.ble.libble.v2.gb.pojo.GBError;
import com.goodix.ble.libble.v2.gb.pojo.GBPhy;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedure;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedureConnect;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedureRssiRead;
import com.goodix.ble.libble.v2.impl.procedure.BondCreate;
import com.goodix.ble.libble.v2.impl.procedure.BondRemove;
import com.goodix.ble.libble.v2.impl.procedure.CiSet;
import com.goodix.ble.libble.v2.impl.procedure.GattConnect;
import com.goodix.ble.libble.v2.impl.procedure.GattDisconnect;
import com.goodix.ble.libble.v2.impl.procedure.GattDiscover;
import com.goodix.ble.libble.v2.impl.procedure.MtuExchange;
import com.goodix.ble.libble.v2.impl.procedure.PhyRead;
import com.goodix.ble.libble.v2.impl.procedure.PhySet;
import com.goodix.ble.libble.v2.impl.procedure.RssiRead;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.TaskQueue;
import com.goodix.ble.libcomx.util.AccessLock;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes5.dex */
public class BleRemoteDevice implements GBRemoteDevice {
    public static final String q = "BleRemoteDevice";

    /* renamed from: a  reason: collision with root package name */
    public ILogger f8019a;
    public BluetoothDevice b;
    public BleGattX c;
    public boolean expectConnection;
    public boolean i;
    @Nullable
    public Event<GBCI> j;
    @Nullable
    public Event<GBError> k;
    @Nullable
    public Event<Integer> l;
    @Nullable
    public Event<GBPhy> m;
    @Nullable
    public Event<Integer> n;
    @Nullable
    public Event<Boolean> o;
    public ArrayList<BleServiceX> d = new ArrayList<>(16);
    public GBPhy e = new GBPhy();
    public GBCI f = new GBCI();
    public boolean g = false;
    @Nullable
    public TaskQueue h = null;
    public AccessLock p = new AccessLock();

    /* loaded from: classes5.dex */
    public class InnerCb extends BluetoothGattCallback implements IEventListener {
        public InnerCb() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(BleUuid.CCCD);
            boolean z = false;
            if (descriptor == null || descriptor.getValue() == null || descriptor.getValue().length != 2 || descriptor.getValue()[0] == 1) {
                z = true;
            }
            int i = z ? 3 : 4;
            synchronized (BleRemoteDevice.this) {
                Iterator it = BleRemoteDevice.this.d.iterator();
                while (it.hasNext()) {
                    ((BleServiceX) it.next()).onCharacteristicChanged(bluetoothGattCharacteristic, i);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                synchronized (BleRemoteDevice.this) {
                    Iterator it = BleRemoteDevice.this.d.iterator();
                    while (it.hasNext()) {
                        ((BleServiceX) it.next()).onCharacteristicChanged(bluetoothGattCharacteristic, 1);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                synchronized (BleRemoteDevice.this) {
                    Iterator it = BleRemoteDevice.this.d.iterator();
                    while (it.hasNext()) {
                        ((BleServiceX) it.next()).onCharacteristicChanged(bluetoothGattCharacteristic, 2);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            TaskQueue taskQueue;
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            BleRemoteDevice.this.i = false;
            if (i2 != 2 || (taskQueue = BleRemoteDevice.this.h) == null || taskQueue.getTaskCount() <= 0) {
                return;
            }
            taskQueue.evtFinished().register(this);
            taskQueue.start(null, null);
        }

        @Keep
        public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
            if (i4 == 0) {
                BleRemoteDevice.this.f.interval = i;
                BleRemoteDevice.this.f.latency = i2;
                BleRemoteDevice.this.f.timeout = i3;
                Event event = BleRemoteDevice.this.j;
                if (event != null) {
                    event.postEvent(BleRemoteDevice.this.f);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            if (i == 0) {
                synchronized (BleRemoteDevice.this) {
                    Iterator it = BleRemoteDevice.this.d.iterator();
                    while (it.hasNext()) {
                        ((BleServiceX) it.next()).onDescriptorChanged(bluetoothGattDescriptor, 1);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (i == 0) {
                synchronized (BleRemoteDevice.this) {
                    Iterator it = BleRemoteDevice.this.d.iterator();
                    while (it.hasNext()) {
                        ((BleServiceX) it.next()).onDescriptorChanged(bluetoothGattDescriptor, 2);
                    }
                }
            }
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        public void onEvent(Object obj, int i, Object obj2) {
            Object obj3;
            if (obj == BleRemoteDevice.this.h && i == 342) {
                ITaskResult iTaskResult = (ITaskResult) obj2;
                Event event = BleRemoteDevice.this.o;
                if (iTaskResult.getError() != null) {
                    BleRemoteDevice.this.i = false;
                    if (event != null) {
                        event.postEvent(Boolean.FALSE);
                    }
                    event = BleRemoteDevice.this.k;
                    if (event == null) {
                        return;
                    }
                    obj3 = new GBError(iTaskResult.getCode(), iTaskResult.getError().getMessage());
                } else {
                    BleRemoteDevice.this.i = true;
                    if (event == null) {
                        return;
                    }
                    obj3 = Boolean.TRUE;
                }
                event.postEvent(obj3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            Event event;
            super.onMtuChanged(bluetoothGatt, i, i2);
            if ((i2 == 0 || i != BleRemoteDevice.this.c.getMtu()) && (event = BleRemoteDevice.this.l) != null) {
                event.postEvent(Integer.valueOf(i));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (i3 == 0) {
                BleRemoteDevice.this.e.txPhy = i;
                BleRemoteDevice.this.e.rxPhy = i2;
                Event event = BleRemoteDevice.this.m;
                if (event != null) {
                    event.postEvent(BleRemoteDevice.this.e);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            onPhyRead(bluetoothGatt, i, i2, i3);
        }
    }

    public BleRemoteDevice(Context context) {
        BleGattX bleGattX = new BleGattX(context);
        this.c = bleGattX;
        bleGattX.register(new InnerCb());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(Object obj, int i, GBError gBError) {
        this.k.postEvent(gBError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(Object obj, int i, Integer num) {
        if (num.intValue() != 2) {
            this.g = false;
        }
        this.n.postEvent(num);
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public void clearEventListener(Object obj) {
        d(this.j, obj);
        d(this.k, obj);
        d(this.l, obj);
        d(this.m, obj);
        d(this.n, obj);
        d(this.o, obj);
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public void clearPendingProcedure() {
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            iLogger.v(q, "Clear all pending procedures");
            Iterator<Object> it = this.p.getPendingList(null).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof GBProcedure) {
                    GBProcedure gBProcedure = (GBProcedure) next;
                    gBProcedure.abort();
                    String str = q;
                    iLogger.v(str, "remove pending procedure: " + gBProcedure.getName());
                }
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedureConnect connect(int i) {
        GattConnect gattConnect = new GattConnect();
        gattConnect.setRemoteDevice(this);
        gattConnect.setPreferredPhy(i);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            gattConnect.setLogger(iLogger);
        }
        return gattConnect;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure createBond() {
        BondCreate bondCreate = new BondCreate();
        bondCreate.setRemoteDevice(this);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            bondCreate.setLogger(iLogger);
        }
        return bondCreate;
    }

    public final void d(Event event, Object obj) {
        if (event != null) {
            event.clear(obj);
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBGattService defineService(UUID uuid, boolean z) {
        BleServiceX bleServiceX = new BleServiceX(this, uuid);
        bleServiceX.d = true;
        bleServiceX.e = z;
        synchronized (this) {
            this.d.add(bleServiceX);
        }
        return bleServiceX;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure disconnect(boolean z) {
        GattDisconnect gattDisconnect = new GattDisconnect();
        gattDisconnect.setRemoteDevice(this);
        gattDisconnect.setClearCache(z);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            gattDisconnect.setLogger(iLogger);
        }
        return gattDisconnect;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure discoverServices() {
        GattDiscover gattDiscover = new GattDiscover();
        gattDiscover.setRemoteDevice(this);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            gattDiscover.setLogger(iLogger);
        }
        return gattDiscover;
    }

    public void dispose() {
        clearEventListener(null);
        this.c.clearListener(null);
        if (this.c.getGatt() != null) {
            this.c.tryCloseGatt();
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public Event<GBCI> evtCIUpdated() {
        if (this.j == null) {
            synchronized (this) {
                if (this.j == null) {
                    this.j = new Event<>(this, 101);
                }
            }
        }
        return this.j;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public Event<GBError> evtError() {
        if (this.k == null) {
            synchronized (this) {
                if (this.k == null) {
                    this.k = new Event<>(this, 102);
                    this.c.evtError().register2(new IEventListener() { // from class: com.goodix.ble.libble.v2.impl.a
                        @Override // com.goodix.ble.libcomx.event.IEventListener
                        public final void onEvent(Object obj, int i, Object obj2) {
                            BleRemoteDevice.this.e(obj, i, (GBError) obj2);
                        }
                    });
                }
            }
        }
        return this.k;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public Event<Integer> evtMtuUpdated() {
        if (this.l == null) {
            synchronized (this) {
                if (this.l == null) {
                    this.l = new Event<>(this, 103);
                }
            }
        }
        return this.l;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public Event<GBPhy> evtPhyUpdated() {
        if (this.m == null) {
            synchronized (this) {
                if (this.m == null) {
                    this.m = new Event<>(this, 104);
                }
            }
        }
        return this.m;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public Event<Boolean> evtReady() {
        if (this.o == null) {
            synchronized (this) {
                if (this.o == null) {
                    this.o = new Event<>(this, 108);
                }
            }
        }
        return this.o;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public Event<Integer> evtStateChanged() {
        if (this.n == null) {
            synchronized (this) {
                if (this.n == null) {
                    this.n = new Event<>(this, 106);
                    this.c.evtStateChanged().register2(new IEventListener() { // from class: com.goodix.ble.libble.v2.impl.b
                        @Override // com.goodix.ble.libcomx.event.IEventListener
                        public final void onEvent(Object obj, int i, Object obj2) {
                            BleRemoteDevice.this.f(obj, i, (Integer) obj2);
                        }
                    });
                }
            }
        }
        return this.n;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public String getAddress() {
        BluetoothDevice bluetoothDevice = this.b;
        return bluetoothDevice != null ? bluetoothDevice.getAddress() : "00:00:00:00:00:00";
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.b;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBCI getConnectionParameter() {
        return this.f;
    }

    public BleGattX getGatt() {
        return this.c;
    }

    public AccessLock getLocker() {
        return this.p;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public ILogger getLogger() {
        return this.f8019a;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public int getMtu() {
        return this.c.getMtu();
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public String getName() {
        BluetoothDevice bluetoothDevice = this.b;
        String name = bluetoothDevice != null ? bluetoothDevice.getName() : null;
        return name == null ? "N/A" : name;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBPhy getPhy() {
        return this.e;
    }

    @Nullable
    public synchronized BleServiceX getService(BluetoothGattService bluetoothGattService) {
        Iterator<BleServiceX> it = this.d.iterator();
        while (it.hasNext()) {
            BleServiceX next = it.next();
            if (next.equals(bluetoothGattService)) {
                return next;
            }
        }
        return null;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public synchronized List<GBGattService> getService(UUID uuid) {
        if (this.d.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<BleServiceX> it = this.d.iterator();
        while (it.hasNext()) {
            BleServiceX next = it.next();
            if (next.getUuid().equals(uuid)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public ArrayList<BleServiceX> getServiceList() {
        return this.d;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public synchronized List<GBGattService> getServices() {
        if (this.d.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList(this.d);
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public TaskQueue getSetupSteps() {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    TaskQueue taskQueue = new TaskQueue();
                    this.h = taskQueue;
                    taskQueue.setAbortOnException(true);
                    this.h.setLogger(this.f8019a);
                }
            }
        }
        return this.h;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public int getState() {
        return this.c.getConnectionState();
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public boolean isBond() {
        return this.b.getBondState() == 12;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public boolean isConnected() {
        return this.c.isConnected();
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public boolean isDisconnected() {
        return this.c.getConnectionState() == 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public boolean isDiscovered() {
        return this.g;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public boolean isInService() {
        return this.expectConnection;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public boolean isReady() {
        return this.i;
    }

    public synchronized void onDiscovered(BluetoothGatt bluetoothGatt, ArrayList<String> arrayList) {
        String str;
        String str2;
        String str3;
        String str4;
        if (bluetoothGatt == null) {
            arrayList.add("gatt is null");
            return;
        }
        ILogger iLogger = this.f8019a;
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        this.d.ensureCapacity(services.size());
        HashMap hashMap = new HashMap(this.d.size());
        Iterator<BleServiceX> it = this.d.iterator();
        while (it.hasNext()) {
            BleServiceX next = it.next();
            UUID uuid = next.getUuid();
            ArrayList arrayList2 = (ArrayList) hashMap.get(uuid);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(4);
                hashMap.put(uuid, arrayList2);
            }
            arrayList2.add(next);
            if (iLogger != null) {
                iLogger.v(q, "old service: " + uuid.toString() + "  #" + next.getInstanceId());
            }
        }
        this.d.clear();
        for (BluetoothGattService bluetoothGattService : services) {
            UUID uuid2 = bluetoothGattService.getUuid();
            ArrayList arrayList3 = (ArrayList) hashMap.get(uuid2);
            if (arrayList3 != null && !arrayList3.isEmpty()) {
                Log.d("onDiscovered", "S =->    " + uuid2);
                BleServiceX bleServiceX = (BleServiceX) arrayList3.remove(0);
                bleServiceX.a(bluetoothGattService, arrayList);
                this.d.add(bleServiceX);
                if (arrayList3.isEmpty()) {
                    hashMap.remove(uuid2);
                }
                if (iLogger != null) {
                    str4 = q;
                    str3 = "update service: " + uuid2.toString() + "  #" + bleServiceX.getInstanceId();
                    iLogger.v(str4, str3);
                }
            }
            Log.d("onDiscovered", "S +->    " + uuid2);
            BleServiceX bleServiceX2 = new BleServiceX(this, uuid2);
            bleServiceX2.a(bluetoothGattService, arrayList);
            this.d.add(bleServiceX2);
            if (iLogger != null) {
                str4 = q;
                str3 = "add service: " + uuid2.toString() + "  #" + bleServiceX2.getInstanceId();
                iLogger.v(str4, str3);
            }
        }
        for (ArrayList arrayList4 : hashMap.values()) {
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                BleServiceX bleServiceX3 = (BleServiceX) it2.next();
                if (bleServiceX3.e) {
                    arrayList.add("Device " + getAddress() + " does not find required service: " + bleServiceX3.getUuid());
                }
                if (bleServiceX3.d) {
                    this.d.add(bleServiceX3);
                    if (iLogger != null) {
                        str = q;
                        str2 = "remain service: " + bleServiceX3.getUuid() + "  #" + bleServiceX3.getInstanceId();
                        iLogger.v(str, str2);
                        bleServiceX3.a(null, arrayList);
                    } else {
                        bleServiceX3.a(null, arrayList);
                    }
                } else if (iLogger != null) {
                    str = q;
                    str2 = "discard service: " + bleServiceX3.getUuid() + "  #" + bleServiceX3.getInstanceId();
                    iLogger.v(str, str2);
                    bleServiceX3.a(null, arrayList);
                } else {
                    bleServiceX3.a(null, arrayList);
                }
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure readCurrentPhy() {
        PhyRead phyRead = new PhyRead();
        phyRead.setRemoteDevice(this);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            phyRead.setLogger(iLogger);
        }
        return phyRead;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedureRssiRead readRemoteRssi() {
        RssiRead rssiRead = new RssiRead();
        rssiRead.setRemoteDevice(this);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            rssiRead.setLogger(iLogger);
        }
        return rssiRead;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure removeBond() {
        BondRemove bondRemove = new BondRemove();
        bondRemove.setRemoteDevice(this);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            bondRemove.setLogger(iLogger);
        }
        return bondRemove;
    }

    public synchronized boolean removeService(BleServiceX bleServiceX) {
        return this.d.remove(bleServiceX);
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBGattService requireService(UUID uuid, boolean z) {
        BleServiceX bleServiceX;
        synchronized (this) {
            Iterator<BleServiceX> it = this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    bleServiceX = null;
                    break;
                }
                bleServiceX = it.next();
                if (bleServiceX.getUuid().equals(uuid)) {
                    break;
                }
            }
        }
        return bleServiceX == null ? defineService(uuid, z) : bleServiceX;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.b = bluetoothDevice;
        this.c.setDevice(bluetoothDevice);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            String str = q;
            iLogger.v(str, "setBluetoothDevice: " + bluetoothDevice.getName() + HexStringBuilder.DEFAULT_SEPARATOR + bluetoothDevice.getAddress());
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure setConnectionPriority(int i) {
        CiSet ciSet = new CiSet();
        ciSet.setRemoteDevice(this);
        ciSet.setPriority(i);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            ciSet.setLogger(iLogger);
        }
        return ciSet;
    }

    public void setDiscovered(boolean z) {
        this.g = z;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public void setLogger(ILogger iLogger) {
        this.f8019a = iLogger;
        this.c.setLogger(iLogger);
        TaskQueue taskQueue = this.h;
        if (taskQueue != null) {
            taskQueue.setLogger(iLogger);
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure setMtu(int i) {
        MtuExchange mtuExchange = new MtuExchange();
        mtuExchange.setRemoteDevice(this);
        mtuExchange.setMtu(i);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            mtuExchange.setLogger(iLogger);
        }
        return mtuExchange;
    }

    @Override // com.goodix.ble.libble.v2.gb.GBRemoteDevice
    public GBProcedure setPreferredPhy(int i, int i2, int i3) {
        PhySet phySet = new PhySet();
        phySet.setRemoteDevice(this);
        phySet.setPhy(i, i2, i3);
        ILogger iLogger = this.f8019a;
        if (iLogger != null) {
            phySet.setLogger(iLogger);
        }
        return phySet;
    }
}
