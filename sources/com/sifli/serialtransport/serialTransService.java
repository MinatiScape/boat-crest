package com.sifli.serialtransport;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
/* loaded from: classes12.dex */
public class serialTransService extends Service {
    public static final String BROADCAST_SERIAL_TRANS = "Sifli.serialTrans.broadcast.BROADCAST_SERIAL_TRANS";
    public static final int ERROR_BLUETOOTH_OFF = 108;
    public static final int ERROR_CONNECTION_STATE = 105;
    public static final int ERROR_DISCOVERY = 106;
    public static final int ERROR_DISCOVERY_TIMEOUT = 102;
    public static final int ERROR_SERVICE_DISCOVERY_NOT_STARTED = 107;
    public static final int ERROR_TIME_OUT = 101;
    public static final int ERROR_WRITE_CCCD_TIMEOUT = 103;
    public static final int ERROR_WRITE_COMMAND_TIMEOUT = 104;
    public static final int ERROR_WRITE_FAIL = 100;
    public static final String EXTRA_DEVICE_ADDRESS = "Sifli.serialTrans.EXTRA_DEVICE_ADDRESS";
    public static final String EXTRA_SERIAL_TRANS_MSG = "Sifli.serialTrans.broadcast.EXTRA_SERIAL_TRANS_MSG";
    public static final String EXTRA_SERIAL_TRANS_STATUS = "Sifli.serialTrans.broadcast.EXTRA_SERIAL_TRANS_STATUS";
    public static final int SEND_NO_ERROR = 0;
    public static final int SERIAL_BLE_CONNECT_FAIL = 132;
    public static final int SERIAL_BLE_CONNECT_SUCCESS = 131;
    public static final int SERIAL_BLE_DISCONNECT = 130;
    public static final int SERIAL_MISS_HEADER = 121;
    public static final int SERIAL_OVER_LENGTH = 120;
    public static final String SERIAL_TRAM_DATA = "00000000-0000-0200-6473-5f696c666973";
    public static final int SIFLI_ERROR_PERMISSIONS = 112;
    public static final int SIFLI_GATT_CONNECT_FAIL = 111;
    public static final int SIFLI_GATT_DISCOVERY_FAILED = 109;
    public static final int SIFLI_GATT_DISCOVERY_NOT_FOUND = 110;
    public static final int STATE_CLOSED = 5;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTED_AND_READY = 3;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 4;
    public static final String UUID_CCC = "00002902-0000-1000-8000-00805f9b34fb";
    public OnSerialTransListener A;
    public String B;
    public boolean C;
    public serialTransBinder h;
    public BluetoothAdapter i;
    public BluetoothDevice j;
    public BluetoothGatt k;
    public BluetoothGattCharacteristic l;
    public int mConnectionState;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public int t;
    public int v;
    public int w;
    public Handler z;
    public boolean m = false;
    public final Object n = new Object();
    public int s = 0;
    public Handler u = new Handler();
    public byte[] x = new byte[0];
    public final ByteOrder y = ByteOrder.LITTLE_ENDIAN;
    public final BluetoothGattCallback D = new a();
    public final Runnable E = new b();
    public final Runnable F = new c();
    public final Runnable G = new d();

    /* loaded from: classes12.dex */
    public class Binder extends android.os.Binder {
        public Binder() {
        }

        public serialTransService getService() {
            return serialTransService.this;
        }
    }

