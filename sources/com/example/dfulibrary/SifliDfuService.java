package com.example.dfulibrary;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.math.BigDecimal;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes9.dex */
public class SifliDfuService extends IntentService {
    public static final String A0 = "Sifli.dfu.broadcast.EXTRA_DFU_MODE";
    public static final int A1 = 19;
    public static final int A2 = 1;
    public static final String B0 = "Sifli.dfu.broadcast.EXTRA_MTU";
    public static final int B1 = 20;
    public static final int B2 = 2;
    public static final String C0 = "Sifli.dfu.broadcast.EXTRA_DFU_SPEED_MODE";
    public static final int C1 = 21;
    public static final int C2 = 3;
    public static final String D0 = "Sifli.dfu.broadcast.EXTRA_DFU_SPEED_WINDOW";
    public static final int D1 = 30;
    public static final int E0 = 0;
    public static final int E1 = 31;
    public static final int F0 = 1;
    public static final int F1 = 32;
    public static final int G0 = 2;
    public static final int G1 = 33;
    public static final int H0 = 0;
    public static final int H1 = 34;
    public static final int I0 = 1;
    public static final int I1 = 35;
    public static final int J0 = 5;
    public static final int J1 = 36;
    public static final int K0 = 10;
    public static final int K1 = 37;
    public static final int L0 = 15;
    public static final int L1 = 38;
    public static final int M0 = 20;
    public static final int M1 = 39;
    public static final int N0 = 0;
    public static final int N1 = 40;
    public static final int O0 = 1;
    public static final int O1 = 41;
    public static final int P0 = 2;
    public static final int P1 = 42;
    public static final int Q0 = 3;
    public static final int Q1 = 43;
    public static final int R0 = 4;
    public static final int R1 = 44;
    public static final int S0 = 5;
    public static final int S1 = 45;
    public static long T0 = 1000;
    public static final int T1 = 46;
    public static final int U1 = 0;
    public static final int V1 = 1;
    public static final int W1 = 2;
    public static final String X = "Sifli.dfu.broadcast.BROADCAST_ACTION";
    public static final int X1 = 3;
    public static final String Y = "Sifli.dfu.broadcast.BROADCAST_LOG";
    public static final int Y1 = 4;
    public static final String Z = "Sifli.dfu.broadcast.BROADCAST_DFU_STATE";
    public static final int Z1 = 5;
    public static final String a0 = "Sifli.dfu.broadcast.BROADCAST_ERROR";
    public static final int a2 = 6;
    public static final String b0 = "Sifli.dfu.broadcast.BROADCAST_PROGRESS";
    public static final int b1 = 1;
    public static final int b2 = 7;
    public static final String c0 = "Sifli.dfu.extra.EXTRA_LOG_INFO";
    public static final int c2 = 8;
    public static final String d0 = "Sifli.dfu.extra.EXTRA_LOG_LEVEL";
    public static final int d2 = 9;
    public static final String e0 = "Sifli.dfu.broadcast.EXTRA_DFU_STATE";
    public static final int e2 = 10;
    public static final String f0 = "Sifli.dfu.broadcast.EXTRA_DFU_STATE_RESULT";
    public static final int f2 = 11;
    public static final String g0 = "Sifli.dfu.broadcast.EXTRA_DEVICE_ADDRESS";
    public static final int g2 = 64;
    public static final String h0 = "Sifli.dfu.broadcast.INIT_FILE_PATH";
    public static final int h2 = 64;
    public static final String i0 = "Sifli.dfu.broadcast.INIT_FILE_URI";
    public static final String j0 = "Sifli.dfu.broadcast.INIT_HCPU_PATH";
    public static final int j1 = 0;
    public static final String k0 = "Sifli.dfu.broadcast.INIT_HCPU_URI";
    public static final int k1 = 1;
    public static final String l0 = "Sifli.dfu.broadcast.INIT_LCPU_PATH";
    public static final int l1 = 2;
    public static final String m0 = "Sifli.dfu.broadcast.INIT_LCPU_URI";
    public static final int m1 = 3;
    public static final String n0 = "Sifli.dfu.broadcast.INIT_PATCH_PATH";
    public static final int n1 = 4;
    public static final String o0 = "Sifli.dfu.broadcast.INIT_PATCH_URI";
    public static final int o1 = 5;
    public static final String p0 = "Sifli.dfu.broadcast.INIT_RES_PATH";
    public static final int p1 = 6;
    public static final String q0 = "Sifli.dfu.broadcast.INIT_RES_URI";
    public static final int q1 = 7;
    public static final String r0 = "Sifli.dfu.broadcast.INIT_FONT_PATH";
    public static final int r1 = 8;
    public static final String s0 = "Sifli.dfu.broadcast.INIT_FONT_URI";
    public static final int s1 = 9;
    public static final String t0 = "Sifli.dfu.broadcast.EXTRA_ERROR";
    public static final int t1 = 10;
    public static final int t2 = 548;
    public static final String u0 = "Sifli.dfu.broadcast.EXTRA_ERROR_CODE";
    public static final int u1 = 11;
    public static final int u2 = 6;
    public static final String v0 = "Sifli.dfu.broadcast.EXTRA_ERROR_STATUS";
    public static final int v1 = 12;
    public static final int v2 = 23;
    public static final String w0 = "Sifli.dfu.broadcast.EXTRA_FREQUENCY";
    public static final int w1 = 13;
    public static final int w2 = 247;
    public static final String x0 = "Sifli.dfu.broadcast.EXTRA_PROGRESS_IMG_ID";
    public static final int x1 = 14;
    public static final int x2 = 0;
    public static final String y0 = "Sifli.dfu.broadcast.EXTRA_PROGRESS";
    public static final int y1 = 15;
    public static final int y2 = 1;
    public static final String z0 = "Sifli.dfu.broadcast.EXTRA_PROGRESS_MODE";
    public static final int z1 = 16;
    public byte[] A;
    public ArrayList<com.crrepa.k0.e> B;
    public BluetoothDevice C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public long J;
    public long K;
    public long L;
    public long M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public final Runnable R;
    public final Runnable S;
    public final Runnable T;
    public final Runnable U;
    public final BluetoothGattCallback V;
    public BroadcastReceiver W;

