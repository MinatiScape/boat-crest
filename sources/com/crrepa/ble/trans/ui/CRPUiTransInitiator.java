package com.crrepa.ble.trans.ui;

import com.crrepa.a0.c;
import com.crrepa.a0.d;
import com.crrepa.ble.conn.listener.CRPTransListener;
import java.io.File;
/* loaded from: classes9.dex */
public class CRPUiTransInitiator extends c {
    public CRPTransListener c;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final CRPUiTransInitiator f7685a = new CRPUiTransInitiator();
    }

    public CRPUiTransInitiator() {
    }

    public static CRPUiTransInitiator getInstance() {
        return b.f7685a;
    }

    public void abort() {
        sendFileCheckResult(false);
        release();
    }

    @Override // com.crrepa.a0.c
    public int getTransType() {
        return 108;
    }

    public final void i(int i, boolean z) {
        CRPTransListener cRPTransListener = this.c;
        if (cRPTransListener != null) {
            cRPTransListener.onError(i);
        }
        if (z) {
            sendFileCheckResult(false);
        }
        release();
    }

    @Override // com.crrepa.a0.c
    public void onCrcFail() {
        i(2, false);
    }

    @Override // com.crrepa.a0.c
    public void onTimeoutError() {
        i(4, true);
    }

    @Override // com.crrepa.a0.c
    public void onTransChanged(int i) {
        CRPTransListener cRPTransListener = this.c;
        if (cRPTransListener != null) {
            cRPTransListener.onTransProgressChanged(i);
        }
    }

    @Override // com.crrepa.a0.c
    public void onTransComplete() {
        CRPTransListener cRPTransListener = this.c;
        if (cRPTransListener != null) {
            cRPTransListener.onTransCompleted();
        }
    }

    @Override // com.crrepa.a0.c
    public void onTransFileError() {
        i(2, true);
    }

    @Override // com.crrepa.a0.c
    public void onTransFileNull() {
        i(1, true);
    }

    public void setListener(CRPTransListener cRPTransListener) {
        this.c = cRPTransListener;
    }

    public void start(File file, int i, String str) {
        createFileManager(file, d.a(str), i);
        if (this.mTransFileManager == null) {
            i(1, false);
            return;
        }
        startTrans();
        startTimer();
    }
}
