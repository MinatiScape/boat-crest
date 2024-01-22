package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.logs.LogTool;
import java.util.List;
/* loaded from: classes11.dex */
public class e extends f {
    private SyncCallBack.IHealthCallBack f = new a();

    /* loaded from: classes11.dex */
    public class a implements SyncCallBack.IHealthCallBack {
        public a() {
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onFailed() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask]failed");
            e.this.e();
            e.this.f12141a.onFailed();
            e.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = e.this.b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetBloodPressureData(healthBloodPressed, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = e.this.b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHeartRateData(healthHeartRate, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> list) {
            ISyncDataListener iSyncDataListener = e.this.b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetSleepData(healthSleep, list);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetSportData(HealthSport healthSport, List<HealthSportItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = e.this.b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetSportData(healthSport, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onProgress(int i) {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask] progress = " + i);
            e.this.f12141a.onProgress(i);
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask]onStart");
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onStop() {
            if (e.this.c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask]onStop");
            e.this.e();
            e.this.f12141a.onFailed();
            e.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask] onSuccess");
            e.this.e();
            e.this.f12141a.onSuccess();
            e.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.c = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask] finished");
        com.ido.ble.callback.b.N().b(this.f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.d = true;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask] start...");
        com.ido.ble.callback.b.N().a(this.f);
        com.ido.ble.i.a.a.v0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.c || !this.d) {
            return;
        }
        com.ido.ble.i.a.a.C0();
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncHealthTask] stop!");
        e();
    }
}
