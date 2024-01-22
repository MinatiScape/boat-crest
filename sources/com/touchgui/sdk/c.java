package com.touchgui.sdk;

import android.os.Handler;
import android.os.Looper;
import com.touchgui.sdk.TGFileTransfer;
import com.touchgui.sdk.exception.TGException;
import com.touchgui.sdk.internal.a4;
import com.touchgui.sdk.internal.m8;
import com.touchgui.sdk.internal.tb;
import com.touchgui.sdk.internal.u3;
import com.touchgui.sdk.internal.u8;
import com.touchgui.sdk.internal.v3;
import com.touchgui.sdk.internal.v8;
import com.touchgui.sdk.internal.x3;
import com.touchgui.sdk.internal.y3;
import com.touchgui.sdk.internal.z3;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class c implements TGFileTransfer.OnProgressListener, x3 {

    /* renamed from: a  reason: collision with root package name */
    public final com.touchgui.sdk.internal.a0 f13733a;
    public final k d;
    public long h;
    public long i;
    public int j;
    public int k;
    public final Handler b = new Handler(Looper.getMainLooper());
    public final CopyOnWriteArrayList c = new CopyOnWriteArrayList();
    public final ArrayList e = new ArrayList();
    public final HashMap f = new HashMap();
    public int g = 0;
    public final AtomicBoolean l = new AtomicBoolean(false);
    public int m = 0;

    public c(com.touchgui.sdk.internal.a0 a0Var) {
        this.f13733a = a0Var;
        k j = a0Var.j();
        this.d = j;
        j.addOnProgressListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.m = 0;
        this.m = 1;
        com.touchgui.sdk.internal.a0 a0Var = this.f13733a;
        TGLogger.d(a0Var, "Number of polling file write status: " + this.m);
        com.touchgui.sdk.internal.a0 a0Var2 = this.f13733a;
        HashMap hashMap = u8.f13829a;
        m8 m8Var = new m8();
        ByteBuffer b = m8Var.b(18);
        b.put((byte) 1);
        b.put((byte) 3);
        new v8(a0Var2, m8Var).execute(new a4(this));
    }

    public final void a() {
        if (this.g >= this.e.size()) {
            this.b.post(new Runnable() { // from class: com.touchgui.sdk.q
                @Override // java.lang.Runnable
                public final void run() {
                    c.this.b();
                }
            });
            return;
        }
        ArrayList arrayList = this.e;
        int i = this.g;
        this.g = i + 1;
        String str = (String) arrayList.get(i);
        File file = (File) this.f.get(str);
        if (file != null) {
            this.d.transfer(file, str, u3.a(str));
        } else {
            a();
        }
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onCompleted() {
        this.i += this.j;
        this.m = 1;
        TGLogger.d(this.f13733a, "Number of polling device status for write AGPS file: " + this.m);
        com.touchgui.sdk.internal.a0 a0Var = this.f13733a;
        HashMap hashMap = u8.f13829a;
        m8 m8Var = new m8();
        ByteBuffer b = m8Var.b(18);
        b.put((byte) 2);
        b.put((byte) 3);
        new v8(a0Var, m8Var).execute(new z3(this));
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onError(Throwable th) {
        a(th);
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onProgress(int i, int i2, int i3) {
        this.j = i3;
        int i4 = (int) (((this.i + i2) * 100) / this.h);
        if (this.k != i4) {
            this.k = i4;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ((TGSyncAgpsFileListener) it.next()).onProgress(i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Exception exc) {
        a((Throwable) new TGException(exc.getMessage(), TGErrorCode.ERROR_AGPS_UNZIP_FILE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            String absolutePath = new File(this.f13733a.d.getCacheDir(), "TGApgs").getAbsolutePath();
            v3.b(new File(absolutePath));
            tb.a(str, absolutePath);
            this.e.clear();
            this.f.clear();
            ArrayList arrayList = new ArrayList();
            File[] listFiles = new File(absolutePath).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (!file.isDirectory()) {
                        arrayList.add(file.getPath());
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (u3.b(str2)) {
                    File file2 = new File(str2);
                    String name = file2.getName();
                    if (name.contains("LTO2")) {
                        name = "LTO2.bin";
                    } else if (name.contains("LTO7")) {
                        name = "LTO7.bin";
                    }
                    this.f.put(name, file2);
                    this.e.add(name);
                } else {
                    TGLogger.w(this.f13733a, "unknown file:" + str2);
                }
            }
            long c = v3.c(new File(absolutePath));
            this.g = 0;
            this.i = 0L;
            this.h = c;
            if (this.e.size() > 0) {
                this.d.setTotalFileSize(c, new y3(this));
                return;
            }
            this.m = 1;
            TGLogger.d(this.f13733a, "Number of polling device status for write AGPS file: " + this.m);
            com.touchgui.sdk.internal.a0 a0Var = this.f13733a;
            HashMap hashMap = u8.f13829a;
            m8 m8Var = new m8();
            ByteBuffer b = m8Var.b(18);
            b.put((byte) 2);
            b.put((byte) 3);
            new v8(a0Var, m8Var).execute(new z3(this));
        } catch (Exception e) {
            e.printStackTrace();
            this.b.post(new Runnable() { // from class: com.touchgui.sdk.s
                @Override // java.lang.Runnable
                public final void run() {
                    c.this.a(e);
                }
            });
        }
    }

    public final void b(final String str) {
        com.touchgui.sdk.internal.a0 a0Var = this.f13733a;
        TGLogger.d(a0Var, "AGPS, filePath=" + str);
        if (this.l.getAndSet(true)) {
            TGLogger.w(this.f13733a, "Update AGPS in progress");
        } else {
            new Thread(new Runnable() { // from class: com.touchgui.sdk.t
                @Override // java.lang.Runnable
                public final void run() {
                    c.this.a(str);
                }
            }).start();
        }
    }

    public final void a(Throwable th) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((TGSyncAgpsFileListener) it.next()).onError(th);
        }
        this.l.set(false);
    }
}
