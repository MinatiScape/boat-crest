package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Handler;
import android.os.Looper;
import com.touchgui.sdk.TGConnectionListener;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.TGLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
/* loaded from: classes12.dex */
public final class g implements m, n {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f13763a = new Handler(Looper.getMainLooper());
    public final q b;
    public final ArrayList c;
    public int d;
    public boolean e;
    public boolean f;
    public t g;
    public e2 h;
    public int i;
    public final ArrayList j;
    public int k;
    public final Runnable l;

    public g(a0 a0Var) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.d = 0;
        this.e = false;
        this.f = false;
        this.i = 0;
        this.j = new ArrayList();
        this.k = 0;
        this.l = new Runnable() { // from class: com.touchgui.sdk.internal.cc
            @Override // java.lang.Runnable
            public final void run() {
                g.this.b();
            }
        };
        this.b = new q(a0Var);
        arrayList.add("00000af0-0000-1000-8000-00805f9b34fb");
        arrayList.add("000027f0-0000-1000-8000-00805f9b34fb");
        arrayList.add("00002760-0000-1000-8000-00805f9b34fb");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        TGLogger.e("ready timeout");
        b(TGErrorCode.ERROR_READY_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        t tVar = this.g;
        if (tVar != null) {
            TGLogger.e(tVar.f13825a.c(), "Failed to connect device: code=10004");
            Iterator it = tVar.f13825a.g.iterator();
            while (it.hasNext()) {
                ((TGConnectionListener) it.next()).onError(TGErrorCode.ERROR_NOT_CONNECTED);
            }
        }
    }

    public final void a() {
        BluetoothGatt bluetoothGatt;
        boolean z = false;
        if (this.i >= this.j.size()) {
            if ((this.e && this.d > 0) || this.d == 247) {
                this.e = true;
                b(0);
                return;
            }
            this.e = true;
            if (this.b.i()) {
                return;
            }
            TGLogger.e(this.b.n, "Failed to call requestMtu");
            b(10001);
            return;
        }
        BluetoothGattDescriptor bluetoothGattDescriptor = (BluetoothGattDescriptor) this.j.get(this.i);
        bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        q qVar = this.b;
        if (c8.a(qVar.f13815a) && (bluetoothGatt = qVar.g) != null && bluetoothGatt.writeDescriptor(bluetoothGattDescriptor)) {
            z = true;
        }
        TGLogger.d(this.b.n, "writeDescriptor - uuid: " + bluetoothGattDescriptor.getUuid().toString() + " ret=" + z);
        this.i = this.i + 1;
    }

