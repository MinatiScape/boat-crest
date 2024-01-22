package com.touchgui.sdk;

import android.os.Handler;
import android.os.Looper;
import com.touchgui.sdk.TGFileTransfer;
import com.touchgui.sdk.internal.l9;
import com.touchgui.sdk.internal.p6;
import com.touchgui.sdk.internal.q6;
import com.touchgui.sdk.internal.tb;
import com.touchgui.sdk.internal.u3;
import com.touchgui.sdk.internal.v3;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class h implements TGFileTransfer.OnProgressListener {

    /* renamed from: a  reason: collision with root package name */
    public final com.touchgui.sdk.internal.a0 f13737a;
    public long e;
    public long f;
    public int g;
    public int h;
    public final k i;
    public String k;
    public TGOTACallback l;
    public final Handler b = new Handler(Looper.getMainLooper());
    public final ArrayList c = new ArrayList();
    public int d = 0;
    public final AtomicBoolean j = new AtomicBoolean(false);

    public h(com.touchgui.sdk.internal.a0 a0Var) {
        this.f13737a = a0Var;
        k j = a0Var.j();
        this.i = j;
        j.addOnProgressListener(this);
    }

    public final void a() {
        if (this.d >= this.c.size()) {
            this.b.post(new Runnable() { // from class: com.touchgui.sdk.w
                @Override // java.lang.Runnable
                public final void run() {
                    h.this.b();
                }
            });
            return;
        }
        File file = new File((String) this.c.get(this.d));
        this.i.transfer(file, file.getName(), u3.a(file.getName()));
        int i = this.d + 1;
        this.d = i;
        TGOTACallback tGOTACallback = this.l;
        if (tGOTACallback != null) {
            tGOTACallback.onFileProgress(i, this.c.size());
        }
    }

    /* renamed from: b */
    public final void a(Throwable th) {
        com.touchgui.sdk.internal.a0 a0Var = this.f13737a;
        TGLogger.d(a0Var, "OTA failure：" + th.getMessage());
        String str = this.k;
        if (str != null) {
            v3.b(new File(str));
        }
        this.j.set(false);
        TGOTACallback tGOTACallback = this.l;
        if (tGOTACallback != null) {
            tGOTACallback.onError(th);
        }
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onCompleted() {
        this.f += this.g;
        this.b.post(new Runnable() { // from class: com.touchgui.sdk.v
            @Override // java.lang.Runnable
            public final void run() {
                h.this.a();
            }
        });
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onError(final Throwable th) {
        this.b.post(new Runnable() { // from class: com.touchgui.sdk.z
            @Override // java.lang.Runnable
            public final void run() {
                h.this.a(th);
            }
        });
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onProgress(int i, int i2, int i3) {
        this.g = i3;
        int i4 = (int) (((this.f + i2) * 100) / this.e);
        if (this.h != i4) {
            this.h = i4;
            TGOTACallback tGOTACallback = this.l;
            if (tGOTACallback != null) {
                tGOTACallback.onProgress(i4);
            }
        }
    }

    public final void b() {
        TGLogger.d(this.f13737a, "reboot device");
        com.touchgui.sdk.internal.a0 a0Var = this.f13737a;
        if (a0Var.k == null) {
            a0Var.k = new l9(a0Var.j);
        }
        a0Var.k.reboot().execute(new q6(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, List list) {
        try {
            tb.a(str, this.k);
            this.d = 0;
            this.f = 0L;
            this.g = 0;
            this.h = 0;
            this.c.clear();
            ArrayList arrayList = new ArrayList();
            String str2 = this.k;
            ArrayList arrayList2 = new ArrayList();
            File[] listFiles = new File(str2).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (!file.isDirectory()) {
                        arrayList2.add(file.getPath());
                    }
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (!u3.b(str3)) {
                    TGLogger.w(this.f13737a, "unknown file:" + str3);
                } else if (list == null || list.size() <= 0) {
                    arrayList.add(str3);
                } else {
                    Iterator it2 = list.iterator();
                    boolean z = false;
                    while (it2.hasNext()) {
                        if (str3.endsWith((String) it2.next())) {
                            arrayList.add(str3);
                            z = true;
                        }
                    }
                    if (!z) {
                        new File(str3).delete();
                    }
                }
            }
            this.c.addAll(arrayList);
            Collections.sort(this.c);
            long c = v3.c(new File(this.k));
            this.e = c;
            if (this.c.size() > 0) {
                this.i.setTotalFileSize(c, new p6(this));
            } else {
                a();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.b.post(new Runnable() { // from class: com.touchgui.sdk.x
                @Override // java.lang.Runnable
                public final void run() {
                    h.this.a(e);
                }
            });
        }
    }

    public final void a(final String str, final ArrayList arrayList) {
        new Thread(new Runnable() { // from class: com.touchgui.sdk.y
            @Override // java.lang.Runnable
            public final void run() {
                h.this.a(str, arrayList);
            }
        }).start();
    }
}
