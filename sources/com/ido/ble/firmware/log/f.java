package com.ido.ble.firmware.log;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.firmware.log.b;
import com.ido.ble.logs.LogTool;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class f {
    private static f g;
    private static final String h = System.getProperty("line.separator");

    /* renamed from: a  reason: collision with root package name */
    private Handler f12276a;
    private StringBuilder b;
    private b c;
    private ICollectDeviceRebootLogListener d;
    private boolean e = false;
    private boolean f = false;

    /* loaded from: classes11.dex */
    public class a implements b.InterfaceC0602b {
        public a() {
        }

        @Override // com.ido.ble.firmware.log.b.InterfaceC0602b
        public void a() {
            if (f.this.f) {
                f.this.e();
            } else {
                f.this.a();
            }
        }
    }

    private f() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(c.f12275a, "failed.");
        this.b.append("-----not full, will collect logs next time---");
        f();
        ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener = this.d;
        if (iCollectDeviceRebootLogListener != null) {
            iCollectDeviceRebootLogListener.onFailed();
        }
        c();
    }

    private void a(boolean z) {
        if (this.f12276a == null) {
            this.f12276a = new Handler(Looper.getMainLooper());
        }
        StringBuilder sb = new StringBuilder();
        this.b = sb;
        if (z) {
            sb.append("[reboot = true]" + h);
        }
        if (this.c == null) {
            this.c = new b();
        }
    }

    public static f b() {
        if (g == null) {
            g = new f();
        }
        return g;
    }

    private void c() {
        LogTool.d(c.f12275a, "release.");
        this.d = null;
        this.e = false;
        this.f = false;
    }

    private boolean c(byte[] bArr) {
        return e.c(bArr) || e.b(bArr) || e.d(bArr);
    }

    private void d() {
        LogTool.d(c.f12275a, "start-->");
        ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener = this.d;
        if (iCollectDeviceRebootLogListener != null) {
            iCollectDeviceRebootLogListener.onStart();
        }
    }

    private void d(byte[] bArr) {
        String str = new String(Arrays.copyOfRange(bArr, 3, bArr.length - 1));
        StringBuilder sb = this.b;
        sb.append(str);
        sb.append(h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(c.f12275a, FirebaseAnalytics.Param.SUCCESS);
        this.c.a();
        String f = f();
        if (this.d != null) {
            if (TextUtils.isEmpty(f)) {
                this.d.onFailed();
            } else {
                this.d.onSuccess(f);
            }
        }
        c();
    }

    private String f() {
        LogTool.d(c.f12275a, "write log to file.");
        return g.a(this.b.toString());
    }

    public boolean a(boolean z, ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener) {
        if (this.e) {
            LogTool.b(c.f12275a, "[FirmwareRebootLogManager] start(), isDoing = true, ignore this action.");
            return false;
        }
        this.e = true;
        this.f = false;
        this.d = iCollectDeviceRebootLogListener;
        a(z);
        d();
        e.b();
        this.c.a(new a(), 10);
        return true;
    }

    public boolean a(byte[] bArr) {
        return this.e && c(bArr);
    }

    public void b(byte[] bArr) {
        LogTool.d(c.f12275a, "[handleDeviceRespond] value is " + com.ido.ble.common.c.b(bArr));
        this.c.b();
        if (e.c(bArr)) {
            if (e.a(bArr)) {
                d(bArr);
                e.b();
                return;
            }
            this.f = true;
            e.a();
        } else if (e.b(bArr)) {
            e.c();
        } else if (e.d(bArr)) {
            e();
        }
    }
}
