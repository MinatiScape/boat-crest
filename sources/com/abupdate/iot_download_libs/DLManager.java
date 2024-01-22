package com.abupdate.iot_download_libs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.abupdate.trace.Trace;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class DLManager {
    public static DLManager g;
    public static final ThreadFactory h;
    public static final int i;
    public static final ThreadPoolExecutor j;
    public volatile boolean d;
    public volatile boolean e;
    public Context mCx;

    /* renamed from: a  reason: collision with root package name */
    public List<DownEntity> f1878a = new ArrayList();
    public List<com.abupdate.iot_download_libs.b> b = new ArrayList();
    public IOnDownListener c = DownSimpleListener.INSTANCE;
    public ThreadPoolExecutor f = j;

    /* loaded from: classes.dex */
    public static class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "DLManager_" + this.h.getAndIncrement());
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (DownEntity downEntity : DLManager.this.f1878a) {
                DLManager.this.b.addAll(c.a(downEntity));
            }
            for (int i = 0; i < DLManager.this.b.size(); i++) {
                if (!((com.abupdate.iot_download_libs.b) DLManager.this.b.get(i)).a()) {
                    DLManager.this.f.execute((Runnable) DLManager.this.b.get(i));
                }
            }
            new FutureTask(new com.abupdate.iot_download_libs.a()).run();
        }
    }

    static {
        a aVar = new a();
        h = aVar;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        i = availableProcessors;
        j = new ThreadPoolExecutor(availableProcessors + 1, (availableProcessors * 2) + 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(1024), aVar);
    }

    public static DLManager getInstance() {
        if (g == null) {
            g = new DLManager();
        }
        return g;
    }

    public void add(List<DownEntity> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            add(list.get(i2));
        }
    }

    public boolean add(DownEntity downEntity) {
        String str;
        if (this.d) {
            str = "add() can't add entity if download task is starting.";
        } else if (!this.f1878a.contains(downEntity)) {
            downEntity.download_cancel = false;
            downEntity.download_status = 0;
            this.f1878a.add(downEntity);
            return true;
        } else {
            str = "add() can't add entity if the downEntity(url) was add.";
        }
        Trace.w("DLManager", str);
        return false;
    }

    public void b() {
        this.f1878a.clear();
        this.b.clear();
        this.c = DownSimpleListener.INSTANCE;
        this.e = false;
    }

    public void cancel_all() {
        Trace.d("DLManager", "cancel_all");
        if (!this.d) {
            Trace.w("DLManager", "cancel_all() invalid,because the task isn't downloading.");
            return;
        }
        this.e = true;
        for (int i2 = 0; i2 < this.f1878a.size(); i2++) {
            this.f1878a.get(i2).download_cancel = true;
        }
    }

    public void cancel_one(DownEntity downEntity) {
        Trace.d("DLManager", "cancel_one() ");
        if (this.d) {
            downEntity.download_cancel = true;
        } else {
            Trace.w("DLManager", "cancel_all() invalid,because the task isn't downloading.");
        }
    }

    public boolean deleteTempFile(DownEntity downEntity) {
        return new File(downEntity.file_path).delete() && d.d(d.a(downEntity).getAbsolutePath());
    }

    public boolean execAsync(IOnDownListener iOnDownListener) {
        Trace.d("DLManager", "execAsync() start. --%s", Integer.valueOf(j.getActiveCount()));
        if (this.d) {
            Trace.d("DLManager", "Download task has begun, can't repeat it.");
            return false;
        }
        synchronized (this) {
            if (this.d) {
                Trace.d("DLManager", "Download task has begun, can't repeat it.");
                return false;
            }
            this.d = true;
            if (this.f1878a.size() == 0) {
                Trace.e("DLManager", "execAsync() e = down entity is null");
                this.d = false;
                return false;
            }
            if (iOnDownListener != null) {
                this.c = iOnDownListener;
            }
            CallBackManager.getInstance().setListener(this.c);
            this.f.execute(new b());
            return true;
        }
    }

    public boolean execute(IOnDownListener iOnDownListener) {
        Trace.d("DLManager", "execute() ");
        if (this.d) {
            Trace.d("DLManager", "Download task has begun, can't repeat it.");
            return false;
        }
        synchronized (this) {
            if (this.d) {
                Trace.d("DLManager", "Download task has begun, can't repeat it.");
                return false;
            }
            this.d = true;
            if (this.f1878a.size() == 0) {
                Trace.e("DLManager", "execAsync() e = down entity is null");
                this.d = false;
                return false;
            }
            if (iOnDownListener != null) {
                this.c = iOnDownListener;
            }
            CallBackManager.getInstance().setListener(this.c);
            for (DownEntity downEntity : this.f1878a) {
                this.b.addAll(c.a(downEntity));
            }
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (!this.b.get(i2).a()) {
                    this.f.execute(this.b.get(i2));
                }
            }
            FutureTask futureTask = new FutureTask(new com.abupdate.iot_download_libs.a());
            futureTask.run();
            try {
                Trace.d("DLManager", "execute() :" + ((Boolean) futureTask.get()));
            } catch (Exception e) {
                Trace.e("DLManager", e);
            }
            return true;
        }
    }

    public List<DownEntity> getEntity_list() {
        return this.f1878a;
    }

    public IOnDownListener getListener() {
        return this.c;
    }

    public List<com.abupdate.iot_download_libs.b> getThread_list() {
        return this.b;
    }

    public boolean is_downloading() {
        return this.d;
    }

    public void setCallbackOnUIThread(boolean z) {
        if (!z) {
            CallBackManager.getInstance().setCallbackOnUIThread((Handler) null);
            return;
        }
        CallBackManager.getInstance().setCallbackOnUIThread(new Handler(Looper.getMainLooper()));
    }

    public void setContext(Context context) {
        this.mCx = context;
    }
}
