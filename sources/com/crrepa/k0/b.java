package com.crrepa.k0;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.UUID;
/* loaded from: classes9.dex */
public class b {
    public static final int A = 3;
    public static final int B = 4;
    public static final int C = 5;
    public static final int D = 6;
    public static final int E = 7;
    public static final int F = 8;
    public static final int G = 9;
    public static final int G0 = 548;
    public static final int H = 10;
    public static final int H0 = 6;
    public static final int I = 0;
    public static final int I0 = 23;
    public static final int J = 1;
    public static final int J0 = 247;
    public static final int K = 2;
    public static final int L = 3;
    public static final int M = 4;
    public static final int N = 5;
    public static final int O = 6;
    public static final int P = 7;
    public static final int Q = 8;
    public static final int R = 9;
    public static final int S = 10;
    public static final int T = 11;
    public static final int U = 12;
    public static final int V = 13;
    public static final int W = 14;
    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    public static final int a0 = 3;
    public static final int b0 = 4;
    public static final int c0 = 4;
    public static final int d0 = 0;
    public static final int e0 = 1;
    public static final int f0 = 2;
    public static final int g0 = 3;
    public static final int h0 = 4;
    public static final int i0 = 5;
    public static final int j0 = 6;
    public static final int k0 = 7;
    public static final int l0 = 8;
    public static final int m0 = 9;
    public static final int n0 = 10;
    public static final int o0 = 11;
    public static final int p0 = 64;
    public static final int q0 = 64;
    public static final int r0 = 128;
    public static final int s0 = 1;
    public static final String t = "00002902-0000-1000-8000-00805f9b34fb";
    public static final String w = "00000000-0000-0200-6473-5f696c666973";
    public static final int x = 0;
    public static final int y = 1;
    public static final int z = 2;

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f7743a;
    public BluetoothGatt b;
    public com.crrepa.k0.d c;
    public BluetoothGattCharacteristic d;
    public Handler e;
    public Context f;
    public int h;
    public byte[] i;
    public int j;
    public e k;
    public int n;
    public c p;
    public ByteOrder g = ByteOrder.LITTLE_ENDIAN;
    public boolean l = true;
    public ArrayList<e> m = new ArrayList<>();
    public final BluetoothGattCallback o = new C0341b();

    /* loaded from: classes9.dex */
    public class a implements Comparator<e> {
        public a(b bVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(e eVar, e eVar2) {
            return Integer.compare(eVar.d(), eVar2.d());
        }
    }

