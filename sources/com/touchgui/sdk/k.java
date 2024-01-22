package com.touchgui.sdk;

import android.os.Handler;
import android.os.Looper;
import com.touchgui.sdk.TGFileTransfer;
import com.touchgui.sdk.bean.TGFileOffset;
import com.touchgui.sdk.bean.TGFileResult;
import com.touchgui.sdk.exception.TGException;
import com.touchgui.sdk.internal.h8;
import com.touchgui.sdk.internal.m3;
import com.touchgui.sdk.internal.n3;
import com.touchgui.sdk.internal.q3;
import com.touchgui.sdk.internal.r3;
import com.touchgui.sdk.internal.t3;
import com.touchgui.sdk.internal.u9;
import com.touchgui.sdk.internal.v9;
import com.touchgui.sdk.internal.x9;
import com.touchgui.sdk.internal.y9;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class k implements TGProgressCallback, TGFileTransfer {

    /* renamed from: a  reason: collision with root package name */
    public final com.touchgui.sdk.internal.a0 f13855a;
    public File d;
    public String e;
    public int f;
    public final int k;
    public m3 m;
    public final Handler b = new Handler(Looper.getMainLooper());
    public final CopyOnWriteArrayList c = new CopyOnWriteArrayList();
    public int g = 10;
    public int h = 0;
    public int i = 0;
    public int j = 0;
    public boolean l = false;

    public k(com.touchgui.sdk.internal.a0 a0Var) {
        this.f13855a = a0Var;
        this.k = a0Var.g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TGFileResult tGFileResult) {
        int checkSum = tGFileResult.getCheckSum();
        String md5 = tGFileResult.getMd5();
        com.touchgui.sdk.internal.a0 a0Var = this.f13855a;
        r3 r3Var = new r3();
        ByteBuffer b = r3Var.b(36);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putInt(checkSum);
        b.put(md5.toLowerCase().getBytes());
        m3 m3Var = new m3(a0Var, r3Var);
        m3Var.f13752a = 30000;
        m3Var.execute(new x9(this));
    }

    @Override // com.touchgui.sdk.TGFileTransfer
    public final void abortTransfer() {
        if (this.l) {
            TGLogger.w(this.f13855a, "File transfer is aborted");
        } else if (!this.f13855a.i()) {
            TGLogger.w(this.f13855a, "Aborted file transfer is not supported");
        } else {
            this.l = true;
            m3 m3Var = this.m;
            if (m3Var != null) {
                m3Var.cancel();
                this.m = null;
                com.touchgui.sdk.internal.a0 a0Var = this.f13855a;
                h8 h8Var = new h8((byte) -47, (byte) 2, 1);
                if (h8Var.d == null) {
                    ByteBuffer allocate = ByteBuffer.allocate(20);
                    h8Var.d = allocate;
                    allocate.order(ByteOrder.LITTLE_ENDIAN);
                }
                ByteBuffer byteBuffer = h8Var.d;
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                byteBuffer.put((byte) 1);
                m3 m3Var2 = new m3(a0Var, h8Var);
                m3Var2.b = 1;
                m3Var2.execute(null);
            }
        }
    }

    @Override // com.touchgui.sdk.TGFileTransfer
    public final void addOnProgressListener(TGFileTransfer.OnProgressListener onProgressListener) {
        if (this.c.contains(onProgressListener)) {
            return;
        }
        this.c.add(onProgressListener);
    }

    @Override // com.touchgui.sdk.TGCallback
    /* renamed from: b */
    public final void onSuccess(final TGFileResult tGFileResult) {
        if (tGFileResult != null && tGFileResult.isSuccess()) {
            this.b.post(new Runnable() { // from class: com.touchgui.sdk.a0
                @Override // java.lang.Runnable
                public final void run() {
                    k.this.a(tGFileResult);
                }
            });
        } else if (tGFileResult != null && tGFileResult.getCode() == 126) {
            a();
        } else if (tGFileResult == null || tGFileResult.getCode() != 127) {
            a(TGException.otaFileError(tGFileResult != null ? tGFileResult.getCode() : -1));
        } else {
            TGFileOffset tGFileOffset = new TGFileOffset(this.i, this.h);
            com.touchgui.sdk.internal.a0 a0Var = this.f13855a;
            int offset = tGFileOffset.getOffset();
            int checkCode = tGFileOffset.getCheckCode();
            q3 q3Var = new q3();
            ByteBuffer b = q3Var.b(8);
            b.order(ByteOrder.LITTLE_ENDIAN);
            b.putInt(offset);
            b.putInt(checkCode);
            m3 m3Var = new m3(a0Var, q3Var);
            m3Var.f13752a = 30000;
            m3Var.execute(new y9(this));
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        int i;
        if ((th instanceof TGException) && ((TGException) th).getCode() == 10009) {
            a(th);
            return;
        }
        com.touchgui.sdk.internal.a0 a0Var = this.f13855a;
        if (a0Var.e.f && (i = this.j) < this.k) {
            this.j = i + 1;
            TGLogger.d(a0Var, "File transfer, retryCount=" + this.j);
            TGFileOffset tGFileOffset = new TGFileOffset(this.i, this.h);
            com.touchgui.sdk.internal.a0 a0Var2 = this.f13855a;
            int offset = tGFileOffset.getOffset();
            int checkCode = tGFileOffset.getCheckCode();
            q3 q3Var = new q3();
            ByteBuffer b = q3Var.b(8);
            b.order(ByteOrder.LITTLE_ENDIAN);
            b.putInt(offset);
            b.putInt(checkCode);
            m3 m3Var = new m3(a0Var2, q3Var);
            m3Var.f13752a = 30000;
            m3Var.execute(new y9(this));
            return;
        }
        a(th);
    }

    @Override // com.touchgui.sdk.TGProgressCallback
    public final void onProgress(int i, int i2, int i3) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((TGFileTransfer.OnProgressListener) it.next()).onProgress(i, i2, i3);
        }
    }

    @Override // com.touchgui.sdk.TGFileTransfer
    public final void removeOnProgressListener(TGFileTransfer.OnProgressListener onProgressListener) {
        this.c.remove(onProgressListener);
    }

    @Override // com.touchgui.sdk.TGFileTransfer
    public final void setTotalFileSize(long j, TGCallback tGCallback) {
        n3 n3Var = new n3();
        ByteBuffer b = n3Var.b(8);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putInt((int) j);
        b.putInt(0);
        new m3(this.f13855a, n3Var).execute(new u9(tGCallback));
    }

    @Override // com.touchgui.sdk.TGFileTransfer
    public final void transfer(File file, String str, int i) {
        this.d = file;
        this.e = str;
        this.f = i;
        this.f13855a.x = true;
        this.j = 0;
        this.h = 0;
        this.i = 0;
        this.l = false;
        this.m = null;
        int length = (int) file.length();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((TGFileTransfer.OnProgressListener) it.next()).onProgress(0, 0, length);
        }
        String str2 = this.e;
        new m3(this.f13855a, t3.a(this.f, this.d.length(), str2)).execute(new v9(this));
    }

    public final void a() {
        this.f13855a.x = false;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((TGFileTransfer.OnProgressListener) it.next()).onCompleted();
        }
    }

    public final void a(Throwable th) {
        this.f13855a.x = false;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((TGFileTransfer.OnProgressListener) it.next()).onError(th);
        }
    }
}
