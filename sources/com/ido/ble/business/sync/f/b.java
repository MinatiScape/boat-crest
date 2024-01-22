package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class b extends f {
    private SyncCallBack.IActivityCallBack f = new a();

    /* loaded from: classes11.dex */
    public class a implements SyncCallBack.IActivityCallBack {
        public a() {
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onFailed() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] onFailed");
            b.this.e();
            b.this.f12141a.onFailed();
            b.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onGetActivityData(HealthActivity healthActivity) {
            ISyncDataListener iSyncDataListener = b.this.b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetActivityData(healthActivity);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] onStart");
            b.this.f12141a.onProgress(50);
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onStop() {
            if (b.this.c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] onStop");
            b.this.e();
            b.this.f12141a.onFailed();
            b.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] onSuccess");
            b.this.f12141a.onProgress(100);
            b.this.e();
            b.this.f12141a.onSuccess();
            b.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.c = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] finished!");
        com.ido.ble.callback.b.N().b(this.f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.d = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] start...");
        com.ido.ble.callback.b.N().a(this.f);
        com.ido.ble.i.a.a.t0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.c || !this.d) {
            return;
        }
        com.ido.ble.i.a.a.A0();
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncActivityTask] stop!");
        e();
    }
}
