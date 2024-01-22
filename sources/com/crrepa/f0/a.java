package com.crrepa.f0;

import com.crrepa.ble.conn.listener.CRPFileTransListener;
import java.io.File;
/* loaded from: classes9.dex */
public class a extends com.crrepa.a0.c {
    public CRPFileTransListener c;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static a f7715a = new a();
    }

    public a() {
    }

    public static a a() {
        return b.f7715a;
    }

    public void a(int i) {
        this.timeout = i;
    }

    public void a(CRPFileTransListener cRPFileTransListener) {
        this.c = cRPFileTransListener;
    }

    public void a(File file) {
        createFileManager(file, com.crrepa.l.a.b().g() ? com.crrepa.l.a.b().c() : 256, 0);
        if (this.mTransFileManager == null) {
            i(1, false);
            return;
        }
        startTrans();
        startTimer();
    }

    public void abort() {
        sendFileCheckResult(false);
        release();
    }

    @Override // com.crrepa.a0.c
    public int getTransType() {
        return 116;
    }

    public final void i(int i, boolean z) {
        CRPFileTransListener cRPFileTransListener = this.c;
        if (cRPFileTransListener != null) {
            cRPFileTransListener.onError(i);
        }
        if (z) {
            sendFileCheckResult(false);
        }
        release();
    }

    @Override // com.crrepa.a0.c
    public void onCrcFail() {
        i(3, false);
    }

    @Override // com.crrepa.a0.c
    public void onTimeoutError() {
        i(2, true);
    }

    @Override // com.crrepa.a0.c
    public void onTransChanged(int i) {
        CRPFileTransListener cRPFileTransListener = this.c;
        if (cRPFileTransListener != null) {
            cRPFileTransListener.onTransProgressChanged(i);
        }
    }

    @Override // com.crrepa.a0.c
    public void onTransComplete() {
        CRPFileTransListener cRPFileTransListener = this.c;
        if (cRPFileTransListener != null) {
            cRPFileTransListener.onTransCompleted();
        }
    }

    @Override // com.crrepa.a0.c
    public void onTransFileError() {
        i(4, true);
    }

    @Override // com.crrepa.a0.c
    public void onTransFileNull() {
        i(1, true);
    }
}
