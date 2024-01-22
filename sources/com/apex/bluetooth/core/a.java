package com.apex.bluetooth.core;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.os.HandlerCompat;
import com.apex.bluetooth.callback.DataReportCallback;
import com.apex.bluetooth.callback.DataResponseCallback;
import com.apex.bluetooth.callback.EABleCallback;
import com.apex.bluetooth.callback.MotionDataReportCallback;
import com.apex.bluetooth.callback.MotionDataResponseCallback;
import com.apex.bluetooth.enumeration.EABleConnectState;
import com.apex.bluetooth.listener.EABleConnectListener;
import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class a {
    public BluetoothAdapter b;
    public BluetoothGatt c;
    public BluetoothDevice d;
    public BluetoothManager e;
    public EABleConnectListener f;
    public com.apex.bluetooth.listener.a g;
    public b h;
    public Context i;
    public boolean j;
    public int k;
    public boolean l;
    public String m;
    public MotionDataReportCallback n;
    public com.apex.bluetooth.broadcast.a o;
    public DataReportCallback p;

    /* renamed from: a  reason: collision with root package name */
    public final String f2168a = a.class.getSimpleName();
    public com.apex.bluetooth.core.n.a q = new C0203a();

    /* renamed from: com.apex.bluetooth.core.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0203a implements com.apex.bluetooth.core.n.a {
        public C0203a() {
        }

        public void a(int i) {
            EABleConnectListener eABleConnectListener;
            if (i == 1) {
                EABleConnectListener eABleConnectListener2 = a.this.f;
                if (eABleConnectListener2 != null) {
                    eABleConnectListener2.deviceConnected();
                }
            } else if (i == 2) {
                EABleConnectListener eABleConnectListener3 = a.this.f;
                if (eABleConnectListener3 != null) {
                    eABleConnectListener3.deviceDisconnect();
                }
            } else if (i == 3) {
                EABleConnectListener eABleConnectListener4 = a.this.f;
                if (eABleConnectListener4 != null) {
                    eABleConnectListener4.unopenedBluetooth();
                }
            } else if (i != 4 || (eABleConnectListener = a.this.f) == null) {
            } else {
                eABleConnectListener.connectTimeOut();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends BluetoothGattCallback {

        /* renamed from: a  reason: collision with root package name */
        public com.apex.bluetooth.core.m.a f2170a;
        public com.apex.bluetooth.core.m.c b;

        public b(a aVar) {
            if (this.f2170a == null) {
                com.apex.bluetooth.core.m.a aVar2 = new com.apex.bluetooth.core.m.a(a.this.p, a.this.i);
                this.f2170a = aVar2;
                aVar2.a(a.this.o);
            }
            if (this.b == null) {
                this.b = new com.apex.bluetooth.core.m.c(a.this.n, aVar);
            }
            a.this.g = new c(a.this.i, a.this.q, a.this.k, this.f2170a, this.b);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            com.apex.bluetooth.listener.a aVar = a.this.g;
            if (aVar != null) {
                c cVar = (c) aVar;
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (value != null) {
                    try {
                        String uuid = bluetoothGattCharacteristic.getUuid().toString();
                        int i = 0;
                        String str = "";
                        if (uuid.equalsIgnoreCase("00008801-0000-1000-8000-00805f9b34fb")) {
                            ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue = cVar.r;
                            if (concurrentLinkedQueue != null) {
                                concurrentLinkedQueue.add(value);
                            }
                            while (i < value.length) {
                                str = str + Integer.toHexString(value[i] & 255) + HexStringBuilder.DEFAULT_SEPARATOR;
                                i++;
                            }
                            LogUtils.i(cVar.f2172a, "Channel 1 receiving data:" + str);
                        } else if (uuid.equalsIgnoreCase("00008802-0000-1000-8000-00805f9b34fb")) {
                            ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue2 = cVar.s;
                            if (concurrentLinkedQueue2 != null) {
                                concurrentLinkedQueue2.add(value);
                            }
                            while (i < value.length) {
                                str = str + Integer.toHexString(value[i] & 255) + HexStringBuilder.DEFAULT_SEPARATOR;
                                i++;
                            }
                            LogUtils.i(cVar.f2172a, "Channel 2 receiving data:" + str);
                            LogData2File.getInstance().saveLogData("Channel 2 receiving data:" + str);
                        } else if (uuid.equalsIgnoreCase("00008803-0000-1000-8000-00805f9b34fb")) {
                            ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue3 = cVar.q;
                            if (concurrentLinkedQueue3 != null) {
                                concurrentLinkedQueue3.add(value);
                            }
                            while (i < value.length) {
                                str = str + Integer.toHexString(value[i] & 255) + HexStringBuilder.DEFAULT_SEPARATOR;
                                i++;
                            }
                            LogUtils.i(cVar.f2172a, "Channel 3 receiving data:" + str);
                            LogData2File.getInstance().saveLogData("Channel 3 receiving data:" + str);
                        } else if (uuid.equalsIgnoreCase("00009901-0000-1000-8000-00805f9b34fb")) {
                            ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue4 = cVar.t;
                            if (concurrentLinkedQueue4 != null) {
                                concurrentLinkedQueue4.add(value);
                            }
                            while (i < value.length) {
                                str = str + Integer.toHexString(value[i] & 255) + HexStringBuilder.DEFAULT_SEPARATOR;
                                i++;
                            }
                            LogUtils.i(cVar.f2172a, "Channel 4 receiving data:" + str);
                            LogData2File.getInstance().saveLogData("Channel 4 receiving data:" + str);
                        }
                    } catch (Exception e) {
                        LogUtils.i(cVar.f2172a, "receiving data exception:" + e.getMessage());
                        LogData2File.getInstance().saveLogData(e.getMessage());
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            com.apex.bluetooth.data_package.b.a<byte[]> aVar;
            com.apex.bluetooth.data_package.b.a<byte[]> aVar2;
            com.apex.bluetooth.data_package.b.a<byte[]> aVar3;
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            com.apex.bluetooth.listener.a aVar4 = a.this.g;
            if (aVar4 != null) {
                c cVar = (c) aVar4;
                String uuid = bluetoothGattCharacteristic.getUuid().toString();
                if (i == 0) {
                    if (uuid.equalsIgnoreCase("00008801-0000-1000-8000-00805f9b34fb")) {
                        if (cVar.j == null || !cVar.c || (aVar3 = ((com.apex.bluetooth.data_package.a) cVar.j).c) == null || aVar3.isEmpty()) {
                            return;
                        }
                        try {
                            cVar.a(aVar3.poll(), "00008800-0000-1000-8000-00805f9b34fb", "00008801-0000-1000-8000-00805f9b34fb");
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    } else if (uuid.equalsIgnoreCase("00008802-0000-1000-8000-00805f9b34fb")) {
                        if (cVar.g == null || !cVar.e) {
                            return;
                        }
                        com.apex.bluetooth.data_package.b.a<byte[]> aVar5 = ((com.apex.bluetooth.data_package.a) cVar.g).c;
                        if (aVar5 != null && !aVar5.isEmpty()) {
                            try {
                                cVar.a(aVar5.poll(), "00008800-0000-1000-8000-00805f9b34fb", "00008802-0000-1000-8000-00805f9b34fb");
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                LogData2File logData2File = LogData2File.getInstance();
                                logData2File.saveLogData("主动上报通道发送数据出现异常:" + e2.getMessage());
                                return;
                            }
                        }
                        DataResponseCallback dataResponseCallback = cVar.K;
                        if (dataResponseCallback != null) {
                            dataResponseCallback.mutualSuccess();
                        }
                        com.apex.bluetooth.data_package.b.b bVar = cVar.g;
                        if (bVar != null && !bVar.f2207a) {
                            cVar.g.f2207a = true;
                        }
                        cVar.e = false;
                        return;
                    } else if (uuid.equalsIgnoreCase("00008803-0000-1000-8000-00805f9b34fb")) {
                        if (cVar.h == null || !cVar.f) {
                            return;
                        }
                        com.apex.bluetooth.data_package.b.a<byte[]> aVar6 = ((com.apex.bluetooth.data_package.a) cVar.h).c;
                        if (aVar6 != null && !aVar6.isEmpty()) {
                            try {
                                cVar.a(aVar6.poll(), "00008800-0000-1000-8000-00805f9b34fb", "00008803-0000-1000-8000-00805f9b34fb");
                                return;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                LogData2File logData2File2 = LogData2File.getInstance();
                                logData2File2.saveLogData("运动数据通道发送数据出现异常:" + e3.getMessage());
                                return;
                            }
                        }
                        MotionDataResponseCallback motionDataResponseCallback = cVar.L;
                        if (motionDataResponseCallback != null) {
                            motionDataResponseCallback.mutualSuccess();
                        }
                        com.apex.bluetooth.data_package.b.b bVar2 = cVar.h;
                        if (bVar2 != null && !bVar2.f2207a) {
                            cVar.h.f2207a = true;
                        }
                        cVar.f = false;
                        return;
                    } else if (uuid.equalsIgnoreCase("00009901-0000-1000-8000-00805f9b34fb")) {
                        if (cVar.i == null || !cVar.d || (aVar2 = ((com.apex.bluetooth.data_package.a) cVar.i).c) == null || aVar2.isEmpty()) {
                            return;
                        }
                        try {
                            cVar.a(aVar2.poll(), "00009900-0000-1000-8000-00805f9b34fb", "00009901-0000-1000-8000-00805f9b34fb");
                            return;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            return;
                        }
                    } else if (!uuid.equalsIgnoreCase("00009902-0000-1000-8000-00805f9b34fb") || cVar.i == null || !cVar.d || (aVar = ((com.apex.bluetooth.data_package.a) cVar.i).c) == null || aVar.isEmpty()) {
                        return;
                    } else {
                        try {
                            cVar.a(aVar.poll(), "00009900-0000-1000-8000-00805f9b34fb", "00009902-0000-1000-8000-00805f9b34fb");
                            return;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            return;
                        }
                    }
                }
                LogUtils.i(cVar.f2172a, "Data sending failed....");
                if (uuid.equalsIgnoreCase("00008801-0000-1000-8000-00805f9b34fb")) {
                    EABleCallback eABleCallback = cVar.M;
                    if (eABleCallback != null) {
                        eABleCallback.mutualFail(5);
                    }
                    com.apex.bluetooth.data_package.b.b bVar3 = cVar.j;
                    if (bVar3 != null) {
                        bVar3.f2207a = true;
                    }
                    cVar.c = false;
                } else if (uuid.equalsIgnoreCase("00008802-0000-1000-8000-00805f9b34fb")) {
                    LogData2File.getInstance().saveLogData("主动上报通道数据发送失败");
                    DataResponseCallback dataResponseCallback2 = cVar.K;
                    if (dataResponseCallback2 != null) {
                        dataResponseCallback2.mutualFail(5);
                    }
                    com.apex.bluetooth.data_package.b.b bVar4 = cVar.g;
                    if (bVar4 != null) {
                        bVar4.f2207a = true;
                    }
                    cVar.e = false;
                } else if (uuid.equalsIgnoreCase("00008803-0000-1000-8000-00805f9b34fb")) {
                    LogData2File.getInstance().saveLogData("运动数据通道数据发送失败");
                    MotionDataResponseCallback motionDataResponseCallback2 = cVar.L;
                    if (motionDataResponseCallback2 != null) {
                        motionDataResponseCallback2.mutualFail(5);
                    }
                    com.apex.bluetooth.data_package.b.b bVar5 = cVar.h;
                    if (bVar5 != null) {
                        bVar5.f2207a = true;
                    }
                    cVar.f = false;
                } else if (uuid.equalsIgnoreCase("00009901-0000-1000-8000-00805f9b34fb")) {
                    com.apex.bluetooth.listener.b bVar6 = cVar.X;
                    if (bVar6 != null) {
                        bVar6.mutualFail(5);
                    }
                    com.apex.bluetooth.data_package.b.b bVar7 = cVar.i;
                    if (bVar7 != null) {
                        bVar7.f2207a = true;
                    }
                    cVar.d = false;
                } else if (uuid.equalsIgnoreCase("00009902-0000-1000-8000-00805f9b34fb")) {
                    com.apex.bluetooth.listener.b bVar8 = cVar.X;
                    if (bVar8 != null) {
                        bVar8.mutualFail(5);
                    }
                    com.apex.bluetooth.data_package.b.b bVar9 = cVar.i;
                    if (bVar9 != null) {
                        bVar9.f2207a = true;
                    }
                    cVar.d = false;
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            com.apex.bluetooth.listener.a aVar = a.this.g;
            if (aVar != null) {
                c cVar = (c) aVar;
                if (i2 == 2) {
                    cVar.b = bluetoothGatt;
                    k kVar = cVar.p;
                    if (kVar != null) {
                        kVar.a(EABleConnectState.STATE_CONNECTING);
                    }
                    int i3 = cVar.Q;
                    if (i3 <= 23) {
                        i3 = 23;
                    }
                    if (cVar.b == null || cVar.P) {
                        return;
                    }
                    cVar.b.requestMtu(i3);
                } else if (i == 133 && i2 == 0) {
                    LogUtils.i(cVar.f2172a, "Disconnect and reconnect!!!");
                    LogData2File.getInstance().saveLogData("133 Disconnect and reconnect!!!");
                    com.apex.bluetooth.core.m.a aVar2 = cVar.J;
                    if (aVar2 != null) {
                        aVar2.c = true;
                    }
                    com.apex.bluetooth.core.m.c cVar2 = cVar.I;
                    if (cVar2 != null) {
                        cVar2.f2194a = true;
                    }
                    k kVar2 = cVar.p;
                    if (kVar2 != null) {
                        kVar2.a(EABleConnectState.STATE_DISCONNECT);
                    }
                    if (cVar.P) {
                        return;
                    }
                    cVar.P = true;
                    bluetoothGatt.disconnect();
                    cVar.b();
                    bluetoothGatt.close();
                    Handler handler = cVar.U;
                    if (handler != null) {
                        handler.removeCallbacksAndMessages("disconnect_eable");
                        HandlerCompat.postDelayed(cVar.U, new e(cVar), "disconnect_eable", 1000L);
                    }
                } else if (i2 == 0) {
                    LogUtils.i(cVar.f2172a, "Disconnect and reconnect...");
                    LogData2File.getInstance().saveLogData("STATE_DISCONNECTED Disconnect and reconnect...");
                    com.apex.bluetooth.core.m.a aVar3 = cVar.J;
                    if (aVar3 != null) {
                        aVar3.c = true;
                    }
                    com.apex.bluetooth.core.m.c cVar3 = cVar.I;
                    if (cVar3 != null) {
                        cVar3.f2194a = true;
                    }
                    k kVar3 = cVar.p;
                    if (kVar3 != null) {
                        kVar3.a(EABleConnectState.STATE_DISCONNECT);
                    }
                    if (cVar.P) {
                        return;
                    }
                    cVar.P = true;
                    bluetoothGatt.disconnect();
                    cVar.b();
                    bluetoothGatt.close();
                    Handler handler2 = cVar.U;
                    if (handler2 != null) {
                        handler2.removeCallbacksAndMessages("disconnect_eable");
                        HandlerCompat.postDelayed(cVar.U, new f(cVar), "disconnect_eable", 1000L);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            com.apex.bluetooth.listener.a aVar = a.this.g;
            if (aVar != null) {
                c cVar = (c) aVar;
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            com.apex.bluetooth.listener.a aVar = a.this.g;
            if (aVar != null) {
                c cVar = (c) aVar;
                if (TextUtils.isEmpty(cVar.R) || TextUtils.isEmpty(cVar.S)) {
                    return;
                }
                if (cVar.S.equalsIgnoreCase("00009902-0000-1000-8000-00805f9b34fb") && cVar.R.equalsIgnoreCase("00009900-0000-1000-8000-00805f9b34fb")) {
                    cVar.b0 = true;
                    k kVar = cVar.p;
                    if (kVar != null) {
                        kVar.a(EABleConnectState.STATE_CONNECTED);
                    }
                    Handler handler = cVar.U;
                    if (handler != null) {
                        handler.removeCallbacksAndMessages("disconnect_eable");
                    }
                    if (cVar.H == null || cVar.P) {
                        return;
                    }
                    ((C0203a) cVar.H).a(1);
                    return;
                }
                try {
                    cVar.a(true, cVar.R, cVar.S);
                } catch (Exception e) {
                    e.printStackTrace();
                    Handler handler2 = cVar.U;
                    if (handler2 != null) {
                        handler2.removeCallbacksAndMessages("disconnect_eable");
                    }
                    LogUtils.i(cVar.f2172a, "Channel open fails");
                    LogData2File.getInstance().saveLogData("Channel open fails");
                    if (cVar.H == null || cVar.P) {
                        return;
                    }
                    ((C0203a) cVar.H).a(2);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            a aVar = a.this;
            if (aVar.j) {
                return;
            }
            aVar.j = true;
            com.apex.bluetooth.listener.a aVar2 = a.this.g;
            if (aVar2 != null) {
                c cVar = (c) aVar2;
                if (i2 == 0) {
                    i.f2187a = i - 3;
                    k kVar = cVar.p;
                    if (kVar != null) {
                        kVar.a(EABleConnectState.STATE_CONNECTING);
                    }
                    cVar.b = bluetoothGatt;
                    if (bluetoothGatt != null && !cVar.P) {
                        cVar.b.discoverServices();
                    }
                    LogUtils.i(cVar.f2172a, "MTU modified successfully");
                    return;
                }
                LogUtils.i(cVar.f2172a, "MTU modification failed");
                i.f2187a = 20;
                if (cVar.b == null || cVar.P) {
                    return;
                }
                cVar.b.discoverServices();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            com.apex.bluetooth.listener.a aVar = a.this.g;
            if (aVar != null) {
                c cVar = (c) aVar;
                if (cVar.P) {
                    return;
                }
                k kVar = cVar.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_CONNECTING);
                }
                cVar.b = bluetoothGatt;
                try {
                    cVar.S = "00008801-0000-1000-8000-00805f9b34fb";
                    cVar.R = "00008800-0000-1000-8000-00805f9b34fb";
                    cVar.a(true, "00008800-0000-1000-8000-00805f9b34fb", "00008801-0000-1000-8000-00805f9b34fb");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public a(Context context, EABleConnectListener eABleConnectListener, int i, DataReportCallback dataReportCallback, MotionDataReportCallback motionDataReportCallback) {
        this.i = context;
        this.f = eABleConnectListener;
        this.k = i;
        this.n = motionDataReportCallback;
        this.p = dataReportCallback;
        b();
        this.h = new b(this);
    }

    public final void b() {
        if (this.o == null) {
            this.o = new com.apex.bluetooth.broadcast.a();
            IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.FOUND");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            intentFilter.addAction("east.apex.bluetooth.headset.proxy");
            intentFilter.addAction("east.apex.bluetooth.a2dp.proxy");
            this.i.registerReceiver(this.o, intentFilter);
        }
    }

    public void c(com.apex.bluetooth.data_package.a aVar, EABleCallback eABleCallback) {
        com.apex.bluetooth.data_package.b.a<byte[]> aVar2;
        com.apex.bluetooth.listener.a aVar3 = this.g;
        if (aVar3 != null) {
            c cVar = (c) aVar3;
            if (aVar == null || (aVar2 = aVar.c) == null || aVar2.isEmpty()) {
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(16);
                }
            } else if (cVar.P) {
                k kVar = cVar.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_DISCONNECT);
                }
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(6);
                }
            } else {
                l lVar = new l();
                lVar.f2191a = eABleCallback;
                lVar.b = aVar;
                ConcurrentLinkedQueue<l> concurrentLinkedQueue = cVar.v;
                if (concurrentLinkedQueue != null) {
                    try {
                        concurrentLinkedQueue.add(lVar);
                    } catch (IllegalStateException unused) {
                        if (eABleCallback != null) {
                            eABleCallback.mutualFail(8);
                        }
                    } catch (NullPointerException unused2) {
                        if (eABleCallback != null) {
                            eABleCallback.mutualFail(8);
                        }
                    }
                }
            }
        }
    }

    public synchronized void a() {
        BluetoothGatt bluetoothGatt;
        LogUtils.i(this.f2168a, "Turn off Bluetooth");
        LogData2File.getInstance().saveLogData("Turn off Bluetooth");
        this.l = true;
        com.apex.bluetooth.broadcast.a aVar = this.o;
        if (aVar != null) {
            Context context = this.i;
            if (context != null) {
                context.unregisterReceiver(aVar);
            }
            this.o = null;
        }
        com.apex.bluetooth.listener.a aVar2 = this.g;
        if (aVar2 != null) {
            c cVar = (c) aVar2;
            com.apex.bluetooth.core.m.a aVar3 = cVar.J;
            if (aVar3 != null) {
                aVar3.c = true;
            }
            com.apex.bluetooth.core.m.c cVar2 = cVar.I;
            if (cVar2 != null) {
                cVar2.f2194a = true;
            }
            if (!cVar.P) {
                cVar.P = true;
                k kVar = cVar.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_DISCONNECT);
                }
                Handler handler = cVar.U;
                if (handler != null) {
                    handler.removeCallbacksAndMessages("disconnect_eable");
                    HandlerCompat.postDelayed(cVar.U, new g(cVar), "disconnect_eable", 1000L);
                }
            } else {
                Handler handler2 = cVar.U;
                if (handler2 != null) {
                    handler2.postDelayed(new h(cVar), 1000L);
                }
            }
            this.g = null;
        }
        BluetoothGatt bluetoothGatt2 = this.c;
        if (bluetoothGatt2 != null) {
            bluetoothGatt2.disconnect();
            synchronized (this) {
                try {
                    Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
                    if (method != null && (bluetoothGatt = this.c) != null) {
                        ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
                this.c.close();
                this.c = null;
            }
        }
        this.d = null;
        this.b = null;
        this.e = null;
        this.h = null;
        this.q = null;
        this.f = null;
    }

    public void b(com.apex.bluetooth.data_package.a aVar, EABleCallback eABleCallback) {
        com.apex.bluetooth.data_package.b.a<byte[]> aVar2;
        com.apex.bluetooth.listener.a aVar3 = this.g;
        if (aVar3 != null) {
            c cVar = (c) aVar3;
            if (aVar == null || (aVar2 = aVar.c) == null || aVar2.isEmpty()) {
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(16);
                }
            } else if (cVar.P) {
                k kVar = cVar.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_DISCONNECT);
                }
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(6);
                }
            } else {
                l lVar = new l();
                lVar.b = aVar;
                lVar.f2191a = eABleCallback;
                ConcurrentLinkedQueue<l> concurrentLinkedQueue = cVar.w;
                if (concurrentLinkedQueue != null) {
                    try {
                        concurrentLinkedQueue.add(lVar);
                    } catch (IllegalStateException unused) {
                        if (eABleCallback != null) {
                            eABleCallback.mutualFail(8);
                        }
                    } catch (NullPointerException unused2) {
                        if (eABleCallback != null) {
                            eABleCallback.mutualFail(8);
                        }
                    }
                }
            }
        }
    }

    public void a(com.apex.bluetooth.data_package.a aVar, EABleCallback eABleCallback) {
        com.apex.bluetooth.data_package.b.a<byte[]> aVar2;
        com.apex.bluetooth.listener.a aVar3 = this.g;
        if (aVar3 != null) {
            c cVar = (c) aVar3;
            if (aVar == null || (aVar2 = aVar.c) == null || aVar2.isEmpty()) {
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(16);
                }
            } else if (cVar.P) {
                k kVar = cVar.p;
                if (kVar != null) {
                    kVar.a(EABleConnectState.STATE_DISCONNECT);
                }
                if (eABleCallback != null) {
                    eABleCallback.mutualFail(6);
                }
            } else {
                l lVar = new l();
                lVar.f2191a = eABleCallback;
                lVar.b = aVar;
                ConcurrentLinkedQueue<l> concurrentLinkedQueue = cVar.u;
                if (concurrentLinkedQueue != null) {
                    try {
                        concurrentLinkedQueue.add(lVar);
                    } catch (IllegalStateException unused) {
                        if (eABleCallback != null) {
                            eABleCallback.mutualFail(8);
                        }
                    } catch (NullPointerException unused2) {
                        if (eABleCallback != null) {
                            eABleCallback.mutualFail(8);
                        }
                    }
                }
            }
        }
    }
}
