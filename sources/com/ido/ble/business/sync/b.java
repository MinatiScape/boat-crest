package com.ido.ble.business.sync;

import com.ido.ble.BLEManager;
import com.ido.ble.business.sync.a;
import com.ido.ble.business.sync.e;
import com.ido.ble.business.sync.f.f;
import com.ido.ble.business.sync.f.g;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes11.dex */
public class b {
    private static final long j = 300000;
    private static b k = null;
    private static final int l = 10;
    private static final int m = 35;
    private static final int n = 45;
    private static final int o = 5;
    private static final int p = 5;

    /* renamed from: a  reason: collision with root package name */
    private ActivityDataCount f12128a;
    private SyncPara h;
    private LinkedList<f> b = new LinkedList<>();
    private LinkedList<f> c = new LinkedList<>();
    private boolean d = false;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int i = -1;

    /* loaded from: classes11.dex */
    public class a implements e.b {
        public a() {
        }

        @Override // com.ido.ble.business.sync.e.b
        public void a() {
            b.this.m();
        }
    }

    /* renamed from: com.ido.ble.business.sync.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0581b implements a.c {
        public C0581b() {
        }

        @Override // com.ido.ble.business.sync.a.c
        public void a(ActivityDataCount activityDataCount) {
            if (!b.this.d) {
                LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] get activity count ok,but task is quit.");
                return;
            }
            b.this.f12128a = activityDataCount;
            if (d.a()) {
                b.this.j();
            } else {
                b.this.i();
            }
        }

        @Override // com.ido.ble.business.sync.a.c
        public void onFailed() {
            b.this.k();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements com.ido.ble.business.sync.f.a {

        /* renamed from: a  reason: collision with root package name */
        private f f12131a;

        public c(f fVar) {
            this.f12131a = fVar;
        }

        @Override // com.ido.ble.business.sync.f.a
        public void onFailed() {
            b.c(b.this);
            b bVar = b.this;
            bVar.e = bVar.f;
            b.this.c();
        }

