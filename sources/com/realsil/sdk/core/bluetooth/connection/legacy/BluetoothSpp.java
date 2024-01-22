package com.realsil.sdk.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import com.coveiot.android.leonardo.utils.MusicConstants;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public final class BluetoothSpp {
    public static final int ROLE_CLIENT = 1;
    public static final int ROLE_SERVER = 2;
    public static final int STATE_CONNECTED = 512;
    public static final int STATE_CONNECTING = 256;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 768;
    public static final int STATE_LISTEN = 257;
    public static final int STATE_NONE = 0;

    /* renamed from: a  reason: collision with root package name */
    public static final UUID f13564a = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public boolean b;
    public boolean c;
    public BluetoothAdapter d;
    public int e;
    public BluetoothDevice f;
    public boolean g;
    public com.realsil.sdk.core.a.a h;
    public int i;
    public boolean initialized;
    public UUID j;
    public b k;
    public c l;
    public a m;
    public BluetoothSppCallback mCallback;
    public int n;

    /* loaded from: classes12.dex */
    public class c extends Thread {

        /* renamed from: a  reason: collision with root package name */
        public final BluetoothSocket f13567a;
        public BufferedInputStream b;
        public BufferedOutputStream c;

        public c(BluetoothSocket bluetoothSocket) {
            BufferedInputStream bufferedInputStream;
            BufferedOutputStream bufferedOutputStream = null;
            this.b = null;
            this.c = null;
            ZLogger.d("create ConnectedThread");
            this.f13567a = bluetoothSocket;
            try {
                bufferedInputStream = new BufferedInputStream(bluetoothSocket.getInputStream());
            } catch (IOException e) {
                e = e;
                bufferedInputStream = null;
            }
            try {
                bufferedOutputStream = new BufferedOutputStream(bluetoothSocket.getOutputStream());
            } catch (IOException e2) {
                e = e2;
                ZLogger.w("temp sockets not created: " + e);
                this.b = bufferedInputStream;
                this.c = bufferedOutputStream;
            }
            this.b = bufferedInputStream;
            this.c = bufferedOutputStream;
        }

        public void a() {
            BluetoothSocket bluetoothSocket = this.f13567a;
            if (bluetoothSocket == null) {
                return;
            }
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                ZLogger.w("close socket failed: " + e);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            byte[] bArr = new byte[1024];
            BluetoothSpp bluetoothSpp = BluetoothSpp.this;
            bluetoothSpp.a(bluetoothSpp.f, 512);
            while (!Thread.currentThread().isInterrupted() && BluetoothSpp.this.e == 512) {
                try {
                    int read = this.b.read(bArr);
                    if (read > 0) {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        if (BluetoothSpp.this.b) {
                            ZLogger.d(String.format(Locale.US, ">> (%d) %s", Integer.valueOf(read), DataConverter.bytes2Hex(bArr2)));
                        }
                        BluetoothSppCallback bluetoothSppCallback = BluetoothSpp.this.mCallback;
                        if (bluetoothSppCallback != null) {
                            bluetoothSppCallback.onDataReceive(bArr2);
                        }
                    }
                } catch (IOException e) {
                    ZLogger.w(e.toString());
                    BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                    ZLogger.v(bluetoothSpp2.b, "connectionLost");
                    bluetoothSpp2.a(bluetoothSpp2.f, 0);
                    bluetoothSpp2.f = null;
                    bluetoothSpp2.start();
                }
            }
            if (BluetoothSpp.this.e == 768) {
                a();
                BluetoothSpp bluetoothSpp3 = BluetoothSpp.this;
                ZLogger.v(bluetoothSpp3.b, "connectionLost");
                bluetoothSpp3.a(bluetoothSpp3.f, 0);
                bluetoothSpp3.f = null;
                bluetoothSpp3.start();
            }
        }
    }

    public BluetoothSpp(BluetoothSppCallback bluetoothSppCallback) {
        this(1, f13564a, bluetoothSppCallback);
    }

    public final void b() {
        ZLogger.v(this.b, "initialize...");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.d = defaultAdapter;
        if (defaultAdapter == null) {
            ZLogger.d("bluetoothAdapter not initialized ");
            this.initialized = false;
        } else if (!defaultAdapter.isEnabled()) {
            ZLogger.d("bluetooth is disabled");
            this.initialized = false;
        } else {
            this.initialized = true;
        }
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice) {
        return connect(new com.realsil.sdk.core.a.a(bluetoothDevice, null, com.realsil.sdk.core.a.a.f13544a, false));
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) {
        ZLogger.v(this.b, "spp connected");
        this.f = bluetoothDevice;
        a();
        a aVar = this.m;
        if (aVar != null) {
            aVar.a();
            this.m = null;
        }
        c cVar = new c(bluetoothSocket);
        this.l = cVar;
        cVar.start();
    }

    public synchronized void destroy() {
        this.mCallback = null;
        stop();
    }

    public int getConnectionState() {
        return this.e;
    }

    public BluetoothDevice getDevice() {
        return this.f;
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        BluetoothDevice bluetoothDevice2 = this.f;
        return bluetoothDevice2 != null && bluetoothDevice2.equals(bluetoothDevice) && this.e == 512;
    }

    public synchronized void start(boolean z) {
        boolean z2 = this.b;
        ZLogger.v(z2, "start secure: " + z);
        b bVar = this.k;
        if (bVar != null) {
            bVar.a();
            this.k.interrupt();
            this.k = null;
        }
        c cVar = this.l;
        if (cVar != null) {
            cVar.a();
            this.l.interrupt();
            this.l = null;
        }
        if ((this.i & 2) == 2 && this.m == null) {
            a aVar = new a(z);
            this.m = aVar;
            aVar.start();
        }
    }

    public synchronized void stop() {
        ZLogger.v(this.b, MusicConstants.CMDSTOP);
        if (this.e == 512) {
            a(this.f, 768);
        }
        this.f = null;
        b bVar = this.k;
        if (bVar != null) {
            bVar.a();
            this.k.interrupt();
            this.k = null;
        }
        c cVar = this.l;
        if (cVar != null) {
            cVar.a();
            this.l.interrupt();
            this.l = null;
        }
        a aVar = this.m;
        if (aVar != null) {
            aVar.a();
            this.m.interrupt();
            this.m = null;
        }
    }

    public boolean write(byte[] bArr) {
        return write(bArr, true);
    }

    public BluetoothSpp(UUID uuid, BluetoothSppCallback bluetoothSppCallback) {
        this(1, uuid, bluetoothSppCallback);
    }

    public final void a() {
        b bVar = this.k;
        if (bVar != null) {
            bVar.a();
            this.k.interrupt();
            this.k = null;
        }
        c cVar = this.l;
        if (cVar != null) {
            cVar.a();
            this.l.interrupt();
            this.l = null;
        }
    }

    public boolean write(byte[] bArr, boolean z) {
        synchronized (this) {
            if (this.e != 512) {
                ZLogger.d(this.b, "not connected");
                return false;
            }
            c cVar = this.l;
            if (cVar == null) {
                ZLogger.d("ConnectedThread not created");
                return false;
            } else if (cVar.c == null) {
                return false;
            } else {
                try {
                    if (BluetoothSpp.this.b) {
                        ZLogger.d(String.format(Locale.US, "<< (%d) %s", Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
                    }
                    cVar.c.write(bArr);
                    if (z) {
                        cVar.c.flush();
                    }
                    return true;
                } catch (IOException e) {
                    ZLogger.w("Exception during write： " + e);
                    return false;
                }
            }
        }
    }

    public BluetoothSpp(int i, UUID uuid, BluetoothSppCallback bluetoothSppCallback) {
        this.b = false;
        this.c = false;
        this.e = 0;
        this.f = null;
        this.g = false;
        this.i = 1;
        this.j = f13564a;
        this.n = -1;
        this.i = i;
        this.j = uuid;
        this.mCallback = bluetoothSppCallback;
        this.e = 0;
        this.b = RtkCore.DEBUG;
        this.c = RtkCore.VDBG;
        b();
    }

    public boolean isConnected() {
        return this.f != null && this.e == 512;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        return connect(new com.realsil.sdk.core.a.a(bluetoothDevice, bluetoothSocket, com.realsil.sdk.core.a.a.f13544a, false));
    }

    /* loaded from: classes12.dex */
    public class a extends Thread {

        /* renamed from: a  reason: collision with root package name */
        public final BluetoothServerSocket f13565a;

        public a(boolean z) {
            this.f13565a = a(z);
            BluetoothSpp.this.a(BluetoothSpp.this.f, 257);
        }

        public final BluetoothServerSocket a(boolean z) {
            BluetoothServerSocket listenUsingInsecureRfcommWithServiceRecord;
            try {
                if (z) {
                    BluetoothSpp bluetoothSpp = BluetoothSpp.this;
                    listenUsingInsecureRfcommWithServiceRecord = bluetoothSpp.d.listenUsingRfcommWithServiceRecord("RtkSppSecure", bluetoothSpp.j);
                } else {
                    BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                    listenUsingInsecureRfcommWithServiceRecord = bluetoothSpp2.d.listenUsingInsecureRfcommWithServiceRecord("RtkSppInsecure", bluetoothSpp2.j);
                }
                return listenUsingInsecureRfcommWithServiceRecord;
            } catch (IOException e) {
                ZLogger.d("createServerSocket failed: " + e.toString());
                return null;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.v(BluetoothSpp.this.b, "BEGIN mAcceptThread");
            setName("AcceptThread:BluetoothSpp");
            while (BluetoothSpp.this.e != 512) {
                try {
                    BluetoothSocket accept = this.f13565a.accept();
                    if (accept != null) {
                        synchronized (BluetoothSpp.this) {
                            BluetoothSpp bluetoothSpp = BluetoothSpp.this;
                            int i = bluetoothSpp.e;
                            if (i == 0 || i == 512) {
                                try {
                                    accept.close();
                                } catch (IOException e) {
                                    ZLogger.w("Could not close unwanted socket： " + e);
                                }
                            } else if (i == 256 || i == 257) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    bluetoothSpp.n = accept.getConnectionType();
                                }
                                BluetoothSpp.this.connected(accept, accept.getRemoteDevice());
                            }
                        }
                    }
                } catch (IOException e2) {
                    ZLogger.w("accept() failed" + e2);
                    BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                    bluetoothSpp2.a(bluetoothSpp2.f, 0);
                }
            }
            ZLogger.d(BluetoothSpp.this.b, "END AcceptThread");
        }

        public void a() {
            try {
                if (this.f13565a != null) {
                    ZLogger.v(BluetoothSpp.this.b, "cancel AcceptThread");
                    this.f13565a.close();
                }
            } catch (IOException e) {
                ZLogger.w("close() of server failed： " + e);
            }
        }
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid) {
        UUID uuid2 = com.realsil.sdk.core.a.a.f13544a;
        if (uuid == null) {
            uuid = uuid2;
        }
        return connect(new com.realsil.sdk.core.a.a(bluetoothDevice, bluetoothSocket, uuid, false));
    }

    public final boolean a(com.realsil.sdk.core.a.a aVar) {
        this.h = aVar;
        this.f = aVar.b;
        this.j = aVar.d;
        this.g = true;
        if (!this.initialized) {
            b();
        }
        BluetoothSocket bluetoothSocket = aVar.c;
        if (bluetoothSocket != null) {
            connected(bluetoothSocket, aVar.b);
        } else {
            a();
            b bVar = new b(this.f, true);
            this.k = bVar;
            bVar.start();
        }
        return true;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, UUID uuid) {
        if (bluetoothDevice == null) {
            return false;
        }
        UUID uuid2 = com.realsil.sdk.core.a.a.f13544a;
        if (uuid == null) {
            uuid = uuid2;
        }
        return connect(new com.realsil.sdk.core.a.a(bluetoothDevice, null, uuid, false));
    }

    public synchronized void start() {
        start(true);
    }

    /* loaded from: classes12.dex */
    public class b extends Thread {

        /* renamed from: a  reason: collision with root package name */
        public BluetoothSocket f13566a;
        public final BluetoothDevice b;

        public b(BluetoothDevice bluetoothDevice, boolean z) {
            this.b = bluetoothDevice;
            this.f13566a = a(bluetoothDevice, z);
        }

        public final BluetoothSocket a(BluetoothDevice bluetoothDevice, boolean z) {
            BluetoothSocket bluetoothSocket;
            boolean z2 = BluetoothSpp.this.b;
            ZLogger.v(z2, "mSecureUuid=" + BluetoothSpp.this.j);
            try {
                if (z) {
                    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(BluetoothSpp.this.j);
                } else {
                    bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(BluetoothSpp.this.j);
                }
            } catch (IOException e) {
                ZLogger.w("createBluetoothSocket failed: " + e.toString());
                bluetoothSocket = null;
            }
            if (bluetoothSocket != null && Build.VERSION.SDK_INT >= 23) {
                BluetoothSpp.this.n = bluetoothSocket.getConnectionType();
            }
            return bluetoothSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BluetoothSpp bluetoothSpp;
            setName("ConnectThread:BluetoothSpp");
            if (BluetoothSpp.this.c) {
                ZLogger.v("SocketConnectionType: " + BluetoothSpp.this.n);
            }
            BluetoothAdapter bluetoothAdapter = BluetoothSpp.this.d;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.cancelDiscovery();
            }
            BluetoothSocket bluetoothSocket = this.f13566a;
            if (bluetoothSocket == null) {
                ZLogger.w("create BluetoothSocket fail");
                BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                bluetoothSpp2.a(bluetoothSpp2.f, 0);
                return;
            }
            try {
                if (bluetoothSocket.isConnected()) {
                    ZLogger.d(BluetoothSpp.this.b, "socket already connected");
                } else {
                    BluetoothSpp bluetoothSpp3 = BluetoothSpp.this;
                    bluetoothSpp3.a(bluetoothSpp3.f, 256);
                    ZLogger.v(BluetoothSpp.this.c, "connect socket ...");
                    this.f13566a.connect();
                }
                synchronized (BluetoothSpp.this) {
                    bluetoothSpp = BluetoothSpp.this;
                    bluetoothSpp.k = null;
                }
                bluetoothSpp.connected(this.f13566a, this.b);
            } catch (IOException e) {
                ZLogger.w(e.toString());
                try {
                    this.f13566a.close();
                } catch (IOException e2) {
                    ZLogger.w("unable to close socket during connection failure: " + e2);
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                if ("Connect refused".equals(e.getMessage())) {
                    if (this.b.getBondState() == 12) {
                        this.f13566a = a(this.b, false);
                    }
                    BluetoothSocket bluetoothSocket2 = this.f13566a;
                    if (bluetoothSocket2 == null) {
                        ZLogger.d("create Insecure BluetoothSocket fail");
                        BluetoothSpp bluetoothSpp4 = BluetoothSpp.this;
                        bluetoothSpp4.a(bluetoothSpp4.f, 0);
                        return;
                    }
                    try {
                        if (bluetoothSocket2.isConnected()) {
                            ZLogger.d("socket already connected");
                        } else {
                            BluetoothSpp bluetoothSpp5 = BluetoothSpp.this;
                            bluetoothSpp5.a(bluetoothSpp5.f, 256);
                            ZLogger.v(BluetoothSpp.this.c, "connect socket ...");
                            this.f13566a.connect();
                        }
                        return;
                    } catch (IOException e4) {
                        ZLogger.d(e4.toString());
                        try {
                            this.f13566a.close();
                        } catch (IOException e5) {
                            ZLogger.w("unable to close socket during connection failure: " + e5);
                        }
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e6) {
                            e6.printStackTrace();
                        }
                        BluetoothSpp.a(BluetoothSpp.this);
                        return;
                    }
                }
                BluetoothSpp.a(BluetoothSpp.this);
            }
        }

        public void a() {
            try {
                BluetoothSocket bluetoothSocket = this.f13566a;
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
            } catch (IOException e) {
                ZLogger.w("close socket failed: " + e);
            }
        }
    }

    public synchronized boolean connect(com.realsil.sdk.core.a.a aVar) {
        if (aVar == null) {
            ZLogger.v("connParameters can not be null or empty");
            return false;
        }
        BluetoothDevice bluetoothDevice = aVar.b;
        if (bluetoothDevice == null) {
            ZLogger.v("device can not be null or empty");
            return false;
        }
        BluetoothDevice bluetoothDevice2 = this.f;
        if (bluetoothDevice2 != null) {
            if (bluetoothDevice2.equals(bluetoothDevice)) {
                int i = this.e;
                if (i == 512) {
                    ZLogger.v(this.b, "device already connected");
                    a(this.f, 512);
                    return true;
                } else if (i == 256) {
                    ZLogger.v(this.b, "device is already at connecting state");
                    a(this.f, 256);
                    return true;
                }
            } else {
                int i2 = this.e;
                if (i2 == 512) {
                    ZLogger.v(this.b, "device already connected");
                    a();
                    return false;
                } else if (i2 == 256) {
                    ZLogger.v(this.b, "device is already at connecting state");
                    a();
                    return false;
                }
            }
        }
        a(aVar);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0069, code lost:
        if (r0 == 2) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp r6) {
        /*
            boolean r0 = r6.g
            r1 = 0
            if (r0 == 0) goto L7c
            r6.g = r1
            android.bluetooth.BluetoothDevice r0 = r6.f
            r2 = 1
            if (r0 != 0) goto L15
            boolean r0 = r6.c
            java.lang.String r2 = "mDevice == null"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r2)
        L13:
            r2 = r1
            goto L6b
        L15:
            int r3 = r6.e
            r4 = 256(0x100, float:3.59E-43)
            if (r3 == r4) goto L2f
            boolean r0 = r6.c
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r1] = r3
            java.lang.String r3 = "mConnectionState=0x%04X"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            com.realsil.sdk.core.logger.ZLogger.v(r0, r2)
            goto L13
        L2f:
            int r0 = r0.getBondState()
            boolean r3 = r6.c
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r4[r1] = r5
            java.lang.String r5 = "bondState=0x%02X"
            java.lang.String r4 = java.lang.String.format(r5, r4)
            com.realsil.sdk.core.logger.ZLogger.v(r3, r4)
            r3 = 12
            if (r0 == r3) goto L4b
            goto L13
        L4b:
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r3 = r6.f
            int r0 = r0.getConnectionState(r2, r3)
            boolean r3 = r6.c
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r4[r1] = r5
            java.lang.String r5 = "hfpState=0x%02X"
            java.lang.String r4 = java.lang.String.format(r5, r4)
            com.realsil.sdk.core.logger.ZLogger.v(r3, r4)
            r3 = 2
            if (r0 != r3) goto L13
        L6b:
            if (r2 == 0) goto L7c
            r6.g = r1
            boolean r0 = r6.c
            java.lang.String r1 = "processAbnormalDisconnection .."
            com.realsil.sdk.core.logger.ZLogger.v(r0, r1)
            com.realsil.sdk.core.a.a r0 = r6.h
            r6.a(r0)
            goto L8e
        L7c:
            boolean r0 = r6.b
            java.lang.String r2 = "connectionFailed"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r2)
            android.bluetooth.BluetoothDevice r0 = r6.f
            r6.a(r0, r1)
            r0 = 0
            r6.f = r0
            r6.start()
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp.a(com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp):void");
    }

    public final synchronized void a(BluetoothDevice bluetoothDevice, int i) {
        int i2 = this.e;
        if (i != i2) {
            ZLogger.v(String.format(Locale.US, ">> ConnectionState=0x%04X > 0x%04X", Integer.valueOf(i2), Integer.valueOf(i)));
        }
        this.e = i;
        BluetoothSppCallback bluetoothSppCallback = this.mCallback;
        if (bluetoothSppCallback != null) {
            bluetoothSppCallback.onConnectionStateChanged(bluetoothDevice, true, i);
        } else {
            ZLogger.v(this.c, "no callback registered");
        }
    }
}
