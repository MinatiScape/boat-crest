package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.logs.LogTool;
import java.util.List;
/* loaded from: classes11.dex */
public class d extends f {
    private GpsCallBack.ISyncGpsDataCallBack f = new a();

    /* loaded from: classes11.dex */
    public class a implements GpsCallBack.ISyncGpsDataCallBack {
        public a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onFailed() {
            d.this.e();
            d.this.f12141a.onFailed();
            d.this.b();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onFinish() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncGpsTask] onFinish");
            d.this.f12141a.onProgress(100);
            d.this.e();
            d.this.f12141a.onSuccess();
            d.this.b();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onGetGpsData(HealthGps healthGps, List<HealthGpsItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = d.this.b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetGpsData(healthGps, list, z);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onProgress(int i) {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncGpsTask] progress = " + i);
            d.this.f12141a.onProgress(i);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncGpsTask] onStart");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.c = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncGpsTask] finished!");
        com.ido.ble.gps.callback.a.h().b(this.f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.d = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncGpsTask] start...");
        com.ido.ble.gps.callback.a.h().a(this.f);
        com.ido.ble.h.a.g();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.c || !this.d) {
            return;
        }
        com.ido.ble.h.a.i();
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncGpsTask] stop!");
        e();
    }
}
