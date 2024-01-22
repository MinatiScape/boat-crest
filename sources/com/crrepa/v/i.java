package com.crrepa.v;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.ble.trans.upgrade.bean.HSFirmwareInfo;
import com.crrepa.m0.j;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public CRPBleFirmwareUpgradeListener f7853a;
    public Handler c = new b(this);
    public boolean d = false;
    public j b = new j(com.crrepa.i0.f.a(), this.c);

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ HSFirmwareInfo h;

        public a(HSFirmwareInfo hSFirmwareInfo) {
            this.h = hSFirmwareInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean g = i.this.g(this.h);
            i.this.e();
            i.this.a(true);
            if (g) {
                i.this.n();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<i> f7854a;
        public float b = 100.0f;
        public float c = 0.0f;
        public int d = 0;

        public b(i iVar) {
            this.f7854a = new WeakReference<>(iVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            com.crrepa.i0.c.c("HS handleMessage what: " + i);
            com.crrepa.i0.c.c("HS handleMessage arg1 " + i2);
            com.crrepa.i0.c.c("HS handleMessage arg2 " + i3);
            i iVar = this.f7854a.get();
            if (iVar == null) {
                return;
            }
            if (i2 == 1000) {
                iVar.k(message, i3);
            } else if (i2 == 1008) {
                this.c = ((Float) message.obj).floatValue();
            } else {
                switch (i2) {
                    case 1002:
                        this.b = i3;
                        return;
                    case 1003:
                        int i4 = (int) ((i3 / this.b) * 100.0f);
                        if (i4 != this.d) {
                            iVar.b(i4, this.c);
                            this.d = i4;
                            return;
                        }
                        return;
                    case 1004:
                        iVar.o(message, i3);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void a() {
        com.crrepa.v.a.d().g();
        com.crrepa.p.c.b().a();
    }

    public void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        this.f7853a = cRPBleFirmwareUpgradeListener;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void a(byte[] bArr, int i) {
        com.crrepa.i0.c.c("setBluetoothNotifyData type: " + i);
        this.b.b(bArr, i);
    }

    public final void b(int i, float f) {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.f7853a;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onUpgradeProgressChanged(i, f);
        }
    }

    public boolean b() {
        return this.d;
    }

    public void c(HSFirmwareInfo hSFirmwareInfo) {
        a(false);
        l(hSFirmwareInfo);
    }

    public void e() {
        this.b.d();
    }

    public final void f(String str) {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.f7853a;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onError(23, str);
        }
    }

    public final boolean g(HSFirmwareInfo hSFirmwareInfo) {
        String userFilePath = hSFirmwareInfo.getUserFilePath();
        String appFilePath = hSFirmwareInfo.getAppFilePath();
        String configFilePath = hSFirmwareInfo.getConfigFilePath();
        String patchFilePath = hSFirmwareInfo.getPatchFilePath();
        if (!TextUtils.isEmpty(userFilePath)) {
            byte[] a2 = com.crrepa.i0.j.a(userFilePath);
            if (a2 == null) {
                f("load user file error");
                return false;
            }
            com.crrepa.i0.c.c("user file size: " + a2.length);
            String userStartAddress = hSFirmwareInfo.getUserStartAddress();
            com.crrepa.i0.c.c("start address: " + userStartAddress);
            int a3 = this.b.a(a2, userStartAddress);
            com.crrepa.i0.c.c("WriteUserData success: " + a3);
            if (a3 < 0) {
                f("load user file error");
                return false;
            }
        }
        return i(appFilePath, 3, "load app file error") && i(configFilePath, 4, "load config file error") && i(patchFilePath, 5, "load patch file error");
    }

    public final boolean i(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        byte[] a2 = com.crrepa.i0.j.a(str);
        com.crrepa.i0.c.c("Binary file size: " + a2.length);
        int a3 = this.b.a(a2, i);
        com.crrepa.i0.c.c("LoadBinary: " + a3);
        if (a3 < 0) {
            f(str2);
            return false;
        }
        return true;
    }

    public final byte[] j(Message message, int i) {
        byte[] bArr = new byte[i];
        System.arraycopy((byte[]) message.obj, 0, bArr, 0, i);
        return bArr;
    }

    public final void k(Message message, int i) {
        com.crrepa.v.a.d().a(j(message, i));
    }

    public final void l(HSFirmwareInfo hSFirmwareInfo) {
        p();
        q();
        new Thread(new a(hSFirmwareInfo)).start();
    }

    public final void n() {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.f7853a;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onUpgradeCompleted();
        }
    }

    public final void o(Message message, int i) {
        com.crrepa.v.a.d().b(j(message, i));
    }

    public final void p() {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.f7853a;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onUpgradeProgressStarting();
        }
    }

    public final void q() {
        this.b.a(false);
    }
}