        @Override // com.ido.ble.business.sync.f.a
        public void onProgress(int i) {
            int a2 = (int) ((i * this.f12131a.a()) / 100.0d);
            if (b.this.h.iSyncProgressListener != null) {
                b.this.h.iSyncProgressListener.onProgress(b.this.e + a2);
            }
            b bVar = b.this;
            bVar.f = bVar.e + a2;
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] totalProgress = " + (b.this.e + a2));
        }

        @Override // com.ido.ble.business.sync.f.a
        public void onSuccess() {
            b.this.e += this.f12131a.a();
            b bVar = b.this;
            bVar.f = bVar.e;
            b.this.c();
        }
    }

    public static /* synthetic */ int c(b bVar) {
        int i = bVar.g;
        bVar.g = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.b.size() == 0) {
            if (this.g == 0) {
                l();
            } else {
                k();
            }
        } else if (!BLEManager.isConnected()) {
            LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] connect is break, stop sync");
            k();
        } else {
            f poll = this.b.poll();
            if (poll != null) {
                poll.a(new c(poll));
                poll.a(this.h.iSyncDataListener);
                poll.c();
            }
        }
    }

    public static b d() {
        if (k == null) {
            k = new b();
        }
        return k;
    }

    private void e() {
        int i;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] initOnlyV3Task...");
        if (this.h.isNeedSyncConfigData) {
            com.ido.ble.business.sync.f.c cVar = new com.ido.ble.business.sync.f.c();
            cVar.a(10);
            i = 90;
            this.b.add(cVar);
        } else {
            i = 100;
        }
        g gVar = new g();
        gVar.a(70);
        int i2 = i - 70;
        this.b.add(gVar);
        ActivityDataCount activityDataCount = this.f12128a;
        if (activityDataCount != null && activityDataCount.gps_count > 0) {
            com.ido.ble.business.sync.f.d dVar = new com.ido.ble.business.sync.f.d();
            dVar.a(20);
            i2 -= 20;
            this.b.add(dVar);
        }
        if (i2 > 0) {
            gVar.a(gVar.a() + i2);
        }
        this.c.addAll(this.b);
    }

    private void f() {
        int i;
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] initSyncV2AndV3Task...");
        if (this.h.isNeedSyncConfigData) {
            com.ido.ble.business.sync.f.c cVar = new com.ido.ble.business.sync.f.c();
            cVar.a(10);
            i = 90;
            this.b.add(cVar);
        } else {
            i = 100;
        }
        com.ido.ble.business.sync.f.e eVar = new com.ido.ble.business.sync.f.e();
        eVar.a(35);
        int i2 = i - 35;
        this.b.add(eVar);
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        if (Z != null && (Z.ex_main3_v3_spo2_data || Z.ex_main4_v3_swim || Z.ex_main4_v3_hr_data || Z.ex_main3_v3_pressure || Z.ex_table_main8_v3_sync_activity)) {
            g gVar = new g();
            gVar.a(45);
            i2 -= 45;
            this.b.add(gVar);
        }
        ActivityDataCount activityDataCount = this.f12128a;
        if (activityDataCount != null) {
            if (activityDataCount.count > 0 && (Z == null || !Z.ex_table_main8_v3_sync_activity)) {
                com.ido.ble.business.sync.f.b bVar = new com.ido.ble.business.sync.f.b();
                bVar.a(5);
                i2 -= 5;
                this.b.add(bVar);
            }
            if (this.f12128a.gps_count > 0) {
                com.ido.ble.business.sync.f.d dVar = new com.ido.ble.business.sync.f.d();
                dVar.a(5);
                i2 -= 5;
                this.b.add(dVar);
            }
        }
        if (i2 > 0) {
            eVar.a(eVar.a() + i2);
        }
        this.c.addAll(this.b);
    }

    private void g() {
        e.a(this.i);
        this.b.clear();
        this.c.clear();
        this.d = false;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h.iSyncDataListener = null;
        this.f12128a = null;
    }

    private void h() {
        new com.ido.ble.business.sync.a().a(new C0581b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        e();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        f();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        g();
        LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] synced failed.");
        ISyncProgressListener iSyncProgressListener = this.h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onFailed();
            this.h.iSyncProgressListener = null;
        }
    }

    private void l() {
        g();
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] synced success.");
        ISyncProgressListener iSyncProgressListener = this.h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onSuccess();
            this.h.iSyncProgressListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] synced time out.");
        b();
        ISyncProgressListener iSyncProgressListener = this.h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onFailed();
            this.h.iSyncProgressListener = null;
        }
    }

    public int a(ISyncProgressListener iSyncProgressListener) {
        if (iSyncProgressListener != null) {
            this.h.iSyncProgressListener = iSyncProgressListener;
        }
        return this.f;
    }

    public boolean a() {
        return this.d;
    }

    public synchronized boolean a(SyncPara syncPara) {
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] start...");
        if (syncPara == null) {
            LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] syncConfig is null!");
            return false;
        } else if (this.d) {
            LogTool.b(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] is in doing state, ignore this action!");
            return false;
        } else {
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] syncPara is " + syncPara.toString());
            this.h = syncPara;
            long j2 = syncPara.timeoutMillisecond;
            if (j2 < 120000) {
                j2 = 300000;
            }
            this.d = true;
            ISyncProgressListener iSyncProgressListener = syncPara.iSyncProgressListener;
            if (iSyncProgressListener != null) {
                iSyncProgressListener.onStart();
            }
            if (d.b()) {
                h();
            } else {
                LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] is not need to get v2 activity and gps count.");
                if (d.a()) {
                    j();
                } else {
                    i();
                }
            }
            this.i = e.a(new a(), j2);
            return true;
        }
    }

    public synchronized void b() {
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[SyncAllDataManager] stop");
        if (this.d) {
            Iterator<f> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().d();
            }
            g();
        }
    }
}
