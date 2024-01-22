package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothDevice;
import com.touchgui.sdk.TGLogger;
/* loaded from: classes12.dex */
public final class p implements g8 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13810a = false;
    public boolean b = false;
    public final /* synthetic */ q c;

    public p(q qVar) {
        this.c = qVar;
    }

    @Override // com.touchgui.sdk.internal.g8
    public final void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice.getAddress().equalsIgnoreCase(this.c.n)) {
            TGLogger.d(this.c.n, "found device");
            this.f13810a = true;
            this.c.o.a(false).booleanValue();
            this.c.c(true);
        }
    }

    @Override // com.touchgui.sdk.internal.g8
    public final void onScanFailed(int i) {
        String str = this.c.n;
        TGLogger.e(str, "scan failed:" + i);
        this.c.a(this.b, true);
    }

    @Override // com.touchgui.sdk.internal.g8
    public final void onScanFinished() {
        String str = this.c.n;
        TGLogger.d(str, "scan finished: found=" + this.f13810a + ", clear=" + this.b);
        if (this.b) {
            q qVar = this.c;
            synchronized (qVar.l) {
                qVar.h();
            }
            this.c.b();
            return;
        }
        this.c.c(false);
    }
}
