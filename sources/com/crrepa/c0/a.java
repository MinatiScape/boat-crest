package com.crrepa.c0;

import com.crrepa.ble.conn.callback.CRPDeviceDfuTypeCallback;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.f.n;
import com.crrepa.i0.c;
import com.crrepa.i0.f;
import java.io.File;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public CRPBleFirmwareUpgradeListener f7692a;

    /* renamed from: com.crrepa.c0.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class C0336a implements CRPDeviceDfuTypeCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ File f7693a;

        public C0336a(File file) {
            this.f7693a = file;
        }

        @Override // com.crrepa.ble.conn.callback.CRPDeviceDfuTypeCallback
        public void onDfuType(int i) {
            a.this.b(i, this.f7693a);
        }
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final a f7694a = new a(null);
    }

    public a() {
    }

    public /* synthetic */ a(C0336a c0336a) {
        this();
    }

    public static a b() {
        return b.f7694a;
    }

    public void a() {
        com.crrepa.e0.b.a().abort();
    }

    public void a(boolean z, CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener, File file, String str) {
        if (cRPBleFirmwareUpgradeListener == null || file == null || !file.exists()) {
            return;
        }
        this.f7692a = cRPBleFirmwareUpgradeListener;
        f.a(str);
        if (z) {
            d(file);
        } else {
            e(file);
        }
    }

    public final void b(int i, File file) {
        c.a("onDfuType: " + i);
        String str = i == 1 ? "gr-B" : "gr-A";
        File file2 = null;
        try {
            file2 = com.crrepa.w.b.a(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file2 != null && file2.listFiles() != null && 1 < file2.listFiles().length) {
            File[] listFiles = file2.listFiles();
            for (File file3 : listFiles) {
                if (file3.getName().contains(str)) {
                    e(file3);
                    return;
                }
            }
        }
        c.b("file is error!");
        this.f7692a.onError(17, "Firmware is null!");
    }

    public final void d(File file) {
        com.crrepa.d.b.a().a(new C0336a(file));
        com.crrepa.m.f.d().a(n.a());
    }

    public final void e(File file) {
        com.crrepa.e0.b a2 = com.crrepa.e0.b.a();
        a2.a(this.f7692a);
        a2.a(file);
        a2.b();
    }
}
