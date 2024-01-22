package com.coveiot.mki.ota;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.coveiot.mki.C0974r;
import com.coveiot.mki.d;
import com.coveiot.mki.f;
import com.coveiot.mki.q;
import com.coveiot.mki.s;
import com.coveiot.mki.t;
/* loaded from: classes9.dex */
public class OTAManager {

    /* renamed from: a  reason: collision with root package name */
    public final Context f7285a;
    public final OTAPlatform b;
    public final BluetoothDevice c;
    public final byte[] d;
    public OTACallback e;
    public OTAService f;
    public final Object g = new Object();
    public final com.coveiot.mki.a h = new a();
    public final ServiceConnection i = new b();

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Context f7286a;
        public OTAPlatform b = null;
        public BluetoothDevice c = null;
        public byte[] d = null;

        public Builder(Context context) {
            this.f7286a = context;
        }

        public OTAManager build() {
            Context context = this.f7286a;
            if (context != null) {
                OTAPlatform oTAPlatform = this.b;
                if (oTAPlatform != null) {
                    BluetoothDevice bluetoothDevice = this.c;
                    if (bluetoothDevice != null) {
                        byte[] bArr = this.d;
                        if (bArr != null) {
                            if (bArr.length != 0) {
                                return new OTAManager(context, oTAPlatform, bluetoothDevice, bArr);
                            }
                            throw new Exception("Empty firmware");
                        }
                        throw new Exception("Null firmware");
                    }
                    throw new Exception("Null device");
                }
                throw new Exception("Null platform");
            }
            throw new Exception("Null context");
        }

        public Builder setFirmware(byte[] bArr) {
            this.d = bArr;
            return this;
        }

        public Builder setOtaDevice(BluetoothDevice bluetoothDevice) {
            this.c = bluetoothDevice;
            return this;
        }

        public Builder setOtaPlatform(OTAPlatform oTAPlatform) {
            this.b = oTAPlatform;
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public class a implements com.coveiot.mki.a {
        public a() {
        }

        @Override // com.coveiot.mki.a
        public void onConnected() {
            synchronized (OTAManager.this.g) {
                if (OTAManager.this.e != null) {
                    OTAManager.this.e.onStarted(OTAManager.this);
                }
            }
            OTAManager.f(OTAManager.this);
        }

        @Override // com.coveiot.mki.a
        public void onConnecting() {
        }

        @Override // com.coveiot.mki.a
        public void onDisconnected(String str) {
            synchronized (OTAManager.this.g) {
                if (OTAManager.this.e != null) {
                    OTAManager.this.e.onError(OTAManager.this, "Device disconnected");
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OTAManager.this.f = (OTAService) ((com.coveiot.mki.c) iBinder).a();
            OTAManager.this.f.setBleCallback(OTAManager.this.h);
            Intent intent = new Intent(OTAManager.this.f7285a, OTAService.class);
            intent.setAction(com.coveiot.mki.b.ACTION_NOTIFICATION_INIT);
            ContextCompat.startForegroundService(OTAManager.this.f7285a, intent);
            OTAManager.this.f.connect(OTAManager.this.c);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (OTAManager.this.g) {
                if (OTAManager.this.e != null) {
                    OTAManager.this.e.onError(OTAManager.this, "Service disconnected");
                }
            }
        }
    }

    public OTAManager(@NonNull Context context, @NonNull OTAPlatform oTAPlatform, @NonNull BluetoothDevice bluetoothDevice, @NonNull byte[] bArr) {
        this.f7285a = context;
        this.b = oTAPlatform;
        this.c = bluetoothDevice;
        this.d = bArr;
    }

    public static void c(OTAManager oTAManager, int i) {
        oTAManager.getClass();
        q qVar = new q();
        byte[] bArr = oTAManager.d;
        int length = (bArr.length + 1023) / 1024;
        int i2 = i * 1024;
        int length2 = bArr.length - i2;
        if (length2 < 0) {
            synchronized (oTAManager.g) {
                OTACallback oTACallback = oTAManager.e;
                if (oTACallback != null) {
                    oTACallback.onError(oTAManager, "Unknown error");
                }
            }
            return;
        }
        int i3 = length2 <= 1024 ? length2 : 1024;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        qVar.a(length, i, bArr2);
        oTAManager.f.command(d.JIELI_SEND_OTA, qVar, new c(oTAManager, i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void f(OTAManager oTAManager) {
        d dVar;
        f bVar;
        OTAService oTAService;
        s sVar;
        if (oTAManager.b == OTAPlatform.Apollo) {
            C0974r c0974r = new C0974r();
            c0974r.a(oTAManager.d);
            OTAService oTAService2 = oTAManager.f;
            dVar = d.SEND_UPDATE_FILE;
            bVar = new com.coveiot.mki.ota.a(oTAManager);
            sVar = c0974r;
            oTAService = oTAService2;
        } else {
            s sVar2 = new s();
            byte[] bArr = oTAManager.d;
            sVar2.a(bArr.length, t.a(bArr));
            OTAService oTAService3 = oTAManager.f;
            dVar = d.JIELI_SET_OTA_MODE;
            bVar = new com.coveiot.mki.ota.b(oTAManager);
            sVar = sVar2;
            oTAService = oTAService3;
        }
        oTAService.command(dVar, sVar, bVar);
    }

    public static void k(OTAManager oTAManager) {
        oTAManager.f.disconnect();
        oTAManager.f.stop();
    }

    public void abort() {
        synchronized (this.g) {
            this.f.stopAllCommands();
            OTACallback oTACallback = this.e;
            if (oTACallback != null) {
                oTACallback.onAborted(this);
            }
            this.e = null;
            this.f.disconnect();
            this.f.stop();
        }
    }

    public void ota(OTACallback oTACallback) {
        this.e = oTACallback;
        this.f7285a.bindService(new Intent(this.f7285a, OTAService.class), this.i, 1);
    }
}
