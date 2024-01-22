package com.realsil.sdk.core.bluetooth.connection.le;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
@TargetApi(18)
/* loaded from: classes12.dex */
public final class BluetoothGattManager {
    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static boolean CLOSE_GATT_ENABLED = true;
    public static boolean DUMP_SERVICE = false;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;

    /* renamed from: a  reason: collision with root package name */
    public static BluetoothGattManager f13562a;
    public boolean b;
    public boolean c;
    public BluetoothManager d;
    public BluetoothAdapter e;
    public volatile boolean j;
    public Context l;
    public static int SDK_INT = Build.VERSION.SDK_INT;
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public final Object k = new Object();
    public HashMap<String, BluetoothGatt> g = new HashMap<>();
    public HashMap<String, Integer> i = new HashMap<>();
    public HashMap<String, List<BluetoothGattCallback>> h = new HashMap<>();
    public List<String> f = new CopyOnWriteArrayList();

    /* loaded from: classes12.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (BluetoothGattManager.this.b) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "<< %s\n(%d)%s", bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "<< %s", bluetoothGattCharacteristic.getUuid()));
                }
            }
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (BluetoothGattManager.this.b) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "%s << %s\n:\t(%d)%s", GattError.parse(i), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "%s << %s", GattError.parse(i), bluetoothGattCharacteristic.getUuid()));
                }
            } else if (value != null) {
                ZLogger.d(String.format(Locale.US, "%s << (%d)", GattError.parse(i), Integer.valueOf(value.length)));
            } else {
                ZLogger.d(String.format(Locale.US, "%s <<", GattError.parse(i)));
            }
            synchronized (BluetoothGattManager.this.k) {
                BluetoothGattManager.this.j = true;
                BluetoothGattManager.this.k.notifyAll();
            }
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(bluetoothGatt.getDevice().getAddress());
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (BluetoothGattManager.this.b) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "%s << %s\n(%d)%s", GattError.parse(i), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "%s << %s", GattError.parse(i), bluetoothGattCharacteristic.getUuid()));
                }
            }
            synchronized (BluetoothGattManager.this.k) {
                BluetoothGattManager.this.j = true;
                BluetoothGattManager.this.k.notifyAll();
            }
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BluetoothDevice device = bluetoothGatt.getDevice();
            if (device == null) {
                return;
            }
            String address = device.getAddress();
            ZLogger.v(String.format(Locale.US, "%s, status: %s , newState: %s", BluetoothHelper.formatAddress(address, true), GattError.parseConnectionError(i), BluetoothHelper.parseProfileState(i2)));
            if (i != 0) {
                BluetoothGattManager.this.i.put(address, 0);
            } else if (i2 == 2) {
                BluetoothGattManager.this.i.put(address, 2);
                BluetoothGattManager.this.g.put(address, bluetoothGatt);
            } else {
                BluetoothGattManager.this.i.put(address, 0);
            }
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
            byte[] value = bluetoothGattDescriptor.getValue();
            if (BluetoothGattManager.this.b) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "%s>> {\nCharacteristic:%s\nDescriptor:%s\nvalue:(%d)%s\n}", GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "%s>> {\nCharacteristic:%s\nDescriptor:%s}", GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid()));
                }
            }
            synchronized (BluetoothGattManager.this.k) {
                BluetoothGattManager.this.j = true;
                BluetoothGattManager.this.k.notifyAll();
            }
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @TargetApi(21)
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, "%s << mtu=%d, addr=%s", GattError.parse(i2), Integer.valueOf(i), BluetoothHelper.formatAddress(address, true)));
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onMtuChanged(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @RequiresApi(api = 26)
        public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyRead(bluetoothGatt, i, i2, i3);
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, "%s << %s: txPhy=%d, rxPhy=%d", BluetoothHelper.formatAddress(address, true), GattError.parse(i3), Integer.valueOf(i), Integer.valueOf(i2)));
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onPhyRead(bluetoothGatt, i, i2, i3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @RequiresApi(api = 26)
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, "%s << %s: txPhy=%d, rxPhy=%d", BluetoothHelper.formatAddress(address, true), GattError.parse(i3), Integer.valueOf(i), Integer.valueOf(i2)));
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onPhyUpdate(bluetoothGatt, i, i2, i3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            super.onReliableWriteCompleted(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, "%s << addr=%s", GattError.parse(i), BluetoothHelper.formatAddress(address, true)));
            if (BluetoothGattManager.DUMP_SERVICE) {
                for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
                    ZLogger.d(String.format(Locale.US, "service: type=%d, %d/%s", Integer.valueOf(bluetoothGattService.getType()), Integer.valueOf(bluetoothGattService.getInstanceId()), bluetoothGattService.getUuid().toString()));
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        ZLogger.v(String.format(Locale.US, "\tcharacteristic: %d/%s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
                    }
                }
            }
            List<BluetoothGattCallback> list = BluetoothGattManager.this.h.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (BluetoothGattCallback bluetoothGattCallback : list) {
                bluetoothGattCallback.onServicesDiscovered(bluetoothGatt, i);
            }
        }
    }

    public BluetoothGattManager(Context context) {
        this.b = false;
        this.c = false;
        this.l = context;
        this.b = RtkCore.DEBUG;
        this.c = RtkCore.VDBG;
        a();
    }

    public static BluetoothGattManager getInstance() {
        return f13562a;
    }

    public static synchronized void initial(Context context) {
        synchronized (BluetoothGattManager.class) {
            if (f13562a == null) {
                synchronized (BluetoothGattManager.class) {
                    if (f13562a == null) {
                        f13562a = new BluetoothGattManager(context.getApplicationContext());
                    }
                }
            }
        }
    }

    public void close(String str) {
        if (str == null) {
            return;
        }
        BluetoothGatt bluetoothGatt = this.g.get(str);
        if (bluetoothGatt != null) {
            if (isConnected(str)) {
                if (this.b) {
                    ZLogger.v("disconnect : " + BluetoothHelper.formatAddress(str, true));
                }
                bluetoothGatt.disconnect();
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (CLOSE_GATT_ENABLED) {
                if (this.c) {
                    ZLogger.v("closeGatt, addr:=" + BluetoothHelper.formatAddress(str, true));
                }
                bluetoothGatt.close();
            }
            this.g.remove(str);
        }
        HashMap<String, List<BluetoothGattCallback>> hashMap = this.h;
        if (hashMap != null) {
            hashMap.remove(str);
        }
        List<String> list = this.f;
        if (list == null || !list.contains(str)) {
            return;
        }
        this.f.remove(str);
    }

    public void closeAll() {
        List<String> list = this.f;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str : this.f) {
            close(str);
        }
    }

    public synchronized void closeGatt(String str) {
        closeGatt(str, CLOSE_GATT_ENABLED);
    }

    public boolean connect(String str, BluetoothGattCallback bluetoothGattCallback) {
        if (Build.VERSION.SDK_INT >= 23) {
            return connect(str, 2, bluetoothGattCallback);
        }
        return connect(str, 2, bluetoothGattCallback);
    }

    public boolean disconnectGatt(String str) {
        BluetoothGatt bluetoothGatt = this.g.get(str);
        List<BluetoothGattCallback> list = this.h.get(str);
        if (bluetoothGatt != null) {
            if (isConnected(str)) {
                if (this.b) {
                    ZLogger.v("disconnect : " + BluetoothHelper.formatAddress(str, true));
                }
                bluetoothGatt.disconnect();
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (list != null && list.size() > 0) {
                for (BluetoothGattCallback bluetoothGattCallback : list) {
                    bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, 0, 0);
                }
            }
            return true;
        }
        return false;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.e;
    }

    public List<String> getBluetoothDeviceAddresss() {
        return this.f;
    }

    public BluetoothGatt getBluetoothGatt(String str) {
        return this.g.get(str);
    }

    public List<BluetoothGattCallback> getCallback(String str) {
        HashMap<String, List<BluetoothGattCallback>> hashMap = this.h;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    public ArrayList<BluetoothDevice> getConnectDevices() {
        ArrayList<BluetoothDevice> arrayList = new ArrayList<>();
        for (String str : this.f) {
            if (isConnected(str)) {
                arrayList.add(getBluetoothGatt(str).getDevice());
            }
        }
        return arrayList;
    }

    public BluetoothDevice getConnectedDevice() {
        for (String str : this.f) {
            if (isConnected(str)) {
                return getBluetoothGatt(str).getDevice();
            }
        }
        return null;
    }

    public ArrayList<BluetoothDevice> getConnectedDevices() {
        ArrayList<BluetoothDevice> arrayList = new ArrayList<>();
        for (String str : this.f) {
            if (isConnected(str)) {
                arrayList.add(getBluetoothGatt(str).getDevice());
            }
        }
        return arrayList;
    }

    public String getDeviceName(String str) {
        BluetoothGatt bluetoothGatt = this.g.get(str);
        if (bluetoothGatt == null) {
            if (this.b) {
                ZLogger.w("no bluetoothGatt exist, addr=" + str);
                return null;
            }
            return null;
        }
        return bluetoothGatt.getDevice().getName();
    }

    public List<BluetoothGattService> getSupportedGattServices(String str) {
        if (this.g.get(str) == null) {
            return null;
        }
        return this.g.get(str).getServices();
    }

    public boolean isBluetoothSupported() {
        return this.e != null || a();
    }

    public boolean isCallbackRegisted(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        return callback != null && callback.contains(bluetoothGattCallback);
    }

    public boolean isConnected(String str) {
        Integer num = this.i.get(str);
        return num != null && num.intValue() == 2;
    }

    public boolean isHostConnected(String str) {
        BluetoothManager bluetoothManager = this.d;
        if (bluetoothManager == null) {
            if (this.b) {
                ZLogger.w("mBluetoothManager == null");
            }
            return false;
        }
        List<BluetoothDevice> connectedDevices = bluetoothManager.getConnectedDevices(7);
        if (connectedDevices != null) {
            for (BluetoothDevice bluetoothDevice : connectedDevices) {
                if (bluetoothDevice.getAddress().equals(str)) {
                    if (this.c) {
                        ZLogger.d("addr: " + str + ", Connected.");
                        return true;
                    }
                    return true;
                }
            }
        }
        if (this.c) {
            ZLogger.v("addr: " + str + ", Disconnected.");
        }
        return false;
    }

    public boolean readCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.e == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.g.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("unspecified address.");
            return false;
        }
        if (this.b) {
            ZLogger.d("addr: " + BluetoothHelper.formatAddress(str, true));
        }
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }

    public boolean readCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.j = false;
        if (readCharacteristic(str, bluetoothGattCharacteristic)) {
            synchronized (this.k) {
                try {
                    if (!this.j) {
                        if (this.b) {
                            ZLogger.v("wait for 3000ms");
                        }
                        this.k.wait(3000L);
                        if (this.b) {
                            ZLogger.v("wait time reached");
                        }
                    }
                } catch (InterruptedException e) {
                    ZLogger.e(e.toString());
                }
            }
            return true;
        }
        return false;
    }

    public synchronized void registerCallback(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        if (callback == null) {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.add(bluetoothGattCallback);
            this.h.put(str, copyOnWriteArrayList);
            return;
        }
        if (!callback.contains(bluetoothGattCallback)) {
            callback.add(bluetoothGattCallback);
            this.h.put(str, callback);
        }
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicIndication(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicNotification(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    public boolean setCharacteristicNotificationSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        this.j = false;
        if (setCharacteristicNotification(str, bluetoothGattCharacteristic, uuid, z)) {
            synchronized (this.k) {
                try {
                    if (!this.j) {
                        if (this.b) {
                            ZLogger.v("wait for 3000ms");
                        }
                        this.k.wait(3000L);
                        if (this.b) {
                            ZLogger.v("wait time reached");
                        }
                    }
                } catch (InterruptedException e) {
                    ZLogger.e(e.toString());
                }
            }
            return true;
        }
        return false;
    }

    public void unRegisterAllCallback(String str) {
        if (this.h.get(str) == null) {
            if (this.b) {
                ZLogger.d("mCallbacks.get(addr) == null");
                return;
            }
            return;
        }
        if (this.b) {
            ZLogger.v("addr: " + BluetoothHelper.formatAddress(str, true));
        }
        this.h.remove(str);
    }

    public synchronized void unRegisterCallback(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        if (callback == null) {
            if (this.b) {
                ZLogger.v("callback not registered, addr= " + BluetoothHelper.formatAddress(str, true));
            }
            return;
        }
        if (callback.contains(bluetoothGattCallback)) {
            callback.remove(bluetoothGattCallback);
            this.h.put(str, callback);
        }
    }

    public boolean writeCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.e == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.g.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("unspecified address.");
            return false;
        }
        return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    public synchronized boolean writeCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.j = false;
        if (writeCharacteristic(str, bluetoothGattCharacteristic)) {
            synchronized (this.k) {
                try {
                    if (!this.j) {
                        if (this.b) {
                            ZLogger.v("wait for 3000ms");
                        }
                        this.k.wait(3000L);
                        if (this.b) {
                            ZLogger.v("wait time reached");
                        }
                    }
                } catch (InterruptedException e) {
                    ZLogger.e(e.toString());
                }
            }
            return true;
        }
        return false;
    }

    public final boolean a() {
        if (this.d == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.l.getSystemService("bluetooth");
            this.d = bluetoothManager;
            if (bluetoothManager == null) {
                ZLogger.w("BLUETOOTH_SERVICE not supported.");
                return false;
            }
        }
        if (this.e == null) {
            BluetoothAdapter adapter = this.d.getAdapter();
            this.e = adapter;
            if (adapter == null) {
                ZLogger.w("BluetoothAdapter is not supported");
                return false;
            }
        }
        ZLogger.d("initialize success");
        return true;
    }

    public synchronized void closeGatt(String str, boolean z) {
        BluetoothGatt bluetoothGatt;
        if (str == null) {
            ZLogger.d(this.b, "Invalid address");
            return;
        }
        HashMap<String, BluetoothGatt> hashMap = this.g;
        if (hashMap != null) {
            if (z && (bluetoothGatt = hashMap.get(str)) != null) {
                if (this.c) {
                    ZLogger.v("closeGatt, addr:=" + BluetoothHelper.formatAddress(str, true));
                }
                bluetoothGatt.close();
            }
            this.g.remove(str);
        }
        HashMap<String, List<BluetoothGattCallback>> hashMap2 = this.h;
        if (hashMap2 != null) {
            hashMap2.remove(str);
        }
        List<String> list = this.f;
        if (list != null) {
            list.remove(str);
        }
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        if (this.e == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.g.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("BluetoothGatt can not be null, addr=" + BluetoothHelper.formatAddress(str, true));
            return false;
        } else if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic is null");
            return false;
        } else {
            if (this.b) {
                ZLogger.d("addr:=" + BluetoothHelper.formatAddress(str, true) + ", enabled=" + z);
            }
            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
            if (descriptor == null) {
                ZLogger.w("descriptor not found, uuid=" + uuid.toString());
                return false;
            }
            if (z) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            } else {
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            }
            bluetoothGatt.writeDescriptor(descriptor);
            return true;
        }
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        if (this.e == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.g.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("BluetoothGatt can not be null, addr=" + BluetoothHelper.formatAddress(str, true));
            return false;
        }
        if (this.b) {
            ZLogger.d("addr:=" + BluetoothHelper.formatAddress(str, true) + ", enabled=" + z);
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
        if (descriptor == null) {
            ZLogger.w("descriptor not found, uuid=" + uuid.toString());
            return false;
        }
        if (z) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        bluetoothGatt.writeDescriptor(descriptor);
        return true;
    }

    public boolean connect(String str, int i, BluetoothGattCallback bluetoothGattCallback) {
        if (Build.VERSION.SDK_INT >= 26) {
            return connect(str, false, i, 1, bluetoothGattCallback);
        }
        return connect(str, false, i, 1, bluetoothGattCallback);
    }

    public boolean connect(String str, int i, int i2, BluetoothGattCallback bluetoothGattCallback) {
        return connect(str, false, i, i2, bluetoothGattCallback);
    }

    public boolean connect(String str, boolean z, int i, int i2, BluetoothGattCallback bluetoothGattCallback) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        } else if (str == null) {
            ZLogger.w("unspecified address.");
            return false;
        } else {
            return connect(bluetoothAdapter.getRemoteDevice(str), z, i, i2, bluetoothGattCallback);
        }
    }

    public boolean connect(BluetoothDevice bluetoothDevice, boolean z, int i, int i2, BluetoothGattCallback bluetoothGattCallback) {
        BluetoothGatt connectGatt;
        BluetoothGatt bluetoothGatt;
        if (this.e == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        } else if (bluetoothDevice == null) {
            ZLogger.w("Device not found.  Unable to connect.");
            return false;
        } else {
            String address = bluetoothDevice.getAddress();
            if (this.f.contains(address) && (bluetoothGatt = this.g.get(address)) != null) {
                if (isConnected(address)) {
                    if (this.b) {
                        ZLogger.v("already connected, addr=" + BluetoothHelper.formatAddress(address, true));
                    }
                    registerCallback(address, bluetoothGattCallback);
                    if (bluetoothGattCallback != null) {
                        bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, 0, 2);
                    }
                    return true;
                } else if (z) {
                    registerCallback(address, bluetoothGattCallback);
                    if (this.b) {
                        ZLogger.v("re-connect previous device: " + address);
                    }
                    if (bluetoothGatt.connect()) {
                        this.i.put(address, 1);
                        return true;
                    }
                    ZLogger.d("reconnect failed.");
                    closeGatt(address);
                    return false;
                } else {
                    closeGatt(address);
                }
            }
            if (this.b) {
                ZLogger.v("create connection to " + BluetoothHelper.formatAddress(address, true));
            }
            registerCallback(address, bluetoothGattCallback);
            this.i.put(address, 1);
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 26) {
                connectGatt = bluetoothDevice.connectGatt(this.l, z, new a(), i, i2);
            } else if (i3 >= 23) {
                connectGatt = bluetoothDevice.connectGatt(this.l, z, new a(), i);
            } else {
                connectGatt = bluetoothDevice.connectGatt(this.l, z, new a());
            }
            if (connectGatt == null) {
                ZLogger.d("BluetoothGatt not exist.  Unable to connect.");
                this.i.put(address, 0);
                closeGatt(address);
                return false;
            }
            this.g.put(address, connectGatt);
            if (!this.f.contains(address)) {
                this.f.add(address);
            }
            return true;
        }
    }
}
