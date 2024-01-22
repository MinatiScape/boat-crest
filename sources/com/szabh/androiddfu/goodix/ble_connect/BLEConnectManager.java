package com.szabh.androiddfu.goodix.ble_connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.polidea.rxandroidble2.ClientComponent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
/* loaded from: classes12.dex */
public class BLEConnectManager {
    public static final String TIME_OUT_CONNECT = "Timeout on connect";
    public static final String TIME_OUT_DISCVERY_SERVICE = "Timeout on discovery service";
    public static final String TIME_OUT_ENABLE_NOTIFY = "Timeout on enable_notify";
    public static final String TIME_OUT_MTU_CHANGE = "Timeout on mtu request";
    public static final int WRITE_CHARACTER_TIME_OUT = 3;
    public static final UUID j = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    /* renamed from: a  reason: collision with root package name */
    public BluetoothManager f13717a;
    public BluetoothAdapter b;
    public Context d;
    public BLEConnectCallback e;
    public boolean f;
    public Timer g;
    public String h;
    public BluetoothGatt c = null;
    public final BluetoothGattCallback i = new b();

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BLEConnectManager.this.c != null) {
                BLEConnectManager.this.c.close();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b extends BluetoothGattCallback {
        public b() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BLEConnectManager.this.e.onBleCharacteristicNotify(bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                BLEConnectManager.this.e.onBleCharacteristicWriteComplete(bluetoothGattCharacteristic);
            } else {
                BLEConnectManager.this.e.onBleError("Error on writing characteristic", i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i == 0 && i2 == 2) {
                BLEConnectManager.this.f = true;
                BLEConnectManager.this.j();
                BLEConnectManager.this.e.onBleConnected();
                BLEConnectManager.this.i(10000, BLEConnectManager.TIME_OUT_DISCVERY_SERVICE);
                bluetoothGatt.discoverServices();
            } else if (i2 == 0) {
                BLEConnectManager.this.j();
                BLEConnectManager.this.e.onBleDisconnected();
                bluetoothGatt.close();
            } else if (i != 0) {
                BLEConnectManager.this.e.onBleError("Error on connection state change", i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            byte[] value;
            if (i == 0) {
                if (bluetoothGattDescriptor != null && BLEConnectManager.j.equals(bluetoothGattDescriptor.getUuid()) && (value = bluetoothGattDescriptor.getValue()) != null && value.length == 2 && value[1] == 0 && value[0] == 1) {
                    BLEConnectManager.this.j();
                    BLEConnectManager.this.e.onBleNotifyEnable();
                    return;
                }
                return;
            }
            BLEConnectManager.this.e.onBleError("Error on writing descriptor", i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            BLEConnectManager.this.j();
            BLEConnectManager.this.e.onBleMtuChanged(i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            BLEConnectManager.this.j();
            if (i == 0) {
                BLEConnectManager.this.e.onBleServicesDiscovered(bluetoothGatt);
            } else {
                BLEConnectManager.this.e.onBleError("Error on discovering services", i);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BLEConnectManager.this.g = null;
            Log.i("sss", ClientComponent.NamedSchedulers.TIMEOUT);
            BLEConnectManager.this.e.onBleTimeOut(BLEConnectManager.this.h);
        }
    }

    public BLEConnectManager(Context context, BLEConnectCallback bLEConnectCallback) {
        this.e = bLEConnectCallback;
        this.d = context;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.f13717a = bluetoothManager;
        this.b = bluetoothManager.getAdapter();
    }

    public boolean changeMtu(int i) {
        if (i < 23) {
            i = 23;
        }
        if (i > 517) {
            i = 517;
        }
        BluetoothGatt bluetoothGatt = this.c;
        if (bluetoothGatt == null) {
            return false;
        }
        i(5000, TIME_OUT_MTU_CHANGE);
        return bluetoothGatt.requestMtu(i);
    }

    public void connect(BluetoothDevice bluetoothDevice, boolean z, int i) {
        if (bluetoothDevice == null) {
            return;
        }
        i(i, TIME_OUT_CONNECT);
        this.c = bluetoothDevice.connectGatt(this.d, z, this.i);
    }

    public void disconnect() {
        BluetoothGatt bluetoothGatt;
        if (this.b == null || (bluetoothGatt = this.c) == null) {
            return;
        }
        bluetoothGatt.disconnect();
        new Handler(Looper.getMainLooper()).postDelayed(new a(), 5000L);
    }

    public boolean enableNotify(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        i(5000, TIME_OUT_ENABLE_NOTIFY);
        BluetoothGatt bluetoothGatt = this.c;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 16) == 0) {
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(j);
        if (descriptor == null) {
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        BluetoothGattCharacteristic characteristic = descriptor.getCharacteristic();
        int writeType = characteristic.getWriteType();
        characteristic.setWriteType(2);
        boolean writeDescriptor = this.c.writeDescriptor(descriptor);
        characteristic.setWriteType(writeType);
        return writeDescriptor;
    }

    public final void i(int i, String str) {
        this.h = str;
        this.g = null;
        Timer timer = new Timer();
        this.g = timer;
        timer.schedule(new c(), i);
    }

    public final void j() {
        Timer timer = this.g;
        if (timer != null) {
            timer.cancel();
            this.g = null;
        }
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        BluetoothGatt bluetoothGatt = this.c;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null) {
            return false;
        }
        bluetoothGattCharacteristic.setValue(bArr);
        bluetoothGattCharacteristic.setWriteType(i);
        if ((bluetoothGattCharacteristic.getProperties() & 12) == 0) {
            return false;
        }
        return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }
}
