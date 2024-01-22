package com.goodix.ble.libble.center;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.impl.BleRemoteDevice;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes5.dex */
public final class BleCenter {
    public static final int EVT_ADDED = 416;
    public static final int EVT_REMOVED = 598;
    public static final int EVT_SELECTED = 269;
    public static final BleCenter h = new BleCenter();
    public static Context i = null;
    public static ILogger j = null;
    public BleItem c;

    /* renamed from: a  reason: collision with root package name */
    public HashMap<String, BleItem> f8007a = new HashMap<>();
    public IDeviceModelCreator b = null;
    public int d = 0;
    public Event<BleItem> e = new Event<>(this, 416);
    public Event<BleItem> f = new Event<>(this, EVT_REMOVED);
    public Event<BleItem> g = new Event<>(this, 269);

    /* loaded from: classes5.dex */
    public interface IDeviceModelCreator {
        Object onCreateDeviceModel(BleItem bleItem);
    }

    public static BleCenter get() {
        return h;
    }

    public static Context getContext() {
        return i;
    }

    public static void setContext(Context context) {
        if (context != null) {
            i = context.getApplicationContext();
        }
    }

    public static void setRootLogger(ILogger iLogger) {
        j = iLogger;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0057 A[Catch: all -> 0x0067, TryCatch #0 {, blocks: (B:7:0x0008, B:9:0x0016, B:11:0x0033, B:12:0x0043, B:16:0x0052, B:18:0x0057, B:13:0x0047, B:15:0x004d, B:19:0x005e), top: B:30:0x0008 }] */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.goodix.ble.libble.center.BleItem addDevice(android.bluetooth.BluetoothDevice r5) {
        /*
            r4 = this;
            android.content.Context r0 = com.goodix.ble.libble.center.BleCenter.i
            if (r0 == 0) goto L72
            if (r5 == 0) goto L6a
            r1 = 0
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, com.goodix.ble.libble.center.BleItem> r2 = r4.f8007a     // Catch: java.lang.Throwable -> L67
            java.lang.String r3 = r5.getAddress()     // Catch: java.lang.Throwable -> L67
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> L67
            com.goodix.ble.libble.center.BleItem r2 = (com.goodix.ble.libble.center.BleItem) r2     // Catch: java.lang.Throwable -> L67
            if (r2 != 0) goto L5e
            com.goodix.ble.libble.center.BleItem r2 = new com.goodix.ble.libble.center.BleItem     // Catch: java.lang.Throwable -> L67
            r2.<init>()     // Catch: java.lang.Throwable -> L67
            java.util.HashMap<java.lang.String, com.goodix.ble.libble.center.BleItem> r1 = r4.f8007a     // Catch: java.lang.Throwable -> L67
            java.lang.String r3 = r5.getAddress()     // Catch: java.lang.Throwable -> L67
            r1.put(r3, r2)     // Catch: java.lang.Throwable -> L67
            com.goodix.ble.libble.v2.impl.BleRemoteDevice r1 = new com.goodix.ble.libble.v2.impl.BleRemoteDevice     // Catch: java.lang.Throwable -> L67
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L67
            r1.setBluetoothDevice(r5)     // Catch: java.lang.Throwable -> L67
            r2.a(r1)     // Catch: java.lang.Throwable -> L67
            int r5 = r4.d     // Catch: java.lang.Throwable -> L67
            if (r5 <= 0) goto L47
            com.goodix.ble.libcomx.logger.RingLogger r5 = new com.goodix.ble.libcomx.logger.RingLogger     // Catch: java.lang.Throwable -> L67
            int r0 = r4.d     // Catch: java.lang.Throwable -> L67
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L67
            r2.logger = r5     // Catch: java.lang.Throwable -> L67
            com.goodix.ble.libcomx.ILogger r0 = com.goodix.ble.libble.center.BleCenter.j     // Catch: java.lang.Throwable -> L67
            r5.setLogger(r0)     // Catch: java.lang.Throwable -> L67
            com.goodix.ble.libcomx.logger.RingLogger r5 = r2.logger     // Catch: java.lang.Throwable -> L67
        L43:
            r1.setLogger(r5)     // Catch: java.lang.Throwable -> L67
            goto L52
        L47:
            com.goodix.ble.libcomx.ILogger r5 = com.goodix.ble.libble.center.BleCenter.j     // Catch: java.lang.Throwable -> L67
            boolean r0 = r5 instanceof com.goodix.ble.libcomx.logger.RingLogger     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L52
            com.goodix.ble.libcomx.logger.RingLogger r5 = (com.goodix.ble.libcomx.logger.RingLogger) r5     // Catch: java.lang.Throwable -> L67
            r2.logger = r5     // Catch: java.lang.Throwable -> L67
            goto L43
        L52:
            r1 = 1
            com.goodix.ble.libble.center.BleCenter$IDeviceModelCreator r5 = r4.b     // Catch: java.lang.Throwable -> L67
            if (r5 == 0) goto L5e
            java.lang.Object r5 = r5.onCreateDeviceModel(r2)     // Catch: java.lang.Throwable -> L67
            r2.b(r5)     // Catch: java.lang.Throwable -> L67
        L5e:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L66
            com.goodix.ble.libcomx.event.Event<com.goodix.ble.libble.center.BleItem> r5 = r4.e
            r5.postEvent(r2)
        L66:
            return r2
        L67:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L67
            throw r5
        L6a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "BluetoothDevice is null."
            r5.<init>(r0)
            throw r5
        L72:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Please call setContext() before calling addDevice()."
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goodix.ble.libble.center.BleCenter.addDevice(android.bluetooth.BluetoothDevice):com.goodix.ble.libble.center.BleItem");
    }

    public BleItem addDevice(String str) {
        return addDevice(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str));
    }

    public Event<BleItem> evtAdded() {
        return this.e;
    }

    public Event<BleItem> evtRemoved() {
        return this.f;
    }

    public Event<BleItem> evtSelected() {
        return this.g;
    }

    public BleItem getDevice(BluetoothDevice bluetoothDevice) {
        BleItem bleItem;
        if (bluetoothDevice == null) {
            return null;
        }
        synchronized (this) {
            bleItem = this.f8007a.get(bluetoothDevice.getAddress());
        }
        return bleItem;
    }

    public BleItem getDevice(String str) {
        BleItem bleItem;
        if (str == null) {
            return null;
        }
        synchronized (this) {
            bleItem = this.f8007a.get(str);
        }
        return bleItem;
    }

    public List<BleItem> getDevices(List<BleItem> list) {
        if (list == null) {
            list = new ArrayList<>(this.f8007a.size());
        }
        synchronized (this) {
            list.addAll(this.f8007a.values());
        }
        return list;
    }

    public BleItem getSelectedDevice() {
        return this.c;
    }

    public <M> M getSelectedDeviceModel() {
        BleItem bleItem = this.c;
        if (bleItem != null) {
            return (M) bleItem.getDeviceModel();
        }
        return null;
    }

    public BleItem remove(String str) {
        BleItem device = getDevice(str);
        remove(device);
        return device;
    }

    public void remove(BleItem bleItem) {
        boolean z;
        if (bleItem == null) {
            return;
        }
        GBRemoteDevice gatt = bleItem.getGatt();
        synchronized (this) {
            z = this.f8007a.remove(gatt.getAddress()) != null;
        }
        if (z) {
            if (gatt.isDisconnected()) {
                ((BleRemoteDevice) gatt).dispose();
            } else {
                gatt.disconnect(false).startProcedure();
            }
            this.f.postEvent(bleItem);
            if (this.c == bleItem) {
                setSelectedDevice(null);
            }
        }
    }

    public void setDeviceModelCreator(IDeviceModelCreator iDeviceModelCreator) {
        this.b = iDeviceModelCreator;
    }

    public void setMaxDeviceLogCount(int i2) {
        this.d = i2;
    }

    public void setSelectedDevice(BleItem bleItem) {
        if (this.c != bleItem) {
            boolean z = false;
            synchronized (this) {
                if (this.c != bleItem) {
                    this.c = bleItem;
                    z = true;
                }
            }
            if (z) {
                this.g.postEvent(bleItem);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0057 A[Catch: all -> 0x0067, TryCatch #0 {, blocks: (B:7:0x0010, B:9:0x001a, B:11:0x002b, B:13:0x0036, B:21:0x0052, B:23:0x0057, B:14:0x003a, B:15:0x0041, B:16:0x0045, B:18:0x004b, B:24:0x005e), top: B:35:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.goodix.ble.libble.center.BleItem wrapDevice(com.goodix.ble.libble.v2.gb.GBRemoteDevice r5) {
        /*
            r4 = this;
            android.content.Context r0 = com.goodix.ble.libble.center.BleCenter.i
            if (r0 == 0) goto L72
            if (r5 == 0) goto L6a
            r0 = 0
            java.lang.String r1 = r5.getAddress()
            com.goodix.ble.libcomx.ILogger r2 = r5.getLogger()
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, com.goodix.ble.libble.center.BleItem> r3 = r4.f8007a     // Catch: java.lang.Throwable -> L67
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L67
            com.goodix.ble.libble.center.BleItem r3 = (com.goodix.ble.libble.center.BleItem) r3     // Catch: java.lang.Throwable -> L67
            if (r3 != 0) goto L5e
            com.goodix.ble.libble.center.BleItem r3 = new com.goodix.ble.libble.center.BleItem     // Catch: java.lang.Throwable -> L67
            r3.<init>()     // Catch: java.lang.Throwable -> L67
            java.util.HashMap<java.lang.String, com.goodix.ble.libble.center.BleItem> r0 = r4.f8007a     // Catch: java.lang.Throwable -> L67
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L67
            r3.a(r5)     // Catch: java.lang.Throwable -> L67
            int r0 = r4.d     // Catch: java.lang.Throwable -> L67
            if (r0 <= 0) goto L45
            com.goodix.ble.libcomx.logger.RingLogger r0 = new com.goodix.ble.libcomx.logger.RingLogger     // Catch: java.lang.Throwable -> L67
            int r1 = r4.d     // Catch: java.lang.Throwable -> L67
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L67
            r3.logger = r0     // Catch: java.lang.Throwable -> L67
            if (r2 == 0) goto L3a
            r0.setLogger(r2)     // Catch: java.lang.Throwable -> L67
            goto L52
        L3a:
            com.goodix.ble.libcomx.ILogger r1 = com.goodix.ble.libble.center.BleCenter.j     // Catch: java.lang.Throwable -> L67
            r0.setLogger(r1)     // Catch: java.lang.Throwable -> L67
            com.goodix.ble.libcomx.logger.RingLogger r0 = r3.logger     // Catch: java.lang.Throwable -> L67
        L41:
            r5.setLogger(r0)     // Catch: java.lang.Throwable -> L67
            goto L52
        L45:
            com.goodix.ble.libcomx.ILogger r0 = com.goodix.ble.libble.center.BleCenter.j     // Catch: java.lang.Throwable -> L67
            boolean r1 = r0 instanceof com.goodix.ble.libcomx.logger.RingLogger     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L52
            com.goodix.ble.libcomx.logger.RingLogger r0 = (com.goodix.ble.libcomx.logger.RingLogger) r0     // Catch: java.lang.Throwable -> L67
            r3.logger = r0     // Catch: java.lang.Throwable -> L67
            if (r2 != 0) goto L52
            goto L41
        L52:
            r0 = 1
            com.goodix.ble.libble.center.BleCenter$IDeviceModelCreator r5 = r4.b     // Catch: java.lang.Throwable -> L67
            if (r5 == 0) goto L5e
            java.lang.Object r5 = r5.onCreateDeviceModel(r3)     // Catch: java.lang.Throwable -> L67
            r3.b(r5)     // Catch: java.lang.Throwable -> L67
        L5e:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L66
            com.goodix.ble.libcomx.event.Event<com.goodix.ble.libble.center.BleItem> r5 = r4.e
            r5.postEvent(r3)
        L66:
            return r3
        L67:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L67
            throw r5
        L6a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "GBRemoteDevice is null."
            r5.<init>(r0)
            throw r5
        L72:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Please call setContext() before calling addDevice()."
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goodix.ble.libble.center.BleCenter.wrapDevice(com.goodix.ble.libble.v2.gb.GBRemoteDevice):com.goodix.ble.libble.center.BleItem");
    }
}
