package com.ido.ble.bluetooth.d;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.text.TextUtils;
import android.util.Log;
import com.ido.ble.bluetooth.d.d;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes11.dex */
class g {
    public static g j;

    /* renamed from: a  reason: collision with root package name */
    private BluetoothSocket f12107a;
    private f b;
    private DataInputStream e;
    private final Lock h;
    private final Condition i;
    private boolean c = true;
    private final Executor d = Executors.newCachedThreadPool();
    private boolean f = true;
    private final ConcurrentLinkedQueue<byte[]> g = new ConcurrentLinkedQueue<>();

    /* loaded from: classes11.dex */
    public class a implements d.b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f12108a;

        public a(f fVar) {
            this.f12108a = fVar;
        }

        @Override // com.ido.ble.bluetooth.d.d.b
        public void a(String str) {
            if (TextUtils.isEmpty(str)) {
                g.this.d();
            } else {
                g.this.a(str, this.f12108a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements e {

        /* loaded from: classes11.dex */
        public class a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ BluetoothDevice f12110a;

            public a(BluetoothDevice bluetoothDevice) {
                this.f12110a = bluetoothDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.a(this.f12110a);
            }
        }

        public b() {
        }

        @Override // com.ido.ble.bluetooth.d.e
        public void a() {
            g.this.d();
        }

        @Override // com.ido.ble.bluetooth.d.e
        public void a(BluetoothDevice bluetoothDevice) {
            g.this.d.execute(new a(bluetoothDevice));
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothSocket f12111a;

        public c(BluetoothSocket bluetoothSocket) {
            this.f12111a = bluetoothSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.a(this.f12111a);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothSocket f12112a;

        public d(BluetoothSocket bluetoothSocket) {
            this.f12112a = bluetoothSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.b(this.f12112a);
        }
    }

    private g() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.h = reentrantLock;
        this.i = reentrantLock.newCondition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] connect " + bluetoothDevice.getName());
        try {
            BluetoothSocket createRfcommSocketToServiceRecord = bluetoothDevice.createRfcommSocketToServiceRecord(com.ido.ble.bluetooth.f.f.f12117a);
            if (!createRfcommSocketToServiceRecord.isConnected()) {
                createRfcommSocketToServiceRecord.connect();
            }
            c(createRfcommSocketToServiceRecord);
        } catch (IOException e) {
            LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] connect. " + e.getMessage());
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothSocket bluetoothSocket) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] startReadThread. max receiver size =" + bluetoothSocket.getMaxReceivePacketSize());
        this.c = true;
        while (this.c) {
            try {
                this.e = new DataInputStream(bluetoothSocket.getInputStream());
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = this.e.read(bArr);
                    if (read != -1) {
                        a(bArr, read);
                    }
                }
            } catch (IOException e) {
                LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] startReadThread. " + e.getMessage());
                b();
            }
        }
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] exit read thread.");
    }

    private void a(String str) {
        com.ido.ble.bluetooth.d.b.b().a(str, new b());
    }

    private void a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] receive <= " + com.ido.ble.common.c.b(bArr2));
        u.b(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothSocket bluetoothSocket) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] startWriteThread. max send size =" + bluetoothSocket.getMaxTransmitPacketSize());
        this.f = true;
        while (true) {
            this.h.lock();
            try {
                try {
                    if (this.g.isEmpty()) {
                        this.i.await();
                    }
                } catch (InterruptedException e) {
                    Log.e(com.ido.ble.logs.a.q, e.getMessage(), e);
                }
                if (!this.f) {
                    this.h.unlock();
                    LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] exit write thread. ");
                    return;
                }
                b(this.g.poll());
                this.h.unlock();
            } catch (Throwable th) {
                this.h.unlock();
                throw th;
            }
        }
    }

    private boolean b(byte[] bArr) {
        String str;
        if (bArr == null) {
            return false;
        }
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] send[" + bArr.length + "] => " + com.ido.ble.common.c.b(bArr));
        if (a()) {
            try {
                this.f12107a.getOutputStream().write(bArr);
                this.f12107a.getOutputStream().flush();
                u.B();
                return true;
            } catch (IOException e) {
                str = "[SPPConnectManager] write() " + e.getMessage();
            }
        } else {
            str = "[SPPConnectManager] write(). not connected.";
        }
        LogTool.b(com.ido.ble.logs.a.q, str);
        return false;
    }

    private void c(BluetoothSocket bluetoothSocket) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] success.");
        this.f12107a = bluetoothSocket;
        f fVar = this.b;
        if (fVar != null) {
            fVar.onSuccess();
        }
        this.d.execute(new c(bluetoothSocket));
        this.d.execute(new d(bluetoothSocket));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] failed.");
        f fVar = this.b;
        if (fVar != null) {
            fVar.onFailed();
        }
        f();
    }

    public static g e() {
        if (j == null) {
            j = new g();
        }
        return j;
    }

    private void f() {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] release. ");
        DataInputStream dataInputStream = this.e;
        if (dataInputStream != null) {
            try {
                dataInputStream.close();
            } catch (IOException unused) {
            }
        }
        this.g.clear();
        this.f = false;
        this.e = null;
        this.c = false;
        this.f12107a = null;
        this.b = null;
        this.h.lock();
        this.i.signal();
        this.h.unlock();
    }

    public void a(f fVar) {
        com.ido.ble.bluetooth.d.d.b().a(new a(fVar));
    }

    public void a(String str, f fVar) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] connect " + str);
        this.c = false;
        this.b = fVar;
        if (fVar != null) {
            fVar.onStart();
        }
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            a(str);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] connect, bluetooth is disEnable ");
        d();
    }

    public void a(byte[] bArr) {
        this.h.lock();
        this.g.add(bArr);
        this.i.signal();
        this.h.unlock();
    }

    public boolean a() {
        BluetoothSocket bluetoothSocket = this.f12107a;
        return bluetoothSocket != null && bluetoothSocket.isConnected();
    }

    public void b() {
        LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] onBreak. ");
        f fVar = this.b;
        if (fVar != null) {
            fVar.a();
        }
        f();
    }

    public void c() {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] toDisconnect. ");
        BluetoothSocket bluetoothSocket = this.f12107a;
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] disconnect. " + e.getMessage());
            }
        }
    }
}