    /* loaded from: classes12.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getUuid().toString().equals("00000000-0000-0200-6473-5f696c666973")) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                byte b = value[0];
                byte b2 = value[1];
                if (b2 == 0) {
                    int M = serialTransService.this.M(value, 2);
                    byte[] bArr = new byte[M];
                    System.arraycopy(value, 4, bArr, 0, M);
                    serialTransService.this.A.onSerialDataReceive(b, bArr);
                    return;
                } else if (b2 == 1) {
                    int M2 = serialTransService.this.M(value, 2);
                    serialTransService.this.v = M2;
                    serialTransService.this.w = 0;
                    serialTransService.this.x = new byte[M2];
                    int length = value.length - 4;
                    System.arraycopy(value, 4, serialTransService.this.x, serialTransService.this.w, length);
                    serialTransService.x(serialTransService.this, length);
                    return;
                } else if (b2 != 2) {
                    if (b2 == 3) {
                        int length2 = value.length - 2;
                        if (serialTransService.this.w + length2 == serialTransService.this.v) {
                            System.arraycopy(value, 2, serialTransService.this.x, serialTransService.this.w, length2);
                            serialTransService.x(serialTransService.this, length2);
                            serialTransService.this.A.onSerialDataReceive(b, serialTransService.this.x);
                            return;
                        }
                        Log.e("sifli-serial", "last packet len error, dataLen: " + length2 + ", serial index: " + serialTransService.this.w + ", serial len: " + serialTransService.this.v);
                        serialTransService.this.A.onSerialTransSend(120);
                        return;
                    }
                    return;
                } else {
                    int length3 = value.length - 2;
                    if (serialTransService.this.v != 0) {
                        if (serialTransService.this.w + length3 <= serialTransService.this.v) {
                            System.arraycopy(value, 2, serialTransService.this.x, serialTransService.this.w, length3);
                            serialTransService.x(serialTransService.this, length3);
                            return;
                        }
                        Log.e("sifli-serial", "continue packet len error, dataLen: " + length3 + ", serial index: " + serialTransService.this.w + ", serial len: " + serialTransService.this.v);
                        serialTransService.this.A.onSerialTransSend(120);
                        return;
                    }
                    Log.e("sifli-serial", "not receive first packet!");
                    serialTransService.this.A.onSerialTransSend(121);
                    return;
                }
            }
            Log.d("sifli-serial", "other notify");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i != 0) {
                Log.e("sifli-serial", "Write fail: " + i);
                serialTransService.this.o = i;
            }
            serialTransService.this.q = true;
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            Log.d("sifli-serial", "serial onConnectionStateChange status " + i + ", newState " + i2);
            if (i != 0) {
                Log.d("sifli-serial", "Bluetooth disconnect with: " + i);
                if (i != 8 && i != 19 && i != 22) {
                    Log.e("sifli-serial", "Connection state change error: " + i + " newState: " + i2);
                } else {
                    Log.w("sifli-serial", "Target device disconnected with status: " + i);
                }
                serialTransService.this.o = 105;
                serialTransService.this.p = i;
                if (i2 == 0) {
                    serialTransService serialtransservice = serialTransService.this;
                    serialtransservice.mConnectionState = 0;
                    serialtransservice.A.onSerialTransSend(130);
                    serialTransService.this.A.onSerialTransBLEStateChanged(0);
                }
            } else if (i2 == 2) {
                serialTransService serialtransservice2 = serialTransService.this;
                serialtransservice2.mConnectionState = 2;
                serialtransservice2.waitFor(500L);
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 31 && ContextCompat.checkSelfPermission(serialTransService.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    Log.e("sifli-serial", "no Permission");
                    serialTransService.this.o = 112;
                    synchronized (serialTransService.this.n) {
                        serialTransService.this.n.notifyAll();
                    }
                    return;
                }
                if (i3 >= 21) {
                    bluetoothGatt.requestMtu(247);
                }
                serialTransService.this.waitFor(500L);
                if (i3 >= 21) {
                    bluetoothGatt.requestConnectionPriority(1);
                }
                serialTransService.this.waitFor(500L);
                if (i3 >= 26) {
                    bluetoothGatt.setPreferredPhy(2, 2, 0);
                }
                serialTransService.this.waitFor(1000L);
                Log.i("sifli-serial", "Connected to GATT server.");
            } else if (i2 == 0) {
                Log.i("sifli-serial", "Disconnected from GATT server");
                serialTransService.this.k.close();
                serialTransService serialtransservice3 = serialTransService.this;
                serialtransservice3.mConnectionState = 0;
                serialtransservice3.A.onSerialTransBLEStateChanged(0);
            }
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            Log.i("sifli-serial", "onDescriptorWrite");
            serialTransService.this.r = true;
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            Log.d("sifli-serial", "status " + i2 + ", mtu " + i);
            serialTransService.this.t = i;
            if (i > 247) {
                serialTransService.this.s = 244;
            } else {
                serialTransService.this.s = i - 3;
            }
            serialTransService.this.A.onSerialMTUChangeResult(i);
            serialTransService serialtransservice = serialTransService.this;
            if (serialtransservice.C) {
                return;
            }
            serialtransservice.C = true;
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(serialtransservice, "android.permission.BLUETOOTH_CONNECT") != 0) {
                Log.e("sifli-serial", "no Permission");
                serialTransService.this.o = 112;
                synchronized (serialTransService.this.n) {
                    serialTransService.this.n.notifyAll();
                }
                return;
            }
            bluetoothGatt.discoverServices();
            serialTransService.this.u.postDelayed(serialTransService.this.E, 40000L);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            serialTransService.this.u.removeCallbacks(serialTransService.this.E);
            int i2 = 0;
            if (i != 0) {
                Log.w("sifli-serial", "onServicesDiscovered received: " + i);
                serialTransService.this.o = 106;
                serialTransService.this.p = i;
                serialTransService.this.O(109, i);
            } else {
                Log.d("sifli-serial", "onServicesDiscovered");
                int i3 = 0;
                while (i2 < bluetoothGatt.getServices().size()) {
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGatt.getServices().get(i2).getCharacteristics()) {
                        Log.e("sifli-serial", "find uuid: " + bluetoothGattCharacteristic.getUuid().toString() + ", expect: 00000000-0000-0200-6473-5f696c666973");
                        if (bluetoothGattCharacteristic.getUuid().toString().equals("00000000-0000-0200-6473-5f696c666973")) {
                            Log.i("sifli-serial", "find serial trans UUID");
                            serialTransService.this.l = bluetoothGattCharacteristic;
                            serialTransService.this.mConnectionState = 3;
                            i3 = 1;
                        }
                    }
                    i2++;
                }
                i2 = i3;
            }
            if (i2 == 0) {
                Log.e("sifli-serial", "fail to find target uuid");
                serialTransService.this.N(110);
                serialTransService.this.mConnectionState = 4;
            }
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-serial", "discovery timeout");
            serialTransService.this.o = 102;
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-serial", "write time out");
            serialTransService.this.o = 104;
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-serial", "write descriptor timeout");
            serialTransService.this.o = 103;
            synchronized (serialTransService.this.n) {
                serialTransService.this.n.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Handler.Callback {
        public e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int length;
            int i = message.what;
            if (i == 0 || i == 1) {
                byte[] byteArray = message.getData().getByteArray("Sifli.serial.BLE_DATA");
                if (serialTransService.this.l == null) {
                    return false;
                }
                byte b = byteArray[1];
                if (b != 0 && b != 1) {
                    length = byteArray.length - 2;
                } else {
                    length = byteArray.length - 4;
                }
                if (serialTransService.this.m) {
                    Log.d("sifli-serial", "message type " + message.what);
                }
                if (message.what == 1) {
                    serialTransService.this.A.onSerialTransProgress(length);
                }
                serialTransService.this.l.setValue(byteArray);
                serialTransService.this.l.setWriteType(1);
                serialTransService.this.q = false;
                if (serialTransService.this.k == null) {
                    return false;
                }
                if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(serialTransService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    serialTransService.this.k.writeCharacteristic(serialTransService.this.l);
                    serialTransService.this.u.postDelayed(serialTransService.this.F, 5000L);
                    synchronized (serialTransService.this.n) {
                        while (!serialTransService.this.q && serialTransService.this.o == 0) {
                            try {
                                serialTransService.this.n.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        serialTransService.this.u.removeCallbacks(serialTransService.this.F);
                    }
                    serialTransService.this.A.onSerialTransSend(serialTransService.this.o);
                } else {
                    Log.e("sifli-serial", "no Permission");
                    serialTransService.this.o = 112;
                    synchronized (serialTransService.this.n) {
                        serialTransService.this.n.notifyAll();
                    }
                    return false;
                }
            }
            if (!serialTransService.this.z.hasMessages(0) && !serialTransService.this.z.hasMessages(1)) {
                serialTransService.this.A.onSerialTranSendFinish();
            }
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public class serialTransBinder extends Binder {
        public serialTransBinder() {
            super();
        }

        public void connectBluetooth() {
            int i;
            BluetoothManager bluetoothManager = (BluetoothManager) serialTransService.this.getSystemService("bluetooth");
            if (bluetoothManager == null) {
                Log.e("sifli-serial", "Unable to initialize BluetoothManager.");
            } else {
                serialTransService.this.i = bluetoothManager.getAdapter();
                serialTransService serialtransservice = serialTransService.this;
                if (!serialtransservice.L(serialtransservice.B, serialtransservice.D)) {
                    serialTransService.this.N(111);
                    Log.d("sifli-serial", "fail to connect");
                } else {
                    serialTransService serialtransservice2 = serialTransService.this;
                    serialtransservice2.R(serialtransservice2.k, serialTransService.this.l);
                    synchronized (serialTransService.this.n) {
                        while (!serialTransService.this.r && serialTransService.this.o == 0) {
                            try {
                                serialTransService.this.n.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    serialTransService.this.u.removeCallbacks(serialTransService.this.G);
                    if (serialTransService.this.o == 0) {
                        i = 0;
                        serialTransService.this.A.onSerialBluetoothConnect(i);
                    }
                }
            }
            i = 132;
            serialTransService.this.A.onSerialBluetoothConnect(i);
        }

        public void doTask() {
            Log.e("sifli-serial", "do task");
            serialTransService.this.A.onSerialTransSend(0);
        }

        public void serialTransDebugProgress() {
            serialTransService.this.m = true;
        }

        public void serialTransSend(byte[] bArr, int i) {
            serialTransService.this.Q(bArr, bArr.length, i, 0);
        }

        public void serialTransSendBlank() {
            int i = serialTransService.this.s - 4;
            serialTransService.this.Q(new byte[i], i, 0, 0);
        }

        public void serialTransSendProgress(byte[] bArr, int i) {
            serialTransService.this.Q(bArr, bArr.length, i, 1);
        }

        public void serialTransSetMtu(int i) {
            Log.i("sifli-serial", "serialTransSetMtu " + i);
            if (i < 23) {
                i = 23;
            }
            serialTransService.this.s = i - 3;
        }

        public void updateConnectionHigh() {
            serialTransService serialtransservice = serialTransService.this;
            int i = serialtransservice.mConnectionState;
            if (i == 2 && i == 3) {
                BluetoothGatt unused = serialtransservice.k;
            } else {
                Log.w("sifli-serial", "connection state error");
            }
        }
    }

    public static /* synthetic */ int x(serialTransService serialtransservice, int i) {
        int i2 = serialtransservice.w + i;
        serialtransservice.w = i2;
        return i2;
    }

