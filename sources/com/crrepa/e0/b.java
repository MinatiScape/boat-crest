package com.crrepa.e0;

import com.crrepa.a0.c;
import com.crrepa.a0.d;
import com.crrepa.ble.R;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.i0.f;
import java.io.File;
/* loaded from: classes9.dex */
public class b extends c {
    public CRPBleFirmwareUpgradeListener c;
    public File d;

    /* renamed from: com.crrepa.e0.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0338b {

        /* renamed from: a  reason: collision with root package name */
        public static b f7704a = new b();
    }

    public b() {
    }

    public static b a() {
        return C0338b.f7704a;
    }

    public void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        this.c = cRPBleFirmwareUpgradeListener;
    }

    public void a(File file) {
        this.d = file;
    }

    public void abort() {
        sendFileCheckResult(false);
        release();
    }

    public void b() {
        String b = f.b();
        com.crrepa.i0.c.c("firmwareVersion: " + b);
        createFileManager(this.d, d.a(b), 0);
        if (this.mTransFileManager == null) {
            i(false);
            return;
        }
        startTrans();
        startTimer();
    }

    @Override // com.crrepa.a0.c
    public int getTransType() {
        return 99;
    }

    public final void i(boolean z) {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.c;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onError(23, f.a().getString(R.string.dfu_status_error_msg));
        }
        if (z) {
            sendFileCheckResult(false);
        }
        release();
    }

    @Override // com.crrepa.a0.c
    public void onCrcFail() {
        i(false);
    }

    @Override // com.crrepa.a0.c
    public void onTimeoutError() {
        i(true);
    }

    @Override // com.crrepa.a0.c
    public void onTransChanged(int i) {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.c;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onUpgradeProgressChanged(i, 1.0f);
        }
    }

    @Override // com.crrepa.a0.c
    public void onTransComplete() {
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.c;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onUpgradeCompleted();
        }
    }

    @Override // com.crrepa.a0.c
    public void onTransFileError() {
        i(true);
    }

    @Override // com.crrepa.a0.c
    public void onTransFileNull() {
        i(true);
    }
}
