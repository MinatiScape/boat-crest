package com.realsil.sdk.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class BluetoothSppManager {
    public static UUID UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /* renamed from: a  reason: collision with root package name */
    public static volatile BluetoothSppManager f13568a;
    public BluetoothSpp c;
    public volatile boolean d;
    public final Object e = new Object();
    public BluetoothSppCallback f = new a();
    public List<BluetoothSppCallback> b = new CopyOnWriteArrayList();

    /* loaded from: classes12.dex */
    public class a extends BluetoothSppCallback {
        public a() {
        }

        @Override // com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            synchronized (BluetoothSppManager.this.b) {
                List<BluetoothSppCallback> list = BluetoothSppManager.this.b;
                if (list != null && list.size() > 0) {
                    for (BluetoothSppCallback bluetoothSppCallback : BluetoothSppManager.this.b) {
                        bluetoothSppCallback.onConnectionStateChanged(bluetoothDevice, z, i);
                    }
                }
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback
        public void onDataReceive(byte[] bArr) {
            super.onDataReceive(bArr);
            synchronized (BluetoothSppManager.this.b) {
                List<BluetoothSppCallback> list = BluetoothSppManager.this.b;
                if (list != null && list.size() > 0) {
                    for (BluetoothSppCallback bluetoothSppCallback : BluetoothSppManager.this.b) {
                        bluetoothSppCallback.onDataReceive(bArr);
                    }
                }
            }
        }
    }

    public static BluetoothSppManager getInstance() {
        return f13568a;
    }

    public static synchronized void initialize() {
        synchronized (BluetoothSppManager.class) {
            if (f13568a == null) {
                synchronized (BluetoothSppManager.class) {
                    if (f13568a == null) {
                        f13568a = new BluetoothSppManager();
                    }
                }
            }
        }
    }

    public final BluetoothSpp a() {
        if (this.c == null) {
            this.c = new BluetoothSpp(UUID_SECURE, this.f);
        }
        return this.c;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, BluetoothSppCallback bluetoothSppCallback) {
        register(bluetoothSppCallback);
        if (getConnectionState() == 512) {
            BluetoothDevice device = a().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                bluetoothSppCallback.onConnectionStateChanged(bluetoothDevice, true, 512);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        boolean connect = a().connect(bluetoothDevice, bluetoothSocket);
        if (!connect) {
            unregister(bluetoothSppCallback);
        }
        return connect;
    }

    public void destroy() {
        synchronized (this.b) {
            List<BluetoothSppCallback> list = this.b;
            if (list != null) {
                list.clear();
            }
        }
        disconnect();
    }

    public void disconnect() {
        a().stop();
    }

    public int getConnectionState() {
        return a().getConnectionState();
    }

    public void notifyAck() {
        synchronized (this.e) {
            this.d = true;
            this.e.notifyAll();
        }
    }

    public void register(BluetoothSppCallback bluetoothSppCallback) {
        synchronized (this.b) {
            if (this.b == null) {
                this.b = new CopyOnWriteArrayList();
            }
            if (!this.b.contains(bluetoothSppCallback)) {
                this.b.add(bluetoothSppCallback);
            }
            ZLogger.v("callback's size=" + this.b.size());
        }
    }

    public synchronized boolean sendPacket(byte[] bArr, boolean z) {
        if (bArr == null) {
            return false;
        }
        if (!z) {
            this.d = false;
        }
        if (!a().write(bArr)) {
            ZLogger.w("send spp data failed");
            return false;
        } else if (z) {
            return true;
        } else {
            synchronized (this.e) {
                if (this.d) {
                    return true;
                }
                try {
                    this.e.wait(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return this.d;
            }
        }
    }

    public void unregister(BluetoothSppCallback bluetoothSppCallback) {
        synchronized (this.b) {
            List<BluetoothSppCallback> list = this.b;
            if (list != null) {
                list.remove(bluetoothSppCallback);
            }
        }
    }

    public boolean write(byte[] bArr) {
        return a().write(bArr);
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSppCallback bluetoothSppCallback) {
        register(bluetoothSppCallback);
        if (getConnectionState() == 512) {
            BluetoothDevice device = a().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                ZLogger.d("connection already connected");
                bluetoothSppCallback.onConnectionStateChanged(bluetoothDevice, true, 512);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        boolean connect = a().connect(bluetoothDevice);
        if (!connect) {
            unregister(bluetoothSppCallback);
        }
        return connect;
    }
}