    /* renamed from: com.crrepa.k0.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class C0341b extends BluetoothGattCallback {
        public C0341b() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Log.i("SIFLI", "onCharacteristicChanged");
            b.this.o(bluetoothGattCharacteristic.getValue());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i != 0) {
                Log.e("SIFLI", "onCharacteristicRead Fail");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            b bVar;
            int i2;
            if (i != 0) {
                Log.e("SIFLI", "Write fail: " + i);
                if (b.this.c.g() == 3) {
                    bVar = b.this;
                    i2 = 7;
                } else {
                    bVar = b.this;
                    i2 = 2;
                }
                bVar.e(i2);
            }
            b.this.l = true;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            Log.i("SIFLI", "onConnectionStateChange");
            if (i2 != 2) {
                if (i2 == 0) {
                    b.this.e(1);
                    Log.i("SIFLI", "Disconnected from GATT server. " + i);
                    if (bluetoothGatt == b.this.b) {
                        Log.d("SIFLI", "mBluetoothGatt Disconnect");
                        if (b.this.c.g() == 3) {
                            Log.e("SIFLI", "downloading interrupt");
                        }
                        if (b.this.c.g() == 4) {
                            Log.d("SIFLI", "Do not close gatt for auto connect after reboot");
                            return;
                        } else if (b.this.c.g() == 2 || b.this.c.g() == 7) {
                            Log.i("SIFLI", "reconnect after reboot");
                            b bVar = b.this;
                            bVar.b = bVar.f7743a.connectGatt(b.this.f, true, b.this.o);
                        }
                    }
                    bluetoothGatt.close();
                    return;
                }
                return;
            }
            b.this.e(0);
            try {
                if (b.this.c.g() == 2 || b.this.c.g() == 7) {
                    Log.i("SIFLI", "init reboot or resume reboot connected");
                    b.this.C(247);
                    b.this.H();
                    Thread.sleep(1000L);
                    int i3 = Build.VERSION.SDK_INT;
                    if (i3 >= 26) {
                        b.this.b.setPreferredPhy(2, 2, 0);
                    }
                    if (i3 >= 21) {
                        b.this.b.requestConnectionPriority(1);
                    }
                    Thread.sleep(500L);
                    Log.i("SIFLI", "Connected to GATT server.");
                    Log.i("SIFLI", "Attempting to start service discovery:" + b.this.b.discoverServices());
                    return;
                }
                b.this.C(247);
                b.this.H();
                if (b.this.c.g() == 4) {
                    b.this.c.g(0);
                    b.this.e(10);
                }
                Thread.sleep(500L);
                int i4 = Build.VERSION.SDK_INT;
                if (i4 >= 26) {
                    b.this.b.setPreferredPhy(2, 2, 0);
                }
                if (i4 >= 21) {
                    b.this.b.requestConnectionPriority(1);
                }
                Thread.sleep(500L);
                Log.i("SIFLI", "Connected to GATT server.");
                Log.i("SIFLI", "Attempting to start service discovery:" + b.this.b.discoverServices());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i != 0) {
                Log.e("SIFLI", "onDescriptorWrite Fail");
                b.this.e(6);
                return;
            }
            Log.d("SIFLI", "write descriptor success, ready to init or send");
            b.this.e(5);
            if (b.this.c.g() == 2 || b.this.c.g() == 7) {
                b bVar = b.this;
                bVar.k = bVar.d();
                b.this.Q();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 != 0) {
                Log.e("SIFLI", "onMtuChanged Fail");
                return;
            }
            b.this.h = i;
            Log.d("SIFLI", "mtu change to " + i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                Log.d("SIFLI", "onServicesDiscovered");
            } else {
                Log.w("SIFLI", "onServicesDiscovered received: " + i);
            }
            for (int i2 = 0; i2 < bluetoothGatt.getServices().size(); i2++) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGatt.getServices().get(i2).getCharacteristics()) {
                    if (bluetoothGattCharacteristic.getUuid().toString().equals("00000000-0000-0200-6473-5f696c666973")) {
                        Log.i("SIFLI", "set write");
                        b.this.d = bluetoothGattCharacteristic;
                        b.this.b.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
                        if (descriptor == null) {
                            Log.e("SIFLI", "desc null!!!");
                            return;
                        }
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                        Log.d("SIFLI", "Write descriptor");
                        if (!b.this.b.writeDescriptor(descriptor)) {
                            b.this.e(6);
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface c {
        void a(int i);

        void a(int i, int i2);

        void b(int i, int i2);
    }

    /* loaded from: classes9.dex */
    public class d implements Handler.Callback {
        public d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            b bVar;
            int i;
            byte[] byteArray = message.getData().getByteArray("sifli_ble_data");
            if (b.this.d != null) {
                b.this.d.setValue(byteArray);
                b.this.l = false;
                if (!b.this.b.writeCharacteristic(b.this.d)) {
                    Log.e("SIFLI", "writeCharacteristic fail");
                }
                long currentTimeMillis = System.currentTimeMillis();
                while (!b.this.l) {
                    if (System.currentTimeMillis() - currentTimeMillis > 5000) {
                        Log.e("SIFLI", "Write fail, time out");
                        bVar = b.this;
                        i = 3;
                    } else if (b.this.l) {
                        return true;
                    }
                }
                return true;
            }
            bVar = b.this;
            i = 4;
            bVar.e(i);
            return false;
        }
    }

    public b(Context context, BluetoothDevice bluetoothDevice) {
        this.f = context;
        this.f7743a = bluetoothDevice;
    }

    public void A(byte[] bArr) {
        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putByteArray("sifli_ble_data", bArr);
        message.setData(bundle);
        this.e.sendMessage(message);
    }

    public final void B(byte[] bArr, int i) {
        int i2 = i + 4;
        if (i2 <= 244) {
            byte[] bArr2 = new byte[i2];
            bArr2[0] = 1;
            bArr2[1] = 0;
            byte[] q = q(i, bArr2, 2);
            System.arraycopy(bArr, 0, q, 4, i);
            A(q);
            return;
        }
        byte[] bArr3 = new byte[244];
        bArr3[0] = 1;
        bArr3[1] = 1;
        byte[] q2 = q(i, bArr3, 2);
        int i3 = 240;
        System.arraycopy(bArr, 0, q2, 4, 240);
        A(q2);
        while (i3 < i) {
            int i4 = i - i3;
            if (i4 > 242) {
                byte[] bArr4 = new byte[244];
                bArr4[0] = 1;
                bArr4[1] = 2;
                System.arraycopy(bArr, i3, bArr4, 2, 242);
                i3 += 242;
                A(bArr4);
            } else {
                byte[] bArr5 = new byte[(i4 + 4) - 2];
                bArr5[0] = 1;
                bArr5[1] = 3;
                System.arraycopy(bArr, i3, bArr5, 2, i4);
                A(bArr5);
                i3 = i;
            }
        }
    }