    /* renamed from: a  reason: collision with root package name */
    public int f7900a;
    public int h;
    public final ByteOrder i;
    public Handler j;
    public Handler k;
    public final Object l;
    public final Object m;
    public i n;
    public BluetoothAdapter o;
    public BluetoothGattCharacteristic p;
    public BluetoothGatt q;
    public int r;
    public int s;
    public com.crrepa.k0.d t;
    public boolean u;
    public com.crrepa.k0.e v;
    public int w;
    public boolean x;
    public boolean y;
    public boolean z;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("SifliDfuService", "init overtime!!!");
            SifliDfuService.this.r = 101;
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("SifliDfuService", "discovery timeout");
            SifliDfuService.this.j(5, "discovery timeout");
            SifliDfuService.this.r = 102;
            SifliDfuService.this.y(42);
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("SifliDfuService", "write descriptor timeout");
            SifliDfuService.this.r = 103;
            SifliDfuService.this.y(103);
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("SifliDfuService", "write time out");
            SifliDfuService.this.r = 104;
            synchronized (SifliDfuService.this.m) {
                SifliDfuService.this.m.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Comparator<com.crrepa.k0.e> {
        public e(SifliDfuService sifliDfuService) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(com.crrepa.k0.e eVar, com.crrepa.k0.e eVar2) {
            return Integer.compare(eVar.d(), eVar2.d());
        }
    }

    /* loaded from: classes9.dex */
    public class f extends BluetoothGattCallback {
        public f(SifliDfuService sifliDfuService) {
        }
    }

    /* loaded from: classes9.dex */
    public class g extends BluetoothGattCallback {
        public g() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Log.i("SifliDfuService", "onCharacteristicChanged");
            SifliDfuService.this.A(bluetoothGattCharacteristic.getValue());
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i != 0) {
                Log.e("SifliDfuService", "Write fail: " + i);
                SifliDfuService.this.M(39, i);
            }
            SifliDfuService.this.u = true;
            synchronized (SifliDfuService.this.m) {
                SifliDfuService.this.m.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                SifliDfuService sifliDfuService = SifliDfuService.this;
                sifliDfuService.j(5, "Bluetooth disconnect with: " + i);
                SifliDfuService.this.M(31, i);
                if (SifliDfuService.this.y) {
                    SifliDfuService.this.r = 41;
                    SifliDfuService.this.y(41);
                }
                if (i == 8 || i == 19 || i == 22) {
                    Log.w("SifliDfuService", "Target device disconnected with status: " + i);
                    if (SifliDfuService.this.t.g() == 2 || SifliDfuService.this.t.g() == 7) {
                        Log.i("SifliDfuService", "remote reboot in download mode");
                        SifliDfuService.this.j(5, "remote reboot, bluetooth disconnect");
                        bluetoothGatt.close();
                    }
                } else {
                    Log.e("SifliDfuService", "Connection state change error: " + i + " newState: " + i2);
                    SifliDfuService sifliDfuService2 = SifliDfuService.this;
                    sifliDfuService2.j(5, "Connection state change error: " + i + " newState: " + i2);
                }
                SifliDfuService.this.r = 105;
                SifliDfuService.this.s = i;
                if (i2 == 0) {
                    SifliDfuService.this.f7900a = 0;
                }
            } else if (i2 == 2) {
                SifliDfuService.this.j(5, "Bluetooth connected");
                SifliDfuService sifliDfuService3 = SifliDfuService.this;
                sifliDfuService3.f7900a = 2;
                sifliDfuService3.M(30, i);
                SifliDfuService.this.a(3000L);
                if (SifliDfuService.this.t.g() == 2 || SifliDfuService.this.t.g() == 7 || SifliDfuService.this.t.g() == 6 || SifliDfuService.this.t.g() == 8) {
                    Log.i("SifliDfuService", "init reboot or resume reboot or resume connected");
                    SifliDfuService.this.k(bluetoothGatt, 247);
                    SifliDfuService.this.a(1000L);
                    SifliDfuService.this.c(bluetoothGatt);
                    SifliDfuService.this.a(1000L);
                    int i3 = Build.VERSION.SDK_INT;
                    if (i3 >= 21) {
                        bluetoothGatt.requestConnectionPriority(1);
                    }
                    SifliDfuService.this.a(1000L);
                    if (i3 >= 26) {
                        bluetoothGatt.setPreferredPhy(2, 2, 0);
                    }
                    SifliDfuService.this.a(1000L);
                }
                Log.i("SifliDfuService", "Connected to GATT server.");
                boolean discoverServices = bluetoothGatt.discoverServices();
                SifliDfuService.this.k.postDelayed(SifliDfuService.this.S, 40000L);
                Log.i("SifliDfuService", "Attempting to start service discovery:" + discoverServices);
                SifliDfuService sifliDfuService4 = SifliDfuService.this;
                sifliDfuService4.j(5, "Attempting to start service discovery:" + discoverServices);
                if (discoverServices) {
                    return;
                }
                SifliDfuService.this.r = 107;
            } else if (i2 == 0) {
                Log.i("SifliDfuService", "Disconnected from GATT server");
                SifliDfuService sifliDfuService5 = SifliDfuService.this;
                sifliDfuService5.f7900a = 0;
                if (sifliDfuService5.t.g() == 2 || SifliDfuService.this.t.g() == 7) {
                    Log.i("SifliDfuService", "reconnect after reboot");
                }
                if (SifliDfuService.this.y) {
                    SifliDfuService.this.r = 41;
                    SifliDfuService.this.y(41);
                }
            }
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            Log.i("SifliDfuService", "onDescriptorWrite");
            SifliDfuService.this.z = true;
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            SifliDfuService sifliDfuService;
            int i3;
            if (i2 == 0) {
                if (i > 247) {
                    sifliDfuService = SifliDfuService.this;
                    i3 = 244;
                } else {
                    sifliDfuService = SifliDfuService.this;
                    i3 = i - 3;
                }
                sifliDfuService.h = i3;
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            SifliDfuService.this.k.removeCallbacks(SifliDfuService.this.S);
            int i2 = 0;
            if (i == 0) {
                Log.d("SifliDfuService", "onServicesDiscovered");
                int i3 = 0;
                while (i2 < bluetoothGatt.getServices().size()) {
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGatt.getServices().get(i2).getCharacteristics()) {
                        Log.e("SifliDfuService", "find uuid: " + bluetoothGattCharacteristic.getUuid().toString() + ", expect: 00000000-0000-0200-6473-5f696c666973");
                        if (bluetoothGattCharacteristic.getUuid().toString().equals("00000000-0000-0200-6473-5f696c666973")) {
                            Log.i("SifliDfuService", "find serial trans UUID");
                            SifliDfuService.this.p = bluetoothGattCharacteristic;
                            SifliDfuService.this.f7900a = 3;
                            i3 = 1;
                        }
                    }
                    i2++;
                }
                i2 = i3;
            } else {
                Log.w("SifliDfuService", "onServicesDiscovered received: " + i);
                SifliDfuService.this.r = 106;
                SifliDfuService.this.s = i;
                SifliDfuService.this.M(40, i);
            }
            if (i2 == 0) {
                Log.e("SifliDfuService", "fail to find target uuid");
                SifliDfuService.this.j(20, "fail to find target uuid");
                SifliDfuService.this.y(43);
                SifliDfuService.this.f7900a = 4;
            }
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class h extends BroadcastReceiver {
        public h() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) != 10) {
                return;
            }
            Log.e("SifliDfuService", "Bluetooth off");
            SifliDfuService.this.r = 46;
            synchronized (SifliDfuService.this.l) {
                SifliDfuService.this.l.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class i extends Binder {
        public i() {
        }

        public SifliDfuService a() {
            return SifliDfuService.this;
        }
    }

    /* loaded from: classes9.dex */
    public class j implements Handler.Callback {
        public j() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i;
            int i2;
            int i3;
            int i4;
            SifliDfuService sifliDfuService = SifliDfuService.this;
            int i5 = 0;
            if (sifliDfuService.f7900a == 0 || sifliDfuService.r != 0) {
                return false;
            }
            int i6 = message.what;
            if (i6 == 0) {
                byte[] byteArray = message.getData().getByteArray("Sifli.dfu.BLE_DATA");
                if (SifliDfuService.this.p == null) {
                    return false;
                }
                SifliDfuService.this.p.setValue(byteArray);
                SifliDfuService.this.u = false;
                if (SifliDfuService.this.q == null) {
                    return false;
                }
                SifliDfuService.this.q.writeCharacteristic(SifliDfuService.this.p);
                SifliDfuService.this.k.postDelayed(SifliDfuService.this.U, 5000L);
                synchronized (SifliDfuService.this.m) {
                    while (!SifliDfuService.this.u && SifliDfuService.this.r == 0) {
                        try {
                            SifliDfuService.this.m.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SifliDfuService.this.k.removeCallbacks(SifliDfuService.this.U);
                }
            } else if (i6 == 1) {
                byte[] byteArray2 = message.getData().getByteArray("Sifli.dfu.BLE_DATA");
                if (SifliDfuService.this.p == null) {
                    return false;
                }
                SifliDfuService.this.p.setValue(byteArray2);
                SifliDfuService.this.u = false;
                if (SifliDfuService.this.q == null) {
                    return false;
                }
                SifliDfuService.this.q.writeCharacteristic(SifliDfuService.this.p);
                if (SifliDfuService.this.M == 0) {
                    SifliDfuService.this.M = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - SifliDfuService.this.M < SifliDfuService.T0) {
                    SifliDfuService.w(SifliDfuService.this, byteArray2.length);
                } else {
                    SifliDfuService.w(SifliDfuService.this, byteArray2.length);
                    SifliDfuService.this.M = System.currentTimeMillis();
                    SifliDfuService.this.M(21, (int) (((SifliDfuService.this.L / 1024.0d) / (System.currentTimeMillis() - SifliDfuService.this.M)) * 1000.0d * 100.0d));
                    SifliDfuService.this.L = 0L;
                }
                if (byteArray2[1] == 0 || byteArray2[1] == 3) {
                    SifliDfuService.N(SifliDfuService.this);
                }
                SifliDfuService.this.k.postDelayed(SifliDfuService.this.U, 5000L);
                synchronized (SifliDfuService.this.m) {
                    while (!SifliDfuService.this.u && SifliDfuService.this.r == 0) {
                        try {
                            SifliDfuService.this.m.wait();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                    SifliDfuService.this.k.removeCallbacks(SifliDfuService.this.U);
                    if (SifliDfuService.this.r != 0) {
                        Log.e("SifliDfuService", "Write fail");
                        return false;
                    }
                    if (SifliDfuService.this.N == 1) {
                        i = (SifliDfuService.this.G * 100) / SifliDfuService.this.v.g();
                    } else if (SifliDfuService.this.N == 0) {
                        if (SifliDfuService.this.v.d() != 0) {
                            Iterator it = SifliDfuService.this.B.iterator();
                            i2 = 0;
                            while (it.hasNext()) {
                                com.crrepa.k0.e eVar = (com.crrepa.k0.e) it.next();
                                if (eVar.d() < SifliDfuService.this.v.d()) {
                                    i2 += eVar.a();
                                }
                            }
                        } else {
                            i2 = 0;
                        }
                        i = ((i2 + SifliDfuService.this.G) * 100) / SifliDfuService.this.O;
                    } else {
                        i = 0;
                    }
                    SifliDfuService sifliDfuService2 = SifliDfuService.this;
                    sifliDfuService2.z(sifliDfuService2.v.d(), i);
                    if (SifliDfuService.this.G == SifliDfuService.this.v.g()) {
                        Log.i("SifliDfuService", "current image send finish");
                        SifliDfuService.this.y = false;
                        synchronized (SifliDfuService.this.l) {
                            SifliDfuService.this.l.notifyAll();
                        }
                    }
                }
            } else if (i6 == 2) {
                byte[] byteArray3 = message.getData().getByteArray("Sifli.dfu.BLE_DATA");
                if (SifliDfuService.this.p == null) {
                    return false;
                }
                SifliDfuService.this.p.setValue(byteArray3);
                SifliDfuService.this.u = false;
                if (SifliDfuService.this.q == null) {
                    return false;
                }
                if (SifliDfuService.this.q.writeCharacteristic(SifliDfuService.this.p)) {
                    SifliDfuService.this.k.postDelayed(SifliDfuService.this.U, 5000L);
                    synchronized (SifliDfuService.this.m) {
                        while (!SifliDfuService.this.u && SifliDfuService.this.r == 0) {
                            try {
                                SifliDfuService.this.m.wait();
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                        }
                        SifliDfuService.this.k.removeCallbacks(SifliDfuService.this.U);
                        if (SifliDfuService.this.r != 0) {
                            return false;
                        }
                        if (byteArray3[1] == 0 || byteArray3[1] == 3) {
                            SifliDfuService.Y(SifliDfuService.this);
                            SifliDfuService.N(SifliDfuService.this);
                        }
                        if (SifliDfuService.this.N == 1) {
                            i5 = (SifliDfuService.this.G * 100) / SifliDfuService.this.v.g();
                        } else if (SifliDfuService.this.N == 0) {
                            if (SifliDfuService.this.v.d() != 0) {
                                Iterator it2 = SifliDfuService.this.B.iterator();
                                while (it2.hasNext()) {
                                    com.crrepa.k0.e eVar2 = (com.crrepa.k0.e) it2.next();
                                    if (eVar2.d() < SifliDfuService.this.v.d()) {
                                        i5 += eVar2.a();
                                    }
                                }
                            }
                            i5 = ((i5 + SifliDfuService.this.G) * 100) / SifliDfuService.this.O;
                        }
                        SifliDfuService sifliDfuService3 = SifliDfuService.this;
                        sifliDfuService3.z(sifliDfuService3.v.d(), i5);
                        synchronized (SifliDfuService.this.m) {
                            while (SifliDfuService.this.H == SifliDfuService.this.w) {
                                try {
                                    SifliDfuService.this.m.wait();
                                } catch (InterruptedException e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                }
                SifliDfuService.this.r = 100;
                return false;
            } else if (i6 == 3) {
                byte[] byteArray4 = message.getData().getByteArray("Sifli.dfu.BLE_DATA");
                if (SifliDfuService.this.p == null) {
                    return false;
                }
                SifliDfuService.this.p.setValue(byteArray4);
                SifliDfuService.this.u = false;
                if (SifliDfuService.this.q == null) {
                    return false;
                }
                if (SifliDfuService.this.q.writeCharacteristic(SifliDfuService.this.p)) {
                    if (byteArray4[1] == 0 || byteArray4[1] == 3) {
                        SifliDfuService.e0(SifliDfuService.this);
                        SifliDfuService.N(SifliDfuService.this);
                    }
                    System.currentTimeMillis();
                    SifliDfuService.this.k.removeCallbacks(SifliDfuService.this.U);
                    synchronized (SifliDfuService.this.m) {
                        while (!SifliDfuService.this.u && SifliDfuService.this.r != 0) {
                            try {
                                SifliDfuService.this.m.wait();
                            } catch (InterruptedException e5) {
                                e5.printStackTrace();
                            }
                        }
                        SifliDfuService.this.k.removeCallbacks(SifliDfuService.this.U);
                        if (SifliDfuService.this.r != 0) {
                            return false;
                        }
                        if (SifliDfuService.this.N == 1) {
                            i3 = (SifliDfuService.this.G * 100) / SifliDfuService.this.v.g();
                        } else if (SifliDfuService.this.N == 0) {
                            if (SifliDfuService.this.v.d() != 0) {
                                Iterator it3 = SifliDfuService.this.B.iterator();
                                i4 = 0;
                                while (it3.hasNext()) {
                                    com.crrepa.k0.e eVar3 = (com.crrepa.k0.e) it3.next();
                                    if (eVar3.d() < SifliDfuService.this.v.d()) {
                                        i4 += eVar3.a();
                                    }
                                }
                            } else {
                                i4 = 0;
                            }
                            i3 = ((i4 + SifliDfuService.this.G) * 100) / SifliDfuService.this.O;
                        } else {
                            i3 = 0;
                        }
                        SifliDfuService sifliDfuService4 = SifliDfuService.this;
                        sifliDfuService4.z(sifliDfuService4.v.d(), i3);
                        if (SifliDfuService.this.G == SifliDfuService.this.v.g()) {
                            Log.i("SifliDfuService", "current image send finish");
                            SifliDfuService.this.y = false;
                            synchronized (SifliDfuService.this.l) {
                                SifliDfuService.this.l.notifyAll();
                            }
                        }
                        if (SifliDfuService.this.I == 0) {
                            do {
                            } while (SifliDfuService.this.I == 0);
                        }
                    }
                }
                SifliDfuService.this.r = 100;
                return false;
            }
            return true;
        }
    }

    public SifliDfuService() {
        super("SifliDfuService");
        this.h = 244;
        this.i = ByteOrder.LITTLE_ENDIAN;
        this.k = new Handler();
        this.l = new Object();
        this.m = new Object();
        this.n = new i();
        this.B = new ArrayList<>();
        this.D = -1;
        this.E = -1;
        this.R = new a();
        this.S = new b();
        this.T = new c();
        this.U = new d();
        new f(this);
        this.V = new g();
        this.W = new h();
    }

    public SifliDfuService(String str) {
        super(str);
        this.h = 244;
        this.i = ByteOrder.LITTLE_ENDIAN;
        this.k = new Handler();
        this.l = new Object();
        this.m = new Object();
        this.n = new i();
        this.B = new ArrayList<>();
        this.D = -1;
        this.E = -1;
        this.R = new a();
        this.S = new b();
        this.T = new c();
        this.U = new d();
        new f(this);
        this.V = new g();
        this.W = new h();
    }

    public static /* synthetic */ int N(SifliDfuService sifliDfuService) {
        int i2 = sifliDfuService.G;
        sifliDfuService.G = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int Y(SifliDfuService sifliDfuService) {
        int i2 = sifliDfuService.H;
        sifliDfuService.H = i2 + 1;
        return i2;
    }

    public static int a(InputStream inputStream, OutputStream outputStream) throws Exception, IOException {
        byte[] bArr = new byte[2048];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 2048);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 2048);
        int i2 = 0;
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr, 0, 2048);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i2 += read;
            } finally {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e3) {
                    Log.e("SifliDfuService", "out close error", e3);
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    Log.e("SifliDfuService", "in close error", e4);
                }
            }
        }
        bufferedOutputStream.flush();
        return i2;
    }

    public static String a(Context context, Uri uri) {
        File externalFilesDir = context.getExternalFilesDir(null);
        String a3 = a(uri);
        if (TextUtils.isEmpty(a3)) {
            return null;
        }
        File file = new File(externalFilesDir + File.separator + a3);
        a(context, uri, file);
        return file.getAbsolutePath();
    }

    public static String a(Uri uri) {
        String path;
        int lastIndexOf;
        if (uri == null || (lastIndexOf = (path = uri.getPath()).lastIndexOf(47)) == -1) {
            return null;
        }
        return path.substring(lastIndexOf + 1);
    }

    public static String a(byte[] bArr) {
        String str = "";
        for (byte b3 : bArr) {
            String hexString = Integer.toHexString(b3 & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase();
        }
        return str;
    }

    public static void a(Context context, Uri uri, File file) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            a(openInputStream, fileOutputStream);
            openInputStream.close();
            fileOutputStream.close();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static /* synthetic */ int e0(SifliDfuService sifliDfuService) {
        int i2 = sifliDfuService.I;
        sifliDfuService.I = i2 - 1;
        return i2;
    }

    public static /* synthetic */ long w(SifliDfuService sifliDfuService, long j2) {
        long j3 = sifliDfuService.L + j2;
        sifliDfuService.L = j3;
        return j3;
    }

    public final void A(byte[] bArr) {
        byte b3;
        String str;
        if (bArr[0] != 1) {
            Log.e("SifliDfuService", "not dfu cate id");
        } else if (bArr[1] != 0) {
            Log.e("SifliDfuService", "more packets to receive, illegal flag");
            Log.e("SifliDfuService", "notify value：" + Integer.toHexString(bArr[0] & 255) + Integer.toHexString(bArr[1] & 255) + Integer.toHexString(bArr[2] & 255) + Integer.toHexString(bArr[3] & 255));
        } else {
            c(bArr, 2);
            int c3 = c(bArr, 4);
            c(bArr, 6);
            if (c3 == 1) {
                Log.d("SifliDfuService", "receive init response");
                this.x = true;
                int c4 = c(bArr, 8);
                byte b4 = bArr[10];
                Log.d("SifliDfuService", "receive init response, result: " + c4 + ", boot: " + ((int) b4));
                M(1, c4);
                this.t.a(b4);
                this.D = c4;
            } else if (c3 == 4) {
                this.x = true;
                int c5 = c(bArr, 8);
                M(4, c5);
                this.D = c5;
                this.J = System.currentTimeMillis();
                if (c5 != 0) {
                    Log.i("SifliDfuService", "fail to resume");
                    this.t.a(0);
                    return;
                }
                byte b5 = bArr[10];
                this.t.d(b5);
                this.t.a(b5);
                this.t.e(bArr[11]);
                int c6 = c(bArr, 12);
                Log.i("SifliDfuService", "resume rsp: boot " + ((int) b5) + ", resume restart " + ((int) b3));
                byte b6 = bArr[14];
                Log.i("SifliDfuService", "resume rsp, img id " + ((int) b6) + ", img num " + c6);
                if (p(b6, c6)) {
                    return;
                }
                y(64);
                this.D = 64;
            } else if (c3 == 7) {
                int c7 = c(bArr, 8);
                Log.d("SifliDfuService", "SIFLI_DFU_IMAGE_SEND_START_RESPONSE " + c7);
                M(7, c7);
                this.x = true;
                this.E = c7;
                this.J = System.currentTimeMillis();
            } else if (c3 == 9) {
                this.x = true;
                int c8 = c(bArr, 8);
                Log.d("SifliDfuService", "send image end rsp " + c8);
                M(9, c8);
                long currentTimeMillis = System.currentTimeMillis();
                this.K = currentTimeMillis;
                double e3 = currentTimeMillis - this.J > 0 ? (((this.v.e() - (this.t.c() * 548.0d)) / 1024.0d) / (this.K - this.J)) * 1000.0d : 0.0d;
                if (Build.VERSION.SDK_INT >= 24) {
                    str = "speed: " + new BigDecimal(e3).setScale(2, 4).doubleValue() + "kb/s";
                } else {
                    str = "speed: " + e3 + "kb/s";
                }
                j(5, str);
                this.t.b(0);
                this.F = c8;
            } else if (c3 != 11) {
                if (c3 != 15) {
                    Log.w("SifliDfuService", "unknown packet");
                } else {
                    Log.e("SifliDfuService", "sync req");
                }
            } else {
                int c9 = c(bArr, 8);
                if (c9 != 0) {
                    Log.e("SifliDfuService", "send response error:" + c9);
                    j(5, "packet rsq error with: " + c9);
                    this.j.removeCallbacksAndMessages(null);
                    a(this.q);
                }
                M(11, c9);
                this.t.a();
                this.H = 0;
                synchronized (this.m) {
                    this.m.notifyAll();
                }
                this.I += this.w;
                Log.d("SifliDfuService", "sendRspCount: " + this.t.f() + ", ExpectRspCount: " + this.v.b());
            }
        }
    }

    public void B(byte[] bArr, int i2) {
        Message message = new Message();
        message.what = i2;
        if (i2 == 1) {
            message.what = this.P;
        }
        Bundle bundle = new Bundle();
        bundle.putByteArray("Sifli.dfu.BLE_DATA", bArr);
        message.setData(bundle);
        this.j.sendMessage(message);
    }

    public final byte[] D(int i2, byte[] bArr, int i3) {
        System.arraycopy(ByteBuffer.allocate(2).order(this.i).putShort((short) i2).array(), 0, bArr, i3, 2);
        return bArr;
    }

    public final com.crrepa.k0.e H() {
        Iterator<com.crrepa.k0.e> it = this.B.iterator();
        while (it.hasNext()) {
            com.crrepa.k0.e next = it.next();
            if (next.d() == 0) {
                return next;
            }
        }
        return null;
    }

    public final void I(int i2, int i3) {
        com.crrepa.k0.e eVar;
        int c3;
        int i4 = i2 == 0 ? 1 : 0;
        Log.i("SifliDfuService", "send dfu resume completed, start " + i4 + ", reboot " + i3);
        byte[] D = D(1, D(5, new byte[5], 0), 2);
        D[4] = (byte) i4;
        if (i4 != 1) {
            this.t.g(0);
            o(D, 5, 0);
            return;
        }
        this.t.g(6);
        if (this.t.c() == this.v.g()) {
            eVar = this.v;
            c3 = eVar.e();
        } else {
            eVar = this.v;
            c3 = this.t.c() * 548;
        }
        eVar.d(c3);
        if (i3 != 0) {
            this.t.g(7);
        }
        o(D, 5, 0);
    }

    public final com.crrepa.k0.e L() {
        if (Build.VERSION.SDK_INT >= 24) {
            this.B.sort(new e(this));
        }
        Iterator<com.crrepa.k0.e> it = this.B.iterator();
        while (it.hasNext()) {
            com.crrepa.k0.e next = it.next();
            if (this.v.d() < next.d()) {
                return next;
            }
        }
        return null;
    }

    public void M(int i2, int i3) {
        Intent intent = new Intent(Z);
        intent.putExtra(e0, i2);
        intent.putExtra(f0, i3);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public final boolean O() {
        String str;
        this.t = new com.crrepa.k0.d();
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            str = "Unable to initialize BluetoothManager.";
        } else {
            BluetoothAdapter adapter = bluetoothManager.getAdapter();
            this.o = adapter;
            if (adapter != null) {
                HandlerThread handlerThread = new HandlerThread("BleWrite");
                handlerThread.start();
                this.j = new Handler(handlerThread.getLooper(), new j());
                return true;
            }
            str = "Unable to obtain a BluetoothAdapter.";
        }
        Log.e("SifliDfuService", str);
        return false;
    }

    public final void Q() {
        this.t.f(0);
        this.v.b(1);
    }

    public boolean S() {
        Iterator<com.crrepa.k0.e> it = this.B.iterator();
        while (it.hasNext()) {
            if (!it.next().f()) {
                return false;
            }
        }
        return true;
    }

    public final void U() {
        registerReceiver(this.W, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    public final boolean X() {
        if (this.A == null) {
            Log.e("SifliDfuService", "init file is empty");
            return false;
        }
        j(5, "send force init");
        this.x = false;
        int length = this.A.length;
        int i2 = length + 4;
        byte[] D = D(length, D(14, new byte[i2], 0), 2);
        System.arraycopy(this.A, 0, D, 4, length);
        this.t.g(1);
        o(D, i2, 0);
        try {
            synchronized (this.l) {
                while (!this.x && this.r == 0) {
                    this.l.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("SifliDfuService", "Sleeping interrupted");
        }
        return true;
    }

    public final void Z() {
        Log.i("SifliDfuService", "sendDfuImageEnd");
        j(5, "send dfu image end");
        this.x = false;
        byte[] D = D(2, D(8, new byte[6], 0), 2);
        D[4] = (byte) this.v.d();
        int i2 = 1;
        this.v.a(true);
        if (S()) {
            Log.d("SifliDfuService", "all image has transport");
            i2 = 0;
        }
        D[5] = (byte) i2;
        Log.d("SifliDfuService", "sendDfuImageEnd, more image: " + i2);
        o(D, 6, 0);
        try {
            synchronized (this.l) {
                while (!this.x && this.r == 0) {
                    this.l.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("SifliDfuService", "Sleeping interrupted");
        }
    }

    public void a(long j2) {
        synchronized (this.l) {
            try {
                j(0, "wait(" + j2 + ")");
                this.l.wait(j2);
            } catch (InterruptedException unused) {
                Log.e("SifliDfuService", "Sleeping interrupted");
            }
        }
    }

    public void a(BluetoothGatt bluetoothGatt) {
        Log.d("SifliDfuService", "Cleaning up...");
        Log.d("SifliDfuService", "gatt.disconnect()");
        bluetoothGatt.disconnect();
        Log.d("SifliDfuService", "gatt.close()");
        bluetoothGatt.close();
        this.f7900a = 5;
    }

    public void b(BluetoothGatt bluetoothGatt) {
        if (this.f7900a == 0) {
            return;
        }
        Log.d("SifliDfuService", "Disconnecting");
        this.f7900a = 4;
        bluetoothGatt.disconnect();
        q();
    }

    public final boolean b0() {
        Log.i("SifliDfuService", "send dfu init");
        byte[] bArr = this.A;
        if (bArr == null) {
            Log.e("SifliDfuService", "init file is empty");
            return false;
        }
        this.x = false;
        int length = bArr.length;
        Log.d("SifliDfuService", "send dfu init len " + length);
        j(5, "send init file.");
        y(0);
        int i2 = length + 4;
        byte[] D = D(length, D(0, new byte[i2], 0), 2);
        System.arraycopy(this.A, 0, D, 4, length);
        this.t.g(1);
        o(D, i2, 0);
        this.k.postDelayed(this.R, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.l) {
                while (!this.x && this.r == 0) {
                    this.l.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("SifliDfuService", "Sleeping interrupted");
        }
        this.k.removeCallbacks(this.R);
        return true;
    }

    public final int c(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, i2, bArr2, 0, 2);
        return ByteBuffer.wrap(bArr2, 0, 2).order(this.i).getShort();
    }

    public void c(BluetoothGatt bluetoothGatt) {
        try {
            boolean booleanValue = ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
            Log.i("SifliDfuService", "Refreshing result: " + booleanValue);
        } catch (Exception e3) {
            Log.e("SifliDfuService", "An exception occurred while refreshing device", e3);
            j(15, "Refreshing failed");
        }
    }

    public void d(@NonNull BluetoothGatt bluetoothGatt) {
        if (this.f7900a != 0) {
            b(bluetoothGatt);
        }
        c(bluetoothGatt);
        a(bluetoothGatt);
        a(600L);
    }

    public final void d0() {
        if (this.r != 0) {
            return;
        }
        while (this.v.e() - this.v.i() >= 548) {
            byte[] D = D(548, D(this.v.a(), D(this.v.d(), D(554, D(10, new byte[com.veryfit.multi.nativeprotocol.b.c2], 0), 2), 4), 6), 8);
            System.arraycopy(this.v.a(548), 0, D, 10, 548);
            o(D, com.veryfit.multi.nativeprotocol.b.c2, 1);
        }
        if (this.v.e() - this.v.i() != 0) {
            int e3 = this.v.e() - this.v.i();
            int i2 = e3 + 6 + 4;
            byte[] D2 = D(e3, D(this.v.a(), D(this.v.d(), D(554, D(10, new byte[i2], 0), 2), 4), 6), 8);
            System.arraycopy(this.v.a(e3), 0, D2, 10, e3);
            o(D2, i2, 1);
        }
        if (this.v.e() == this.v.i()) {
            Log.i("SifliDfuService", "resume last packet, send nothing");
            this.y = false;
            synchronized (this.l) {
                this.l.notifyAll();
            }
        }
    }

    public final BluetoothGatt f(BluetoothDevice bluetoothDevice) {
        BluetoothGatt connectGatt;
        if (this.o.isEnabled()) {
            this.f7900a = 1;
            Log.i("SifliDfuService", "connecting to " + bluetoothDevice);
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 26) {
                j(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, preferredPhy = LE_1M | LE_2M)");
                connectGatt = bluetoothDevice.connectGatt(this, true, this.V, 2, 3);
            } else if (i2 >= 23) {
                j(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
                connectGatt = bluetoothDevice.connectGatt(this, true, this.V, 2);
            } else {
                j(0, "gatt = device.connectGatt(autoConnect = false)");
                connectGatt = bluetoothDevice.connectGatt(this, true, this.V);
            }
            try {
                synchronized (this.l) {
                    while (true) {
                        int i3 = this.f7900a;
                        if ((i3 == 1 || i3 == 2) && this.r == 0) {
                            this.l.wait();
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Log.e("SifliDfuService", "Sleeping interrupted");
            }
            return connectGatt;
        }
        return null;
    }

    public final void f0() {
        Log.i("SifliDfuService", "send dfu resume");
        if (this.A == null) {
            Log.e("SifliDfuService", "resume ctrl file is empty");
            return;
        }
        y(3);
        this.x = false;
        int length = this.A.length;
        int i2 = length + 4;
        byte[] D = D(length, D(3, new byte[i2], 0), 2);
        System.arraycopy(this.A, 0, D, 4, length);
        this.t.g(6);
        o(D, i2, 0);
        try {
            synchronized (this.l) {
                while (!this.x && this.r == 0) {
                    this.l.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("SifliDfuService", "Sleeping interrupted");
        }
    }

    public final BluetoothGatt g(BluetoothDevice bluetoothDevice, BluetoothGattCallback bluetoothGattCallback) {
        BluetoothGatt connectGatt;
        if (this.o.isEnabled()) {
            this.f7900a = 1;
            Log.i("SifliDfuService", "connecting to " + bluetoothDevice);
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 26) {
                j(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, preferredPhy = LE_1M | LE_2M)");
                connectGatt = bluetoothDevice.connectGatt(this, false, bluetoothGattCallback, 2, 3);
            } else if (i2 >= 23) {
                j(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
                connectGatt = bluetoothDevice.connectGatt(this, false, bluetoothGattCallback, 2);
            } else {
                j(0, "gatt = device.connectGatt(autoConnect = false)");
                connectGatt = bluetoothDevice.connectGatt(this, false, bluetoothGattCallback);
            }
            try {
                synchronized (this.l) {
                    while (true) {
                        int i3 = this.f7900a;
                        if ((i3 == 1 || i3 == 2) && this.r == 0) {
                            this.l.wait();
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Log.e("SifliDfuService", "Sleeping interrupted");
            }
            return connectGatt;
        }
        return null;
    }

    public final void h0() {
        Log.i("SifliDfuService", "send dfu start");
        this.x = false;
        this.M = 0L;
        this.L = 0L;
        com.crrepa.k0.e eVar = this.v;
        if (eVar == null) {
            Log.e("SifliDfuService", "image file is empty");
            y(38);
            return;
        }
        M(6, eVar.d());
        j(5, "IMG ID: " + this.v.d());
        j(5, "remote is erasing flash...");
        byte[] s = s(this.v.g(), s(this.v.e(), D(10, D(6, new byte[14], 0), 2), 4), 8);
        s[12] = (byte) this.w;
        s[13] = (byte) this.v.d();
        this.v.c(this.w);
        Q();
        if (this.t.g() == 6 || this.t.g() == 7) {
            if (this.t.e() == 0) {
                if (this.w != 0) {
                    int c3 = this.t.c() / this.w;
                    Log.i("SifliDfuService", "resume send rsp pack already notified count: " + c3 + ", transport size: " + this.v.i());
                    this.t.f(c3);
                }
                this.v.b(this.t.c() + 1);
            } else {
                this.t.f(0);
                this.v.b(1);
            }
        }
        if (this.t.g() == 7 || this.t.g() == 6) {
            Log.d("SifliDfuService", "keep state in " + this.t.g());
        } else {
            this.t.g(3);
        }
        Log.i("SifliDfuService", "sendDfuStart, total size: " + this.v.e() + ", total count: " + this.v.g() + ", expect rsp count: " + this.v.b());
        o(s, 14, 0);
        try {
            synchronized (this.l) {
                while (!this.x && this.r == 0) {
                    this.l.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("SifliDfuService", "Sleeping interrupted");
        }
    }

    public final void i(int i2) {
        byte[] D = D(1, D(2, new byte[5], 0), 2);
        int i3 = i2 != 0 ? 0 : 1;
        D[4] = (byte) i3;
        Log.i("SifliDfuService", "send dfu init complete, start: " + i3);
        this.t.g(2);
        o(D, 5, 0);
    }

    public void j(int i2, String str) {
        Intent intent = new Intent(Y);
        intent.putExtra(c0, "[DFU] " + str);
        intent.putExtra(d0, i2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public final void j0() {
        Log.i("SifliDfuService", "sendDfuTransmissionEnd");
        j(5, "send dfu end, dfu service is going down");
        y(12);
        byte[] D = D(1, D(12, new byte[5], 0), 2);
        D[3] = 0;
        this.t.g(4);
        o(D, 5, 0);
    }

    public final void k(BluetoothGatt bluetoothGatt, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            bluetoothGatt.requestMtu(i2);
        }
    }

    public final void l(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (descriptor == null) {
            Log.e("SifliDfuService", "desc null!!!");
            return;
        }
        this.z = false;
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        Log.d("SifliDfuService", "Write descriptor");
        this.k.postDelayed(this.T, 30000L);
        bluetoothGatt.writeDescriptor(descriptor);
    }

    public final void o(byte[] bArr, int i2, int i3) {
        int i4 = i2 + 4;
        if (i4 > 65535) {
            Log.e("SifliDfuService", "serial length over");
            return;
        }
        int i5 = this.h;
        if (i4 <= i5) {
            byte[] bArr2 = new byte[i4];
            bArr2[0] = 1;
            bArr2[1] = 0;
            byte[] D = D(i2, bArr2, 2);
            System.arraycopy(bArr, 0, D, 4, i2);
            B(D, i3);
            return;
        }
        byte[] bArr3 = new byte[i5];
        bArr3[0] = 1;
        bArr3[1] = 1;
        byte[] D2 = D(i2, bArr3, 2);
        System.arraycopy(bArr, 0, D2, 4, this.h - 4);
        int i6 = (this.h - 4) + 0;
        B(D2, i3);
        while (i6 < i2) {
            int i7 = i2 - i6;
            int i8 = this.h;
            int i9 = (i8 - 4) + 2;
            if (i7 > i9) {
                byte[] bArr4 = new byte[i8];
                bArr4[0] = 1;
                bArr4[1] = 2;
                System.arraycopy(bArr, i6, bArr4, 2, i9);
                i6 += (this.h - 4) + 2;
                B(bArr4, i3);
            } else {
                byte[] bArr5 = new byte[(i7 + 4) - 2];
                bArr5[0] = 1;
                bArr5[1] = 3;
                System.arraycopy(bArr, i6, bArr5, 2, i7);
                B(bArr5, i3);
                i6 = i2;
            }
        }
    }

    @Override // android.app.IntentService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.n;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.e("SifliDfuService", "create.");
        if (O()) {
            return;
        }
        Log.e("SifliDfuService", "init fail");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.e("SifliDfuService", "onDestroy");
        this.j.removeCallbacksAndMessages(null);
        BluetoothGatt bluetoothGatt = this.q;
        if (bluetoothGatt != null) {
            a(bluetoothGatt);
        }
        this.q = null;
        this.k.removeCallbacks(this.S);
        this.k.removeCallbacks(this.R);
        this.k.removeCallbacks(this.T);
        this.k.removeCallbacks(this.U);
        unregisterReceiver(this.W);
        M(19, this.r);
        Log.i("SifliDfuService", "Service is invoke Destroyed");
    }

    /* JADX WARN: Removed duplicated region for block: B:153:0x0423 A[Catch: all -> 0x042f, LOOP:4: B:153:0x0423->B:155:0x0427, LOOP_START, TRY_ENTER, TryCatch #4 {InterruptedException -> 0x0432, blocks: (B:151:0x0420, B:152:0x0422, B:153:0x0423, B:155:0x0427, B:156:0x042d), top: B:256:0x0420 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x044e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0238  */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onHandleIntent(android.content.Intent r17) {
        /*
            Method dump skipped, instructions count: 1544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.dfulibrary.SifliDfuService.onHandleIntent(android.content.Intent):void");
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.i("SifliDfuService", "onStartCommand");
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i("SifliDfuService", "Service is invoke onUnbind");
        return super.onUnbind(intent);
    }

    public final boolean p(int i2, int i3) {
        com.crrepa.k0.e eVar = this.v;
        if (eVar == null || eVar.d() != i2) {
            Iterator<com.crrepa.k0.e> it = this.B.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.crrepa.k0.e next = it.next();
                if (next.d() == i2) {
                    this.v = next;
                    break;
                }
            }
        }
        if (this.v.d() != i2) {
            Log.e("SifliDfuService", "can not find img ID: " + i2);
            return false;
        } else if (this.v.g() < i3) {
            Log.e("SifliDfuService", "img total count: " + this.v.g() + ", current count: " + i3);
            return false;
        } else {
            Iterator<com.crrepa.k0.e> it2 = this.B.iterator();
            while (it2.hasNext()) {
                com.crrepa.k0.e next2 = it2.next();
                if (next2.d() < this.v.d()) {
                    next2.a(true);
                }
            }
            this.t.b(i3);
            return true;
        }
    }

    public void q() {
        try {
            synchronized (this.l) {
                while (this.f7900a != 0 && this.r == 0) {
                    this.l.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("SifliDfuService", "Sleeping interrupted");
        }
    }

    public final boolean r(String str, boolean z, BluetoothGattCallback bluetoothGattCallback, Intent intent) {
        String format;
        Log.i("SifliDfuService", "init connect");
        this.r = 0;
        BluetoothDevice remoteDevice = this.o.getRemoteDevice(str);
        this.C = remoteDevice;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        BluetoothGatt f3 = z ? f(remoteDevice) : g(remoteDevice, bluetoothGattCallback);
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        if (f3 == null) {
            Log.e("SifliDfuService", "Bluetooth adapter disabled");
        }
        this.q = f3;
        int i2 = this.r;
        if (i2 <= 0) {
            if (this.f7900a != 0) {
                Log.d("SifliDfuService", "connect success");
                j(5, "Services discovered");
                return true;
            }
            Log.e("SifliDfuService", "Disconnect due to state disconnect");
            j(20, "Disconnected");
            d(f3);
            return true;
        }
        int i3 = this.s;
        if (i2 == 105) {
            Log.i("SifliDfuService", "Connection error after: " + (elapsedRealtime2 - elapsedRealtime) + " ms");
            if (i3 == 133 && elapsedRealtime2 > elapsedRealtime + 25000) {
                Log.e("SifliDfuService", "Device not reachable. Check if the device with address " + str + " is in range, is advertising and is connectable");
                format = "Error 133: Connect timeout";
            } else {
                Log.e("SifliDfuService", "An error occurred while connecting to the device:" + i3);
                format = String.format(Locale.US, "Connection failed (0x%02X)", Integer.valueOf(i3));
            }
        } else if (i2 == 107) {
            Log.e("SifliDfuService", "error discovery not start");
            format = String.format(Locale.US, "error discovery not start, Connection failed (0x%02X)", Integer.valueOf(i3));
        } else if (i2 == 102) {
            Log.e("SifliDfuService", "discovery time out");
            format = String.format(Locale.US, "discovery time out, Connection failed (0x%02X)", Integer.valueOf(i3));
        } else {
            Log.e("SifliDfuService", "An error occurred during discovering services:" + i3);
            format = String.format(Locale.US, "discovery error, Connection failed (0x%02X)", Integer.valueOf(i3));
        }
        j(20, format);
        int intExtra = intent.getIntExtra("Sifli.dfu.EXTRA_RECONNECTION_ATTEMPT", 0);
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt: ");
        int i4 = intExtra + 1;
        sb.append(i4);
        Log.i("SifliDfuService", sb.toString());
        if (intExtra >= 2) {
            d(f3);
            return true;
        }
        Log.i("SifliDfuService", "Retrying");
        j(15, "Retrying...");
        if (this.f7900a != 0) {
            b(f3);
        }
        a(f3);
        Log.i("SifliDfuService", "restart tht service");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        intent2.putExtra("Sifli.dfu.EXTRA_RECONNECTION_ATTEMPT", i4);
        startService(intent2);
        return false;
    }

    public final byte[] s(int i2, byte[] bArr, int i3) {
        System.arraycopy(ByteBuffer.allocate(4).order(this.i).putInt(i2).array(), 0, bArr, i3, 4);
        return bArr;
    }

    public final byte[] t(String str) {
        File file = new File(str);
        byte[] bArr = new byte[0];
        getResources().getAssets();
        try {
            Log.i("SifliDfuService", "processFileNew: " + str);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "ISO-8859-1");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = bufferedReader.read();
                if (read == -1) {
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    return bArr;
                }
                byteArrayOutputStream.write(read);
            }
        } catch (FileNotFoundException unused) {
            Log.e("SifliDfuService", str + " doesn't found!");
            return null;
        } catch (IOException e3) {
            Log.e("SifliDfuService", str + " read exception, " + e3.getMessage());
            e3.printStackTrace();
            return bArr;
        }
    }

    public final int u() {
        Iterator<com.crrepa.k0.e> it = this.B.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().g();
        }
        return i2;
    }

    public void y(int i2) {
        Intent intent = new Intent(Z);
        intent.putExtra(e0, i2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void z(int i2, int i3) {
        Intent intent = new Intent(Z);
        intent.putExtra(e0, 20);
        intent.putExtra(x0, i2);
        intent.putExtra(y0, i3);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
