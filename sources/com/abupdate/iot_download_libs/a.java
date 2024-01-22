package com.abupdate.iot_download_libs;

import com.abupdate.trace.Trace;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public class a implements Callable<Boolean> {
    public final long o;
    public String h = "DownMonitor";
    public List<DownEntity> k = new ArrayList();
    public List<DownEntity> l = new ArrayList();
    public DLManager n = DLManager.getInstance();
    public List<DownEntity> i = new ArrayList(this.n.getEntity_list());
    public List<DownEntity> j = new ArrayList(this.i);
    public List<b> m = new ArrayList(this.n.getThread_list());

    public a() {
        long j = 0;
        for (DownEntity downEntity : this.j) {
            j += downEntity.file_size;
        }
        this.o = j;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public Boolean call() {
        int i;
        List<DownEntity> list;
        int i2;
        Trace.d(this.h, "call() ");
        CallBackManager.getInstance().on_start();
        int i3 = 0;
        do {
            int i4 = 0;
            while (true) {
                long j = 0;
                if (i4 >= this.j.size()) {
                    break;
                }
                DownEntity downEntity = this.j.get(i4);
                ArrayList arrayList = new ArrayList(this.m);
                boolean z = false;
                boolean z2 = true;
                while (arrayList.size() > 0) {
                    b bVar = (b) arrayList.get(0);
                    if (downEntity.equals(bVar.f1880a)) {
                        j += bVar.c;
                        z = bVar.f1880a.download_status != 0;
                        if (z2 && !bVar.a()) {
                            z2 = false;
                        }
                    }
                    arrayList.remove(0);
                }
                if (downEntity.downloaded_size < j) {
                    downEntity.downloaded_size = j;
                }
                boolean z3 = downEntity.downloaded_size > downEntity.file_size ? true : z2;
                if (!downEntity.download_cancel && (i2 = (int) ((downEntity.downloaded_size * 100) / downEntity.file_size)) != 0) {
                    CallBackManager.getInstance().on_progress(downEntity, i2, downEntity.downloaded_size, downEntity.file_size);
                }
                if (z) {
                    Trace.e(this.h, "run() download error, cancel other thread download tasks.error=" + downEntity.download_status);
                    DLManager.getInstance().cancel_one(downEntity);
                }
                if (z3) {
                    int i5 = i4 - 1;
                    this.j.remove(i4);
                    if (downEntity.download_cancel) {
                        this.l.add(downEntity);
                        if (downEntity.download_status != 0) {
                            CallBackManager.getInstance().on_failed(downEntity);
                        }
                    } else {
                        if (b(downEntity)) {
                            if (!downEntity.download_cancel) {
                                CallBackManager.getInstance().on_success(downEntity);
                            }
                            list = this.k;
                        } else {
                            downEntity.download_status = -1;
                            if (!downEntity.download_cancel) {
                                CallBackManager.getInstance().on_failed(downEntity);
                            }
                            list = this.l;
                        }
                        list.add(downEntity);
                    }
                    i4 = i5;
                }
                i4++;
            }
            long d = d();
            if (d != 0 && (i = (int) ((100 * d) / this.o)) > i3) {
                Trace.i(this.h, "run() all_progress_post = %s", Integer.valueOf(i));
                CallBackManager.getInstance().on_all_progress(i, d, this.o);
                i3 = i;
            }
            if (this.j.size() == 0) {
                break;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!DLManager.getInstance().e);
        c();
        if (DLManager.getInstance().e) {
            CallBackManager.getInstance().on_manual_cancel();
        } else {
            CallBackManager.getInstance().on_finished(this.k, this.l);
        }
        DLManager.getInstance().b();
        DLManager.getInstance().d = false;
        return Boolean.TRUE;
    }

    public final boolean b(DownEntity downEntity) {
        String a2 = d.a(new File(downEntity.file_path));
        String str = this.h;
        Trace.i(str, "verify_md5() local md5:" + a2 + ",server md5:" + downEntity.md5);
        String str2 = downEntity.md5;
        if (str2 == null || a2.equalsIgnoreCase(str2)) {
            return true;
        }
        String str3 = this.h;
        Trace.e(str3, "run() entity:" + downEntity.file_size + "md5校验失败");
        return false;
    }

    public final void c() {
        for (DownEntity downEntity : this.k) {
            for (b bVar : this.m) {
                if (downEntity.equals(bVar.f1880a)) {
                    bVar.b();
                }
            }
        }
        for (DownEntity downEntity2 : this.l) {
            if (downEntity2.download_status == -1) {
                for (b bVar2 : this.m) {
                    if (downEntity2.equals(bVar2.f1880a)) {
                        DLManager.getInstance().deleteTempFile(downEntity2);
                    }
                }
            }
            if (downEntity2.download_status == -5) {
                for (b bVar3 : this.m) {
                    if (downEntity2.equals(bVar3.f1880a)) {
                        DLManager.getInstance().deleteTempFile(downEntity2);
                    }
                }
            }
        }
    }

    public final long d() {
        long j = 0;
        for (DownEntity downEntity : this.i) {
            j += downEntity.downloaded_size;
        }
        return j;
    }
}
