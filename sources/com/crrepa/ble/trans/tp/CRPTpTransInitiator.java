package com.crrepa.ble.trans.tp;

import com.crrepa.a0.c;
import com.crrepa.a0.d;
import com.crrepa.ble.conn.listener.CRPTransListener;
import com.crrepa.f.d1;
import com.crrepa.i0.e;
/* loaded from: classes9.dex */
public class CRPTpTransInitiator extends c {
    public CRPTransListener c;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final CRPTpTransInitiator f7684a = new CRPTpTransInitiator();
    }

    public CRPTpTransInitiator() {
    }

    public static CRPTpTransInitiator getInstance() {
        return b.f7684a;
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

    public void sendTransLength(int i) {
        long d = this.mTransFileManager.d();
        if (d < 0) {
            i(1, false);
            return;
        }
        byte[] a2 = e.a(d);
        byte[] a3 = e.a(i);
        byte[] bArr = new byte[a2.length + a3.length];
        System.arraycopy(a2, 0, bArr, 0, a2.length);
        System.arraycopy(a3, 0, bArr, a2.length, a3.length);
        sendBleMessage(d1.a(getTransType(), bArr));
    }

    public void start(CRPTpInfo cRPTpInfo, CRPTransListener cRPTransListener) {
        if (cRPTpInfo == null) {
            i(1, false);
            return;
        }
        this.c = cRPTransListener;
        createFileManager(cRPTpInfo.getFile(), d.a(cRPTpInfo.getFirmwareVersion()), cRPTpInfo.getStartIndex());
        setTransLength(cRPTpInfo.getLength());
        if (this.mTransFileManager == null) {
            i(1, false);
            return;
        }
        sendTransLength(cRPTpInfo.getDeviceStartIndex());
        startTimer();
    }
}
