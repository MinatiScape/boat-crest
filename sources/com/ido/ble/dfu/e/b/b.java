package com.ido.ble.dfu.e.b;

import android.util.Log;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.common.n;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class b {
    private static boolean e = false;
    private static final int f = 5;
    private c b;

    /* renamed from: a  reason: collision with root package name */
    private int f12197a = 0;
    private int c = -1;
    private EnterDfuModeCallback.ICallBack d = new a();

    /* loaded from: classes11.dex */
    public class a implements EnterDfuModeCallback.ICallBack {
        public a() {
        }

        @Override // com.ido.ble.callback.EnterDfuModeCallback.ICallBack
        public void onError(EnterDfuModeCallback.DfuError dfuError) {
            LogTool.b(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] error is " + dfuError);
            b.this.a(dfuError);
        }

        @Override // com.ido.ble.callback.EnterDfuModeCallback.ICallBack
        public void onSuccess() {
            b.this.g();
        }
    }

    /* renamed from: com.ido.ble.dfu.e.b.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0592b implements n.b {
        public C0592b() {
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            Log.e(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] onTimeOut, retry...");
            b.this.d();
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a();

        void a(EnterDfuModeCallback.DfuError dfuError);

        void b();

        void onSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(EnterDfuModeCallback.DfuError dfuError) {
        LogTool.b(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] enter dfu mode failed!");
        b();
        this.b.a(dfuError);
    }

    private void b() {
        LogTool.d(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] finished!");
        n.a(this.c);
        c();
    }

    private void c() {
        e = false;
        com.ido.ble.callback.b.N().b(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.f12197a > 5) {
            LogTool.d(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] out of max retry times.");
            e();
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] restart...");
        this.f12197a++;
        if (com.ido.ble.bluetooth.a.h()) {
            f();
            com.ido.ble.i.a.a.f();
            return;
        }
        b();
        this.b.b();
    }

    private void e() {
        b();
        this.b.a();
    }

    private void f() {
        this.c = n.a(new C0592b(), 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] enter dfu mode success!");
        b();
        this.b.onSuccess();
    }

    public void a() {
        if (e) {
            LogTool.d(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] stop task!");
            c();
        }
    }

    public void a(c cVar) {
        if (e) {
            LogTool.b(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] is doing, ignore this action!");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.b, "[EnterDFUModeTask] start...");
        this.b = cVar;
        com.ido.ble.callback.b.N().a(this.d);
        e = true;
        if (com.ido.ble.bluetooth.a.h()) {
            f();
            com.ido.ble.i.a.a.f();
            return;
        }
        b();
        cVar.b();
    }
}