    public final void b(final int i) {
        if (this.k != 1) {
            TGLogger.d("Not in preparation.");
            return;
        }
        this.f13763a.removeCallbacks(this.l);
        boolean z = i == 0;
        this.f = z;
        if (z) {
            this.k = 2;
            q qVar = this.b;
            final BluetoothDevice bluetoothDevice = qVar.h;
            if (bluetoothDevice != null) {
                TGLogger.d(qVar.n, "it is ready...");
                j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.fc
                    @Override // java.lang.Runnable
                    public final void run() {
                        g.this.a(bluetoothDevice);
                    }
                });
                return;
            }
            TGLogger.e(qVar.n, "Failed to ready");
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.dc
                @Override // java.lang.Runnable
                public final void run() {
                    g.this.c();
                }
            });
            return;
        }
        this.k = 0;
        TGLogger.e(this.b.n, "Failed to ready");
        j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.ec
            @Override // java.lang.Runnable
            public final void run() {
                g.this.a(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        t tVar = this.g;
        if (tVar != null) {
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            a0 a0Var = tVar.f13825a;
            if (a0Var.k == null) {
                a0Var.k = new l9(a0Var.j);
            }
            a0Var.k.getDeviceInfo().execute(new u(a0Var, name, address));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        t tVar = this.g;
        if (tVar != null) {
            String c = tVar.f13825a.c();
            TGLogger.e(c, "Failed to connect device: code=" + i);
            Iterator it = tVar.f13825a.g.iterator();
            while (it.hasNext()) {
                ((TGConnectionListener) it.next()).onError(i);
            }
        }
    }

    public final void a(int i, String str) {
        if (i != 2) {
            this.d = 0;
            this.e = false;
            this.f = false;
            if (i == 0) {
                this.f13763a.removeCallbacks(this.l);
            }
        } else {
            this.k = 1;
            this.f13763a.postDelayed(this.l, 10000L);
        }
        t tVar = this.g;
        if (tVar != null) {
            if (i == 0) {
                a0 a0Var = tVar.f13825a;
                a0Var.D = 0;
                i3 i3Var = a0Var.f;
                synchronized (i3Var) {
                    Iterator it = i3Var.c.iterator();
                    while (it.hasNext()) {
                        ((h3) it.next()).a(false);
                    }
                }
            }
            Iterator it2 = tVar.f13825a.g.iterator();
            while (it2.hasNext()) {
                ((TGConnectionListener) it2.next()).onConnectionStateChange(i, str);
            }
        }
    }

    public final boolean a(int i, byte[] bArr) {
        String str;
        UUID uuid;
        BluetoothGatt bluetoothGatt;
        String str2;
        q qVar = this.b;
        if ((qVar.k == 2) && this.f) {
            BluetoothGatt bluetoothGatt2 = qVar.g;
            BluetoothGattService bluetoothGattService = null;
            for (BluetoothGattService bluetoothGattService2 : bluetoothGatt2 == null ? null : bluetoothGatt2.getServices()) {
                if (i == 3 || i == 4) {
                    if (bluetoothGattService2.getUuid().equals(UUID.fromString("00002760-0000-1000-8000-00805f9b34fb"))) {
                        bluetoothGattService = bluetoothGattService2;
                        break;
                    }
                } else if (bluetoothGattService2.getUuid().equals(UUID.fromString("000027f0-0000-1000-8000-00805f9b34fb"))) {
                    bluetoothGattService = bluetoothGattService2;
                    break;
                } else if (bluetoothGattService2.getUuid().equals(UUID.fromString("00000af0-0000-1000-8000-00805f9b34fb"))) {
                    bluetoothGattService = bluetoothGattService2;
                }
            }
            if (i == 3 || i == 4) {
                bluetoothGattService = null;
            }
            if (bluetoothGattService == null) {
                TGLogger.e(this.b.n, "not find service");
                return false;
            }
            if (bluetoothGattService.getUuid().equals(UUID.fromString("00002760-0000-1000-8000-00805f9b34fb"))) {
                if (i == 3) {
                    str = "00002761-0000-1000-8000-00805f9b34fb";
                } else {
                    if (i == 4) {
                        str = "00002763-0000-1000-8000-00805f9b34fb";
                    }
                    uuid = null;
                }
                uuid = UUID.fromString(str);
            } else {
                if (bluetoothGattService.getUuid().equals(UUID.fromString("000027f0-0000-1000-8000-00805f9b34fb"))) {
                    if (i == 1) {
                        str = "000027f6-0000-1000-8000-00805f9b34fb";
                    } else if (i == 2) {
                        str = "000027f1-0000-1000-8000-00805f9b34fb";
                    }
                    uuid = UUID.fromString(str);
                }
                uuid = null;
            }
            if (uuid == null) {
                if (i == 2) {
                    str2 = "00000af1-0000-1000-8000-00805f9b34fb";
                } else {
                    str2 = i == 1 ? "00000af6-0000-1000-8000-00805f9b34fb" : "00000af6-0000-1000-8000-00805f9b34fb";
                }
                uuid = UUID.fromString(str2);
            }
            BluetoothGattCharacteristic characteristic = uuid != null ? bluetoothGattService.getCharacteristic(uuid) : null;
            if (characteristic == null) {
                TGLogger.e(this.b.n, "not find channel");
                return false;
            }
            characteristic.setValue(bArr);
            characteristic.setWriteType(1);
            q qVar2 = this.b;
            if (!c8.a(qVar2.f13815a) || qVar2.f == null || (bluetoothGatt = qVar2.g) == null) {
                return false;
            }
            return bluetoothGatt.writeCharacteristic(characteristic);
        }
        String str3 = qVar.n;
        StringBuilder sb = new StringBuilder("not ready：connected=");
        sb.append(this.b.k == 2);
        sb.append(", ready=");
        sb.append(this.f);
        TGLogger.e(str3, sb.toString());
        return false;
    }

    public final void a(t tVar) {
        this.g = tVar;
    }
}
