package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.q.b;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.e.a;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.Method;
/* loaded from: classes11.dex */
abstract class b {
    private static final long i = 3000;
    private static final long j = 35000;
    private static final long k = 10000;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    private BluetoothGatt d;
    private com.ido.ble.bluetooth.connect.j e;
    private BLEDevice g;

    /* renamed from: a  reason: collision with root package name */
    private int f12047a = 1;
    private boolean b = false;
    private boolean c = false;
    private Handler f = new Handler(Looper.getMainLooper());
    private boolean h = true;

    /* loaded from: classes11.dex */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothGatt f12048a;

        public a(BluetoothGatt bluetoothGatt) {
            this.f12048a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.ido.ble.bluetooth.f.a.a(this.f12048a, true)) {
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyHealth reEnable ok");
                return;
            }
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyHealth reEnable failed");
            b.this.A();
        }
    }

    /* renamed from: com.ido.ble.bluetooth.connect.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class RunnableC0570b implements Runnable {
        public RunnableC0570b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(0L);
            b.this.x();
            b.this.m();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements b.InterfaceC0575b {

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.x();
                b.this.g();
            }
        }

        public c() {
        }

        @Override // com.ido.ble.bluetooth.connect.q.b.InterfaceC0575b
        public void onFailed() {
            if (b.this.f12047a == 1) {
                return;
            }
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] encrypted failed.");
            b.this.f.post(new a());
        }

        @Override // com.ido.ble.bluetooth.connect.q.b.InterfaceC0575b
        public void onSuccess() {
            if (b.this.f12047a == 1) {
                return;
            }
            b.this.b = true;
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] connect success");
            com.ido.ble.i.a.a.T();
            b bVar = b.this;
            bVar.b(bVar.g);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a((long) b.i);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.v();
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.u();
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.z();
        }
    }

    /* loaded from: classes11.dex */
    public class h implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothGatt f12056a;

        public h(BluetoothGatt bluetoothGatt) {
            this.f12056a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f12056a.discoverServices()) {
                return;
            }
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] discover services failed again");
            b.this.e.c();
            b.this.z();
        }
    }

    /* loaded from: classes11.dex */
    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(0L);
            b.this.x();
            b.this.l();
        }
    }

    /* loaded from: classes11.dex */
    public class j implements a.d {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothGatt f12058a;

        public j(BluetoothGatt bluetoothGatt) {
            this.f12058a = bluetoothGatt;
        }

        @Override // com.ido.ble.e.a.d
        public void a(String str) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] create bond finished.");
            b.this.c(this.f12058a);
        }
    }

    /* loaded from: classes11.dex */
    public class k implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothGatt f12059a;

        public k(BluetoothGatt bluetoothGatt) {
            this.f12059a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.c(this.f12059a);
        }
    }

    /* loaded from: classes11.dex */
    public class l implements Runnable {
        public l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.x();
            b bVar = b.this;
            bVar.c(bVar.g);
        }
    }

    /* loaded from: classes11.dex */
    public class m implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothGatt f12061a;

        public m(BluetoothGatt bluetoothGatt) {
            this.f12061a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.ido.ble.bluetooth.f.a.b(this.f12061a, true)) {
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyNormal reEnable ok");
                return;
            }
            b.this.B();
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyNormal reEnable failed");
        }
    }

    /* loaded from: classes11.dex */
    public class n implements Runnable {
        public n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(0L);
            b.this.x();
            b.this.n();
        }
    }

    /* loaded from: classes11.dex */
    public class o extends BluetoothGattCallback {

        /* loaded from: classes11.dex */
        public class a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ BluetoothGatt f12064a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;

            public a(BluetoothGatt bluetoothGatt, int i, int i2) {
                this.f12064a = bluetoothGatt;
                this.b = i;
                this.c = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.a(this.f12064a, this.b, this.c);
            }
        }

        public o() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            b.this.a(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            b.this.a(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            b.this.e.b();
            b.this.e.a();
            if (b.this.h) {
                com.ido.ble.common.e.a(new a(bluetoothGatt, i, i2));
            } else {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:onConnectionStateChange()] onConnectionStateChange is called, but mIsNeedHandGattCallback is false");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (!com.ido.ble.bluetooth.f.f.c.equals(bluetoothGattDescriptor.getUuid())) {
                    return;
                }
                if (bluetoothGattDescriptor.getValue()[0] == 1) {
                    if (com.ido.ble.bluetooth.f.f.j.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        b.this.b(bluetoothGatt);
                        return;
                    } else if (com.ido.ble.bluetooth.f.f.l.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        b.this.y();
                        return;
                    } else {
                        return;
                    }
                }
            }
            b.this.n();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            b.this.e.c();
            if (i == 0) {
                LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:servicesDiscovered()] discoverServices ok!");
                b.this.a(bluetoothGatt);
                return;
            }
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:servicesDiscovered()] discoverServices failed");
            b.this.z();
        }
    }

    public b() {
        D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        com.ido.ble.common.e.a(new RunnableC0570b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        com.ido.ble.common.e.a(new n());
    }

    private void C() {
        com.ido.ble.common.e.a(new l());
    }

    private void D() {
        this.e = new com.ido.ble.bluetooth.connect.o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j2) {
        w();
        if (this.d == null) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] disconnect failed, mBluetoothGatt is null.");
            v();
            return;
        }
        if (j2 != 0) {
            this.h = true;
            this.e.a(new e(), j2);
        } else {
            this.h = false;
        }
        this.d.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothGatt bluetoothGatt) {
        if (d(bluetoothGatt)) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] device in dfu mode");
            C();
            return;
        }
        if (e(bluetoothGatt)) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] device  dw02 in dfu mode");
            c(this.g);
        }
        if (!com.ido.ble.e.a.a(bluetoothGatt)) {
            this.f.postDelayed(new k(bluetoothGatt), 100L);
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] is need create bond");
        com.ido.ble.e.a.c().a(bluetoothGatt.getDevice(), new j(bluetoothGatt));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothGatt bluetoothGatt, int i2, int i3) {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[BaseConnect:connectionStateChange()] status = " + i2 + ",newState = " + i3);
        if (i2 == 0) {
            if (i3 == 2) {
                b(bluetoothGatt, i2, i3);
                return;
            }
        } else if (this.f12047a != 3) {
            d(i2, i3);
            return;
        }
        c(i2, i3);
    }

    private void a(String str) {
        BluetoothDevice a2 = com.ido.ble.bluetooth.f.e.a(str);
        if (a2 == null) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] printPhoneEnvInfo, not paired!");
        } else {
            boolean a3 = com.ido.ble.bluetooth.f.e.a(a2);
            String str2 = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.d(str2, "[BaseConnect] printPhoneEnvInfo, has paired, isConnectedByPhone=" + a3);
            if (CustomConfig.getConfig().isNeedRemoveBondBeforeConnect() && !com.ido.ble.bluetooth.f.e.b()) {
                boolean b = com.ido.ble.bluetooth.f.e.b(a2);
                String str3 = com.ido.ble.bluetooth.f.b.f12116a;
                LogTool.d(str3, "[BaseConnect] printPhoneEnvInfo, remove bond status is =" + b);
            }
        }
        String b2 = com.ido.ble.common.m.b();
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        String str4 = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.b(str4, "[BaseConnect] printPhoneEnvInfo, " + b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothGatt bluetoothGatt) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] start to enablePeerDeviceNotifyHealth...");
        if (com.ido.ble.bluetooth.f.a.a(bluetoothGatt, true)) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyHealth ok");
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyHealth failed, retry...");
        this.f.postDelayed(new a(bluetoothGatt), 50L);
    }

    private void b(BluetoothGatt bluetoothGatt, int i2, int i3) {
        if (this.f12047a == 3) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] in connected state, not do next steps!");
            return;
        }
        this.f12047a = 3;
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] gatt connected.");
        g(bluetoothGatt);
    }

    private void c(int i2, int i3) {
        if (this.f12047a != 3) {
            x();
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] connect failed");
            b(i2, i3);
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] connect break");
        x();
        a(i2, i3);
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] bluetooth is open");
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] user close bluetooth");
        com.ido.ble.callback.e.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothGatt bluetoothGatt) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] start to enablePeerDeviceNotifyNormal...");
        if (com.ido.ble.bluetooth.f.a.b(bluetoothGatt, true)) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyNormal ok");
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] enablePeerDeviceNotifyNormal failed, retry...");
        this.f.postDelayed(new m(bluetoothGatt), 50L);
    }

    private void d(int i2, int i3) {
        x();
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] connect failed");
        b(i2, i3);
    }

    private boolean d(BluetoothGatt bluetoothGatt) {
        return (bluetoothGatt.getService(com.ido.ble.bluetooth.f.f.d) == null && bluetoothGatt.getService(com.ido.ble.bluetooth.f.f.e) == null) ? false : true;
    }

    private boolean e(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.getService(com.ido.ble.bluetooth.f.f.f) != null;
    }

    private void f(BluetoothGatt bluetoothGatt) {
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            try {
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                if (method != null) {
                    method.invoke(bluetoothGatt, new Object[0]);
                }
            } catch (Exception e2) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, e2.toString());
            }
        }
    }

    private void g(BluetoothGatt bluetoothGatt) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] start to discoverServices...");
        this.e.b(new g(), 10000L);
        if (bluetoothGatt.discoverServices()) {
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect:connectionStateChange()] discover services failed, retry...");
        this.f.postDelayed(new h(bluetoothGatt), 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] callConnectMethodSystemNoRespond()");
        x();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] callDisconnectMethodSystemNoRespond()");
        x();
        a(255, 255);
    }

    private void w() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("you should call this method on Main-Thread.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] close()");
        w();
        com.ido.ble.bluetooth.connect.j jVar = this.e;
        if (jVar != null) {
            jVar.b();
            this.e.a();
            this.e.c();
        }
        this.f12047a = 1;
        this.b = false;
        this.c = false;
        this.f.removeCallbacksAndMessages(null);
        BluetoothGatt bluetoothGatt = this.d;
        if (bluetoothGatt != null) {
            f(bluetoothGatt);
            this.d.close();
            this.d = null;
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.c = true;
        com.ido.ble.bluetooth.connect.q.b.b().a(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        com.ido.ble.common.e.a(new i());
    }

    public abstract void a(int i2, int i3);

    public abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    public abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2);

    public abstract void b(int i2, int i3);

    public abstract void b(BLEDevice bLEDevice);

    public void b(BLEDevice bLEDevice, long j2) {
        a(bLEDevice.mDeviceAddress);
        if (j2 < j) {
            j2 = 35000;
        }
        this.g = bLEDevice;
        if (bLEDevice.mIsInDfuMode) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] device in dfu mode, not to connect , address is " + bLEDevice.mDeviceAddress);
            C();
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] connect() , address is " + bLEDevice.mDeviceAddress);
        w();
        if (!BluetoothAdapter.checkBluetoothAddress(bLEDevice.mDeviceAddress)) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] connect() is refused, address is invalid");
            h();
        } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] connect() is refused, bluetooth is closed");
            f();
        } else if (!com.ido.ble.common.m.j()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] hasPhoneBluetoothPermission false.");
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] " + com.ido.ble.common.m.b());
            ScanCallBack.a();
        } else {
            int i2 = this.f12047a;
            if (i2 == 2 || i2 == 3) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] connect() is refused, state = " + this.f12047a);
                k();
                return;
            }
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] start to connect " + bLEDevice.mDeviceAddress);
            i();
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bLEDevice.mDeviceAddress);
            this.e.c(new f(), j2);
            this.h = true;
            this.d = (Build.VERSION.SDK_INT < 23 || remoteDevice.getType() != 3) ? remoteDevice.connectGatt(com.ido.ble.common.e.a(), false, new o()) : remoteDevice.connectGatt(com.ido.ble.common.e.a(), false, new o(), 2);
            this.f12047a = 2;
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] connecting " + bLEDevice.mDeviceAddress);
            k();
        }
    }

    public abstract void c(BLEDevice bLEDevice);

    public void d(BLEDevice bLEDevice) {
        b(bLEDevice, j);
    }

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract void j();

    public abstract void k();

    public abstract void l();

    public abstract void m();

    public abstract void n();

    public void o() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[BaseConnect] to disconnect.");
        this.b = false;
        com.ido.ble.common.e.a(new d());
    }

    public BLEDevice p() {
        return this.g;
    }

    public BluetoothGatt q() {
        return this.d;
    }

    public boolean r() {
        return this.c && this.d != null;
    }

    public boolean s() {
        return this.b && this.d != null;
    }

    public boolean t() {
        int i2;
        return this.d != null && ((i2 = this.f12047a) == 3 || i2 == 2);
    }
}