    public final byte[] J(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(2).order(this.y).putShort((short) i).array(), 0, bArr, i2, 2);
        return bArr;
    }

    public final BluetoothGatt K(BluetoothDevice bluetoothDevice, BluetoothGattCallback bluetoothGattCallback) {
        BluetoothGatt connectGatt;
        if (this.i.isEnabled()) {
            this.mConnectionState = 1;
            Log.i("sifli-serial", "connecting to " + bluetoothDevice);
            int i = Build.VERSION.SDK_INT;
            if (i >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                Log.e("sifli-serial", "no Permission");
                this.o = 112;
                synchronized (this.n) {
                    this.n.notifyAll();
                }
                return null;
            }
            if (i >= 26) {
                Log.d("sifli-serial", "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, preferredPhy = LE_1M | LE_2M)");
                connectGatt = this.j.connectGatt(this, false, bluetoothGattCallback, 2, 3);
            } else if (i >= 23) {
                Log.d("sifli-serial", "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
                connectGatt = this.j.connectGatt(this, false, bluetoothGattCallback, 2);
            } else {
                Log.d("sifli-serial", "gatt = device.connectGatt(autoConnect = false)");
                connectGatt = this.j.connectGatt(this, false, bluetoothGattCallback);
            }
            try {
                synchronized (this.n) {
                    while (true) {
                        int i2 = this.mConnectionState;
                        if ((i2 == 1 || i2 == 2) && this.o == 0) {
                            this.n.wait();
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Log.e("sifli-serial", "Sleeping interrupted");
            }
            return connectGatt;
        }
        return null;
    }

    public final boolean L(String str, BluetoothGattCallback bluetoothGattCallback) {
        this.j = this.i.getRemoteDevice(str);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.k = K(this.j, bluetoothGattCallback);
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        int i = this.o;
        boolean z = true;
        if (i > 0) {
            int i2 = this.p;
            if (i == 105) {
                Log.i("sifli-serial", "Connection error after: " + (elapsedRealtime2 - elapsedRealtime) + " ms");
                if (i2 != 133 || elapsedRealtime2 <= elapsedRealtime + 25000) {
                    z = false;
                }
                if (z) {
                    Log.e("sifli-serial", "Device not reachable. Check if the device with address " + str + " is in range, is advertising and is connectable");
                } else {
                    Log.e("sifli-serial", "An error occurred while connecting to the device:" + i2);
                }
            } else if (i == 107) {
                Log.e("sifli-serial", "error discovery not start");
            } else if (i == 102) {
                Log.e("sifli-serial", "discovery time out");
            } else {
                Log.e("sifli-serial", "An error occurred during discovering services:" + i2);
            }
            terminateConnection(this.k);
            return false;
        } else if (this.mConnectionState == 0) {
            this.o = 105;
            Log.e("sifli-serial", "Disconnect due to state disconnect");
            terminateConnection(this.k);
            return false;
        } else {
            return true;
        }
    }

    public final int M(byte[] bArr, int i) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, i, bArr2, 0, 2);
        return ByteBuffer.wrap(bArr2, 0, 2).order(this.y).getShort();
    }

    public void N(int i) {
        Intent intent = new Intent(BROADCAST_SERIAL_TRANS);
        intent.putExtra(EXTRA_SERIAL_TRANS_MSG, i);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void O(int i, int i2) {
        Intent intent = new Intent(BROADCAST_SERIAL_TRANS);
        intent.putExtra(EXTRA_SERIAL_TRANS_MSG, i);
        intent.putExtra(EXTRA_SERIAL_TRANS_STATUS, i2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void P(byte[] bArr, int i) {
        Message message = new Message();
        message.what = i;
        Bundle bundle = new Bundle();
        bundle.putByteArray("Sifli.serial.BLE_DATA", bArr);
        message.setData(bundle);
        this.z.sendMessage(message);
    }

    public final void Q(byte[] bArr, int i, int i2, int i3) {
        int i4 = this.t;
        int i5 = i4 - 3;
        if (i4 > 247) {
            i5 = 244;
        }
        int i6 = this.s;
        if (i6 != 0 && i5 > i6) {
            i5 = i6;
        }
        int i7 = i + 4;
        if (i7 > 65535) {
            Log.e("sifli-serial", "serial length over");
        } else if (i7 <= i5) {
            byte[] bArr2 = new byte[i7];
            bArr2[0] = (byte) i2;
            bArr2[1] = 0;
            byte[] J = J(i, bArr2, 2);
            System.arraycopy(bArr, 0, J, 4, i);
            P(J, i3);
        } else {
            byte[] bArr3 = new byte[i5];
            byte b2 = (byte) i2;
            bArr3[0] = b2;
            bArr3[1] = 1;
            byte[] J2 = J(i, bArr3, 2);
            int i8 = i5 - 4;
            System.arraycopy(bArr, 0, J2, 4, i8);
            int i9 = i8 + 0;
            P(J2, i3);
            while (i9 < i) {
                int i10 = i - i9;
                int i11 = i8 + 2;
                if (i10 > i11) {
                    byte[] bArr4 = new byte[i5];
                    bArr4[0] = b2;
                    bArr4[1] = 2;
                    System.arraycopy(bArr, i9, bArr4, 2, i11);
                    i9 += i11;
                    P(bArr4, i3);
                } else {
                    byte[] bArr5 = new byte[(i10 + 4) - 2];
                    bArr5[0] = b2;
                    bArr5[1] = 3;
                    System.arraycopy(bArr, i9, bArr5, 2, i10);
                    P(bArr5, i3);
                    i9 = i;
                }
            }
        }
    }

    public final void R(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Log.e("sifli-serial", "no Permission");
            this.o = 112;
            synchronized (this.n) {
                this.n.notifyAll();
            }
            return;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (descriptor == null) {
            Log.e("sifli-serial", "desc null!!!");
            return;
        }
        this.r = false;
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        Log.d("sifli-serial", "Write descriptor");
        this.u.postDelayed(this.G, 30000L);
        bluetoothGatt.writeDescriptor(descriptor);
    }

    public void close(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (i >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                Log.e("sifli-serial", "no Permission");
                this.o = 112;
                synchronized (this.n) {
                    this.n.notifyAll();
                }
                return;
            }
            bluetoothGatt.requestConnectionPriority(2);
        }
        waitFor(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        Log.d("sifli-serial", "Cleaning up...");
        Log.d("sifli-serial", "gatt.disconnect()");
        bluetoothGatt.disconnect();
        Log.d("sifli-serial", "gatt.close()");
        bluetoothGatt.close();
        this.mConnectionState = 5;
    }

    public void disconnect(BluetoothGatt bluetoothGatt) {
        if (this.mConnectionState == 0) {
            return;
        }
        Log.d("sifli-serial", "Disconnecting");
        this.mConnectionState = 4;
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Log.e("sifli-serial", "no Permission");
            this.o = 112;
            synchronized (this.n) {
                this.n.notifyAll();
            }
            return;
        }
        bluetoothGatt.disconnect();
        waitUntilDisconnected();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        serialTransBinder serialtransbinder = this.h;
        if (serialtransbinder != null) {
            return serialtransbinder;
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("sifli-serial", "on create version 1.2.6d");
        this.h = new serialTransBinder();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        int i = this.mConnectionState;
        if (i != 5 && i != 0) {
            close(this.k);
        }
        Log.d("sifli-serial", "on destroy");
        this.u.removeCallbacks(this.E);
        this.u.removeCallbacks(this.F);
        this.u.removeCallbacks(this.G);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d("sifli-serial", "onStartCommand");
        this.B = intent.getStringExtra(EXTRA_DEVICE_ADDRESS);
        this.t = 23;
        this.C = false;
        HandlerThread handlerThread = new HandlerThread("BleWrite");
        handlerThread.start();
        this.z = new Handler(handlerThread.getLooper(), new e());
        return super.onStartCommand(intent, i, i2);
    }

    public void refreshDeviceCache(BluetoothGatt bluetoothGatt) {
        try {
            boolean booleanValue = ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
            Log.i("sifli-serial", "Refreshing result: " + booleanValue);
        } catch (Exception e2) {
            Log.e("sifli-serial", "An exception occurred while refreshing device", e2);
        }
    }

    public void setOnSerialTransListener(OnSerialTransListener onSerialTransListener) {
        this.A = onSerialTransListener;
    }

    public void terminateConnection(@NonNull BluetoothGatt bluetoothGatt) {
        if (this.mConnectionState != 0) {
            disconnect(bluetoothGatt);
        }
        close(bluetoothGatt);
        waitFor(600L);
    }

    public void waitFor(long j) {
        synchronized (this.n) {
            try {
                Log.d("sifli-serial", "wait(" + j + ")");
                this.n.wait(j);
            } catch (InterruptedException unused) {
                Log.e("sifli-serial", "Sleeping interrupted");
            }
        }
    }

    public void waitUntilDisconnected() {
        try {
            synchronized (this.n) {
                while (this.mConnectionState != 0) {
                    this.n.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-serial", "Sleeping interrupted");
        }
    }
}
