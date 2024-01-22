package com.ido.ble.business.sync.f;

import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;
/* loaded from: classes11.dex */
public class c extends f {
    private b f;
    private SyncCallBack.IConfigCallBack g = new a();

    /* loaded from: classes11.dex */
    public class a implements SyncCallBack.IConfigCallBack {
        public a() {
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onFailed() {
            LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] onFailed");
            c.this.e();
            c.this.f12141a.onFailed();
            c.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] onStart");
            c.this.f12141a.onProgress(50);
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onStop() {
            if (c.this.c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] onStop");
            c.this.e();
            c.this.f12141a.onFailed();
            c.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] onSuccess");
            c.this.g();
        }
    }

    /* loaded from: classes11.dex */
    public class b extends com.ido.ble.callback.a {
        private b() {
        }

        public /* synthetic */ b(c cVar, a aVar) {
            this();
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            c.this.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.c = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] finished");
        com.ido.ble.callback.b.N().b(this.g);
        if (this.f != null) {
            com.ido.ble.callback.b.N().b(this.f);
        }
    }

    private void f() {
        this.f = new b(this, null);
        com.ido.ble.callback.b.N().a(this.f);
        com.ido.ble.i.a.a.T();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.c) {
            return;
        }
        com.ido.ble.business.sync.f.a aVar = this.f12141a;
        if (aVar != null) {
            aVar.onProgress(100);
        }
        e();
        com.ido.ble.business.sync.f.a aVar2 = this.f12141a;
        if (aVar2 != null) {
            aVar2.onSuccess();
        }
        b();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.d = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] start...");
        com.ido.ble.callback.b.N().a(this.g);
        com.ido.ble.i.a.a.u0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.c || (!this.d)) {
            return;
        }
        com.ido.ble.i.a.a.B0();
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncConfigTask] stop!");
        e();
    }
}
