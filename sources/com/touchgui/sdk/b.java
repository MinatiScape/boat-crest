package com.touchgui.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.touchgui.sdk.TGDialManager;
import com.touchgui.sdk.TGFileTransfer;
import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.exception.TGException;
import com.touchgui.sdk.internal.c5;
import com.touchgui.sdk.internal.ea;
import com.touchgui.sdk.internal.n2;
import com.touchgui.sdk.internal.o2;
import com.touchgui.sdk.internal.p2;
import com.touchgui.sdk.internal.q2;
import com.touchgui.sdk.internal.s2;
import com.touchgui.sdk.internal.t2;
import com.touchgui.sdk.internal.u3;
import com.touchgui.sdk.internal.u8;
import com.touchgui.sdk.internal.v8;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class b implements TGFileTransfer.OnProgressListener {

    /* renamed from: a  reason: collision with root package name */
    public final com.touchgui.sdk.internal.a0 f13731a;
    public int c;
    public TGDial d;
    public int e;
    public final k f;
    public p2 k;
    public final Handler b = new Handler(Looper.getMainLooper());
    public final Queue g = new ConcurrentLinkedDeque();
    public int h = 0;
    public int i = 0;
    public final AtomicBoolean j = new AtomicBoolean(false);

    public b(com.touchgui.sdk.internal.a0 a0Var) {
        this.f13731a = a0Var;
        k j = a0Var.j();
        this.f = j;
        j.addOnProgressListener(this);
    }

    public final void a(TGDial tGDial) {
        this.d = tGDial;
        this.c = tGDial.getDialId();
        ((ConcurrentLinkedDeque) this.g).clear();
        ((ConcurrentLinkedDeque) this.g).add(new q2(tGDial.getFilePath()));
        this.h = ((ConcurrentLinkedDeque) this.g).size();
        this.i = 0;
        this.e = 0;
        p2 p2Var = this.k;
        if (p2Var != null) {
            Iterator it = ((i) p2Var).f13738a.b.iterator();
            while (it.hasNext()) {
                ((TGDialManager.OnSyncDialListener) it.next()).onProgress(0);
            }
        }
        int i = this.c;
        int i2 = 1;
        if (!this.f13731a.a(TGFunctions.FUNC_REPLACE_DIAL)) {
            tGDial = null;
        } else if (tGDial.getReplacedDialId() > 0) {
            i2 = 4;
        }
        new v8(this.f13731a, u8.a(i, i2, tGDial)).execute(new n2(this));
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onCompleted() {
        this.b.post(new Runnable() { // from class: com.touchgui.sdk.p
            @Override // java.lang.Runnable
            public final void run() {
                b.this.a();
            }
        });
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onError(Throwable th) {
        a(th);
    }

    @Override // com.touchgui.sdk.TGFileTransfer.OnProgressListener
    public final void onProgress(int i, int i2, int i3) {
        int i4 = this.h;
        int i5 = ((i2 * 100) / i4) / i3;
        if (this.e != i5) {
            this.e = i5;
            int i6 = (((this.i - 1) * 100) / i4) + i5;
            com.touchgui.sdk.internal.a0 a0Var = this.f13731a;
            TGLogger.d(a0Var, "progress=" + i6);
            p2 p2Var = this.k;
            if (p2Var != null) {
                Iterator it = ((i) p2Var).f13738a.b.iterator();
                while (it.hasNext()) {
                    ((TGDialManager.OnSyncDialListener) it.next()).onProgress(i6);
                }
            }
        }
    }

    public final void a(Throwable th) {
        com.touchgui.sdk.internal.a0 a0Var = this.f13731a;
        TGLogger.d(a0Var, "sync dial failure：" + th.getMessage());
        this.j.set(false);
        p2 p2Var = this.k;
        if (p2Var != null) {
            Iterator it = ((i) p2Var).f13738a.b.iterator();
            while (it.hasNext()) {
                ((TGDialManager.OnSyncDialListener) it.next()).onError(th);
            }
        }
    }

    public final void a(i iVar) {
        this.k = iVar;
    }

    public final boolean a(ea eaVar, boolean z) {
        int i;
        int i2;
        Context context = this.f13731a.d;
        t2 a2 = s2.a(context, eaVar.getResWatch(), this.f13731a.b);
        boolean z2 = false;
        String str = null;
        if (a2 != null) {
            int i3 = eaVar.c;
            if (i3 > 0 && (i2 = eaVar.d) > 0) {
                a2.b(i3, i2);
            }
            int i4 = eaVar.e;
            if (i4 > 0 && (i = eaVar.f) > 0) {
                a2.a(i4, i);
            }
            int d = this.f13731a.d();
            a2.a((d == 534 || d == 536 || d == 537 || d == 538 || d == 539) ? 1 : 0);
            boolean a3 = a2.a(eaVar.getResWatch());
            if (a3) {
                a2.a(eaVar.f13760a);
                a2.b(eaVar.b);
                if (!TextUtils.isEmpty(eaVar.g)) {
                    a2.a(eaVar.g);
                }
                a2.c(eaVar.i);
                File file = new File(context.getCacheDir(), String.format(Locale.getDefault(), "%d.watch", Long.valueOf(new Date().getTime())));
                String absolutePath = file.getAbsolutePath();
                boolean b = a2.b(file);
                if (!b || !z || eaVar.h != 3) {
                    z2 = b;
                } else if (file.length() <= a2.a()) {
                    z2 = true;
                }
                str = absolutePath;
            } else {
                z2 = a3;
            }
            a2.b();
        }
        if (z) {
            return z2;
        }
        if (z2) {
            c5 c5Var = new c5(eaVar.getDialId(), str, Boolean.TRUE);
            c5Var.setReplacedDialId(eaVar.getReplacedDialId());
            a(c5Var);
        } else {
            a(new TGException("Failed to load .watch file", 60001));
        }
        return true;
    }

    public final void a() {
        q2 q2Var = (q2) ((ConcurrentLinkedDeque) this.g).poll();
        if (q2Var != null) {
            this.i++;
            this.f.transfer(new File(q2Var.f13816a), "cfg_res.watch", u3.a("cfg_res.watch"));
            return;
        }
        p2 p2Var = this.k;
        if (p2Var != null) {
            Iterator it = ((i) p2Var).f13738a.b.iterator();
            while (it.hasNext()) {
                ((TGDialManager.OnSyncDialListener) it.next()).onInstalling();
            }
        }
        int i = this.c;
        TGDial tGDial = this.d;
        if (!this.f13731a.a(TGFunctions.FUNC_REPLACE_DIAL)) {
            tGDial = null;
        }
        v8 v8Var = new v8(this.f13731a, u8.a(i, 3, tGDial));
        v8Var.f13752a = 60000;
        v8Var.execute(new o2(this));
    }
}