    public final void C(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.b.requestMtu(i);
        }
    }

    public boolean F() {
        Iterator<e> it = this.m.iterator();
        while (it.hasNext()) {
            if (!it.next().f()) {
                return false;
            }
        }
        return true;
    }

    public final boolean H() {
        try {
            Method method = this.b.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(this.b, new Object[0])).booleanValue();
            }
        } catch (Exception unused) {
            Log.e("SIFLI", "An exception occurred while refreshing device");
        }
        return false;
    }

    public final boolean J() {
        if (this.i == null) {
            Log.e("SIFLI", "ctrl file is empty");
            return false;
        }
        int i = this.j + 4;
        byte[] q = q(this.j, q(14, new byte[i], 0), 2);
        System.arraycopy(this.i, 0, q, 4, this.j);
        this.c.g(1);
        w(q, i);
        return true;
    }

    public final void L() {
        Log.i("SIFLI", "sendDfuImageEnd");
        int i = 0;
        byte[] q = q(2, q(8, new byte[6], 0), 2);
        q[4] = (byte) this.k.d();
        this.k.a(true);
        if (F()) {
            Log.d("SIFLI", "all image has transport");
        } else {
            i = 1;
        }
        q[5] = (byte) i;
        Log.d("SIFLI", "sendDfuImageEnd, more image: " + i);
        t(8, i);
        w(q, 6);
    }

    public final boolean N() {
        Log.i("SIFLI", "send dfu init");
        if (this.i == null) {
            Log.e("SIFLI", "ctrl file is empty");
            return false;
        }
        int i = this.j + 4;
        byte[] q = q(this.j, q(0, new byte[i], 0), 2);
        System.arraycopy(this.i, 0, q, 4, this.j);
        this.c.g(1);
        w(q, i);
        return true;
    }

    public final void O() {
        while (this.k.e() - this.k.i() >= 548) {
            byte[] q = q(548, q(this.k.a(), q(this.k.d(), q(554, q(10, new byte[com.veryfit.multi.nativeprotocol.b.c2], 0), 2), 4), 6), 8);
            System.arraycopy(this.k.a(548), 0, q, 10, 548);
            B(q, com.veryfit.multi.nativeprotocol.b.c2);
        }
        if (this.k.e() - this.k.i() != 0) {
            int e = this.k.e() - this.k.i();
            int i = e + 6 + 4;
            byte[] q2 = q(e, q(this.k.a(), q(this.k.d(), q(554, q(10, new byte[i], 0), 2), 4), 6), 8);
            System.arraycopy(this.k.a(e), 0, q2, 10, e);
            B(q2, i);
        }
    }

    public final boolean P() {
        Log.i("SIFLI", "send dfu resume");
        if (this.i == null) {
            Log.e("SIFLI", "resume ctrl file is empty");
            return false;
        }
        int i = this.j + 4;
        byte[] q = q(this.j, q(3, new byte[i], 0), 2);
        System.arraycopy(this.i, 0, q, 4, this.j);
        this.c.g(6);
        w(q, i);
        return true;
    }

    public final void Q() {
        Log.i("SIFLI", "send dfu start");
        if (this.k == null) {
            Log.e("SIFLI", "image file is empty");
            e(9);
            return;
        }
        t(6, 0);
        byte[] j = j(this.k.g(), j(this.k.e(), q(10, q(6, new byte[14], 0), 2), 4), 8);
        j[12] = (byte) this.n;
        j[13] = (byte) this.k.d();
        this.k.c(this.n);
        x();
        if (this.c.g() == 6 || this.c.g() == 7) {
            if (this.c.e() == 0) {
                int c2 = this.c.c() / this.n;
                Log.i("SIFLI", "resume send rsp pack already notified count: " + c2 + ", transport size: " + this.k.i());
                this.c.f(c2);
                this.k.b(this.c.c() + 1);
            } else {
                this.c.f(0);
                this.k.b(1);
            }
        }
        if (this.c.g() == 7 || this.c.g() == 6) {
            Log.d("SIFLI", "keep state in " + this.c.g());
        } else {
            this.c.g(3);
        }
        Log.i("SIFLI", "sendDfuStart, total size: " + this.k.e() + ", total count: " + this.k.g() + ", expect rsp count: " + this.k.b());
        w(j, 14);
    }

    public final void R() {
        Log.i("SIFLI", "sendDfuTransmissionEnd");
        byte[] q = q(1, q(12, new byte[5], 0), 2);
        q[3] = 0;
        this.c.g(4);
        t(12, 0);
        w(q, 5);
    }

    public void a(BluetoothGatt bluetoothGatt) {
        this.b = bluetoothGatt;
        this.c = new com.crrepa.k0.d();
        HandlerThread handlerThread = new HandlerThread("BleWrite");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper(), new d());
        this.n = 5;
    }

    public void a(c cVar) {
        this.p = cVar;
    }

    public boolean a(byte[] bArr) {
        int length = bArr.length;
        if (length <= 0) {
            return false;
        }
        this.i = bArr;
        this.j = length;
        return true;
    }

    public boolean a(byte[] bArr, int i) {
        if (i < 0 || i > 4 || bArr == null) {
            return false;
        }
        e eVar = new e(bArr, i);
        Iterator<e> it = this.m.iterator();
        while (it.hasNext()) {
            if (it.next().d() == i) {
                Log.e("SIFLI", "repeat image ID");
                return false;
            }
        }
        this.m.add(eVar);
        return true;
    }

    public boolean b(int i) {
        Iterator<e> it = this.m.iterator();
        while (it.hasNext()) {
            if (it.next().d() == i) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<e> c() {
        return this.m;
    }

    public final e d() {
        Iterator<e> it = this.m.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.d() == 0) {
                return next;
            }
        }
        return null;
    }

    public boolean d(int i) {
        if (i < 0 || i >= 20) {
            return false;
        }
        this.n = i;
        return true;
    }

    public void e() {
        this.b = this.f7743a.connectGatt(this.f, false, this.o);
        this.c = new com.crrepa.k0.d();
        HandlerThread handlerThread = new HandlerThread("BleWrite");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper(), new d());
        this.n = 5;
    }

    public final void e(int i) {
        this.p.a(i);
    }

    public final boolean h(int i, int i2) {
        String str;
        if (this.k.d() != i) {
            Iterator<e> it = this.m.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                e next = it.next();
                if (next.d() == i) {
                    this.k = next;
                    break;
                }
            }
        }
        if (this.k.d() != i) {
            str = "can not find img ID: " + i;
        } else if (this.k.g() >= i2) {
            this.k.g();
            this.c.b(i2);
            return true;
        } else {
            str = "img total count: " + this.k.g() + ", current count: " + i2;
        }
        Log.e("SIFLI", str);
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public final byte[] j(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(4).order(this.g).putInt(i).array(), 0, bArr, i2, 4);
        return bArr;
    }

    public final int l(byte[] bArr, int i) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, i, bArr2, 0, 2);
        return ByteBuffer.wrap(bArr2, 0, 2).order(this.g).getShort();
    }

    public final e m() {
        e eVar = this.k;
        if (eVar == null) {
            Log.e("SIFLI", "get hcpu image first");
            return null;
        }
        if (Build.VERSION.SDK_INT < 24) {
            int d2 = eVar.d();
            while (true) {
                d2++;
                if (d2 > 4) {
                    break;
                }
                Iterator<e> it = this.m.iterator();
                while (it.hasNext()) {
                    e next = it.next();
                    if (next.d() == d2) {
                        return next;
                    }
                }
            }
        } else {
            this.m.sort(new a(this));
            Iterator<e> it2 = this.m.iterator();
            while (it2.hasNext()) {
                e next2 = it2.next();
                if (this.k.d() < next2.d()) {
                    return next2;
                }
            }
        }
        return null;
    }

    public final void n(int i, int i2) {
        this.p.b(i, i2);
    }

    public void o() {
        BluetoothGatt bluetoothGatt = this.b;
        if (bluetoothGatt == null) {
            return;
        }
        bluetoothGatt.disconnect();
        this.b.close();
    }

    public final void o(byte[] bArr) {
        if (bArr[0] != 1) {
            return;
        }
        if (bArr[1] != 0) {
            Log.e("SIFLI", "more packets to receive, illegal flag");
            return;
        }
        l(bArr, 2);
        int l = l(bArr, 4);
        l(bArr, 6);
        if (l == 1) {
            Log.d("SIFLI", "receive init response");
            int l2 = l(bArr, 8);
            byte b = bArr[10];
            Log.d("SIFLI", "receive init response, result: " + l2 + ", boot: " + ((int) b));
            t(1, l2);
            this.c.a(b);
            if (l2 == 0) {
                s(l2);
            }
        } else if (l == 4) {
            int l3 = l(bArr, 8);
            t(4, l3);
            if (l3 != 0) {
                Log.i("SIFLI", "fail to resume, start force init");
                y(0, 0);
                return;
            }
            byte b2 = bArr[10];
            this.c.d(b2);
            byte b3 = bArr[11];
            this.c.e(b3);
            if (b3 == 1) {
                this.k = d();
                y(1, b2);
                return;
            }
            int l4 = l(bArr, 12);
            byte b4 = bArr[14];
            Log.i("SIFLI", "resume rsp, img id " + ((int) b4) + ", img num " + l4);
            y(h(b4, l4) ? 1 : 0, b2);
        } else if (l == 7) {
            int l5 = l(bArr, 8);
            Log.d("SIFLI", "SIFLI_DFU_IMAGE_SEND_START_RESPONSE " + l5);
            t(7, l5);
            if (l5 == 0) {
                if (this.c.g() == 3) {
                    O();
                } else if (this.c.g() == 6 || this.c.g() == 7) {
                    O();
                    this.c.g(3);
                }
            }
        } else if (l == 9) {
            int l6 = l(bArr, 8);
            Log.d("SIFLI", "send image end rsp" + l6);
            t(9, l6);
            if (F()) {
                if (l6 == 0) {
                    R();
                    return;
                }
                return;
            }
            Log.d("SIFLI", "more image will send");
            this.k = m();
            Q();
        } else if (l != 11) {
            Log.w("SIFLI", "unknown packet");
        } else {
            int l7 = l(bArr, 8);
            if (l7 != 0) {
                Log.d("SIFLI", "send response " + l7);
            }
            t(11, l7);
            this.c.a();
            Log.d("SIFLI", "sendRspCount: " + this.c.f() + "ExpectRspCount: " + this.k.b());
            n(this.k.d(), (this.c.f() * 100) / this.k.b());
            if (this.c.f() == this.k.b()) {
                L();
            }
        }
    }

    public boolean p() {
        return J();
    }

    public boolean q() {
        return P();
    }

    public final byte[] q(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(2).order(this.g).putShort((short) i).array(), 0, bArr, i2, 2);
        return bArr;
    }

    public boolean r() {
        return N();
    }

    public final void s(int i) {
        int i2 = 0;
        byte[] q = q(1, q(2, new byte[5], 0), 2);
        if (i == 0) {
            e(8);
            i2 = 1;
        }
        q[4] = (byte) i2;
        Log.i("SIFLI", "send dfu init complete, start: " + i2);
        this.c.g(2);
        w(q, 5);
        if (i2 == 1 && this.c.b() == 0) {
            try {
                Thread.sleep(4000L);
                this.k = d();
                Q();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public final void t(int i, int i2) {
        this.p.a(i, i2);
    }

    public void v(byte[] bArr) {
        Message message = new Message();
        message.what = 0;
        Bundle bundle = new Bundle();
        bundle.putByteArray("sifli_ble_data", bArr);
        message.setData(bundle);
        this.e.sendMessage(message);
    }

    public final void w(byte[] bArr, int i) {
        int i2 = i + 4;
        if (i2 <= 244) {
            byte[] bArr2 = new byte[i2];
            bArr2[0] = 1;
            bArr2[1] = 0;
            byte[] q = q(i, bArr2, 2);
            System.arraycopy(bArr, 0, q, 4, i);
            v(q);
            return;
        }
        byte[] bArr3 = new byte[244];
        bArr3[0] = 1;
        bArr3[1] = 1;
        byte[] q2 = q(i, bArr3, 2);
        int i3 = 240;
        System.arraycopy(bArr, 0, q2, 4, 240);
        v(q2);
        while (i3 < i) {
            int i4 = i - i3;
            if (i4 > 242) {
                byte[] bArr4 = new byte[244];
                bArr4[0] = 1;
                bArr4[1] = 2;
                System.arraycopy(bArr, i3, bArr4, 2, 242);
                i3 += 242;
                v(bArr4);
            } else {
                byte[] bArr5 = new byte[(i4 + 4) - 2];
                bArr5[0] = 1;
                bArr5[1] = 3;
                System.arraycopy(bArr, i3, bArr5, 2, i4);
                v(bArr5);
                i3 = i;
            }
        }
    }

    public final void x() {
        this.c.f(0);
        this.k.b(1);
    }

    public final void y(int i, int i2) {
        Log.i("SIFLI", "send dfu resume completed, start " + i + ", reboot " + i2);
        byte[] q = q(1, q(5, new byte[5], 0), 2);
        q[4] = (byte) i;
        if (i != 1) {
            this.c.g(0);
            w(q, 5);
            return;
        }
        this.c.g(6);
        t(5, i);
        this.k.d(this.c.c() * 548);
        if (i2 == 0) {
            Q();
        } else {
            e(8);
            this.c.g(7);
        }
        w(q, 5);
    }
}
